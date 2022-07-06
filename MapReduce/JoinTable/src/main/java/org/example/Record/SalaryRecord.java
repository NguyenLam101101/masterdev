package org.example.Record;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class SalaryRecord implements Writable {
    public Text job = new Text();
    public IntWritable salary = new IntWritable();

    public SalaryRecord(){}
    public SalaryRecord(Text job, IntWritable salary){
        this.job = job;
        this.salary = salary;
    }

    @Override
    public void write(DataOutput out) throws IOException{
        this.job.write(out);
        this.salary.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException{
        this.job.readFields(in);
        this.salary.readFields(in);
    }
}
