package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _366_DFS_Find_Leaves_of_Binary_Tree_M {

    public class Solution {
        public List<List<Integer>> findLeaves(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            height(root, res);
            return res;
        }
        private int height(TreeNode node, List<List<Integer>> res){
            if(null==node)  return -1;
            int level = 1 + Math.max(height(node.left, res), height(node.right, res));
            if(res.size()<level+1)  res.add(new ArrayList<>());
            res.get(level).add(node.val);
            return level;
        }
    }


    //Java backtracking O(n) time O(n) space No hashing!
    public class Solution2 {
        public List<List<Integer>> findLeaves(TreeNode root) {
            List<List<Integer>> list = new ArrayList<>();
            findLeavesHelper(list, root);
            return list;
        }

        // return the level of root
        private int findLeavesHelper(List<List<Integer>> list, TreeNode root) {
            if (root == null) {
                return -1;
            }
            int leftLevel = findLeavesHelper(list, root.left);
            int rightLevel = findLeavesHelper(list, root.right);
            int level = Math.max(leftLevel, rightLevel) + 1;
            if (list.size() == level) {
                list.add(new ArrayList<>());
            }
            list.get(level).add(root.val);
            root.left = root.right = null;
            return level;
        }
    }
//////////////////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */