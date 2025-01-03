import kotlinx.coroutines.*
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

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

        "A".also2 {
            doSomethingElse()
        }

        printCurrentThreadName("More $infix end")
    }

    private suspend fun doSomethingElse() {
        yield()
    }


    inline fun <T> T.also2(block: (T) -> Unit): T {
        contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
        block(this)
        return this
    }


    fun sequence9(): Sequence<String> =
        sequence {
            for (c1 in 'A'..'C') {
                for (c2 in 'X'..'Z') {
                    yield("$c1$c2")
                }
            }
        }



}





