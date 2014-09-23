/* SimpleApp.scala */

import java.io.StringReader
import au.com.bytecode.opencsv.CSVReader

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.serializer.KryoSerializer
import org.elasticsearch.spark._

object SimpleApp {
  def main(args: Array[String]) {

    // change the logfile path
    val logFile = "/Users/TQui/Tools/spark-1.0.2-bin-hadoop2/README.md" // Should be some file on your system
    val conf = new SparkConf().setAppName("Simple Application")
    conf.set("es.index.auto.create", "true")


    val sc = new SparkContext(conf)
    sc.hadoopConfiguration.set("es.resource", "csvtest/entry")
    sc.hadoopConfiguration.set("spark.serializer", classOf[KryoSerializer].getName)

    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))

    // change the csv file path
    val inputFile = "/Users/TQui/Projects/log-synth/customer1M_headless.csv"
    val input = sc.textFile(inputFile)
    val result = input.map{ line =>
      val reader = new CSVReader(new StringReader(line));
      reader.readNext();
    }
    println("count " + result.count())
    //result.take(10).foreach(csvArray => println(csvArray.mkString("|")))
    val result_map = result.map{ line => convertToMap(line)}
    val numbers = Map("one" -> 1, "two" -> 2, "three" -> 3)
    val airports = Map("OTP" -> "Otopeni", "SFO" -> "San Fran")
    result_map.saveToEs("spark/docs")
  }

  def convertToMap(input: Array[String]) : scala.collection.mutable.Map[String,String]={
    var dataMap : scala.collection.mutable.Map[String,String] = scala.collection.mutable.Map()
    for(i <- 0 until input.length){
      dataMap.put(i.toString, input(i))
    }

    return dataMap
  }


}
