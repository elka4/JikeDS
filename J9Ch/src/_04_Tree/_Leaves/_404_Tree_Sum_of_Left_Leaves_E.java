package _04_Tree._Leaves;
import lib.TreeNode;
import java.util.*;

//  404. Sum of Left Leaves
//  https://leetcode.com/problems/sum-of-left-leaves/
public class _404_Tree_Sum_of_Left_Leaves_E {
        public int sumOfLeftLeaves1(TreeNode root) {
            if(root == null || root.left == null && root.right == null) return 0;

            int res = 0;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while(!queue.isEmpty()) {
                TreeNode curr = queue.poll();

                if(curr.left != null && curr.left.left == null && curr.left.right == null){
                    res += curr.left.val;
                }
                if(curr.left != null) {
                    queue.offer(curr.left);
                }
                if(curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            return res;
        }

//////////////////////////////////////////////////////////////////////////////////////
        //其实就是两个条件，第一，node必须是个左树，第二node左右子树必须为null
/*Iterative method. Here for each node in the tree we check whether its left child is a leaf. If it is true, we add its value to answer, otherwise add left child to the stack to process it later. For right child we add it to stack only if it is not a leaf.
* */

    public int sumOfLeftLeaves3(TreeNode root) {
        if(root == null) return 0;
        int ans = 0;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while(!stack.empty()) {
            TreeNode node = stack.pop();
            if(node.left != null) {
                if (node.left.left == null && node.left.right == null)
                    ans += node.left.val;
                else
                    stack.push(node.left);
            }
            if(node.right != null) {
                if (node.right.left != null || node.right.right != null)
                    stack.push(node.right);
            }
        }
        return ans;
    }


//////////////////////////////////////////////////////////////////////////////////////
    /*Java iterative and recursive solutions
        Recursive method. For given node we check whether its left child is a leaf. If it is the case, we add its value to answer, otherwise recursively call method on left child. For right child we call method only if it has at least one nonnull child.*/
    //这个和九章的一样
    public int sumOfLeftLeaves2(TreeNode root) {
        if(root == null) return 0;
        int ans = 0;
        if(root.left != null) {
            if(root.left.left == null && root.left.right == null)
                ans += root.left.val;
            else ans += sumOfLeftLeaves2(root.left);
        }
        ans += sumOfLeftLeaves2(root.right);

        return ans;
    }

//////////////////////////////////////////////////////////////////////////////////////

    //Jiuzhang
public class Jiuzhang {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int sum = 0;

        if(root.left != null) {
            TreeNode left = root.left;
            if(left.left == null && left.right == null) {//左树而且其左右皆为null，就是左叶子
                sum += left.val;
            }
            else {
                sum += sumOfLeftLeaves(left);
            }
        }

        if(root.right != null) {
            TreeNode right = root.right;
            sum += sumOfLeftLeaves(right);
        }
        return sum;
    }
}
//////////////////////////////////////////////////////////////////////////////////////
}
/*
Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
/*

 */
