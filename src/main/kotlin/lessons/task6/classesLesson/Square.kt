package lessons.task6.classesLesson

class Square(
    val a: Int
): Shape {
    override fun getArea() = a * a * 1.0
    override fun getPerimeter(): Double = a * 4.0

}