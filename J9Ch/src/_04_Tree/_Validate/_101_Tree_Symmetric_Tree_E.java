package _04_Tree._Validate;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

//  101. Symmetric Tree
//  https://leetcode.com/problems/symmetric-tree/description/
//
public class _101_Tree_Symmetric_Tree_E {

///////////////////////////////////////////////////////////////////////
    public boolean isSymmetric(TreeNode root) {

        return root==null || isSymmetricHelp(root.left, root.right);
    }
    private boolean isSymmetricHelp(TreeNode left, TreeNode right){ if(left==null || right==null)
        return left==right; if(left.val!=right.val)
        return false;
        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right,
                right.left);
    }
///////////////////////////////////////////////////////////////////////

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

    This problem can be solve by using a simple recursion.
    The key is finding the conditions that return false, such as value is not equal,
    only one node(left or right) has value.*/

    public boolean isSymmetric99(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetric99(root.left, root.right);
    }


    public boolean isSymmetric99(TreeNode l, TreeNode r) {
        if (l == null && r == null) {
            return true;
        } else if (r == null || l == null) {
            return false;
        }

        if (l.val != r.val)
            return false;

        if (!isSymmetric99(l.left, r.right))
            return false;
        if (!isSymmetric99(l.right, r.left))
            return false;

        return true;
    }
///////////////////////////////////////////////////////////////////////

    public boolean isSymmetric2(TreeNode root) {
        if(root==null)  return true;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode left, right;
        if(root.left!=null){
            if(root.right==null) return false;
            stack.push(root.left);
            stack.push(root.right);
        }
        else if(root.right!=null){
            return false;
        }

        while(!stack.empty()){
            if(stack.size()%2!=0)   return false;
            right = stack.pop();
            left = stack.pop();
            if(right.val!=left.val) return false;

            if(left.left!=null){
                if(right.right==null)   return false;
                stack.push(left.left);
                stack.push(right.right);
            }
            else if(right.right!=null){
                return false;
            }

            if(left.right!=null){
                if(right.left==null)   return false;
                stack.push(left.right);
                stack.push(right.left);
            }
            else if(right.left!=null){
                return false;
            }
        }

        return true;
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




/////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////
}
/*

 */


/*
LeetCode – Symmetric Tree (Java)

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
 */

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
