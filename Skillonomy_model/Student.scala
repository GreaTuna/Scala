import scala.util.Random
import scala.collection.mutable.Map

class Student(
  _name: String,
  _surname: String,
  _age: Int,
  _address: Address,
  _wallet:  Wallet,
  _userId: String,
  private var _myCourses:List[String] = List.empty) extends User(_name, _surname, _age, _address, _wallet, _userId) {
  
  def this() = this("Ajax", "Lebowski", 20, new Address, new Wallet, generateId())
  
  // Гетеры сетеры
  def myCourses: List[String] = _myCourses
  def myCourses_=(newMyCourses: List[String]): Unit = _myCourses = newMyCourses

  def signUpToCourse(stock: Stock, data : Data, platform: Platform) : Unit = {
    
    val random = new Random()
    val availableCourses = data.coursesData.coursesList.filter(course => !_myCourses.contains(course.courseId))  // Исключаем курсы на которые студент уже записан
    
    if (availableCourses.nonEmpty) {  // Проверка на то, есть ли курсы на которые может записатся студент
      val randomIndexOfCourses = random.nextInt(availableCourses.length)  // Выбор случайного индекса из списка доступных курсов
      val randomeCourse = availableCourses(randomIndexOfCourses)  // Случайный курс из списка доступных курсов
      val price = randomeCourse.coursePrice

      var fullName = _name + " " + _surname

      platform.transactionSuccessCheck(stock, price, _wallet)
      platform.courseRegistrationPayment(data, stock, _wallet, fullName, randomeCourse)

      _myCourses = randomeCourse.courseId :: _myCourses  // Добавляем id курса на который он записался в список курсов студента
      
      randomeCourse.students = _userId :: randomeCourse.students  // Добавляем id студента в курс на который он записался
      randomeCourse.studentScores += (_userId -> new CourseGrades())  // Создаем для студента список оценок за курс, по клюу в виде его уникального id
    }
  }

  def myCoursesInfo(data : Data) : Unit = {  // Выводит информацию про курсы на которые записан студент и его оценки
    
    println(s"Студент: ${_name} ${_surname}")
    
    if(_myCourses.nonEmpty) {  // Если студент записан хотябы на один курс
      data.coursesData.coursesList.foreach(course => {

        if(_myCourses.contains(course.courseId)) {
          var studentScores = course.studentScores(_userId).courseGrades.mkString(", ")  // Получаем список оценок за курс и приобразуем его в строку вида 1, 2, 3..., n

          if(studentScores.isEmpty) {  // Если список с оценками пустой 
            studentScores = "Преподаватель ещё не выставил оценки"
          }

          println(
            s"Название курса: ${course.courseName}, " +
            s"Длительность: ${course.courseDuration} месяцев, " +
            s"Преподаватель: ${course.courseAuthor}, " +
            s"Мои оценки: ${studentScores}")
        }
      })
      println()
    }
    else {  // Если студент не записан ни на один курс
      println("Вы пока не зарегистрировались ни на один курс")
    }
  }
}