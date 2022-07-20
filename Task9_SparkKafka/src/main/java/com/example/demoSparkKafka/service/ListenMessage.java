//package com.example.demoSparkKafka.service;
//
//import com.example.demoSparkKafka.DataTrackingClass;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class ListenMessage {
//    @KafkaListener(topics = "data_tracking_lamnv155")
//    public void listen(DataTrackingClass.DataTracking message) throws IOException{
//        System.out.println(message.toString());
//    }
//}
