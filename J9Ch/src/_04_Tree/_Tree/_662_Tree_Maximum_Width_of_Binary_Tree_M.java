package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _662_Tree_Maximum_Width_of_Binary_Tree_M {

    class Solution{
        public int widthOfBinaryTree(TreeNode root) {
            return dfs(root, 0, 1, new ArrayList<Integer>(), new ArrayList<Integer>());
        }

        public int dfs(TreeNode root, int level, int order, List<Integer> start, List<Integer> end){
            if(root == null)return 0;
            if(start.size() == level){
                start.add(order); end.add(order);
            }
            else end.set(level, order);
            int cur = end.get(level) - start.get(level) + 1;
            int left = dfs(root.left, level + 1, 2*order, start, end);
            int right = dfs(root.right, level + 1, 2*order + 1, start, end);
            return Math.max(cur, Math.max(left, right));
        }

    }

    //DFS - Return Value
    class Solution2 {
        public int widthOfBinaryTree(TreeNode root) {
            List<Integer> lefts = new ArrayList<Integer>(); // left most nodes at each level;
            return dfs(root, 1, 0, lefts);
        }

        private int dfs(TreeNode n, int id, int d, List<Integer> lefts) { // d : depth
            if (n == null) return 0;
            if (d >= lefts.size()) lefts.add(id);   // add left most node
            return Math.max(id + 1 - lefts.get(d), Math.max(dfs(n.left, id*2, d+1, lefts), dfs(n.right, id*2+1, d+1, lefts)));
        }
    }


    //DFS - Side Effect
    class Solution3 {
        public int widthOfBinaryTree(TreeNode root) {
            List<Integer> lefts = new ArrayList<Integer>(); // left most nodes at each level;
            int[] res = new int[1]; // max width
            dfs(root, 1, 0, lefts, res);
            return res[0];
        }
        private void dfs(TreeNode node, int id, int depth, List<Integer> lefts, int[] res) {
            if (node == null) return;
            if (depth >= lefts.size()) lefts.add(id);   // add left most node
            res[0] = Integer.max(res[0], id + 1 - lefts.get(depth));
            dfs(node.left,  id * 2,     depth + 1, lefts, res);
            dfs(node.right, id * 2 + 1, depth + 1, lefts, res);
        }
    }

    //BFS
    class Solution4 {
        public int widthOfBinaryTree(TreeNode root) {
            if (root == null) return 0;
            int max = 0;
            Queue<Map.Entry<TreeNode, Integer>> q = new LinkedList<Map.Entry<TreeNode, Integer>>();
            q.offer(new AbstractMap.SimpleEntry(root, 1));

            while (!q.isEmpty()) {
                int l = q.peek().getValue(), r = l; // right started same as left
                for (int i = 0, n = q.size(); i < n; i++) {
                    TreeNode node = q.peek().getKey();
                    r = q.poll().getValue();
                    if (node.left != null) q.offer(new AbstractMap.SimpleEntry(node.left, r * 2));
                    if (node.right != null) q.offer(new AbstractMap.SimpleEntry(node.right, r * 2 + 1));
                }
                max = Math.max(max, r + 1 - l);
            }

            return max;
        }
    }


    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        ArrayDeque<Integer>  count = new ArrayDeque<>();
        queue.offer(root);
        count.offer(0);
        int max = 1;

        while(!queue.isEmpty()) {
            int size = queue.size();
            int left = 0;
            int right = 0;
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                int index = count.poll();
                if(i == 0)  left = index;
                if(i == size-1)  right = index;
                if(node.left != null) {
                    queue.offer(node.left);
                    count.offer(index*2);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                    count.offer(index*2 + 1);
                }
            }
            max = Math.max(max,right - left + 1);
        }
        return max;
    }
}
