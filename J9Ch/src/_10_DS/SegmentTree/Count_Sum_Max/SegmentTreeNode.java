package _10_DS.SegmentTree.Count_Sum_Max;

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

        //this.count = 1;

    }
/*    public SegmentTreeNode(int start, int end, int sum) {
        this.start = start;
        this.end = end;
        this.count = end - start + 1;
        this.left = this.right = null;
        this.sum = sum;
    }*/
    @Override
    public String toString(){
        return "[start: " + start + "; end: " + end + "; count: " + count
                + "; max: " + max +"; min: " + min + "; sum: " + sum + "]";
    }


}
