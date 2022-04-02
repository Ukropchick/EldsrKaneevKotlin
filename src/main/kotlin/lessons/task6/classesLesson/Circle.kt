package lessons.task6.classesLesson

import kotlin.math.PI

class Circle(
    val radius: Int
): Shape {
    override fun getArea(): Double = PI * radius * radius

    override fun getPerimeter() = 2 * PI * radius

}