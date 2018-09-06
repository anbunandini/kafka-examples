package org.bigdataavenue.examples.kafka

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.Minutes

object SparkStreamingExample {
  
  def main(args : Array[String]) {
    
    val sparkConf = new SparkConf().setAppName("SparkStreamingExampleAlpp").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
    
    val streamingContext = new StreamingContext(sc, Minutes(1))
    
    val dstreamRDD = streamingContext.socketTextStream("localhost", 9999)
    
    val words = dstreamRDD.flatMap(lines => lines.split(" "))
    
    val wordCount = words.map(x => (x,1)).reduceByKey( (x,y) => x+y)
    
    wordCount.print()
    
    streamingContext.start()
    streamingContext.awaitTermination()   
    
//    issue command $nc -lk 9999 on a terminal 
//    execute the program and then type the words on the terminal
  }
}