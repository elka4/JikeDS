package _04_Tree._Level;

import lib.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


//
//
//
public class _103_Tree_Binary_Tree_Zigzag_Level_Order_Traversal_M {

    public class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root)
        {
            List<List<Integer>> sol = new ArrayList<>();
            travel(root, sol, 0);
            return sol;
        }

        private void travel(TreeNode curr, List<List<Integer>> sol, int level)
        {
            if(curr == null) return;

            if(sol.size() <= level)
            {
                List<Integer> newLevel = new LinkedList<>();
                sol.add(newLevel);
            }

            List<Integer> collection  = sol.get(level);
            if(level % 2 == 0) collection.add(curr.val);
            else collection.add(0, curr.val);

            travel(curr.left, sol, level + 1);
            travel(curr.right, sol, level + 1);
        }
    }


    public class Solution2 {
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
/*

 */
/*

 */
