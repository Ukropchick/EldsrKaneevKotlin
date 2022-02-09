package lessons.task3

import lessons.task1.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag

internal class Tests {

    @Test
    @Tag("Example")
    fun sqRoots() {
        assertEquals(listOf<Double>(), sqRoots(-1.0))
        assertArrayEquals(listOf(0.0).toDoubleArray(), sqRoots(0.0).toDoubleArray(), 1e-5)
        assertArrayEquals(listOf(-5.0, 5.0).toDoubleArray(), sqRoots(25.0).toDoubleArray(), 1e-5)
    }

    @Test
    @Tag("Example")
    fun biRoots() {
        assertEquals(listOf<Double>(), biRoots(0.0, 0.0, 1.0))
        assertEquals(listOf<Double>(), biRoots(0.0, 1.0, 2.0))
        assertArrayEquals(
            listOf(-2.0, 2.0).toDoubleArray(),
            biRoots(0.0, 1.0, -4.0).toDoubleArray(),
            1e-5
        )
        assertEquals(listOf<Double>(), biRoots(1.0, -2.0, 4.0))
        assertArrayEquals(
            listOf(-1.0, 1.0).toDoubleArray(),
            biRoots(1.0, -2.0, 1.0).toDoubleArray(),
            1e-5
        )
        assertEquals(listOf<Double>(), biRoots(1.0, 3.0, 2.0))
        assertArrayEquals(
            listOf(-2.0, -1.0, 1.0, 2.0).toDoubleArray(),
            biRoots(1.0, -5.0, 4.0).sorted().toDoubleArray(),
            1e-5
        )
    }

    @Test
    @Tag("Example")
    fun negativeList() {
        assertEquals(listOf<Int>(), negativeList(listOf(1, 2, 3)))
        assertEquals(listOf(-1, -5), negativeList(listOf(-1, 2, 4, -5)))
    }

    @Test
    @Tag("Example")
    fun invertPositives() {
        val list1 = mutableListOf(1, 2, 3)
        invertPositives(list1)
        assertEquals(listOf(-1, -2, -3), list1)
        val list2 = mutableListOf(-1, 2, 4, -5)
        invertPositives(list2)
        assertEquals(listOf(-1, -2, -4, -5), list2)
    }



    @Test
    @Tag("Example")
    fun buildSumExample() {
        assertEquals("42 = 42", buildSumExample(listOf(42)))
        assertEquals("3 + 6 + 5 + 4 + 9 = 27", buildSumExample(listOf(3, 6, 5, 4, 9)))
    }

    @Test
    fun decimalFromString() {
        assertEquals(1, decimalFromString("1", 2))
        assertEquals(100, decimalFromString("1210", 4))
        assertEquals(250, decimalFromString("13c", 14))
        assertEquals(1000, decimalFromString("2ec", 19))
        assertEquals(35, decimalFromString("z", 36))
        assertEquals(Int.MAX_VALUE, decimalFromString("a02220281", 11))
    }

    @Test
    fun russian() {
        assertEquals("шестьсот десять тысяч восемьсот двенадцать", russian(610812))
        assertEquals("четыреста восемьдесят пять тысяч семьсот девяносто пять", russian(485795))
        assertEquals("триста семьдесят пять", russian(375))
        assertEquals("двадцать две тысячи девятьсот шестьдесят четыре", russian(22964))
        assertEquals("сто девятнадцать тысяч пятьсот восемь", russian(119508))
        assertEquals("две тысячи три", russian(2003))
        assertEquals("двести тысяч два", russian(200002))
        assertEquals("девятьсот тысяч", russian(900000))
        assertEquals("двенадцать", russian(12))
        assertEquals("четыреста восемьдесят пять тысяч семьсот девяносто пять", russian(485795))
    }

    @Test
    @Tag("Example")
    fun timeStrToSeconds() {
        assertEquals(36000, timeStrToSeconds("10:00:00"))
        assertEquals(41685, timeStrToSeconds("11:34:45"))
        assertEquals(86399, timeStrToSeconds("23:59:59"))
    }

    @Test
    @Tag("Example")
    fun twoDigitStr() {
        assertEquals("00", twoDigitStr(0))
        assertEquals("09", twoDigitStr(9))
        assertEquals("10", twoDigitStr(10))
        assertEquals("99", twoDigitStr(99))
    }

    @Test
    @Tag("Example")
    fun timeSecondsToStr() {
        assertEquals("10:00:00", timeSecondsToStr(36000))
        assertEquals("11:34:45", timeSecondsToStr(41685))
        assertEquals("23:59:59", timeSecondsToStr(86399))
    }


    @Test
    fun dateStrToDigit() {
        assertEquals("15.07.2016", dateStrToDigit("15 июля 2016"))
        assertEquals("", dateStrToDigit("3 мартобря 1918"))
        assertEquals("18.11.2018", dateStrToDigit("18 ноября 2018"))
        assertEquals("", dateStrToDigit("23"))
        assertEquals("03.04.2011", dateStrToDigit("3 апреля 2011"))
        assertEquals("", dateStrToDigit("32 сентября 2011"))
        assertEquals("", dateStrToDigit("29 февраля 1993"))
    }

    @Test
    fun dateDigitToStr() {
        assertEquals("15 июля 2016", dateDigitToStr("15.07.2016"))
        assertEquals("", dateDigitToStr("01.02.20.19"))
        assertEquals("", dateDigitToStr("28.00.2000"))
        assertEquals("3 апреля 2011", dateDigitToStr("03.04.2011"))
        assertEquals("", dateDigitToStr("32.09.2011"))
        assertEquals("", dateDigitToStr("29.02.1993"))
    }

    @Test
    fun firstDuplicateIndex() {
        assertEquals(-1, firstDuplicateIndex("Привет")); println("1 passed")
        assertEquals(9, firstDuplicateIndex("Он пошёл в в школу")); println("1 passed"); println("2 passed")
        assertEquals(40, firstDuplicateIndex("Яблоко упало на ветку с ветки оно упало на на землю")); println("3 passed")
        assertEquals(9, firstDuplicateIndex("Мы пошли прямо Прямо располагался магазин")); println("4 passed")
    }
}