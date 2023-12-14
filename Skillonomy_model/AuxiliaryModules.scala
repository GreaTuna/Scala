import scala.util.Random

def generateId(): String = {  // Функция для генерации уникального id для: teacher, student, course
  val length = 15
  val characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
  val random = new Random
  val userId = new StringBuilder

  for (_ <- 1 to length) {
    val charIndex = random.nextInt(characters.length)
    userId.append(characters(charIndex))
  }

  userId.toString()
}

def generateGrade(grades: List[Int], courseDuration: Int): List[Int] = {

  if (grades.length < courseDuration) {
    val random = new Random()
    var randomGrade = (random.nextInt(5) + 1)
    var updatedGrades = grades :+ randomGrade

    return updatedGrades
  }
  else {
    return grades
  }
}

def averageGradePoint(gradesList : List[Int]): Double = {

  val sum: Double = gradesList.sum
  val length: Double = gradesList.length
  val averageGrade: Double = sum / length

  return averageGrade
}




