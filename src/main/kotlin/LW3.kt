import kotlin.math.PI
import kotlin.math.sqrt
import kotlin.random.Random

interface Shape {
    fun calcArea(): Double
    fun calcPerimeter(): Double
}

class Circle(val _radius: Double) : Shape {
    init {
        if (_radius <= 0)
            throw IllegalArgumentException("Radius can't be zero or less.")
    }

    override fun calcArea(): Double {
        return PI * _radius * _radius
    }

    override fun calcPerimeter(): Double {
        return 2 * PI * _radius
    }
}

class Square(val _side: Double) : Shape {

    init {
        if (_side <= 0) throw IllegalArgumentException("Square's side can't be zero or less.")
    }

    override fun calcArea(): Double {
        return _side * _side
    }

    override fun calcPerimeter(): Double {
        return 4 * _side
    }
}

class Triangle(
    val _first: Double,
    val _second: Double,
    val _third: Double
) : Shape {

    init {
        if ((_first + _second < _third) or (_first + _third < _second)
            or (_second + _third < _first)
        ) throw IllegalArgumentException("This triangle is not valid.")
        if ((_first <= 0) or (_second <= 0) or (_third <= 0))
            throw IllegalArgumentException("Triangle's sides can't be zero or less.")
    }

    override fun calcArea(): Double {
        /* Heron's formula */
        val halfPer: Double = calcPerimeter()/2
        return sqrt(
            halfPer * (halfPer - _first) *
                    (halfPer - _second) * (halfPer - _third)
        )
    }

    override fun calcPerimeter(): Double {
        return (_first + _second + _third)
    }
}

class Rectangle(val _first: Double, val _second: Double) : Shape {
    init {
        if ((_first <= 0) or (_second <= 0))
            throw IllegalArgumentException("Rectangle's sides can't be zero or less.")
    }

    override fun calcArea(): Double {
        return _first * _second
    }

    override fun calcPerimeter(): Double {
        return 2 * (_first + _second)
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
        // random double number as a radius
        return Circle(Random.nextDouble(0.0, 1000.0))
        // here could be some specific formula with Double.MAX_VALUE,
        // but I'm too lazy for that
        // can't type (0.0, Double.MAX_VALUE) 'coz of pow(2)
    }

    override fun createRandomRectangle(): Rectangle {
        return Rectangle(Random.nextDouble(0.0, 1000.0),
            Random.nextDouble(0.0, 1000.0))
    }

    override fun createRandomSquare(): Square {
        return Square(Random.nextDouble(0.0, 1000.0))
    }

    override fun createRandomTriangle(): Triangle {
        return Triangle(Random.nextDouble(0.0, 1000.0),
            Random.nextDouble(0.0, 1000.0),
            Random.nextDouble(0.0, 1000.0))
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