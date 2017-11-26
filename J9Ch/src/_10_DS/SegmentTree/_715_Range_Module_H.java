package _10_DS.SegmentTree;
import java.util.*;

//   715. Range Module
//  https://leetcode.com/problems/range-module/description/
//  Array Segment Tree Binary Search Tree
public class _715_Range_Module_H {
    //https://leetcode.com/problems/range-module/solution/

    //1
    //Approach #1: Maintain Sorted Disjoint Intervals [Accepted]

    class RangeModule {
        TreeSet<Interval> ranges;
        public RangeModule() {
            ranges = new TreeSet();
        }

        public void addRange(int left, int right) {
            Iterator<Interval> itr = ranges.tailSet(new Interval(0, left - 1)).iterator();
            while (itr.hasNext()) {
                Interval iv = itr.next();
                if (right < iv.left) break;
                left = Math.min(left, iv.left);
                right = Math.max(right, iv.right);
                itr.remove();
            }
            ranges.add(new Interval(left, right));
        }

        public boolean queryRange(int left, int right) {
            Interval iv = ranges.higher(new Interval(0, left));
            return (iv != null && iv.left <= left && right <= iv.right);
        }

        public void removeRange(int left, int right) {
            Iterator<Interval> itr = ranges.tailSet(new Interval(0, left)).iterator();
            ArrayList<Interval> todo = new ArrayList();
            while (itr.hasNext()) {
                Interval iv = itr.next();
                if (right < iv.left) break;
                if (iv.left < left) todo.add(new Interval(iv.left, left));
                if (right < iv.right) todo.add(new Interval(right, iv.right));
                itr.remove();
            }
            for (Interval iv: todo) ranges.add(iv);
        }
    }

    class Interval implements Comparable<Interval>{
        int left;
        int right;

        public Interval(int left, int right){
            this.left = left;
            this.right = right;
        }

        public int compareTo(Interval that){
            if (this.right == that.right) return this.left - that.left;
            return this.right - that.right;
        }
    }

//-------------------------------------------------------------------------------
//2
//Java Segment Tree


    class RangeModule2 {

        class SegNode {
            int left, right, mid;
            SegNode lc, rc;
            boolean modified;
            boolean covered;
            boolean empty;
            public SegNode(int left, int right){
                this.left=left;
                this.right=right;
                this.mid=left+(right-left)/2;
                this.empty=true;
            }
            void addRange(int left, int right){
//                log.info("addRange({}, {}) on ({})", left, right, this);
                if (left<=this.left&&right>=this.right){
                    if (!covered) {
                        covered = true;
                        modified=true;
                        empty=false;
                    }
                } else {
                    pushdown();
                    if (left<=mid) this.lc.addRange(left, right);
                    if (right>mid)this.rc.addRange(left, right);
                    maintain();
                }
//                log.info("after addRange({}, {}) on ({}", left, right, this);
            }
            void maintain(){
                this.covered=this.lc.covered&&this.rc.covered;
                this.empty=this.lc.empty&&this.rc.empty;
            }
            void removeRange(int left, int right){
//                log.info("remove ({}, {}) on ({})", left, right, this);
                if (left<=this.left&&right>=this.right){
                    if (!empty){
                        empty=true;
                        covered=false;
                        modified=true;
                    }
                } else {
                    pushdown();
                    if (left<=mid)this.lc.removeRange(left, right);
                    if (right>mid)this.rc.removeRange(left, right);
                    maintain();

                }
//                log.info("after remove ({}, {}) on ({})", left, right, this);
            }
            boolean query(int left, int right){
//                log.info("query ({}, {}) on ({}, {})", left, right, this);
                if (this.empty)return false;
                if (this.covered)return true;
                if (left<=this.left&&right>=this.right) {
                    return this.covered;
                }
                boolean ans=true;
                if (left<=mid)ans&=this.lc.query(left, right);
                if (right>mid)ans&=this.rc.query(left, right);
                return ans;
            }
            void pushdown(){
                if (this.lc==null){
                    this.lc=new SegNode(left, mid);
                    this.lc.covered=this.covered;
                    this.lc.empty=this.empty;
                    this.rc=new SegNode(mid+1,right);
                    this.rc.covered=this.covered;
                    this.rc.empty=this.empty;
                    this.modified=false;
                } else if (modified){
                    this.lc.covered=this.covered;
                    this.lc.empty=this.empty;
                    this.lc.modified=true;
                    this.rc.covered=this.covered;
                    this.rc.empty=this.empty;
                    this.rc.modified=true;
                    this.modified=false;
                }
            }

//            @Override
//            public String toString() {
//                return MoreObjects.toStringHelper(this)
//                        .add("left", left)
//                        .add("right", right)
//                        .add("mid", mid)
//                        .add("modified", modified)
//                        .add("covered", covered)
//                        .add("empty", empty)
//                        .toString();
//            }
        }
        SegNode root;
        public RangeModule2() {
            root =new SegNode(1, 1_000_000_000);

        }

        public void addRange(int left, int right) {
            root.addRange(left, right-1);

        }

        public boolean queryRange(int left, int right) {
            return root.query(left, right-1);

        }

        public void removeRange(int left, int right) {
            root.removeRange(left, right-1);

        }
    }
//-------------------------------------------------------------------------------


//-------------------------------------------------------------------------------


//-------------------------------------------------------------------------------


//-------------------------------------------------------------------------------
}
/*
A Range Module is a module that tracks ranges of numbers. Your task is to design and implement the following interfaces in an efficient manner.

addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
queryRange(int left, int right) Returns true if and only if every real number in the interval [left, right) is currently being tracked.
removeRange(int left, int right) Stops tracking every real number currently being tracked in the interval [left, right).
Example 1:
addRange(10, 20): null
removeRange(14, 16): null
queryRange(10, 14): true (Every number in [10, 14) is being tracked)
queryRange(13, 15): false (Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
queryRange(16, 17): true (The number 16 in [16, 17) is still being tracked, despite the remove operation)
Note:

A half open interval [left, right) denotes all real numbers left <= x < right.
0 < left < right < 10^9 in all calls to addRange, queryRange, removeRange.
The total number of calls to addRange in a single test case is at most 1000.
The total number of calls to queryRange in a single test case is at most 5000.
The total number of calls to removeRange in a single test case is at most 1000.
 */