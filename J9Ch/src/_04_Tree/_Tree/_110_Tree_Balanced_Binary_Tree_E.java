package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _110_Tree_Balanced_Binary_Tree_E {

    public boolean isBalanced(TreeNode root) { if(root==null){
        return true; }
        return height(root)!=-1;
    }
    public int height(TreeNode node){
        if(node==null){ return 0;
        }
        int lH=height(node.left); if(lH==-1){
            return -1; }
        int rH=height(node.right); if(rH==-1){
            return -1; }
        if(lH-rH<-1 || lH-rH>1){ return -1;
        }
        return Math.max(lH,rH)+1;
    }
}
