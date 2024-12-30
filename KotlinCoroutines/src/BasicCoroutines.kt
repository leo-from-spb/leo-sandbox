import kotlinx.coroutines.*

object BasicCoroutines {


    @JvmStatic
    fun main(args: Array<String>) {
        println("Hello!")
        printCurrentThreadName("Main")

        runBlocking {
            do1()
        }

    }


    suspend fun do1() {
        printCurrentThreadName("Top coroutine at beginning")
        delay(42)
        coroutineScope {
            launch { doMore("A") }
        }
        coroutineScope {
            launch { doMore("B") }
        }
        printCurrentThreadName("Top coroutine after launching coroutines")
        delay(42)
        printCurrentThreadName("Top coroutine at end")
    }

    suspend fun doMore(infix: String) {
        printCurrentThreadName("More $infix start")
        delay(42)
        yield()
        printCurrentThreadName("More $infix end")
    }



}





