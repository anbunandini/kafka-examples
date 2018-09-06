package org.bigdataavenue.examples.kafka

import java.util.Properties
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord

object ScalaProducerExample {
  
  def main(args : Array[String]) {
   
   val prop = new Properties()
   prop.put("bootstrap.servers", "localhost:9092")
   prop.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
   prop.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer")
   
   val producer = new KafkaProducer[String,String](prop)
   
   for (i <- 1 to 100) {
   producer.send(new ProducerRecord[String,String]("MyFirstTopic", i.toString , i.toString))
   println("i = ", i)
   }
   
   producer.close()
  }
  
    
    
 }