package chapter3_BianryTree;

public class _474Lowest_Common_Ancestor_II {
class ParentTreeNode {
	      public ParentTreeNode parent, left, right;
}

 /**
 * @param root: The root of the tree
 * @param A, B: Two node in the tree
 * @return: The lowest common ancestor of A and B
 */
public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root,
                                             ParentTreeNode A,
                                             ParentTreeNode B) {
    // Write your code here
    if (root == null) {
        return null;
    }
    if (root == A || root == B) {
        return root;
    }

    ParentTreeNode left = lowestCommonAncestorII(root.left, A, B);
    ParentTreeNode right = lowestCommonAncestorII(root.right, A, B);
    if (left != null && right != null) {
        return root;
    } else if (left != null) {
        return left;
    } else if (right != null) {
        return right;
    } else {
        return null;
    }        
}
}

/*Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.

The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.

The node has an extra attribute parent which point to the father of itself. The root's parent is null.

Have you met this question in a real interview? Yes
Example
For the following binary tree:

  4
 / \
3   7
   / \
  5   6
LCA(3, 5) = 4

LCA(5, 6) = 7

LCA(6, 7) = 7

Tags 
LintCode Copyright Binary Tree
Related Problems 
Medium Lowest Common Ancestor 36 %*/