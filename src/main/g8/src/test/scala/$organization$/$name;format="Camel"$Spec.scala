package $organization$.$name;format="lower"$

import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.FlatSpec

class $name;format="Camel"$Spec extends FlatSpec with SharedSparkContext {
  "this is the test desc" should "this is the success condition" in {
    assert(true)
  }
}
