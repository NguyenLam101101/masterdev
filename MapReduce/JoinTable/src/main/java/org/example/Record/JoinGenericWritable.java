package org.example.Record;

import org.apache.hadoop.io.GenericWritable;
import org.apache.hadoop.io.Writable;

public class JoinGenericWritable extends GenericWritable {
    private static Class<? extends Writable>[] CLASSES = null;

    static {
        CLASSES = (Class<? extends Writable>[]) new Class[]{PeopleRecord.class, SalaryRecord.class};
    }

    public JoinGenericWritable(){}
    public JoinGenericWritable(Writable instance){
        set(instance);
    }

    @Override
    protected  Class<? extends Writable>[] getTypes(){
        return CLASSES;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
