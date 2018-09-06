package com.bigdataavenue.examples.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.Producer;


public class ProducerExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		Producer<String,String> producer = new KafkaProducer<String,String>(props);
		
		for (Integer i=0 ; i<100 ; i++) {
			producer.send(new ProducerRecord<String,String>("MyFirstTopic", i.toString(), i.toString()));
			System.out.println(i.toString());
		}
		producer.close();
	}

}
