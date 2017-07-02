package _1Linear;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _60ArrayRemoveDuplicate {
	public static void main (String[] args) throws FileNotFoundException{
		Scanner in = new Scanner(new File
	("/Users/tzh/IdeaProjects/JikeDS/leetlint/src/top100/HighFreq/_1Linear/input_60"));
		int n = in.nextInt();
		while (n != -1) {
			int[] nums = new int[n]; //第一个数值给的是array长度
			for (int i = 0; i < n; ++i) {
				nums[i] = in.nextInt();
			}
			int pre = 0;
			for (int i = 0; i < n; ++i) {
				if (nums[i] != nums[pre]) {
					nums[++pre] = nums[i];
				}
			}
			for (int i = 0; i <= pre; ++i) {
				System.out.print(nums[i] + " ");
			}
			System.out.println();
			n = in.nextInt();
		}
		in.close();
	}

	@Test
	public void test01() throws FileNotFoundException{
		Scanner in = new Scanner(new File
("/Users/tzh/IdeaProjects/JikeDS/leetlint/src/top100/HighFreq/_1Linear/input_60"));
		int n = in.nextInt();
		while (n != -1) {
			//remove the duplicate
			int[] nums = new int[n]; //第一个数值给的是array长度
			for (int i = 0; i < n; ++i) { nums[i] = in.nextInt(); } //读取数据到数组nums
			int last = 0;
			int curr = 0;
			for (curr=1; curr<n; ++curr) {
				if(nums[curr] != nums[curr-1]){
					nums[++last] = nums[curr];
				}
			}
			//output the result
			for (int i = 0; i <= last; ++i) {
				System.out.print(nums[i] + " ");
			}
			System.out.println();
			n = in.nextInt();
		}
		in.close();
	}
}
//想一下如果允许重复两次怎么做