//unitTest.scala

import org.scalatest._

package unitTest{

    class TestDemoSuite extends FunSuite{
        test("100 / 10 should be 10"){
            val a = 100 / 10
            assert(a == 10)
        }
    }
    
}