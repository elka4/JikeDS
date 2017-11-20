package _04_Tree._Right;

import lib.TreeNode;

import java.util.*;


//  545. Boundary of Binary Tree
//  https://leetcode.com/problems/boundary-of-binary-tree
//  Tree
//  Binary Tree Right Side View
//  6: 3, 4, 6
public class _545_Tree_Boundary_of_Binary_Tree_M {


    //https://leetcode.com/articles/boundary-of-binary-tree/
//-------------------------------------------------------------------------------------
    //1
    //        Approach #1 Simple Solution [Accepted]

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public class Solution1 {

        public boolean isLeaf(TreeNode t) {
            return t.left == null && t.right == null;
        }

        public void addLeaves(List<Integer> res, TreeNode root) {
            if (isLeaf(root)) {
                res.add(root.val);
            } else {
                if (root.left != null) {
                    addLeaves(res, root.left);
                }
                if (root.right != null) {
                    addLeaves(res, root.right);
                }
            }
        }

        public List<Integer> boundaryOfBinaryTree(TreeNode root) {
            ArrayList<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            if (!isLeaf(root)) {
                res.add(root.val);
            }
            TreeNode t = root.left;
            while (t != null) {
                if (!isLeaf(t)) {
                    res.add(t.val);
                }
                if (t.left != null) {
                    t = t.left;
                } else {
                    t = t.right;
                }

            }
            addLeaves(res, root);
            Stack<Integer> s = new Stack<>();
            t = root.right;
            while (t != null) {
                if (!isLeaf(t)) {
                    s.push(t.val);
                }
                if (t.right != null) {
                    t = t.right;
                } else {
                    t = t.left;
                }
            }
            while (!s.empty()) {
                res.add(s.pop());
            }
            return res;
        }
    }
//-------------------------------------------------------------------------------------
    //2
    //        Approach #2 Using PreOrder Traversal [Accepted]


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public class Solution2 {
        public List < Integer > boundaryOfBinaryTree(TreeNode root) {
            List < Integer > left_boundary = new LinkedList < > (),
                    right_boundary = new LinkedList < > (), leaves = new LinkedList < > ();

            preorder(root, left_boundary, right_boundary, leaves, 0);
            left_boundary.addAll(leaves);
            left_boundary.addAll(right_boundary);
            return left_boundary;
        }

        public boolean isLeaf(TreeNode cur) {
            return (cur.left == null && cur.right == null);
        }

        public boolean isRightBoundary(int flag) {
            return (flag == 2);
        }

        public boolean isLeftBoundary(int flag) {
            return (flag == 1);
        }

        public boolean isRoot(int flag) {
            return (flag == 0);
        }

        public int leftChildFlag(TreeNode cur, int flag) {
            if (isLeftBoundary(flag) || isRoot(flag))
                return 1;
            else if (isRightBoundary(flag) && cur.right == null)
                return 2;
            else return 3;
        }

        public int rightChildFlag(TreeNode cur, int flag) {
            if (isRightBoundary(flag) || isRoot(flag))
                return 2;
            else if (isLeftBoundary(flag) && cur.left == null)
                return 1;
            else return 3;
        }

        public void preorder(TreeNode cur, List < Integer > left_boundary,
                             List < Integer > right_boundary, List < Integer > leaves, int flag) {
            if (cur == null)
                return;
            if (isRightBoundary(flag))
                right_boundary.add(0, cur.val);
            else if (isLeftBoundary(flag) || isRoot(flag))
                left_boundary.add(cur.val);
            else if (isLeaf(cur))
                leaves.add(cur.val);

            preorder(cur.left, left_boundary, right_boundary, leaves, leftChildFlag(cur, flag));
            preorder(cur.right, left_boundary, right_boundary, leaves, rightChildFlag(cur, flag));
        }
    }

//-------------------------------------------------------------------------------------
    //3
    //Java(12ms) - left boundary, left leaves, right leaves, right boundary
    //三个recursion分别处理左边界，叶子，右边界。
    //这个解法是一个非常好的找左边界，叶子，右边界的recursive的解法，要记住。
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


//-------------------------------------------------------------------------------------
    //4
    //    [Java] [C++] Clean Code (1 Pass perorder postorder hybrid)
    //DFS
    //真的非常简洁
    public List<Integer> boundaryOfBinaryTree4(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root != null) {
            result.add(root.val);
            getBounds(root.left, result, true, false);
            getBounds(root.right, result, false, true);
        }
        return result;
    }
    //
    private void getBounds(TreeNode node, List<Integer> result, boolean lb, boolean rb) {
        if (node == null) return;
        if (lb) result.add(node.val);                                       //左边界

        if (!lb && !rb && node.left == null && node.right == null)          //叶子

        getBounds(node.left, result,     lb,                    rb && node.right == null);
        getBounds(node.right, result, lb && node.left == null,  rb);

        if (rb) result.add(node.val);                                       //右边界
    }

//-------------------------------------------------------------------------------------
    //5
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

        else if (flag <= 1 || cur.left == null && cur.right == null)
            left.add(cur.val);

        preorder(cur.left, left, right, flag <= 1 ? 1 : (flag == 2 && cur.right == null) ? 2 : 3);
        preorder(cur.right, left, right, flag % 2 == 0 ? 2 : (flag == 1 && cur.left == null) ? 1 : 3);
    }

//-------------------------------------------------------------------------------------
    //6
    //iterative
    //三个iterative分别处理左边界，叶子，右边界。
    //这个解法是一个非常好的找左边界，叶子，右边界的iterative的解法，要记住。
    private List<Integer> bound = new ArrayList<>();

    public List<Integer> boundaryOfBinaryTree6(TreeNode root) {
        if (root == null) return bound;
        if (!(root.left == null && root.right == null)) {
            bound.add(root.val);
        }


        //左边界
        TreeNode l = root.left;
        while (l != null && !(l.left == null && l.right == null)) {
            bound.add(l.val);
            if (l.left != null) l = l.left;
            else l = l.right;
        }

        //叶子
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while (p != null || stack.size() != 0) {
            if (p != null) {
                stack.add(p);
                if (p.left == null && p.right == null)
                    bound.add(p.val);
                p = p.left;
            }
            else {
                TreeNode tmp = stack.pollLast();
                p = tmp.right;
            }
        }

        //右边界
        TreeNode r = root.right;
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


//-------------------------------------------------------------------------------------
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


/*
题目大意：
给定二叉树，逆时针输出二叉树的边界。边界包括左边界、叶子节点和右边界。

左边界是指从根出发到最左侧节点经过的路径。右边界是指从根出发到最右侧节点经过的路径。

如果根节点不包含左子树或者右子树，则对应的边界不存在。注意此定义是指整棵二叉树，不包含子树。

最左侧节点是指从根节点出发尽量向左走，如果不能则向右走，到达的叶子结点。

最右侧节点定义参考最左侧节点，左右互换即可。

解题思路：
左边界、右边界根据题意求解。叶子节点通过先序遍历得到。


 */