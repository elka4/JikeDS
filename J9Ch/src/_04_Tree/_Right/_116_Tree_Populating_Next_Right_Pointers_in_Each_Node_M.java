package _04_Tree._Right;

import _04_Tree._Successor_Iterator.TreeLinkNode;

//
//
//
public class _116_Tree_Populating_Next_Right_Pointers_in_Each_Node_M {
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
/*

 */
/*

 */
