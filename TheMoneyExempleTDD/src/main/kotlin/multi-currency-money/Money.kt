package `multi-currency-money`


class Money(val amount: Int, val currency: String) : Expression {

    companion object {
        fun dollar(amount: Int): Money {
            return Money(amount, "USD")
        }

        fun franc(amount: Int): Money {
            return Money(amount, "CHF")
        }
    }

    override fun times(multiplier: Int): Expression {
        return Money(amount * multiplier, this.currency)
    }

    override fun equals(other: Any?): Boolean {
        if (other is Money)
            return other.amount == amount && this.currency == other.currency

        return false
    }

    override operator fun plus(addend: Expression): Expression {

        return Sum(this, addend)
    }


    override fun toString(): String {
        return "${this.amount} ${this.currency}"
    }

    override fun reduce(bank: Bank, to: String): Money {
        val rate = bank.rate(currency, to)

        return Money(amount / rate, to)
    }
}