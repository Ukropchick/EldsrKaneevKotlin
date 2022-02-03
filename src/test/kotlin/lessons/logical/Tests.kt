package lessons.logical

import lessons.task1.isNumberHappy
import lessons.task1.rookOrBishopThreatens
import lessons.task1.triangleKind
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Tests {

    @Test
    fun rookOrBishopThreatens() {
        assertEquals(0, rookOrBishopThreatens(4, 5, 5, 7, 8, 8))
        assertEquals(1, rookOrBishopThreatens(2, 8, 6, 8, 1, 6))
        assertEquals(2, rookOrBishopThreatens(5, 4, 3, 7, 1, 8))
        assertEquals(3, rookOrBishopThreatens(1, 6, 7, 6, 3, 8))
        assertEquals(1, rookOrBishopThreatens(1, 1, 1, 3, 3, 2))
    }

    @Test
    fun triangleKind() {
        assertEquals(-1, triangleKind(3.0, 7.5, 4.0))
        assertEquals(1, triangleKind(5.0, 3.0, 4.0))
        assertEquals(2, triangleKind(4.0, 6.0, 8.0))
        assertEquals(0, triangleKind(1.0, 1.5, 1.5))
    }

    @Test
    fun isNumberHappy() {
        assertTrue(isNumberHappy(1221))
        assertTrue(isNumberHappy(1450))
        assertFalse(isNumberHappy(1234))
        assertFalse(isNumberHappy(3512))
    }

    @Test
    fun queenThreatens() {
    }

    @Test
    fun daysInMonth() {
    }

    @Test
    fun brickPasses() {
    }
}