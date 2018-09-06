package org.bigdataavenue.examples.kafka

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.kafka.KafkaUtils
import java.util.Properties
import org.apache.kafka.clients.consumer.ConsumerConfig
import kafka.serializer.StringDecoder
import kafka.serializer.DefaultDecoder

object SparkStreamingHDFSExample {
  
  def main(args : Array[String]) {
    val sparkConf  = new SparkConf().setAppName("SparkStreamingtoHDFSApp").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
    val streamingContext = new StreamingContext(sc, Seconds(1))
    
    val kafkaParam : Map[String,String] =  Map (
     "bootstrap.servers" ->  "localhost:9092")
//     ConsumerConfig.GROUP_ID_CONFIG -> "test",
//     ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serilization.StringDeserializer",
//     ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serilization.StringDeserializer")
    
    
//    val messages = KafkaUtils.createStream
//     (streamingContext, classOf[String], classOf[String],  kafkaParam, "MyFirstTopic".toSet, )
//   
//    val streamingInputDF = spark.readStream( 
  }
}