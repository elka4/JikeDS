package _04_Tree._Level;

import lib.TreeNode;

import java.util.*;


//  637. Average of Levels in Binary Tree
//  https://leetcode.com/problems/average-of-levels-in-binary-tree/description/
//  Tree
//  4:
public class _637_Tree_Average_of_Levels_in_Binary_Tree_E {
    //https://leetcode.com/articles/average-of-levels/

//---------------------------------------------------------------------------------------

    //1
    //    Approach #1 Using Depth First Search [Accepted]
    //DFS
    //双List，一个存sum，一个存count
    //重点sum.set(i,val)....不断更新list里的内容
   public class Solution01 {
        public List < Double > averageOfLevels(TreeNode root) {
            List < Integer > count = new ArrayList < > ();
            List < Double > result = new ArrayList < > ();
            
            average(root, 0, result, count);

            for (int i = 0; i < result.size(); i++)
                result.set(i, result.get(i) / count.get(i));

            return result;
        }
        
        //preorder
        public void average(TreeNode node, int level, List < Double > sum, List < Integer > count) {
            if (node == null)
                return;

            if (level < sum.size()) {
                sum.set(level, sum.get(level) + node.val);
                count.set(level, count.get(level) + 1);
            } else {//level == sum.size()
                sum.add(1.0 * node.val);
                count.add(1);
            }

            average(node.left, level + 1, sum, count);
            average(node.right, level + 1, sum, count);
        }
    }

//---------------------------------------------------------------------------------------
    //2
    //    Approach #2 Breadth First Search [Accepted]
    public List < Double > averageOfLevels2(TreeNode root) {
        List < Double > res = new ArrayList < > ();
        Queue < TreeNode > queue = new LinkedList < > ();
        queue.add(root);
        while (!queue.isEmpty()) {
            long sum = 0, count = 0;
            Queue < TreeNode > temp = new LinkedList < > ();
            while (!queue.isEmpty()) {
                TreeNode n = queue.remove();
                sum += n.val;
                count++;
                if (n.left != null)
                    temp.add(n.left);
                if (n.right != null)
                    temp.add(n.right);
            }
            queue = temp;
            res.add(sum * 1.0 / count);
        }
        return res;
    }

//---------------------------------------------------------------------------------------
    //3
    //Java BFS Solution
    //和上面的方法一样
    public List<Double> averageOfLevels3(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        if(root == null) return result;
        q.add(root);
        while(!q.isEmpty()) {//每个循环就是一层
            int n = q.size();
            double sum = 0.0;
            for(int i = 0; i < n; i++) {
                TreeNode node = q.poll();
                sum += node.val;
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }
            result.add(sum / n);
        }
        return result;
    }

//---------------------------------------------------------------------------------------

    //4
    //DFS
    public List<Double> averageOfLevels4(TreeNode root) {
        List<Double> result = new ArrayList<Double>();
        List<Integer> cntLs = new ArrayList<Integer>();
        if (root == null) return result;
        
        helper(root, 0, result, cntLs);
        
        for (int i = 0; i < result.size(); i++)
            result.set(i, result.get(i) / cntLs.get(i));
        
        return result;
    }
    //preorder
    private void helper(TreeNode root, int lv, List<Double> result, List<Integer> cntLs) {
        if (root != null) {
            if (result.size() <= lv) {
                result.add((double) root.val);
                cntLs.add(1);
            } else {
                result.set(lv, result.get(lv) + root.val);
                cntLs.set(lv, cntLs.get(lv) + 1);
            }

            helper(root.left, lv + 1, result, cntLs);
            helper(root.right, lv + 1, result, cntLs);
        }
    }

//---------------------------------------------------------------------------------------

}
/*

Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.

Example 1:
Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]

Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].


Note:
The range of node's value is in the range of 32-bit signed integer.
 */
