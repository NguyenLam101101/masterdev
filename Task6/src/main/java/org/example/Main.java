package org.example;

import com.mysql.cj.jdbc.JdbcConnection;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class Main {
    static final String driver = "com.mysql.cj.jdbc.Driver";
    static final String db = "jdbc:mysql://172.17.80.26:3306/masterdev_lamnv155";
    static final String user = "lamnv155";
    static final String password = "3Xga5eh4shcNUCKR";

    //    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
//    spring.datasource.url=jdbc:mysql://localhost:3306/master_dev_demo
//    spring.datasource.username=root
//    spring.datasource.password=NguyenLam2001
    public static List<String[]> read(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        CSVReader csvReader = new CSVReader(fileReader);
        List<String[]> lines = csvReader.readAll();
        csvReader.close();
        return lines;
    }

    public static String generateteUser(int num) throws IOException{
        String data_insert = "";
        List<String[]> first_name = read("src/main/resources/first_name.csv");
        List<String[]> last_name = read("src/main/resources/last_name.csv");
        List<String[]> mid_name = read("src/main/resources/mid_name.csv");
        List<String[]> province = read("src/main/resources/province.csv");
        FileWriter userWrite = new FileWriter("src/main/resources/user.txt");
        String pv, fullname, username, fn, ln, mn;
        int age;
        Random rd = new Random();
        for(int i=0;i<num;i++){
            fn = first_name.get(rd.nextInt(first_name.size()))[0];
            mn = mid_name.get(rd.nextInt(mid_name.size()))[0];
            ln = last_name.get(rd.nextInt(last_name.size()))[0];
            username = removeAccent(String.valueOf(fn.charAt(0))+String.valueOf(mn.charAt(0))+String.valueOf(ln.charAt(0)))+String.valueOf(rd.nextInt(1000000));
            fullname = fn + " " + mn + " " + ln;
            pv = province.get(rd.nextInt(province.size()))[0];
            age = rd.nextInt(50)+10;
            data_insert += "(\"%s\", \"%s\", \"%s\", %d),\n".formatted(username, fullname, pv, age);
            //userWrite.write("(\"%s\", \"%s\", \"%s\", %d),".formatted(username, fullname, pv, age));
            //userWrite.write("\n");
        }
        return data_insert.substring(0,data_insert.length()-2)+";";
        //userWrite.close();
    }

    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public static void main(String[] args) throws SQLException, IOException {
        for(int i=0;i<10000;i++){
            Connection connection = DriverManager.getConnection(db, user, password);
            Statement statement = connection.createStatement();
            String query = "insert into user(user_name,full_name,province,age) values" + generateteUser(100);
            try{statement.execute(query);}
            catch(Exception ex){
                i -= 1;
                continue;
            }
            //System.out.println(query);
        }
    }
}

