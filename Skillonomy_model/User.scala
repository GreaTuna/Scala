abstract class User(
  private var _name: String,
  private var _surname: String,
  private var _age: Int,
  private var _address: Address,
  private var _wallet: Wallet,
  private var _userId: String = generateId()) {
  
  // Гетеры сетеры
  def name: String = _name
  def name_=(newName: String): Unit = _name = newName

  def surname: String = _surname
  def surname_=(newSurname: String): Unit = _surname = newSurname

  def age: Int = _age
  def age_=(newAge: Int): Unit = _age = newAge

  def address: Address = _address
  def address_=(newAddress: Address): Unit = _address = newAddress

  def wallet: Wallet = _wallet
  def wallet_=(newWallet: Wallet): Unit = _wallet = newWallet

  def userId: String = _userId
  def userId_=(newUserId: String): Unit = _userId = newUserId

  
  // Методы класса
  def printUserInfo(): Unit = {
    println(
      s"Прiзвище та iм'я користувача: $_name $_surname, " +  
      s"Вiк: $_age рокiв, " +
      s"ID: $_userId" 
    )
    _address.printAddressInfo()
  }
}