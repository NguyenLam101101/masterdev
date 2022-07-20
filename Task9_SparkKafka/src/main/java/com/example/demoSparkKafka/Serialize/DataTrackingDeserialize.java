package com.example.demoSparkKafka.Serialize;

import com.example.demoSparkKafka.DataTrackingClass;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.common.serialization.Deserializer;

public class DataTrackingDeserialize extends Adapter implements Deserializer<DataTrackingClass.DataTracking> {

    @Override
    public DataTrackingClass.DataTracking deserialize(final String topic, byte[] data) {
        try {
            return DataTrackingClass.DataTracking.parseFrom(data);
        }
        catch (final InvalidProtocolBufferException e) {
            throw new RuntimeException("Received unparseable message " + e.getMessage(), e);
        }
    }

}