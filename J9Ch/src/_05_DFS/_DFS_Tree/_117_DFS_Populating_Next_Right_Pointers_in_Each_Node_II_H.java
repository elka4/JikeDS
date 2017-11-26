package _05_DFS._DFS_Tree;
import java.util.*;
//  117. Populating Next Right Pointers in Each Node II
//  https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/
//
public class _117_DFS_Populating_Next_Right_Pointers_in_Each_Node_II_H {
 //Java solution with O(1) memory+ O(n) time
    public void connect1(TreeLinkNode root) {
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

//-----------------------------------------------------------------------------
    // My recursive solution(Java)
    public void connect2(TreeLinkNode root) {
        if(root == null)
            return;

        if(root.left != null){
            root.left.next = root.right;
            if(root.next != null)
                root.right.next = root.next.left;
        }

        connect2(root.left);
        connect2(root.right);
    }

//-----------------------------------------------------------------------------
    //Java solution traversing by level without extra space
    public void connect3(TreeLinkNode root) {
        if(root==null) return;
        TreeLinkNode cur = root;
        TreeLinkNode nextLeftmost = null;

        while(cur.left!=null){
            nextLeftmost = cur.left; // save the start of next level
            while(cur!=null){
                cur.left.next=cur.right;
                cur.right.next = cur.next==null? null : cur.next.left;
                cur=cur.next;
            }
            cur=nextLeftmost;  // point to next level
        }
    }
//-------------------------------------------------------------------------------

    //Java Solution 1 - Simple
    public void connect4(TreeLinkNode root) {
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

//------------------------------------------------------------------------------

    //Java Solution 2

    //This solution is easier to understand. You can use the example tree above
    // to walk through the algorithm. The basic idea is have 4 pointers to move
    // towards right on two levels (see comments in the code).

    //populating-next-right-pointers-in-each-node

    public void connect5(TreeLinkNode root) {
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

//------------------------------------------------------------------------------

    // 9Ch
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }

        TreeLinkNode parent = root;
        TreeLinkNode next = parent.left;
        while (parent != null && next != null) {
            TreeLinkNode prev = null;
            while (parent != null) {
                if (prev == null) {
                    prev = parent.left;
                } else {
                    prev.next = parent.left;
                    prev = prev.next;
                }
                prev.next = parent.right;
                prev = prev.next;
                parent = parent.next;
            }
            parent = next;
            next = parent.left;
        }
    }
//-------------------------------------------------------------------------------
}
/*

Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
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
