package org.example;

import java.io.IOException;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class DCDriver extends Configured implements Tool {

    public int run(String[] args) throws IOException
    {
        if (args.length < 2)
        {
            System.out.println("Please give valid inputs");
            return -1;
        }

        JobConf conf = new JobConf(DCDriver.class);
        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));
        conf.setMapperClass(DCMapper.class);
        conf.setCombinerClass(DCCombiner.class);
        conf.setReducerClass(DCReducer.class);
        conf.setMapOutputKeyClass(IntWritable.class);
        conf.setMapOutputValueClass(Text.class);
        conf.setOutputKeyClass(IntWritable.class);
        conf.setOutputValueClass(IntWritable.class);
        JobClient.runJob(conf);
        return 0;
    }

    // Main Method
    public static void main(String[] args) throws Exception
    {
        int exitCode = ToolRunner.run(new DCDriver(), args);
        System.out.println(exitCode);
    }
}