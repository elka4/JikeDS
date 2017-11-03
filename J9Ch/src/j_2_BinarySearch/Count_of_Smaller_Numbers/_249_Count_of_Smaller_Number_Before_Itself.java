package j_2_BinarySearch.Count_of_Smaller_Numbers;

import java.util.ArrayList;


//  lintcode    Count of Smaller Number before itself
//  http://lintcode.com/zh-cn/problem/count-of-smaller-number-before-itself/

//
public class _249_Count_of_Smaller_Number_Before_Itself {
    /*
     * @param nums: An integer array
     * @return: Count the number of element before this element 'ai' is
     *          smaller than it and return count number array
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
    public ArrayList<Integer> countOfSmallerNumberII(int[] A) {
        // write your code here
        root = build(0, 10000);
        ArrayList<Integer> ans = new ArrayList<Integer>();
        int res;
        for(int i = 0; i < A.length; i++) {
            res = 0;
            if(A[i] > 0) {
                res = querySegmentTree(root, 0, A[i]-1);
            }
            modifySegmentTree(root, A[i], 1);
            ans.add(res);
        }
        return ans;
    }
////////////////////////////////////////////////////////////////////////////////////////////////
}

/*
统计前面比自己小的数的个数

 描述
 笔记
 数据
 评测
给定一个整数数组（下标由 0 到 n-1， n 表示数组的规模，取值范围由 0 到10000）。对于数组中的每个 ai 元素，请计算 ai 前的数中比它小的元素的数量。

 注意事项

We suggest you finish problem Segment Tree Build, Segment Tree Query II and Count of Smaller Number first.

您在真实的面试中是否遇到过这个题？ Yes
样例
对于数组[1,2,7,8,5] ，返回 [0,1,2,3,2]
 */
