//implicit.scala

import scala.language.implicitConversions

implicit def d2i (d:Double) = d.toInt //name such d2i does not matter

var a = 1
println(a)
a = 1.3
println(a)
a = 10.03
println(a)


case class PreferredPrompt(val preference:String)

object Greeter {
    def greet(name:String)(implicit prompt : PreferredPrompt){
        println("Welcome, " + name + ". The system is ready.")
        println(prompt.preference)
    }
}

Greeter.greet("sion")(PreferredPrompt(">"))

implicit val defaultPrompt = PreferredPrompt("$")

Greeter.greet("sion")

//scala has too many magics