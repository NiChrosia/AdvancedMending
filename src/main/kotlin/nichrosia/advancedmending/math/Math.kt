package nichrosia.advancedmending.math

fun clamp(value: Int, min: Int = 0, max: Int = value): Int {
    return when {
        value < min -> min
        value > max -> max
        else -> value
    }
}

fun floor(value: Double) = value.toInt()

fun Int.pow(exponent: Int): Int {
    var output = this

    for (i in 1 until exponent) output *= this

    return output
}