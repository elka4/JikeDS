package top100.HighFreq._1Linear;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _60ArrayRemoveDuplicate {
	public static void main (String[] args) throws FileNotFoundException{
		Scanner in = new Scanner(new File("input.txt"));
		int n = in.nextInt();
		while (n != -1) {
			int[] nums = new int[n];
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
}
