package `multi-currency-money`

interface Expression {
    operator fun times(multiplier: Int): Expression
    operator fun plus(addend: Expression): Expression

    fun reduce(bank: Bank, to: String): Money
}