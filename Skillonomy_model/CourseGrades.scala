class CourseGrades(
  private var _courseGrades: List[Int]){

  def this() = this(List.empty)
  
  // Гетеры сетеры
  def courseGrades: List[Int] = _courseGrades
  def courseGrades_=(newCourseGrades: List[Int]): Unit = _courseGrades = newCourseGrades
}