package _10_DS.SegmentTree.count;

public class SegmentTreeNode {
    public int start, end;
    public SegmentTreeNode left, right;
    int value;
    public int count;
    public SegmentTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        this.left = this.right = null;
        this.count = 1;
    }
    public SegmentTreeNode(int start, int end, int value) {
        this.start = start;
        this.end = end;
        this.count = end - start + 1;
        this.left = this.right = null;
        this.value = value;
    }
    @Override
    public String toString(){
        return "[start: " + start + "; end: " + end + "; count: " + count + "]";
    }
}
