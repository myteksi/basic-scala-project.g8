package $organization$.$name;format="lower"$

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object $name;format="Camel"$ {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("$name$")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    // do something wonderful here!
  }
}
