package `multi-currency-money`

class Sum(val augend: Expression, val addend: Expression) : Expression {

    override fun times(multiplier: Int): Expression {
        return Sum(augend * multiplier, addend * multiplier)
    }

    override fun reduce(bank: Bank, to: String): Money {
        val result = augend.reduce(bank, to).amount + addend.reduce(bank, to).amount

        return Money(result, to)
    }

    override operator fun plus(addend: Expression): Expression {
        return Sum(this, addend)
    }
}