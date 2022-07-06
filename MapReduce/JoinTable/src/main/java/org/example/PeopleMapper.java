package org.example;

// Importing libraries
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.example.Record.JoinGenericWritable;
import org.example.Record.PeopleRecord;

public  class PeopleMapper extends MapReduceBase implements Mapper<LongWritable,
        Text, JobKey, JoinGenericWritable> {
    public void map(LongWritable key, Text value, OutputCollector<JobKey,
            JoinGenericWritable> output, Reporter rep) throws IOException
    {
        if (JoinDriver.peopleHead == 1){
            String[] line = value.toString().split(",");
            IntWritable recordType = JobKey.PEOPLE_RECORD;
            IntWritable id = new IntWritable(Integer.parseInt(line[0]));
            Text firstName = new Text(line[1]);
            Text lastName = new Text(line[2]);
            Text job = new Text(line[8]);

            JobKey jobKey = new JobKey(recordType, job);
            PeopleRecord peopleRecord = new PeopleRecord(id, firstName, lastName, job);
            JoinGenericWritable genericRecord = new JoinGenericWritable(peopleRecord);
            output.collect(jobKey, genericRecord);
        }
        else {
            JoinDriver.peopleHead = 1;
        }
    }
}


