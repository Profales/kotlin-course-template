fun main() {
    // LW#3
    val shpFact = ShapeFactoryImpl()
    val shapesList = listOf(
        shpFact.createCircle(15.13),
        shpFact.createSquare(0.01),
        shpFact.createRandomCircle(),
        shpFact.createRandomRectangle(),
        shpFact.createTriangle(10.0, 20.0, 13.5),
        shpFact.createRandomShape(),
        shpFact.createRandomSquare()
    )

    var sumPerimeter = 0.0
    var sumArea = 0.0
    var minPerimeter: Double = Double.MAX_VALUE
    var minArea: Double = Double.MAX_VALUE
    var maxPerimeter: Double = Double.MIN_VALUE
    var maxArea: Double = Double.MIN_VALUE
    
    for (shape in shapesList){
        sumPerimeter += shape.calcPerimeter()
        sumArea += shape.calcArea()
        if (shape.calcPerimeter() < minPerimeter)
            minPerimeter = shape.calcPerimeter()
        if (shape.calcArea() < minArea)
            minArea = shape.calcArea()
        if (shape.calcPerimeter() > maxPerimeter)
            maxPerimeter = shape.calcPerimeter()
        if (shape.calcArea() > maxArea)
            maxArea = shape.calcArea()
    }
    // Try adding program arguments at Run/Debug configuration
    println("Total area: $sumArea")
    println("Total perimeter: $sumPerimeter")

    /* how can i make that better? */
    val shapeMinA = shapesList.find{it.calcArea() == minArea}
    val shapeMinP = shapesList.find{it.calcPerimeter() == minPerimeter}
    val shapeMaxA = shapesList.find{it.calcArea() == maxArea}
    val shapeMaxP = shapesList.find{it.calcPerimeter() == maxPerimeter}
    /*
    putting 'shapesList.find{it.calcPerimeter() == maxPerimeter}' in println()
    doesn't work
    */

    println("Shape with the minimal area, type: " +
            shapeMinA?.javaClass?.simpleName
    )
    println("Shape with the minimal area, min area: " +
            "$minArea")
    println("Shape with the minimal perimeter, type: " +
            shapeMinP?.javaClass?.simpleName
    )
    println("Shape with the minimal perimeter, min perimeter: " +
            "$minPerimeter")

    println("Shape with the maximal area, type: " +
            shapeMaxA?.javaClass?.simpleName
    )
    println("Shape with the maximal area, max area: " +
            "$maxArea")
    println("Shape with the maximal perimeter, type: " +
            shapeMaxP?.javaClass?.simpleName
    )
    println("Shape with the maximal perimeter, max perimeter: " +
            "$maxPerimeter")
}