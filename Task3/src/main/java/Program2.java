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
    public static void main(String[] args) throws IOException {

        //Serialize
        Employee employee1 = new Employee(1,"Nguyen","Lam",2001, 2.1,"CEO", department.Management);
        Employee employee2 = new Employee(2,"Tran", "Ha",1999,1.7, "Staff",department.Sale);
        DatumWriter<Employee> datumWriter = new SpecificDatumWriter<>(Employee.class);
        DataFileWriter<Employee> fileWriter = new DataFileWriter<>(datumWriter);

        File fileSerialize = new File("src/main/resources/employeeSerialize.avro");
        fileWriter.create(employee1.getSchema(), fileSerialize);

        fileWriter.append(employee1);
        fileWriter.append(employee2);

        fileWriter.close();

        //Deserialize
        DatumReader<Employee> datumReader = new SpecificDatumReader<>(Employee.class);
        DataFileReader<Employee> fileReader = new DataFileReader<>(fileSerialize, datumReader);
        while(fileReader.hasNext()){
            System.out.println(fileReader.next());
        }
        fileReader.close();

    }
}
