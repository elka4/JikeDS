package _1Linear;


import java.io.File;
import java.util.Scanner;

public class _42Desert_II {
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(new File("/Users/tzh/IdeaProjects/JikeDS/leetlint/src/top100/HighFreq/_1Linear/input_42"));
        int n = in.nextInt();
        while(n != -1){
            int m = in.nextInt();

            int[] gas = new int[n];
            int[] dis = new int[n];
            for(int i = 0; i < n; ++i) {
                gas[i] = in.nextInt();
                dis[i] = in.nextInt();
            }

            int trip = 0;
            int tank = 0;
            int begin = 0;

            for (int i = 0; i < 2*n; ++i) {
                int curr = i%n;
                tank += gas[curr];
                if(tank>m) tank = m;
                tank -= dis[curr];
                if(tank < 0){
                    begin = curr + 1;
                    tank = 0;
                    trip = 0;
                } else {
                   if(++trip == n) break;
                }
            }
            System.out.println(trip == n ?begin:-1);
            n = in.nextInt();
        }
        in.close();
    }

}
