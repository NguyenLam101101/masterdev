import java.util.Arrays;
import java.util.Hashtable;

public class Task1{
    public static int SumArray(int[] array){  //M1
        return Arrays.stream(array).sum();
    }

    public static char MaxCount(String string){  //M2
        Hashtable<Character, Integer> count = new Hashtable<Character, Integer>();
        for (int i=0;i< string.length();i++){
            try{
                count.replace(string.charAt(i), count.get(string.charAt(i))+1);
            }
            catch(Exception ex){
                count.put(string.charAt(i), 1);
            }
        }
        int max_count = 0, k;
        char max_key = string.charAt(0);
        for (int i=0;i< string.length();i++){
            k = count.get(string.charAt(i));
            if(k > max_count){
                max_count = k;
                max_key = string.charAt(i);
            }
        }
        return max_key;
    }
    
    public static int[] Sort(int[] array){ //M3
        Arrays.sort(array);
        return array;
    }

    public static boolean IsPrime(int a){   //M4
        if(a<=1)
            return false;
        for(int i = 2;i<= Math.sqrt(a);i++){
            if(a % i == 0)
                return false;
        }
        return true;
    }

    public static double Area(int a, int b, int c) throws Exception{  //M5
        if (Math.abs(a-b) < c && c < a+b){
            double p = (a+b+c)/2;
            return Math.sqrt(p*(p-a)*(p-b)*(p-c));
        }
        else{
            throw new Exception("Not valid values");
        }
    }
    public static String repeat(String str, int count){
        String repeat_str = "";
        for(int i=0;i<count;i++){
            repeat_str += str;
        }
        return repeat_str;
    } 

    public static void Draw(int r){    //M6
        int x = (int) r/2;
        System.out.print(repeat(" ",r-x+1));
        System.out.print(repeat("*",2*x+1));
        System.out.println(repeat(" ",r-x));
        int first = r-x;
        int last = r-x+2*x+1;
        for(int i=0;i<r-x/2-1;i++){
            System.out.print(repeat(" ",first));
            System.out.print(repeat("*",2));
            System.out.print(repeat(" ",last - first - 2));
            System.out.println(repeat("*",2));
            first -=1;
            last += 1;
        }
        first += 1;
        last -= 1;
        for(int i=0;i<r-x/2-1;i++){
            System.out.print(repeat(" ",first));
            System.out.print("*");
            System.out.print(repeat(" ",last - first));
            System.out.println("*");
        }
        for(int i=0;i<r-x/2-1;i++){
            System.out.print(repeat(" ",first));
            System.out.print(repeat("*",2));
            System.out.print(repeat(" ",last - first - 2));
            System.out.println(repeat("*",2));
            first +=1;
            last -= 1;
        }
        System.out.print(repeat(" ",r-x+1));
        System.out.print(repeat("*",2*x+1));
        System.out.println(repeat(" ",r-x));
    }

    public static void main(String[] args){
        System.out.println(IsPrime(5));
    }
}


