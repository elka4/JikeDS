package _04_Tree._Successor_Iterator;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;
import java.util.*;

//  285. Inorder Successor in BST
//  https://leetcode.com/problems/inorder-successor-in-bst/description/
//  Tree
//  Binary Tree Inorder Traversal, Binary Search Tree Iterator
//  11: 1给出了前后node的recursion解法！！！！！ 2给出了后的iterative。能不能写出前node？
//  不管是recursion还是iteration，关键都是要里用BST非左既右的特点。
public class _285_Tree_Inorder_Successor_in_BST_M {
//--------------------------------------------------------------------------
    //1
    //    Share my Java recursive solution
    //    Just want to share my recursive solution for both getting
    // the successor and predecessor for a given node in BST.
    //    Successor
    public TreeNode successor(TreeNode root, TreeNode p) {
        if (root == null)
            return null;

        if (root.val <= p.val) {
            return successor(root.right, p);
        } else {
            TreeNode left = successor(root.left, p);
            return (left != null) ? left : root;
        }
    }

    //    Predecessor
    public TreeNode predecessor(TreeNode root, TreeNode p) {
        if (root == null)
            return null;

        if (root.val >= p.val) {
            return predecessor(root.left, p);
        } else {
            TreeNode right = predecessor(root.right, p);
            return (right != null) ? right : root;
        }
    }

//--------------------------------------------------------------------------
    //2
    /*
    Java/Python solution, O(h) time and O(1) space, iterative
The inorder traversal of a BST is the nodes in ascending order. To find a successor, you just need to find the smallest one that is larger than the given value since there are no duplicate values in a BST. It just like the binary search in a sorted list. The time complexity should be O(h) where h is the depth of the result node. succ is a pointer that keeps the possible successor. Whenever you go left the current root is the new possible successor, otherwise the it remains the same.

Only in a balanced BST O(h) = O(log n). In the worst case h can be as large as n.
     */
    public TreeNode inorderSuccessor01(TreeNode root, TreeNode p) {
        TreeNode succ = null;
        while (root != null) {
            if (p.val < root.val) {
                succ = root;
                root = root.left;
            }
            else
                root = root.right;
        }
        return succ;
    }

    //http://www.makeinjava.com/find-inorder-predecessor-binary-search-tree-bst-examples/
    //iterative 的找前node
    public class InorderPredecessor {
        private TreeNode max(TreeNode root) {
            // we found the max node
            if (root.right == null) {
                return root;
            }
            return max(root.right);
        }

        public TreeNode predecessor(TreeNode root, TreeNode node) {
            // Example 3 or Example 4
            if (node.left != null)
                return max(node.left);

            // Example 1 or Example 2
            TreeNode predecessor = null;
            // Start from root and search for predecessor down the tree

            while (root != null) {

                if (node.val == root.val) {
                    // by now we might found our predecessor
                    break;
                } else if (node.val < root.val) {
                    root = root.left;
                } else if (node.val > root.val) {
                    predecessor = root;
                    root = root.right;
                }
            }
            return predecessor;
        }
    }
//--------------------------------------------------------------------------
    //3

/*    *Java* 5ms short code with explanations
    The idea is to compare root's value with p's value if root is not null, and consider the following two cases:

    root.val > p.val. In this case, root can be a possible answer, so we store the root node first and call it res. However, we don't know if there is anymore node on root's left that is larger than p.val. So we move root to its left and check again.

    root.val <= p.val. In this case, root cannot be p's inorder successor, neither can root's left child. So we only need to consider root's right child, thus we move root to its right and check again.

    We continuously move root until exhausted. To this point, we only need to return the res in case 1.

    Code in Java:*/

    public TreeNode inorderSuccessor33(TreeNode root, TreeNode p) {
        TreeNode res = null;
        while(root!=null) {
            if(root.val > p.val) {
                res = root;
                root = root.left;
            }
            else root = root.right;
        }
        return res;
    }
//--------------------------------------------------------------------------------
    //4
 /*
    10 (and 4) lines O(h) Java/C++
Update: Ugh, turns out I didn't think it through and the big case distinction is unnecessary. Just search from root to bottom, trying to find the smallest node larger than p and return the last one that was larger.
     */
public TreeNode inorderSuccessor4(TreeNode root, TreeNode p) {
    if (p.right != null) {
        p = p.right;
        while (p.left != null)
            p = p.left;
        return p;
    }
    TreeNode candidate = null;
    while (root != p)
        root = (p.val > root.val) ? root.right : (candidate = root).left;
    return candidate;
}
//--------------------------------------------------------------------------------
    //5
    // 9Ch
    public TreeNode inorderSuccessor5(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null && root != p) {
            if (root.val > p.val) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }

        if (root == null) {
            return null;
        }

        if (root.right == null) {
            return successor;
        }

        root = root.right;
        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    @Test
    public void test01(){
        int[] arr = {7,5,10};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        root.right.setLeftChild(new TreeNode(8));
        root.right.setRightChild(new TreeNode(12));
        System.out.println("root: ");
        root.print();
        inorderSuccessor5(root, root).print();
        //System.out.println(inorderSuccessor(root, root).val);
        //System.out.println(inorderSuccessor(root, root.right).val);
        inorderSuccessor5(root, root.left).print();
        inorderSuccessor5(root, root.right.left).print();
    }

//--------------------------------------------------------------------------------
    //6
    //time O(logn) spaceO(1)

    public TreeNode inorderSuccessor6(TreeNode root, TreeNode p){
        if(root == null) {
            return null;
        }
        //Case 1: has right child
        if (p.right != null) {
            return searchLeftMost(p.right);
        } else {
            //Case 2: no right -> find the first left turing on the searching path
            return searchPar(root, p);
        }
    }

    private TreeNode searchPar(TreeNode root, TreeNode p) {
        TreeNode par = null;
        while(root != p) {
            if(p.val < root.val) {
                par = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return par;
    }

    private TreeNode searchLeftMost(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

//--------------------------------------------------------------------------------
    //7
    //mehtod 2
    public TreeNode inorderSuccessor7(TreeNode root, TreeNode p) {
        TreeNode par = null;
        /*
        退出条件就是root == null, 也就是在上一次循环中root.left或者root.rigth为null，
        如果是"p.val < root.val", 说明当前root太大了，要变小，所以 root = root.left;
        这时候记录当前root为parent。

        如果是"else" 说明当前root太小了，要变大，所以 root = root.right;
         */
        while (root != null){
            if (p.val < root.val) {
                par = root;
                System.out.println("p.val < root.val: ");
                root.print();
                root = root.left;

            } else {
                System.out.println("else: ");
                root.print();
                root = root.right;
            }
        }
        return par;

    }

    @Test
    public void test03() {
        int[] arr = {5, 3, 7,1,2,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        /*
           5
          / \
         /   \
         3   7
        / \ / \
        1 2 6 8
         */
        inorderSuccessor3(root,root.left).print();
        /*     root.left is 3

                p.val < root.val:
                   5
                  / \
                 /   \
                 3   7
                / \ / \
                1 2 6 8

                else:
                 3
                / \
                1 2

                else:
                2

                   5
                  / \
                 /   \
                 3   7
                / \ / \
                1 2 6 8
         */
        inorderSuccessor3(root,root.right.left).print();
        /*  root.right.left is 6
            else:
               5
              / \
             /   \
             3   7
            / \ / \
            1 2 6 8

            p.val < root.val:
             7
            / \
            6 8

            else:
            6

             7
            / \
            6 8
         */
        inorderSuccessor3(root,root.right).print();
        /*   root.right is 7
                else:
                   5
                  / \
                 /   \
                 3   7
                / \ / \
                1 2 6 8

                else:
                 7
                / \
                6 8

                p.val < root.val:  5 < 8
                8

                8
         */
    }

//--------------------------------------------------------------------------------
    //8
    // version: 高频题班
    public TreeNode inorderSuccessorX(TreeNode root, TreeNode p) {
        // write your code here
        if (root == null || p == null) {
            return null;
        }

        if (root.val <= p.val) {
            return inorderSuccessorX(root.right, p);
        } else {
            TreeNode left = inorderSuccessorX(root.left, p);
            return (left != null) ? left : root;
        }
    }

    @Test
    public void test02(){
        int[] input = {1,2};
        TreeNode root = TreeNode.createMinimalBST(input);
        root.print();
        inorderSuccessorX(root, new TreeNode(1)).print();

    }

    @Test
    public void test02_2(){
        int[] input = {1,2,3};
        TreeNode root = TreeNode.createMinimalBST(input);
        root.print();
        inorderSuccessorX(root, new TreeNode(2)).print();

    }


//--------------------------------------------------------------------------------
    //9
    //Java Solution 1

    public TreeNode inorderSuccessor11(TreeNode root, TreeNode p) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root==null || p==null)
            return null;

        stack.push(root);
        boolean isNext = false;
        while(!stack.isEmpty()){
            TreeNode top = stack.pop();

            if(top.right==null&&top.left==null){
                if(isNext){
                    return top;
                }

                if(p.val==top.val){
                    isNext = true;
                }
                continue;
            }

            if(top.right!=null){
                stack.push(top.right);
                top.right=null;
            }

            stack.push(top);

            if(top.left!=null){
                stack.push(top.left);
                top.left=null;
            }
        }

        return null;
    }
    //Time is O(n), Space is O(n).

//--------------------------------------------------------------------------------
    //10
    //Java Solution 2

    public TreeNode inorderSuccessor22(TreeNode root, TreeNode p) {
        if(root==null)
            return null;

        TreeNode next = null;
        TreeNode c = root;
        while(c!=null && c.val!=p.val){
            if(c.val > p.val){
                next = c;
                c = c.left;
            }else{
                c= c.right;
            }
        }

        if(c==null)
            return null;

        if(c.right==null)
            return next;

        c = c.right;
        while(c.left!=null)
            c = c.left;

        return c;
    }
    //Time is O(log(n)) and space is O(1).


//--------------------------------------------------------------------------------
    //11
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        while (root != null && root.val <= p.val)
            root = root.right;
        if (root == null)
            return null;
        TreeNode left = inorderSuccessor2(root.left, p);
        return (left != null && left.val > p.val) ? left : root;
    }

    //    And another:
    public TreeNode inorderSuccessor3(TreeNode root, TreeNode p) {
        while (root != null && root.val <= p.val)
            root = root.right;
        TreeNode left = root == null ? null : inorderSuccessor3(root.left, p);
        return (left != null && left.val > p.val) ? left : root;
    }


//--------------------------------------------------------------------------
}
/*  leetcode
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.
 */

/* lintcode
 * Given a binary search tree (See Definition) and a node in it,
 *  find the in-order successor of that node in the BST.

If the given node has no in-order successor in the tree, return null.

 Notice

It's guaranteed p is one node in the given tree.
(You can directly compare the memory address to find p)

-------------------------------------------------
Example
Given tree = [2,1] and node = 1:

  2
 /
1
return node 2.

-------------------------------------------------
Example
Given tree = [2,1,3] and node = 2:

  2
 / \
1   3
return node 3.
-------------------------------------------------
Challenge: O(h), where h is the height of the BST.

Tags: Binary Search Tree Binary Tree
Related Problems:
Medium Validate Binary Search Tree 21 %
Hard Binary Search Tree Iterator 33 %*/

