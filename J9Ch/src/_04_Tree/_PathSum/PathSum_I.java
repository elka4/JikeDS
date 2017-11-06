package _04_Tree._PathSum;

import lib.*;

import java.util.LinkedList;
import java.util.Stack;

/*
LeetCode –  112. Path Sum ， root-to-leaf
 */


public class PathSum_I {

    /*
    Java Solution 1 - Using Queue

    Add all node to a queue and store sum value of each node to another queue.
    When it is a leaf node, check the stored sum value.

    For the tree above, the queue would be: 5 - 4 - 8 - 11 - 13 - 4 - 7 - 2 - 1.
    It will check node 13, 7, 2 and 1. This is a typical breadth first search(BFS) problem.
     */

    /*
        平行操作两个stack，一个放node，一个放到这个node为止的sum
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;

        LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
        LinkedList<Integer> values = new LinkedList<Integer>();

        nodes.add(root);
        values.add(root.val);

        while(!nodes.isEmpty()){
            TreeNode curr = nodes.poll();
            int sumValue = values.poll();

            if(curr.left == null && curr.right == null && sumValue == sum){
                return true;
            }

            if(curr.left != null){
                nodes.add(curr.left);
                values.add(sumValue + curr.left.val);
            }

            if(curr.right != null){
                nodes.add(curr.right);
                values.add(sumValue + curr.right.val);
            }
        }

        return false;
    }

////////////////////////////////////////////////////////////////////////////

    //Java Solution 2 - Recursion
//[Accepted]My recursive solution in Java
/*The basic idea is to subtract the value of current node from sum until it reaches
a leaf node and the subtraction equals 0, then we know that we got a hit. O
therwise the subtraction at the end could not be 0.*/

    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null)
            return false;

        if (root.left == null && root.right == null)
            return root.val == sum;

        return hasPathSum(root.left, sum - root.val) ||
                hasPathSum(root.right, sum - root.val);
    }

////////////////////////////////////////////////////////////////////////////
    //Java solution, both recursion and iteration
    /*
    双Stack平行运作，一个stac存treenode，一个存这个node对应的pathsum
     */
    class Solution7{
        public boolean hasPathSum(TreeNode root, int sum) {
            // iteration method
            if (root == null) {return false;}
            Stack<TreeNode> path = new Stack<>();
            Stack<Integer> sub = new Stack<>();
            path.push(root);
            sub.push(root.val);

            while (!path.isEmpty()) {
                TreeNode temp = path.pop();
                int tempVal = sub.pop();

                if (temp.left == null && temp.right == null) {
                    if (tempVal == sum) return true;
                }

                if (temp.left != null) {
                    path.push(temp.left);
                    sub.push(temp.left.val + tempVal);
                }
                if (temp.right != null) {
                    path.push(temp.right);
                    sub.push(temp.right.val + tempVal);
                }
            }
            return false;
        }

        public boolean hasPathSum7(TreeNode root, int sum) {
            // recursion method
            if (root == null) return false;
            if (root.left == null && root.right == null && root.val == sum)
                return true;
            return hasPathSum7(root.left, sum - root.val) ||
                    hasPathSum7(root.right, sum - root.val);
        }
    }

////////////////////////////////////////////////////////////////////////////

    //My java no-recursive method
    class Solution5{
        //the idea is preorder traverse , instead of using recursive call, I am using a stack.
        //the only problem is that I changed TreeNode value ！！！！！所以不好

        public boolean hasPathSum(TreeNode root, int sum) {
            Stack <TreeNode> stack = new Stack<> ();
            stack.push(root) ;
            while (!stack.isEmpty() && root != null){
                TreeNode cur = stack.pop() ;
                if (cur.left == null && cur.right == null){
                    if (cur.val == sum ) return true ;
                }
                if (cur.right != null) {
                    cur.right.val = cur.val + cur.right.val ;
                    stack.push(cur.right) ;
                }
                if (cur.left != null) {
                    cur.left.val = cur.val + cur.left.val;
                    stack.push(cur.left);
                }
            }
            return false ;
        }
    }

////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////

}
/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such
 that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */