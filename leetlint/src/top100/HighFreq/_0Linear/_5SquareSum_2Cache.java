package top100.HighFreq._0Linear;
import java.io.*;
import java.util.*;

class Perfect{
    HashMap <Integer, Integer>  cache;

    Perfect(){
        cache = new HashMap <Integer,Integer> ();  // cache the cacluated n
    }

    int search (int n){
         // input: n
         // output: the MIN number of perfect numbers summed as n
        if(cache.containsKey(n)) return cache.get(n);

        int result = n;

 //        System.out.println(n);

        for(int i= 1; i*i <=n; ++i){
            int temp = search(n-i*i);
            if(temp+ 1 <result) result = temp+ 1;
        }

        cache.put(n, result);
        return result;
    }
}

public class _5SquareSum_2Cache {
    public static void main (String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new File("input.txt"));

         //read input process
        int n = in.nextInt();
        while(n!=- 1){
             //calculate n
            long start = System.nanoTime();

            Perfect perfect = new Perfect();
            System.out.print(n +" RESULT: "+perfect.search(n) );

            long end = System.nanoTime();
            System.out.println(" TIME: "+(end-start)/ 1000000+"ms");

            n =in.nextInt();
        }

        in.close();
    }
}
