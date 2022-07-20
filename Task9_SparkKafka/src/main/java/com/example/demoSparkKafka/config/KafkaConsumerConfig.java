//package com.example.demoSparkKafka.config;
//
//import com.example.demoSparkKafka.Serialize.DataTrackingDeserialize;
//import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@Configuration
//public class KafkaConsumerConfig {
//    private static final String bootstrap_server = "172.17.80.26:9092";
//
//    public Map<String, Object> consumerConfig(){
//        HashMap<String, Object> pros = new HashMap<>();
//        pros.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_server);
//        pros.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
//        pros.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        pros.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, DataTrackingDeserialize.class);
//        pros.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        return pros;
//    }
//
//    @Bean
//    public ConsumerFactory<String, String> consumerFactory(){
//        return new DefaultKafkaConsumerFactory<>(this.consumerConfig());
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//}
