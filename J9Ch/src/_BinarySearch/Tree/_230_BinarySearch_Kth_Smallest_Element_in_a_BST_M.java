package _BinarySearch.Tree;

import lib.TreeNode;

import java.util.Stack;


public class _230_BinarySearch_Kth_Smallest_Element_in_a_BST_M {

//3 ways implemented in JAVA (Python): Binary Search, in-order iterative & recursive
//    Binary Search (dfs): most preferable
    class Solution1{
        public int kthSmallest(TreeNode root, int k) {
            int count = countNodes(root.left);
            if (k <= count) {
                return kthSmallest(root.left, k);
            } else if (k > count + 1) {
                return kthSmallest(root.right, k-1-count); // 1 is counted as current node
            }

            return root.val;
        }

        public int countNodes(TreeNode n) {
            if (n == null) return 0;

            return 1 + countNodes(n.left) + countNodes(n.right);
        }
    }


//    DFS in-order recursive:

    // better keep these two variables in a wrapper class
    class Solution2{
        private int number = 0;
        private int count = 0;

        public int kthSmallest(TreeNode root, int k) {
            count = k;
            helper(root);
            return number;
        }

        public void helper(TreeNode n) {
            if (n.left != null) helper(n.left);
            count--;
            if (count == 0) {
                number = n.val;
                return;
            }
            if (n.right != null) helper(n.right);
        }
    }


//    DFS in-order iterative:
    class Solution3{
        public int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> st = new Stack<>();

            while (root != null) {
                st.push(root);
                root = root.left;
            }

            while (k != 0) {
                TreeNode n = st.pop();
                k--;
                if (k == 0) return n.val;
                TreeNode right = n.right;
                while (right != null) {
                    st.push(right);
                    right = right.left;
                }
            }

            return -1; // never hit if k is valid
        }
    }

//--------------------------------------------------------------------------------
/*
Two Easiest In Order Traverse (Java)
    In order traverse for BST gives the natural order of numbers. No need to use array.

            Recursive:
*/
    class Solution4{
        int count = 0;
        int result = Integer.MIN_VALUE;

        public int kthSmallest(TreeNode root, int k) {
            traverse(root, k);
            return result;
        }

        public void traverse(TreeNode root, int k) {
            if(root == null) return;
            traverse(root.left, k);
            count ++;
            if(count == k) result = root.val;
            traverse(root.right, k);
        }
    }


//    Iterative:
    class Solution5{
        public int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            TreeNode p = root;
            int count = 0;

            while(!stack.isEmpty() || p != null) {
                if(p != null) {
                    stack.push(p);  // Just like recursion
                    p = p.left;

                } else {
                    TreeNode node = stack.pop();
                    if(++count == k) return node.val;
                    p = node.right;
                }
            }

            return Integer.MIN_VALUE;
        }
    }


}
