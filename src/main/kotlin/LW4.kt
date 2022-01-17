class Matrix(
    mtrx: Array<Array<Int>> = emptyArray()
) {
    // all data we will obtain by functions
    private var matrix: Array<Array<Int>> = emptyArray()
    val rows: Int
        get() = matrix.size
    val columns: Int
        get() = matrix[0].size

    init {
        if (mtrx.isEmpty() or mtrx[0].isEmpty())
            throw IllegalArgumentException("Matrix can't be empty.")
        val trueSize = mtrx[0].size
        for (elem in mtrx){
            if (elem.size != trueSize)
                throw IllegalArgumentException("Matrix can't have unequal columns.")
        }
        matrix = Array(mtrx.size) { Array(mtrx[0].size) { 0 } }
        // could it be copyOf()? or clone()?
        for (i in mtrx.indices)
            for (j in mtrx[0].indices)
                matrix[i][j] = mtrx[i][j]
    }

    operator fun plus(other: Matrix): Matrix{
        if ((other.rows != this.rows) or (other.columns != this.columns))
            throw IllegalArgumentException("ERROR: The dimensions of the matrices do not match.")
        val result = Matrix(this.matrix)
        for (i in 0 until other.rows)
            for (j in 0 until other.columns)
                result[i, j] = this[i, j] + other[i, j]
        return result
    }

    operator fun plusAssign(other: Matrix) {
        if ((other.rows != this.rows) or (other.columns != this.columns))
            throw IllegalArgumentException("ERROR: The dimensions of the matrices do not match.")
        for (i in 0 until other.rows)
            for (j in 0 until other.columns)
                this[i, j] += other[i, j]
    }

    operator fun times(scalar: Int): Matrix {
        val result = Matrix(this.matrix)
        for (i in 0 until result.rows)
            for (j in 0 until result.columns)
                result[i, j] = scalar*this[i, j]
        return result
    }

    operator fun timesAssign(scalar: Int) {
        for (i in 0 until this.rows)
            for (j in 0 until this.columns)
                this[i, j] *= scalar
    }

    operator fun set(i: Int, j: Int, value: Int) {
        if ((i >= rows) or (j >= columns)
            or (i < 0) or (j < 0))
            throw IllegalArgumentException("ERROR: Index is out of bounds.")
        matrix[i][j] = value
    }

    operator fun get(i: Int, j: Int): Int {
        if ((i >= rows) or (j >= columns)
            or (i < 0) or (j < 0))
            throw IllegalArgumentException("ERROR: Index is out of bounds.")
        return matrix[i][j]
    }

    operator fun minus(other: Matrix): Matrix{
        return this + (-other)
    }

    operator fun unaryMinus(): Matrix {
        return(this.times(-1))  // returns (-matrix)
    }

    operator fun unaryPlus(): Matrix {
        return this
    }

    operator fun div(scalar: Int): Matrix{
        val result = Matrix(this.matrix)
        for (i in 0 until result.rows)
            for (j in 0 until result.columns)
                result[i, j] = this[i, j]/scalar
        return result
    }

    operator fun divAssign(scalar: Int) {
        for (i in 0 until this.rows)
            for (j in 0 until this.columns)
                this[i, j] /= scalar
    }

    override fun hashCode(): Int {
        return matrix.contentDeepHashCode()
    }

    override fun toString(): String {
        var resStr = ""
        for (i in matrix.indices){
            for (j in matrix[0].indices)
                resStr += matrix[i][j].toString() + " "
            if (i != matrix.lastIndex)
                resStr += "\n"
        }
        return resStr
    }

    override fun equals(other: Any?): Boolean {
        // checking class name
        if (javaClass != other?.javaClass)
            return false
        // checking address
        if (this === other)
            return true

        other as Matrix
        // checking dimensions
        if ((rows != other.rows) or (columns != other.columns))
            return false
        // checking values
        for (i in matrix.indices)
            for (j in matrix[0].indices)
                if (this[i, j] != other[i, j])
                    return false
        // everything is ok!
        return true
    }
}