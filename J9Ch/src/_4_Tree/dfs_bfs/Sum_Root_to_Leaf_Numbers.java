package _4_Tree.dfs_bfs;
import java.util.*;
/*
LeetCode – Sum Root to Leaf Numbers (Java)

Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number. Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Return the sum = 12 + 13 = 25.
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


/////////////////////////////////////////

    //Same approach, but simpler coding style.

    public int sumNumbers2(TreeNode root) {
        if(root == null)
            return 0;

        return dfs(root, 0, 0);
    }

    public int dfs(TreeNode node, int num, int sum){
        if(node == null) return sum;

        num = num*10 + node.val;

        // leaf
        if(node.left == null && node.right == null) {
            sum += num;
            return sum;
        }

        // left subtree + right subtree
        sum = dfs(node.left, num, sum) + dfs(node.right, num, sum);
        return sum;
    }

}
