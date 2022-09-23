package coroutines.lesson1

import kotlinx.coroutines.*
import java.lang.Thread.sleep
import java.util.concurrent.Executors

// lab 1 *
// suspend functions *
// threads *
// coroutine context *
// runBlocking *
// lab 2 *
// lab 3
// jobs, cancellation why not cancel immediately
// async?
//
//



fun main() {
//    lab1()
//    lab2()
    lab3()
}



/**
 * Create first coroutine with another coroutine))
 */
fun lab1() {
    println("Lab1")
    runBlocking {
        launch {
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            println("World!") // print after delay
        }
        println("Hello") // main coroutine continues while a previous one is delayed
    }
}

/**
 * Difference between coroutine and Thread
 */
fun lab2() {
    println("Lab2")
    println("Test Threads = true\nTest Coroutines = false\nInput: ")
    try {
        val testCase = readln().toBooleanStrict()

        if (testCase) {
            println("starting thread test:")
            repeat(100_000) {
                Thread {
                    sleep(1000L)
                    println("$it")
                }.start()
            }
        } else {
            runBlocking {
                println("starting coroutine test:")
                repeat(100_000) {
                    launch {
                        delay(1000L)
                        println("$it")
                    }
                }
            }
        }
        println("finished")
    } catch (e: Exception) {
        println("not true/false")
    }
}


/**
 * MULTITHREADING WITH COROUTINES AND MAJOR DIFFICULTIES
 */
fun lab3() {
    println("Lab3")
    var counter = 0

    runBlocking {
        launch { repeat(100_000) { counter += 1 } }
        launch { repeat(100_000) { counter += 1 } }
    }
    println(counter)


    println("Now creating 2 threads")
    var sum = 0
    val tread1 = Thread { repeat(100000) { sum += 1 } }
    val tread2 = Thread { repeat(100000) { sum += 1 } }

    tread1.start()
    tread2.start()

    tread1.join()
    tread2.join()
    println(sum)

    println("Now creating 2 threads with coroutines")

    val context1 = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    val context2 = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    var sum2 = 0


    runBlocking {
        launch(context1) {
            repeat(100000) { sum2 += 1 }
        }
        launch(context2) {
            repeat(100000) { sum2 += 1 }
        }
    }
    println(sum2)
}



suspend fun doWorld() {
    coroutineScope {
        delay(5000L)
        launch {
            delay(2000L)
            println("World 2")
        }
        launch {
            delay(1000L)
            println("World 1")
        }
        println("Hello")
    }
}


