package _04_Tree._Build;

import lib.TreeNode;
import org.junit.Test;
import java.util.*;

//  106. Construct Binary Tree from Inorder and Postorder Traversal
//  https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
//  http://www.lintcode.com/zh-cn/problem/construct-binary-tree-from-inorder-and-postorder-traversal/

//9

/*
解法

本题在属于二叉树遍历的经典题目，已知二叉树的两个遍历序列构造二叉树，有如下性质：

若已知先序和中序，则可以构造出唯一的二叉树

若已知先序和后序，则可以构造出多颗不同的二叉树

若已知中序和后序，则可以构造出唯一的二叉树

本题中我们已知的条件为中序遍历和后序遍历，所以我们一定可以构造出唯一的二叉树。


我们先将整棵树看作根节点和两颗子树，则其两种遍历得到的序列为

             in order
+------------+------+-------------+
| left child | root | right child |
+------------+------+-------------+

            post order
+------------+-------------+------+
| left child | right child | root |
+------------+-------------+------+
可以肯定后序遍历序列中最后一个数一定是当前二叉树的根节点root。又因为二叉树不存在相同的数，我们可以找到root在中序遍历中位置p。

则我们可以分别找到两颗子树对应的中序和后序遍历：

左子树的中序 = inOrder[1 .. p - 1]
左子树的后序 = postOrder[1 .. p - 1]
右子树的中序 = inOrder[p + 1 .. n]
右子树的后序 = postOrder[p .. n - 1]
在此基础上我们就可以递归处理两颗子树。

当我们发现当前中序遍历和后序遍历长度都为 1 的时候，也就找到了叶子节点，此时我们开始回溯。
 */



/*
要知道如何构建二叉树，首先我们需要知道二叉树的几种遍历方式，譬如有如下的二叉树：

                1
        --------|-------
        2               3
    ----|----       ----|----
    4       5       6       7
前序遍历 1245367
中序遍历 4251637
后续遍历 4526731
具体到上面这一题，我们知道了一个二叉树的中序遍历以及后序遍历的结果，那么如何构建这颗二叉树呢？

仍然以上面那棵二叉树为例，我们可以发现，对于后序遍历来说，最后一个元素一定是根节点，也就是1。然后我们在中序遍历的结果里面找到1所在的位置，那么它的左半部分就是其左子树，有半部分就是其右子树。

我们将中序遍历左半部分425取出，同时发现后序遍历的结果也在相应的位置上面，只是顺序稍微不一样，也就是452。我们可以发现，后序遍历中的2就是该子树的根节点。

上面说到了左子树，对于右子树，我们取出637，同时发现后序遍历中对应的数据偏移了一格，并且顺序也不一样，为673。而3就是这颗右子树的根节点。

重复上述过程，通过后续遍历找到根节点，然后在中序遍历数据中根据根节点拆分成两个部分，同时将对应的后序遍历的数据也拆分成两个部分，重复递归，就可以得到整个二叉树了。
 */
public class _106_Tree_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal_M {
    //https://www.tianmaying.com/tutorial/LC106
//------------------------------------------------------------------------
    //1
    // 9Ch
	private int findPosition(int[] arr, int start, int end, int key) {
        int i;
        for (i = start; i <= end; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }
    /*
                1
        --------|-------
        2               3
    ----|----       ----|----
    4       5       6       7
     */
    //中序遍历 425 1 637
    //后续遍历 452 673 1
    private TreeNode myBuildTree(int[] inorder, int instart, int inend,
            int[] postorder, int poststart, int postend) {
        if (instart > inend) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postend]);
        int position = findPosition(inorder, instart, inend, postorder[postend]);

        root.left = myBuildTree(inorder, instart, position - 1,             //425
        postorder, poststart, poststart + position - instart - 1);       //452

        root.right = myBuildTree(inorder, position + 1, inend,            //637
        postorder, poststart + position - instart, postend - 1);//673

        return root;
    }

    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length) {
            return null;
        }
        return myBuildTree(inorder, 0, inorder.length - 1, 
        					   postorder, 0, postorder.length - 1);
    }

    @Test
    public void test01(){
        int[] postorder = {1,3,2};
        int[] inorder = {1,2,3};
        buildTree1(postorder, inorder).print();
    }

//------------------------------------------------------------------------
    //2
    //My recursive Java code with O(n) time and O(n) space
    /*
    The the basic idea is to take the last element in postorder array as the root, find the position of the root in the inorder array; then locate the range for left sub-tree and right sub-tree and do recursion. Use a HashMap to record the index of root in the inorder array.
     */
    public TreeNode buildTreePostIn2(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length)
            return null;
        HashMap<Integer, Integer> hm = new HashMap<Integer,Integer>();
        for (int i=0;i<inorder.length;++i)
            hm.put(inorder[i], i);
        return buildTreePostIn(inorder, 0, inorder.length-1, postorder, 0,
                postorder.length-1,hm);
    }

    private TreeNode buildTreePostIn(int[] inorder, int is, int ie, int[] postorder, int ps, int pe,
                                     HashMap<Integer,Integer> hm){
        if (ps>pe || is>ie) return null;
        TreeNode root = new TreeNode(postorder[pe]);
        int ri = hm.get(postorder[pe]);
        TreeNode leftchild = buildTreePostIn(inorder, is, ri-1, postorder, ps, ps+ri-is-1, hm);
        TreeNode rightchild = buildTreePostIn(inorder,ri+1, ie, postorder, ps+ri-is, pe-1, hm);
        root.left = leftchild;
        root.right = rightchild;
        return root;
    }
//------------------------------------------------------------------------
    //3
    // Java iterative solution with explanation
    public TreeNode buildTree3(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) return null;
        int ip = inorder.length - 1;
        int pp = postorder.length - 1;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode prev = null;
        TreeNode root = new TreeNode(postorder[pp]);
        stack.push(root);
        pp--;

        while (pp >= 0) {
            while (!stack.isEmpty() && stack.peek().val == inorder[ip]) {
                prev = stack.pop();
                ip--;
            }
            TreeNode newNode = new TreeNode(postorder[pp]);
            if (prev != null) {
                prev.left = newNode;
            } else if (!stack.isEmpty()) {
                TreeNode currTop = stack.peek();
                currTop.right = newNode;
            }
            stack.push(newNode);
            prev = null;
            pp--;
        }

        return root;
    }
//------------------------------------------------------------------------
    //4
    public TreeNode buildTree4(int[] inorder, int[] postorder) {
        int inStart = 0;
        int inEnd = inorder.length - 1;
        int postStart = 0;
        int postEnd = postorder.length - 1;

        return buildTree(inorder, inStart, inEnd, postorder, postStart, postEnd);
    }

    public TreeNode buildTree(int[] inorder, int inStart, int inEnd,
                              int[] postorder, int postStart, int postEnd) {

        if (inStart > inEnd || postStart > postEnd)
            return null;

        int rootValue = postorder[postEnd];
        TreeNode root = new TreeNode(rootValue);

        int k = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootValue) {
                k = i;
                break;
            }
        }

        root.left = buildTree(inorder, inStart, k - 1,
                postorder, postStart,postStart + k - (inStart + 1));

        // Becuase k is not the length, it it need to -(inStart+1) to
        // get the length
        root.right = buildTree(inorder, k + 1, inEnd,
                postorder, postStart + k- inStart, postEnd - 1);

        // postStart+k-inStart = postStart+k-(inStart+1) +1

        return root;
    }


//-------------------------------------------------------------------------
    //5
    public TreeNode buildTreePostIn5(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length)
            return null;
        HashMap<Integer, Integer> hm = new HashMap<Integer,Integer>();
        for (int i=0;i<inorder.length;++i)
            hm.put(inorder[i], i);
        return buildTreePostIn5(inorder, 0, inorder.length-1, postorder, 0,
                postorder.length-1,hm);
    }

    private TreeNode buildTreePostIn5(int[] inorder, int is, int ie, int[] postorder, int ps, int pe,
                                     HashMap<Integer,Integer> hm){
        if (ps>pe || is>ie) return null;
        TreeNode root = new TreeNode(postorder[pe]);
        int ri = hm.get(postorder[pe]);
        TreeNode leftchild = buildTreePostIn5(inorder, is, ri-1, postorder, ps, ps+ri-is-1, hm);
        TreeNode rightchild = buildTreePostIn5(inorder,ri+1, ie, postorder, ps+ri-is, pe-1, hm);
        root.left = leftchild;
        root.right = rightchild;
        return root;
    }


//-------------------------------------------------------------------------
    //6
    //Java iterative solution with explanation
    /*
    This is my iterative solution, think about "Constructing Binary Tree from inorder and preorder array", the idea is quite similar. Instead of scanning the preorder array from beginning to end and using inorder array as a kind of mark, in this question, the key point is to scanning the postorder array from end to beginning and also use inorder array from end to beginning as a mark because the logic is more clear in this way. The core idea is: Starting from the last element of the postorder and inorder array, we put elements from postorder array to a stack and each one is the right child of the last one until an element in postorder array is equal to the element on the inorder array. Then, we pop as many as elements we can from the stack and decrease the mark in inorder array until the peek() element is not equal to the mark value or the stack is empty. Then, the new element that we are gonna scan from postorder array is the left child of the last element we have popped out from the stack.
     */
    public TreeNode buildTree6(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) return null;
        int ip = inorder.length - 1;
        int pp = postorder.length - 1;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode prev = null;
        TreeNode root = new TreeNode(postorder[pp]);
        stack.push(root);
        pp--;

        while (pp >= 0) {
            while (!stack.isEmpty() && stack.peek().val == inorder[ip]) {
                prev = stack.pop();
                ip--;
            }
            TreeNode newNode = new TreeNode(postorder[pp]);
            if (prev != null) {
                prev.left = newNode;
            } else if (!stack.isEmpty()) {
                TreeNode currTop = stack.peek();
                currTop.right = newNode;
            }
            stack.push(newNode);
            prev = null;
            pp--;
        }

        return root;
    }



//-------------------------------------------------------------------------
    //7
    //Simple and clean Java solution with comments, recursive.
    public TreeNode buildTree7(int[] inorder, int[] postorder) {
        return buildTree7(inorder, inorder.length-1, 0,
                postorder, postorder.length-1);
    }

    private TreeNode buildTree7(int[] inorder, int inStart, int inEnd, int[] postorder,
                               int postStart) {
        if (postStart < 0 || inStart < inEnd)
            return null;

        //The last element in postorder is the root.
        TreeNode root = new TreeNode(postorder[postStart]);

        //find the index of the root from inorder. Iterating from the end.
        int rIndex = inStart;
        for (int i = inStart; i >= inEnd; i--) {
            if (inorder[i] == postorder[postStart]) {
                rIndex = i;
                break;
            }
        }
        //build right and left subtrees. Again, scanning from the end to find the sections.
        root.right = buildTree7(inorder, inStart, rIndex + 1,
                postorder, postStart-1);
        root.left = buildTree7(inorder, rIndex - 1, inEnd,
                postorder, postStart - (inStart - rIndex) -1);
        return root;
    }

//-------------------------------------------------------------------------
    //8
    //My JAVA recursive answer, BEAT 92.9%, 2ms
    public TreeNode buildTree8(int[] inorder, int[] postorder) {
        return build8(inorder,inorder.length-1,0,postorder,postorder.length-1);
    }

    public TreeNode build8(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart){
        if(inEnd > inStart){
            return null;
        }
        TreeNode root = new TreeNode(postorder[postStart]);
        if(inEnd == inStart){
            return root;
        }
        int index = 0;
        // find the index in inorder:
        for(int i = inStart; i >= inEnd; i--){
            if(inorder[i] == root.val){
                index = i;
                break;
            }
        }

        root.right = build8(inorder,inStart,index+1,postorder,postStart-1);
        root.left = build8(inorder,index-1,inEnd,postorder,postStart-(inStart-index)-1);

        return root;
    }



//-------------------------------------------------------------------------
    //9
    //Concise recursive Java code by making slight modification to the previous problem.
    //Code for Problem 106:
    public  TreeNode buildTree9(int[] inorder, int[] postorder) {
        return helper9(postorder, postorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    public  TreeNode helper9(int[] postorder, int postStart, int[] inorder, int inStart, int inEnd) {
        if (postStart < 0 || inStart > inEnd)
            return null;
        TreeNode root = new TreeNode(postorder[postStart]);

        int target = inStart;
        while (inorder[target] != postorder[postStart]) target++;

        root.left = helper9(postorder, postStart - inEnd + target - 1,
                inorder, inStart, target - 1);
        root.right = helper9(postorder, postStart - 1,
                inorder, target + 1, inEnd);

        return root;
    }


//------------------------------------------------------------------------
}
/*
中序遍历和后序遍历树构造二叉树

 描述
 笔记
 数据
 评测
根据中序遍历和后序遍历树构造二叉树

 注意事项

你可以假设树中不存在相同数值的节点

样例
给出树的中序遍历： [1,2,3] 和后序遍历： [1,3,2]

返回如下的树：

  2

 /  \

1    3

标签
二叉树
 */


/*Given inorder and postorder traversal of a tree, construct the binary tree.

 Notice

You may assume that duplicates do not exist in the tree.

Have you met this question in a real interview? Yes
Example
Given inorder [1,2,3] and postorder [1,3,2], return a tree:

  2
 / \
1   3
Tags 
Binary Tree*/