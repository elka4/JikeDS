package _04_Tree.Subtree;
import lib.*;
import org.junit.Test;

import java.util.*;

// 652. Find Duplicate Subtrees
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

    @Test
    public void test01(){
        TreeNode root = TreeNode.createMinimalBST(new int[]{2,1,3});
        root.left.left  = new TreeNode(4);
        root.right.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(4);
        root.print();
        System.out.println(findDuplicateSubtrees(root));
    }
    /*
       1
      / \
     /   \
    /     \
   /       \
   2       3
  /       / \
 /       /   \
 4       2   4
        /
        4

[4 , 2 ]
     */
/////////////////////////////////////////////////////////////////////////////////


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
    // [C++] [Java] Clean Code
class Solution4 {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, List<TreeNode>> map = new HashMap<String, List<TreeNode>>();
        List<TreeNode> dups = new ArrayList<TreeNode>();

        serialize(root, map);

        for (List<TreeNode> group : map.values()) {
            if (group.size() > 1) dups.add(group.get(0));
        }
        return dups;
    }

    private String serialize(TreeNode node, Map<String, List<TreeNode>> map) {
        if (node == null) return "";
        String s = "(" + serialize(node.left, map) + node.val + serialize(node.right, map) + ")";
        if (!map.containsKey(s)) map.put(s, new ArrayList<TreeNode>());
        map.get(s).add(node);
        return s;
    }
}

    @Test
    public void test04(){
        TreeNode root = TreeNode.createMinimalBST(new int[]{2,1,3});
        root.left.left  = new TreeNode(4);
        root.right.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(4);
        root.print();
        System.out.println(new Solution4().findDuplicateSubtrees(root));
    }

/////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////

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