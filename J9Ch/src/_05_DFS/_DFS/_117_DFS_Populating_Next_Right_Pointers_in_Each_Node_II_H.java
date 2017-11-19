package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _117_DFS_Populating_Next_Right_Pointers_in_Each_Node_II_H {

    //O(1) space O(n) complexity Iterative Solution
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


    public class Solution2{

        public void connect(TreeLinkNode root) {
            TreeLinkNode head=root;//The left most node in the lower level
            TreeLinkNode prev=null;//The previous node in the lower level
            TreeLinkNode curr=null;//The current node in the upper level
            while (head!=null){
                curr=head;
                prev=null;
                head=null;
                while (curr!=null){
                    if (curr.left!=null){
                        if (prev!=null)
                            prev.next=curr.left;
                        else
                            head=curr.left;
                        prev=curr.left;
                    }
                    if (curr.right!=null){
                        if (prev!=null)
                            prev.next=curr.right;
                        else
                            head=curr.right;
                        prev=curr.right;
                    }
                    curr=curr.next;
                }
            }
        }
    }

    public class Solution3 {
        public void connect(TreeLinkNode root) {
            TreeLinkNode nextHead = new TreeLinkNode(0);
            nextHead.next = root;
            while(nextHead.next != null){
                TreeLinkNode tail = nextHead;
                TreeLinkNode n = nextHead.next;
                nextHead.next = null;
                for(; n != null; n = n.next){
                    if(n.left != null){
                        tail.next = n.left;
                        tail = tail.next;
                    }

                    if(n.right != null){
                        tail.next = n.right;
                        tail = tail.next;
                    }
                }
            }
        }
    }

    public void connect(TreeLinkNode root) {
        if (root == null)
            return;
        TreeLinkNode dummy = new TreeLinkNode(0);
        dummy.next = root;
        while (dummy.next != null) {
            TreeLinkNode cur = dummy.next, pre = dummy;
            dummy.next = null;
            while (cur != null) {
                if (cur.left != null)
                    pre = pre.next = cur.left;
                if (cur.right != null)
                    pre = pre.next = cur.right;
                cur = cur.next;
            }
        }
    }

//-------------------------------------------------------------------------///
public class Jiuzhang {
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
}



//-------------------------------------------------------------------------///






}
/*

 */