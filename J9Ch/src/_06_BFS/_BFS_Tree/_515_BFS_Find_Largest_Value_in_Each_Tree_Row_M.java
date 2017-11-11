package _06_BFS._BFS_Tree;

import lib.TreeNode;

import java.util.*;

public class _515_BFS_Find_Largest_Value_in_Each_Tree_Row_M {

//9ms JAVA DFS solution
//    Just a simple pre-order traverse idea. Use depth to expand result list
// size and put the max value in the appropriate position.

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


//    Java BFS
    public int[] findValueMostElement2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> res = new ArrayList<Integer>();
        queue.add(root);
        int queueSize = root == null ? 0 : 1;
        while (queueSize > 0) {
            int largestElement = Integer.MIN_VALUE;
            for (int i=0;i<queueSize;i++) {
                TreeNode cur = queue.poll();
                largestElement = Math.max(cur.val, largestElement);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            res.add(largestElement);
            queueSize = queue.size();
        }
        int[] resArray = new int[res.size()];
        for (int i=0;i<res.size();i++) resArray[i] = res.get(i);
        return resArray;
    }


    //5-lines Java DFS Solution
    public class Solution3 {
        public int[] findValueMostElement(TreeNode root) {
            return dfs(root, 0, new ArrayList<>()).stream().mapToInt(TreeSet::last).toArray();
        }
        private ArrayList<TreeSet<Integer>> dfs(TreeNode root, int depth, ArrayList<TreeSet<Integer>> list) {
            if (root==null) return list;
            if (list.size()<=depth) list.add(new TreeSet<>());
            list.get(depth).add(root.val);
            return dfs(root.right, depth+1, dfs(root.left, depth+1, list));
        }
    }


    //Standard travel by level - Java BFS
    //Use a queue to travel the tree level by level.

    public class Solution4 {
        public int[] findValueMostElement(TreeNode root) {
            if(root==null) return new int[0];
            List<Integer> res = new LinkedList<>();
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            while(!q.isEmpty()){
                int max = q.peek().val;
                int size = q.size();
                for(int i = 0; i<size; i++){
                    TreeNode cur = q.poll();
                    max = Math.max(max, cur.val);
                    if(cur.left!=null) q.add(cur.left);
                    if(cur.right!=null) q.add(cur.right);
                }
                res.add(max);
            }
            return res.stream().mapToInt(k->k).toArray();
        }
    }

    //Verbose Java Solution, Binary tree level order traversal, again.
//    Alright, two binary tree level order traversal problems in one contest.
// This time, mission is to find the max of each level...

    public class Solution5 {
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

    //Java clean BFS 11 ms
    public class Solution6 {
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) return result;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int max = queue.peek().val;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode temp = queue.poll();
                    max = Math.max(temp.val, max);
                    if (temp.left != null) queue.add(temp.left);
                    if (temp.right!= null) queue.add(temp.right);
                }
                result.add(max);
            }
            return result;
        }
    }


}
/*
You need to find the largest value in each row of a binary tree.

Example:
Input:

          1
         / \
        3   2
       / \   \
      5   3   9

Output: [1, 3, 9]
 */