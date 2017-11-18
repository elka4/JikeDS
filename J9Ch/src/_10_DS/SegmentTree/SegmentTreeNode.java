package _10_DS.SegmentTree;

public class SegmentTreeNode {
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
    }


/*    public SegmentTreeNode(int start, int end, int sum) {
        this.start = start;
        this.end = end;
        this.count = end - start + 1;
        this.left = this.right = null;
        this.sum = sum;
    }*/
    public String toString(){
        return "[start: " + start + "; end: " + end + "; count: " + count
                + "; max: " + max +"; min: " + min + "; sum: " + sum + "]";
    }


}
