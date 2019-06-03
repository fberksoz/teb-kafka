package com.teb.teb.kafka;

import com.teb.teb.objects.LiveData;
import com.teb.teb.objects.Log;
import com.teb.teb.repositories.ILogRepository;
import com.teb.teb.repositories.Repo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Consumer {

    private KafkaConsumer<String, String> kafkaConsumer;

    private Consumer(String topic, Properties consumerProperties) {
        kafkaConsumer = new KafkaConsumer<>(consumerProperties);
        kafkaConsumer.subscribe(Arrays.asList(topic));
    }


    public static void startConsuming(Repo repo) {
        Thread consumerThread = new Thread(() -> {

            Consumer consumer = new Consumer(
                    KafkaProperties.KAFKA_TOPIC,
                    KafkaProperties.getConsumerProperties()
            );

            consumer.readRecords(repo);
        });

        consumerThread.start();
    }

    private void readRecords(Repo repo) {
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                String message = record.value();
                Log log = new Log(message);
                repo.saveLog(log);
                LiveData.addLog(log);
            }
        }
    }
}
