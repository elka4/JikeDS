package _10_DS.SegmentTree;
import java.util.*;

//  315. Count of Smaller Numbers After Self
//  https://leetcode.com/problems/count-of-smaller-numbers-after-self/description/
//  Divide and Conquer Binary Indexed Tree Segment Tree Binary Search Tree
public class _315_Count_of_Smaller_Numbers_After_Self_H {


//-------------------------------------------------------------------------/
    //1

//3 Ways (Segment Tree, Binary Indexed Tree, Merge Sort) clean Java code

    public class Solution1 {
        class SegTreeNode {
            int min, max; // range [min, max]
            int count;
            SegTreeNode left, right;

            public int mid() {
                return ((max + 1 - min) / 2 + min);
            }

            public SegTreeNode(int min, int max) {
                this.min = min;
                this.max = max;
                count = 0;
            }
        }

        public List<Integer> countSmaller(int[] nums) {
            List<Integer> list = new LinkedList<Integer>();

            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int i : nums) {
                min = min > i ? i : min;
                max = max < i ? i : max;
            }

            SegTreeNode root = new SegTreeNode(min, max);

            for (int i = nums.length - 1; i >= 0; i--) {
                // minus 1, in case there will be a equal one
                list.add(0, find(nums[i] - 1, root));
                add(nums[i], root);
            }

            return list;
        }

        private int find(int x, SegTreeNode root) {
            if (root == null) return 0;

            if (x >= root.max) {
                return root.count;
            } else {
                int mid = root.mid();
                if (x < mid) {
                    return find(x, root.left);
                } else {
                    return find(x, root.left) + find(x, root.right);
                }
            }
        }

        private void add(int x, SegTreeNode root) {
            if (x < root.min || x > root.max) return;

            root.count++;
            if (root.max == root.min) return;

            int mid = root.mid();
            if (x < mid) {
                if (root.left == null) {
                    root.left = new SegTreeNode(root.min, mid - 1);
                }
                add(x, root.left);
            } else {
                if (root.right == null) {
                    root.right = new SegTreeNode(mid, root.max);
                }
                add(x, root.right);
            }
        }
    }

//-------------------------------------------------------------------------/
    //2
    //Binary indexed Tree
    public class Solution2 {
        public List<Integer> countSmaller(int[] nums) {
            List<Integer> res = new LinkedList<Integer>();
            if (nums == null || nums.length == 0) {
                return res;
            }
            // find min value and minus min by each elements, plus 1 to avoid 0 element
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                min = (nums[i] < min) ? nums[i]:min;
            }
            int[] nums2 = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                nums2[i] = nums[i] - min + 1;
                max = Math.max(nums2[i],max);
            }
            int[] tree = new int[max+1];
            for (int i = nums2.length-1; i >= 0; i--) {
                res.add(0,get(nums2[i]-1,tree));
                update(nums2[i],tree);
            }
            return res;
        }
        private int get(int i, int[] tree) {
            int num = 0;
            while (i > 0) {
                num +=tree[i];
                i -= i&(-i);
            }
            return num;
        }
        private void update(int i, int[] tree) {
            while (i < tree.length) {
                tree[i] ++;
                i += i & (-i);
            }
        }
    }
//-------------------------------------------------------------------------/
    //3
    //Merge Sort
    public class Solution3 {

        public List<Integer> countSmaller(int[] nums) {
            int len = (nums == null? 0 : nums.length);

            int[] idxs = new int[len];
            int[] count = new int[len];

            for (int i = 0; i < len; i++) idxs[i] = i;

            mergeSort(nums, idxs, 0, len, count);

            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i : count) list.add(i);

            return list;
        }

        private void mergeSort(int[] nums, int[] idxs, int start, int end, int[] count) {
            if (start + 1 >= end) return;

            int mid = (end - start) / 2 + start;
            mergeSort(nums, idxs, start, mid, count);
            mergeSort(nums, idxs, mid, end, count);

            merge(nums, idxs, start, end, count);
        }

        private void merge(int[] nums, int[] idxs, int start, int end, int[] count) {
            int mid = (end - start) / 2 + start;

            int[] tmp = new int[end - start];
            int[] tmpidx = new int[end - start];
            int i = start, j = mid, k = 0;
            while (k < end - start) {
                if (i < mid) {
                    if (j < end && nums[j] < nums[i]) {
                        tmpidx[k] = idxs[j];
                        tmp[k++] = nums[j++];
                    } else {
                        count[idxs[i]] += j - mid; // add those already counted
                        tmpidx[k] = idxs[i];
                        tmp[k++] = nums[i++];
                    }

                } else {
                    tmpidx[k] = idxs[j];
                    tmp[k++] = nums[j++];
                }
            }

            System.arraycopy(tmpidx, 0, idxs, start, end - start);
            System.arraycopy(tmp, 0, nums, start, end - start);
        }
    }
//-------------------------------------------------------------------------/


//-------------------------------------------------------------------------/


//-------------------------------------------------------------------------/


//-------------------------------------------------------------------------/
}
/*
You are given an integer array nums and you have to return a new counts array.

The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].


 */