package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _116_DFS_Populating_Next_Right_Pointers_in_Each_Node_M {

    public class Solution {
        public void connect(TreeLinkNode root) {
            TreeLinkNode level_start=root;
            while(level_start!=null){
                TreeLinkNode cur=level_start;
                while(cur!=null){
                    if(cur.left!=null) cur.left.next=cur.right;
                    if(cur.right!=null && cur.next!=null) cur.right.next=cur.next.left;

                    cur=cur.next;
                }
                level_start=level_start.left;
            }
        }
    }

    class Solution2{
        public void connect(TreeLinkNode root) {
            if(root == null) return;
            helper(root.left, root.right);
        }

        void helper(TreeLinkNode node1, TreeLinkNode node2){
            if(node1 == null) return;
            node1.next = node2;
            helper(node1.left, node1.right);
            helper(node2.left, node2.right);
            helper(node1.right, node2.left);
        }
    }
}
