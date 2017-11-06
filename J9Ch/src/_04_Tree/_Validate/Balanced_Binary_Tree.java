package _04_Tree._Validate;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;
//Version 2: without ResultType
//coding style不好，不鼓励使用

public class Balanced_Binary_Tree {
	public boolean isBalanced(TreeNode root) {
		//这定义太绕了
	   return maxDepth(root) != -1;
	}
	
	//用－1表示不平衡，用其他数字表示高度，这定义太绕了
	private int maxDepth(TreeNode root) {
	   if (root == null) {
	       return 0;
	   }
	
	   int left = maxDepth(root.left);
	   int right = maxDepth(root.right);
	   if (left == -1 || right == -1 || Math.abs(left-right) > 1) {
	       return -1;
	   }
	   return Math.max(left, right) + 1;
	}

    /*
A)  3            B)    3
   / \                  \
  9  20                 20
    /  \                / \
   15   7              15  7
 */
    @Test
    public void test03(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(isBalanced(root));
    }
    @Test
    public void test04(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        //root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(isBalanced(root));
    }
    @Test
    public void test05(){
        TreeNode root = new TreeNode(3);
        root.setRightChild(new TreeNode(20));
        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(isBalanced(root));
    }
////////////////////////////////////////////////////////////////////////////////

//Version 1: with ResultType
//为了包含两个信息
    class ResultType {
        public boolean isBalanced;
        public int maxDepth;
        public ResultType(boolean isBalanced, int maxDepth) {
            this.isBalanced = isBalanced;
            this.maxDepth = maxDepth;
        }
    }
    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced2(TreeNode root) {
        return helper(root).isBalanced;
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(true, 0);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        root.print();// I added

        // subtree not balance
        if (!left.isBalanced || !right.isBalanced) {
            return new ResultType(false, -1);
        }

        // root not balance  能走到这一步说明subtree都是balanced
        if (Math.abs(left.maxDepth - right.maxDepth) > 1) {
            return new ResultType(false, -1);
        }

        return new ResultType(true,
                Math.max(left.maxDepth, right.maxDepth) + 1);
    }

    @Test
    public void test01(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(isBalanced2(root));
    }

    @Test
    public void test02(){
        TreeNode root = new TreeNode(3);
        root.setRightChild(new TreeNode(20));
        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(isBalanced2(root));
    }
////////////////////////////////////////////////////////////////////////////

    //Devide and Conquer
        public boolean isBalanced3(TreeNode root) {
            if (root == null)
                return true;

            if (getHeight(root) == -1)
                return false;

            return true;
        }

        public int getHeight(TreeNode root) {
            if (root == null)
                return 0;

            int left = getHeight(root.left);
            int right = getHeight(root.right);

            if (left == -1 || right == -1)
                return -1;

            if (Math.abs(left - right) > 1) {
                return -1;
            }

            return Math.max(left, right) + 1;

        }

        @Test
        public void test(){
            int[] arr = {1,2,3,4,5,6,7};
            TreeNode root = TreeNode.createMinimalBST(arr);
            root.print();

            System.out.println(isBalanced(root));
        }

////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////
}

/*
 * Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined
as a binary tree in which the depth of the two subtrees of
every node never differ by more than 1.

Have you met this question in a real interview? Yes
Example
Given binary tree A = {3,9,20,#,#,15,7}, B = {3,#,20,15,7}

A)  3            B)    3
   / \                  \
  9  20                 20
    /  \                / \
   15   7              15  7
The binary tree A is a height-balanced binary tree, but B is not.

Tags
Binary Search Divide and Conquer Recursion
Related Problems
Easy Complete Binary Tree 25 %
Medium Validate Binary Search Tree 21 %
 * */

