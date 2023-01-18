class OraIntrospector {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val o = OraIntrospector()
            o.foo()
        }
    }

    private var v1 = false

    fun foo() {
        var outerState = "Just to see it in the Watch window"
        println("Start")
        if (v1) {    // v1 is false
            var innerState: Number = 3336L
            println("Point1: $innerState") // to not eliminate the variable
            // any number of any commands here
            println("Point2") // the last command
        }
        val y: Number  // don't move or initialize it here
        if (v1) {
            y = 10L
        }
        else {                                                                                    
            y = 3.1415
        }
        println("Point3: $y")
    }

}