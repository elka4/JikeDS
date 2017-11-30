package _String._DFS;
import java.util.*;
import org.junit.Test;
import lib.*;

//
//  606. Construct String from Binary Tree
//  https://leetcode.com/problems/construct-string-from-binary-tree/description/
//
public class _606_Construct_String_from_Binary_Tree_E {
//------------------------------------------------------------------------------
//https://leetcode.com/problems/construct-string-from-binary-tree/solution/
//------------------------------------------------------------------------------
//Approach #1 Using Recursion [Accepted]
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public class Solution1 {
        public String tree2str(TreeNode t) {
            if(t==null)
                return "";
            if(t.left==null && t.right==null)
                return t.val+"";
            if(t.right==null)
                return t.val+"("+tree2str(t.left)+")";
            return t.val+"("+tree2str(t.left)+")("+tree2str(t.right)+")";
        }
    }




//------------------------------------------------------------------------------
//Approach #2 Iterative Method Using stack [Accepted]

    public class Solution2 {
        public String tree2str(TreeNode t) {
            if (t == null)
                return "";
            Stack < TreeNode > stack = new Stack < > ();
            stack.push(t);
            Set < TreeNode > visited = new HashSet < > ();
            StringBuilder s = new StringBuilder();
            while (!stack.isEmpty()) {
                t = stack.peek();
                if (visited.contains(t)) {
                    stack.pop();
                    s.append(")");
                } else {
                    visited.add(t);
                    s.append("(" + t.val);
                    if (t.left == null && t.right != null)
                        s.append("()");
                    if (t.right != null)
                        stack.push(t.right);
                    if (t.left != null)
                        stack.push(t.left);
                }
            }
            return s.substring(1, s.length() - 1);
        }
    }



//------------------------------------------------------------------------------
    //Java Solution, Tree Traversal
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


//------------------------------------------------------------------------------

//Java simple recursion
class Solution4{
    public String tree2str(TreeNode t) {
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
}
//------------------------------------------------------------------------------
    //[C++] [Java] 1 liner
class Solution5 {
    public String tree2str(TreeNode t) {
        return t == null ? "" : t.val + (t.left != null ? "(" + tree2str(t.left) + ")" : t.right != null ? "()" : "")
                + (t.right != null ? "(" + tree2str(t.right) + ")" : "");
    }
}


    //Java Cozy
    class Solution6 {
        public String tree2str(TreeNode t) {
            if (t == null) return "";
            String s = new String(t.val + "");
            if (t.left != null) s += "(" + tree2str(t.left) + ")";
            else if (t.right != null) s += "()";
            if (t.right != null) s += "(" + tree2str(t.right) + ")";
            return s;
        }
    }

    //Java StringBuilder
    class Solution7 {
        public String tree2str(TreeNode t) {
            StringBuilder sb = new StringBuilder("");
            tree2str(t, sb);
            return sb.toString();
        }

        private void tree2str(TreeNode t, StringBuilder sb) {
            if (t == null) return;
            sb.append(t.val + "");
            if (t.left != null) {
                sb.append("(");
                tree2str(t.left, sb);
                sb.append(")");
            }
            else if (t.right != null) sb.append("()");

            if (t.right != null) {
                sb.append("(");
                tree2str(t.right, sb);
                sb.append(")");
            }
        }
    }
//------------------------------------------------------------------------------
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
except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
Companies
Amazon
Related Topics
String Tree
Similar Questions
 */