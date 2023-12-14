object Main {
  def main(args: Array[String]): Unit = {
    
    val stock = new Stock
    val fakeDb =  new Data
    val platform = new Platform
    val creator = new Automation

    println("Баланс платформы до симуляции_____________________________ ")
    platform.printEntireBalance()
    println("Создаём учителей___________________________________________")
    creator.createTeachers(2, fakeDb)
    fakeDb.printTeachersData()
    println("Создаём студентов__________________________________________")
    creator.createStudents(4, fakeDb)
    fakeDb.printStudentsData()
    println("Создаём курсы______________________________________________")
    creator.createCourses(2, fakeDb, platform, stock)
    println("Список курсов______________________________________________")
    fakeDb.printCoursesData()
    println("Регистрируем студентов на курсы____________________________")
    creator.signUpStudentsToCourse(fakeDb, platform, stock)
    println("Журнал до симуляции________________________________________")
    fakeDb.printTeachersСourseLogs()
    println("Cимуляция===========================================================================")

    var courses = fakeDb.coursesData.coursesList
    var numberOfMonths = 25
    
    for (i <- 1 to numberOfMonths) {
      println(s"Month - $i")
      courses.foreach(course => {
        var duration = course.courseDuration
        var teacher_id = course.ownerId
        var teacher = fakeDb.teachersData.teachersList(teacher_id)
        teacher.setScore(course)
        platform.scholarshipPayment(course, fakeDb, stock)
      })
    }

    println("Журнал после симуляции_____________________________________")
    fakeDb.printTeachersСourseLogs()
    println("Баланс платформы после симуляции___________________________")
    platform.printEntireBalance()
  }
}
  