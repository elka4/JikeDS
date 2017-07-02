package Depth_first_Search_54;

/**
 * Created by tianhuizhu on 6/19/17.
 */
public class b_116_Populating_Next_Right_Pointers_in_Each_Node {
    public class Solution1 {
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
}
