package _04_Tree._LCS;

import lib.TreeNode;


//
//
//
public class _298_Tree_Binary_Tree_Longest_Consecutive_Sequence_M {

    public class Solution {
        private int max = 0;
        public int longestConsecutive(TreeNode root) {
            if(root == null) return 0;
            helper(root, 0, root.val);
            return max;
        }

        public void helper(TreeNode root, int cur, int target){
            if(root == null) return;
            if(root.val == target) cur++;
            else cur = 1;
            max = Math.max(cur, max);
            helper(root.left, cur, root.val + 1);
            helper(root.right, cur, root.val + 1);
        }
    }

    public int longestConsecutive(TreeNode root) {
        int[] lens = new int[1];
        if (root == null)  return 0;
        helper(root, 0, lens, root.val);
        return lens[0];
    }

    private void helper(TreeNode root, int cur, int[] cnt, int target) {
        if (root == null)  return;
        if (root.val == target)  cur++;
        else  cur = 1;
        cnt[0] = Math.max(cur,cnt[0]);
        helper(root.left, cur, cnt, root.val+1);
        helper(root.right, cur, cnt, root.val+1);
    }



    public int longestConsecutive3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return DFS3(root, root.val + 1, 1, 1);
    }

    private int DFS3(TreeNode node, int target, int curr, int max) {
        if (node == null) {
            return max;
        }
        if (node.val == target) {
            curr++;
            max = Math.max(max, curr);
        } else {
            curr = 1;
        }
        return Math.max(DFS3(node.left, node.val + 1, curr, max),
                DFS3(node.right, node.val + 1, curr, max));
    }

    // Simple Recursive DFS without global variable


    public class Solution4 {
        public int longestConsecutive(TreeNode root) {
            return (root==null)?0:Math.max(dfs(root.left, 1, root.val),
                    dfs(root.right, 1, root.val));
        }

        public int dfs(TreeNode root, int count, int val){
            if(root==null) return count;
            count = (root.val - val == 1)?count+1:1;
            int left = dfs(root.left, count, root.val);
            int right = dfs(root.right, count, root.val);
            return Math.max(Math.max(left, right), count);
        }
    }
}
/*

 */
/*

 */
