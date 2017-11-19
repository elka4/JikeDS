package _04_Tree._Right;

//  117. Populating Next Right Pointers in Each Node II
//  https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/
//
public class _117_Tree_Populating_Next_Right_Pointers_in_Each_Node_II_H {

    //O(1) space O(n) complexity Iterative Solution
    //Just share my iterative solution with O(1) space and O(n) Time complexity
    class TreeLinkNode{
        int val;
        TreeLinkNode(int val){
            this.val = val;
        }
        TreeLinkNode left;
        TreeLinkNode right;
        TreeLinkNode next;
    }
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

//-------------------------------------------------------------------------/

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

//-------------------------------------------------------------------------/

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

//-------------------------------------------------------------------------/

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
    //jiuzhang
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
