@file:JvmName("ConsoleUtil")


fun printCurrentThreadName(something: String? = null) {
    val threadName = Thread.currentThread().name
    var message =
        if (something != null && something.isNotEmpty()) "$something thread: $threadName"
        else "Current thread: $threadName"
    println(message)
}
