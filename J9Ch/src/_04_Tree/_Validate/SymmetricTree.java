package _04_Tree._Validate;

// Symmetric Tree

import lib.*;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
/*
LeetCode – Symmetric Tree (Java)

Problem

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:

    1
   / \
  2   2
   \   \
   3    3
 */
public class SymmetricTree {
	//recursion
	public boolean isSymetric_1 (TreeNode root) {
		if (root == null) return true;
		return isSymetric(root.left, root.right);
	}
	public boolean isSymetric (TreeNode one, TreeNode two) {
		//base case
		if (one == null || two == null) {
			if (one == two) return true;
			else return false;
		}

		if (one.val != two.val) {//curretn level
			return false;
		}else {//return left && right
			return isSymetric(one.left, two.right) &&
					isSymetric(one.right, two.left);
		}
		
	}
    @Test
    public void test01() {
        int[] arr = {1, 2, 2,3,3,3,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(isSymetric_1(root));
    }
    @Test
    public void test02() {
        int[] arr = {1, 2, 2,3,3,3,4};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(isSymetric_1(root));
    }

/////////////////////////////////////////////////////////

	//iteration, deque
	public boolean isSymetric_2(TreeNode root){
		if(root == null){
			return true;
		}
		Deque<TreeNode> deque = new LinkedList<>();
		deque.offer(root.left);
		deque.offer(root.right);

		while(!deque.isEmpty()){
			//left polled from first, right polled from last
			TreeNode left = deque.pollFirst();
			TreeNode right = deque.pollLast();

			//Tricky here: if list.add(null), list.isEmpty() == false
			if(left == null && right == null){
				continue;
			}
			if(left == null || right == null || left.val != right.val){
				return false;
			}

			//left offered from first, right offered from last
			deque.offerFirst(left.right);
			deque.offerFirst(left.left);
			deque.offerLast(right.left);
			deque.offerLast(right.right);
		}
		return true;
	}
    @Test
    public void test03() {
        int[] arr = {1, 2, 2,3,3,3,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(isSymetric_2(root));
    }
    @Test
    public void test04() {
        int[] arr = {1, 2, 2,3,3,3,4};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(isSymetric_2(root));
    }

/////////////////////////////////////////////////////////////////////////

    public boolean isSymmetric3(TreeNode root) {
        // Write your code here
        if (root == null) {
            return true;
        }
        return check(root.left, root.right);
    }

    private boolean check(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return check(root1.left, root2.right) && check(root1.right, root2.left);
    }
    @Test
    public void test05() {
        int[] arr = {1, 2, 2,3,3,3,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(isSymmetric3(root));
    }
    @Test
    public void test06() {
        int[] arr = {1, 2, 2,3,3,3,4};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(isSymmetric3(root));
    }
///////////////////////////////////////////////////////////////////////////////
        /*Java Solution - Recursion

    This problem can be solve by using a simple recursion. The key is finding the conditions that return false, such as value is not equal, only one node(left or right) has value.*/

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetric(root.left, root.right);
    }


    public boolean isSymmetric(TreeNode l, TreeNode r) {
        if (l == null && r == null) {
            return true;
        } else if (r == null || l == null) {
            return false;
        }

        if (l.val != r.val)
            return false;

        if (!isSymmetric(l.left, r.right))
            return false;
        if (!isSymmetric(l.right, r.left))
            return false;

        return true;
    }
/////////////////////////////////////////////////////////////////////////////

    public boolean isSymmetric11(TreeNode root) {
        // Write your code here
        if (root == null) {
            return true;
        }
        return check1(root.left, root.right);
    }

    private boolean check1(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return check1(root1.left, root2.right) && check1(root1.right, root2.left);
    }
/////////////////////////////////////////////////////////////////////////////




}

/*Given a binary tree, check whether it is a mirror of itself
 * (i.e., symmetric around its center).

Have you met this question in a real interview? Yes
Example
    1
   / \
  2   2
 / \ / \
3  4 4  3
is a symmetric binary tree.

    1
   / \
  2   2
   \   \
   3    3
is not a symmetric binary tree.

Challenge
Can you solve it both recursively and iteratively?

Tags
Binary Tree
Related Problems
Easy Identical Binary Tree 45 %
Easy Complete Binary Tree 25 %*/
