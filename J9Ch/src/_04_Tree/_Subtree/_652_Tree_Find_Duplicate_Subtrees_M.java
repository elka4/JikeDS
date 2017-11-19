package _04_Tree._Subtree;

import lib.TreeNode;
import org.junit.Test;

import java.util.*;


//  652. Find Duplicate Subtrees
//  https://leetcode.com/problems/find-duplicate-subtrees/description/
//
public class _652_Tree_Find_Duplicate_Subtrees_M {

    //method1
    public boolean findTarget1(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }

    public boolean dfs(TreeNode root, HashSet<Integer> set, int k){
        if(root == null)return false;
        if(set.contains(k - root.val))return true;
        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }

//-------------------------------------------------------------------------/
    //method2
    public boolean findTarget2(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        for(int i = 0, j = nums.size()-1; i<j;){
            if(nums.get(i) + nums.get(j) == k)return true;
            if(nums.get(i) + nums.get(j) < k)i++;
            else j--;
        }
        return false;
    }

    public void inorder(TreeNode root, List<Integer> nums){
        if(root == null)return;
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

//-------------------------------------------------------------------------/
    // method3
    public boolean findTarget3(TreeNode root, int k) {

        return dfs3(root, root,  k);
    }

    public boolean dfs3(TreeNode root,  TreeNode cur, int k){
        if(cur == null)return false;
        return search(root, cur, k - cur.val) ||
                dfs3(root, cur.left, k) || dfs3(root, cur.right, k);
    }

    public boolean search(TreeNode root, TreeNode cur, int value){
        if(root == null)return false;
        return (root.val == value) && (root != cur)
                || (root.val < value) && search(root.right, cur, value)
                || (root.val > value) && search(root.left, cur, value);
    }

//-------------------------------------------------------------------------/////////

    //Java Concise Postorder Traversal Solution
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        postorder(root, new HashMap<>(), res);
        return res;
    }

    public String postorder(TreeNode cur, Map<String, Integer> map, List<TreeNode> res) {
        if (cur == null) return "#";
        String serial = cur.val + "," + postorder(cur.left, map, res)
                + "," + postorder(cur.right, map, res);
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
//-------------------------------------------------------------------------///////////////////
    //Java 1ms solution, by passing a three-element array up to parent

    class solution4{
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

//-------------------------------------------------------------------------///////////////////
    // [C++] [Java] Clean Code
    class Solution5 {
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
        System.out.println(new Solution5().findDuplicateSubtrees(root));
    }

//-------------------------------------------------------------------------///////////////////
//6
public List<TreeNode> findDuplicateSubtrees6(TreeNode root) {
    List<TreeNode> result = new LinkedList<>();
    Map<String,Integer> map = new HashMap<>();
    findDuplicateSubtreesHelperPostOrder(map,root,result);

    return result;

}
    public String findDuplicateSubtreesHelperPostOrder(Map<String,
            Integer> map,TreeNode root,List<TreeNode> result){
        if(root==null)
            return "#";
        String path = root.val +
                findDuplicateSubtreesHelperPostOrder(map,root.left,result) +
                findDuplicateSubtreesHelperPostOrder(map,root.right,result);
        Integer times = map.getOrDefault(path,0);
        if(times==1){
            result.add(root);
        }
        map.put(path,times+1);
        return path;
    }

//-------------------------------------------------------------------------///////////////////
}

/*
Given a binary tree, return all duplicate subtrees.
For each kind of duplicate subtrees, you only need to return the root node of any one of them.

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