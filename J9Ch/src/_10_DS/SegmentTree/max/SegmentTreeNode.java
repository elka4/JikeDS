package _10_DS.SegmentTree.max;

public class SegmentTreeNode {
    public int start, end, max;
    public SegmentTreeNode left, right;
    public int count;
/*    public SegmentTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        this.left = this.right = null;
    }*/
    public SegmentTreeNode(int start, int end, int max) {
        this.start = start;
        this.end = end;
        this.max = max;
        this.left = this.right = null;
    }
    @Override
    public String toString(){
        return "[start: " + start + "; end: " + end + "; max: " + max + "]";
    }
}
