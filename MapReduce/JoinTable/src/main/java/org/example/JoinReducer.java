package org.example;

// Importing libraries
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.example.Record.JoinGenericWritable;
import org.example.Record.PeopleRecord;
import org.example.Record.SalaryRecord;

public class JoinReducer extends MapReduceBase implements Reducer<JobKey, JoinGenericWritable, NullWritable, Text> {

    public static int node = 0;
    // Reduce function
    public void reduce(JobKey key, Iterator<JoinGenericWritable> value,
                       OutputCollector<NullWritable, Text> output,
                       Reporter rep) throws IOException {
        //ArrayList<String> strRecord = new ArrayList<>();
        if (node == 0){
            output.collect(NullWritable.get(), new Text("id,firstName,lastName,job,salary"));
            node = 1;
        }
        String people = null;
        String salary = null;
        SalaryRecord salaryRecord = (SalaryRecord) value.next().get();
        salary = "," + salaryRecord.salary.toString();
        while(value.hasNext()){
                PeopleRecord peopleRecord = (PeopleRecord) value.next().get();
                people = peopleRecord.id.toString() + "," + peopleRecord.firstName.toString()
                        + "," + peopleRecord.lastName.toString() + "," + peopleRecord.job.toString();
            output.collect(NullWritable.get(), new Text(people+salary));
        }
    }
}
