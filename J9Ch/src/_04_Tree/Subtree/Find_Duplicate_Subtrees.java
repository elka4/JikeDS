package _04_Tree.Subtree;
import lib.*;
import java.util.*;

public class Find_Duplicate_Subtrees {
    //Java Concise Postorder Traversal Solution
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        postorder(root, new HashMap<>(), res);
        return res;
    }

    public String postorder(TreeNode cur, Map<String, Integer> map, List<TreeNode> res) {
        if (cur == null) return "#";
        String serial = cur.val + "," + postorder(cur.left, map, res) + "," + postorder(cur.right, map, res);
        if (map.getOrDefault(serial, 0) == 1) res.add(cur);
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        return serial;
    }
/////////////////////////////////////////////////////////////////////////////////
    //Clean and easy to understand Java Solution
    class solution2{
        public int largestBSTSubtree(TreeNode root) {
            if (root == null) return 0;
            if (root.left == null && root.right == null) return 1;
            if (isValid(root, null, null)) return countNode(root);
            return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
        }

        public boolean isValid(TreeNode root, Integer min, Integer max) {
            if (root == null) return true;
            if (min != null && min >= root.val) return false;
            if (max != null && max <= root.val) return false;
            return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
        }

        public int countNode(TreeNode root) {
            if (root == null) return 0;
            if (root.left == null && root.right == null) return 1;
            return 1 + countNode(root.left) + countNode(root.right);
        }
    }

/////////////////////////////////////////////////////////////////////////////////
    //Java 1ms solution, by passing a three-element array up to parent
    class solution3{
        private int largestBSTSubtreeSize = 0;
        public int largestBSTSubtree(TreeNode root) {
            helper(root);
            return largestBSTSubtreeSize;
        }

        private int[] helper(TreeNode root) {
            // return 3-element array:
            // # of nodes in the subtree, leftmost value, rightmost value
            // # of nodes in the subtree will be -1 if it is not a BST
            int[] result = new int[3];
            if (root == null) {
                return result;
            }
            int[] leftResult = helper(root.left);
            int[] rightResult = helper(root.right);
            if ((leftResult[0] == 0 || leftResult[0] > 0 && leftResult[2] <= root.val) &&
                    (rightResult[0] == 0 || rightResult[0] > 0 && rightResult[1] >= root.val)) {
                int size = 1 + leftResult[0] + rightResult[0];
                largestBSTSubtreeSize = Math.max(largestBSTSubtreeSize, size);
                int leftBoundary = leftResult[0] == 0 ? root.val : leftResult[1];
                int rightBoundary = rightResult[0] == 0 ? root.val : rightResult[2];
                result[0] = size;
                result[1] = leftBoundary;
                result[2] = rightBoundary;
            } else {
                result[0] = -1;
            }
            return result;
        }
    }

/////////////////////////////////////////////////////////////////////////////////



}
/*
Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1:
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:
      2
     /
    4
and
    4
Therefore, you need to return above trees' root in the form of a list.

 */