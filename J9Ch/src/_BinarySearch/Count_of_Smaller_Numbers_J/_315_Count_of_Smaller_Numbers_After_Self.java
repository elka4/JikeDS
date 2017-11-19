package _BinarySearch.Count_of_Smaller_Numbers_J;
import java.util.*;
import java.util.stream.IntStream;


//  leetcode  315. Count of Smaller Numbers After Self
//  https://leetcode.com/problems/count-of-smaller-numbers-after-self/description/
public class _315_Count_of_Smaller_Numbers_After_Self {
/*
9ms short Java BST solution get answer when building BST
Every node will maintain a val sum recording the total of number on it's left bottom side, dup counts the duplication. For example, [3, 2, 2, 6, 1], from back to beginning,we would have:

                1(0, 1)
                     \
                     6(3, 1)
                     /
                   2(0, 2)
                       \
                        3(0, 1)
When we try to insert a number, the total number of smaller number would be adding dup and sum of the nodes where we turn right.
for example, if we insert 5, it should be inserted on the way down to the right of 3, the nodes where we turn right is 1(0,1), 2,(0,2), 3(0,1), so the answer should be (0 + 1)+(0 + 2)+ (0 + 1) = 4

if we insert 7, the right-turning nodes are 1(0,1), 6(3,1), so answer should be (0 + 1) + (3 + 1) = 5
 */
public class Solution1 {
    class Node {
        Node left, right;
        int val, sum, dup = 1;
        public Node(int v, int s) {
            val = v;
            sum = s;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        Integer[] ans = new Integer[nums.length];
        Node root = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            root = insert(nums[i], root, ans, i, 0);
        }
        return Arrays.asList(ans);
    }
    private Node insert(int num, Node node, Integer[] ans, int i, int preSum) {
        if (node == null) {
            node = new Node(num, 0);
            ans[i] = preSum;
        } else if (node.val == num) {
            node.dup++;
            ans[i] = preSum + node.sum;
        } else if (node.val > num) {
            node.sum++;
            node.left = insert(num, node.left, ans, i, preSum);
        } else {
            node.right = insert(num, node.right, ans, i, preSum + node.dup + node.sum);
        }
        return node;
    }
}

//My simple AC Java Binary Search code
/*
Traverse from the back to the beginning of the array, maintain an sorted array of numbers have been visited. Use findIndex() to find the first element in the sorted array which is larger or equal to target number. For example, [5,2,3,6,1], when we reach 2, we have a sorted array[1,3,6], findIndex() returns 1, which is the index where 2 should be inserted and is also the number smaller than 2. Then we insert 2 into the sorted array to form [1,2,3,6].

Due to the O(n) complexity of ArrayList insertion, the total runtime complexity is not very fast, but anyway it got AC for around 53ms.

 */
class Solution2{
    public List<Integer> countSmaller(int[] nums) {
        Integer[] ans = new Integer[nums.length];
        List<Integer> sorted = new ArrayList<Integer>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = findIndex(sorted, nums[i]);
            ans[i] = index;
            sorted.add(index, nums[i]);
        }
        return Arrays.asList(ans);
    }
    private int findIndex(List<Integer> sorted, int target) {
        if (sorted.size() == 0) return 0;
        int start = 0;
        int end = sorted.size() - 1;
        if (sorted.get(end) < target) return end + 1;
        if (sorted.get(start) >= target) return 0;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (sorted.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (sorted.get(start) >= target) return start;
        return end;
    }
}

//11ms JAVA solution using merge sort with explanation
/*
The basic idea is to do merge sort to nums[]. To record the result, we need to keep the index of each number in the original array. So instead of sort the number in nums, we sort the indexes of each number.
Example: nums = [5,2,6,1], indexes = [0,1,2,3]
After sort: indexes = [3,1,0,2]

While doing the merge part, say that we are merging left[] and right[], left[] and right[] are already sorted.

We keep a rightcount to record how many numbers from right[] we have added and keep an array count[] to record the result.

When we move a number from right[] into the new sorted array, we increase rightcount by 1.

When we move a number from left[] into the new sorted array, we increase count[ index of the number ] by rightcount.
 */


class Solution3{
    int[] count;
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();

        count = new int[nums.length];
        int[] indexes = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            indexes[i] = i;
        }
        mergesort(nums, indexes, 0, nums.length - 1);
        for(int i = 0; i < count.length; i++){
            res.add(count[i]);
        }
        return res;
    }
    private void mergesort(int[] nums, int[] indexes, int start, int end){
        if(end <= start){
            return;
        }
        int mid = (start + end) / 2;
        mergesort(nums, indexes, start, mid);
        mergesort(nums, indexes, mid + 1, end);

        merge(nums, indexes, start, end);
    }
    private void merge(int[] nums, int[] indexes, int start, int end){
        int mid = (start + end) / 2;
        int left_index = start;
        int right_index = mid+1;
        int rightcount = 0;
        int[] new_indexes = new int[end - start + 1];

        int sort_index = 0;
        while(left_index <= mid && right_index <= end){
            if(nums[indexes[right_index]] < nums[indexes[left_index]]){
                new_indexes[sort_index] = indexes[right_index];
                rightcount++;
                right_index++;
            }else{
                new_indexes[sort_index] = indexes[left_index];
                count[indexes[left_index]] += rightcount;
                left_index++;
            }
            sort_index++;
        }
        while(left_index <= mid){
            new_indexes[sort_index] = indexes[left_index];
            count[indexes[left_index]] += rightcount;
            left_index++;
            sort_index++;
        }
        while(right_index <= end){
            new_indexes[sort_index++] = indexes[right_index++];
        }
        for(int i = start; i <= end; i++){
            indexes[i] = new_indexes[i - start];
        }
    }
}

//  Easiest Java solution
    /*
    Traverse from nums[len - 1] to nums[0], and build a binary search tree, which stores:

val: value of nums[i]
count: if val == root.val, there will be count number of smaller numbers on the right
Run time is 10ms. Hope it helps!
     */
class Solution4{
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        TreeNode root = new TreeNode(nums[nums.length - 1]);
        res.add(0);
        for(int i = nums.length - 2; i >= 0; i--) {
            int count = insertNode(root, nums[i]);
            res.add(count);
        }
        Collections.reverse(res);
        return res;
    }

    public int insertNode(TreeNode root, int val) {
        int thisCount = 0;
        while(true) {
            if(val <= root.val) {
                root.count++;
                if(root.left == null) {
                    root.left = new TreeNode(val); break;
                } else {
                    root = root.left;
                }
            } else {
                thisCount += root.count;
                if(root.right == null) {
                    root.right = new TreeNode(val); break;
                } else {
                    root = root.right;
                }
            }
        }
        return thisCount;
    }
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        int count = 1;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}

//  https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/
//  Short Java Binary Index Tree BEAT 97.33% With Detailed Explanation

/*
This is the Binary Index Tree.

Here is a very good explanation.

What is Binary Index Tree

The basic idea is:

1, we should build an array with the length equals to the max element of the nums array as BIT.
2, To avoid minus value in the array, we should first add the (min+1) for every elements
(It may be out of range, where we can use long to build another array. But no such case in the test cases so far.)
3, Using standard BIT operation to solve it.
 */
public class Solution5 {
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


//  Map each number into its corresponding ordered index first.
//  https://discuss.leetcode.com/topic/39656/short-java-binary-index-tree-beat-97-33-with-detailed-explanation/5
class Solution6{
    class BIT {
        int n;
        int[] bit;

        BIT(int size) {
            this.n = size + 1;
            this.bit = new int[this.n];
        }

        void update(int i) {
            while (i <= n - 1) {
                bit[i]++;
                i = i + (i & -i);
            }
        }

        int sum(int i) {
            int ans = 0;
            while (i > 0) {
                ans += bit[i];
                i = i - (i & -i);
            }
            return ans;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> counts = new LinkedList<Integer>();

        if (nums == null || nums.length == 0)
            return counts;

        int[] orderedNums = nums.clone();
        Arrays.sort(orderedNums);
        int[] nums2 = IntStream.of(nums)
                .map(x -> Arrays.binarySearch(orderedNums, x) + 1).toArray();

        BIT bit = new BIT(nums2.length);
        for (int i = nums2.length - 1; i >= 0; i--) {
            counts.add(0, bit.sum(nums2[i]));
            bit.update(nums2[i] + 1);
        }

        return counts;
    }

}

//  Fenwick Tree
public class Solution7 {
    public List<Integer> countSmaller(int[] nums) {
        if(nums == null || nums.length == 0) return new ArrayList<>();

        // find min value and minus min by each elements, plus 1 to avoid 0 element
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++) min = Math.min(min, nums[i]);;
        for(int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] - min + 1;
            max = Math.max(max, nums[i]);
        }

        List<Integer> res = new ArrayList<>();
        int[] fenwickTree = new int[max + 1];
        for(int i = nums.length - 1; i >= 0; i--) {
            // the index of nums[i] is nums[i] - 1
            // we need to find the sum (-INF, nums[i] - 1], so the index is nums[i] - 2
            res.add(0, getSum(fenwickTree, nums[i] - 2));

            // after searching, we need to update the fenwick tree for the next round
            // the new added number is nums[i], but its index of original is nums[i] - 1
            updateFenwickTree(fenwickTree, nums[i] - 1, 1);
        }
        return res;
    }

    // the index is the index of original array
    private void updateFenwickTree(int[] fenwickTree, int index, int value) {
        // the index of fenwick tree is one larger than the index of original array
        for(int i = index + 1; i < fenwickTree.length; i += i & (-i)) {
            fenwickTree[i] += value;
        }
    }

    // the index is the index of original array
    private int getSum(int[] fenwickTree, int index) {
        int sum = 0;
        // the index of fenwick tree is one larger than the index of original array
        for(int i = index + 1; i > 0; i -= i & (-i)) {
            sum += fenwickTree[i];
        }
        return sum;
    }
}


//Another BIT Solution written in Java
public class Solution8 {
    public List<Integer> countSmaller(int[] nums) {
        if(nums == null || nums.length == 0) return new ArrayList<>();

        // clone the original array and sort it, store <value, position> into hash map
        Map<Integer, Integer> map = new HashMap<>();
        int[] sortedNum = nums.clone();
        Arrays.sort(sortedNum);
        for(int i = 0; i < nums.length; i++) map.put(sortedNum[i], i);

        // create fenwick tree whose length is one larger than the original array
        int[] fenwickTree = new int[nums.length + 1];
        List<Integer> res = new ArrayList<>();
        for(int i = nums.length - 1; i >= 0; i--) {
            res.add(0, getSum(fenwickTree, map.get(nums[i]) - 1));
            updateFenwickTree(fenwickTree, map.get(nums[i]), 1);
        }
        return res;
    }

    // the index is the index of original array
    private void updateFenwickTree(int[] fenwickTree, int index, int value) {
        // the index of fenwick tree is one larger than the index of original array
        for(int i = index + 1; i < fenwickTree.length; i += i & (-i)) {
            fenwickTree[i] += value;
        }
    }

    // the index is the index of original array
    private int getSum(int[] fenwickTree, int index) {
        int sum = 0;
        // the index of fenwick tree is one larger than the index of original array
        for(int i = index + 1; i > 0; i -= i & (-i)) {
            sum += fenwickTree[i];
        }
        return sum;
    }
}
/*
Memory efficient version, in case the given array is sparse.
Map size would be O(lg(max element) + n)
 */

class Solution9{
    public List<Integer> countSmaller(int[] nums) {
        Map<Integer, Integer> bit = new HashMap<>();
        List<Integer> ret = new ArrayList<>();
        if (nums.length==0) return ret;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i=0;i<nums.length;i++) {
            max = Math.max(nums[i], max);
            min = Math.min(nums[i], min);
        }

        int adjust = 0;
        if (min<0) adjust = -min;
        max+=adjust+1;
        for (int i=nums.length-1;i>=0;i--) {
            int n = nums[i] + adjust;
            ret.add(get(bit, n));
            update(bit, n+1, 1, max);
        }
        Collections.reverse(ret);
        return ret;
    }

    int get(Map<Integer, Integer> bit, int idx) {
        int sum=0;
        while (idx>0) {
            sum+=bit.getOrDefault(idx,0);
            idx-=(idx&-idx);
        }
        return sum;
    }

    void update(Map<Integer, Integer> bit, int idx, int val, int max) {
        while (idx<=max+1) {
            bit.put(idx, bit.getOrDefault(idx, 0) + val);
            idx+=(idx&-idx);
        }
    }
}

//-------------------------------------------------------------------------////
    //Java, 5 methods, merge sort, binary indexed tree, binary search tree
    //  Bottom up merge sort, sort the index array and count the exchanges during merge
    class Solution11{
        private void merge(int[] nums, int[] indices, int[] aux, int[] count, int start, int end, int step) {
            for (int i = start, j = start+step, k = start; k <= end; k++) {
                if (i == start+step) aux[k++] = indices[j++];
                else if (j == end+1) {
                    aux[k++] = indices[i];
                    count[indices[i++]] += j-start-step;
                } else if (nums[indices[i]] <= nums[indices[j]]) {
                    aux[k++] = indices[i];
                    count[indices[i++]] += j-start-step;
                } else {
                    aux[k++] = indices[j++];
                }
            }
            for (int i = start; i <= end; i++) {
                indices[i] = aux[i];
            }
        }
        public List<Integer> countSmaller(int[] nums) {
            int n = nums.length;
            int[] aux = new int[n], indices = new int[n], count = new int[n];
            for (int i = 0; i < n; i++) indices[i] = i;
            for (int i = 1; i < n; i *= 2) {
                for (int j = 0; j < n; j += 2*i) {
                    if (j+i >= nums.length) continue;
                    merge(nums, indices, aux, count, j, Math.min(j+i*2-1, nums.length-1), i);
                }
            }
            LinkedList<Integer> result = new LinkedList<>();
            for (int c : count) {
                result.add(c);
            }
            return result;
        }
    }

    //top-down merge sort, similar idea to solution1:
    class Solution22{
        private void merge(int[] nums, int[] indices, int[] aux, int[] count, int start, int end) {
            if (start >= end) return;
            int mid = start+(end-start)/2;
            merge(nums, indices, aux, count, start, mid);
            merge(nums, indices, aux, count, mid+1, end);
            for (int i = start, j = mid+1, k = start; k <= end; k++) {
                if (i == mid+1) aux[k] = indices[j++];
                else if (j == end+1) {
                    aux[k] = indices[i];
                    count[indices[i++]] += j-mid-1;
                } else if (nums[indices[i]] <= nums[indices[j]]) {
                    aux[k] = indices[i];
                    count[indices[i++]] += j-mid-1;
                } else {
                    aux[k] = indices[j++];
                }
            }
            for (int i = start; i <= end; i++) {
                indices[i] = aux[i];
            }
        }
        public List<Integer> countSmaller(int[] nums) {
            int n = nums.length;
            int[] aux = new int[n], indices = new int[n], count = new int[n];
            for (int i = 0; i < n; i++) {
                indices[i] = i;
            }
            merge(nums, indices, aux, count, 0, nums.length-1);
            LinkedList<Integer> result = new LinkedList<>();
            for (int c : count) result.add(c);
            return result;
        }
    }

    //  Binary Indexed Tree, iterate from the back of the array, use the rank as BIT index:
    class Solution33{
        private void update(int[]BIT, int index, int val) {
            index++;
            while (index < BIT.length) {
                BIT[index] += val;
                index += index & (-index);
            }
        }
        private int getSum(int[]BIT, int index) {
            index++;
            int sum = 0;
            while (index > 0) {
                sum += BIT[index];
                index -= index & (-index);
            }
            return sum;
        }
        public List<Integer> countSmaller(int[] nums) {
            LinkedList<Integer> result = new LinkedList<>();
            if (nums.length == 0) return result;
            int[] sorted = nums.clone();
            Arrays.sort(sorted);
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < sorted.length; i++) {
                map.put(sorted[i], i);
            }
            int[] BIT = new int[nums.length+1];
            for (int i = nums.length-1; i >= 0; i--) {
                result.addFirst(getSum(BIT, map.get(nums[i])-1));
                update(BIT, map.get(nums[i]), 1);
            }
            return result;
        }
    }
    //  Binary Indexed Tree, iterate according to sorted order and use original index (from the back) as BIT index:
    class Solution44{
        private void update(int[]BIT, int index, int val) {
            index++;
            while (index < BIT.length) {
                BIT[index] += val;
                index += index & (-index);
            }
        }
        private int getSum(int[]BIT, int index) {
            index++;
            int sum = 0;
            while (index > 0) {
                sum += BIT[index];
                index -= index & (-index);
            }
            return sum;
        }
        public List<Integer> countSmaller(int[] nums) {
            int[] sorted = nums.clone();
            Arrays.sort(sorted);
            HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                LinkedList<Integer> l = map.getOrDefault(nums[i], new LinkedList<>());
                l.add(i);
                map.put(nums[i], l);
            }
            int[] BIT = new int[nums.length+1], count = new int[nums.length];
            for (int i = 0; i < sorted.length; i++) {
                if (i > 0 && sorted[i] == sorted[i-1]) continue;
                LinkedList<Integer> list = map.get(sorted[i]);
                for (int j : list) {
                    count[j] = getSum(BIT, nums.length-1-j);
                }
                for (int j : list) {
                    update(BIT, nums.length-1-j, 1);
                }
            }
            LinkedList<Integer> result = new LinkedList<>();
            for (int c : count) result.add(c);
            return result;
        }
    }

    //  Binary Search Tree:
    class Solution55{
        private class TreeNode {
            int val, leftchildren;
            TreeNode left, right;
            public TreeNode (int v) {
                val = v;
                leftchildren = 0;
            }
        }
        private int insert(TreeNode root, int val) {
            int count = 0;
            while (true) {
                if (val <= root.val) {
                    root.leftchildren++;
                    if (root.left != null) root = root.left;
                    else {
                        root.left = new TreeNode(val);
                        break;
                    }
                } else {
                    count += root.leftchildren+1;
                    if (root.right != null) root = root.right;
                    else {
                        root.right = new TreeNode(val);
                        break;
                    }
                }
            }
            return count;
        }
        public List<Integer> countSmaller(int[] nums) {
            LinkedList<Integer> result = new LinkedList<>();
            if (nums.length == 0) return result;
            result.add(0);
            TreeNode root = new TreeNode(nums[nums.length-1]);
            for (int i = nums.length-2; i >= 0; i--) {
                result.addFirst(insert(root, nums[i]));
            }
            return result;
        }
    }
//-------------------------------------------------------------------------/////////////
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