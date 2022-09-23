package lessons.task6.classesLesson

/**
 *  1. Создание класса *
 *  2. init блок *
 *  3. Наследование *
 *  4. Модификаторы видимости *
 *  5. Абстранции *
 *  7. Конструкторы и их перегрузка + функции *
 *  8. Объекты и компаньоны к классу
 */


fun main() {


    val firstRectangle = Rectangle(6, 9)
    val secondRectangle = Rectangle(5, 8)
//    firstRectangle.changeName("Колесо")

    fun perimeterXArea(shape1: Shape) {
        println(shape1.getPerimeter() * shape1.getArea())
    }
    perimeterXArea(firstRectangle)
    println(firstRectangle)

    println("############")
    println(Rectangle.PI)
    Rectangle.PI = 2.1
    println(firstRectangle.getPI())
    println(secondRectangle.getPI())
    println("############")
    println(myVars.PI)
    myVars.PI = 2.8
    println(myVars.PI)

    println(Rectangle(3, 4))

    val a = 100000000000000000


}




