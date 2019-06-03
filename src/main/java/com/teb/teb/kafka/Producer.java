package com.teb.teb.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Producer {

    private static KafkaProducer<String, String> producer = new KafkaProducer<>(KafkaProperties.getProducerProperties());

    public static void sendMessage(String data) {
        producer.send(new ProducerRecord<>(KafkaProperties.KAFKA_TOPIC, data));
//        producer.close();
    }
}
