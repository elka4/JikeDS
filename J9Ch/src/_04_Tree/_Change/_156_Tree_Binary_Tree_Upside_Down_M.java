package _04_Tree._Change;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;
import java.util.Deque;
import java.util.*;

//  156. Binary Tree Upside Down
//  https://leetcode.com/problems/binary-tree-upside-down/
public class _156_Tree_Binary_Tree_Upside_Down_M {

//-------------------------------------------------------------------------/

    public TreeNode upsideDownBinaryTree1(TreeNode root) {
        if (root == null || root.left == null && root.right == null)
            return root;

        TreeNode newRoot = upsideDownBinaryTree1(root.left);

        root.left.left = root.right;
        root.left.right = root;

        root.left = null;
        root.right = null;

        return newRoot;
    }

    @Test
    public void test01() {
        int[] arr = {5, 3, 7,1,2,6};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        upsideDownBinaryTree1(root).print();
    }
    /*
    root:
                   5
                  / \
                 /   \
                 3   7
                / \ /
                1 2 6

                       1
                      / \
                     /   \
                    /     \
                   /       \
                   2       3
                          / \
                         /   \
                         7   5
                        /
                        6

     */
//-------------------------------------------------------------------------/

    //recurtion
    public TreeNode upsideDownBinaryTree2(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }

        //Assume all lower levels are handled
        TreeNode newRoot = upsideDownBinaryTree2(root.left);

        //Handle current level
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }

    @Test
    public void test02() {
        int[] arr = {5, 3, 7,1,2,6};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        upsideDownBinaryTree2(root).print();
    }


//-------------------------------------------------------------------------/

    public TreeNode UpsideDownBinaryTree3(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null;
        TreeNode next = null;
        TreeNode temp = null;

        while (curr != null) {
            next = curr.left;
            curr.left = temp;
            temp = curr.right;
            curr.right = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    @Test
    public void test03() {
        int[] arr = {5, 3, 7,1,2,6};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        UpsideDownBinaryTree3(root).print();
    }

//-------------------------------------------------------------------------/

    //time O(n) space O(n)
    public TreeNode upsideDownBinaryTree4(TreeNode root) {
        if (root == null) {
            return root;
        }
        Deque<TreeNode> stack = new LinkedList<TreeNode>();

        //pull left nodes into stack， 不能丢了
        while(root != null) {
            stack.offerLast(root);
            root = root.left;
        }

        TreeNode newRoot = stack.pollLast();//左边从下往上第一个
        TreeNode cur = newRoot;

        //change pointers
        while (!stack.isEmpty()) {
            TreeNode oriParent = stack.pollLast();//左边从下往上第二个
            cur.right = oriParent;
            cur.left = oriParent.right;

            cur = oriParent;
            oriParent.left = null;
            oriParent.right = null;
        }
        return newRoot;
    }

    @Test
    public void test04() {
        int[] arr = {5, 3, 7,1,2,6};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        upsideDownBinaryTree4(root).print();
    }

//-------------------------------------------------------------------------/
}
/*
all right nodes are either 1 leaf nodes with a left sibling or 2 empty
reverse it into a new tree where the original right nodes becoming new left leaf nodes.
                     1
                   2   3
                 4   5

                     4
                   5   2
                     3  1

右边的sibling编程左孩子
父节点变成右孩子
 */

/*
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 */