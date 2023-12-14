class Platform(
    private var _platformFiatBalance: Double,
    private var _platformTokenBalance: Double,
    private var _platformReserveBalance: Double,
    private var _commission: Double) {

    def this() = this(25000, 1000, 0, 1.1)
    
    // Гетеры сетеры
    def platformFiatBalance: Double =_platformFiatBalance
    def platformFiatBalance_=(newPlatformFiatBalance : Double): Unit = _platformFiatBalance = newPlatformFiatBalance

    def platformTokenBalance: Double = _platformTokenBalance
    def platformTokenBalance_=(newPlatformTokenBalance: Double): Unit = _platformTokenBalance = newPlatformTokenBalance

    def platformReserveBalance: Double = _platformReserveBalance
    def platformReserveBalance_=(newPlatformReserveBalance: Double): Unit = _platformReserveBalance = newPlatformReserveBalance

    def commission: Double = _commission
    def commission_=(newCommission: Double): Unit = _commission = newCommission
    
    // Методы класса
    def printEntireBalance(): Unit = {

        println(
            s"Фиат: $_platformFiatBalance грн \n" +
            s"Токены: $_platformTokenBalance токенов \n" +
            s"Резерв: $_platformReserveBalance грн \n"
        )
    }

    def transactionSuccessCheck(stock: Stock, price: Double, balance: Wallet) : Unit = {
        var tokenRate: Double = stock.tokenRate
        var fiat: Double = balance.fiatBalance
        var tokens: Double = balance.tokenBalance
        var totalPrice: Double = (price * _commission) / tokenRate // цена в токенах с учетом комисии 

        println(s"Суммарная стоисость услуги составляет ${"%.2f".format(totalPrice)} токенов, стоисость услуги ${"%.2f".format(price / tokenRate)} токенов + комиссия 10%")

        if(tokens - totalPrice >= 0) {  // Покупаем за токены
            println(s"На вашем балансе ${"%.2f".format(tokens)} токенов. Вы звплатите - ${"%.2f".format(totalPrice)} токенов ") 
        }
        else {
            var missingTokens: Double = totalPrice - tokens   // Сколько токенов не хватает

            if((fiat / tokenRate) - missingTokens >= 0) {   // Покупаем токены за фиатную валюту если на фиатном балансе достаточно средств
                println(s"Вам не хватает ${"%.2f".format(missingTokens)} токенов. На вашем балансе ${"%.2f".format(fiat)} грн, вы можете купить недостающие количество токенов за ${"%.2f".format(missingTokens * tokenRate)} грн")
                stock.buyTokens(this, balance, missingTokens)   
            }
            else {   // Пополняем фиатный баланс на сумму missingTokens*_tokenRate - fiat и покупаем токены за фиатную валюту
                var replenishmentAmount: Double = (missingTokens * tokenRate) - fiat   // Сколько 
                println(s"Вам не хватает ${"%.2f".format(missingTokens)} токенов. На вашем балансе ${"%.2f".format(fiat)} грн, пополните его ещё на ${"%.2f".format(replenishmentAmount)} грн, чтобы купить нужное количество токенов")

                stock.refillBalance(balance, replenishmentAmount)
                stock.buyTokens(this, balance, missingTokens)
            }
        }

        println("Транзакция успешна!")
    }

    def courseRegistrationPayment(data: Data, stock: Stock, studentBalance: Wallet, studentFullName: String, course: Course) : Unit = { 

        var price = course.coursePrice
        var teacherId =  course.ownerId
        var courseName = course.courseName

        var tokenRate: Double = stock.tokenRate
        var tokenPrice: Double = price / tokenRate
        var totalTokenPrice: Double = (price * _commission) / tokenRate
        var commission: Double = totalTokenPrice - tokenPrice
        
        var teacherBalance = data.teachersData.teachersList(teacherId).wallet
        var studentTokenBalance: Double = studentBalance.tokenBalance - totalTokenPrice
        var teacherTokenBalance: Double = teacherBalance.tokenBalance + tokenPrice
        
        studentBalance.tokenBalance_=(studentTokenBalance) // Снимае деньги у студента за регистрацию на курс
        teacherBalance.tokenBalance_=(teacherTokenBalance) // Платим учителя за регестрацию на его курс
        _platformReserveBalance = commission * tokenRate // Алптформа получает комиссию 10%

        println(s"Студент ${studentFullName} зарегестрировался на курс $courseName \n")
    }

    def courseCreationPayment(data: Data, stock: Stock, teacherBalance: Wallet, course: Course) : Unit = {

        var price = course.coursePrice
        var courseName = course.courseName
        var ownerId = course.ownerId
        var creatorName =  data.teachersData.teachersList(ownerId).name
        var creatorSurname =  data.teachersData.teachersList(ownerId).surname
        var creatorsFullName = creatorName + " " + creatorSurname

        var tokenRate: Double = stock.tokenRate
        var tokenPrice: Double = price / tokenRate
        var commission: Double = (tokenPrice * _commission) - tokenPrice
        var teacherTokenBalance: Double = teacherBalance.tokenBalance - commission

        teacherBalance.tokenBalance_=(teacherTokenBalance)
        _platformReserveBalance = commission * tokenRate

        println(s"Учитель $creatorsFullName создал курс $courseName \n")
    }

    def scholarshipAmount(averageScore: Double, coursePrice: Double) : Double = {
        
        var scholarshi: Double = coursePrice
        if (averageScore > 1 &&  averageScore < 2) {
            return (scholarshi * 0.7)
        }
        else if (averageScore >= 2 &&  averageScore < 3) {
            return (scholarshi * 0.8)
        }
        else if (averageScore >= 3 &&  averageScore < 4) {
            return (scholarshi * 0.9)
        }
        else if (averageScore >= 4 &&  averageScore < 5) {
            return (scholarshi * 1)
        }
        else if (averageScore == 5) {
            return (scholarshi * 1.1)
        }
        else {
            return 0
        }
    }

    def scholarshipPayment(course: Course, data: Data, stock: Stock) : Unit = {

        var duration = course.courseDuration
        var scores = course.studentScores
        var coursePrice = course.coursePrice

        scores.keys.filter(key => scores(key).courseGrades.length == duration).foreach(key => {
            val score = scores(key).courseGrades
            var averageScore = averageGradePoint(score)
            var scholarship = scholarshipAmount(averageScore, coursePrice)

            var student = data.studentsData.studentsList(key)
            var studentFullName: String = student.name + " " + student.surname
            var studentFiatBalance: Double = student.wallet.fiatBalance + scholarship
            
            student.wallet.fiatBalance_=(studentFiatBalance)
            println(s"Студент ${studentFullName} получил стипендию за курс ${course.courseName} в размере ${"%.2f".format(scholarship)} грн, средний балл ${"%.2f".format(averageScore)}")

            course.completeСourse(student)
            student.signUpToCourse(stock, data, this)
        })
    }
}