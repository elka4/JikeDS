package top100.HighFreq._0Linear;


import java.io.*;
import java.util.*;


class Perfect4{
    HashMap <Integer, Integer> cache;

    Perfect4(){
        cache = new HashMap <Integer,Integer>();  // cache the cacluated n
    }

    int search (int n){
         // input: n
         // output: the MIN number of Perfect4 numbers summed as n
        if(cache.containsKey(n)) return cache.get(n);

        int result = n;

        int ceiling = (int)Math.pow(n, 0.5);

        if(ceiling*ceiling==n) result =  1;
        else {
            for (int i = ceiling; i >  0; --i) {
                int temp = search(n - i * i);
                if (temp +  1  < result) result = temp +  1;
                if(temp ==  1) break;
            }
        }

        cache.put(n, result);
        return result;
    }
}

public class _5SquareSum_4Ceiling {
    public static void main (String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new File("input.txt"));

         //read input process
        int n = in.nextInt();
        while(n!=- 1){
             //calculate n
            long start = System.nanoTime();

            Perfect4 Perfect4 = new Perfect4();
            System.out.print(n +" RESULT: "+Perfect4.search(n) );

            long end = System.nanoTime();
            System.out.println(" TIME: "+(end-start)/ 1000000+"ms");

            n =in.nextInt();
        }

        in.close();
    }
}