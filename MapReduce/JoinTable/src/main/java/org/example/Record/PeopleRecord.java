package org.example.Record;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PeopleRecord implements Writable {
    public IntWritable id = new IntWritable();
    public Text firstName = new Text();
    public Text lastName = new Text();
    public Text job = new Text();

    public PeopleRecord(){}
    public PeopleRecord(IntWritable id, Text firstName, Text lastName, Text job){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.job = job;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        this.id.write(out);
        this.firstName.write(out);
        this.lastName.write(out);
        this.job.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException{
        this.id.readFields(in);
        this.firstName.readFields(in);
        this.lastName.readFields(in);
        this.job.readFields(in);
    }
}
