package _04_Tree._Subtree;
import lib.TreeNode;

import java.util.Stack;

//  572. Subtree of Another Tree
//  https://leetcode.com/problems/subtree-of-another-tree/description/
//
public class _572_Tree_Subtree_of_Another_Tree_E {
    //https://leetcode.com/problems/subtree-of-another-tree/solution/

//    Approach #1 Using Preorder Traversal [Accepted]

//    Approach #2 By Comparison of Nodes [Accepted]
    //need to do type in

/////////////////////////////////////////////////////////////////////////////
    //Java Solution, tree traversal

    //    For each node during pre-order traversal of s, use a recursive function
    // isSame to validate if sub-tree started with this node is the same with t.

    public boolean isSubtree3(TreeNode s, TreeNode t) {
        if (s == null) return false;
        if (isSame(s, t)) return true;
        return isSubtree3(s.left, t) || isSubtree3(s.right, t);
    }

    private boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;

        if (s.val != t.val) return false;

        return isSame(s.left, t.left) && isSame(s.right, t.right);
    }

/////////////////////////////////////////////////////////////////////////////
    //Easy O(n) java solution using preorder traversal
    public boolean isSubtree4(TreeNode s, TreeNode t) {
        String spreorder = generatepreorderString(s);
        String tpreorder = generatepreorderString(t);

        return spreorder.contains(tpreorder) ;
    }
    public String generatepreorderString(TreeNode s){
        StringBuilder sb = new StringBuilder();
        Stack<TreeNode> stacktree = new Stack();
        stacktree.push(s);
        while(!stacktree.isEmpty()){
            TreeNode popelem = stacktree.pop();
            if(popelem==null)
                // Appending # inorder to handle same values but not subtree cases
                sb.append(",#");
            else
                sb.append(","+popelem.val);
            if(popelem!=null){
                stacktree.push(popelem.right);
                stacktree.push(popelem.left);
            }
        }
        return sb.toString();
    }

/////////////////////////////////////////////////////////////////////////////
    //Java Concise O(n+m) Time O(n+m) Space
    public boolean isSubtree5(TreeNode s, TreeNode t) {
        // Java uses a naive contains algorithm so to ensure linear time,
        return serialize(s).contains(serialize(t));
        // replace with KMP algorithm
    }

    public String serialize(TreeNode root) {
        StringBuilder res = new StringBuilder();
        serialize(root, res);
        return res.toString();
    }

    private void serialize(TreeNode cur, StringBuilder res) {
        if (cur == null) {res.append(",#"); return;}
        res.append("," + cur.val);
        serialize(cur.left, res);
        serialize(cur.right, res);
    }

/////////////////////////////////////////////////////////////////////////////
}
/*

Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.

 */
