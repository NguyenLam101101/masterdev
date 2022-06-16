package com.example.demokafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
public class DemoKafkaApplication {
    private static final String writePath = "src/main/resources/customerListener.csv";
    private static final String readPath = "src/main/resources/customer.csv";

    public static void main(String[] args) throws IOException {

        SpringApplication.run(DemoKafkaApplication.class, args);

        //Send message form csv file
        ImportMessage importMessage = new ImportMessage();
        importMessage.sendMessage(readPath);

        //Export message to csv file
        ListenerMessage listenerMessage = new ListenerMessage();
        FileWriter fileWriter = new FileWriter(writePath);
        listenerMessage.ExportToFile(writePath);
        fileWriter.close();

    }
}
