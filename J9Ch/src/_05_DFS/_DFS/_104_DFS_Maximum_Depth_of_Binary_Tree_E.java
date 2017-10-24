package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _104_DFS_Maximum_Depth_of_Binary_Tree_E {

    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }
//////////////////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */