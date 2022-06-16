package com.example.demokafka;

import com.example.demokafka.config.KafkaProducerConfig;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImportMessage {
    public ArrayList<String> listId = new ArrayList<String>();
    KafkaProducerConfig kafkaProducerConfig = new KafkaProducerConfig();
    public void sendMessage(String path) throws IOException {
        String[] line;
        String message = "";
        int i;
        FileReader fileReader = new FileReader(path);
        CSVReader csvReader = new CSVReader(fileReader);
        while ((line = csvReader.readNext()) != null) {
            if (! listId.contains(line[0]) ) {
                for (i = 0; i < line.length - 1; i++) {
                    message += line[i] + ',';
                }
                message += line[i];
                kafkaProducerConfig.kafkaTemplate().send("customer", message);
                message = "";
                listId.add(line[0]);
            }
            else {continue;}
        }
    }
}
