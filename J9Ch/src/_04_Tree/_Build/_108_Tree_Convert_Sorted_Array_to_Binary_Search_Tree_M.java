package _04_Tree._Build;
import lib.TreeNode;
import java.util.*;
import org.junit.Test;

//  108. Convert Sorted Array to Binary Search Tree
//  https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
//  http://lintcode.com/zh-cn/problem/convert-sorted-array-to-binary-search-tree-with-minimal-height/
public class _108_Tree_Convert_Sorted_Array_to_Binary_Search_Tree_M {
    // jiuzhang
    private TreeNode buildTree1(int[] num, int start, int end) {
        if (start > end) {
            return null;
        }

        TreeNode node = new TreeNode(num[(start + end) / 2]);
        node.left = buildTree1(num, start, (start + end) / 2 - 1);
        node.right = buildTree1(num, (start + end) / 2 + 1, end);
        return node;
    }

    public TreeNode sortedArrayToBST1(int[] num) {
        if (num == null) {
            return null;
        }
        return buildTree1(num, 0, num.length - 1);
    }
//-------------------------------------------------------------------------////
    //time O(n) space O(logn)
    public TreeNode sortedArrayToBST2(int[] num) {
        if (num == null || num.length == 0) {
            return null;
        }
        return buildtree2(num, 0, num.length - 1);
    }
    private TreeNode buildtree2(int[] num, int start, int end){
        if(start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;

        TreeNode root = new TreeNode(num[mid]);
        root.left = buildtree2(num, start, mid - 1);
        root.right = buildtree2(num, mid + 1, end);

        return root;
    }

    @Test
    public void test02(){
        int[] arr = {1,3,5,6,7,8};
        TreeNode root = sortedArrayToBST2(arr);
        System.out.println("root: ");
        root.print();
    }

/////////////////////////////////////////////////////////////
    public TreeNode sortedArrayToBST3(int[] num) {
        if (num.length == 0) {
            return null;
        }
        TreeNode head = helper3(num, 0, num.length - 1);
        return head;
    }

    public TreeNode helper3(int[] num, int low, int high) {
        if (low > high) { // Done
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(num[mid]);
        node.left = helper3(num, low, mid - 1);
        node.right = helper3(num, mid + 1, high);
        return node;
    }
/////////////////////////////////////////////////////////////
    //A typical DFS problem using recursion.
    // Definition for binary tree
    public TreeNode sortedArrayToBST5(int[] num) {
        if (num.length == 0)
            return null;

        return sortedArrayToBST5(num, 0, num.length - 1);
    }

    public TreeNode sortedArrayToBST5(int[] num, int start, int end) {
        if (start > end)
            return null;

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(num[mid]);

        root.left = sortedArrayToBST5(num, start, mid - 1);
        root.right = sortedArrayToBST5(num, mid + 1, end);

        return root;
    }

/////////////////////////////////////////////////////////////
    //Java Iterative Solution
    /*
    I came up with the recursion solution first and tried to translate it into an iterative solution.
    It is very similar to doing a tree inorder traversal,
    I use three stacks - nodeStack stores the node I am going to process next,
    and leftIndexStack and rightIndexStack store the range where this node need to read from the nums.
     */
    public TreeNode sortedArrayToBST4(int[] nums) {

        int len = nums.length;
        if ( len == 0 ) { return null; }

        // 0 as a placeholder
        TreeNode head = new TreeNode(0);

        Deque<TreeNode> nodeStack       = new LinkedList<TreeNode>() {{ push(head);  }};
        Deque<Integer>  leftIndexStack  = new LinkedList<Integer>()  {{ push(0);     }};
        Deque<Integer>  rightIndexStack = new LinkedList<Integer>()  {{ push(len-1); }};

        while ( !nodeStack.isEmpty() ) {
            TreeNode currNode = nodeStack.pop();
            int left  = leftIndexStack.pop();
            int right = rightIndexStack.pop();
            int mid   = left + (right-left)/2; // avoid overflow
            currNode.val = nums[mid];
            if ( left <= mid-1 ) {
                currNode.left = new TreeNode(0);
                nodeStack.push(currNode.left);
                leftIndexStack.push(left);
                rightIndexStack.push(mid-1);
            }
            if ( mid+1 <= right ) {
                currNode.right = new TreeNode(0);
                nodeStack.push(currNode.right);
                leftIndexStack.push(mid+1);
                rightIndexStack.push(right);
            }
        }
        return head;
    }

//-------------------------------------------------------------------------
}
/*
把排序数组转换为高度最小的二叉搜索树

 描述
 笔记
 数据
 评测
给一个排序数组（从小到大），将其转换为一棵高度最小的排序二叉树。

 注意事项

There may exist multiple valid solutions, return any of them.

您在真实的面试中是否遇到过这个题？ Yes
样例
给出数组 [1,2,3,4,5,6,7], 返回

     4
   /   \
  2     6
 / \    / \
1   3  5   7
标签
Cracking The Coding Interview 递归 二叉树
相关题目
中等 排序列表转换为二分查找树
 */

//  Given an array where elements are sorted in ascending order, convert it to a height balanced BST.


