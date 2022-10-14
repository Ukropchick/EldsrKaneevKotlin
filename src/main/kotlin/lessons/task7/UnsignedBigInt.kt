package lessons.task7

import lessons.task7.UnsignedBigInteger.Companion.addIndexed
import kotlin.math.pow

/**
 * Класс "беззнаковое большое целое число".
 *
 * Общая сложность задания -- очень сложная, общая ценность в баллах -- 32.
 * Объект класса содержит целое число без знака произвольного размера
 * и поддерживает основные операции над такими числами, а именно:
 * сложение, вычитание (при вычитании большего числа из меньшего бросается исключение),
 * умножение, деление, остаток от деления,
 * преобразование в строку/из строки, преобразование в целое/из целого,
 * сравнение на равенство и неравенство
 */


fun Int.countDigits(): Int {
    var num = this
    var counter = 1
    while (num > 9) { num /= 10; counter++ }
    return counter
}


class UnsignedBigInteger : Comparable<UnsignedBigInteger> {
    companion object {
        const val digitCount = 9
        val maxInt = 10.0.pow(digitCount).toInt()

        /**
         *  Добавляет на индекс в списке и заботится о переполнении ячейки списка
         */

        fun MutableList<Int>.addIndexed(numThatAdds: Long, index: Int) {
            if (numThatAdds == 0L) return
            while (this.size <= index) this.add(0)
            val numToWhichAdd = this[index]
            val res = numToWhichAdd + numThatAdds
            val mainPart = (res % maxInt).toInt()
            val overPart = (res / maxInt)
            if (res < maxInt) {
                this[index] = mainPart
            } else {
                this[index] = mainPart
                this.addIndexed(overPart,index + 1 )
            }
        }
    }

    val list = mutableListOf<Int>()

    /**
     * Конструктор пустого BigInteger
     */

    private constructor()





    /**
     * Конструктор из строки
     */
    constructor(s: String) {
        var counter = 0
        var container = 0.0
        for (index in s.length - 1 downTo 0) {
            container += s[index].digitToInt() * 10.0.pow(counter)
            counter++
            if (counter == digitCount) {
                list.add(container.toInt())
                counter = 0
                container = 0.0
            }
        }
        if (counter in 1 until digitCount) {
            list.add(container.toInt())
        }
        list.reverse()
    }

    /**
     * Конструктор из целого
     */
    constructor(i: Int) {
        if (i > maxInt) {
            list.add(i / maxInt)
        }
        list.add(i % maxInt)
    }

    /**
     * Конструктор из листа
     */
    constructor(listOfInt: List<Int>) {
        var index = listOfInt.size - 1
        while (index > -1) {
            list.add(listOfInt[index])
            index--
        }
    }

    /**
     * Сложение
     */
    operator fun plus(other: UnsignedBigInteger): UnsignedBigInteger {
        val result = mutableListOf<Int>()
        var partToAdd = 0
        val biggestNumber = if (this.compareTo(other) == 1 || this.compareTo(other) == 0) this else other
        val smallestNumber = if  (other.compareTo(this) == -1 || other.compareTo(this) == 0) other else this
        var index1 = biggestNumber.list.size - 1
        var index2 = smallestNumber.list.size - 1
        while (index1 > -1) {
            val part1 = biggestNumber.list[index1]
            val part2 = if (index2 > -1) smallestNumber.list[index2] else 0
            val sum = part1 + part2 + partToAdd
            if (sum > maxInt) result.add(sum % maxInt).also { partToAdd = sum / maxInt }
            else result.add(sum).also { partToAdd = 0 }
            index1--
            index2--
        }
        if (partToAdd > 0) result.add(partToAdd)

        return UnsignedBigInteger(result)
    }

    /**
     * Вычитание (бросить ArithmeticException, если this < other)
     */
    operator fun minus(other: UnsignedBigInteger): UnsignedBigInteger {
        val result = mutableListOf<Int>()
        if (this.compareTo(other) == -1) throw ArithmeticException() else {
            var subtractionPart = 0
            var index1 = this.list.size - 1
            var index2 = other.list.size - 1
            while (index1 > -1) {
                val part1 = this.list[index1]
                val part2 = if (index2 > -1) other.list[index2] else 0
                val dif = part1 - part2 - subtractionPart
                if (dif >= 0) {
                    result.add(dif)
                    subtractionPart = 0
                } else {
                    result.add(dif * -1)
                    subtractionPart = 1
                }
                index1--
                index2--
            }
            while (result[0] == 0 && result.size > 1) {
                result.removeFirst()
            }
        }
        return UnsignedBigInteger(result)
    }



    /**
     * Умножение
     */
    operator fun times(other: UnsignedBigInteger): UnsignedBigInteger {
        var index3 = 0
        val result = mutableListOf<Int>(0)
        var index1 = this.list.size - 1
        var index2 = other.list.size - 1
        var offset = 0
        while (index1 > -1) {
            while (index2 > -1) {
                val term1 = this.list[index1]
                val term2 = other.list[index2]
                val multiplication = term1.toLong() * term2
                result.addIndexed(multiplication, index3 + offset)
                index2--
                index3++
            }
            index3 = 0
            offset++
            index1--
            index2 = other.list.size - 1
        }
        return UnsignedBigInteger(result)
    }

    /**
     * Главная функция деления
     */

    private fun mainDiv(dividend: UnsignedBigInteger, divider: UnsignedBigInteger): Pair<UnsignedBigInteger, UnsignedBigInteger> {
        if (divider == UnsignedBigInteger(0)) throw ArithmeticException("На ноль делить нельзя!")

        var res = ""
        var container = UnsignedBigInteger()
        var counterForContainer = 0
        var counter = 0

        while (counterForContainer < dividend.list.size) {
            while (container < divider && counterForContainer < dividend.list.size) {
                container.list.add(dividend.list[counterForContainer])
                counterForContainer++
            }

            while (container >= divider) {
                container -= divider
                counter++
            }
            res += counter
            counter = 0
        }

        return Pair(UnsignedBigInteger(res), container)
    }

    /**
     * Деление
     */
    operator fun div(other: UnsignedBigInteger): UnsignedBigInteger = mainDiv(this, other).first

    /**
     * Взятие остатка
     */
    operator fun rem(other: UnsignedBigInteger): UnsignedBigInteger = mainDiv(this, other).second

    /**
     * Сравнение на равенство (по контракту [Any.equals])
     */
    override fun equals(other: Any?): Boolean =
        if (other is UnsignedBigInteger) this.compareTo(other) == 0 else false

    /**
     * Сравнение на больше/меньше (по контракту Comparable.compareTo)
     */
    override fun compareTo(other: UnsignedBigInteger): Int {
        var counter = 0
        when {
            this.list.size > other.list.size -> return 1
            this.list.size == other.list.size -> {
                this.list.forEach {
                    when {
                        it > other.list[counter] -> return 1
                        it == other.list[counter] -> counter++
                        it < other.list[counter] -> return -1
                    }
                }
                return 0
            }
            else -> return -1
        }
    }

    /**
     * Преобразование в строку
     */

    override fun toString(): String = StringBuilder().apply {
        var isFirst = true
        list.forEach {
            if (!isFirst) {
                append("0".repeat(digitCount - it.countDigits()))
            }
            isFirst = false
            append(it).toString() }
    }.toString()

    /**
     * Преобразование в целое
     * Если число не влезает в диапазон Int, бросить ArithmeticException
     */
    fun toInt(): Int {
        val sizeOfList = this.list.size
        if (sizeOfList > 2) throw ArithmeticException() else {
            when (sizeOfList) {
                1 -> return this.list[0]
                2 -> {
                    if (this.list[0] >= 2 && this.list[1] >= 147483647) throw ArithmeticException()
                    else return this.list[0] * 1000000000 + this.list[1]
                }
            }
        }
        return this.list[0]
    }

}