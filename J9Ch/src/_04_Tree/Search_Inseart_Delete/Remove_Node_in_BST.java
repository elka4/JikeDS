package _04_Tree.Search_Inseart_Delete;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

public class Remove_Node_in_BST {
	 /**
     * @param root: The root of the binary search tree.
     * @param value: Remove the node with given value.
     * @return: The root of the binary search tree after removal.
     */
    public TreeNode removeNode(TreeNode root, int value) {
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        
        TreeNode parent = findNode(dummy, root, value);
        TreeNode node;
        if (parent.left != null && parent.left.val == value) {
            node = parent.left;
        } else if (parent.right != null && parent.right.val == value) {
            node = parent.right;
        } else {
            return dummy.left;
        }
        
        deleteNode(parent, node);
        
        return dummy.left;
    }
    
    private TreeNode findNode(TreeNode parent, TreeNode node, int value) {
        if (node == null) {
            return parent;
        }
        
        if (node.val == value) {
            return parent;
        }
        if (value < node.val) {
            return findNode(node, node.left, value);
        } else {
            return findNode(node, node.right, value);
        }
    }
    
    private void deleteNode(TreeNode parent, TreeNode node) {
        if (node.right == null) {
            if (parent.left == node) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
        } else {
            TreeNode temp = node.right;
            TreeNode father = node;
            
            while (temp.left != null) {
                father = temp;
                temp = temp.left;
            }
            
            if (father.left == temp) {
                father.left = temp.right;
            } else {
                father.right = temp.right;
            }
            
            if (parent.left == node) {
                parent.left = temp;
            } else {
                parent.right = temp;
            }
            
            temp.left = node.left;
            temp.right = node.right;
        }
    }

    @Test
    public void test04() {
        int[] arr = {5, 3, 6};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);

        System.out.println("root: ");
        root.print();

        deleteNode(root, root.left);
        root.print();
    }
/////////////////////////////////////////////////////////
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
/////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////


}

//http://www.mathcs.emory.edu/~cheung/Courses/171/Syllabus/9-BinTree/BST-delete.html

/*Given a root of Binary Search Tree with unique value
*  for each node. Remove the node with given value. 
*  If there is no such a node with given value in the
*   binary search tree, do nothing. You should keep the 
*   tree still a binary search tree after removal.

Example:
Given binary search tree:

  5
 / \
3   6
/ \
2   4
Remove 3, you can either return:

  5
 / \
2   6
 \
  4
or

  5
 / \
4   6
/
2
Tags: LintCode Copyright Binary Search Tree
Related Problems: Easy Insert Node in a Binary Search Tree 41 %*/
