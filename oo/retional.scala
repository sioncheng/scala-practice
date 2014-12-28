//retional.scala

import scala.language.implicitConversions //magic

class Retional (n:Int, d:Int) {

	require(d != 0)

	val number : Int = n
	val denom : Int = d

	override def toString = number + "/" + denom

	def this(n:Int) = this(n,1) //auxilary constructor

	def + (that : Retional) : Retional = 
		new Retional(
			this.number * that.denom + that.number * this.denom , this.denom * that.denom
		)

	def + (n : Int) : Retional = 
		new Retional(this.number + n * this.denom, this.denom)
}


implicit def intToRetional(x:Int) = new Retional(x) //magic 

println(new Retional(1,2))
println((new Retional(1,2)) + 2)
println(2 + new Retional(3))