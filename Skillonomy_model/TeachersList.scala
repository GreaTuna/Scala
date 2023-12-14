class TeachersList(
  private var _teachersId: List[String] = List.empty,
  private var _teachersList: Map[String, Teacher] = Map.empty[String, Teacher]) {
  
  // Гетеры сетеры
  def teachersId: List[String] = _teachersId
  def teachersId_=(newTeachersId: List[String]): Unit = _teachersId = newTeachersId

  def teachersList: Map[String, Teacher] = _teachersList
  def teachersList_=(newTeachersList: Map[String, Teacher]): Unit = _teachersList = newTeachersList

  
  // Методы класса
  def addTeacherToList(teacher : Teacher) : Unit = {
    _teachersId = teacher.userId :: _teachersId
    _teachersList += (teacher.userId -> teacher)
  } 
}
