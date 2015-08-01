package leo.lparser

/**
 * @author Leonid Bushuev from JetBrains
 **/


public data class FileRef
(
        val name: String
)


public data class Element
(
        val file:       FileRef,
        val line:       Int,
        val offset:     Int,
        val marker:     String?,
        val identity:   String?,
        val expression: String?,
        val inner:      List<Element>?
)

public data class ParsedFile
(
        val file: FileRef,
        val roots: List<Element>
)
