//padding.scala

import scala.io.Source

def widthOfLength( s : String) = s.length.toString().length

def padding(w : Int, maxW : Int) = (" " * (maxW - w) )

if (args.length > 0) {
    val lines = Source.fromFile(args(0)).getLines.toList
    val longestLine = lines.reduceLeft(
        (a,b) => if (a.length > b.length) a else b
    )
    val maxWidth = widthOfLength(longestLine)
    val maxWidthOfLineNum = lines.size.toString().length
    var numLine = 1
    for (line <- lines) {
        val pad1 = padding(widthOfLength(numLine.toString()),maxWidthOfLineNum)
        val pad2 = padding(widthOfLength(line),maxWidth)
        println(pad1 + numLine + " , " + pad2 + line.length + " | " + line)
        numLine += 1
    }
} else {
    Console.err.println("please enter filename")
}