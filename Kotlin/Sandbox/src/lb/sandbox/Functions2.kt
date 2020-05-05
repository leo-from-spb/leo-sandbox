package lb.sandbox

import kotlin.jvm.internal.CallableReference
import kotlin.reflect.KClass
import kotlin.reflect.KParameter


fun <R, C1, C2> produce2(args: Array<Any?>, producer: Function2<C1, C2, R>): R {
    val ps: List<KParameter> = (producer as CallableReference).parameters
    val a1: C1 = castArgument(args[0], ps[0], producer)
    val a2: C2 = castArgument(args[1], ps[1], producer)
    val result: R = producer.invoke(a1, a2)
    return result
}

fun <R, C1, C2, C3> produce3(args: Array<Any?>, producer: Function3<C1, C2, C3, R>): R {
    val ps: List<KParameter> = (producer as CallableReference).parameters
    val a1: C1 = castArgument(args[0], ps[0], producer)
    val a2: C2 = castArgument(args[1], ps[1], producer)
    val a3: C3 = castArgument(args[2], ps[2], producer)
    val result: R = producer.invoke(a1, a2, a3)
    return result
}




@Throws(IllegalArgumentException::class)
fun<V> castArgument(value: Any?, parameter: KParameter, producer: Function<*>): V {
    if (value != null) {
        var expClass = (parameter.type.classifier as? KClass<*>)?.java
        if (expClass != null) {
            if (expClass.isPrimitive) expClass = Primitives.wrappers[expClass] ?: expClass
            val valClass = value.javaClass
            if (!expClass.isAssignableFrom(valClass)) {
                val pos = parameter.index + 1
                val name = parameter.name
                val fn = producer.readableName()
                throw IllegalArgumentException("Cannot invoke $fn: argument $name at position $pos should be ${expClass.simpleName} but it's ${valClass.simpleName})")
            }
        }
        try {
            @Suppress("unchecked_cast")
            return value as V
        }
        catch (e: Exception) {
            val pos = parameter.index + 1
            val name = parameter.name
            val fn = producer.readableName()
            throw IllegalArgumentException("Cannot invoke $fn: cannot cast argument $name at position $pos: ${e.message}", e)
        }
    }
    else {
        if (parameter.type.isMarkedNullable) {
            @Suppress("unchecked_cast")
            return null as V
        }
        else {
            val pos = parameter.index + 1
            val name = parameter.name
            val fn = producer.readableName()
            throw IllegalArgumentException("Cannot invoke $fn: argument $name at position $pos is declared as not null but called with null value")
        }
    }
}


private fun Function<*>.readableName(): String {
    val r = (this as? CallableReference) ?: return this.toString()
    var name = r.name
    val owner = r.owner
    if (owner != null) {
        val ownerName = owner.javaClass.simpleName
        if (ownerName != null) name = "$ownerName.$name"
    }
    return name
}


fun main() {
    val args_11_22 = arrayOf<Any?>(11.toByte(), 22.toShort())
    val r1: BS = produce2(args_11_22, ::BS)
    println(r1)

    val r2: NM = produce2(args_11_22, ::NM)
    println(r2)

    val argsErrNull = arrayOf<Any?>(null, 9999)
    val r3: NM = produce2(argsErrNull, ::NM)
    println(r3)
}

