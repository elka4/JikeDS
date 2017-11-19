package _04_Tree._Kth;

import lib.TreeNode;

import java.util.*;


//  671. Second Minimum Node In a Binary Tree
//  https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/description/
public class _671_Tree_Second_Minimum_Node_In_a_Binary_Tree_E {

    //  https://leetcode.com/articles/second-minimum-node-in-a-binary-tree/

    //    Approach #1: Brute Force [Accepted]
    class Solution01 {
        public void dfs(TreeNode root, Set<Integer> uniques) {
            if (root != null) {
                uniques.add(root.val);
                dfs(root.left, uniques);
                dfs(root.right, uniques);
            }
        }
        public int findSecondMinimumValue(TreeNode root) {
            Set<Integer> uniques = new HashSet<Integer>();
            dfs(root, uniques);

            int min1 = root.val;//The first minimum must be root.val.
            long ans = Long.MAX_VALUE;
            for (int v : uniques) {
                if (min1 < v && v < ans) ans = v;
            }
            return ans < Long.MAX_VALUE ? (int) ans : -1;
        }
    }


    //    Approach #2: Ad-Hoc [Accepted]
    class Solution02 {
        int min1;
        long ans = Long.MAX_VALUE;

        public void dfs(TreeNode root) {
            if (root != null) {
                if (min1 < root.val && root.val < ans) {
                    ans = root.val;
                } else if (min1 == root.val) {
                    dfs(root.left);
                    dfs(root.right);
                }
            }
        }
        public int findSecondMinimumValue(TreeNode root) {
            min1 = root.val;
            dfs(root);
            return ans < Long.MAX_VALUE ? (int) ans : -1;
        }
    }

//-------------------------------------------------------------------------/////////////
    //  https://www.zhihu.com/question/28414001
    /*
    JDK8的HashSet实现变了，导致元素插入的位置发生了变化；iterator自身实现的顺序倒没变，还是按照内部插入的位置顺序来遍历，于是题主就看到了JDK7和JDK8的结果不一样。具体来说，是JDK7与JDK8的java.util.HashMap的hash算法以及HashMap的数据布局发生了变化。

作者：RednaxelaFX
链接：https://www.zhihu.com/question/28414001/answer/40733996
来源：知乎
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int findSecondMinimumValue3(TreeNode root) {
        if (root == null) {
            return -1;
        }
        Set<Integer> set = new TreeSet<>();
        dfs(root, set);
        Iterator<Integer> iterator = set.iterator();//set的iterator可以保证从小到大的顺序？
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            int result = iterator.next();
            if (count == 2) {
                return result;
            }
        }
        return -1;
    }

    private void dfs(TreeNode root, Set<Integer> set) {
        if (root == null) {
            return;
        }
        set.add(root.val);
        dfs(root.left, set);
        dfs(root.right, set);
        return;
    }

//-------------------------------------------------------------------------/////////////
    //BFS
    public int findSecondMinimumValue4(TreeNode root) {
        int rootVal = root.val;
        int secondSmall =Integer.MAX_VALUE;
        boolean diffFound = false;
        Queue<TreeNode> Q= new LinkedList<TreeNode>();
        Q.add(root);

        while(!Q.isEmpty()) {
            TreeNode curr=Q.poll();
            if(curr.val!=rootVal && curr.val < secondSmall) {
                secondSmall=curr.val;
                diffFound=true;
            }
            if(curr.left!=null) {
                Q.add(curr.left);
                Q.add(curr.right);
            }
        }

        return (secondSmall == Integer.MAX_VALUE && !diffFound) ? -1 : secondSmall;
    }

//-------------------------------------------------------------------------/////////////
}

/*
Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:
Input:
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.
Example 2:
Input:
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.

 */
