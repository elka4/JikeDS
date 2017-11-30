package _04_Tree._Right;

//  117. Populating Next Right Pointers in Each Node II
//  https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/
//  Tree, Depth-first Search
//  如果给定的二叉树是任意二叉树该（非满二叉树了）该怎么办？之前的解决办法还有效吗？
//  NOT perfect binary tree
//  5:

import lib.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
1、怎么样转到下一层？
    这里可以对当前层的孩子结点进行遍历，遇到第一个孩子结点，把它保存下来，作为下一层的遍历的首结点。

2、怎么样找到下一个结点，把它令为当前结点的next
    同样，对当前层的孩子结点进行遍历，如果存在，则把它令为前一个结点的next。
    如果前一个结点不存在，则把当前孩子结点作为当前结点，然后去求它的next。
 */
public class _117_Populating_Next_Right_Pointers_in_Each_Node_II_H {

//-------------------------------------------------------------------------
    class TreeLinkNode{
        int val;
        TreeLinkNode(int val){
            this.val = val;
        }
        TreeLinkNode left;
        TreeLinkNode right;
        TreeLinkNode next;
    }
//-------------------------------------------------------------------------
    //1
    //O(1) space O(n) complexity Iterative Solution
    //Just share my iterative solution with O(1) space and O(n) Time complexity
    public class Solution {
        //based on level order traversal
        public void connect(TreeLinkNode root) {

            TreeLinkNode head = null; //head of the next level
            TreeLinkNode prev = null; //the leading node on the next level
            TreeLinkNode cur = root;  //current node of current level

            while (cur != null) {

                while (cur != null) { //iterate on the current level
                    //left child
                    if (cur.left != null) {
                        if (prev != null) {
                            prev.next = cur.left;
                        } else {
                            head = cur.left;
                        }
                        prev = cur.left;
                    }
                    //right child
                    if (cur.right != null) {
                        if (prev != null) {
                            prev.next = cur.right;
                        } else {
                            head = cur.right;
                        }
                        prev = cur.right;
                    }
                    //move to next node
                    cur = cur.next;
                }

                //move to next level
                cur = head;
                head = null;
                prev = null;
            }

        }
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        if (root == null) {
            return preorder;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode node = stack.pop();

            preorder.add(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return preorder;
    }

//-------------------------------------------------------------------------
    //2
    //Simple solution using constant space
    /*
    The idea is simple: level-order traversal.
    You can see the following code:
     */
    public void connect2(TreeLinkNode root) {

        while(root != null){
            TreeLinkNode tempChild = new TreeLinkNode(0);
            TreeLinkNode currentChild = tempChild;
            while(root!=null){
                if(root.left != null) {
                    currentChild.next = root.left;
                    currentChild = currentChild.next;
                }
                if(root.right != null) {
                    currentChild.next = root.right;
                    currentChild = currentChild.next;
                }
                root = root.next;
            }
            root = tempChild.next;
        }
    }

//-------------------------------------------------------------------------
    //3
    //    Java solution with constant space
    public void connect3(TreeLinkNode root) {
        TreeLinkNode dummyHead = new TreeLinkNode(0);
        TreeLinkNode pre = dummyHead;
        while (root != null) {
            if (root.left != null) {
                pre.next = root.left;
                pre = pre.next;
            }
            if (root.right != null) {
                pre.next = root.right;
                pre = pre.next;
            }
            root = root.next;
            if (root == null) {
                pre = dummyHead;
                root = dummyHead.next;
                dummyHead.next = null;
            }
        }
    }

//-------------------------------------------------------------------------
    //4
    public void connect4(TreeLinkNode root) {
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

//-------------------------------------------------------------------------
    //5
    // 9Ch
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }

        TreeLinkNode parent = root;
        TreeLinkNode pre;
        TreeLinkNode next;
        while (parent != null) {
            pre = null;
            next = null;
            while (parent != null) {
                if (next == null){
                    next = (parent.left != null) ? parent.left: parent.right;
                }

                if (parent.left != null){
                    if (pre != null) {
                        pre.next = parent.left;
                        pre = pre.next;
                    } else {
                        pre = parent.left;
                    }
                }

                if (parent.right != null) {
                    if (pre != null) {
                        pre.next = parent.right;
                        pre = pre.next;
                    } else {
                        pre = parent.right;
                    }
                }
                parent = parent.next;
            }
            parent = next;
        }
    }
//-------------------------------------------------------------------------
}
/*

Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL

 */

/*
填充每个结点指向其同层右边下一个结点的指针next。如果右边没有下一个结点，则next指针指向NULL。

初始时，所有的next指针全部指向NULL。

 */