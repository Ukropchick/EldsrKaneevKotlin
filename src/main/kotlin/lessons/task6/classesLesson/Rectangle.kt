package lessons.task6.classesLesson

class Rectangle constructor(
    val a: Double,
    val b: Double
): Shape {
    constructor(
        a: Int,
        b: Int
    ) : this(a.toDouble(), b.toDouble())

    init {
        println("Created rectangle with sides $a, $b")
        println(getArea())
    }

    companion object {
        var PI = 3.14
    }


    fun getPI() = PI

    override fun getArea() = a * b
    override fun getPerimeter() = (a + b) * 2
}