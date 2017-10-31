package _04_Tree.Lowest_Common_Ancestor;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

/** 578. Lowest Common Ancestor III
 * Medium
 * Created by tianhuizhu on 6/27/17.
 */

//node A or node B may not exist in tree.
//Return null if LCA does not exist.

public class Lowest_Common_Ancestor_III {
    class ResultType {
        public boolean a_exist, b_exist;
        public TreeNode node;
        ResultType(boolean a, boolean b, TreeNode n) {
            a_exist = a;
            b_exist = b;
            node = n;
        }
    }

    /**
     * @param root The root of the binary tree.
     * @param A and B two nodes
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        ResultType rt = helper(root, A, B);

        if (rt.a_exist && rt.b_exist)
            return rt.node;
        else
            return null;
    }

    public ResultType helper(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null)
            return new ResultType(false, false, null);

        // Divide
        ResultType left_rt = helper(root.left, A, B);
        ResultType right_rt = helper(root.right, A, B);

        // Conquer
        boolean a_exist = left_rt.a_exist || right_rt.a_exist || root == A;
        boolean b_exist = left_rt.b_exist || right_rt.b_exist || root == B;


        // if (root == null || root == node1 || root == node2) return root;
        if (root == A || root == B)
            return new ResultType(a_exist, b_exist, root);

        // if (left != null && right != null) return root;
        if (left_rt.node != null && right_rt.node != null)
            return new ResultType(a_exist, b_exist, root);

        // if (left != null)   return left;
        if (left_rt.node != null)
            return new ResultType(a_exist, b_exist, left_rt.node);

        // if (right != null)  return right;
        if (right_rt.node != null)
            return new ResultType(a_exist, b_exist, right_rt.node);

        return new ResultType(a_exist, b_exist, null);


    }


    @Test
    public void test01() {
        int[] arr = {4, 3, 7};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.setLeftChild(new TreeNode(5));
        root.right.setRightChild(new TreeNode(6));
        System.out.println("root: ");
        root.print();
        TreeNode node4 = root;
        TreeNode node3 = root.left;
        TreeNode node7 = root.right;
        TreeNode node5 = root.right.left;
        TreeNode node6 = root.right.right;
        lowestCommonAncestor3(root, node3,node5).print();
        lowestCommonAncestor3(root, node5,node6).print();
        lowestCommonAncestor3(root, node6,node7).print();

    }
    @Test
    public void test02(){
        int[] arr = {4,3,7};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.setLeftChild(new TreeNode(5));
        root.right.setRightChild(new TreeNode(6));
        System.out.println("root: ");
        root.print();
        lowestCommonAncestor3(root, root.right.right, new TreeNode(4)).print();

    }
        /*

  4
 / \
3   7
   / \
  5   6
LCA(3, 5) = 4

LCA(5, 6) = 7

LCA(6, 7) = 7

     */
        
/////////////////////////////////////////////////////////////////////////////

}
/*
给一棵二叉树和二叉树中的两个节点，找到这两个节点的最近公共祖先LCA。

两个节点的最近公共祖先，是指两个节点的所有父亲节点中（包括这两个节点），离这两个节点最近的公共的节点。

返回 null 如果两个节点在这棵树上不存在最近公共祖先的话。


 */
