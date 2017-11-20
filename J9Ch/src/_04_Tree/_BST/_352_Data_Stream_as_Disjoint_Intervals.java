package _04_Tree._BST;
import java.util.*;

//  352. Data Stream as Disjoint Intervals
//  https://leetcode.com/problems/data-stream-as-disjoint-intervals/description/
//  2
public class _352_Data_Stream_as_Disjoint_Intervals {
//-------------------------------------------------------------------------////////////
    class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
//-------------------------------------------------------------------------////////////
    //1
    /* Java solution using TreeMap, real O(logN) per adding.
    Use TreeMap to easily find the lower and higher keys, the key is the start of the interval.
    Merge the lower and higher intervals when necessary. The time complexity for adding is O(logN) since lowerKey(), higherKey(), put() and remove() are all O(logN). It would be O(N) if you use an ArrayList and remove an interval from it.*/

    public class SummaryRanges {
        TreeMap<Integer, Interval> tree;

        public SummaryRanges() {
            tree = new TreeMap<>();
        }

        public void addNum(int val) {
            if(tree.containsKey(val)) return;
            Integer l = tree.lowerKey(val);
            Integer h = tree.higherKey(val);
            if(l != null && h != null && tree.get(l).end + 1 == val && h == val + 1) {
                tree.get(l).end = tree.get(h).end;
                tree.remove(h);
            } else if(l != null && tree.get(l).end + 1 >= val) {
                tree.get(l).end = Math.max(tree.get(l).end, val);
            } else if(h != null && h == val + 1) {
                tree.put(val, new Interval(val, tree.get(h).end));
                tree.remove(h);
            } else {
                tree.put(val, new Interval(val, val));
            }
        }

        public List<Interval> getIntervals() {
            return new ArrayList<>(tree.values());
        }
    }

//-------------------------------------------------------------------------////////////
    //2
//Java fast log (N) solution (186ms) without using the TreeMap but a customized BST
    public class SummaryRanges2 {
        class BSTNode {
            Interval interval;
            BSTNode left;
            BSTNode right;
            BSTNode(Interval in){
                interval = in;
            }
        }

        BSTNode findMin(BSTNode root) {
            if (root == null) return null;
            if (root.left == null ) return root;
            else return findMin(root.left);
        }

        BSTNode remove(Interval x, BSTNode root) {
            if (root == null) return null;
            else if ( x == null ) return root;
            else if (x.start > root.interval.end ) {
                root.right = remove(x, root.right);
            } else if (x.end < root.interval.start ) {
                root.left = remove(x, root.left);
            } else if ( root.left != null && root.right != null) {
                root.interval = findMin(root.right).interval;
                root.right = remove( root.interval, root.right);
            } else {
                root = ( root.left != null ) ? root.left : root.right;
            }
            return root;
        }

        BSTNode findKey(int val, BSTNode root) {
            if (root == null) return null;
            if (root.interval.start > val) {
                return findKey(val, root.left);
            } else if (root.interval.end < val) {
                return findKey(val, root.right);
            } else return root;
        }

        BSTNode addKey(int val, BSTNode root) {
            if (root == null) {
                root = new BSTNode( new Interval(val, val) );
            } else if (root.interval.start > val) {
                root.left = addKey(val, root.left);
            } else if (root.interval.end < val) {
                root.right = addKey(val, root.right);
            }
            return root;
        }
        void inOrder(BSTNode root) {
            if (root != null) {
                inOrder(root.left);
                list.add(root.interval);
                inOrder(root.right);
            }
        }

        /** Initialize your data structure here. */
        BSTNode root;
        List<Interval> list = new ArrayList();
        public SummaryRanges2() {
            root = null;
        }

        public void addNum(int val) {
            if (root == null) {
                root = addKey(val, root);
            } else {
                if ( findKey(val, root) != null) return;
                BSTNode left = findKey(val-1, root);
                BSTNode right = findKey(val+1, root);
                if (left == null && right == null) {
                    root = addKey(val, root);
                } else if (left != null && right == null) {
                    left.interval.end++;
                } else if (left == null && right != null) {
                    right.interval.start--;
                } else {
                    Interval l = left.interval;
                    int e = right.interval.end;
                    root = remove(right.interval, root);
                    l.end = e;
                }
            }
        }

        public List<Interval> getIntervals() {
            list.clear();
            inOrder(root);
            return list;
        }
    }


//-------------------------------------------------------------------------////////////
}
/*
Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.

For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:

[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]
Follow up:
What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
 */
