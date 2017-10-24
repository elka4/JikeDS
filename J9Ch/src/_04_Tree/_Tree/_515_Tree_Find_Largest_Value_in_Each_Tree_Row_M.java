package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _515_Tree_Find_Largest_Value_in_Each_Tree_Row_M {
    /*
    Just a simple pre-order traverse idea. Use depth to expand result list size and put the max value in the appropriate position.


     */
    public class Solution {
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            helper(root, res, 0);
            return res;
        }
        private void helper(TreeNode root, List<Integer> res, int d){
            if(root == null){
                return;
            }
            //expand list size
            if(d == res.size()){
                res.add(root.val);
            }
            else{
                //or set value
                res.set(d, Math.max(res.get(d), root.val));
            }
            helper(root.left, res, d+1);
            helper(root.right, res, d+1);
        }
    }

    //Alright, two binary tree level order traversal problems in one contest.
    // This time, mission is to find the max of each level...


    public class Solution2 {
        public int[] findValueMostElement(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return new int[0];

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                int max = Integer.MIN_VALUE;
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    max = Math.max(max, node.val);
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
                res.add(max);
            }

            int[] result = new int[res.size()];
            for (int i = 0; i < res.size(); i++) {
                result[i] = res.get(i);
            }

            return result;
        }
    }
}
