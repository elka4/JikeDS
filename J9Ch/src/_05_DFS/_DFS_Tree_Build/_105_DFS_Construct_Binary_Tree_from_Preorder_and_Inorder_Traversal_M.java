package _05_DFS._DFS_Tree_Build;
import lib.TreeNode;
import org.junit.Test;
import java.util.*;
import java.util.Map;

//  105. Construct Binary Tree from Preorder and Inorder Traversal
//  https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
//  http://www.lintcode.com/zh-cn/problem/construct-binary-tree-from-preorder-and-inorder-traversal/
public class _105_DFS_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal_M {
// jiuzhang
	private int findPosition(int[] arr, int start, int end, int key) {
	    int i;
	    for (i = start; i <= end; i++) {
	        if (arr[i] == key) {
	            return i;
	        }
	    }
	    return -1;
	}

	private TreeNode myBuildTree(int[] inorder, int instart, int inend,
	        int[] preorder, int prestart, int preend) {
	    if (instart > inend) {
	        return null;
	    }

	    TreeNode root = new TreeNode(preorder[prestart]);

	    int position = findPosition(inorder, instart, inend, preorder[prestart]);

	    root.left = myBuildTree(inorder, instart, position - 1,
	            preorder, prestart + 1, prestart + position - instart);

	    root.right = myBuildTree(inorder, position + 1, inend,
	            preorder, position - inend + preend + 1, preend);

	    return root;
	}

	public TreeNode buildTree1(int[] preorder, int[] inorder) {
	    if (inorder.length != preorder.length) {
	        return null;
	    }
	    return myBuildTree(inorder, 0, inorder.length - 1,
                           preorder, 0, preorder.length - 1);
	}

	@Test
    public void test01(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree1(preorder, inorder).print();
    }

//----------------------------------------------------------------------------
    //My Accepted Java Solution
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0; // Index of current root in inorder

        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }
        root.left = helper(preStart + 1,
                inStart, inIndex - 1, preorder, inorder);

        root.right = helper(preStart + inIndex - inStart + 1,
                inIndex + 1, inEnd, preorder, inorder);

        return root;
    }

    @Test
    public void test02(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree2(preorder, inorder).print();
    }
//----------------------------------------------------------------------------
    // 5ms Java Clean Solution with Caching
    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();

        for(int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        TreeNode root = buildTree3(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1, inMap);
        return root;
    }

    public TreeNode buildTree3(int[] preorder, int preStart, int preEnd, int[] inorder,
                              int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if(preStart > preEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = buildTree3(preorder, preStart + 1, preStart + numsLeft,
                inorder, inStart, inRoot - 1, inMap);

        root.right = buildTree3(preorder, preStart + numsLeft + 1, preEnd, inorder,
                inRoot + 1, inEnd, inMap);

        return root;
    }

    @Test
    public void test03(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree2(preorder, inorder).print();
    }
//--------------------------------------------------------------------------------
    public TreeNode buildTree4(int[] preorder, int[] inorder) {
        int preStart = 0;
        int preEnd = preorder.length-1;
        int inStart = 0;
        int inEnd = inorder.length-1;

        return construct(preorder, preStart, preEnd, inorder, inStart, inEnd);
    }

    public TreeNode construct(int[] preorder, int preStart, int preEnd,
                              int[] inorder, int inStart, int inEnd){

        if(preStart>preEnd||inStart>inEnd){
            return null;
        }

        int val = preorder[preStart];
        TreeNode p = new TreeNode(val);

        //find parent element index from inorder
        int k=0;
        for(int i=0; i<inorder.length; i++){
            if(val == inorder[i]){
                k=i;
                break;
            }
        }

        p.left = construct(preorder, preStart+1,
                preStart+(k-inStart), inorder, inStart, k-1);

        p.right= construct(preorder, preStart+(k-inStart)+1,
                preEnd, inorder, k+1 , inEnd);

        return p;
    }

    @Test
    public void test04(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree4(preorder, inorder).print();
    }


//-------------------------------------------------------------------------

    //Concise recursive Java code by making slight modification to the previous problem.
    public  TreeNode buildTree5(int[] preorder, int[] inorder) {
        return helper(preorder, 0, inorder, 0, inorder.length - 1);
    }

    public TreeNode helper(int[] preorder, int preStart, int[] inorder, int inStart, int inEnd) {
        if (preStart > preorder.length - 1 || inStart > inEnd)
            return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int target = inStart;
        while (inorder[target] != preorder[preStart]) target++;

        root.left = helper(preorder, preStart + 1, inorder, inStart, target - 1);

        root.right = helper(preorder, preStart + target - inStart + 1,
                inorder, target + 1, inEnd);

        return root;
    }

    @Test
    public void test05(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree5(preorder, inorder).print();
    }
//-------------------------------------------------------------------------
    /*
        My Accepted Java Solution
        Hi guys, this is my Java solution. I read this post, which is very helpful.

        The basic idea is here:
        Say we have 2 arrays, PRE and IN.
        Preorder traversing implies that PRE[0] is the root node.
        Then we can find this PRE[0] in IN, say it's IN[5].
        Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the left side,
        IN[6] to the end is on the right side.
        Recursively doing this on subarrays, we can build a tree out of it :)

        Hope this helps.
     */
    public TreeNode buildTree6(int[] preorder, int[] inorder) {
        return helper6(0, 0, inorder.length - 1, preorder, inorder);
    }

    public TreeNode helper6(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0; // Index of current root in inorder
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }
        root.left = helper6(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = helper6(preStart + inIndex - inStart + 1, inIndex + 1,
                inEnd, preorder, inorder);
        return root;
    }

    @Test
    public void test06(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree6(preorder, inorder).print();
    }

//-------------------------------------------------------------------------

    /*
        5ms Java Clean Solution with Caching
        In this questions, most of people just loop through inorder[] to find the root.
        However, by caching positions of inorder[] indices using a HashMap,
        the run time can drop from 20ms to 5ms.

        Here is my 5ms AC solution:
     */

    public TreeNode buildTree7(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();

        for(int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        TreeNode root = buildTree7(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
        return root;
    }

    public TreeNode buildTree7(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if(preStart > preEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = buildTree7(preorder, preStart + 1,
                preStart + numsLeft, inorder, inStart, inRoot - 1, inMap);
        root.right = buildTree7(preorder, preStart + numsLeft + 1,
                preEnd, inorder, inRoot + 1, inEnd, inMap);

        return root;
    }

    @Test
    public void test07(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree7(preorder, inorder).print();
    }


//-------------------------------------------------------------------------

    //Here is the iterative solution in Java
    public TreeNode buildTree8(int[] preorder, int[] inorder) {
        if (inorder.length==0) return null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        stack.push(root);
        int i=0, j=0;
        TreeNode node = null;
        TreeNode cur = root;
        while (j<inorder.length){
            if (stack.peek().val == inorder[j]){
                node = stack.pop();
                j++;
            }
            else if (node!=null){
                cur = new TreeNode(preorder[i]);
                node.right = cur;
                node=null;
                stack.push(cur);
                i++;
            }
            else {
                cur = new TreeNode(preorder[i]);
                stack.peek().left = cur;
                stack.push(cur);
                i++;
            }
        }
        return root.left;
    }

    @Test
    public void test08(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree8(preorder, inorder).print();
    }

//-------------------------------------------------------------------------

    //Concise Java Recursive Solution
    public TreeNode buildTree9(int[] preorder, int[] inorder) {
        if(preorder==null || inorder==null || inorder.length==0 || preorder.length==0)
            return null;
        TreeNode root = new TreeNode(preorder[0]);
        if(preorder.length==1) return root;
        int breakindex = -1;
        for(int i=0;i<inorder.length;i++) {
            if(inorder[i]==preorder[0]) {
                breakindex=i;
                break;
            }
        }
        int[] subleftpre  = Arrays.copyOfRange(preorder,1,breakindex+1);
        int[] subleftin   = Arrays.copyOfRange(inorder,0,breakindex);
        int[] subrightpre = Arrays.copyOfRange(preorder,breakindex+1,preorder.length);
        int[] subrightin  = Arrays.copyOfRange(inorder,breakindex+1,inorder.length);

        root.left  = buildTree9(subleftpre,subleftin);
        root.right = buildTree9(subrightpre,subrightin);
        return root;
    }

    @Test
    public void test09(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree9(preorder, inorder).print();
    }

//-------------------------------------------------------------------------
    //Recursive solution in Java
    //This question is similar to the one using postorder and inorder arrays.
    // Once one notices that the first element of preorder is the root node,
    // the rest is fairly straightforward.
    public TreeNode buildTree10(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        return buildTree10(preorder, inorder, 0, 0, inorder.length - 1);
    }

    private TreeNode buildTree10(int[] preorder, int[] inorder,
                                 int preorderIndex, int start, int end) {
        if (start > end) return null;
        TreeNode node = new TreeNode(preorder[preorderIndex]);
        int inorderIndex = findInorderIndex(inorder, start, end, preorder[preorderIndex]);
        int leftTreeSize = inorderIndex - start;
        int rightTreeSize = end - inorderIndex;
        node.left = buildTree10(preorder, inorder,
                preorderIndex + 1, start, inorderIndex - 1);
        node.right = buildTree10(preorder, inorder,
                preorderIndex + leftTreeSize + 1, inorderIndex + 1, end);
        return node;
    }

    private int findInorderIndex(int[] inorder, int start, int end, int key) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == key) return i;
        }
        return -1;
    }

    @Test
    public void test10(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree10(preorder, inorder).print();
    }

//--------------------------------------------------------------------------------
}
/*
前序遍历和中序遍历树构造二叉树

 描述
 笔记
 数据
 评测
根据前序遍历和中序遍历树构造二叉树.

 注意事项

你可以假设树中不存在相同数值的节点

样例
给出中序遍历：[1,2,3]和前序遍历：[2,1,3]. 返回如下的树:

  2
 / \
1   3
标签
二叉树
 */

/*Given preorder and inorder traversal of a tree, construct the binary tree.

 Notice

You may assume that duplicates do not exist in the tree.

Have you met this question in a real interview? Yes
Example
Given in-order [1,2,3] and pre-order [2,1,3], return a tree:

  2
 / \
1   3
Tags
Binary Tree*/

/*
LeetCode – Construct Binary Tree from Preorder and Inorder Traversal (Java)

Given preorder and inorder traversal of a tree, construct the binary tree.

Analysis

Consider the following example:

in-order:   4 2 5 (1) 6 7 3 8
pre-order: (1) 2 4 5  3 7 6 8
From the pre-order array, we know that first element is the root. We can find the root in in-order array. Then we can identify the left and right sub-trees of the root from in-order array.

Using the length of left sub-tree, we can identify left and right sub-trees in pre-order array. Recursively, we can build up the tree.

For this example, the constructed tree is:
 */