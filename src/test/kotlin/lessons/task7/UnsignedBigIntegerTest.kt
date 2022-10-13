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
    fun testToString() {
        assertEquals("1234567890", UnsignedBigInteger("1234567890").toString())
        assertEquals("1234567890", UnsignedBigInteger("1234567890").toString())
        assertEquals("1234567890123456789012345678901234567890", UnsignedBigInteger("1234567890123456789012345678901234567890").toString())
        assertEquals("12345", UnsignedBigInteger("12345").toString())
        assertEquals("890", UnsignedBigInteger("890").toString())
        assertEquals("0", UnsignedBigInteger(0).toString())
        assertEquals("1234567890", UnsignedBigInteger(1234567890).toString())
        assertEquals("1000000008", UnsignedBigInteger("1000000008").toString())
        assertEquals("10000000", UnsignedBigInteger("10000000").toString())
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

    private fun doAddIndexedTest(toDo:(output:List<Int>, input:List<Int>) -> Unit) {
        val digits = Random.nextInt(2, 1000)
        val randIndex = Random.nextInt(0, digits - 1)
        val input = mutableListOf<Int>().apply { repeat(digits) { add(999999999) } }
        UnsignedBigInteger.apply {
            input.addIndexed(1, randIndex)
        }
        val output = mutableListOf<Int>().apply {
            add(1)
            repeat(digits - randIndex) { add(0) }
            repeat(randIndex) { add(999999999)}
        }
        println("\ninput  = ${input.reversed()},\noutput = ${output}\n\n")
        toDo(output.toList(), input.reversed())
    }

    @Test
    fun addIndexed() {
        repeat(10000) {
            doAddIndexedTest { output, input->
                assertEquals(output, input)
            }
        }
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
        assertEquals(
            "1092340304936617220000000", (
                    UnsignedBigInteger(318248038)
                    * UnsignedBigInteger(343235519)
                    * UnsignedBigInteger("10000000")
                    ).toString())
        println("\n\nBegining Random tests!\n")
        repeat(10000) {
            val a = Random.nextInt(0, Int.MAX_VALUE)
            val b = Random.nextInt(0, Int.MAX_VALUE)
            val c = a * b.toLong()
            val z = 10.0.pow(Random.nextInt(1,16)).toLong().toString()
            val res = StringBuilder(c.toString()).append("0".repeat(z.length - 1)).toString()
            println("a = $a \nb = $b \nz = $z \nres = $res \n")
            assertEquals(res, (UnsignedBigInteger(b) * UnsignedBigInteger(a) * UnsignedBigInteger(z)).toString())
        }
    }

    @Test
    fun div() {
//        assertEquals((Int.MAX_VALUE / Int.MAX_VALUE).toString(), (UnsignedBigInteger(Int.MAX_VALUE) / UnsignedBigInteger(Int.MAX_VALUE)).toString())
//        assertFailsWith<ArithmeticException> { UnsignedBigInteger(Int.MAX_VALUE) / UnsignedBigInteger(0) }
//        assertFailsWith<ArithmeticException> { UnsignedBigInteger(0) / UnsignedBigInteger(0) }
//        assertEquals((0 / Int.MAX_VALUE).toString(), (UnsignedBigInteger(0) / UnsignedBigInteger(Int.MAX_VALUE)).toString())
//        assertEquals((Int.MAX_VALUE / 1).toString(), (UnsignedBigInteger(Int.MAX_VALUE) / UnsignedBigInteger(1)).toString())
//        assertEquals((1 / 1).toString(), (UnsignedBigInteger(1) / UnsignedBigInteger(1)).toString())
//        assertEquals((Int.MAX_VALUE / 1).toString(), (UnsignedBigInteger(Int.MAX_VALUE) / UnsignedBigInteger(1)).toString())
//        assertEquals((7689 / 254).toString(), (UnsignedBigInteger(7689) / UnsignedBigInteger(254)).toString())
        repeat(1) {
            val a = Random.nextInt(0, Int.MAX_VALUE)
            val b = Random.nextInt(1, Int.MAX_VALUE)
            val c = a / b
            val z = 10.0.pow(Random.nextInt(1,16)).toLong().toString()
            val res = StringBuilder(c.toString()).append("0".repeat(z.length - 1)).toString()
            println("a = $a \nb = $b \nz = $z \nc = $c \nres = $res \n")
            assertEquals(c.toString(), (UnsignedBigInteger(a) / UnsignedBigInteger(b)).toString())

        }
    }

    @Test
    fun rem() {
        assertEquals((Int.MAX_VALUE % Int.MAX_VALUE).toString(), (UnsignedBigInteger(Int.MAX_VALUE) % UnsignedBigInteger(Int.MAX_VALUE)).toString())
        assertFailsWith<ArithmeticException> { UnsignedBigInteger(Int.MAX_VALUE) % UnsignedBigInteger(0) }
        assertFailsWith<ArithmeticException> { UnsignedBigInteger(0) % UnsignedBigInteger(0) }
        assertEquals((0 % Int.MAX_VALUE).toString(), (UnsignedBigInteger(0) % UnsignedBigInteger(Int.MAX_VALUE)).toString())
        assertEquals((Int.MAX_VALUE % 1).toString(), (UnsignedBigInteger(Int.MAX_VALUE) % UnsignedBigInteger(1)).toString())
        assertEquals((1 % 1).toString(), (UnsignedBigInteger(1) % UnsignedBigInteger(1)).toString())
        assertEquals((Int.MAX_VALUE % 1).toString(), (UnsignedBigInteger(Int.MAX_VALUE) % UnsignedBigInteger(1)).toString())
        assertEquals((7689 % 254).toString(), (UnsignedBigInteger(7689) % UnsignedBigInteger(254)).toString())
        assertEquals(("93248499044975392436495").toString(), (UnsignedBigInteger("2030104606172384519837456") % UnsignedBigInteger("1936856107127409127400961")).toString())
        assertEquals(("147483647147483647147483647147483647147483647147483647147483647147483647147483647147483647147483647147483647").toString(),
            (UnsignedBigInteger("147483647147483647147483647147483647147483647147483647147483647147483647147483647147483647147483647147483647")
                % UnsignedBigInteger("14748364714748364711474836471117483647147483647147483647147483647147483647147483647147483647147483647147483647")).toString())
        repeat(1) {
            val a = Random.nextInt(0, Int.MAX_VALUE)
            val b = Random.nextInt(1, Int.MAX_VALUE)
            val c = a % b
            val z = 10.0.pow(Random.nextInt(1,16)).toLong().toString()
            val res = StringBuilder(c.toString()).append("0".repeat(z.length - 1)).toString()
            println("a = $a \nb = $b \nz = $z \nc = $c \nres = $res \n")
            assertEquals(c.toString(), (UnsignedBigInteger(a) % UnsignedBigInteger(b)).toString())

        }
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
    fun toInt() {
        assertEquals(1, UnsignedBigInteger(1).toInt())
        assertEquals(1, UnsignedBigInteger("1").toInt())
        assertEquals(2147483647, UnsignedBigInteger(2147483647).toInt())
        assertEquals(2147483647, UnsignedBigInteger("2147483647").toInt())
        assertFailsWith<ArithmeticException> { UnsignedBigInteger("93248499044975392436495").toInt() }
    }
}