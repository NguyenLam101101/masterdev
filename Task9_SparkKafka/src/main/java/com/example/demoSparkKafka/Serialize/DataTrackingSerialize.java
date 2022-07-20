package com.example.demoSparkKafka.Serialize;

import com.example.demoSparkKafka.DataTrackingClass;
import org.apache.kafka.common.serialization.Serializer;
import org.jetbrains.annotations.NotNull;

public class DataTrackingSerialize extends Adapter implements Serializer<DataTrackingClass.DataTracking> {
    @Override
    public byte[] serialize(final String topic, @NotNull final DataTrackingClass.DataTracking data) {
        return data.toByteArray();
    }
}