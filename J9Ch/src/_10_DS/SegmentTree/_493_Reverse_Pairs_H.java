package _10_DS.SegmentTree;
import java.util.*;

//  493. Reverse Pairs
//  https://leetcode.com/problems/reverse-pairs/description/
//  Divide and Conquer Binary Indexed Tree Segment Tree Binary Search Tree
public class _493_Reverse_Pairs_H {

    //https://leetcode.com/problems/reverse-pairs/solution/



////////////////////////////////////////////////////////////////////////////////////
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
////////////////////////////////////////////////////////////////////////////////////
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
////////////////////////////////////////////////////////////////////////////////////
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
////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////
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