package lessons.task6.practice

import kotlin.math.*

fun sqr(n: Int) = n * n
fun sqr(n: Double) = n * n

/**
 * Точка на плоскости
 */
data class Point(val x: Double, val y: Double) {
    /**
     * Пример
     *
     * Рассчитать (по известной формуле) расстояние между двумя точками
     */
    fun distance(other: Point): Double = sqrt(sqr(x - other.x) + sqr(y - other.y))
}

/**
 * Треугольник, заданный тремя точками (a, b, c, см. constructor ниже).
 * Эти три точки хранятся в множестве points, их порядок не имеет значения.
 */
@Suppress("MemberVisibilityCanBePrivate")
class Triangle private constructor(private val points: Set<Point>) {

    private val pointList = points.toList()

    val a: Point get() = pointList[0]

    val b: Point get() = pointList[1]

    val c: Point get() = pointList[2]

    constructor(a: Point, b: Point, c: Point) : this(linkedSetOf(a, b, c))

    /**
     * Пример: полупериметр
     */
    fun halfPerimeter() = (a.distance(b) + b.distance(c) + c.distance(a)) / 2.0

    /**
     * Пример: площадь
     */
    fun area(): Double {
        val p = halfPerimeter()
        return sqrt(p * (p - a.distance(b)) * (p - b.distance(c)) * (p - c.distance(a)))
    }

    /**
     * Пример: треугольник содержит точку
     */
    fun contains(p: Point): Boolean {
        val abp = Triangle(a, b, p)
        val bcp = Triangle(b, c, p)
        val cap = Triangle(c, a, p)
        return abp.area() + bcp.area() + cap.area() <= area()
    }

    override fun equals(other: Any?) = other is Triangle && points == other.points

    override fun hashCode() = points.hashCode()

    override fun toString() = "Triangle(a = $a, b = $b, c = $c)"
}

/**
 * Окружность с заданным центром и радиусом
 */
data class Circle(val center: Point, val radius: Double) {
    /**
     * Простая (2 балла)
     *
     * Рассчитать расстояние между двумя окружностями.
     * Расстояние между непересекающимися окружностями рассчитывается как
     * расстояние между их центрами минус сумма их радиусов.
     * Расстояние между пересекающимися окружностями считать равным 0.0.
     */
    fun distance(other: Circle): Double {
        return if (center.distance(other.center) > radius + other.radius) {
            center.distance(other.center) - (radius + other.radius)
        } else 0.0
    }

    /**
     * Тривиальная (1 балл)
     *
     * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
     */
    fun contains(p: Point): Boolean = p.distance(center) <= radius
}

/**
 * Отрезок между двумя точками
 */
data class Segment(val begin: Point, val end: Point) {
    override fun equals(other: Any?) =
        other is Segment && (begin == other.begin && end == other.end || end == other.begin && begin == other.end)

    override fun hashCode() =
        begin.hashCode() + end.hashCode()
}

/**
 * Средняя (3 балла)
 *
 * Дано множество точек. Вернуть отрезок, соединяющий две наиболее удалённые из них.
 * Если в множестве менее двух точек, бросить IllegalArgumentException
 */
fun diameter(vararg points: Point): Segment {
    if (points.size < 2) throw IllegalArgumentException()
    var maxPoints = Pair(Point(0.0, 0.0), Point(0.0, 0.0))
    var maxDistance = 0.0
    var (i, j) = 0 to 0
    while (i < points.size - 1) {
        j = i + 1
        val fPoint = points[i]
        while (j < points.size) {
            val sPoint = points[j]
            val distance = fPoint.distance(sPoint)
            if (distance > maxDistance) {
                maxPoints = Pair(fPoint, sPoint); maxDistance = distance
            }
        }
    }
    return Segment(maxPoints.first, maxPoints.second)
}

/**
 * Простая (2 балла)
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle {
    val radius = diameter.end.distance(diameter.begin) / 2
    val centerX = (diameter.end.x + diameter.begin.x) / 2.0
    val centerY = (diameter.end.y + diameter.begin.y) / 2.0

    return Circle(Point(centerX, centerY), radius)
}

//

/**
 * Прямая, заданная точкой point и углом наклона angle (в радианах) по отношению к оси X.
 * Уравнение прямой: (y - point.y) * cos(angle) = (x - point.x) * sin(angle)
 * или: y * cos(angle) = x * sin(angle) + b, где b = point.y * cos(angle) - point.x * sin(angle).
 * Угол наклона обязан находиться в диапазоне от 0 (включительно) до PI (исключительно).
 *
 * y = x + tan(angle) + b / cos(angle)
 *
 */



class Line private constructor(val b: Double, val angle: Double) {
    init {
        require(angle >= 0 && angle < PI) { "Incorrect line angle: $angle" }
    }

    constructor(point: Point, angle: Double) : this(point.y * cos(angle) - point.x * sin(angle), angle)

    /**
     * Средняя (3 балла)
     *
     * Найти точку пересечения с другой линией.
     * Для этого необходимо составить и решить систему из двух уравнений (каждое для своей прямой)
     */
    fun crossPoint(other: Line): Point {
        val x = (other.b / cos(other.angle) - b / cos(angle)) / (tan(angle) - tan(other.angle))
        val y = (x  * sin(angle)) / cos(angle) + (b / cos(angle))

        return Point(x, y)
    }

    override fun equals(other: Any?) = other is Line && angle == other.angle && b == other.b

    override fun hashCode(): Int {
        var result = b.hashCode()
        result = 31 * result + angle.hashCode()
        return result
    }

    override fun toString() = "Line(${cos(angle)} * y = ${sin(angle)} * x + $b)"
}

/**
 * Средняя (3 балла)
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line {
    val gipotenuza = s.begin.distance(s.end)
    val katet = s.end.y - s.begin.y
    val sinusAngle = katet / gipotenuza
    val angle = asin(sinusAngle)

    return Line(s.begin, angle)
}

/**
 * Средняя (3 балла)
 *
 * Построить прямую по двум точкам
 */
fun lineByPoints(a: Point, b: Point): Line {
    val distance = a.distance(b)
    val katet = abs(a.y- b.y)
    val sinusAngle = katet / distance
    val angle = asin(sinusAngle)

    return Line(a, angle)

}

/**
 * Сложная (5 баллов)
 *
 * Построить серединный перпендикуляр по отрезку или по двум точкам
 */
fun bisectorByPoints(a: Point, b: Point): Line {
    val line = lineByPoints(a, b)
    val centerX = (a.x + b.x) / 2.0
    val centerY = (a.y + b.y) / 2.0

    return Line(Point(centerX, centerY), (line.angle + PI / 2.0) % PI)

}

/**
 * Средняя (3 балла)
 *
 * Задан список из n окружностей на плоскости.
 * Найти пару наименее удалённых из них; расстояние между окружностями
 * рассчитывать так, как указано в Circle.distance.
 *
 * При наличии нескольких наименее удалённых пар,
 * вернуть первую из них по порядку в списке circles.
 *
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> {
    if (circles.size < 2) throw IllegalArgumentException()
    var (i, j) = 0 to 0
    val zeroCircle = Circle(Point(0.0, 0.0), 0.0)
    var minDistance = Double.MAX_VALUE
    var minPair = Pair<Circle, Circle>(zeroCircle, zeroCircle)
    while (i < circles.size - 1) {
        j = i + 1
        val circle1 = circles[i]
        while (j < circles.size) {
            val circle2 = circles[j]
            if (circle1.distance(circle2) < minDistance) {
                minDistance = circle1.distance(circle2)
                minPair = circle1 to circle2
            }
            j++
        }
        i++
    }
    return minPair
}

///**
// * Сложная (5 баллов)
// *
// * Дано три различные точки. Построить окружность, проходящую через них
// * (все три точки должны лежать НА, а не ВНУТРИ, окружности).
// * Описание алгоритмов см. в Интернете
// * (построить окружность по трём точкам, или
// * построить окружность, описанную вокруг треугольника - эквивалентная задача).
// */
//fun circleByThreePoints(a: Point, b: Point, c: Point): Circle = TODO()
//
///**
// * Очень сложная (10 баллов)
// *
// * Дано множество точек на плоскости. Найти круг минимального радиуса,
// * содержащий все эти точки. Если множество пустое, бросить IllegalArgumentException.
// * Если множество содержит одну точку, вернуть круг нулевого радиуса с центром в данной точке.
// *
// * Примечание: в зависимости от ситуации, такая окружность может либо проходить через какие-либо
// * три точки данного множества, либо иметь своим диаметром отрезок,
// * соединяющий две самые удалённые точки в данном множестве.
// */
//fun minContainingCircle(vararg points: Point): Circle = TODO()



