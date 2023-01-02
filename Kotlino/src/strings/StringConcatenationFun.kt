@file:Suppress("nothing_to_inline", "ConvertToStringTemplate")

package lb.sandbox.kotlino.strings



inline operator fun String?.times(that: String?): String? =
    if (this != null && that != null) this + that
    else null



inline operator fun String?.minus(that: String?): String? =
    when {
        this != null && that != null -> this + ' ' + that
        this != null -> this
        that != null -> that
        else -> null
    }
