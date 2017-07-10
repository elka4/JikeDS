package J_4_Breadth_First_Search.Related_7;
import java.util.*;import lib.TreeNode;import lib.AssortedMethods;import org.junit.Test;
/** 70 Binary Tree Level Order Traversal II
 * Created by tianhuizhu on 6/28/17.
 */
public class _70_Binary_Tree_Level_Order_Traversal_II_Medium {

    public class Solution {
        /**
         * @param root: The root of binary tree.
         * @return: buttom-up level order a list of lists of integer
         */
        public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
            ArrayList<ArrayList<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                ArrayList<Integer> level = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode head = queue.poll();
                    level.add(head.val);
                    if (head.left != null) {
                        queue.offer(head.left);
                    }
                    if (head.right != null) {
                        queue.offer(head.right);
                    }
                }
                result.add(level);
            }

            Collections.reverse(result);
            return result;
        }
    }
}
