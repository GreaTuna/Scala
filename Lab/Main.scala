import scala.util.Random

class Address(
  private var _country: String,
  private var _city: String) {

  def this() = this("Ukraine", "Kherson")

  def country: String = _country
  def country_=(newCountry: String): Unit = _country = newCountry

  def city: String = _city
  def city_=(newCity: String): Unit = _city = newCity

  def printInfo(): Unit = {
    println(
        s"Country: $_country" + "\n" +
        s"City: $_city" + "\n")
  }
}

class Human(
  private var _name: String,
  private var _surname: String,
  private var _age: Int,
  private var _address: Address) {
  
  def this() = this("Ajax", "Lebowski", 20, new Address())
  
  def name: String = _name
  def name_=(newName: String): Unit = _name = newName

  def surname: String = _surname
  def surname_=(newSurname: String): Unit = _surname = newSurname

  def age: Int = _age
  def age_=(newAge: Int): Unit = _age = newAge
  _address

  def printInfo(): Unit = {
    println(
      s"Iм'я: $_name" + "\n" +
      s"Прiзвище: $_surname" + "\n" +
      s"Вiк: $_age роки" + "\n" 
    )

    _address.printInfo()
  }
}

class Student(
  private var _average_score: Double,
  private var _group: String,
  _name: String = "Ajax",
  _surname: String = "Lebowski",
  _age: Int = 20,
  _address: Address = new Address()) extends Human(_name, _surname, _age, _address) {
    
  def this() = this(5, "222-M")


  def average_score: Double = _average_score
  def average_score_=(newAverage_score: Double): Unit = _average_score = newAverage_score

  def group: String = _group
  def group_=(newGroup: String): Unit = _group = newGroup

  def changeAddress(newCountry: String, newCity: String): Unit = {
    _address.country=(newCountry)
    _address.city_=(newCity)
  }

  def studentPrintInfo(): Unit = {
    printInfo()

    println(
    s"Група: $_group" + "\n" +
    s"Середнiй бал : $_average_score" + "\n"
    )
  }
}

class Teacher (
  private var _listStudents: List[Student],
  _name: String = "Valery",
  _surname: String = "Albertovich",
  _age: Int = 40,
  _address: Address = new Address()) extends Human(_name, _surname, _age, _address) {
  
  def this() = this(List.empty)
  

  def changeAddress(newCountry: String, newCity: String): Unit = {
    _address.country=(newCountry)
    _address.city_=(newCity)
  }

  def addStudent(student: Student): Unit = {
    _listStudents = student :: _listStudents
  }

  def setScore(): Unit = {
    for (student <- _listStudents) {
      val random = new Random()
      student.average_score_= (random.nextInt(5) + 1)
    }
  }

  def printStudents(): Unit = {
    _listStudents.foreach { student =>
      student.studentPrintInfo()
      println("______________________________________________________________________")
    }
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    
    val teacher = new Teacher()

    teacher.addStudent(new  Student())
    teacher.addStudent(new  Student (5, "221", "Abob", "Abdob", 19, new Address()))
    teacher.addStudent(Student(5, "221", "Bubble", "Bass", 18, new Address()))
    teacher.addStudent(new Student(5, "223-M", "Bobi", "Midovich", 21, new Address("Ukraine", "Kyiv")))
    
    teacher.changeAddress("Ukraine", "Kyiv")
    println("Information about teacher:")
    teacher.printInfo()

    println("Information about students:")
    teacher.printStudents()
    
    println("Setting random score for student:")
    teacher.setScore()
    teacher.printStudents()
  }
}