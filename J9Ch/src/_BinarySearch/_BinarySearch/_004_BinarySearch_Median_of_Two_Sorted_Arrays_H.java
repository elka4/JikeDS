package _BinarySearch._BinarySearch;
import java.util.*;
import org.junit.Test;

//  leetcode    4. Median of Two Sorted Arrays
public class _004_BinarySearch_Median_of_Two_Sorted_Arrays_H {



    ////Approach #1 Recursive Approach [Accepted]
    //  Your runtime beats 25.30 % of java submissions.
    class Solution_leet{
        public double findMedianSortedArrays(int nums1[], int nums2[]) {
            int m = nums1.length;
            int n = nums2.length;

            if (m > n) { // do ensure m <= n
                int[] temp = nums1; nums1 = nums2; nums2 = temp;
                int tmp = m; m = n; n = tmp;
            }

            int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;

            while (iMin <= iMax) {
                int i = (iMin + iMax) / 2;

                int j = halfLen - i;

                if (i < iMax && nums2[j - 1] > nums1[i]) {
                    iMin = iMin + 1; //i is too small
                }
                else if (i > iMin && nums1[i - 1] > nums2[j]){
                    iMax = iMax - 1; //i is too big
                }
                else { // i is perfect
                    int maxLeft = 0;
                    if (i == 0) {maxLeft = nums2[j - 1];}
                    else if (j == 0) {maxLeft = nums1[i - 1];}
                    else {maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);}
                    if((m + n) % 2 == 1) {return maxLeft;}

                    int minRight = 0;
                    if (i == m) {minRight = nums2[j];}
                    else if (j == n) {minRight = nums1[i];}
                    else {minRight = Math.min(nums2[j], nums1[i]);}
                    return (maxLeft + minRight) / 2.0;
                }
            }
            return 0.0;
        }
    }


//Very concise O(log(min(M,N))) iterative solution with detailed explanation
    //  https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/
    class Solution3{
        public double findMedianSortedArrays(int nums1[], int nums2[]) {
            int N1 = nums1.length;
            int N2 = nums2.length;
            // Make sure A2 is the shorter one.
            if (N1 < N2) return findMedianSortedArrays(nums2, nums1);

            int lo = 0, hi = N2 * 2;
            while (lo <= hi) {
                int mid2 = (lo + hi) / 2;   // Try Cut 2
                int mid1 = N1 + N2 - mid2;  // Calculate Cut 1 accordingly
                // Get L1, R1, L2, R2 respectively
                double L1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1-1)/2];
                double L2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2-1)/2];
                double R1 = (mid1 == N1 * 2) ? Integer.MAX_VALUE : nums1[(mid1)/2];
                double R2 = (mid2 == N2 * 2) ? Integer.MAX_VALUE : nums2[(mid2)/2];

                // A1's lower half is too big; need to move C1 left (C2 right)
                if (L1 > R2) lo = mid2 + 1;
                // A2's lower half too big; need to move C2 left.
                else if (L2 > R1) hi = mid2 - 1;
                    // Otherwise, that's the right cut.
                else return (Math.max(L1,L2) + Math.min(R1, R2)) / 2;
            }
            return -1;
        }
    }

/*    Concise JAVA solution based on Binary Search
            Explanation

    The key point of this problem is to ignore half part of A and B each
     step recursively by comparing the median of remaining A and B:

            if (aMid < bMid) Keep [aRight + bLeft]
            else Keep [bRight + aLeft]
            As the following: time=O(log(m + n))
            */

    class Solution4{
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            int l = (m + n + 1) / 2;
            int r = (m + n + 2) / 2;
            return (getkth(nums1, 0, nums2, 0, l) +
                    getkth(nums1, 0, nums2, 0, r)) / 2.0;
        }

        public double getkth(int[] nums1, int aStart, int[] nums2, int bStart, int k) {
            if (aStart > nums1.length - 1) return nums2[bStart + k - 1];
            if (bStart > nums2.length - 1) return nums1[aStart + k - 1];
            if (k == 1) return Math.min(nums1[aStart], nums2[bStart]);

            int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
            if (aStart + k/2 - 1 < nums1.length) aMid = nums1[aStart + k/2 - 1];
            if (bStart + k/2 - 1 < nums2.length) bMid = nums2[bStart + k/2 - 1];
            // Check: aRight + bLeft
            if (aMid < bMid)
                return getkth(nums1, aStart + k/2, nums2, bStart,       k - k/2);
            else    // Check: bRight + aLeft
                return getkth(nums1, aStart,       nums2, bStart + k/2, k - k/2);
        }

    }

    /*
    Share my iterative solution with O(log(min(n, m)))
This is my iterative solution using binary search. The main idea is to find the approximate location of the median and compare the elements around it to get the final result.

do binary search. suppose the shorter list is A with length n. the runtime is O(log(n)) which means no matter how large B array is, it only depends on the size of A. It makes sense because if A has only one element while B has 100 elements, the median must be one of A[0], B[49], and B[50] without check everything else. If A[0] <= B[49], B[49] is the answer; if B[49] < A[0] <= B[50], A[0] is the answer; else, B[50] is the answer.

After binary search, we get the approximate location of median. Now we just need to compare at most 4 elements to find the answer. This step is O(1).

the same solution can be applied to find kth element of 2 sorted arrays.

Here is the code:


     */
    class Solution5{
        public double findMedianSortedArrays(int nums1[], int nums2[]) {
            int n = nums1.length;
            int m = nums2.length;
            // the following call is to make sure len(nums1) <= len(nums2).
            // yes, it calls itself, but at most once, shouldn't be
            // consider a recursive solution
            if (n > m)
                return findMedianSortedArrays(nums2, nums1);

            // now, do binary search
            int k = (n + m - 1) / 2;
            int l = 0, r = Math.min(k, n); // r is n, NOT n-1, this is important!!
            while (l < r) {
                int midnums1 = (l + r) / 2;
                int midnums2 = k - midnums1;
                if (nums1[midnums1] < nums2[midnums2])
                    l = midnums1 + 1;
                else
                    r = midnums1;
            }

            // after binary search, we almost get the median because it must be between
            // these 4 numbers: nums1[l-1], nums1[l], nums2[k-l], and nums2[k-l+1]

            // if (n+m) is odd, the median is the larger one between nums1[l-1] and nums2[k-l].
            // and there are some corner cases we need to take care of.
            int a = Math.max(l > 0 ? nums1[l - 1] :
                    Integer.MIN_VALUE, k - l >= 0 ? nums2[k - l] : Integer.MIN_VALUE);
            if (((n + m) & 1) == 1)
                return (double) a;

            // if (n+m) is even, the median can be calculated by
            //      median = (max(nums1[l-1], nums2[k-l]) + min(nums1[l], nums2[k-l+1]) / 2.0
            // also, there are some corner cases to take care of.
            int b = Math.min(l < n ? nums1[l] :
                    Integer.MAX_VALUE, k - l + 1 < m ? nums2[k - l + 1] : Integer.MAX_VALUE);
            return (a + b) / 2.0;
        }
    }

    //JAVA----------------------Easy Version To Understand!!
    class Solution6{
        public int findKthSmallest(int[] a, int m, int begin1, int[] b, int n, int begin2, int k) {

            if (m > n)
                return findKthSmallest(b, n, begin2, a, m, begin1, k);
            if (m == 0)
                return b[begin2 + k - 1];
            if (k == 1)
                return Integer.min(a[begin1], b[begin2]);
            int partA = Integer.min(k / 2, m), partB = k - partA;
            if (a[begin1 + partA - 1] == b[begin2 + partB - 1])
                return a[begin1 + partA - 1];
            else if (a[begin1 + partA - 1] > b[begin2 + partB - 1])
                return findKthSmallest(a, m, begin1, b, n - partB, begin2 + partB, k - partB);
            else
                return findKthSmallest(a, m - partA, begin1 + partA, b, n, begin2, k - partA);

        }

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int len1 = nums1.length, len2 = nums2.length, sumLen = len1 + len2;
            if (sumLen % 2 != 0) {
                return findKthSmallest(nums1, len1, 0, nums2, len2, 0, sumLen / 2 + 1);
            } else {
                return (findKthSmallest(nums1, len1, 0, nums2, len2, 0, sumLen / 2)
                + findKthSmallest(nums1, len1, 0, nums2, len2, 0, sumLen / 2 + 1)) / 2.0;
            }

        }
    }





//------------------------------------------------------------------------------
    // 9Ch
public class Jiuzhang {
    public double findMedianSortedArrays(int nums1[], int nums2[]) {
        int len = nums1.length + nums2.length;
        if (len % 2 == 1) {
            return findKth(nums1, 0, nums2, 0, len / 2 + 1);
        }
        return (
                findKth(nums1, 0, nums2, 0, len / 2) +
                findKth(nums1, 0, nums2, 0, len / 2 + 1) ) / 2.0;
    }

    // find kth number of two sorted array
    public int findKth(int[] nums1, int nums1_start,
                       int[] nums2, int nums2_start,
                              int k){

        if (nums1_start >= nums1.length) {
            return nums2[nums2_start + k - 1];
        }
        if (nums2_start >= nums2.length) {
            return nums1[nums1_start + k - 1];
        }

        if (k == 1) {
            return Math.min(nums1[nums1_start], nums2[nums2_start]);
        }

        int nums1_key = nums1_start + k / 2 - 1 < nums1.length ?
                nums1[nums1_start + k / 2 - 1] : Integer.MAX_VALUE;

        int nums2_key = nums2_start + k / 2 - 1 < nums2.length ?
                nums2[nums2_start + k / 2 - 1] : Integer.MAX_VALUE;

        if (nums1_key < nums2_key) {
            return findKth(nums1, nums1_start + k / 2, nums2, nums2_start, k - k / 2);
        } else {
            return findKth(nums1, nums1_start, nums2, nums2_start + k / 2, k - k / 2);
        }
    }
}
//------------------------------------------------------------------------------



//------------------------------------------------------------------------------
}
/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0


Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 */