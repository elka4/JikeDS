package _BinarySearch;
import java.util.*;
import org.junit.Test;
public class _378_BinarySearch_Kth_Smallest_Element_in_a_Sorted_Matrix_M {

   /* Share my thoughts and Clean Java Code
    Solution 1 : Heap
    Here is the step of my solution:

    Build a minHeap of elements from the first row.
    Do the following operations k-1 times :
    Every time when you poll out the root(Top Element in Heap), you need to know the row number and column number of that element(so we can create a tuple class here), replace that root with the next element from the same column.
    After you finish this problem, thinks more :

    For this question, you can also build a min Heap from the first column, and do the similar operations as above.(Replace the root with the next element from the same row)
    What is more, this problem is exact the same with Leetcode373 Find K Pairs with Smallest Sums, I use the same code which beats 96.42%, after you solve this problem, you can check with this link:
    https://discuss.leetcode.com/topic/52953/share-my-solution-which-beat-96-42*/
    public class Solution1 {
        public int kthSmallest(int[][] matrix, int k) {
            int n = matrix.length;
            PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
            for(int j = 0; j <= n-1; j++) pq.offer(new Tuple(0, j, matrix[0][j]));
            for(int i = 0; i < k-1; i++) {
                Tuple t = pq.poll();
                if(t.x == n-1) continue;
                pq.offer(new Tuple(t.x+1, t.y, matrix[t.x+1][t.y]));
            }
            return pq.poll().val;
        }
    }

    class Tuple implements Comparable<Tuple> {
        int x, y, val;
        public Tuple (int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo (Tuple that) {
            return this.val - that.val;
        }
    }
/*    Solution 2 : Binary Search
    We are done here, but let's think about this problem in another way:
    The key point for any binary search is to figure out the "Search Space". For me, I think there are two kind of "Search Space" -- index and range(the range from the smallest number to the biggest number). Most usually, when the array is sorted in one direction, we can use index as "search space", when the array is unsorted and we are going to find a specific number, we can use "range".

    Let me give you two examples of these two "search space"

    index -- A bunch of examples -- https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/ ( the array is sorted)
    range -- https://leetcode.com/problems/find-the-duplicate-number/ (Unsorted Array)
    The reason why we did not use index as "search space" for this problem is the matrix is sorted in two directions, we can not find a linear way to map the number and its index.*/

    public class Solution2 {
        public int kthSmallest(int[][] matrix, int k) {
            int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
            while(lo < hi) {
                int mid = lo + (hi - lo) / 2;
                int count = 0,  j = matrix[0].length - 1;
                for(int i = 0; i < matrix.length; i++) {
                    while(j >= 0 && matrix[i][j] > mid) j--;
                    count += (j + 1);
                }
                if(count < k) lo = mid + 1;
                else hi = mid;
            }
            return lo;
        }
    }


/*    Java 1ms nlog(max -min) solution
    Main loop is binary search of max - min.
    Swap from left-bottom to right-top can get count <= mid in O(n) time instead of O(nlogn), total complexity will be O(nlogm) while m = max - min.*/

    public class Solution3 {
        public int kthSmallest(int[][] matrix, int k) {
            int n = matrix.length;
            int lo = matrix[0][0], hi = matrix[n - 1][n - 1];
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                int count = getLessEqual(matrix, mid);
                if (count < k) lo = mid + 1;
                else hi = mid - 1;
            }
            return lo;
        }

        private int getLessEqual(int[][] matrix, int val) {
            int res = 0;
            int n = matrix.length, i = n - 1, j = 0;
            while (i >= 0 && j < n) {
                if (matrix[i][j] > val) i--;
                else {
                    res += i + 1;
                    j++;
                }
            }
            return res;
        }
    }
}
/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note:
You may assume k is always valid, 1 ≤ k ≤ n2.
 */