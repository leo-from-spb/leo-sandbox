package leo.lparser

import java.io.File
import java.util.*

/**
 * @author Leonid Bushuev from JetBrains
 **/

public class LowLevelParser {

    var baseDirectory = File(".")


    public constructor(baseDirectory: File) {
        this.baseDirectory = baseDirectory
    }


    public fun parseFile(fileName: String): ParsedFile {
        say(fileName)
        val f = File(baseDirectory, fileName)
        if (!f.exists()) throw RuntimeException("File $f doesn't exist")

        val reader = FileReader(f)
        try {
            return parseFileContent(reader, fileName)
        }
        finally {
            reader.close()
        }
    }

    private fun parseFileContent(reader: FileReader, fileName: String): ParsedFile {
        val fileRef = FileRef(fileName)
        val roots = ArrayList<Element>()
        while (!reader.eof) {
            val root = parseElement(reader, fileRef)
            if (root != null) roots add root
        }
        return ParsedFile(fileRef, roots)
    }

    private fun parseElement(reader: FileReader, fileRef: FileRef): Element? {
        val currLine = reader.curr ?: return null
        val currLineNr = reader.lineNr
        val currOffset = currLine.offset() ?: return null

        reader.next()

        // TODO analyze the line

        var innerContent: ArrayList<Element>? = null
        while (!reader.eof) {
            val innerLine = reader.curr ?: break
            val innerOffset = innerLine.offset()
            if (innerOffset == null) { // an empty line
                reader.next()
                continue
            }
            if (innerOffset <= currOffset) break;

            val innerElement = parseElement(reader, fileRef)
            if (innerElement != null) {
                if (innerContent == null) innerContent = ArrayList<Element>()
                innerContent add innerElement
            }
        }

        // construct the element
        return Element(fileRef, currLineNr, currOffset,
                       null, null, currLine.trim(),
                       innerContent)
    }


    fun say(message: String) {
        System.out.println(message)
    }


}

/*
data class AnalyzedLine
(
        offset:        Int,
        markerBegin:   Int,
        markerEnd:     Int,
        identityBegin: Int,
        identityEnd:   Int,
        essenceBegin:  Int,
        essenceEnd:    Int
)

fun analyzeLine(line: String) {

}
*/


