//kids.scala

import scala.actors._
import scala.actors.Actor._

case object Poke 
case object Feed

class Kid() extends Actor {
    def act(){
        loop{
            react{
                case Poke => {
                    println("Poke")
                }
                case Feed => {
                    println("Feed")
                }
            }
        }
    }
}

val bart = (new Kid()).start
var lisa = (new Kid()).start

println("Poke or Feed...")

bart ! Poke
lisa ! Feed
lisa ! Poke
bart ! Feed

//how to stop?
//bart.stop
//lisa.stop

