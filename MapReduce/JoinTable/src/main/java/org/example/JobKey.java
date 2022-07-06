package org.example;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class JobKey implements WritableComparable<JobKey> {
    public IntWritable recordType = new IntWritable();
    public Text job = new Text();
    public static final IntWritable PEOPLE_RECORD = new IntWritable(1);
    public static final IntWritable SALARY_RECORD = new IntWritable(0);

    public JobKey(){}
    public JobKey(IntWritable recordType, Text job){
        this.recordType = recordType;
        this.job = job;
    }

    public boolean equals(JobKey other) {
        return this.job.equals(other.job) && this.recordType.equals(other.recordType);
    }

    @Override
    public int compareTo(JobKey other) {
        if (this.job.equals(other.job )) {
            return this.recordType.compareTo(other.recordType);
        } else {
            return this.job.compareTo(other.job);
        }
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException{
        this.recordType.write(dataOutput);
        this.job.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException{
        this.recordType.readFields(dataInput);
        this.job.readFields(dataInput);
    }
}
