package leo.lparser

import java.io.File

/**
 * @author Leonid Bushuev from JetBrains
 **/


fun main(args: Array<String>) {
    val baseDir = File("examples")
    val fileName = "File1.ef"

    val parser = LowLevelParser(baseDir)
    val parsedFile = parser.parseFile(fileName)

    System.out.println(parsedFile)
}
