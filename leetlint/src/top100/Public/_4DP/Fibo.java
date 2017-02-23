package top100.Public._4DP;

import java.io.*;
import java.util.*;

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

public class Fibo {

    public static void main(String[] args) throws  FileNotFoundException{
        Scanner in = new Scanner(new File(
"/Users/tzh/IdeaProjects/JikeDS/leetlint/src/top100/Public/_4DP/FiboInput"));
        F1 f = new F1();
        int n = in.nextInt();
        while(n != -1){
            System.out.println(f.get(n));
            n = in.nextInt();
        }
        in.close();
    }

}
