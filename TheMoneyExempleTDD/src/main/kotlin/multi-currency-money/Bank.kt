package `multi-currency-money`

import java.lang.Exception
import java.util.*


class Bank {
    private val rates = Hashtable<Pair, Int>()

    fun reduce(source: Expression, to: String): Money {
        return source.reduce(this, to)
    }

    fun addRate(from: String, to: String, rate: Int) {
        rates[Pair(from, to)] = rate
    }

    fun rate(currency: String, to: String): Int {
        if (currency == to) return 1

        return rates[Pair(currency, to)] ?: throw Exception("rate not found")
    }
}
