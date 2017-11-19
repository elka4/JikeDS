package _04_Tree._BST;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

//  99. Recover Binary Search Tree
//  https://leetcode.com/problems/recover-binary-search-tree/description/
//  Tree, DFS
//  5:
public class _099_Tree_Recover_Binary_Search_Tree_H {

//-------------------------------------------------------------------------------
    //1
    public class Solution {
        TreeNode firstElement = null;
        TreeNode secondElement = null;
        // The reason for this initialization is to avoid null pointer exception
        // in the first comparison when prevElement has not been initialized
        TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

        public void recoverTree(TreeNode root) {

            // In order traversal to find the two elements
            traverse(root);

            // Swap the values of the two nodes
            int temp = firstElement.val;
            firstElement.val = secondElement.val;
            secondElement.val = temp;
        }

        private void traverse(TreeNode root) {

            if (root == null)
                return;

            traverse(root.left);

            // Start of "do some business",
            // If first element has not been found, assign it to prevElement
            // (refer to 6 in the example above)
            if (firstElement == null && prevElement.val >= root.val) {
                firstElement = prevElement;
            }

            // If first element is found, assign the second element to the root
            // (refer to 2 in the example above)
            if (firstElement != null && prevElement.val >= root.val) {
                secondElement = root;
            }
            prevElement = root;

            // End of "do some business"

            traverse(root.right);
        }
    }

    @Test
    public void test01() {
        TreeNode root = new TreeNode(7);
        root.left  = new TreeNode(9);
        root.right = new TreeNode(5);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(6));
        System.out.println("root: ");
        root.print();
        new Solution().recoverTree(root);
        root.print();
    }

//---------------------------------------------------------------------------------
    //2
    //这个是干嘛的？
    public void morrisTraversal(TreeNode root){
        TreeNode temp = null;
        while(root!=null){
            if(root.left!=null){
                // connect threading for root
                temp = root.left;
                while(temp.right!=null && temp.right != root)
                    temp = temp.right;
                // the threading already exists
                if(temp.right!=null){
                    temp.right = null;
                    System.out.println(root.val);
                    root = root.right;
                }else{
                    // construct the threading temp.right = root;
                    root = root.left;
                }
            }else{
                System.out.println(root.val);
                root = root.right;
            }
        }
    }

    @Test
    public void test02() {
        TreeNode root = new TreeNode(7);
        root.left  = new TreeNode(9);
        root.right = new TreeNode(5);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(6));
        System.out.println("root: ");
        root.print();
        morrisTraversal(root);
        root.print();
    }
    /*
    root:
   7
  / \
 /   \
 9   5
/ \
4 6
     */
//---------------------------------------------------------------------------------
    //3
    public void recoverTree(TreeNode root) {
        TreeNode pre = null;
        TreeNode first = null, second = null; // Morris Traversal
        TreeNode temp = null;
        while(root!=null){
            if(root.left!=null){
                // connect threading for root
                temp = root.left;
                while(temp.right!=null && temp.right != root)
                    temp = temp.right;
                // the threading already exists
                if(temp.right!=null){
                    if(pre!=null && pre.val > root.val){
                        if(first==null){
                            first = pre;second = root;
                        }
                        else{
                            second = root;
                        }
                    }
                    pre = root;
                    temp.right = null;
                    root = root.right;
                }else{
                    // construct the threading
                    temp.right = root;
                    root = root.left;
                }
            }else{
                if(pre!=null && pre.val > root.val){
                    if(first==null){
                            first = pre;second = root;
                    }
                    else{
                        second = root;
                    }
                }
                pre = root;
                root = root.right;
            }
        }
        // swap two node values;
        if(first!= null && second != null){ int t = first.val;
            first.val = second.val; second.val = t;
        }
    }

    @Test
    public void test03() {
        TreeNode root = new TreeNode(7);
        root.left  = new TreeNode(9);
        root.right = new TreeNode(5);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(6));
        System.out.println("root: ");
        root.print();
        recoverTree(root);
        root.print();
    }
    /*
    root:
                   7
                  / \
                 /   \
                 9   5
                / \
                4 6

                   7
                  / \
                 /   \
                 5   9
                / \
                4 6
     */
//---------------------------------------------------------------------------------
    //4
    class Recover_BST {

        TreeNode first;
        TreeNode second;
        TreeNode pre;

        public void inorder(TreeNode root){
            if(root==null)
                return;

            inorder(root.left);

            if(pre==null){
                pre=root;
            }else{
                if(root.val<pre.val){
                    if(first==null){
                        first=pre;
                    }

                    second=root;
                }
                pre=root;
            }

            inorder(root.right);
        }

        public void recoverTree(TreeNode root) {
            if(root==null)
                return;

            inorder(root);
            if(second!=null && first !=null){
                int val = second.val;
                second.val = first.val;
                first.val = val;
            }

        }
    }

    @Test
    public void test04() {
        TreeNode root = new TreeNode(7);
        root.left  = new TreeNode(9);
        root.right = new TreeNode(5);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(6));
        System.out.println("root: ");
        root.print();
        new Recover_BST().recoverTree(root);
        root.print();
    }

//---------------------------------------------------------------------------------
    //5
    //jiuzhang
    public class Jiuzhang {
        private TreeNode firstElement = null;
        private TreeNode secondElement = null;
        private TreeNode lastElement = new TreeNode(Integer.MIN_VALUE);

        private void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            traverse(root.left);
            if (firstElement == null && root.val < lastElement.val) {
                firstElement = lastElement;
            }
            if (firstElement != null && root.val < lastElement.val) {
                secondElement = root;
            }
            lastElement = root;
            traverse(root.right);
        }

        public void recoverTree(TreeNode root) {
            // traverse and get two elements
            traverse(root);
            // swap
            int temp = firstElement.val;
            firstElement.val = secondElement.val;
            secondElement.val = temp;
        }
    }

    @Test
    public void test05() {
        TreeNode root = new TreeNode(7);
        root.left  = new TreeNode(9);
        root.right = new TreeNode(5);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(6));
        System.out.println("root: ");
        root.print();
        new Jiuzhang().recoverTree(root);
        root.print();
    }
//---------------------------------------------------------------------------------
}
/*
LeetCode – Recover Binary Search Tree (Java)

Two elements of a binary search tree (BST) are swapped by mistake. Recover the tree without changing its structure.

Java Solution

Inorder traveral will return values in an increasing order. So if an element is less than its previous element,the previous element is a swapped node.
 */

/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

 */