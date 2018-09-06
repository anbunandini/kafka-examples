package org.bigdataavenue.examples.kafka

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object SparkConsumerExample {
  
  def main(args : Array[String]) {
   
    val sparkConf = new SparkConf().setAppName("SparkConsumerSample").setMaster("local[2]")
    
    val sc = new SparkContext(sparkConf)
    
        
    
   
    
  }
}