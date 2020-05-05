package lb.sandbox




open class IllegalPosArgumentException : IllegalArgumentException {
    val position: Int

    constructor(position: Int, message: String?, cause: Throwable?) : super(message, cause) {
        this.position = position
    }
}

class NullPosArgumentException : IllegalPosArgumentException {
    constructor(position: Int, message: String?, cause: Throwable?) : super(position, message, cause)
}


inline fun<R, reified C1, reified C2> call2(args: Array<Any?>, noinline producer: Function2<C1, C2, R>): R =
    callMaker2(C1::class.java, C2::class.java, args, producer)

fun <R, C1, C2> callMaker2(class1: Class<out C1>,
                           class2: Class<out C2>,
                           args: Array<Any?>,
                           producer: Function2<C1, C2, R>): R {

    val a1: C1 = castArgumentTo(class1, args[0], 1)
    val a2: C2 = castArgumentTo(class2, args[1], 2)

    val result: R = producer.invoke(a1, a2)
    return result
}



fun<V> castArgumentTo(clazz: Class<*>, value: Any?, position: Int): V {
    try {
        @Suppress("unchecked_cast")
        return value as V
    }
    catch (iae: IllegalArgumentException) {
        when {
            value == null && clazz.isPrimitive -> throw NullPosArgumentException(position, "Attempted to pass null into a primitive argument (type: ${clazz.simpleName}) at position $position", iae)
            else -> throw IllegalPosArgumentException(position, "Failed to pass value into argument at position $position: ${iae.message}", iae)
        }
    }
}



fun main() {
    val args_11_22 = arrayOf<Any?>(11.toByte(), 22.toShort())
    val r1: BS = callMaker2(Byte::class.java, Short::class.java, args_11_22, ::BS)
    println(r1)

    val r2: NM = callMaker2(Number::class.java, Number::class.java, args_11_22, ::NM)
    println(r2)


    val argsErrNull = arrayOf<Any?>(null, null)
    val r3: NM = callMaker2(Number::class.java, Number::class.java, argsErrNull, ::NM)
    println(r3)

}
