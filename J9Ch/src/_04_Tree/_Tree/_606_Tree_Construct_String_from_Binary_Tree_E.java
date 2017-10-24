package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _606_Tree_Construct_String_from_Binary_Tree_E {
    public class Solution {
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



}
