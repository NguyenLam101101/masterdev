package org.example;

import org.apache.spark.sql.*;

import static org.apache.spark.sql.functions.*;

public class Main {
    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .appName("demoSpark")
                .getOrCreate();
        Dataset<Row> rawDf = spark.read().parquet("/user/lamnv155/Sample_data/");
        Dataset<Row> clearDf = rawDf.filter(rawDf.col("device_model").isNotNull()).filter(rawDf.col("user_id").isNotNull()).filter(rawDf.col("user_id").notEqual(""));

        Dataset<Row> device_model_user = clearDf.select(clearDf.col("device_model"), clearDf.col("user_id")).distinct();
        Dataset<Row> device_model_num_user = device_model_user.groupBy("device_model").count();
        Dataset<Row> device_model_list_user = device_model_user.groupBy("device_model").agg(collect_list("user_id").as("list_user_id"));
        device_model_num_user.repartition(1).write().format("parquet").save("/user/lamnv155/device_model_num_user");
        device_model_list_user.repartition(1).write().format("orc").save("/user/lamnv155/device_model_list_user");

        Dataset<Row> concat_device_model_user_id = clearDf.filter(clearDf.col("button_id").isNotNull()).select(concat(clearDf.col("user_id"),lit("_"),clearDf.col("device_model")).as("user_id_device_model"), clearDf.col("button_id"));
        Dataset<Row> action_by_button_id = concat_device_model_user_id.groupBy("button_id", "user_id_device_model").count();
        action_by_button_id.repartition(1).write().format("parquet").save("/user/lamnv155/action_by_button_id");
    }
}