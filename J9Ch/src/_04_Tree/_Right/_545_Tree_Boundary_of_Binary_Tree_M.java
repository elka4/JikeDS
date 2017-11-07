package _04_Tree._Right;

import lib.TreeNode;

import java.util.*;


//  545. Boundary of Binary Tree
//  https://leetcode.com/problems/boundary-of-binary-tree
//
public class _545_Tree_Boundary_of_Binary_Tree_M {


    //https://leetcode.com/articles/boundary-of-binary-tree/

    //        Approach #1 Simple Solution [Accepted]
    //        Approach #2 Using PreOrder Traversal [Accepted]



//////////////////////////////////////////////////////////////////////////////////////
    //Java(12ms) - left boundary, left leaves, right leaves, right boundary
    List<Integer> nodes = new ArrayList<>(1000);

    public List<Integer> boundaryOfBinaryTree3(TreeNode root) {
        if(root == null) return nodes;

        nodes.add(root.val);
        leftBoundary(root.left);
        leaves(root.left);
        leaves(root.right);
        rightBoundary(root.right);

        return nodes;
    }
    public void leftBoundary(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return;
        nodes.add(root.val);
        if(root.left == null) leftBoundary(root.right);
        else leftBoundary(root.left);
    }
    public void rightBoundary(TreeNode root) {
        if(root == null || (root.right == null && root.left == null)) return;
        if(root.right == null)rightBoundary(root.left);
        else rightBoundary(root.right);
        nodes.add(root.val); // add after child visit(reverse)
    }
    public void leaves(TreeNode root) {
        if(root == null) return;
        if(root.left == null && root.right == null) {
            nodes.add(root.val);
            return;
        }
        leaves(root.left);
        leaves(root.right);
    }


//////////////////////////////////////////////////////////////////////////////////////
    //    [Java] [C++] Clean Code (1 Pass perorder postorder hybrid)

    public List<Integer> boundaryOfBinaryTree4(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root != null) {
            res.add(root.val);
            getBounds(root.left, res, true, false);
            getBounds(root.right, res, false, true);
        }
        return res;
    }

    private void getBounds(TreeNode node, List<Integer> res, boolean lb, boolean rb) {
        if (node == null) return;
        if (lb) res.add(node.val);
        if (!lb && !rb && node.left == null && node.right == null) res.add(node.val);
        getBounds(node.left, res, lb, rb && node.right == null);
        getBounds(node.right, res, lb && node.left == null, rb);
        if (rb) res.add(node.val);
    }

//////////////////////////////////////////////////////////////////////////////////////
    /*
    Java Preorder Single Pass O(n) Solution
We perform a single preorder traversal of the tree, keeping tracking of the left boundary and middle leaf nodes and the right boundary nodes in the process. A single flag is used to designate the type of node during the preorder traversal. Its values are:
0 - root, 1 - left boundary node, 2 - right boundary node, 3 - middle node.
     */
    public List<Integer> boundaryOfBinaryTree5(TreeNode root) {
        List<Integer> left = new LinkedList<>(), right = new LinkedList<>();
        preorder(root, left, right, 0);
        left.addAll(right);
        return left;
    }

    public void preorder(TreeNode cur, List<Integer> left, List<Integer> right, int flag) {
        if (cur == null) return;
        if (flag == 2) right.add(0, cur.val);
        else if (flag <= 1 || cur.left == null && cur.right == null) left.add(cur.val);
        preorder(cur.left, left, right, flag <= 1 ? 1 : (flag == 2 && cur.right == null) ? 2 : 3);
        preorder(cur.right, left, right, flag % 2 == 0 ? 2 : (flag == 1 && cur.left == null) ? 1 : 3);
    }

//////////////////////////////////////////////////////////////////////////////////////
    private List<Integer> bound = new ArrayList<>();

    public List<Integer> boundaryOfBinaryTree6(TreeNode root) {
        if (root == null) return bound;
        if (!(root.left == null && root.right == null)) {
            bound.add(root.val);
        }
        TreeNode l = root.left;
        TreeNode r = root.right;

        while (l != null && !(l.left == null && l.right == null)) {
            bound.add(l.val);
            if (l.left != null) l = l.left;
            else l = l.right;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;

        while (p != null || stack.size() != 0) {
            if (p != null) {
                stack.add(p);
                if (p.left == null && p.right == null) bound.add(p.val);
                p = p.left;
            }
            else {
                TreeNode tmp = stack.pollLast();
                p = tmp.right;
            }
        }

        List<Integer> right = new ArrayList<>();
        while (r != null && !(r.left == null && r.right == null)) {
            right.add(r.val);
            if (r.right != null) r = r.right;
            else r = r.left;
        }
        for (int i = right.size() - 1; i >= 0; i--) {
            bound.add(right.get(i));
        }
        return bound;
    }


//////////////////////////////////////////////////////////////////////////////////////
}
/*

 */
/*
Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.

Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.

The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.

The right-most node is also defined by the same way with left and right exchanged.

Example 1
Input:
  1
   \
    2
   / \
  3   4

Ouput:
[1, 3, 4, 2]

Explanation:
The root doesn't have left subtree, so the root itself is left boundary.
The leaves are node 3 and 4.
The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
So order them in anti-clockwise without duplicates and we have [1,3,4,2].
Example 2
Input:
    ____1_____
   /          \
  2            3
 / \          /
4   5        6
   / \      / \
  7   8    9  10

Ouput:
[1,2,4,7,8,9,10,6,3]

Explanation:
The left boundary are node 1,2,4. (4 is the left-most node according to definition)
The leaves are node 4,7,8,9,10.
The right boundary are node 1,3,6,10. (10 is the right-most node).
So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
 */
