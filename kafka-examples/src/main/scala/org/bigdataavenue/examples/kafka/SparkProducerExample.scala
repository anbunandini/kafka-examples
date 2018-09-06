package org.bigdataavenue.examples.kafka

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import java.util.Properties
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.DataFrameReader
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord

object SparkProducerExample {
  
  def main(args : Array[String]) {
    
    val sparkConf = new SparkConf().setAppName("SparkProducerApp").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
    
    val sqlContext = new SQLContext(sc)
    
    val prop = new Properties() 
    val url  = "jdbc:mysql://quickstart.cloudera:3306/retail_db"
    prop.setProperty("user", "retail_dba")
    prop.setProperty("password", "cloudera")
    prop.setProperty("driver", "com.mysql.jdbc.Driver")
    
    val orderDF = sqlContext.read.jdbc(url, "orders", prop)
    orderDF.show()
    
//    val mappedOrderRDD = orderDF.map(x => (x(0).toString() , x.toString()))
    
//    mappedOrderRDD.take(5).foreach(println)
    val TOPIC = "MyFirstTopic"
    
    val producerProp = new Properties()
    producerProp.put("bootstrap.servers", "localhost:9092")
    producerProp.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
    producerProp.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
//    
//    val producer = new KafkaProducer[String,String](producerProp)
    
    
//    orderDF.map ( x  => producer.send(new ProducerRecord[String,String](TOPIC, x(0).toString(), x(1).toString() )) )
//    orderDF.map ( x  => producer.send(new ProducerRecord(TOPIC, x.toString() )) )

//  The above code gave java.io.NotSerializableException, so trying with foreachPartition.  THE BELOW CODE WORKED!!!
    
    orderDF.foreachPartition { orderRDDPartition => 
      
      val producer = new KafkaProducer[String,String](producerProp)
      orderRDDPartition.foreach(x => producer.send(new ProducerRecord[String,String](TOPIC, x(0).toString(), x.toString())))    
    }
  }    
  
}

