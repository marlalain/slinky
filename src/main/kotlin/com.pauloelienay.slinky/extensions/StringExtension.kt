package kotlin.com.pauloelienay.slinky.extensions

val String.isValid: Boolean
    get() = this.isNotBlank() && this.isNotEmpty()

val String.isInvalid: Boolean
    get() = this.isBlank() || this.isEmpty()

fun String.toList(vararg strings: String): List<String> {
    return listOf(*strings)
}