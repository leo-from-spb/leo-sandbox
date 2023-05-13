@file:OptIn(DelicateCoroutinesApi::class, ExperimentalContracts::class)
@file:Suppress("BlockingMethodInNonBlockingContext")

package lb.sandbox.kotlino.corutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ChannelResult
import kotlinx.coroutines.channels.SendChannel
import java.lang.Thread.sleep
import kotlin.contracts.ExperimentalContracts

object Channels3 {

    @JvmStatic
    fun main(args: Array<String>) {
        xxx()
    }


    fun xxx() {
        val theChannel = Channel<Number>(15)
        GlobalScope.async {
            produceNumbers(theChannel)
            delay(1)
            theChannel.close()
        }

        var x: Number? = theChannel.takeNext()
        while (x != null) {
            println("Received: $x")
            sleep(55)
            x = theChannel.takeNext()
        }
    }


    private suspend fun produceNumbers(channel: SendChannel<Number>) {
        var k = 0
        for (i in 1..20) {
            val bunch: Array<Number> =
                    withContext(Dispatchers.IO) {
                        sleep(33)
                        println("Generating data in thread ${Thread.currentThread().name}")
                        arrayOf<Number>((++k).toByte(), (++k).toShort(), ++k, (++k).toLong())
                    }
            println("Sending numbers (${bunch.joinToString()}) from ${Thread.currentThread().name}")
            //for (x in bunch) channel.send(x)
            bunch.forEach { channel.send(it) }
        }
        println("Producer has finished.")
    }




    fun <T> Channel<T>.takeNext(): T? {
        val ch = this
        var result: T?
        runBlocking {
            val z: ChannelResult<T> = ch.receiveCatching()
            result = z.getOrNull()
        }
        return result
    }


}