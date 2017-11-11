package _06_BFS._BFS_Tree;

import lib.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

//  513. Find Bottom Left Tree Value
public class _513_BFS_Find_Bottom_Left_Tree_Value_M {
//Right-to-Left BFS (Python + Java)
/*
Doing BFS right-to-left means we can simply return the last node's value and don't have to keep track of the first node in the current row or even care about rows at all. Inspired by @fallcreek's solution (not published) which uses two nested loops to go row by row but already had the right-to-left idea making it easier. I just took that further.
 */
public int findLeftMostNode(TreeNode root) {
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


//Simple Java Solution, beats 100.0%!

    public class Solution2 {
        int ans=0, h=0;
        public int findBottomLeftValue(TreeNode root) {
            findBottomLeftValue(root, 1);
            return ans;
        }
        public void findBottomLeftValue(TreeNode root, int depth) {
            if (h<depth) {ans=root.val;h=depth;}
            if (root.left!=null) findBottomLeftValue(root.left, depth+1);
            if (root.right!=null) findBottomLeftValue(root.right, depth+1);
        }
    }
//    No global variables, 6ms (faster):

    public class Solution3 {
        public int findBottomLeftValue(TreeNode root) {
            return findBottomLeftValue(root, 1, new int[]{0,0});
        }
        public int findBottomLeftValue(TreeNode root, int depth, int[] res) {
            if (res[1]<depth) {res[0]=root.val;res[1]=depth;}
            if (root.left!=null) findBottomLeftValue(root.left, depth+1, res);
            if (root.right!=null) findBottomLeftValue(root.right, depth+1, res);
            return res[0];
        }
    }

/*
Add if (null != root.left) and if (null != root.right) before findLeftMostNode(root.left, depth+1);findLeftMostNode(root.right, depth+1); could improve efficiency of recursion.
 */





//    Similar idea, no global variable, with some comments:
    class Solution4{
        private void helper(TreeNode root, int row, int[] result/*result-val, lowest row*/ ){
            if(root==null)
                return;

            if(row > result[1]){
                result[0] = root.val;
                result[1] = row;
            }
            //no need to care about the cols of a row, as here we always go to left first,
            // the left-most node of a row will always be captured first
            helper(root.left, row+1, result);
            helper(root.right, row+1, result);
        }

        public int findBottomLeftValue(TreeNode root) {
            if(root==null)
                return 0;
            int[] result = {root.val, 0};
            helper(root, 0, result);
            return result[0];
        }
    }

//    Got something similar:
    class Solution5{
        public int findBottomLeftValue(TreeNode root) {
            int[] res = new int[]{0, root.val};
            dfs(root, 0, res);
            return res[1];
        }

        private void dfs(TreeNode root, int level, int[] res) {
            if (root == null) return;

            dfs(root.left, level + 1, res);
            if (level > res[0]) {
                res[0] = level;
                res[1] = root.val;
            }
            dfs(root.right, level + 1, res);
        }
    }


    //Verbose Java Solution, Binary tree level order traversal
//    Typical way to do binary tree level order traversal.
// Only additional step is to remember the first element of each level.

    public class Solution6 {
        public int findLeftMostNode(TreeNode root) {
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
    }

    //standard BFS in java
    public int findBottomLeftValue(TreeNode root) {
            /*any initial value is valid*/
        int result = -1;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            result = queue.peek().val;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
        }
        return result;
    }

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