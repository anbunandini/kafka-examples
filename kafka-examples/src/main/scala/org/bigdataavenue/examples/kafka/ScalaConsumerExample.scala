package org.bigdataavenue.examples.kafka

import java.util.Properties
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.ConsumerRecord
import scala.collection.JavaConverters._

object ScalaConsumerExample {
  def main(args : Array[String]){
    
    val prop = new Properties()
    
    prop.put("bootstrap.servers", "localhost:9092")
    prop.put("group.id", "test")
    prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    prop.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
    
    val TOPIC = "MyFirstTopic"
    
    val consumer = new KafkaConsumer[String,String](prop)
    
    consumer.subscribe(java.util.Collections.singletonList(TOPIC))
    
    while(true) {
    	val records : ConsumerRecords[String,String] = consumer.poll(100)
      for (record : ConsumerRecord[String,String] <- records.asScala) 
          println(record.offset(), record.key(), record.value())            
    }
  }
}