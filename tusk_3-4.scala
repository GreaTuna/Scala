class Address(
  private var _country: String,
  private var _city: String, 
  private var _street: String,
  private var _houseNumber: String,
  private var _postalCode: String) {

  def this() = this("Ukraine", "Kherson", "Pushkinskaya", "141-a", "73000")

  def country: String = _country
  def country_=(newCountry: String): Unit = _country = newCountry

  def city: String = _city
  def city_=(newCity: String): Unit = _city = newCity

  def street: String = _street
  def street_=(newStreet: String): Unit = _street = newStreet

  def houseNumber: String = _houseNumber
  def houseNumber_=(newHouseNumber: String): Unit = _houseNumber = newHouseNumber

  def postalCode: String = _postalCode
  def postalCode_=(newPostalCode: String): Unit = _postalCode = newPostalCode

  def printInfo(): Unit = {
    println(
        s"Country: $_country" + "\n" +
        s"City: $_city" + "\n" +
        s"Street: $_street" + "\n" +
        s"House number: $_houseNumber" + "\n" +
        s"Postal code: $_postalCode"
    )
  }
}

class Human(
  private var _name: String,
  private var _age: Int,
  private var _weight: Int,
  private var _height: Int,
  private var _address: Address) {
  
  def this() = this("Ajax", 25, 84, 187, new Address())
  
  def name: String = _name
  def name_=(newName: String): Unit = _name = newName

  def age: Int = _age
  def age_=(newAge: Int): Unit = _age = newAge

  def weight: Int = _weight
  def weight_=(newWeight: Int): Unit = _weight = newWeight

  def height: Int = _height
  def height_=(newHeight: Int): Unit = _height = newHeight

  def address: Address = _address
  def address_=(newAddress: Address): Unit = _address = newAddress

  def printInfo(): Unit = {
    println(
      s"Имя: $_name" + "\n" +
      s"Возраст: $_age лет" + "\n" +
      s"Вес: $_weight кг" + "\n" +
      s"Рост: $_height см"
    )

    _address.printInfo()
  }
}

class ListHuman(
  private var _listHumans: List[Human]) {
  
  def this() = this(List.empty)
  
  def addHuman(human: Human): Unit = {
    _listHumans = human :: _listHumans
  }

  def removeHuman(human: Human): Unit = {
    _listHumans = _listHumans.filterNot(_ == human)
  }

  def removeHumanByName(humanName : String): Unit = {

    _listHumans = _listHumans.filterNot(_.name == humanName)
  }

  def filterByAge(): Unit = {
    for (human <- _listHumans) {
      if(human.age < 18) {
        human.printInfo()
      }
    }
  }

  def findHuman(humanName : String): Unit = {
    var isIn = false

    for (human <- _listHumans) {
      if(human.name == humanName) {
          human.printInfo()
          println()
          isIn = true
      }
    }

    if(!isIn) {
      println("there are no people with that name in list")
    }
  }

  def getHumans: List[Human] = _listHumans

  def printHumans(): Unit = {
    _listHumans.foreach { human =>
      human.printInfo()
      println("______________________________________________________________________")
    }
  }
}


object Main {
  def main(args: Array[String]): Unit = {

    val listOfHumans = new ListHuman()

    listOfHumans.addHuman(new Human())  //Добавляем людей в список через метод addHuman
    listOfHumans.addHuman(new Human("Axe", 27, 68, 179, new Address()))
    listOfHumans.addHuman(new Human("Bobi", 17, 66, 175, new Address("Us", "Alabama", "Freedom-Street", "322", "72000")))
    listOfHumans.addHuman(new Human("Jonathan", 34, 89, 190, new Address("Us", "Alabama", "Freedom-Street", "322", "75000")))
    
    println("Вывод всех людей в списке:")
    listOfHumans.printHumans()  //Вывод всех дюдей в списке
    
    listOfHumans.removeHuman(listOfHumans.getHumans(2)) // Удаление по индексу в списке
    println("\n\n")
    println("Удаление человека  по индексу в списке:")
    listOfHumans.printHumans()
    
    listOfHumans.removeHumanByName("Jonathan") // Удаление по имени в списке
    println("\n\n")
    println("Удаление человека по имени:")
    listOfHumans.printHumans()
    
    listOfHumans.addHuman(new Human("Ajax", 34, 89, 190, new Address("Us", "Alabama", "Freedom-Street", "322", "75000"))) // Добавляем людей в список через метод addHuman
    println("\n\n")
    println("Добавление в список:")
    listOfHumans.printHumans() 
    
    println("\n\n")  // Поиск по имени в списке
    println("Поиск людей по имени:")
    listOfHumans.findHuman("Ajax")

    println("\n\n")  // Вывод через фильтр
    println("Фильтр по возрасту:")
    listOfHumans.filterByAge()

    listOfHumans.getHumans(0).name_=("Jmishenko") // Изменения данных через сетеры
    println("\n\n")
    println("Изменения имени через сетер")
    listOfHumans.printHumans()
  }
}
