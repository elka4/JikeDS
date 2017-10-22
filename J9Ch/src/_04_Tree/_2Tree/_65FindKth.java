package _04_Tree._2Tree;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class _65FindKth {
    static class Quicker {
        int select(int[] nums, int k) {

            return select(nums, 0, nums.length - 1, k - 1);
        }

        int select(int[] nums, int start, int end, int k) {
            int divider = nums[end];
            int left = start;
            for(int i = start; i < end ; ++i){
                if(nums[i] < divider) swap (nums, i, left++);
            }
            if(left == k) {
                return nums[end];
            }
            if(left < k) {
                return select(
            nums, left, end - 1,k - (left - start) - 1);//if left==0?
            } else {
                return select(nums, start, left - 1, k);
            }
        }

        void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }


	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File(
"/Users/tianhuizhu/Downloads/uber/code/JikeDS/top100/src/_2Tree/input_65"));
		int n = in.nextInt();
		Quicker quicker = new Quicker();
		while (n != -1) {
			int[] nums = new int[n];
			for(int i = 0; i < n; ++i) nums[i] = in.nextInt();
			int m = in.nextInt();
			while (m-- != 0) {
				System.out.println(quicker.select(nums, in.nextInt()));
			}
			n = in.nextInt();
		}
		in.close();
	}
	@Test
    public void test01(){
        Quicker quicker = new Quicker();
        int[] input = {5,4,6,3,7,2,1};
        System.out.println(quicker.select(input, 4));
    }
}
/*
8
7 5 1 2 3 0 4 6
4
1
3
5
7
-1


8
0 1 2
1 3 4
2 5 -1
3 -1 -1
4 6 7
5 -1 -1
6 -1 -1
7 -1 -1
-1
*/

