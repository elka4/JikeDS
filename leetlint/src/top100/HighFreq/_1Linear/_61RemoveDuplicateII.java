package top100.HighFreq._1Linear;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class _61RemoveDuplicateII {

    public static void main (String[] args) throws Exception{
        Scanner in = new Scanner(new File("/Users/tzh/IdeaProjects/JikeDS/leetlint/src/top100/HighFreq/_1Linear/input_61"));
        int n = in.nextInt();
        while (n != -1) {
            int k = in.nextInt();

            int[] nums = new int[n];
            for (int i = 0; i < n; ++i) {
                nums[i] = in.nextInt();
            }
            int last = 0;
            int count = 1;
            for (int curr = 1; curr < n; ++curr) {
                if (nums[curr] != nums[curr - 1]) {
                    nums[++last] = nums[curr];
                    count = 1;
                } else {
                    if(++count > k) continue;
                    else nums[++last] = nums[curr];
                }
            }
            for (int i = 0; i <= last; ++i) { System.out.print(nums[i] + " "); }
            System.out.println();
            n = in.nextInt();
        }
        in.close();
    }
@Test
    public void test01() throws  Exception{
        Scanner in = new Scanner(new File("/Users/tzh/IdeaProjects/JikeDS/leetlint/src/top100/HighFreq/_1Linear/input_61"));
        int n = in.nextInt();
        while (n != -1) {
            int k = in.nextInt();

            int[] nums = new int[n];
            for (int i = 0; i < n; ++i) {
                nums[i] = in.nextInt();
            }
            int last = 0;
            int count = 1;
            for (int curr = 1; curr < n; ++curr) {
                if (nums[curr] != nums[curr - 1]) {
                    nums[++last] = nums[curr];
                    count = 1;
                } else {
                    if(++count > k) continue;
                    else nums[++last] = nums[curr];
                }
            }
            for (int i = 0; i <= last; ++i) { System.out.print(nums[i] + " "); }
            System.out.println();
            n = in.nextInt();
        }
        in.close();
    }
}
