package lb.sandbox.kotlino.strings

import org.junit.jupiter.api.Test


class StringConcatenationFunTest {

    @Test
    fun test1() {
        val s1: String? = "A"
        val s2: String? = "B"
        val s3: String? = "C"
        val s4: String? = "D"
        val s5: String? = null
        val s6: String? = "F"

        val sx = s1*s2*s3 - s4*s5*s6

        println("sx = $sx")
    }

}