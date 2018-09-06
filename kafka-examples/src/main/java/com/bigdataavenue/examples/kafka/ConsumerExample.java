package com.bigdataavenue.examples.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "test");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			
		KafkaConsumer<String,String> consumer = new KafkaConsumer(props);
		
		consumer.subscribe(Arrays.asList("MyFirstTopic"));
		while (true) {
			ConsumerRecords<String,String> records = consumer.poll(20);
			for (ConsumerRecord<String,String> record: records) {

				System.out.printf("offset= %d , key = %s, value = %s%n", record.offset(),record.key(),record.value());
			}
		}
	}

}
