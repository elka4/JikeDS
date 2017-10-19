package _4_Tree.BinaryTree_LinkedList;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

/**
 * 378
 * Convert Binary Search Tree to Doubly Linked List
 * Created by tianhuizhu on 6/28/17.
 */

//  in-order traversal. 这题的本质是双向链表
public class _378_Convert_Binary_Search_Tree_to_Doubly_Linked_List {

    public class DoublyListNode {
        int val;
        DoublyListNode next, prev;
        DoublyListNode(int val) {
            this.val = val;
            this.next = this.prev = null;
        }
        @Override
        public String toString(){
            String str = val + "";
            while(next != null){
                str += "<->" + next.val;
                next = next.next;
            }
            return str += "<->null";
        }

    }

    class ResultType {
        DoublyListNode first, last;

        public ResultType(DoublyListNode first, DoublyListNode last) {
            this.first = first;
            this.last = last;
        }
    }

    /**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    public DoublyListNode bstToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }

        ResultType result = helper(root);
        return result.first;
    }

    public ResultType helper(TreeNode root) {
        if (root == null) {
            return null;
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        DoublyListNode node = new DoublyListNode(root.val);

        ResultType result = new ResultType(null, null);

        if (left == null) {
            result.first = node;
        } else {
            result.first = left.first;
            left.last.next = node;
            node.prev = left.last;
        }

        if (right == null) {
            result.last = node;
        } else {
            result.last = right.last;
            right.first.prev = node;
            node.next = right.first;
        }

        return result;
    }

    /*
        4
       / \
      2   5
     / \
    1   3
    return 1<->2<->3<->4<->5
     */

    @Test
    public void test01(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();

        System.out.println(bstToDoublyList(root));
    }


}
