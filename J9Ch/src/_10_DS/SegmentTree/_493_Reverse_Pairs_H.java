package _10_DS.SegmentTree;
import _10_DS.SegmentTree.Count_Sum_Max.SegmentTreeNode;

import java.util.*;

//  493. Reverse Pairs
//  https://leetcode.com/problems/reverse-pairs/description/
//  Divide and Conquer Binary Indexed Tree Segment Tree Binary Search Tree
public class _493_Reverse_Pairs_H {

    //https://leetcode.com/problems/reverse-pairs/solution/

    //http://massivealgorithms.blogspot.com/2017/02/leetcode-493-reverse-pairs.html

//-------------------------------------------------------------------------------
    //1
    //Clean Java Solution using Enhanced Binary Search Tree


    /*
This is literally the same problem with 315. Count of Smaller Numbers After Self.
The only difference is to find count of numbers smaller than half of the current number after itself.
To efficiently search for count of numbers smaller than a target, we can use a Binary Search Tree. There is a little change of the TreeNode to include count of numbers smaller or equal to it. This will make the query even faster because we don't need to traverse all its left sub-tree to get the count.

Overall Algorithm:

Scan the numbers from right to left.
First search the tree to get count of numbers smaller than nums[i] / 2.0, sum to the final result.
Insert nums[i] to the tree.
Insert logic:

Recursively try to find a place to insert this number. When root is null, its time to create a new node. If meet the same number, just increase the count.
When try to insert the number to left sub-tree, increase count of current node.
Query logic:

If target value is greater than the current value, meaning current node and all left sub-tree are smaller than target, return count (remember it stands for count of numbers smaller or equal to current number) of current node plus any possible smaller number than target in right sub-tree.
Otherwise, only search left sub-tree.
 */
    public class Solution1 {
        class Node {
            int value, count;
            Node left, right;
            Node (int v) {
                value = v; count = 1;
            }
        }

        public int reversePairs(int[] nums) {
            int result = 0;
            if (nums == null || nums.length <= 1) return result;

            int len = nums.length;
            Node root = new Node(nums[len - 1]);

            for(int i = len - 2; i >= 0; i--) {
                result += query(root, nums[i] / 2.0);
                insert(root, nums[i]);
            }

            return result;
        }

        private Node insert(Node root, int value) {
            if (root == null) return new Node(value);

            if (root.value == value) {
                root.count++;
            }
            else if (root.value > value) {
                root.count++;
                root.left = insert(root.left, value);
            }
            else {
                root.right = insert(root.right, value);
            }

            return root;
        }

        private int query(Node root, double value) {
            if (root == null) return 0;

            if (value > root.value) {
                return root.count + query(root.right, value);
            }
            else {
                return query(root.left, value);
            }
        }
    }
//-------------------------------------------------------------------------------
//2
    //Your code can't pass test now ! I changed it by balancing the tree. but neither do it.

    /**

     Created by songbo.han on 2017/2/23.
     */
    public class Solution2{
        public class BNode{
            public BNode left, right;
            public int data, height, num;
            public BNode(int data) {
                this.data = data;
            }
        }

        private int height(BNode t){
            return null == t ? -1 : t.height;
        }

        private BNode balance(BNode t){
            if(height(t.left) - height(t.right) > 1){
                if(height(t.left.left) > height(t.left.right))
                    t = rotateR(t);
                else{
                    t.left = rotateL(t.left);
                    t = rotateR(t);
                }
            }
            else if(height(t.right) - height(t.left) > 1){
                if(height(t.right.right) > height(t.right.left))
                    t = rotateL(t);
                else{
                    t.right = rotateR(t.right);
                    t = rotateL(t);
                }
            }
            t.height = Math.max(height(t.left), height(t.right)) + 1;
            return t;

        }

        private BNode rotateR(BNode k2){
            //System.out.println("绕" + k2.data + "右旋");
            BNode k1 = k2.left;
            k2.left = k1.right;
            k1.right = k2;
            k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
            k1.height = Math.max(height(k1.left), k2.height) + 1;
            return k1;
        }

        private BNode rotateL(BNode k2){
            //System.out.println("绕" + k2.data + "左旋");
            BNode k1 = k2.right;
            k2.right = k1.left;
            k1.left = k2;
            k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
            k1.height = Math.max(height(k1.right), k2.height) + 1;
            return k1;
        }

        public BNode insert(BNode t, int target){
            if(null == t)
                return new BNode(target);
            else{
                if(t.data > target)
                    t.left = insert(t.left, target);
                else
                    t.right = insert(t.right, target);

                return balance(t);
            }
        }

        public int find(BNode t, double target){
            if(null == t)
                return 0;
            if(t.data >= target)
                return find(t.left, target);
            else
                return find(t.left, target) + find(t.right, target) + 1;
        }

        public int reversePairs(int[] nums) {
            int result = 0;
            if (nums == null || nums.length <= 1) return result;

            int len = nums.length;
            BNode root = new BNode(nums[len - 1]);
            for (int i = len - 2; i >= 0; i--) {
                result += find(root, nums[i] / 2.0);
                root = insert(root, nums[i]);
            }

            return result;

        }
    }
//-------------------------------------------------------------------------------
//3
//Java Segment Tree Solution
//Inspired by binary index tree solution, I just wrote a segment tree version.

    public class Solution3 {
        int n;
        int[] nums;
        int[] numsSorted;
        int[] segmentTree;

        public int reversePairs(int[] nums) {
            n = nums.length;
            nums = nums;
            numsSorted = Arrays.copyOf(nums, n);
            Arrays.sort(numsSorted);
            segmentTree = new int[n * 2];

            int res = 0;
            for (int val : nums) {
                res += search(findIndex(2L * val + 1));
                update(findIndex(val));
            }
            return res;
        }

        private int findIndex(long val) {
            int l = 0, r = n - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (numsSorted[mid] >= val) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }

        private int search(int pos) {
            int l = pos + n;
            int r = n * 2 - 1;
            int sum = 0;
            while (l <= r) {
                if (l % 2 == 1) {
                    sum += segmentTree[l];
                    l++;
                }
                if (r % 2 == 0) {
                    sum += segmentTree[r];
                    r--;
                }
                l /= 2;
                r /= 2;
            }
            return sum;
        }

        private void update(int pos) {
            pos += n;
            segmentTree[pos] += 1;
            while (pos > 0) {
                int l = pos;
                int r = pos;
                if (pos % 2 == 0) {
                    r = pos + 1;
                } else {
                    l = pos - 1;
                }
                segmentTree[pos / 2] = segmentTree[l] + segmentTree[r];
                pos /= 2;
            }
        }
    }
//-------------------------------------------------------------------------------

//4

    class Solution4{
        /*  public int reversePairs(int[] nums) {
/*            int result = 0;
              if (nums == null || nums.length <= 1) return result;

              int len = nums.length;
              Node root = new Node(nums[len - 1]);

              for(int i = len - 2; i >= 0; i--) {
                  result += query(root, nums[i] / 2.0);
                  insert(root, nums[i]);
              }

              return result;
          }*/
        //--------------------------------------------------------------------
        class SegmentTreeNode {
            public int start, end;
            public SegmentTreeNode left, right;

            //int value;
            int count;
            int sum;
            int max;
            int min;

            public SegmentTreeNode(int start, int end) {
                this.start = start;
                this.end = end;
                this.left = this.right = null;
            }
        }
        //--------------------------------------------------------------------
        class SegmentTree {

    ///------------------------------------------------------------------
    SegmentTreeNode root;
    SegmentTree(){

    }

    SegmentTree(int start, int end){
        root = build(start, end);
    }

    SegmentTree(int[] arr){
        int n = arr.length;
        root = build(1, n);
        for (int i = 1; i <= n; i++) {
            modify(i, arr[i-1]);
        }
    }

    public SegmentTreeNode build(int start, int end) {
        if(start > end) {
            return null;
        }

        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start == end){
            root.count = 1;
            return  root;
        }

        int mid = (start + end) / 2;
        root.left = build(start, mid);
        root.right = build(mid+1, end);

        root.count = root.left.count + root.right.count;
        root.sum = root.left.sum + root.right.sum;
        root.max = Math.max(root.left.max, root.right.max);
        root.min = Math.min(root.left.min, root.right.min);

        return root;
    }
//-----------------------------------------------------------------------------------------

    public void modify(int index, int value){
        modify(root, index, value);
    }

    private void modify(SegmentTreeNode root, int index, int value) {
        if(root.start == index && root.end == index) { // 查找到
            root.sum = value;
            root.max = value;
            root.min = value;
            return;
        }

        // 查询
        int mid = (root.start + root.end) / 2;
        if(root.start <= index && index <=mid) {
            modify(root.left, index, value);
        }

        if(mid < index && index <= root.end) {
            modify(root.right, index, value);
        }
        //更新
        root.count = root.left.count + root.right.count;
        root.sum = root.left.sum + root.right.sum;
        root.max = Math.max(root.left.max, root.right.max);
        root.min = Math.min(root.left.min, root.right.min);
    }
//-----------------------------------------------------------------------------------------

    public int query_count(int start, int end){
        return query_count(root, start, end);
    }

    public int query_count(SegmentTreeNode root, int start, int end) {
        // write your code here
        if(start > end || root==null)
            return 0;
        if(start <= root.start && root.end <= end) { // 相等
            return root.count;
        }

        int mid = (root.start + root.end)/2;
        int leftCount = 0, rightCount = 0;
        // 左子区
        if(start <= mid) {
            if( mid < end) { // 分裂
                leftCount =  query_count(root.left, start, mid);
            } else { // 包含
                leftCount = query_count(root.left, start, end);
            }
        }
        // 右子区
        if(mid < end) { // 分裂 3
            if(start <= mid) {
                rightCount = query_count(root.right, mid+1, end);
            } else { //  包含
                rightCount = query_count(root.right, start, end);
            }
        }
        // else 就是不相交
        return leftCount + rightCount;
    }
//-----------------------------------------------------------------------------------------
    public int query_max(int start, int end){
        return query_max(root, start, end);
    }

    public int query_max(SegmentTreeNode root, int start, int end) {
        // write your code here
        if(start == root.start && root.end == end) { // 相等
            return root.max;
        }


        int mid = (root.start + root.end)/2;
        int leftmax = Integer.MIN_VALUE, rightmax = Integer.MIN_VALUE;
        // 左子区
        if(start <= mid) {
            if( mid < end) { // 分裂
                leftmax =  query_max(root.left, start, mid);
            } else { // 包含
                leftmax = query_max(root.left, start, end);
            }
            // leftmax = query(root.left, start, Math.min(mid,end));
        }
        // 右子区
        if(mid < end) { // 分裂 3
            if(start <= mid) {
                rightmax = query_max(root.right, mid+1, end);
            } else { //  包含
                rightmax = query_max(root.right, start, end);
            }
            //rightmax = query(root.right, Math.max(mid+1,start), end);
        }
        // else 就是不相交
        return Math.max(leftmax, rightmax);
    }

//-----------------------------------------------------------------------------------------
    public int query_min(int start, int end){
        return query_min(root, start, end);
    }

    public int query_min(SegmentTreeNode root, int start, int end) {
        // write your code here
        if(start == root.start && root.end == end) { // 相等
            return root.min;
        }


        int mid = (root.start + root.end)/2;
        int leftmax = Integer.MAX_VALUE, rightmax = Integer.MAX_VALUE;
        // 左子区
        if(start <= mid) {
            if( mid < end) { // 分裂
                leftmax =  query_min(root.left, start, mid);
            } else { // 包含
                leftmax = query_min(root.left, start, end);
            }
            // leftmax = query(root.left, start, Math.min(mid,end));
        }
        // 右子区
        if(mid < end) { // 分裂 3
            if(start <= mid) {
                rightmax = query_min(root.right, mid+1, end);
            } else { //  包含
                rightmax = query_min(root.right, start, end);
            }
            //rightmax = query(root.right, Math.max(mid+1,start), end);
        }
        // else 就是不相交
        return Math.min(leftmax, rightmax);
    }

//-----------------------------------------------------------------------------------------
    public int query_sum(int start, int end){
        return query_sum(root, start, end);
    }

    public int query_sum(SegmentTreeNode root, int start, int end) {
        // write your code here
        if(start > end || root==null)
            return 0;
        if(start <= root.start && root.end <= end) { // 相等
            return root.sum;
        }

        int mid = (root.start + root.end)/2;
        int leftsum = 0, rightsum = 0;
        // 左子区
        if(start <= mid) {
            if( mid < end) { // 分裂
                leftsum =  query_sum(root.left, start, mid);
            } else { // 包含
                leftsum = query_sum(root.left, start, end);
            }
        }
        // 右子区
        if(mid < end) { // 分裂 3
            if(start <= mid) {
                rightsum = query_sum(root.right, mid+1, end);
            } else { //  包含
                rightsum = query_sum(root.right, start, end);
            }
        }
        // else 就是不相交
        return leftsum + rightsum;
    }
//-----------------------------------------------------------------------------------------

}
        //--------------------------------------------------------------------
    }

    class Solution5{

    }
//-------------------------------------------------------------------------------
}
/*
Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:
Input: [1,3,2,3,1]
Output: 2

Example2:
Input: [2,4,3,5,1]
Output: 3

Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.

 */