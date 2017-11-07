package _04_Tree._Leaves;
import lib.TreeNode;
import java.util.*;

//  366. Find Leaves of Binary Tree
//  https://leetcode.com/problems/find-leaves-of-binary-tree/description/
//  http://lintcode.com/zh-cn/problem/binary-tree-leaves-order-traversal/
public class _366_Tree_Find_Leaves_of_Binary_Tree_M {
    //  10 lines simple Java solution using recursion with explanation
    /*
    For this question we need to take bottom-up approach. The key is to find the height of each node. Here the definition of height is: The height of a node is the number of edges from the node to the deepest leaf. --CMU 15-121 Binary Trees. I used a helper function to return the height of current node. According to the definition, the height of leaf is 0. h(node) = 1 + max(h(node.left), h(node.right)). The height of a node is also the its index in the result list (res). For example, leaves, whose heights are 0, are stored in res[0]. Once we find the height of a node, we can put it directly into the result.

    UPDATE:
    Thanks @adrianliu0729 for pointing out that my previous code does not actually remove leaves. I added one line node.left = node.right = null; to remove visited nodes

    UPDATE:
    There seems to be some debate over whether we need to actually "remove" leaves from the input tree. Anyway, it is just a matter of one line code. In the actual interview, just confirm with the interviewer whether removal is required.
     */
        public List<List<Integer>> findLeaves1(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            height1(root, res);
            return res;
        }
        private int height1(TreeNode node, List<List<Integer>> res){
            if(null==node)  return -1;
            int level = 1 + Math.max(height1(node.left, res), height1(node.right, res));
            if(res.size()<level+1)  res.add(new ArrayList<>());
            res.get(level).add(node.val);
            return level;
        }


///////////////////////////////////////////////////////////////////////////////
    /*
    Java backtracking O(n) time O(n) space No hashing!
    The essential of problem is not to find the leaves, but group leaves of same level together and also to cut the tree. This is the exact role backtracking plays. The helper function returns the level which is the distance from its furthest subtree leaf to root, which helps to identify which group the root belongs to
     */
        public List<List<Integer>> findLeaves2(TreeNode root) {
            List<List<Integer>> list = new ArrayList<>();
            findLeavesHelper2(list, root);
            return list;
        }

        // return the level of root
        private int findLeavesHelper2(List<List<Integer>> list, TreeNode root) {
            if (root == null) {
                return -1;
            }
            int leftLevel = findLeavesHelper2(list, root.left);
            int rightLevel = findLeavesHelper2(list, root.right);
            int level = Math.max(leftLevel, rightLevel) + 1;
            if (list.size() == level) {
                list.add(new ArrayList<>());
            }
            list.get(level).add(root.val);
            root.left = root.right = null;
            return level;
        }

/////////////////////////////////////////////////////////////////////////////////

        // 1 ms Easy understand Java Solution
        public List<List<Integer>> findLeaves3(TreeNode root) {

            List<List<Integer>> leavesList = new ArrayList< List<Integer>>();
            List<Integer> leaves = new ArrayList<Integer>();

            while(root != null) {
                if(isLeave3(root, leaves)) root = null;
                leavesList.add(leaves);
                leaves = new ArrayList<Integer>();
            }
            return leavesList;
        }

        public boolean isLeave3(TreeNode node, List<Integer> leaves) {

            if (node.left == null && node.right == null) {
                leaves.add(node.val);
                return true;
            }

            if (node.left != null) {
                if(isLeave3(node.left, leaves))  node.left = null;
            }

            if (node.right != null) {
                if(isLeave3(node.right, leaves)) node.right = null;
            }

            return false;
        }
/////////////////////////////////////////////////////////////////////////////////
    //jiuzhang
    public class Jiuzhang1 {
        /**
         * @param root the root of binary tree
         * @return collect and remove all leaves
         */
        public List<List<Integer>> findLeaves(TreeNode root) {
            // Write your code here
            List<List<Integer>> results = new ArrayList<List<Integer>>();
            dfs(root, results);
            return results;
        }

        int dfs(TreeNode root, List<List<Integer>> results) {
            if (root == null) {
                return 0;
            }
            int level = Math.max(dfs(root.left, results), dfs(root.right, results)) + 1;
            int size = results.size();
            if (level > size) {
                results.add(new ArrayList<Integer>());
            }
            results.get(level - 1).add(root.val);
            return level;
        }
    }

    // version: 高频题班
    public class Jiuzhang2 {
        /**
         * @param root the root of binary tree
         * @return collect and remove all leaves
         */
        Map<Integer, List<Integer>> depth = new HashMap<>();

        int dfs(TreeNode cur) {
            if (cur == null) {
                return 0;
            }
            int d = Math.max(dfs(cur.left), dfs(cur.right)) + 1;

            depth.putIfAbsent(d, new ArrayList<>());
            depth.get(d).add(cur.val);
            return d;
        }

        public List<List<Integer>> findLeaves(TreeNode root) {
            // Write your code here
            List<List<Integer>> ans = new ArrayList<>();

            int max_depth = dfs(root);

            for (int i = 1; i <= max_depth; i++) {
                ans.add(depth.get(i));
            }
            return ans;
        }
    }
/////////////////////////////////////////////////////////////////////////////////
}
/*
给定一个二叉树，像这样收集树节点：收集并移除所有叶子，重复，直到树为空。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出一个二叉树：

           1
          / \
         2   3
        / \
       4   5
返回 [[4, 5, 3], [2], [1]].


 */
/*
Given a binary tree, collect a tree's nodes as if you were doing this:
Collect and remove all leaves, repeat until the tree is empty.

Example:
Given binary tree
          1
         / \
        2   3
       / \
      4   5
Returns [4, 5, 3], [2], [1].

Explanation:
1. Removing the leaves [4, 5, 3] would result in this tree:

          1
         /
        2
2. Now removing the leaf [2] would result in this tree:

          1
3. Now removing the leaf [1] would result in the empty tree:

          []
Returns [4, 5, 3], [2], [1].
 */
