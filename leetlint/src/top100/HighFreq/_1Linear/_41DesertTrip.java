package top100.HighFreq._1Linear;

import java.io.File;
import java.util.Scanner;

public class _41DesertTrip {
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(new File("/Users/tzh/IdeaProjects/JikeDS/leetlint/src/top100/HighFreq/_1Linear/input_41"));
        int n = in.nextInt();
        while(n != -1){
            int tank = 0;
            int begin = 0;
            int total = 0;
            for(int i = 0; i < n; ++i){
                int diff = in.nextInt() - in.nextInt(); //gas - distance
                total += diff;
                if(diff + tank >= 0) tank += diff;
                else {
                    begin = i+ 1;
                    tank = 0;
                }
            }
            System.out.println(total >= 0 && begin < n ? begin : -1);
            n = in.nextInt();
        }
        in.close();
    }
}

/*
第一行是n：
第二行是m:
第三行是n对整数，分别
 */
