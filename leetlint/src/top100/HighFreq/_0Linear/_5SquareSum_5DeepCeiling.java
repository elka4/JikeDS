package top100.HighFreq._0Linear;



import java.io.*;
import java.util.*;

class Perfect5{
    HashMap <Integer, Integer> cache;
    int best;

    Perfect5(){
        cache = new HashMap <Integer,Integer>();  // cache the cacluated n
    }

    int search (int n, int depth){
         // input: n
         // output: the MIN number of Perfect5 numbers summed as n

        if(depth>best) return - 1; // short cut

        if(cache.containsKey(n)) return cache.get(n);

        int result = - 1;

        int ceiling = (int)Math.pow(n, 0.5);

        if(ceiling*ceiling==n){
            result =  1;
            if(depth <best) best = depth;
        }
        else {
            for (int i = ceiling; i >  0; --i) {
                int temp = search(n - i * i, depth+ 1);
                if(temp==- 1) continue;

                if (result == - 1 || temp +  1  < result) result = temp +  1;
                if(temp+depth <best) best = temp+depth;
                if(temp ==  1) break;
            }
        }

        if(result != - 1) cache.put(n, result);
        return result;
    }
}

public class _5SquareSum_5DeepCeiling {
    public static void main (String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new File("input.txt"));

         //read input process
        int n = in.nextInt();
        Perfect5 Perfect5 = new Perfect5();

        while(n!=- 1){
             //calculate n
            long start = System.nanoTime();

            Perfect5.best = n;

            System.out.print(n +" RESULT: "+Perfect5.search(n,  1) );

            long end = System.nanoTime();
            System.out.println(" TIME: "+(end-start)/ 1000000+"ms");

            n =in.nextInt();
        }

        in.close();
    }
}