package _05_DFS._DFS_Tree;

import lib.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _515_DFS_Find_Largest_Value_in_Each_Tree_Row_M {

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
//////////////////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */