package com.example.demokafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

import java.util.HashMap;
import java.util.UUID;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    private static final String bootstrap_sever = "172.17.80.26:9092";
    @Bean
    public HashMap<String, Object> consumerConfig() {
        HashMap<String, Object> pros = new HashMap<>();
        pros.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_sever);
        pros.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
        pros.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        pros.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        pros.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return pros;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> filterKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.setRecordFilterStrategy(
//            new RecordFilterStrategy<String, String>() {
//                @Override
//                public boolean filter(ConsumerRecord<String, String> consumerRecord) {
//                    try {
//                        return Integer.parseInt(consumerRecord.value().split(",")[2]) >= 20
//                                || Integer.parseInt(consumerRecord.value().split(",")[1]) <= 100;
//                    }
//                    catch(Exception ex){
//                        return true;
//                    }
//                }
//            }
//        );
//        return factory;
//    }
}
