package lb.sandbox

import kotlin.reflect.KClass


inline infix fun Any?.isInstanceOf(klass: KClass<out Any>) =
    this != null && klass.isInstance(this)

inline infix fun Any?.isInstanceOf(klass: Class<out Any>) =
    this != null && klass.isAssignableFrom(this.javaClass)


infix fun Class<*>.iz(superClass: Class<*>): Boolean = superClass.isAssignableFrom(this)

infix fun Class<*>.iz(superClass: KClass<*>): Boolean = superClass.java.isAssignableFrom(this)


