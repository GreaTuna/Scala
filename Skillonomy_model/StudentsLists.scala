class StudentsList(
  private var _studentsId: List[String] = List.empty,
  private var _studentsList: Map[String, Student] = Map.empty[String, Student]) {
  
  // Гетеры сетеры
  def studentsId: List[String] = _studentsId
  def studentsId_=(newStudentsId: List[String]): Unit = _studentsId = newStudentsId

  def studentsList: Map[String, Student] = _studentsList
  def studentsList_=(newStudentsList: Map[String, Student]): Unit = _studentsList = newStudentsList

  
  // Методы класса
  def addStudentToList(student : Student) : Unit = {
    _studentsId = student.userId :: _studentsId
    _studentsList += (student.userId -> student)
  } 
}
