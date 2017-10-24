package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _111_Tree_Minimum_Depth_of_Binary_Tree_E {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;

    }



}
