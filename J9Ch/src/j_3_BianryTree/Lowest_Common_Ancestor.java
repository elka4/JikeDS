package j_3_BianryTree;

import lib.TreeNode;

import java.util.ArrayList;

/**
 * Created by tianhuizhu on 6/19/17.
 */
public class Lowest_Common_Ancestor {
    class _88Lowest_Common_Ancestor_DivideConquer {
        // 在root为根的二叉树中找A,B的LCA:
        // 如果找到了就返回这个LCA
        // 如果只碰到A，就返回A
        // 如果只碰到B，就返回B
        // 如果都没有，就返回null
        public TreeNode lowestCommonAncestor(TreeNode root,
                                             TreeNode node1,
                                             TreeNode node2) {

            if (root == null || root == node1 || root == node2) {
                return root;
            }

            // Divide
            TreeNode left = lowestCommonAncestor(root.left, node1, node2);
            TreeNode right = lowestCommonAncestor(root.right, node1, node2);

            // Conquer
            if (left != null && right != null) {
                return root;
            }
            if (left != null) {
                return left;
            }
            if (right != null) {
                return right;
            }
            return null;
        }
    }
//-------------------------------------------------------------------------//////////
//Version 1: Traditional Method， 如果有父亲节点
@SuppressWarnings("all")

public class _88Lowest_Common_Ancestor_parentNode {
    class TreeNode {
        public TreeNode parent, left, right;
    }

    private ArrayList<TreeNode> getPath2Root(TreeNode node) {
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        while (node != null) {
            list.add(node);
            node = node.parent;
        }
        return list;
    }

    public TreeNode lowestCommonAncestor(TreeNode node1, TreeNode node2) {
        ArrayList<TreeNode> list1 = getPath2Root(node1);
        ArrayList<TreeNode> list2 = getPath2Root(node2);

        int i, j;
        for (i = list1.size() - 1, j = list2.size() - 1;
             i >= 0 && j >= 0; i--, j--) {

            if (list1.get(i) != list2.get(j)) {
                return list1.get(i).parent;
            }
        }
        return list1.get(i+1);
    }
}

/*
  //不用recursion的做法？
    class ResultType{
    		TreeNode lca;
    		int count;
    }
    class Lowest_Common_Ancestor3 {
    		
    }
 */



/*
 * Given the root and two nodes in a Binary Tree. Find the 
 * lowest common ancestor(LCA) of the two nodes.

The lowest common ancestor is the node with largest depth
 which is the ancestor of both nodes.

Example: For the following binary tree:

  4
 / \
3   7
   / \
  5   6
LCA(3, 5) = 4

LCA(5, 6) = 7

LCA(6, 7) = 7

Tags：LintCode Copyright LinkedIn Binary Tree Facebook
Related Problems： Easy Lowest Common Ancestor II 29 %
 * 
 * */
//-------------------------------------------------------------------------//////////
//-------------------------------------------------------------------------//////////
//-------------------------------------------------------------------------//////////
}
