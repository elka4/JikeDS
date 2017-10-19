package _4_Tree._2Tree_Class;

import CtCILibrary.AssortedMethods;
import CtCILibrary.TreeNode;
import org.junit.Test;

public class _7_DeleteANodeinBST {

public TreeNode delete (TreeNode root, int val) {
        if (root == null)
            return root;
        TreeNode dummy = new TreeNode (Integer.MIN_VALUE);
        dummy.left = root;
        TreeNode prev = dummy;
        TreeNode cur = root;

        //1. Find the node with val
        while (cur != null && cur.val != val) {
            prev = cur;
            if (cur.val < val) {
                cur = cur.right;
            }
            else {
                cur = cur.left;
            }
        }
        if (cur == null)
            return dummy.left;//Not found
        TreeNode target = cur;

    //2. Case 1: Two child
    if (cur.left != null && cur.right != null) {
      //2.1 Find the leftMost node in right subTree
      prev = cur;
      cur = cur.right;
      while (cur.left != null) {
          prev = cur;
          cur = cur.left;
      }
      //2.2 Change value
      target.val = cur.val;
  }

      //3. Case 2: Delete cur, which has one or no child
      if (cur.left == null) {
          if (prev.left == cur)
              prev.left = cur.right;
          else
              prev.right = cur.right;
      } else {
          if (prev.left == cur)
              prev.left = cur.left;
          else
              prev.right = cur.left;
      }
      return dummy.left;
    }

    @Test
    public void test01(){
        int[] arr = {1,3,5,6,7,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        delete(root, 6);
        root.print();
    }

    @Test
    public void test02(){
        int[] arr = {1,3,5,6,7,8,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        delete(root, 5);
        root.print();
    }

    @Test
    public void test03(){
        int[] arr = {1,3,5,6,7,8,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        delete(root, 3);
        root.print();
    }



}
