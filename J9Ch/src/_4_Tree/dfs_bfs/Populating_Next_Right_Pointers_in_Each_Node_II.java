package _4_Tree.dfs_bfs;
import lib.*;
/*
LeetCode – Populating Next Right Pointers in Each Node II (Java)

Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Analysis

Similar to Populating Next Right Pointers in Each Node, we have 4 pointers at 2 levels of the tree.


 */

public class Populating_Next_Right_Pointers_in_Each_Node_II {

    class TreeLinkNode{
        int val;
        TreeLinkNode next;
        TreeLinkNode left;
        TreeLinkNode right;
    }

    public void connect(TreeLinkNode root) {
        if(root == null)
            return;

        TreeLinkNode lastHead = root;//prevous level's head 
        TreeLinkNode lastCurrent = null;//previous level's pointer
        TreeLinkNode currentHead = null;//currnet level's head 
        TreeLinkNode current = null;//current level's pointer

        while(lastHead!=null){
            lastCurrent = lastHead;

            while(lastCurrent!=null){
                //left child is not null
                if(lastCurrent.left!=null)    {
                    if(currentHead == null){
                        currentHead = lastCurrent.left;
                        current = lastCurrent.left;
                    }else{
                        current.next = lastCurrent.left;
                        current = current.next;
                    }
                }

                //right child is not null
                if(lastCurrent.right!=null){
                    if(currentHead == null){
                        currentHead = lastCurrent.right;
                        current = lastCurrent.right;
                    }else{
                        current.next = lastCurrent.right;
                        current = current.next;
                    }
                }

                lastCurrent = lastCurrent.next;
            }

            //update last head
            lastHead = currentHead;
            currentHead = null;
        }
    }



////////////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////////////


}
