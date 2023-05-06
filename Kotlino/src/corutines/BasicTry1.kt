package lb.sandbox.kotlino.corutines

import kotlinx.coroutines.*


object BasicTry1 {


    @JvmStatic
    fun main(args: Array<String>) {
        printThreadName("Very begin")
        runBlocking {
            delay(1)
            printThreadName("From runBlocking")
        }

        for (x in mkSeq()) {
            println(x)
        }
    }

    fun mkSeq(): Sequence<Number> = sequence {
        yield(1)
        yieldAll(listOf(22,33,44))
        printThreadName("From mkSeq")
    }
    


    @JvmStatic
    private fun printThreadName(prefix: String? = null) {
        val threadName = Thread.currentThread().name
        if (prefix != null) println("$prefix: thread: $threadName")
        else println("Thread: $threadName")
    }


}