import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.sqrt
import kotlin.random.Random

interface Shape {
    fun calcArea(): Double
    fun calcPerimeter(): Double
}

class Circle(val radius: Double) : Shape {
    init {
        if (radius <= 0)
            throw IllegalArgumentException("Radius can't be zero or less.")
    }

    override fun calcArea(): Double {
        return PI * radius * radius
    }

    override fun calcPerimeter(): Double {
        return 2 * PI * radius
    }
}

class Square(val side: Double) : Shape {

    init {
        if (side <= 0) throw IllegalArgumentException("Square's side can't be zero or less.")
    }

    override fun calcArea(): Double {
        return side * side
    }

    override fun calcPerimeter(): Double {
        return 4 * side
    }
}

class Triangle(
    val first: Double,
    val second: Double,
    val third: Double
) : Shape {

    init {
        if ((first + second < third) or (first + third < second)
            or (second + third < first)
        ) throw IllegalArgumentException("This triangle is not valid.")
        if ((first <= 0) or (second <= 0) or (third <= 0))
            throw IllegalArgumentException("Triangle's sides can't be zero or less.")
    }

    override fun calcArea(): Double {
        /* Heron's formula */
        val halfPer: Double = calcPerimeter() / 2
        return sqrt(
            halfPer * (halfPer - first) *
                    (halfPer - second) * (halfPer - third)
        )
    }

    override fun calcPerimeter(): Double {
        return (first + second + third)
    }
}

class Rectangle(val first: Double, val second: Double) : Shape {
    init {
        if ((first <= 0) or (second <= 0))
            throw IllegalArgumentException("Rectangle's sides can't be zero or less.")
    }

    override fun calcArea(): Double {
        return first * second
    }

    override fun calcPerimeter(): Double {
        return 2 * (first + second)
    }
}

interface ShapeFactory {
    fun createCircle(radius: Double): Circle
    fun createSquare(side: Double): Square
    fun createRectangle(first: Double, second: Double): Rectangle
    fun createTriangle(first: Double, second: Double, third: Double): Triangle

    fun createRandomCircle(): Circle
    fun createRandomSquare(): Square
    fun createRandomRectangle(): Rectangle
    fun createRandomTriangle(): Triangle

    fun createRandomShape(): Shape
}

class ShapeFactoryImpl : ShapeFactory {
    private val maxRnd = 1000.0

    override fun createCircle(radius: Double): Circle {
        return Circle(radius)
    }

    override fun createSquare(side: Double): Square {
        return Square(side)
    }

    override fun createRectangle(first: Double, second: Double): Rectangle {
        return Rectangle(first, second)
    }

    override fun createTriangle(first: Double, second: Double, third: Double): Triangle {
        return Triangle(first, second, third)
    }

    override fun createRandomCircle(): Circle {
        return Circle(Random.nextDouble(0.0, maxRnd))
    }

    override fun createRandomRectangle(): Rectangle {
        return Rectangle(
            Random.nextDouble(0.0, maxRnd),
            Random.nextDouble(0.0, maxRnd)
        )
    }

    override fun createRandomSquare(): Square {
        return Square(Random.nextDouble(0.0, maxRnd))
    }

    override fun createRandomTriangle(): Triangle {
        val first = Random.nextDouble(0.0, maxRnd)
        val second = Random.nextDouble(0.0, maxRnd)
        val third = Random.nextDouble(abs(first - second) + 1.0, (first + second))
        return Triangle(first, second, third)
    }

    override fun createRandomShape(): Shape {
        return when ((1..4).random()) {
            1 -> createRandomCircle()
            2 -> createRandomSquare()
            3 -> createRandomTriangle()
            else -> createRandomRectangle()
        }
    }
}