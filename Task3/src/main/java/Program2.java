import com.example.*;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class Program2 {
    public static void Serialize(Employee[] employee, File fileSerialize) throws IOException{
        DatumWriter<Employee> datumWriter = new SpecificDatumWriter<>(Employee.class);
        DataFileWriter<Employee> fileWriter = new DataFileWriter<>(datumWriter);
        fileWriter.create(employee[0].getSchema(), fileSerialize);
        for(Employee i:employee) {
            fileWriter.append(i);
        }
        fileWriter.close();
    }

    public static void Deserialize(File fileSerialize) throws IOException{
        DatumReader<Employee> datumReader = new SpecificDatumReader<>(Employee.class);
        DataFileReader<Employee> fileReader = new DataFileReader<>(fileSerialize, datumReader);
        while(fileReader.hasNext()){
            System.out.println(fileReader.next());
        }
        fileReader.close();
    }

    public static void main(String[] args) throws IOException {
        Employee[] employee = new Employee[2];
        employee[0] = new Employee(1,"Nguyen","Lam",2001, 2.1,"CEO", department.Management);
        employee[1] = new Employee(2,"Tran", "Ha",1999,1.7, "Staff",department.Sale);
        File fileSerialize = new File("src/main/resources/employeeSerialize.avro");
        Serialize(employee, fileSerialize);
        Deserialize(fileSerialize);
    }
}
