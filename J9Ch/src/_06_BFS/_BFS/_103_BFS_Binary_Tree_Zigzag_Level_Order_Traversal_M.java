package _06_BFS._BFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _103_BFS_Binary_Tree_Zigzag_Level_Order_Traversal_M {
    public class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if(root == null) return res;

            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            boolean order = true;
            int size = 1;

            while(!q.isEmpty()) {
                List<Integer> tmp = new ArrayList<>();
                for(int i = 0; i < size; ++i) {
                    TreeNode n = q.poll();
                    if(order) {
                        tmp.add(n.val);
                    } else {
                        tmp.add(0, n.val);
                    }
                    if(n.left != null) q.add(n.left);
                    if(n.right != null) q.add(n.right);
                }
                res.add(tmp);
                size = q.size();
                order = order ? false : true;
            }
            return res;
        }
    }

}
