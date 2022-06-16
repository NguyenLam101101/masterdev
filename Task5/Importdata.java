import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Random;
import java.util.stream.IntStream;

public class Importdata {

    static public int num_student = 10000;
    static public int num_teacher = 1000;
    static public int num_subject = 200;

    public static void generateStudent() throws IOException{
        FileReader fileReader = new FileReader("Complete_List_Names.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        FileWriter fileWriter = new FileWriter("student.txt",false);
        String line;
        int age, count=0;
        Random rd = new Random();
        while((line = bufferedReader.readLine()) != null && count < num_student){
            age = rd.nextInt(5) + 1999;
            String text = "(";
            ;
            String[] a = line.split(",");
            for (String b:a){
                text += "\""+b+"\""+",";
            }
            text += Integer.toString(age)+")"+",";
            fileWriter.write(text);
            fileWriter.write("\n");
            count++;
        }
        bufferedReader.close();
        fileReader.close();
        fileWriter.close();
    }

    public static void generateTeacher() throws IOException{
      String[] subject = new String[num_teacher];
      int i = 0;
      FileReader subjectReader = new FileReader("subject.csv");
      BufferedReader bSubjectReader = new BufferedReader(subjectReader);
      String line;
      while((line=bSubjectReader.readLine()) != null && i<num_teacher){
        subject[i] = line.split(",")[0];
        i++;
      }
      bSubjectReader.close();
      subjectReader.close();

        FileReader fileReader = new FileReader("Complete_List_Names.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        FileWriter fileWriter = new FileWriter("teacher.txt");
        String[] lines = new String[num_student];
        int age;
        i=0;
        Random rd = new Random();
        while((line = bufferedReader.readLine()) != null && i<num_student){
            age = rd.nextInt(20) + 1970;
            String text = "(";
            String[] a = line.split(",");
            for (String b:a){
                text += "\""+b+"\""+",";
            }
            text += Integer.toString(age)+","+"\""+subject[rd.nextInt(199)+1].toString()+"\""+")"+",";
            lines[i] = text;
            i++;   
        }
        int[] index = rd.ints(0, 10000)
        .distinct().limit(num_teacher).toArray();
        for(int j:index){
            fileWriter.write(lines[j]);
            fileWriter.write("\n");
        }
        bufferedReader.close();
        fileReader.close();
        fileWriter.close();
   }

  public static void generateSubject() throws IOException{
        FileReader fileReader = new FileReader("subject.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        FileWriter fileWriter = new FileWriter("subject.txt",false);
        String line;
        int num_credit;
        Random rd = new Random();
        for(int i=0;i<num_subject;i++){
          line = bufferedReader.readLine();
          num_credit = rd.nextInt(2) + 2;
          String text = "(";
          ;
          String[] a = line.split(",");
          for (String b:a){
              text += "\""+b+"\""+",";
          }
          text += Integer.toString(num_credit)+")"+",";
         fileWriter.write(text);
         fileWriter.write("\n");
        }
        bufferedReader.close();
        fileReader.close();
        fileWriter.close();
   }


    public static void generateClass() throws IOException{
      int[] teacher = new int[num_teacher];
      int i, count;
      for(i = 0;i<teacher.length;i++){
        teacher[i] = i+1;
      }
      ArrayList<Integer> teachers = new ArrayList<>();
      Random rd = new Random();
      for(int j:teacher){
        count = rd.nextInt(15)+2;
        for(i=0;i<count;i++)
        teachers.add(j);
      }
      Collections.shuffle(teachers);
      FileWriter fileWriter = new FileWriter("class.txt");
      for (int j:teachers){
        fileWriter.write("("+ Integer.toString(j) +")"+",");
        fileWriter.write("\n");
      }
      fileWriter.close();
    }

    //chạy sql -> file class-subject.csv trước
    public static void generateDetail_Class() throws IOException{
      Hashtable<String, String> map = new Hashtable<>();
      FileReader fileReader = new FileReader("class-subject.csv");
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      FileWriter fileWriter = new FileWriter("detail_class.txt");

      String line;
      String[] lines;
      while((line = bufferedReader.readLine()) != null){
        lines = line.split(",");
        map.put(lines[0], lines[1]);
      }
      

      Random rd = new Random();
      float[] decimal = new float[] {0,(float)0.25,(float)0.5,(float)0.75};
      for(int i=1;i<=num_student;i++){
        int count = rd.nextInt(5)+5;
        int j=0;
        ArrayList<String> subject = new ArrayList<>(); 
        while(j<count){
          int cla = rd.nextInt(map.size()-1)+1;
          if(subject.contains(map.get(Integer.toString(cla))))
            continue;
          else{
            subject.add(map.get(Integer.toString(cla)));
            float mark = rd.nextInt(10)+decimal[rd.nextInt(4)];
            if (mark>10) mark =10;
            fileWriter.write("("+Integer.toString(cla)+","+Integer.toString(i)+","+Float.toString(mark)+"),");
            fileWriter.write("\n");
            j++;
          }
          }
        }
      fileWriter.close();
      bufferedReader.close();
      fileReader.close();
    }
    public static void main(String[] args) throws IOException{
      generateDetail_Class();
    }
}