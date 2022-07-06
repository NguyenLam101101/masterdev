package org.example;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SortingComparator extends WritableComparator {
    public SortingComparator()
    {
        super (JobKey.class, true);
    }

    @Override
    public int compare (WritableComparable a, WritableComparable b){
        JobKey first = (JobKey) a;
        JobKey second = (JobKey) b;
        return first.compareTo(second);
    }
}