//sizer.scala

import scala.io._
import scala.actors._
import Actor._

object PageLoader{
    def getPageSize(url : String) = Source.fromURL(url).mkString.length
}

val urls = List(
    "http://www.scala-lang.org/",
    "http://hestia.typepad.com/flatlander/2009/01/scala-for-c-programmers-part-1-mixins-and-traits.html",
    "http://www.scala-lang.org/download/"
    )

def timeMethod(method : () => Unit) = {
    val start = System.nanoTime
    method()
    val end = System.nanoTime
    println("method took " + ((end - start)/1000000000.0) + " seconds.")
}

def getPageSizeSequentially() = {
    for(url <- urls){
        println("size for " + url + " : " + PageLoader.getPageSize(url))
    }
}

def getPageSizeConcurrently() = {
    val caller = self

    for(url <- urls){
        actor { caller ! (url,PageLoader.getPageSize(url)) }
    }

    for ( i <- 1 to urls.size) {
        receive{
            case (url,size) => {
                println("size for " + url + " : " + size)
            }
        }
    }
}

println("sequential run ...")
timeMethod { getPageSizeSequentially }

println("concurrent run ...")
timeMethod { getPageSizeConcurrently }