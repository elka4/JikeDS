package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _513_DFS_Find_Bottom_Left_Tree_Value_M {
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

    public int findBottomLeftValue(TreeNode root) {

        int result=0;
        LinkedList<TreeNode> queue=new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){

            TreeNode node=queue.poll();
            result=node.val;
            if(node.right!=null)
                queue.offer(node.right);
            if(node.left!=null)
                queue.offer(node.left);
        }

        return result;

    }

    public class Solution3 {
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

    public class Solution4 {
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

//----------------------------------------------------------------------------




//----------------------------------------------------------------------------






}
/*

 */
