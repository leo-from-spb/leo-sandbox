package lb.sandbox

fun main(args: Array<String>) {

    var s1: String? = ""
    var s2: String = ""

    if (s1.isNotEmpty()) {
        doSomething()
    }
    
}


fun doSomething() {

}


inline fun CharSequence?.isNotEmpty(): Boolean = this != null && this.length > 0


