import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class LW3_tests {
    val factory = ShapeFactoryImpl()

    @Test
    fun testWrongRadius() {
        assertFails { factory.createCircle(0.0) }
    }

    @Test
    fun testWrongSquareSide() {
        assertFails { factory.createSquare(-5.55) }
    }

    @Test
    fun testWrongRectangleSides() {
        assertFails { factory.createRectangle(0.0, -10.02) }
    }

    @Test
    fun testWrongTriangleSides() {
        assertFails { factory.createTriangle(-5.0, 0.01, 0.0) }
    }

    @Test
    fun testTrianglesLaw() {
        val exception = "This triangle is not valid."
        assertFails(exception) { factory.createTriangle(10.0, 20.0, 100.0) }
    }

    @Test
    fun testCirclePerimeter() {
        val circle = factory.createCircle(10.0)
        assertEquals(62.83185307179586, circle.calcPerimeter())
    }

    @Test
    fun testCircleArea() {
        val circle = factory.createCircle(10.0)
        assertEquals(314.1592653589793, circle.calcArea())
    }

    @Test
    fun testSquarePerimeter() {
        val sq = factory.createSquare(10.0)
        assertEquals(40.0, sq.calcPerimeter())
    }

    @Test
    fun testSquareArea() {
        val sq = factory.createSquare(10.0)
        assertEquals(100.0, sq.calcArea())
    }

    @Test
    fun testRectanglePerimeter() {
        val rect = factory.createRectangle(10.0, 5.0)
        assertEquals(30.0, rect.calcPerimeter())
    }

    @Test
    fun testRectangleArea() {
        val rect = factory.createRectangle(10.0, 5.0)
        assertEquals(50.0, rect.calcArea())
    }

    @Test
    fun testTrianglePerimeter() {
        val triangle = factory.createTriangle(3.0, 4.0, 5.0)
        assertEquals(12.0, triangle.calcPerimeter())
    }

    @Test
    fun testTriangleArea() {
        val triangle = factory.createTriangle(3.0, 4.0, 5.0)
        assertEquals(6.0, triangle.calcArea())
    }
}