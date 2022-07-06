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

public class WCMapper extends MapReduceBase implements Mapper<LongWritable,
        Text, IntWritable, Text> {
    public void map(LongWritable key, Text value, OutputCollector<IntWritable,
            Text> output, Reporter rep) throws IOException
    {
        output.collect(new IntWritable(0), value);
    }
}
