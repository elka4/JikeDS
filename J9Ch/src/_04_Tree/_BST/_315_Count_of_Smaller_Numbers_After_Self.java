package _04_Tree._BST;
import java.util.*;

//  315. Count of Smaller Numbers After Self
//  https://leetcode.com/problems/count-of-smaller-numbers-after-self/description/
//
public class _315_Count_of_Smaller_Numbers_After_Self {
/*
9ms short Java BST solution get answer when building BST
    Every node will maintain a val sum recording the total of number on it's left bottom side, dup counts the duplication. For example, [3, 2, 2, 6, 1], from back to beginning,we would have:

            1(0, 1)
            \
            6(3, 1)
            /
            2(0, 2)
            \
            3(0, 1)
    When we try to insert a number, the total number of smaller number would be adding dup and sum of the nodes where we turn right.
            for example, if we insert 5, it should be inserted on the way down to the right of 3, the nodes where we turn right is 1(0,1), 2,(0,2), 3(0,1), so the answer should be (0 + 1)+(0 + 2)+ (0 + 1) = 4

            if we insert 7, the right-turning nodes are 1(0,1), 6(3,1), so answer should be (0 + 1) + (3 + 1) = 5
*/

    public class Solution {
        class Node {
            Node left, right;
            int val, sum, dup = 1;
            public Node(int v, int s) {
                val = v;
                sum = s;
            }
        }
        public List<Integer> countSmaller(int[] nums) {
            Integer[] ans = new Integer[nums.length];
            Node root = null;
            for (int i = nums.length - 1; i >= 0; i--) {
                root = insert(nums[i], root, ans, i, 0);
            }
            return Arrays.asList(ans);
        }
        private Node insert(int num, Node node, Integer[] ans, int i, int preSum) {
            if (node == null) {
                node = new Node(num, 0);
                ans[i] = preSum;
            } else if (node.val == num) {
                node.dup++;
                ans[i] = preSum + node.sum;
            } else if (node.val > num) {
                node.sum++;
                node.left = insert(num, node.left, ans, i, preSum);
            } else {
                node.right = insert(num, node.right, ans, i, preSum + node.dup + node.sum);
            }
            return node;
        }
    }



    class Soluition2{
/*        My simple AC Java Binary Search code
        Traverse from the back to the beginning of the array, maintain an sorted array of numbers have been visited. Use findIndex() to find the first element in the sorted array which is larger or equal to target number. For example, [5,2,3,6,1], when we reach 2, we have a sorted array[1,3,6], findIndex() returns 1, which is the index where 2 should be inserted and is also the number smaller than 2. Then we insert 2 into the sorted array to form [1,2,3,6].*/

        public List<Integer> countSmaller(int[] nums) {
            Integer[] ans = new Integer[nums.length];
            List<Integer> sorted = new ArrayList<Integer>();
            for (int i = nums.length - 1; i >= 0; i--) {
                int index = findIndex(sorted, nums[i]);
                ans[i] = index;
                sorted.add(index, nums[i]);
            }
            return Arrays.asList(ans);
        }
        private int findIndex(List<Integer> sorted, int target) {
            if (sorted.size() == 0) return 0;
            int start = 0;
            int end = sorted.size() - 1;
            if (sorted.get(end) < target) return end + 1;
            if (sorted.get(start) >= target) return 0;
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (sorted.get(mid) < target) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            if (sorted.get(start) >= target) return start;
            return end;
        }
//        Due to the O(n) complexity of ArrayList insertion, the total runtime complexity is not very fast, but anyway it got AC for around 53ms.
    }



    class Solution3{
/*        11ms JAVA solution using merge sort with explanation
        The basic idea is to do merge sort to nums[]. To record the result, we need to keep the index of each number in the original array. So instead of sort the number in nums, we sort the indexes of each number.
        Example: nums = [5,2,6,1], indexes = [0,1,2,3]
        After sort: indexes = [3,1,0,2]

        While doing the merge part, say that we are merging left[] and right[], left[] and right[] are already sorted.

        We keep a rightcount to record how many numbers from right[] we have added and keep an array count[] to record the result.

        When we move a number from right[] into the new sorted array, we increase rightcount by 1.

        When we move a number from left[] into the new sorted array, we increase count[ index of the number ] by rightcount.*/

        int[] count;
        public List<Integer> countSmaller(int[] nums) {
            List<Integer> res = new ArrayList<Integer>();

            count = new int[nums.length];
            int[] indexes = new int[nums.length];
            for(int i = 0; i < nums.length; i++){
                indexes[i] = i;
            }
            mergesort(nums, indexes, 0, nums.length - 1);
            for(int i = 0; i < count.length; i++){
                res.add(count[i]);
            }
            return res;
        }
        private void mergesort(int[] nums, int[] indexes, int start, int end){
            if(end <= start){
                return;
            }
            int mid = (start + end) / 2;
            mergesort(nums, indexes, start, mid);
            mergesort(nums, indexes, mid + 1, end);

            merge(nums, indexes, start, end);
        }
        private void merge(int[] nums, int[] indexes, int start, int end){
            int mid = (start + end) / 2;
            int left_index = start;
            int right_index = mid+1;
            int rightcount = 0;
            int[] new_indexes = new int[end - start + 1];

            int sort_index = 0;
            while(left_index <= mid && right_index <= end){
                if(nums[indexes[right_index]] < nums[indexes[left_index]]){
                    new_indexes[sort_index] = indexes[right_index];
                    rightcount++;
                    right_index++;
                }else{
                    new_indexes[sort_index] = indexes[left_index];
                    count[indexes[left_index]] += rightcount;
                    left_index++;
                }
                sort_index++;
            }
            while(left_index <= mid){
                new_indexes[sort_index] = indexes[left_index];
                count[indexes[left_index]] += rightcount;
                left_index++;
                sort_index++;
            }
            while(right_index <= end){
                new_indexes[sort_index++] = indexes[right_index++];
            }
            for(int i = start; i <= end; i++){
                indexes[i] = new_indexes[i - start];
            }
        }
    }
}
/*
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].


 */
/*

 */
