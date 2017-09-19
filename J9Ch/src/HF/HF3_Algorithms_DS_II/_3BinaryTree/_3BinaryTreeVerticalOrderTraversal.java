package HF.HF3_Algorithms_DS_II._3BinaryTree;

import java.util.*;

//Binary Tree Vertical Order Traversal
public class _3BinaryTreeVerticalOrderTraversal {

    public class Solution {
        /**
         * @param root the root of binary tree
         * @return the vertical order traversal
         */
        public List<List<Integer>> verticalOrder(TreeNode root) {
            // Write your code here
            List<List<Integer>> results = new ArrayList<>();
            if (root == null) {
                return results;
            }
            Map<Integer, List<Integer>> map = new TreeMap<Integer, List<Integer>>();
            Queue<Integer> qCol = new LinkedList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            qCol.offer(0);

            while(!queue.isEmpty()) {
                TreeNode curr = queue.poll();
                int col = qCol.poll();
                if(!map.containsKey(col)) {
                    map.put(col, new ArrayList<Integer>(Arrays.asList(curr.val)));
                } else {
                    map.get(col).add(curr.val);
                }
                if(curr.left != null) {
                    queue.offer(curr.left);
                    qCol.offer(col - 1);
                }
                if(curr.right != null) {
                    queue.offer(curr.right);
                    qCol.offer(col + 1);
                }
            }
            for(int n : map.keySet()) {
                results.add(map.get(n));
            }
            return results;
        }
    }

////////////////////////////////////////////////////////////

    // version: 高频题班
    public class Solution2 {
        /**
         * @param root the root of binary tree
         * @return the vertical order traversal
         */
        public List<List<Integer>> verticalOrder(TreeNode root) {
            // Write your code here
            List<List<Integer>> ans = new ArrayList<>();
            if (root == null) {
                return ans;
            }

            Map<Integer, List<Integer>> col = new HashMap<>();
            Queue<Integer> qCol = new LinkedList<>();
            Queue<TreeNode> qNode = new LinkedList<>();

            qCol.offer(0);
            qNode.offer(root);

            while (!qCol.isEmpty()) {                      // bfs
                int c = qCol.poll();
                TreeNode node = qNode.poll();

                col.putIfAbsent(c, new ArrayList<>());
                col.get(c).add(node.val);

                if (node.left != null) {
                    qCol.offer(c - 1);
                    qNode.offer(node.left);
                }
                if (node.right != null) {
                    qCol.offer(c + 1);
                    qNode.offer(node.right);
                }
            }

            for (int i = Collections.min(col.keySet()); i <= Collections.max(col.keySet()); i++) {
                ans.add(col.get(i));
            }
            return ans;
        }
    }
}
/*
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Have you met this question in a real interview? Yes
Example
Given binary tree {3,9,20,#,#,15,7}

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7
Return its vertical order traversal as:
[[9],[3,15],[20],[7]]

Given binary tree {3,9,8,4,0,1,7}

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
Return its vertical order traversal as:
[[4],[9],[3,0,1],[8],[7]]
 */
