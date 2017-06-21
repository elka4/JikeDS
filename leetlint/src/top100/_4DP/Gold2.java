package top100._4DP;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//正面的做法
public class Gold2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(
                "/Users/tzh/IdeaProjects/JikeDS/leetlint/src/top100/Public/_4DP/GoldInput"));
        int n = in.nextInt();
        while(n != -1){
            int best = 0;
            int best0 = 0;
            int best1 = 0;
            int best2 = 0;
            int curr = 0;
            while(n-- != 0){
                curr = in.nextInt();
                best2 = best1 + curr;
                best1 = best0 + curr;
                best0 = best;
                best = Math.max(best, Math.max(best1, best2));
            }
            System.out.println(best);
            n = in.nextInt();
        }
        in.close();
    }

}
