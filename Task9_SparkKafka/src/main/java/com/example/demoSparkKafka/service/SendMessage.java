package com.example.demoSparkKafka.service;


import com.example.demoSparkKafka.DataTrackingClass;
import com.example.demoSparkKafka.config.KafkaProducerConfig;

public class SendMessage {

    KafkaProducerConfig kafkaProducerConfig = new KafkaProducerConfig();

    public void send(int num){
        for(int i=num*10; i<num*10+10; i++) {
            DataTrackingClass.DataTracking dataTracking = DataTrackingClass.DataTracking.newBuilder()
                    .setVersion("3.1."+String.valueOf(i))
                    .setName("data"+String.valueOf(i))
                    .setTimestamp(i)
                    .setPhoneId("Phone000"+String.valueOf(i))
                    .setLon(i)
                    .setLat(i)
                    .build();
            kafkaProducerConfig.kafkaTemplate().send("data_tracking_lamnv155", dataTracking);
        }
    }
}
