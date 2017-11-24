package _BinarySearch._BinarySearch;
import java.util.*;
import org.junit.Test;
public class _644_BinarySearch_Maximum_Average_Subarray_II_H {
/*    Java solution O(nlogM) Binary search the answer
(nums[i]+nums[i+1]+...+nums[j])/(j-i+1)>x
=>nums[i]+nums[i+1]+...+nums[j]>x*(j-i+1)
            =>(nums[i]-x)+(nums[i+1]-x)+...+(nums[j]-x)>0*/

    public class Solution1 {
        boolean check(int[] nums,int k,double x) //Check whether we can find a subarray whose average is bigger than x
        {
            int n=nums.length;
            double[] a=new double[n];
            for (int i=0;i<n;i++) a[i]=nums[i]-x; //Transfer to a[i], find whether there is a subarray whose sum is bigger than 0
            double now=0,last=0;
            for (int i=0;i<k;i++) now+=a[i];
            if (now>=0) return true;
            for (int i=k;i<n;i++)
            {
                now+=a[i];
                last+=a[i-k];
                if (last<0)
                {
                    now-=last;
                    last=0;
                }
                if (now>=0) return true;
            }
            return false;
        }
        public double findMaxAverage(int[] nums, int k) {
            double l=Integer.MIN_VALUE,r=Integer.MAX_VALUE;
            while (r-l>0.000004) //Binary search the answer
            {
                double mid=(l+r)/2;
                if (check(nums,k,mid)) l=mid; else r=mid;
            }
            return r;
        }
    }



//-------------------------------------------------------------------------///////////////////
    //jiuzhang
public class Jiuzhang{
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        double l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            l = Math.min(l, (double)nums[i]);
            r = Math.max(r, (double)nums[i]);
        }
        double[] sumNums = new double[n + 1];
        sumNums[0] = 0;
        while (r - l > 1e-6) {
            double mid = (l + r) / 2;
            for (int i = 0; i < n; i++) {
                sumNums[i + 1] = sumNums[i] + nums[i] - mid;
            }
            double preMin = 0;
            double sumMax = Integer.MIN_VALUE;
            for (int i = k; i <= n; i++) {
                sumMax = Math.max(sumMax, sumNums[i] - preMin);
                preMin = Math.min(preMin, sumNums[i - k + 1]);
            }
            if (sumMax >= 0) {
                l = mid;
            }
            else {
                r = mid;
            }
        }
        return l;
    }
}
}
/*
Given an array consisting of n integers, find the contiguous subarray whose length is greater than or equal to k that has the maximum average value. And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation:
when length is 5, maximum average value is 10.8,
when length is 6, maximum average value is 9.16667.
Thus return 12.75.
Note:
1 <= k <= n <= 10,000.
Elements of the given array will be in range [-10,000, 10,000].
The answer with the calculation error less than 10-5 will be accepted.
 */