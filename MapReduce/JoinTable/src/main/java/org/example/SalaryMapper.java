package org.example;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.example.Record.JoinGenericWritable;
import org.example.Record.SalaryRecord;

import java.io.IOException;

public class SalaryMapper extends MapReduceBase implements Mapper<LongWritable, Text, JobKey, JoinGenericWritable> {
    public void map (LongWritable key, Text value, OutputCollector<JobKey,
            JoinGenericWritable> output, Reporter rep) throws IOException {
        if (JoinDriver.salaryHead == 1){
            String[] line = value.toString().split(",");
            Text job = new Text(line[0]);
            IntWritable salary = new IntWritable(Integer.parseInt(line[1]));

            JobKey jobKey = new JobKey(JobKey.SALARY_RECORD, job);
            SalaryRecord salaryRecord = new SalaryRecord(job, salary);
            JoinGenericWritable genericRecord = new JoinGenericWritable(salaryRecord);
            output.collect(jobKey, genericRecord);
        }
        else {
            JoinDriver.salaryHead = 1;
        }
    }
}

