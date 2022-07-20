package com.example.demoSparkKafka;


import com.example.demoSparkKafka.Serialize.DataTrackingDeserialize;
import com.example.demoSparkKafka.service.SendMessage;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.DataTypes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.spark.sql.*;

import static org.apache.spark.sql.functions.split;

@SpringBootApplication
public class demoSparkKafkaApplication {
    private static final String bootstrap_sever = "172.17.80.26:9092";

    public static void main(String[] args) throws StreamingQueryException{
        SpringApplication.run(demoSparkKafkaApplication.class, args);
        sparkStreaming();
//        SparkSession spark = SparkSession
//                .builder()
//                .master("local")
//                .appName("demoSparKafka")
//                .getOrCreate();
//        Dataset<Row> dts = spark.read().parquet("user/lamnv155/data_tracking/year=2022/month=7/day=18");
        //dts.withColumn("partition_id", spark_partition_id()).groupBy("partition_id").count().show();

    }

    public static void sparkStreaming() throws StreamingQueryException {
        int round = 1;
        SparkSession spark = SparkSession
                .builder()
                .master("local")
                .appName("demoSparKafka")
                .getOrCreate();
        Dataset<Row> dts = spark
                .readStream()
                .format("kafka")
                .option("startingOffsets","earliest")
                .option("kafka.bootstrap.servers", bootstrap_sever)
                .option("subscribe", "data_tracking_lamnv155")
                .option("failOnDataLoss", "false")
                .load();

        SendMessage sendMessage = new SendMessage();
        sendMessage.send(round);
        round += 1;

        Dataset<Row> dateSplitDts = dts.selectExpr("YEAR(timestamp) as year", "MONTH(timestamp) as month",
                "DAY(timestamp) as day", "HOUR(timestamp) as hour","value");

        spark.udf().register("deserialize", (byte[] value) -> {
            DataTrackingClass.DataTracking record = new DataTrackingDeserialize().deserialize("", value);
            return record.getVersion() + "," + record.getName() + "," + record.getTimestamp() + ","
                    + record.getPhoneId() + "," + record.getLon() + "," + record.getLat();
        }, DataTypes.StringType);

        Dataset<Row> valueDeserializeDts = dateSplitDts.selectExpr("deserialize(value) as value", "year","month","day","hour");
        Dataset<Row> valueSplitDts = valueDeserializeDts.select(
                split(valueDeserializeDts.col("value"), ",").getItem(0).as("version"),
                split(valueDeserializeDts.col("value"), ",").getItem(1).as("name"),
                split(valueDeserializeDts.col("value"), ",").getItem(2).as("timeStamp"),
                split(valueDeserializeDts.col("value"), ",").getItem(3).as("phoneId"),
                split(valueDeserializeDts.col("value"), ",").getItem(4).as("lon"),
                split(valueDeserializeDts.col("value"), ",").getItem(5).as("lat"),
                valueDeserializeDts.col("year"),
                valueDeserializeDts.col("month"),
                valueDeserializeDts.col("day"),
                valueDeserializeDts.col("hour"));

        StreamingQuery query = valueSplitDts.writeStream()
                .outputMode("append")
                .format("parquet")
                .option("partition", 1)
                .partitionBy("year", "month", "day", "hour")
                .option("checkpointLocation", "/tmp/checkpoint/")
                .start("user/lamnv155/data_tracking/");
        query.awaitTermination();
        //query.stop();
    }

}
