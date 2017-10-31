package _04_Tree.Path_Sum;

import lib.*;

import java.util.ArrayList;


/* LeetCode  129. Sum Root to Leaf Numbers

LeetCode – Sum Root to Leaf Numbers (Java)

 */


public class Sum_Root_to_Leaf_Numbers {

    //Java Solution - Recursive

    //This problem can be solved by a typical DFS approach.

    public int sumNumbers(TreeNode root) {
        int result = 0;
        if(root==null)
            return result;

        ArrayList<ArrayList<TreeNode>> all = new ArrayList<ArrayList<TreeNode>>();
        ArrayList<TreeNode> l = new ArrayList<TreeNode>();
        l.add(root);
        dfs(root, l, all);

        for(ArrayList<TreeNode> a: all){
            StringBuilder sb = new StringBuilder();
            for(TreeNode n: a){
                sb.append(String.valueOf(n.val));
            }
            int currValue = Integer.valueOf(sb.toString());
            result = result + currValue;
        }

        return result;
    }

    public void dfs(TreeNode n, ArrayList<TreeNode> l,  ArrayList<ArrayList<TreeNode>> all){
        if(n.left==null && n.right==null){
            ArrayList<TreeNode> t = new ArrayList<TreeNode>();
            t.addAll(l);
            all.add(t);
        }

        if(n.left!=null){
            l.add(n.left);
            dfs(n.left, l, all);
            l.remove(l.size()-1);
        }

        if(n.right!=null){
            l.add(n.right);
            dfs(n.right, l, all);
            l.remove(l.size()-1);
        }

    }


////////////////////////////////////////////////////////////////////

    //Same approach, but simpler coding style.

    public int sumNumbers2(TreeNode root) {
        if(root == null)
            return 0;

        return dfs2(root, 0, 0);
    }

    public int dfs2(TreeNode node, int num, int sum){
        if(node == null) return sum;

        num = num*10 + node.val;

        // leaf
        if(node.left == null && node.right == null) {
            sum += num;
            return sum;
        }

        // left subtree + right subtree
        sum = dfs2(node.left, num, sum) + dfs2(node.right, num, sum);

        return sum;
    }

    // 这个可以简化成以下代码。因为
    class Solution22 {
        public int sumNumbers(TreeNode root) {
            if(root == null)
                return 0;

            return dfs2(root, 0, 0);
        }

        public int dfs2(TreeNode node, int num, int sum){
            if(node == null) return sum;

            num = num*10 + node.val;

            // leaf
            if(node.left == null && node.right == null) {
                // sum += num;
                return num;
            }

            // left subtree + right subtree
            sum = dfs2(node.left, num, sum) + dfs2(node.right, num, sum);

            return sum;
        }
    }


////////////////////////////////////////////////////////////////////
//Clean Java DFS solution (preorder traversal)
    public class Solution3 {
        int total;

        public int sumNumbers(TreeNode root) {
            total = 0;
            helper(root, 0);
            return total;
        }

        void helper(TreeNode root, int sum) {
            if (root == null) return;

            sum = sum * 10 + root.val;

            if (root.left == null && root.right == null) {
                total += sum;
                return;
            }

            helper(root.left, sum);
            helper(root.right, sum);
        }
    }
////////////////////////////////////////////////////////////////////
    //jiuzhang
public class Jiuzhang {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int prev){
        if(root == null) {
            return 0;
        }

        int sum = root.val + prev * 10;
        if(root.left == null && root.right == null) {
            return sum;
        }

        return dfs(root.left, sum) + dfs(root.right, sum);
    }
}






////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////


}
/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.


 */