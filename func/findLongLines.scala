//findLongLines.scala

import scala.io.Source
import scala.collection.mutable._

object LongLines {
	def processFile(filename : String, width : Int) : List[String] = {
		for{
			line <- Source.fromFile(filename).getLines.toList
			if line.length > width
		} yield line + "\n"
	}
}

object LongLinesWithLineNum {
	def processFile(filename : String, width : Int) : List[String] = {

		def generateResultOfLine(line : String, ln : Int) = ln + " | " + line + "\n" //local functions

		val lines  = MutableList[String]()
		var ln = 0
		val source = Source.fromFile(filename)
      	for (line <- source.getLines){
      		ln += 1
        	lines += generateResultOfLine(line,ln) //magic, is it good ? lines.+=(generateResultOfLine(line,ln))
        }
		return lines.toList
	}
}

object FindLongLines {
	def main(args : Array[String]) {
		println(LongLines.processFile(args(1),args(0).toInt))
		println("\n\n")
		println(LongLinesWithLineNum.processFile(args(1),args(0).toInt))
	}
}

//scalac findLongLines.scala
//scala FindLongLines 10 ./findLongLines.scala