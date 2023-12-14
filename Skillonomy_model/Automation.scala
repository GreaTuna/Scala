import scala.util.Random

class Automation {

  def createTeachers(amount: Int, data: Data): Unit = {

    for (i <- 1 to amount) {
        
        val random = new Random()
        var age = random.nextInt(31) + 20
        var fiatBalance = random.nextInt(500)
        var tokenBalance = random.nextInt(45)

        var teacher = new Teacher(s"Kick-${i}", s"Butovsky-${i}", age, new Address, new Wallet(fiatBalance, tokenBalance), generateId())
        data.addTeacher(teacher)
    }
  }

  def createStudents(amount: Int, data: Data): Unit = {

    for (i <- 1 to amount) {
        
        val random = new Random()
        var age = random.nextInt(17) + 20
        var fiatBalance = random.nextInt(800)
        var tokenBalance = random.nextInt(35)
  
        var student = new Student(s"Abdurauf-${i}", s"Bambani-${i}", age, new Address, new Wallet(fiatBalance, tokenBalance), generateId())
        data.addStudent(student)
    }
  }

  def createCourses(amount: Int, data: Data, platform: Platform, stock: Stock): Unit = {
    
    var counter = 1
    val random = new Random()
    var teachers = data.teachersData.teachersList

    teachers.keys.foreach(key => {

      var randomAmount = random.nextInt(amount) + 1  
      
      for (i <- 1 to randomAmount) {
        var coursePrice = random.nextInt(1600) + 900
        var courseDuration = random.nextInt(15) + 5
        teachers(key).createCourse(s"Course-$counter", coursePrice, courseDuration, data: Data, platform: Platform, stock: Stock)
        counter = counter + 1
      } 
    })
  }

  def signUpStudentsToCourse(data: Data, platform: Platform, stock: Stock) : Unit = {
      
    var students = data.studentsData.studentsList

    students.keys.foreach(key => {
      students(key).signUpToCourse(stock, data, platform)
    })
  }
}
