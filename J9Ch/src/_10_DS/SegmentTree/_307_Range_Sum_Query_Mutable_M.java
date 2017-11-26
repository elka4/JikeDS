package _10_DS.SegmentTree;
import java.util.*;

//  307. Range Sum Query - Mutable
//  https://leetcode.com/problems/range-sum-query-mutable/description/
//  Binary Indexed Tree Segment Tree
public class _307_Range_Sum_Query_Mutable_M {

    //https://leetcode.com/problems/range-sum-query-mutable/solution/
    //1
    int[] data;
    //Approach #1 (Naive) [Time Limit Exceeded]
    class Solution1{
        private int[] nums;
        public int sumRange(int i, int j) {
            int sum = 0;
            for (int l = i; l <= j; l++) {
                sum += data[l];
            }
            return sum;
        }

//        public int update(int i, int val) {
//            nums[i] = val;
//        }
    }

//-------------------------------------------------------------------------------
    //2
    //Approach #2 (Sqrt decomposition) [Accepted]
    class NumArray2{
        private int[] b;
        private int len;
        private int[] nums;

        public NumArray2(int[] nums) {
            this.nums = nums;
            double l = Math.sqrt(nums.length);
            len = (int) Math.ceil(nums.length/l);
            b = new int [len];
            for (int i = 0; i < nums.length; i++)
                b[i / len] += nums[i];
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            int startBlock = i / len;
            int endBlock = j / len;
            if (startBlock == endBlock) {
                for (int k = i; k <= j; k++)
                    sum += nums[k];
            } else {
                for (int k = i; k <= (startBlock + 1) * len - 1; k++)
                    sum += nums[k];
                for (int k = startBlock + 1; k <= endBlock - 1; k++)
                    sum += b[k];
                for (int k = endBlock * len; k <= j; k++)
                    sum += nums[k];
            }
            return sum;
        }

        public void update(int i, int val) {
            int b_l = i / len;
            b[b_l] = b[b_l] - nums[i] + val;
            nums[i] = val;
        }
    // Accepted

    }


//-------------------------------------------------------------------------------
    //3
//Approach #3 (Segment tree) [Accepted]
    class NumArray3{
        int[] tree;
        int n;
        public NumArray3(int[] nums) {
            if (nums.length > 0) {
                n = nums.length;
                tree = new int[n * 2];
                buildTree(nums);
            }
        }
        private void buildTree(int[] nums) {
            for (int i = n, j = 0;  i < 2 * n; i++,  j++)
                tree[i] = nums[j];
            for (int i = n - 1; i > 0; --i)
                tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
        void update(int pos, int val) {
            pos += n;
            tree[pos] = val;
            while (pos > 0) {
                int left = pos;
                int right = pos;
                if (pos % 2 == 0) {
                    right = pos + 1;
                } else {
                    left = pos - 1;
                }
                // parent is updated after child is updated
                tree[pos / 2] = tree[left] + tree[right];
                pos /= 2;
            }
        }

        public int sumRange(int l, int r) {
            // get leaf with value 'l'
            l += n;
            // get leaf with value 'r'
            r += n;
            int sum = 0;
            while (l <= r) {
                if ((l % 2) == 1) {
                    sum += tree[l];
                    l++;
                }
                if ((r % 2) == 0) {
                    sum += tree[r];
                    r--;
                }
                l /= 2;
                r /= 2;
            }
            return sum;
        }
    }


//-------------------------------------------------------------------------------
//-------------------------------------------------------------------------------

    class NumArray {
        SegmentTree st;

        public NumArray(int[] nums) {
            st = new SegmentTree(nums);
        }

        public void update(int i, int val) {
            st.modify(i, val);
        }

        public int sumRange(int i, int j) {
            return st.query_sum(i, j);
        }

        //---------------------------------//////
        class SegmentTree {
            SegmentTreeNode root;
            SegmentTree(){

            }

            SegmentTree(int start, int end){
                root = build(start, end);
            }

            SegmentTree(int[] arr){
                int n = arr.length;
                root = build(0, n-1);
                for (int i = 0; i < n; i++) {
                    modify(i, arr[i]);
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

                //this.count = 1;

            }
            /*    public SegmentTreeNode(int start, int end, int sum) {
                    this.start = start;
                    this.end = end;
                    this.count = end - start + 1;
                    this.left = this.right = null;
                    this.sum = sum;
                }*/
            @Override
            public String toString(){
                return "[start: " + start + "; end: " + end + "; count: " + count
                        + "; max: " + max +"; min: " + min + "; sum: " + sum + "]";
            }


        }

    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
//-------------------------------------------------------------------------------
//-------------------------------------------------------------------------------


//-------------------------------------------------------------------------------
}
/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.
 */