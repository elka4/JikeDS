package top100.HighFreq._0Linear;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Simple{
	int search(int n) {
		int result = n;
		for (int i = 1; i*i < n; i++) {
			int temp = search(n - i*i) + 1;
			if (temp < result) {
				result = temp;
			}
		}
		return result;
	}
}

public class _5SquareSum_1DFS {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input.txt"));
		Simple solution = new Simple();
		int n = in.nextInt();
		while (n != -1){
			long startTime = System.nanoTime();
			System.out.println(solution.search(n));
			long endTime = System.nanoTime();
			long duration = (endTime - startTime); 
			System.out.println("Time: " + duration/1000000 + "ms");
			n = in.nextInt();
		}
		in.close();
	}
}
