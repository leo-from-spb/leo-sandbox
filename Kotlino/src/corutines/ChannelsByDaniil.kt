package lb.sandbox.kotlino.corutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce

object ChannelsByDaniil {

    @JvmStatic
    fun main(args: Array<String>) {
        runBlocking(Dispatchers.Default) { // CPU-bound
            xxx()
        }
    }

    fun CoroutineScope.xxx() {
        val channel = produce(capacity = Int.MAX_VALUE) {
            for (i in 1..50) {
                withContext(Dispatchers.IO) {
                    Thread.sleep(10)
                }
                println("Sending $i from ${Thread.currentThread().name}")
                send(i) // checks for cancellation if suspends
                ensureActive()
            }
        }
        launch {
            try {
                val iterator = channel.iterator()
                while (iterator.hasNext()) {
                    val item = iterator.next()
                    Thread.sleep(100)
                    println("Processing $item on ${Thread.currentThread().name}")
                }
            }
            finally {
                println("clean up")
            }
        }
    }

}