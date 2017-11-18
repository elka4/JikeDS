package _10_DS.SegmentTree.Sum;

public class SegmentTreeNode {
    public int start, end;
    public int value;
    public SegmentTreeNode left, right;
    public int sum;
    public SegmentTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        this.left = this.right = null;
    }
    public SegmentTreeNode(int start, int end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
        this.left = this.right = null;
    }
    @Override
    public String toString(){
        return "[start: " + start + "; end: " + end + "; sum: " + sum + "]";
    }
}
