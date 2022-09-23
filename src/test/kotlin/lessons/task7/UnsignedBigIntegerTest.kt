package lessons.task7

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*
import kotlin.collections.RandomAccess
import kotlin.math.pow
import kotlin.random.Random
import kotlin.test.assertFailsWith

internal class UnsignedBigIntegerTest {

    private fun <T> doAbstractTest(expected: T, funct: (Int) -> T) {
        var a = 9
        while (a < 1000000000) {
            assertEquals(expected, funct(a))
            a *= 10
            a += 9
        }
    }

    @Test
    fun getList() {
        assertEquals("1234567890", UnsignedBigInteger("1234567890").toString())
        assertEquals("1234567890123456789012345678901234567890", UnsignedBigInteger("1234567890123456789012345678901234567890").toString())
        assertEquals("12345", UnsignedBigInteger("12345").toString())
        assertEquals("890", UnsignedBigInteger("890").toString())
        assertEquals("0", UnsignedBigInteger(0).toString())
        assertEquals("1234567890", UnsignedBigInteger(1234567890).toString())
        assertEquals("1000000008", UnsignedBigInteger("1000000008").toString())
    }

    @Test
    fun plus() {
        assertEquals((112312 + 1412341234).toString(), (UnsignedBigInteger(112312) + UnsignedBigInteger(1412341234)).toString())
        assertEquals((412341234 + 112112312).toString(), (UnsignedBigInteger(412341234) + UnsignedBigInteger(112112312)).toString())
        assertEquals((1412341234 + 112312).toString(), (UnsignedBigInteger(1412341234) + UnsignedBigInteger(112312)).toString())
        assertEquals((1412341234912319126 + 1123121207301237600).toString(), (UnsignedBigInteger("1412341234912319126") + UnsignedBigInteger("1123121207301237600")).toString())
    }

    @Test
    fun minus() {
        assertFailsWith<ArithmeticException> { UnsignedBigInteger(1) - UnsignedBigInteger(2) }
        assertEquals((Int.MAX_VALUE - 1).toString(), (UnsignedBigInteger(Int.MAX_VALUE) - UnsignedBigInteger(1)).toString())
        assertEquals((Int.MAX_VALUE - Int.MAX_VALUE).toString(), (UnsignedBigInteger(Int.MAX_VALUE) - UnsignedBigInteger(Int.MAX_VALUE)).toString())
        assertEquals((9000000 - 8000000).toString(), (UnsignedBigInteger(9000000) - UnsignedBigInteger(8000000)).toString())
        assertEquals((9 - 5).toString(), (UnsignedBigInteger(9) - UnsignedBigInteger(5)).toString())
        assertEquals((1892459929 - 92313345).toString(), (UnsignedBigInteger(1892459929) - UnsignedBigInteger(92313345)).toString())
        assertEquals((223714841 - 128164767).toString(), (UnsignedBigInteger(223714841) - UnsignedBigInteger(128164767)).toString())


    }

    @Test
    fun addIndexed() {
        val ar = mutableListOf<Int>(999999999, 999999999, 999999999)

        UnsignedBigInteger.apply {
            ar.addIndexed(1, 0)
        }

        assertEquals(listOf(1, 0, 0, 0), ar.reversed())

    }

    @Test
    fun times() {
        assertEquals(("39320205039799288420000000").toString(), (UnsignedBigInteger(1936856107) * UnsignedBigInteger(2030104606) * UnsignedBigInteger(10000000)).toString())
        assertEquals((4611686014132420609).toString(), (UnsignedBigInteger(Int.MAX_VALUE) * UnsignedBigInteger(Int.MAX_VALUE)).toString())
        assertEquals("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
            (UnsignedBigInteger("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")
                    * UnsignedBigInteger("100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")
                    ).toString())
        assertEquals(
            "21751426175923461330095496484267531638439566792611601946783637100955672255127707409299742563471777717643812828312995674140960519968925365796890211624855057452819903280784749108749594936714440764679286592644132420609",
            (UnsignedBigInteger("147483647147483647147483647147483647147483647147483647147483647147483647147483647147483647147483647147483647") *
                    UnsignedBigInteger("147483647147483647147483647147483647147483647147483647147483647147483647147483647147483647147483647147483647")).toString())
        assertEquals((Int.MAX_VALUE * 1).toString(), (UnsignedBigInteger(Int.MAX_VALUE) * UnsignedBigInteger(1)).toString())
        assertEquals(((UnsignedBigInteger.maxInt - 1) * 1).toString(), (UnsignedBigInteger(UnsignedBigInteger.maxInt - 1) * UnsignedBigInteger(1)).toString())
        assertEquals((25431 * 7689).toString(), (UnsignedBigInteger(25431) * UnsignedBigInteger(7689)).toString())
        assertEquals((254 * 7689).toString(), (UnsignedBigInteger(254) * UnsignedBigInteger(7689)).toString())
        assertEquals((1 * 1).toString(), (UnsignedBigInteger(1) * UnsignedBigInteger(1)).toString())
        assertEquals((0 * 0).toString(), (UnsignedBigInteger(0) * UnsignedBigInteger(0)).toString())
        repeat(1000000000) {
            val a = Random.nextInt(0, Int.MAX_VALUE)
            val b = Random.nextInt(0, Int.MAX_VALUE)
            val c = a * b.toLong()
            val z = 10.0.pow(Random.nextInt(1,16)).toLong().toString()
            val res = StringBuilder(c.toString()).append("0".repeat(z.length - 1)).toString()
//            println("a = $a \nb = $b \nz = $z \nres = $res \n")
            assertEquals(c.toString(), (UnsignedBigInteger(b) * UnsignedBigInteger(a) ).toString())
        }
    }

    @Test
    fun div() {
    }

    @Test
    fun rem() {
    }

    @Test
    fun testEquals() {
        doAbstractTest(true) {
            UnsignedBigInteger(it) == UnsignedBigInteger(it)
        }
        doAbstractTest(false) {
            UnsignedBigInteger(it) < UnsignedBigInteger(it)
        }
    }

    @Test
    fun compareTo() {
        doAbstractTest(1) {
            UnsignedBigInteger(it).compareTo(UnsignedBigInteger(1))
        }
        doAbstractTest(true) {
            UnsignedBigInteger(it) > UnsignedBigInteger(1)
        }
        doAbstractTest(false) {
            UnsignedBigInteger(it) < UnsignedBigInteger(1)
        }
        doAbstractTest(0) {
            UnsignedBigInteger(it) == UnsignedBigInteger(it)
        }
    }



    @Test
    fun testToString() {
        assertEquals("1234567890", UnsignedBigInteger(1234567890).toString())
        assertEquals("0", UnsignedBigInteger(0).toString())
    }

    @Test
    fun toInt() {
    }
}