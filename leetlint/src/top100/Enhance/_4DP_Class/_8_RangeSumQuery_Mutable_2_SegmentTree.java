package top100.Enhance._4DP_Class;

public class _8_RangeSumQuery_Mutable_2_SegmentTree {
  class SegmentTreeNode {
        int start;
        int end;
        SegmentTreeNode left, right;
        int sum;
        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
            this.sum = 0;
        }
    }
    SegmentTreeNode root = null;
    public _8_RangeSumQuery_Mutable_2_SegmentTree(int[] nums) {
        root = build(nums, 0, nums.length - 1);
    }
    private SegmentTreeNode build(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start == end) {
            root.sum = nums[end];
        } else {
            int mid = start  + (end - start) / 2;             
            root.left = build(nums, start, mid);
            root.right = build(nums, mid + 1, end);
            root.sum = root.left.sum + root.right.sum;
        }         
        return root;
    }
    public void update(int i, int val) {
        update(root, i, val);
    }
    private void update(SegmentTreeNode root, int index, int newVal) {
        if (root.start == root.end) {
           root.sum = newVal;
           return;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (mid >= index) {
            update(root.left, index, newVal);
        } else {
            update(root.right, index, newVal);
        }
        root.sum = root.left.sum + root.right.sum;
    }
    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }
    private int sumRange(SegmentTreeNode root, int start, int end) {
        if (root.end == end && root.start == start) {
            return root.sum;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (mid >= end) {
            return sumRange(root.left, start, end);
        } else if (mid < start) {
            return sumRange(root.right, start, end);
        }  else {    
            return sumRange(root.left, start, mid) + sumRange(root.right, mid + 1, end);
        }
    }
}
