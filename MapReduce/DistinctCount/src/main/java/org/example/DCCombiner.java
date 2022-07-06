package org.example;

// Importing libraries
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class DCCombiner extends MapReduceBase implements Reducer<IntWritable, Text, IntWritable, Text> {

    // Reduce function
    public void reduce(IntWritable key, Iterator<Text> value,
                       OutputCollector<IntWritable, Text> output,
                       Reporter rep) throws IOException
    {
        HashMap<Text, Integer> hashMap = new HashMap<>();
        Text line;
        while (value.hasNext()){
            line = value.next();
            if(! hashMap.containsKey(line)){
                hashMap.put(line, 1);
                output.collect(new IntWritable(0), line);
            }
        }
    }
}
