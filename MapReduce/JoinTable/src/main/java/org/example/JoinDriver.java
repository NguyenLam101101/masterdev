package org.example;

import java.io.IOException;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapred.lib.MultipleInputs;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.example.Record.JoinGenericWritable;

public class JoinDriver extends Configured implements Tool {
    public static int peopleHead = 0;
    public static int salaryHead = 0;

    public int run(String[] args) throws IOException
    {
        if (args.length < 3)
        {
            System.out.println("Please give valid inputs");
            return -1;
        }

        JobConf conf = new JobConf(JoinDriver.class);
        MultipleInputs.addInputPath(conf, new Path(args[0]), TextInputFormat.class, PeopleMapper.class);
        MultipleInputs.addInputPath(conf, new Path(args[1]), TextInputFormat.class, SalaryMapper.class);

        FileOutputFormat.setOutputPath(conf, new Path(args[2]));

        conf.setReducerClass(JoinReducer.class);

        conf.setMapOutputKeyClass(JobKey.class);
        conf.setMapOutputValueClass(JoinGenericWritable.class);

        conf.setOutputKeyClass(NullWritable.class);
        conf.setOutputValueClass(Text.class);

        conf.setOutputKeyComparatorClass(SortingComparator.class);
        conf.setOutputValueGroupingComparator(GroupingComparator.class);
        JobClient.runJob(conf);
        return 0;
    }

    // Main Method
    public static void main(String[] args) throws Exception
    {
        int exitCode = ToolRunner.run(new JoinDriver(), args);
        System.out.println(exitCode);
    }
}

