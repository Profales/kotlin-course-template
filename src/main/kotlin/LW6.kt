object ShapeComparator {
    val byAreaDesc = compareByDescending<Shape> { it.calcArea() }
    val byAreaAsc = compareBy<Shape> { it.calcArea() }
    val byPerimeterDesc = compareByDescending<Shape> { it.calcPerimeter() }
    val byPerimeterAsc = compareBy<Shape> { it.calcPerimeter() }
    val byRadiusDesc = compareByDescending<Circle> { it.radius }
    val byRadiusAsc = compareBy<Circle> { it.radius }
}

class ShapeCollector<T : Shape> {
    private val allShapes = mutableListOf<T>()

    fun add(new: T) {
        allShapes.add(new)
    }

    fun addAll(new: Collection<T>) {
        allShapes.addAll(new)
    }

    fun getAll(): List<T> {
        return allShapes
    }

    fun getAllSorted(comparator: Comparator<in T>): List<T> {
        return allShapes.sortedWith(comparator)
    }

    //fun getAllByClass(classType: ??????????): List<T> {
    //    /* ??????? */
    //}
}
