package _01_Array.Two_Sum;

import java.util.Arrays;

public class _10Subarray_Sum_II {
	/**
     * @param A an integer array
     * @param start an integer
     * @param end an integer
     * @return the number of possible answer
     */
    int find(int[] A, int len, int value) {
        if (A[len-1] < value )
            return len;
        
        int l = 0, r = len-1, ans = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (value <= A[mid]) {
                ans = mid;
                r = mid - 1;
            }  else
                l = mid + 1;
        }
        return ans;
    }

    public int subarraySumII(int[] A, int start, int end) {
        // Write your code here
        int len = A.length;
        for (int i = 1; i <len; ++i)
            A[i] += A[i-1];

        Arrays.sort(A);
        int cnt = 0;
        for (int i = 0; i <len; ++i) {
            if (A[i] >= start && A[i] <= end)
                cnt ++;
            int l = A[i] - end;
            int r = A[i] - start;
            cnt += find(A, len, r+1) - find(A, len, l); 
        }
        return cnt;
    }
}

/*Given an integer array, find a subarray where the sum of numbers is in a given interval. Your code should return the number of possible answers. (The element in the array should be positive)

Have you met this question in a real interview? Yes
Example
Given [1,2,3,4] and interval = [1,3], return 4. The possible answers are:

[0, 0]
[0, 1]
[1, 1]
[2, 2]*/