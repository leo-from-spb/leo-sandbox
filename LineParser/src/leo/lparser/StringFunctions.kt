package leo.lparser

/**
 * @author Leonid Bushuev from JetBrains
 **/


fun String.offset() : Int? {
    val n = this.length()
    var i = 0
    while (i < n) {
        val c = this[i]
        val k = i
        i++
        if (Character.isSpaceChar(c)) continue
        return k
    }
    return null
}