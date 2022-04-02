package lessons.task4

/**
 * NULLABLES
 */



fun main() {

}

/*
    Операции над null
    Напоследок давайте чуть ближе познакомимся с объектом null --- тем самым специальным значением, которое означает
    отсутствие чего-то в ассоциативном массиве. Данная "пустота" в Котлине не может появиться и использоваться
    просто так; если вы попробуете, например, присвоить null в переменную типа Int, то у вас ничего не получится.
    Дело в том, что значение null является допустимым только для специальных nullable типов; все обычные типы по
    умолчанию являются non-nullable.

    Каким образом можно сделать nullable тип? Очень просто --- если вы хотите сделать nullable версию Int,
    то нужно написать Int?. Знак вопроса, обычно выражающий сомнение, в данном контексте делает то
    же самое --- сигнализирует, что этот тип может как иметь нормальное значение, так и значение null.

    Есть ли еще какая-либо разница между типами Int и Int?, кроме того, что во втором может храниться null?
    Да, разница есть, и она заключается в том, что многие операции, возможные над Int, нельзя выполнить
    просто так над Int?. Представим, что мы хотим сложить два Int?.

 */


/**
 *      fun addNullables(a: Int?, b: Int?): Int = a + b  // ERROR
 */

/*
    Данный код не будет работать аж с целыми двумя ошибками:
    "Operator call corresponds to a dot-qualified call 'a.plus(b)' which is not allowed on a nullable receiver 'a'" и
    "Type mismatch: inferred type is Int? but Int was expected". Эти ошибки вызваны как раз тем, что в переменной
    с типом Int? может храниться null, а как сложить что-то с тем, чего нет?

    Так как операции с nullable типами являются потенциально опасными, в Котлине для работы с ними есть специальные
    безопасные операции и операторы, которые учитывают возможность появления null. Одним из таких операторов является
    элвис-оператор ?:, названный так в честь схожести с прической короля рок-н-ролла Элвиса Пресли.
    Рассмотрим, как он работает.

    Выражение a ?: valueIfNull возвращает a в случае, если a не равно null,
    и valueIfNull в противном случае. Это позволяет предоставить "значение по умолчанию" для случая,
    когда в переменной хранится null. В нашем случае сложения двух чисел мы можем считать,
    что если какого-то числа нет (null), то оно равно нулю.
 */

fun addNullables(a: Int?, b: Int?): Int = (a ?: 0) + (b ?: 0)

/*
    Еще один null-специфичный оператор --- это оператор безопасного вызова ?.
    Он используется в случаях, когда необходимо безопасно вызвать функцию над объектом,
    который может быть null. Выражение a?.foo(b, c) возвращает результат вызова функции foo с аргументами b и c над
    получателем a, если a не равен null; в противном случае возвращается null. Пусть нам нужно
    вернуть сумму элементов в nullable cписке.
 */

/**
 *      fun sumOfNullableList(list: List<Int>?): Int = list?.sum()  // ERROR
 */

/*

Такой код не будет работать, потому что list?.sum() может вернуть null. Если подсмотреть в IntelliJ IDEA, то можно
увидеть, что тип такого выражения, --- Int?; чтобы исправить ситуацию с типом возвращаемого значения, можно
воспользоваться элвис-оператором.
 */

fun sumOfNullableList(list: List<Int>?): Int = list?.sum() ?: 0


// оператор !! для игнорирования ошибки nullPointerException при вызове функции


/**
 * MAPS
 */


/*
   Рассмотрим основные операции, доступные над ассоциативными массивами ( Map<Key, Value> ):


    * map[key] / map.get(key) возвращает значение для ключа key или null в случае, если значения нет

    * map.size / map.count() возвращает количество пар "ключ"-"значение" в ассоциативном массиве

    * map + pair возвращает новый ассоциативный массив на основе map, в который добавлено (или изменено)
      значение ключа, соответствующее паре "ключ"-"значение" из pair

    * map - key возвращает новый ассоциативный массив на основе map, из которого, наоборот, удалено значение ключа key

    * map1 + map2 собирает два ассоциативных массива в один, причем пары "ключ"-"значение" из map2 вытесняют значения
      из map1

    * map - listOfKeys возвращает новый ассоциативный массив на основе map, в котором нет ключей из списка listOfKeys

    * map.getOrDefault(key, defaultValue) является расширенной версией операции индексирования. В случае, если в map есть
      значение для ключа key, данное выражение вернет его; если значения нет, то будет возвращено значение
      по умолчанию defaultValue.

    * key in map / map.contains(key) / map.containsKey(key) возвращает true, если map содержит значение
      для ключа key и false в противном случае

    * map.containsValue(value) возвращает true, если map содержит значение value для хотя бы одного ключа
      и false в противном случае
 */

/*
   Изменяемый ассоциативный массив ( MutableMap<Key, Value> )

   Как и в случае со списками, обычный ассоциативный массив (или Map) нельзя изменить; если вы хотите иметь такую
   возможность, то следует использовать изменяемый ассоциативный массив (или MutableMap) типа MutableMap<Key, Value>.
   Аналогично List и MutableList, MutableMap расширяет Map, т.е. объект MutableMap может использоваться везде,
   где нужен Map, --- в подобных местах вы просто не будете использовать его возможности по модификации.

   MutableMap предоставляет следующие основные возможности по модификации.

    * map[key] = value изменяет имеющееся значение для заданного ключа или добавляет новую пару "ключ"-"значение"
      в случае, если ключ key не был связан в map

    * map.remove(key) удаляет пару, связанную с ключом key

   Основные операции, доступные над изменяемыми ассоциативными массивами:

    * map.clear() удаляет все записи из данного MutableMap

    * map[key] = value / map.put(key, value) добавляет или изменяет соответствующую пару "ключ"-"значение"

    * map.putAll(otherMap) добавляет в MutableMap map все пары из otherMap, в случае одинаковых ключей значения
      из otherMap перезаписывают значения из map

    * map.remove(key) удаляет пару для ключа key
*/

/**
 * SETS
 */

/*
   Рассмотрим основные операции, доступные над множествами ( Set<T> ):

    * set.size / set.count() возвращает количество элементов в множестве

    * e in set / set.contains(e) проверяет, содержится ли элемент e во множестве set

    * set.intersect(otherSet) осуществляет пересечение множеств

    * set.union(otherSet) объединяет два множества

    * set + e / set + array / set + list создает новое множество с добавленным элементом или элементами

    * set - e / set - array / set - list возвращает множество, из которого удалены указанные элементы

    Все операции поддерживают уникальность элементов в результирующем множестве автоматически.
 */

/*
   Рассмотрим основные операции, доступные над изменяемыми множествами ( MutableSet<T> ).

    * set.add(element) добавляет элемент в множество

    * set.addAll(listOrSet) добавляет все элементы из заданного набора элементов

    * set.remove(element) удаляет элемент из множества

    * set.removeAll(listOrSet) удаляет все элементы из заданного набора элементов

    * set.retainAll(listOrSet) оставляет в множестве только элементы, которые есть в заданном наборе элементов

    * set.clear() удаляет из множества все элементы

    Как и раньше, поддержание уникальности элементов выполняется автоматически.
 */


/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
    shoppingList: List<String>,
    costs: Map<String, Double>
): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
    phoneBook: MutableMap<String, String>,
    countryCode: String
) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
    text: List<String>,
    vararg fillerWords: String
): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}

/**
 * Задача 1
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 *
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Оставлять пустой список знакомых для людей, которые их не имеют (см. EvilGnome ниже),
 * в том числе для случая, когда данного человека нет в ключах, но он есть в значениях
 * (см. GoodGnome ниже).
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta"),
 *       "Friend" to setOf("GoodGnome"),
 *       "EvilGnome" to setOf()
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat"),
 *          "Friend" to setOf("GoodGnome"),
 *          "EvilGnome" to setOf(),
 *          "GoodGnome" to setOf()
 *        )
 */

fun addFromSet(map: Map<String, Set<String>>, name: String, blacklist: Set<String>): Set<String> {
    val resultSet = mutableSetOf<String>()
    for (setName in map[name] ?: setOf()) {
        if (setName !in blacklist) {
            resultSet.add(setName)
            resultSet.union(addFromSet(map, setName, blacklist + setName))
        }
    }
    return resultSet
}

fun cleaner(map: Map<String, Set<String>>): Map<String, Set<String>> {
    val resultOfClean = mutableMapOf<String, Set<String>>()
    for ((name, setToClean) in map) {
        resultOfClean[name] = setToClean
        for (setNameToClean in setToClean) {
            if (resultOfClean[name] == null) resultOfClean[name] = setOf()
            if (setNameToClean == name) {
                setToClean - setNameToClean
            }
        }
    }
    return resultOfClean
}

fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    val result = mutableMapOf<String, Set<String>>()
    for ((name, set) in friends) {
        result[name] = addFromSet(friends, name, setOf(name))
    }
    return result
}