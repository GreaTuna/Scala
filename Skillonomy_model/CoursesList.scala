class CoursesList(
  private var _coursesList: List[Course]) {
  
  def this() = this(List.empty)
  
  // Гетеры сетеры
  def coursesList: List[Course] = _coursesList
  def coursesList_=(newCourseList: List[Course]): Unit = _coursesList = newCourseList

  
  // Методы класса
  def addCourseToList(course: Course): Unit = {
    _coursesList = course :: _coursesList
  }

  def printCourses(): Unit = {
    _coursesList.foreach { course =>
      course.printCourseInfo()
      println()
    }
  }
}