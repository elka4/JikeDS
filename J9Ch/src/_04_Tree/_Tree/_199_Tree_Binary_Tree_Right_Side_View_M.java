package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _199_Tree_Binary_Tree_Right_Side_View_M {
    public class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> result = new ArrayList<Integer>();
            rightView(root, result, 0);
            return result;
        }

        public void rightView(TreeNode curr, List<Integer> result, int currDepth){
            if(curr == null){
                return;
            }
            if(currDepth == result.size()){
                result.add(curr.val);
            }

            rightView(curr.right, result, currDepth + 1);
            rightView(curr.left, result, currDepth + 1);

        }
    }

}
