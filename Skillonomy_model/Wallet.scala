class Wallet(
  private var _fiatBalance : Double,
  private var _tokenBalance: Double) {

  def this() = this(0, 0)

  // Гетеры сетеры
  def fiatBalance: Double = _fiatBalance
  def fiatBalance_=(newFiatBalance: Double): Unit = _fiatBalance = newFiatBalance

  def tokenBalance: Double = _tokenBalance
  def tokenBalance_=(newTokenBalance: Double): Unit = _tokenBalance = newTokenBalance
}