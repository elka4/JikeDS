package _10_DS.SegmentTree;


public class SegmentTree {

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
