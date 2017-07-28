package _4DP;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;



public class Fibo {
    class F1{

        HashMap<Integer, Integer> cache;
        F1(){
            cache = new HashMap<>();
            cache.put(0,0);
            cache.put(1,1);
        }
        int get(int n) {
            if (cache.containsKey(n)) return cache.get(n);
            if(n == 0 || n == 1) return n;
            cache.put(n, get(n-1) +get(n-2));
            return cache.get(n);
        }
    }

    @Test
    public void test01() throws  FileNotFoundException{
        Scanner in = new Scanner(new File(
                "/Users/tianhuizhu/Downloads/uber/code/JikeDS/top100/src/_4DP/FiboInput"));
        F1 f = new F1();
        int n = in.nextInt();
        while(n != -1){
            System.out.println(f.get(n));
            n = in.nextInt();
        }
        in.close();
    }


}
