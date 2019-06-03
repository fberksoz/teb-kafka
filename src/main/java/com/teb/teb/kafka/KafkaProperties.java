package com.teb.teb.kafka;

import java.util.Properties;

public class KafkaProperties {

    public static final String KAFKA_BOOTSTRAP_SERVER = "kafka:9092";
    public static final String ZOOKEEPER_GROUPID = "teb";
    public static final String KAFKA_TOPIC = "teb";

    public static Properties getProducerProperties() {
        Properties producerProperties = new Properties();
        producerProperties.put("bootstrap.servers", KAFKA_BOOTSTRAP_SERVER);
        producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return producerProperties;
    }

    public static Properties getConsumerProperties() {
        Properties consumerProperties = new Properties();
        consumerProperties.put("bootstrap.servers", KAFKA_BOOTSTRAP_SERVER);
        consumerProperties.put("group.id", ZOOKEEPER_GROUPID);
        consumerProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return consumerProperties;
    }
}
