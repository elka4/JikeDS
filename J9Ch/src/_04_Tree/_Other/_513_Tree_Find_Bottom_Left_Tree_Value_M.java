package _04_Tree._Other;

import lib.TreeNode;

import java.util.LinkedList;
import java.util.Queue;


//
//
//
public class _513_Tree_Find_Bottom_Left_Tree_Value_M {
    public class Solution {
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
}
/*

 */
/*

 */
