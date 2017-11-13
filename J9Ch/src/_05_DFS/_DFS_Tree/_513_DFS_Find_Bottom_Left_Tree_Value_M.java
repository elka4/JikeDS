package _05_DFS._DFS_Tree;
import lib.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

//  513. Find Bottom Left Tree Value
//  https://leetcode.com/problems/find-bottom-left-tree-value/description/
//
public class _513_DFS_Find_Bottom_Left_Tree_Value_M {
//Right-to-Left BFS (Python + Java)
    /*
    Doing BFS right-to-left means we can simply return the last node's value and don't have to keep track of the first node in the current row or even care about rows at all. Inspired by @fallcreek's solution (not published) which uses two nested loops to go row by row but already had the right-to-left idea making it easier. I just took that further.
     */
    public int findLeftMostNode1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null)
                queue.add(root.right);
            if (root.left != null)
                queue.add(root.left);
        }
        return root.val;
    }


//////////////////////////////////////////////////////////////////////////////////////////

    public int findLeftMostNode2(TreeNode root) {
        if (root == null) return 0;

        int result = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) result = node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }

        return result;
    }



////////////////////////////////////////////////////////////////////////////////////////////
    //Simple Java Solution, beats 100.0%!
    int ans=0, h=0;
    public int findBottomLeftValue4(TreeNode root) {
        findBottomLeftValue4(root, 1);
        return ans;
    }
    public void findBottomLeftValue4(TreeNode root, int depth) {
        if (h<depth) {ans=root.val;h=depth;}
        if (root.left!=null) findBottomLeftValue4(root.left, depth+1);
        if (root.right!=null) findBottomLeftValue4(root.right, depth+1);
    }


////////////////////////////////////////////////////////////////////////////////////////////


    //    No global variables, 6ms (faster):
    public int findBottomLeftValue5(TreeNode root) {

        return findBottomLeftValue5(root, 1, new int[]{0,0});
    }
    public int findBottomLeftValue5(TreeNode root, int depth, int[] res) {
        if (res[1]<depth) {res[0]=root.val;res[1]=depth;}
        if (root.left!=null) findBottomLeftValue5(root.left, depth+1, res);
        if (root.right!=null) findBottomLeftValue5(root.right, depth+1, res);
        return res[0];
    }


////////////////////////////////////////////////////////////////////////////////////////////

    /* Verbose Java Solution, Binary tree level order traversal. Typical way to do binary tree level order traversal. Only additional step is to remember the first element of each level.*/
    public int findLeftMostNode6(TreeNode root) {
        if (root == null) return 0;

        int result = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) result = node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }

        return result;
    }


////////////////////////////////////////////////////////////////////////////////////////////
}
/*

Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:

    2
   / \
  1   3

Output:
1
Example 2:
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
Note: You may assume the tree (i.e., the given root node) is not NULL.



 */
