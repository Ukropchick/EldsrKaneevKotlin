package lessons.task6

/**
 * Extension function (функция с ресивером)
 */

fun Int.isPrime(): Boolean {
    for (i in 2 until this - 1) {
        if (this % i == 0) {
            return false
        }
    }
    return true
}





/**
 * vararg агрумент и зачем)
 */
fun getMax(vararg numbers: Int): Int {
    var max = numbers[0]
    for (number in numbers) if (number > max) max = number
    return max
}
fun getMax(a: Int, b: Int): Int {
    return if (a > b) a
    else b
}



fun main() {
    print(7.isPrime())
    println(getMax(81543765, 8, 9, 10, 81877561))
}