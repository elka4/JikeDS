package _BinarySearch.Count_of_Smaller_Numbers_J;
import java.util.*;


//  lintcode    Count of Smaller Number before itself
//  http://lintcode.com/zh-cn/problem/count-of-smaller-number-before-itself/

//
public class _249_Count_of_Smaller_Number_Before_Itself {
    //  http://blog.csdn.net/u010638189/article/details/68952224
    public class Solution1 {
        /**
         * @param A: An integer array
         * @return: Count the number of element before this element 'ai' is
         *          smaller than it and return count number array
         */
        public ArrayList<Integer> countOfSmallerNumberII(int[] A) {
            // write your code here
            ArrayList<Integer> list = new ArrayList<Integer>();

            TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();

            Iterator<Map.Entry<Integer, Integer>> it = null;

            for(int i = 0 ; i < A.length ; i ++){
                int count = 0 ;
                it = map.entrySet().iterator();
                int temp = A[i];

                while(it.hasNext()){
                    Map.Entry<Integer,Integer> entry = it.next();
                    if(entry.getKey() >= temp)
                        break;
                    count = count + entry.getValue();
                }

                list.add(count);

                if(map.containsKey(temp)){
                    int sum = map.get(temp);
                    sum++;
                    map.put(temp,sum);
                }else{
                    map.put(temp,1);
                }
            }

            return list;
        }
    }

/////////////////////////////////////////////////////////////////////////////
    //  https://github.com/Silocean/LintCode/blob/master/248%20%E7%BB%9F%E8%AE%A1%E6%AF%94%E7%BB%99%E5%AE%9A%E6%95%B4%E6%95%B0%E5%B0%8F%E7%9A%84%E6%95%B0%E7%9A%84%E4%B8%AA%E6%95%B0/CountOfSmallerNumberII.java
public class CountOfSmallerNumberII {
    public  void main(String[] args) {
        ArrayList<Integer> result = countOfSmallerNumber(new int[]{1, 2, 7, 8, 5}, new int[]{1, 12, 6});
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    /**
     * @param A: An integer array
     * @return: The number of element in the array that
     * are smaller that the given integer
     */
    public  ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        ArrayList<Integer> result = new ArrayList<>();
        Arrays.sort(A);
        for (int i = 0; i < queries.length; i++) {
            result.add(binarySearch(A, queries[i]));
        }
        return result;
    }

    private int binarySearch(int[] A, int number) {
        if (A.length == 0) return 0;
        int start = 0;
        int end = A.length - 1;
        int mid = 0;
        while (start + 1 < end) {
            mid = (start + end) / 2;
            if (number > A[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (A[start] >= number) {
            return start;
        }
        if (A[end] >= number) {
            return end;
        }
        return end + 1;
    }
}

    /////////////////////////////////////////////////////////////////////////////
public class Solution3 {
    /**
     * @param A: An integer array
     * @return: Count the number of element before this element 'ai' is
     *          smaller than it and return count number array
     */

    int count = 0 ;
    public ArrayList<Integer> countOfSmallerNumberII(int[] A) {
        // write your code here

        ArrayList<Integer> list = new ArrayList<Integer>();

        if(A.length == 0)
            return list;

        TreeNode head = new TreeNode();
        head.sel = 1;
        head.val = A[0];
        list.add(0);

        for(int i = 1 ; i < A.length ; i ++){
            count = 0;
            insert(head,A[i]);
            list.add(count);
        }

        return list;

    }

    private void insert(TreeNode node , int var){
        if(var == node.val){
            node.sel++;
            count = count + node.smaller;
            return;
        } else if(var < node.val){
            node.smaller++;
            if(node.left == null){
                TreeNode newnode = new TreeNode();
                newnode.val = var;
                newnode.sel = 1;
                node.left = newnode;
                return;
            }else{
                insert(node.left,var);
            }
        } else if(var > node.val){
            count = count + node.sel + node.smaller;
            if(node.right == null){
                TreeNode newnode = new TreeNode();
                newnode.val = var;
                newnode.sel = 1;
                node.right = newnode;
                return;
            }else{
                insert(node.right,var);
            }
        }
    }

    private class TreeNode{
        TreeNode left = null;
        TreeNode right = null;
        int smaller = 0;//比该节点小的值得个数
        int sel = 0;//该节点的值出现的个数
        int val = 0;//该节点的值
    }
}
/////////////////////////////////////////////////////////////////////////////
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
