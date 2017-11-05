package _BinarySearch.Count_of_Smaller_Numbers_J;
import org.junit.Test;

import java.util.*;

// Count of Smaller Number
// lint:    http://lintcode.com/zh-cn/problem/count-of-smaller-number/

public class _248_Count_of_Smaller_Number {
    //  http://blog.csdn.net/sunday0904/article/details/72721803
    public class Solution1 {
        /**
         * @param A: An integer array
         * @return: The number of element in the array that
         *          are smaller that the given integer
         */
        public ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
            // write your code here
            ArrayList<Integer> result = new ArrayList<Integer>();
            Arrays.sort(A);

            for(int i = 0;i<queries.length;i++){
                int count = 0;
                for(int j = 0;j<A.length;j++){
                    if(A[j] >= queries[i]){
                        count = j;
                        break;
                    }
                }
                result.add(count);
            }
            return result;
        }
    }

///////////////////////////////////////////////////////////////////////////

    //  https://github.com/Silocean/LintCode/blob/master/248%20%E7%BB%9F%E8%AE%A1%E6%AF%94%E7%BB%99%E5%AE%9A%E6%95%B4%E6%95%B0%E5%B0%8F%E7%9A%84%E6%95%B0%E7%9A%84%E4%B8%AA%E6%95%B0/CountOfSmallerNumber.java
    public class Solution2 {
        /**
         * @param A: An integer array
         * @return: The number of element in the array that
         * are smaller that the given integer
         */
        public ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
            ArrayList<Integer> result = new ArrayList<>();
            Arrays.sort(A);
            for (int i = 0; i < queries.length; i++) {
                result.add(helper(A, queries[i]));
            }

            return result;
        }

        private int helper(int[] A, int num) {
            int count = 0;
            for (int i = 0; i < A.length; i++) {
                if (A[i] < num) {
                    count++;
                } else {
                    break;
                }
            }

            return count;
        }
    }

    @Test
    public void test02(){
        ArrayList<Integer> result = countOfSmallerNumber(new int[]{1, 2, 7, 8, 5}, new int[]{1, 8, 5});
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

///////////////////////////////////////////////////////////////////////////
	/*
     * @param nums: An integer array
     * @return: The number of element in the array that 
     *          are smaller that the given integer
     */
    class SegmentTreeNode {
        public int start, end;
        public int count;
        public SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end, int count) {
              this.start = start;
              this.end = end;
              this.count = count;
              this.left = this.right = null;
        }
    }
    SegmentTreeNode root;
    public SegmentTreeNode build(int start, int end) {
        // write your code here
        if(start > end) {  // check core case
            return null;
        }
        
        SegmentTreeNode root = new SegmentTreeNode(start, end, 0);
        
        if(start != end) {
            int mid = (start + end) / 2;
            root.left = build(start, mid);
            root.right = build(mid+1, end);
        } else {
            root.count =  0;
        }
        return root;
    }
    public int querySegmentTree(SegmentTreeNode root, int start, int end) {
        // write your code here
        if(start == root.start && root.end == end) { // 相等 
            return root.count;
        }
        
        
        int mid = (root.start + root.end)/2;
        int leftcount = 0, rightcount = 0;
        // 左子区
        if(start <= mid) {
            if( mid < end) { // 分裂 
                leftcount =  querySegmentTree(root.left, start, mid);
            } else { // 包含 
                leftcount = querySegmentTree(root.left, start, end);
            }
        }
        // 右子区
        if(mid < end) { // 分裂 3
            if(start <= mid) {
                rightcount = querySegmentTree(root.right, mid+1, end);
            } else { //  包含 
                rightcount = querySegmentTree(root.right, start, end);
            } 
        }  
        // else 就是不相交
        return leftcount + rightcount;
    }
    public void modifySegmentTree(SegmentTreeNode root, int index, int value) {
        // write your code here
        if(root.start == index && root.end == index) { // 查找到
            root.count += value;
            return;
        }
        
        // 查询
        int mid = (root.start + root.end) / 2;
        if(root.start <= index && index <=mid) {
            modifySegmentTree(root.left, index, value);
        }
        
        if(mid < index && index <= root.end) {
            modifySegmentTree(root.right, index, value);
        }
        //更新
        root.count = root.left.count + root.right.count;
    }
    public ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        // write your code here
        root = build(0, 10000);
        ArrayList<Integer> ans = new ArrayList<>();
        int res;
        for(int i = 0; i < A.length; i++) {
            modifySegmentTree(root, A[i], 1);
        }
        for(int i = 0; i < queries.length; i++) {
            res = 0;
            if(queries[i] > 0)
                res = querySegmentTree(root, 0, queries[i] - 1);
            ans.add(res);
        }
        return ans;
    }

/////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////



}
/*
统计比给定整数小的数的个数

给定一个整数数组 （下标由 0 到 n-1，其中 n 表示数组的规模，数值范围由 0 到 10000），

以及一个 查询列表。对于每一个查询，将会给你一个整数，请你返回该数组中小于给定整数的元素的数量。

 注意事项

在做此题前，最好先完成 线段树的构造 and 线段树查询 II 这两道题目。

您在真实的面试中是否遇到过这个题？ Yes
样例
对于数组 [1,2,7,8,5] ，查询 [1,8,5]，返回 [0,4,2]

挑战
可否用一下三种方法完成以上题目。

仅用循环方法

分类搜索 和 二进制搜索

构建 线段树 和 搜索


 */

/*
 * Give you an integer array (index from 0 to n-1,
 *  where n is the size of this array, value from 0 
 *  to 10000) and an query list. For each query, 
 *  give you an integer, return the number of element
 *   in the array that are smaller than the given integer.

 Notice

We suggest you finish problem Segment Tree Build 
and Segment Tree Query II first.

Have you met this question in a real interview? Yes
Example
For array [1,2,7,8,5], and queries [1,8,5], return [0,4,2]

Challenge 
Could you use three ways to do it.

Just loop
Sort and binary search
Build Segment Tree and Search.
Tags 
Binary Search LintCode Copyright Segment Tree
Related Problems 
Easy Intersection of Two Arrays II 21 %
Easy Intersection of Two Arrays 25 %
Hard Count of Smaller Number before itself 18 %
 * 
 * */
