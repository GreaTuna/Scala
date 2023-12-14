class Stock(
    private var _tokenRate: Double) {

    def this() = this(25)
    
    def tokenRate: Double = _tokenRate
    def tokenRate_=(newTokenRate: Double): Unit = _tokenRate = newTokenRate

    // Методы класса
    def buyTokens(platform: Platform, balance: Wallet, tokensAmount: Double) : Unit = { 
        var tokensPrice: Double = (tokensAmount * _tokenRate)   // Цена токенов в фиатной валюте

        val newFiatBalance: Double = balance.fiatBalance - tokensPrice   
        val newTokenBalance: Double = balance.tokenBalance + tokensAmount
        balance.fiatBalance_=(newFiatBalance)
        balance.tokenBalance_=(newTokenBalance)

        val platformTokenBalance = platform.platformTokenBalance  - tokensAmount
        val platformFiatBalance = platform.platformFiatBalance + tokensPrice
        platform.platformTokenBalance_=(platformTokenBalance)
        platform.platformFiatBalance_=(platformFiatBalance)

        println(s"Вы купили ${"%.2f".format(tokensAmount)} токенов, за ${"%.2f".format(tokensPrice)} грн")
    }

    def refillBalance(balance: Wallet, replenishmentAmount: Double) : Unit = {
        var newFiatBalance: Double = balance.fiatBalance + replenishmentAmount
        balance.fiatBalance_=(newFiatBalance)
        println(s"Вы пополнили баланс на ${"%.2f".format(replenishmentAmount)} грн")
    }
}
