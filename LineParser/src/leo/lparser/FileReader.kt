package leo.lparser

import java.io.*
import java.nio.charset.Charset

/**
 * @author Leonid Bushuev from JetBrains
 **/

public class FileReader
(
        val file: File,
        val charset: Charset = Charsets.UTF_8
)
: Iterator<String?>, Closeable
{
    val br = BufferedReader(InputStreamReader(FileInputStream(file), charset))

    public var curr: String? private set;

    public var lineNr: Int private set;

    public var eof: Boolean private set;


    init {
        $curr = br.readLine()
        $lineNr = 1
        $eof = $curr == null
    }

    private fun doReadNext() {
        $curr = br.readLine()
        $lineNr++
        $eof = $curr == null
    }

    public override fun next(): String? {
        val lineToReturn = curr
        if (lineToReturn != null) {
            doReadNext()
        }
        return lineToReturn
    }

    public override fun hasNext(): Boolean {
        return curr != null
    }

    public override fun close() {
        curr = null
        br.close()
    }
}