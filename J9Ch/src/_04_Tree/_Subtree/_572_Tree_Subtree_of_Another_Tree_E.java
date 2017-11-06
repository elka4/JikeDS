package _04_Tree._Subtree;

import lib.TreeNode;

import java.util.Stack;


//
//
//
public class _572_Tree_Subtree_of_Another_Tree_E {

//    For each node during pre-order traversal of s, use a recursive function isSame to validate if sub-tree started with this node is the same with t.

    public class Solution {
        public boolean isSubtree(TreeNode s, TreeNode t) {
            if (s == null) return false;
            if (isSame(s, t)) return true;
            return isSubtree(s.left, t) || isSubtree(s.right, t);
        }

        private boolean isSame(TreeNode s, TreeNode t) {
            if (s == null && t == null) return true;
            if (s == null || t == null) return false;

            if (s.val != t.val) return false;

            return isSame(s.left, t.left) && isSame(s.right, t.right);
        }
    }

    //Easy O(n) java solution using preorder traversal
    public class Solution2 {
        public boolean isSubtree(TreeNode s, TreeNode t) {
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
                    sb.append(",#"); // Appending # inorder to handle same values but not subtree cases
                else
                    sb.append(","+popelem.val);
                if(popelem!=null){
                    stacktree.push(popelem.right);
                    stacktree.push(popelem.left);
                }
            }
            return sb.toString();
        }
    }

//Java Concise O(n+m) Time O(n+m) Space


    class Solution3{
        public boolean isSubtree(TreeNode s, TreeNode t) {
            return serialize(s).contains(serialize(t)); // Java uses a naive contains algorithm so to ensure linear time,
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
    }

}
/*

 */
/*

 */
