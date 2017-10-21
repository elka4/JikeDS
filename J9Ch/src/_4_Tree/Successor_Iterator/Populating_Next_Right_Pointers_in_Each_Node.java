package _4_Tree.Successor_Iterator;

import java.util.LinkedList;
/*
LeetCode – Populating Next Right Pointers in Each Node (Java)

Given the following perfect binary tree,

         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:

         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
 */


public class Populating_Next_Right_Pointers_in_Each_Node {

    class TreeLinkNode{
        int val;
        TreeLinkNode next;
        TreeLinkNode left;
        TreeLinkNode right;
    }

    //Java Solution 1 - Simple

    public void connect(TreeLinkNode root) {
        if(root==null)
            return;

        LinkedList<TreeLinkNode> nodeQueue = new LinkedList<TreeLinkNode>();
        LinkedList<Integer> depthQueue = new LinkedList<Integer>();

        if(root!=null){
            nodeQueue.offer(root);
            depthQueue.offer(1);
        }

        while(!nodeQueue.isEmpty()){
            TreeLinkNode topNode = nodeQueue.poll();
            int depth = depthQueue.poll();

            if(depthQueue.isEmpty()){
                topNode.next = null;
            }else if(depthQueue.peek()>depth){
                topNode.next = null;
            }else{
                topNode.next = nodeQueue.peek();
            }

            if(topNode.left!=null){
                nodeQueue.offer(topNode.left);
                depthQueue.offer(depth+1);
            }

            if(topNode.right!=null){
                nodeQueue.offer(topNode.right);
                depthQueue.offer(depth+1);
            }
        }
    }


////////////////////////////////////////////////////////////////////////////

    //Java Solution 2

    //This solution is easier to understand. You can use the example tree above to walk through the algorithm. The basic idea is have 4 pointers to move towards right on two levels (see comments in the code).

    //populating-next-right-pointers-in-each-node

    public void connect2(TreeLinkNode root) {
        if(root == null)
            return;

        TreeLinkNode lastHead = root;//prevous level's head
        TreeLinkNode lastCurrent = null;//previous level's pointer
        TreeLinkNode currentHead = null;//currnet level's head
        TreeLinkNode current = null;//current level's pointer

        while(lastHead!=null){
            lastCurrent = lastHead;

            while(lastCurrent!=null){
                if(currentHead == null){
                    currentHead = lastCurrent.left;
                    current = lastCurrent.left;
                }else{
                    current.next = lastCurrent.left;
                    current = current.next;
                }

                if(currentHead != null){
                    current.next = lastCurrent.right;
                    current = current.next;
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
