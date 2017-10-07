package July._4Array;

public class SegmentTreeBuildII {

    class SegmentTreeNode {
      public int start, end, max;
      public SegmentTreeNode left, right;
      public SegmentTreeNode(int start, int end, int max) {
          this.start = start;
          this.end = end;
          this.max = max;
          this.left = this.right = null;
      }
  }


    public SegmentTreeNode build(int[] A) {
        // write your code here
        return buildTree(0, A.length - 1, A);
    }

    public SegmentTreeNode buildTree(int start, int end, int[] A) {
        if (start > end)
            return null;

        if (start == end) {
            return new SegmentTreeNode(start, end, A[start]);
        }

        SegmentTreeNode node = new SegmentTreeNode(start, end, A[start]);

        int mid = (start + end) / 2;

        node.left = this.buildTree(start, mid, A);
        node.right = this.buildTree(mid + 1, end, A);

        if (node.left != null && node.left.max > node.max)
            node.max = node.left.max;

        if (node.right != null && node.right.max > node.max)
            node.max = node.right.max;

        return node;
    }
}
/*
线段树是一棵二叉树，他的每个节点包含了两个额外的属性start和end用于表示该节点所代表的区间。start和end都是整数，并按照如下的方式赋值:

根节点的 start 和 end 由 build 方法所给出。
对于节点 A 的左儿子，有 start=A.left, end=(A.left + A.right) / 2。
对于节点 A 的右儿子，有 start=(A.left + A.right) / 2 + 1, end=A.right。
如果 start 等于 end, 那么该节点是叶子节点，不再有左右儿子。
对于给定数组设计一个build方法，构造出线段树

说明
wiki:
Segment Tree
Interval Tree

样例
给出[3,2,1,4]，线段树将被这样构造

                 [0,  3] (max = 4)
                  /            \
        [0,  1] (max = 3)     [2, 3]  (max = 4)
        /        \               /             \
[0, 0](max = 3)  [1, 1](max = 2)[2, 2](max = 1) [3, 3] (max = 4)
 */