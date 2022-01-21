package lessons.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import kotlin.math.PI

internal class Tests {

    @Test
    fun sqr() {
        assertEquals(100, sqr(10))
        assertEquals(4, sqr(2))
        assertEquals(9, sqr(-3))
    }

    @Test
    fun sqrDouble() {
        assertEquals(0.0, sqr(0.0), 1e-13)
        assertEquals(4.0, sqr(2.0), 1e-13)
        assertEquals(9.0, sqr(-3.0), 1e-13)
    }

    @Test
    fun discriminant() {
        assertEquals(0.0, discriminant(0.0, 0.0, 0.0), 1e-13)
        assertEquals(0.0, discriminant(1.0, -2.0, 1.0), 1e-13)
        assertEquals(1.0, discriminant(1.0, 3.0, 2.0), 1e-13)
    }

    @Test
    fun quadraticEquationRoot() {
        assertEquals(2.0, quadraticEquationRoot(1.0, -3.0, 2.0), 1e-13)
        assertEquals(1.0, quadraticEquationRoot(1.0, -2.0, 1.0), 1e-13)
        assertEquals(-3.0, quadraticEquationRoot(1.0, 6.0, 9.0), 1e-13)
    }

    @Test
    fun quadraticRootProduct() {
        assertEquals(1.0, quadraticRootProduct(1.0, -2.0, 1.0), 1e-13)
        assertEquals(9.0, quadraticRootProduct(1.0, 6.0, 9.0), 1e-13)
        assertEquals(2.0, quadraticRootProduct(1.0, 3.0, 2.0), 1e-13)
    }

    @Test
    fun trackLength() {
        assertEquals(5.0, trackLength(3.0, 0.0, 0.0, 4.0), 1e-5)
        assertEquals(1.0, trackLength(0.0, 1.0, -1.0, 1.0), 1e-5)
        assertEquals(1.41, trackLength(1.0, 1.0, 2.0, 2.0), 1e-2)
    }

    @Test
    fun lengthInMeters() {
        assertEquals(18.98, lengthInMeters(8, 2, 11), 1e-2)
        assertEquals(2.13, lengthInMeters(1, 0, 0), 1e-2)
    }

    @Test
    fun angleInRadian() {
        assertEquals(0.63256, angleInRadian(36, 14, 35), 1e-5)
        assertEquals(PI / 2.0, angleInRadian(90, 0, 0), 1e-5)
    }

    @Test
    fun travelMinutes() {
        assertEquals(216, travelMinutes(9, 25, 13, 1))
        assertEquals(1, travelMinutes(21, 59, 22, 0))
    }

    @Test
    fun accountInThreeYears() {
        assertEquals(133.1, accountInThreeYears(100, 10), 1e-2)
        assertEquals(1.0, accountInThreeYears(1, 0), 1e-2)
        assertEquals(104.0, accountInThreeYears(13, 100), 1e-2)
    }
}