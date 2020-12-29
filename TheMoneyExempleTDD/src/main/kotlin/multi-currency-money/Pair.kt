package `multi-currency-money`

internal data class Pair(val from: String, val to: String) {


    override fun equals(other: Any?): Boolean {
        if (other is Pair)
            return other.from == this.from && this.to == other.to
        return false
    }

    override fun hashCode(): Int {
        return 0
    }
}
