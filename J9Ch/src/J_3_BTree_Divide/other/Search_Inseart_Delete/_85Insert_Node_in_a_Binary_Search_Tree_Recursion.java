package J_3_BTree_Divide.other.Search_Inseart_Delete;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

public class _85Insert_Node_in_a_Binary_Search_Tree_Recursion {
    /**
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        if (root == null) {
            return node;
        }
        if (root.val > node.val) {
            root.left = insertNode(root.left, node);
        } else {
            root.right = insertNode(root.right, node);
        }
        return root;
    }

    @Test
    public void test03() {
        int[] arr = {3, 2, 5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        insertNode(root, new TreeNode(4));
        root.print();
    }
}

/*
 * Given a binary search tree and a new tree node, 
 * insert the node into the tree. You should keep the 
 * tree still be a valid binary search tree.

 Notice:
You can assume there is no duplicate values in this tree + node.

Example
Given binary search tree as follow, 
after Insert node 6, the tree should be:

  2             2
 / \           / \
1   4   -->   1   4
   /             / \ 
  3             3   6
Challenge: Can you do it without recursion?

Tags: LintCode Copyright Binary Search Tree
Related Problems: Hard Remove Node in Binary Search Tree 25 %
 * */
