package _06_BFS._BFS_Tree;

import lib.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _199_BFS_Binary_Tree_Right_Side_View_M {

    public class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> result = new ArrayList<Integer>();
            if(root==null) return result;
            Queue<TreeNode> q = new LinkedList<TreeNode>();
            q.add(root);
            while(q.size()>0){
                int size = q.size();
                for(int i=0;i<size;i++){
                    TreeNode node= q.poll();
                    if(i==size-1)
                        result.add(node.val);
                    if(node.left!=null) q.add(node.left);
                    if(node.right!=null) q.add(node.right);
                }
            }
            return result;
        }
    }

/*    The basic idea is using level by level BFS to traverse the tree: add the right-most node of each level to the result.

    Runtime complexity = O(n)

    n is the number of the nodes in the tree: because each node is visited only once, so the runtime complexity is O(n).*/

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer>res = new ArrayList<Integer>();
        if (root == null) return res;
        Queue<TreeNode>queue = new LinkedList<TreeNode>();

        queue.offer(root);
        while (!queue.isEmpty()) {//Level by level BFS
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                if (i == count - 1) // The right-most node of the current level
                    res.add(node.val);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
        }
        return res;
    }
}
