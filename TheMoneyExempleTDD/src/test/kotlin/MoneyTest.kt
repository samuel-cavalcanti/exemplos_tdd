import `multi-currency-money`.Bank
import `multi-currency-money`.Money
import `multi-currency-money`.Sum
import org.junit.Assert.*
import org.junit.Test

class MoneyTest {

    @Test
    fun testMultiplication() {

        val fiveDollars = Money.dollar(5)

        assertEquals(fiveDollars * 2, Money.dollar(10))

        assertEquals(fiveDollars * 3, Money.dollar(15))


    }


    @Test
    fun testEquality() {
        assertTrue(Money.dollar(5) == Money.dollar(5))
        assertFalse(Money.dollar(5) == Money.dollar(6))

        assertFalse("5 dollars should be diferent of 5 Francs", Money.dollar(5) == Money.franc(5))
    }


    @Test
    fun testSimpleAddition() {
        val five = Money.dollar(5)

        val sum = five + five

        val bank = Bank()

        val reduced = bank.reduce(sum, "USD")

        assertEquals(Money.dollar(10), reduced)
    }

    @Test
    fun testPlusReturnsSum() {
        val five = Money.dollar(5)
        val result = five + five
        val sum = result as Sum
        assertEquals(five, sum.augend)
        assertEquals(five, sum.addend)
    }

    @Test
    fun testReduceSum() {
        val sum = Sum(Money.dollar(3), Money.dollar(4))
        val bank = Bank()
        val result = bank.reduce(sum, "USD")
        assertEquals(Money.dollar(7), result)
    }


    @Test
    fun testReduceMoney() {
        val bank = Bank()
        val result = bank.reduce(Money.dollar(1), "USD")
        assertEquals(Money.dollar(1), result)
    }

    @Test
    fun testIdentityRate() {
        assertEquals(1, Bank().rate("USD", "USD"))
    }

    @Test
    fun testReduceMoneyDifferentCurrency() {
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val result = bank.reduce(Money.franc(2), "USD")
        assertEquals(Money.dollar(1), result)
    }

    @Test
    fun testMixedAddition() {
        val fiveBucks = Money.dollar(5)
        val tenFrancs = Money.franc(10)
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val result = bank.reduce(fiveBucks + tenFrancs, "USD")

        assertEquals(Money.dollar(10), result)
    }

    @Test
    fun testSumPlusMoney() {
        val fiveBucks = Money.dollar(5)
        val tenFrancs = Money.franc(10)
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val sum = Sum(fiveBucks, tenFrancs) + fiveBucks
        val result = bank.reduce(sum, "USD")
        assertEquals(Money.dollar(15), result)
    }

    @Test
    fun testSumTimes() {
        val fiveBucks = Money.dollar(5)
        val tenFrancs = Money.franc(10)
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val sum = Sum(fiveBucks, tenFrancs) * 2
        val result = bank.reduce(sum, "USD")

        assertEquals(Money.dollar(20), result)
    }


}