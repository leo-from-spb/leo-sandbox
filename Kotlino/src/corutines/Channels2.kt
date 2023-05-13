@file:OptIn(DelicateCoroutinesApi::class, ExperimentalContracts::class)
@file:Suppress("BlockingMethodInNonBlockingContext")

package lb.sandbox.kotlino.corutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ChannelIterator
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

object Channels2 {

    @JvmStatic
    fun main(args: Array<String>) {
        xxx()
    }


    fun xxx() {
        val theChannel = Channel<Number>(10)
        runBlocking {
            val job1 = launch(Dispatchers.Default) {
                produceNumbers(theChannel)
                delay(1)
                theChannel.close()
            }
            val job2 = launch(Dispatchers.Default) {
                consumeNumbers(theChannel)
            }
            //job1.join()
            //job2.join()
        }
    }


    private suspend fun produceNumbers(channel: SendChannel<Number>) {
        var k = 0
        for (i in 1..20) {
            val bunch: Array<Number> =
                withContext(Dispatchers.IO) {
                    Thread.sleep(33)
                    println("Generating data in thread ${Thread.currentThread().name}")
                    arrayOf<Number>((++k).toByte(), (++k).toShort(), ++k, (++k).toLong())
                }
            println("Sending numbers (${bunch.joinToString()}) from ${Thread.currentThread().name}")
            //for (x in bunch) channel.send(x)
            bunch.forEach { channel.send(it) }
            zzz { channel.send(-1) }
        }
        println("Producer has finished.")
    }

    private suspend fun consumeNumbers(channel: ReceiveChannel<Number>) {
        try {
            val iterator: ChannelIterator<Number> = channel.iterator()
            while (iterator.hasNext()) {
                val item = iterator.next()
                Thread.sleep(55)
                println("Processing $item on ${Thread.currentThread().name}")
            }
        }
        finally {
            println("clean up")
        }
    }


    private inline fun zzz (block: ()->Unit) {
        contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
        block()
    }

}