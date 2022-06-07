//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import org.apache.avro.Schema;
//import org.apache.avro.file.DataFileReader;
//import org.apache.avro.generic.GenericData;
//import org.apache.avro.generic.GenericDatumReader;
//import org.apache.avro.generic.GenericDatumWriter;
//import org.apache.avro.generic.GenericRecord;
//import org.apache.avro.file.DataFileWriter;
//
//public class Program1 {
//    public static GenericRecord CreateEmployee(Schema schema, int id, String firstName, String lastName, int birthYear, double salaryRate, String position, String department){
//        GenericRecord employee = new GenericData.Record(schema);
//        employee.put("id", id);
//        employee.put("firstName", firstName);
//        employee.put("lastName", lastName);
//        employee.put("birthYear", birthYear);
//        employee.put("salaryRate", salaryRate);
//        employee.put("position", position);
//        employee.put("department", department);
//        return employee;
//    }
//
//    public static void Serialize(Schema schema, GenericRecord[] employee, File fw) throws IOException{
//        GenericDatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<> (schema);
//        DataFileWriter<GenericRecord> writer = new DataFileWriter<>(datumWriter);
//        writer.create(schema, fw);
//        for(GenericRecord i:employee){
//            writer.append(i);
//        }
//        writer.close();
//    }
//
//    public static void Deserialize(Schema schema, File fr) throws IOException {
//        GenericDatumReader<GenericRecord> datumReader = new GenericDatumReader<>(schema);
//        DataFileReader<GenericRecord> reader = new DataFileReader<>(fr, datumReader);
//        while(reader.hasNext()){
//            System.out.println(reader.next());
//        }
//        reader.close();
//    }
//
//    public static void main(String[] args) throws IOException {
//        Schema.Parser parser = new Schema.Parser();
//        Schema schema = parser.parse(new File("src/main/resources/Employee.avsc"));
//        GenericRecord[] employee = new GenericRecord[2];
//        employee[0] = CreateEmployee(schema,1,"Nguyen","Lam",2001, 2.1,"CEO", "Management");
//        employee[1] = CreateEmployee(schema,2,"Tran", "Ha",1999,1.7, "Staff","Sale");
//        File fileSerialize = new File("employeeSerialize.avro");
//        Serialize(schema, employee, fileSerialize);
//        Deserialize(schema, fileSerialize);
//    }
//
//}
