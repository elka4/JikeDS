package _04_Tree._Right;

import lib.TreeNode;

import java.util.*;


//
//
//
public class _545_Tree_Boundary_of_Binary_Tree_M {
    class Solution{
        List<Integer> nodes = new ArrayList<>(1000);
        public List<Integer> boundaryOfBinaryTree(TreeNode root) {

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
    }


    class Solution2{
        public List<Integer> boundaryOfBinaryTree(TreeNode root) {
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
    }


    public class Solution3 {

        private List<Integer> bound = new ArrayList<>();

        public List<Integer> boundaryOfBinaryTree(TreeNode root) {
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
    }
}
/*

 */
/*

 */
