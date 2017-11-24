package _04_Tree._BST;
import java.util.*;

//  493. Reverse Pairs
//  https://leetcode.com/problems/reverse-pairs/description/
//  http://www.lintcode.com/zh-cn/problem/reverse-pairs/
public class _493_Reverse_Pairs {
    //https://leetcode.com/problems/reverse-pairs/discuss/
    //General principles behind problems similar to "Reverse Pairs"

    //I -- Sequential recurrence relation
        class Node {
            int val, cnt;
            Node left, right;

            Node(int val) {
                this.val = val;
                this.cnt = 1;
            }
        }
        private int search(Node root, long val) {
            if (root == null) {
                return 0;
            } else if (val == root.val) {
                return root.cnt;
            } else if (val < root.val) {
                return root.cnt + search(root.left, val);
            } else {
                return search(root.right, val);
            }
        }

        private Node insert(Node root, int val) {
            if (root == null) {
                root = new Node(val);
            } else if (val == root.val) {
                root.cnt++;
            } else if (val < root.val) {
                root.left = insert(root.left, val);
            } else {
                root.cnt++;
                root.right = insert(root.right, val);
            }

            return root;
        }
        public int reversePairs1(int[] nums) {
            int res = 0;
            Node root = null;

            for (int ele : nums) {
                res += search(root, 2L * ele + 1);
                root = insert(root, ele);
            }

            return res;
        }

//-------------------------------------------------------------------------//
    //  2. BIT-based solution
    private int search(int[] bit, int i) {
        int sum = 0;

        while (i < bit.length) {
            sum += bit[i];
            i += i & -i;
        }

        return sum;
    }

    private void insert(int[] bit, int i) {
        while (i > 0) {
            bit[i] += 1;
            i -= i & -i;
        }
    }
    public int reversePairs2(int[] nums) {
        int res = 0;
        int[] copy = Arrays.copyOf(nums, nums.length);
        int[] bit = new int[copy.length + 1];

        Arrays.sort(copy);

        for (int ele : nums) {
            res += search(bit, index(copy, 2L * ele + 1));
            insert(bit, index(copy, ele));
        }

        return res;
    }

    private int index(int[] arr, long val) {
        int l = 0, r = arr.length - 1, m = 0;

        while (l <= r) {
            m = l + ((r - l) >> 1);

            if (arr[m] >= val) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return l + 1;
    }


//-------------------------------------------------------------------------//
    //3
    //II -- Partition recurrence relation
    public int reversePairs3(int[] nums) {

        return reversePairsSub(nums, 0, nums.length - 1);
    }

    private int reversePairsSub(int[] nums, int l, int r) {
        if (l >= r) return 0;

        int m = l + ((r - l) >> 1);
        int res = reversePairsSub(nums, l, m) + reversePairsSub(nums, m + 1, r);

        int i = l, j = m + 1, k = 0, p = m + 1;
        int[] merge = new int[r - l + 1];

        while (i <= m) {
            while (p <= r && nums[i] > l * nums[p]) p++;
            res += p - (m + 1);

            while (j <= r && nums[i] >= nums[j]) merge[k++] = nums[j++];
            merge[k++] = nums[i++];
        }

        while (j <= r) merge[k++] = nums[j++];

        System.arraycopy(merge, 0, nums, l, merge.length);

        return res;
    }

//------------------------------------------------------------------------------/////////
    //4
    //Very Short and Clear MergeSort & BST Java Solutions
   /* MergeSort

    Explanation: In each round, we divide our array into two parts and sort them. So after "int cnt = mergeSort(nums, s, mid) + mergeSort(nums, mid+1, e); ", the left part and the right part are sorted and now our only job is to count how many pairs of number (leftPart[i], rightPart[j]) satisfies leftPart[i] <= 2*rightPart[j].
    For example,
    left: 4 6 8 right: 1 2 3
    so we use two pointers to travel left and right parts. For each leftPart[i], if j<=e && nums[i]/2.0 > nums[j], we just continue to move j to the end, to increase rightPart[j], until it is valid. Like in our example, left's 4 can match 1 and 2; left's 6 can match 1, 2, 3, and left's 8 can match 1, 2, 3. So in this particular round, there are 8 pairs found, so we increases our total by 8.*/

    public int reversePairs4(int[] nums) {

        return mergeSort4(nums, 0, nums.length-1);
    }
    private int mergeSort4(int[] nums, int s, int e){
        if(s>=e) return 0;
        int mid = s + (e-s)/2;
        int cnt = mergeSort4(nums, s, mid) + mergeSort4(nums, mid+1, e);
        for(int i = s, j = mid+1; i<=mid; i++){
            while(j<=e && nums[i]/2.0 > nums[j]) j++;
            cnt += j-(mid+1);
        }
        Arrays.sort(nums, s, e+1);
        return cnt;
    }
//------------------------------------------------------------------------------/////////
//5
/*    Or:
    Because left part and right part are sorted, you can replace the Arrays.sort() part with a actual merge sort process. The previous version is easy to write, while this one is faster.*/
    int[] helper;
    public int reversePairs(int[] nums) {
        this.helper = new int[nums.length];
        return mergeSort5(nums, 0, nums.length-1);
    }
    private int mergeSort5(int[] nums, int s, int e){
        if(s>=e) return 0;
        int mid = s + (e-s)/2;
        int cnt = mergeSort5(nums, s, mid) + mergeSort5(nums, mid+1, e);
        for(int i = s, j = mid+1; i<=mid; i++){
            while(j<=e && nums[i]/2.0 > nums[j]) j++;
            cnt += j-(mid+1);
        }
        //Arrays.sort(nums, s, e+1);
        myMerge(nums, s, mid, e);
        return cnt;
    }

    private void myMerge(int[] nums, int s, int mid, int e){
        for(int i = s; i<=e; i++) helper[i] = nums[i];
        int p1 = s;//pointer for left part
        int p2 = mid+1;//pointer for rigth part
        int i = s;//pointer for sorted array
        while(p1<=mid || p2<=e){
            if(p1>mid || (p2<=e && helper[p1] >= helper[p2])){
                nums[i++] = helper[p2++];
            }else{
                nums[i++] = helper[p1++];
            }
        }
    }
//------------------------------------------------------------------------------/////////
//6
/*    BST
    BST solution is no longer acceptable, because it's performance can be very bad, O(n^2) actually, for extreme cases like [1,2,3,4......49999], due to the its unbalance, but I am still providing it below just FYI.
    We build the Binary Search Tree from right to left, and at the same time, search the partially built tree with nums[i]/2.0. The code below should be clear enough.*/

    //    Similar to this https://leetcode.com/problems/count-of-smaller-numbers-after-self/. But the main difference is: here, the number to add and the number to search are different (add nums[i], but search nums[i]/2.0), so not a good idea to combine build and search together.

    public int reversePairs6(int[] nums) {
        Node6 root = null;
        int[] cnt = new int[1];
        for(int i = nums.length-1; i>=0; i--){
            search(cnt, root, nums[i]/2.0);//search and count the partially built tree
            root = build(nums[i], root);//add nums[i] to BST
        }
        return cnt[0];
    }

    private void search(int[] cnt, Node6 node, double target){
        if(node==null) return;
        else if(target == node.val) cnt[0] += node.less;
        else if(target < node.val) search(cnt, node.left, target);
        else{
            cnt[0]+=node.less + node.same;
            search(cnt, node.right, target);
        }
    }

    private Node6 build(int val, Node6 n){
        if(n==null) return new Node6(val);
        else if(val == n.val) n.same+=1;
        else if(val > n.val) n.right = build(val, n.right);
        else{
            n.less += 1;
            n.left = build(val, n.left);
        }
        return n;
    }

    class Node6{
        int val, less = 0, same = 1;//less: number of nodes that less than this node.val
        Node6 left, right;
        public Node6(int v){
            this.val = v;
        }
    }

//------------------------------------------------------------------------------/////////
}
/*
在数组中的两个数字如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。给你一个数组，求出这个数组中逆序对的总数。
概括：如果a[i] > a[j] 且 i < j， a[i] 和 a[j] 构成一个逆序对。

样例
序列 [2, 4, 1, 3, 5] 中，有 3 个逆序对 (2, 1), (4, 1), (4, 3)，则返回 3 。

标签
数组 归并排序
 */

/*
Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.
 */