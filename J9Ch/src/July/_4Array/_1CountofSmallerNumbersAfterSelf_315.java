package July._4Array;

import org.junit.Test;

import java.util.*;

//  Count of Smaller Numbers After Self
public class _1CountofSmallerNumbersAfterSelf_315 {
    //jiuzhang

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

/////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @param A: An integer array
     * @return: The number of element in the array that
     *          are smaller that the given integer
     */

    public ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        // write your code here
        root = build(0, 10000);
        ArrayList<Integer> ans = new ArrayList<Integer>();
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

    @Test
    public void test02(){
        int[] A = new int[]{1,2,7,8,5};
        int[] queries = new int[]{1,8,5};
        System.out.println(countOfSmallerNumber(A, queries));
        // [0, 4, 2]
    }

////////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////////



}
/*
给定一个整数数组 （下标由 0 到 n-1，其中 n 表示数组的规模，数值范围由 0 到 10000），以及一个 查询列表。
对于每一个查询，将会给你一个整数，请你返回该数组中小于给定整数的元素的数量。

 注意事项

在做此题前，最好先完成 线段树的构造 and 线段树查询 II 这两道题目。

样例
对于数组 [1,2,7,8,5] ，查询 [1,8,5]，返回 [0,4,2]



 */