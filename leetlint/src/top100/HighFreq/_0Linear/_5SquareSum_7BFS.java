package top100.HighFreq._0Linear;

import java.io.*;
import java.util.*;


class Perfect7{

    int bfs (int n){
        LinkedList <Integer> processing = new LinkedList <Integer>();
        processing.add(n);
        processing.add(- 1);

        HashSet <Integer> used = new HashSet <Integer>();

        int need =  1;

        while(processing.isEmpty()==false) {
            int temp = processing.pollFirst();
            if (temp  <  0 ) {
                processing.addLast(temp -  1);
                need++;
            }else {
                int ceiling = (int) Math.pow(temp,  0.5);
                if (ceiling * ceiling == temp) break;

                for (int i = ceiling; i >  0; --i) {
                    if (used.add(temp - i * i)) processing.addLast(temp - i * i);
                }
            }
        }
        return need;
    }
}

public class _5SquareSum_7BFS {
    public static void main (String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new File("input.txt"));

         //read input process
        int n = in.nextInt();
        Perfect7 Perfect7 = new Perfect7();

        while(n!=- 1){
             //calculate n
            long start = System.nanoTime();

         //   Perfect7.best = n; //这行出错，best没有定义

 //            System.out.print(n +" RESULT: "+Perfect7.search(n, 1) );

            System.out.print(n +" RESULT: "+Perfect7.bfs(n) );
            long end = System.nanoTime();
            System.out.println(" TIME: "+(end-start)/ 1000000+"ms");

            n =in.nextInt();
        }

        in.close();
    }
}