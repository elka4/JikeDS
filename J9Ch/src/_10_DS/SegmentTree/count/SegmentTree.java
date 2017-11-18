package _10_DS.SegmentTree.count;

import org.junit.Test;

public class SegmentTree {
/*

public class SegmentTreeNode {
    public int start, end, max;
    public SegmentTreeNode left, right;
    public int count;
    public SegmentTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        this.left = this.right = null;
    }
    public SegmentTreeNode(int start, int end, int max) {
        this.start = start;
        this.end = end;
        this.max = max;
        this.left = this.right = null;
    }
    @Override
    public String toString(){
        return "[start: " + start + "; end: " + end + "; max: " + max + "; count: " + count + "]";
    }
}

 */
//-----------------------------------------------------------
    SegmentTree(){}
//-----------------------------------------------------------

    //  Segment Tree Build
    //  http://www.lintcode.com/zh-cn/problem/segment-tree-build/

    /*
    线段树是一棵二叉树，他的每个节点包含了两个额外的属性start和end用于表示该节点所代表的区间。start和end都是整数，并按照如下的方式赋值:

根节点的 start 和 end 由 build 方法所给出。
对于节点 A 的左儿子，有 start=A.left, end=(A.left + A.right) / 2。
对于节点 A 的右儿子，有 start=(A.left + A.right) / 2 + 1, end=A.right。
如果 start 等于 end, 那么该节点是叶子节点，不再有左右儿子。
实现一个 build 方法，接受 start 和 end 作为参数, 然后构造一个代表区间 [start, end] 的线段树，返回这棵线段树的根。

说明
线段树(又称区间树), 是一种高级数据结构，他可以支持这样的一些操作:

查找给定的点包含在了哪些区间内
查找给定的区间包含了哪些点
见百科：
线段树
区间树

样例
比如给定start=1, end=6，对应的线段树为：

               [1,  6]
             /        \
      [1,  3]           [4,  6]
      /     \           /     \
   [1, 2]  [3,3]     [4, 5]   [6,6]
   /    \           /     \
[1,1]   [2,2]     [4,4]   [5,5]
     */
    /**
     *@param start, end: Denote an segment / interval
     *@return: The root of Segment Tree
     */
    public SegmentTreeNode build(int start, int end) {
        // write your code here
        if(start > end) {  // check core case
            return null;
        }
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start == end){
            return  root;
        }


        int mid = (start + end) / 2;
        root.left = build(start, mid);
        root.right = build(mid+1, end);

        root.count = root.left.count + root.right.count;
        return root;
    }
//-----------------------------------------------------------
    //  http://www.lintcode.com/zh-cn/problem/segment-tree-modify/
    /*
    对于一棵 最大线段树, 每个节点包含一个额外的 max 属性，用于存储该节点所代表区间的最大值。

设计一个 modify 的方法，接受三个参数 root、 index 和 value。该方法将 root 为根的线段树中 [start, end] = [index, index] 的节点修改为了新的 value ，并确保在修改后，线段树的每个节点的 max 属性仍然具有正确的值。

 注意事项

在做此题前，最好先完成线段树的构造和 线段树查询这两道题目。

样例
对于线段树:

                      [1, 4, max=3]
                    /                \
        [1, 2, max=2]                [3, 4, max=3]
       /              \             /             \
[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=3]



如果调用 modify(root, 2, 4), 返回:

                      [1, 4, max=4]
                    /                \
        [1, 2, max=4]                [3, 4, max=3]
       /              \             /             \
[1, 1, max=2], [2, 2, max=4], [3, 3, max=0], [4, 4, max=3]




或 调用 modify(root, 4, 0), 返回:

                      [1, 4, max=2]
                    /                \
        [1, 2, max=2]                [3, 4, max=0]
       /              \             /             \
[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=0]
挑战
时间复杂度 O(h) , h 是线段树的高度
     */
    /**
     *@param root, index, value: The root of segment tree and
     *@ change the node's value with [index, index] to the new given value
     *@return: void
     */
    public void modify(SegmentTreeNode root, int index, int value) {
        // write your code here
        if(root.start == index && root.end == index) { // 查找到
            root.value = value;
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
//        root.count = Math.max(root.left.count, root.right.count) + 1;
    }
//-----------------------------------------------------------


//-----------------------------------------------------------
    //  http://www.lintcode.com/zh-cn/problem/segment-tree-query-ii/
/*
对于一个数组，我们可以对其建立一棵 线段树, 每个结点存储一个额外的值 count 来代表这个结点所指代的数组区间内的元素个数. (数组中并不一定每个位置上都有元素)

实现一个 query 的方法，该方法接受三个参数 root, start 和 end, 分别代表线段树的根节点和需要查询的区间，找到数组中在区间[start, end]内的元素个数。

 注意事项

It is much easier to understand this problem if you finished Segment Tree Buildand Segment Tree Query first.

样例
对于数组 [0, 空，2, 3], 对应的线段树为：

                     [0, 3, count=3]
                     /             \
          [0,1,count=1]             [2,3,count=2]
          /         \               /            \
   [0,0,count=1] [1,1,count=0] [2,2,count=1], [3,3,count=1]
query(1, 1), return 0

query(1, 2), return 1

query(2, 3), return 2

query(0, 2), return 2
 */

    /**
     *@param root, start, end: The root of segment tree and
     *                         an segment / interval
     *@return: The count number in the interval [start, end]
     */
    public int query2(SegmentTreeNode root, int start, int end) {
        // write your code here
        if(start > end || root==null)
            return 0;
        if(start <= root.start && root.end <= end) { // 相等
            return root.count;
        }

        int mid = (root.start + root.end)/2;
        int leftsum = 0, rightsum = 0;
        // 左子区
        if(start <= mid) {
            if( mid < end) { // 分裂
                leftsum =  query2(root.left, start, mid);
            } else { // 包含
                leftsum = query2(root.left, start, end);
            }
        }
        // 右子区
        if(mid < end) { // 分裂 3
            if(start <= mid) {
                rightsum = query2(root.right, mid+1, end);
            } else { //  包含
                rightsum = query2(root.right, start, end);
            }
        }
        // else 就是不相交
        return leftsum + rightsum;
    }
//-----------------------------------------------------------
/*@Test
public void test01(){
    SegmentTree st = new SegmentTree();
    SegmentTreeNode stn = st.build(1,10);
    st.modify(stn, 1,1);
    System.out.println(st.query(stn, 1,1));

}*///java.lang.Exception: Test class should have exactly one public constructor
//-----------------------------------------------------------


}
