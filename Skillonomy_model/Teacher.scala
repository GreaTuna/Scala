import scala.util.Random

class Teacher(
  _name: String,
  _surname: String,
  _age: Int,
  _address: Address,
  _wallet: Wallet,
  _userId:String,
  private var _myCourses:List[String] = List.empty) extends User(_name, _surname, _age, _address, _wallet, _userId) {

  def this() = this("Ajax", "Lebowski", 20, new Address, new Wallet, generateId())
  
  // Гетеры сетеры
  def myCourses: List[String] = _myCourses
  def myCourses_=(newMyCourses: List[String]): Unit = _myCourses = newMyCourses
  
  
  // Методы класса
  def myCoursesInfo(data : Data) : Unit = {
    
    if(_myCourses.nonEmpty) {
      data.coursesData.coursesList.foreach(course => {

        if(_myCourses.contains(course.courseId)) {
          course.printCourseInfo()
        }
      })
    }
    else {
      println("Вы пока не создали ни одного курса")
    }
  }

  def createCourse(courseName : String, coursePrice : Double, courseDuration : Int, data : Data, platform: Platform, stock: Stock): Unit = {
    
    val fullName = _name + " " + _surname
    val course = new Course(_userId, "", fullName , courseName, coursePrice, courseDuration)
    val servicePrice = (coursePrice * platform.commission) - coursePrice

    platform.transactionSuccessCheck(stock, servicePrice, _wallet)
    platform.courseCreationPayment(data, stock, _wallet, course)

    _myCourses = course.courseId :: _myCourses
    data.addCourse(course)
  }


  def setScore(course: Course): Unit = {  // Выставляет оценки всем студентам которые записаны на курсы этого преподавателся 
    
    var duration = course.courseDuration
    var scores = course.studentScores  // Словарь с оценками, доступ по уникальному id студента

    scores.keys.foreach(studentId => { 
      var grades = scores(studentId).courseGrades 
      scores(studentId).courseGrades_=(generateGrade(grades, duration)) 
    })
  }

  def courseLog(data : Data) : Unit = {
    
    if(_myCourses.nonEmpty) {  // Проверка на то, создавал ли преподаватель курсы
      data.coursesData.coursesList.foreach(course => {

        if(_myCourses.contains(course.courseId)) {
          println(s"Курс: ${course.courseName}, Студенты:")

          course.students.foreach(studentId => {
            val student = data.studentsData.studentsList(studentId)    
            var studentScores = course.studentScores(studentId).courseGrades.mkString(", ")  // Получаем список оценок за курс и приобразуем его в строку вида 1, 2, 3..., n

            if(studentScores.isEmpty) {  // Если список с оценками пустой 
              studentScores = "Вы ещё не высиавили оценку этому студенту"
            }

            println(
              s"Имя и фамилия студента: ${student.name} ${student.surname}, " +
              s"Оценки: ${studentScores}")
          })

          println()
        }
      })
    }
    else {
      println("Вы пока не создали ни одного курса")
    }
  }  
}