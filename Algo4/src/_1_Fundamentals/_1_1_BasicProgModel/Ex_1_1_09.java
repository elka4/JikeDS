package _1_Fundamentals._1_1_BasicProgModel;

import StdLib.StdOut;

public class Ex_1_1_09 {

    public static void main(String[] args)
    {
        int N = 234245;
        
        StdOut.println(Integer.toBinaryString(N));
        
        String s = "";
        for (int n = N; n > 0; n /= 2)
            s = (n % 2) + s;
        StdOut.println(s);
    }
    
}
