package com.example.demokafka;

import com.example.demokafka.config.KafkaConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;


@Component
public class ListenerMessage{

//    @KafkaListener(topics = "customer"
//            ,containerFactory = "filterKafkaListenerContainerFactory")
//    public void listen(String message) throws IOException {
//        System.out.println(message);
//    }
    public ConsumerRecords<String, String> listen(){
        KafkaConsumerConfig kafkaConsumerConfig = new KafkaConsumerConfig();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(kafkaConsumerConfig.consumerConfig());
        consumer.subscribe(Collections.singleton("customer"));
        ConsumerRecords<String, String> records = consumer.poll(1000);
        return records;
    }

    public void ExportToFile(String path) throws IOException{
        ConsumerRecords<String, String> records = this.listen();
        FileWriter fileWriter = new FileWriter(path, true);
        for (ConsumerRecord<String, String> record : records) {
            try {
                if (Integer.parseInt(record.value().split(",")[2]) < 20
                        && Integer.parseInt(record.value().split(",")[1]) > 100)
                    fileWriter.write(record.value() + "\n");
            }
            catch (Exception ex){
                continue;
            }
        }
        fileWriter.close();
    }
}
