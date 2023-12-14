import scala.collection.mutable.ListBuffer

class Course(
  private var _ownerId: String,
  private var _courseId: String,
  private var _courseAuthor: String,
  private var _courseName: String,
  private var _coursePrice: Double,
  private var _courseDuration: Int,
  private var _students : List[String] = List.empty,
  private var _studentScores: Map[String, CourseGrades] = Map.empty[String, CourseGrades]) {
  
  _courseId = generateId()  // Генерация уникального id курса
  
  // Гетеры сетеры
  def ownerId: String = _ownerId                                          
  def ownerId_=(newOwnerId: String): Unit = _ownerId = newOwnerId

  def courseAuthor: String = _courseAuthor
  def courseAuthor_=(newCourseAuthor: String): Unit = _courseAuthor = newCourseAuthor

  def courseId: String = _courseId
  def courseId_=(newCourseId: String): Unit = _courseId = newCourseId

  def courseName: String = _courseName
  def courseName_=(newCourseName: String): Unit = _courseName = newCourseName
  
  def coursePrice: Double = _coursePrice
  def coursePrice_=(newCoursePrice: Double): Unit = _coursePrice = newCoursePrice
  
  def courseDuration: Int = _courseDuration
  def courseDuration_=(newCourseDuration: Int): Unit = _courseDuration = newCourseDuration

  def students: List[String] = _students
  def students_=(newStudents: List[String]): Unit = _students = newStudents

  def studentScores: Map[String, CourseGrades] = _studentScores
  def studentScores_=(newStudentScores: Map[String, CourseGrades]): Unit = _studentScores = newStudentScores


  def completeСourse(student: Student): Unit = {
    var student_id = student.userId
    var studentFullName: String = student.name + " " + student.surname
    var student_courses = student.myCourses

    _students = _students.filterNot(_ == student_id)
    _studentScores = _studentScores - student_id
    student_courses = student_courses.filterNot(_ == _courseId)
    student.myCourses_=(student_courses)
    
    println(s"Студент ${studentFullName} завершил курс $_courseName \n")
  }
  
  // Методы класса
  def printCourseInfo(): Unit = {
    println(
      s"Авор: $_courseAuthor" + "\n" +
      s"Название: $_courseName" + "\n" +
      s"Цена: $_coursePrice" + "\n" +
      s"Длительность: $_courseDuration" + "\n" +
      s"Id Вдадельца: $_ownerId" + "\n" +
      s"Id Курса: $_courseId" + "\n" +
      s"Людей записаных на курс: ${_students.length}" + "\n"
    )
  }
}