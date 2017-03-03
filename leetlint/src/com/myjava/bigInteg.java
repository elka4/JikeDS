package com.myjava;
import java.io.*;
import java.util.*;
import java.math.BigInteger;
/**
 * Created by tzh on 3/2/17.
 */
public class bigInteg {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BigInteger result = BigInteger.valueOf(0);
        for(int i= 0; i < n; i++) {
            String str = sc.next();
            BigInteger bi = new BigInteger(str);
            System.out.println(bi);
            result = result.add(bi);
        }
        System.out.println(result);
    }

}
