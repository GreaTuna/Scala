class Address(
  private var _country: String,
  private var _city: String, 
  private var _street: String,
  private var _houseNumber: String) {

  def this() = this("Ukraine", "Kherson", "Pushkinskaya", "141-a")
  
  // Гетеры сетеры
  def country: String = _country
  def country_=(newCountry: String): Unit = _country = newCountry

  def city: String = _city
  def city_=(newCity: String): Unit = _city = newCity

  def street: String = _street
  def street_=(newStreet: String): Unit = _street = newStreet

  def houseNumber: String = _houseNumber
  def houseNumber_=(newHouseNumber: String): Unit = _houseNumber = newHouseNumber

  
  // Методы класса
  def printAddressInfo(): Unit = {
    println(
      s"Country: $_country, " +
      s"City: $_city, " +
      s"Street: $_street, " +
      s"House number: $_houseNumber " + "\n" 
    )
  }
}