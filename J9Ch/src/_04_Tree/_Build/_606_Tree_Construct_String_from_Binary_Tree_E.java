package _04_Tree._Build;

import lib.TreeNode;


//  606. Construct String from Binary Tree
//  https://leetcode.com/problems/construct-string-from-binary-tree/description/
//
public class _606_Tree_Construct_String_from_Binary_Tree_E {

    //https://leetcode.com/articles/construct-string-from-binary-tree/

//    Approach #1 Using Recursion [Accepted]
//    Approach #2 Iterative Method Using stack [Accepted]

//////////////////////////////////////////////////////////////////////////////////////Î
    public class Solution3 {
        public String tree2str(TreeNode t) {
            if (t == null) return "";

            String result = t.val + "";

            String left = tree2str(t.left);
            String right = tree2str(t.right);

            if (left == "" && right == "") return result;
            if (left == "") return result + "()" + "(" + right + ")";
            if (right == "") return result + "(" + left + ")";
            return result + "(" + left + ")" + "(" + right + ")";
        }
    }
    public String tree2str(TreeNode t) {
        if(t == null) return "";
        String left = tree2str(t.left);
        String right = tree2str(t.right);

        if(left == "" && right == "") return  t.val + "";

        return t.val + "(" + left  + ")" + (right == "" ? "" : "(" + right + ")");
    }
//    EDIT : Changed from (left == "" ? "" : left) to left.


//////////////////////////////////////////////////////////////////////////////////////Î
//Java Solution, Tree Traversal
    public class Solution4 {
        public String tree2str(TreeNode t) {
            if (t == null) return "";

            String result = t.val + "";

            String left = tree2str(t.left);
            String right = tree2str(t.right);

            if (left == "" && right == "") return result;
            if (left == "") return result + "()" + "(" + right + ")";
            if (right == "") return result + "(" + left + ")";
            return result + "(" + left + ")" + "(" + right + ")";
        }
    }

//    Java simple recursion
    public String tree2str5(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        helper(sb,t);
        return sb.toString();
    }
    public void helper(StringBuilder sb,TreeNode t){
        if(t!=null){
            sb.append(t.val);
            if(t.left!=null||t.right!=null){
                sb.append("(");
                helper(sb,t.left);
                sb.append(")");
                if(t.right!=null){
                    sb.append("(");
                    helper(sb,t.right);
                    sb.append(")");
                }
            }
        }
    }

//    Java 1 liner

    class Solution6 {
        public String tree2str(TreeNode t) {
            return t == null ? "" : t.val + (t.left != null ? "(" + tree2str(t.left) + ")" : t.right != null ? "()" : "")
                    + (t.right != null ? "(" + tree2str(t.right) + ")" : "");
        }
    }
//////////////////////////////////////////////////////////////////////////////////////Î

}
/*
You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.

The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.

Example 1:
Input: Binary tree: [1,2,3,4]
       1
     /   \
    2     3
   /
  4

Output: "1(2(4))(3)"

Explanation: Originallay it needs to be "1(2(4)())(3()())",
but you need to omit all the unnecessary empty parenthesis pairs.
And it will be "1(2(4))(3)".
Example 2:
Input: Binary tree: [1,2,3,null,4]
       1
     /   \
    2     3
     \
      4

Output: "1(2()(4))(3)"

Explanation: Almost the same as the first example,
except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between
 */
/*

 */
