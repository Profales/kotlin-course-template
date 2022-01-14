import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class LW1_tests {
    @Test
    fun testWithEmptyString(){
        val testTxt = ""
        val exception = "ERROR: line is empty."
        assertFails(exception) {alignText(testTxt)}
    }
    @Test
    fun testWithWrongWidth(){
        val testTxt = "dddd"
        assertFails {alignText(testTxt, 0)}
    }
    @Test
    fun testAlignLeft(){
        val testTxt = "223 11 2 666"
        assertEquals("22\n3 \n11\n2 \n66\n6 ", alignText(testTxt, 2))
    }
    @Test
    fun testAlignRight(){
        val testTxt = "223 11 2 666"
        assertEquals("22\n 3\n11\n 2\n66\n 6", alignText(testTxt, 2, Alignment.RIGHT))
    }
    @Test
    fun testAlignCenter(){
        val testTxt = "223 1 2 666"
        assertEquals("223\n1 2\n666", alignText(testTxt, 3, Alignment.CENTER))
    }
    @Test
    fun testLongWord(){
        val testTxt = "Now it's the time for LOOOOOL"
        assertEquals("Now \nit's\nthe \ntime\nfor \nLOOO\nOOL ", alignText(testTxt, 4))
    }
}