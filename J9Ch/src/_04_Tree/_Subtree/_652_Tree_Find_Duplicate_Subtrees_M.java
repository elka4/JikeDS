package _04_Tree._Subtree;

import lib.TreeNode;
import org.junit.Test;

import java.util.*;


//  652. Find Duplicate Subtrees
//  https://leetcode.com/problems/find-duplicate-subtrees/description/
//  Tree
//  297 Serialize and Deserialize Binary Tree
//  449 Serialize and Deserialize BST
//  606 Construct String from Binary Tree
//  3: 三个解法本质上一样，只是细节不同
public class _652_Tree_Find_Duplicate_Subtrees_M {
//-------------------------------------------------------------------------

//-------------------------------------------------------------------------
    //1
    //Java Concise Postorder Traversal Solution
    //用post order 遍历，将树线性化成String，用hashmap存储String和出现次数
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new LinkedList<>();
        postorder(root, new HashMap<>(), result);
        return result;
    }

    public String postorder(TreeNode cur, Map<String, Integer> map, List<TreeNode> result) {
        if (cur == null) return "#";
        String serial = cur.val + "," + postorder(cur.left, map, result)
                + "," + postorder(cur.right, map, result);

        if (map.getOrDefault(serial, 0) == 1) result.add(cur);
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
//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------
    //2  和上面的一样
    // [C++] [Java] Clean Code
    class Solution2 {
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
        System.out.println(new Solution2().findDuplicateSubtrees(root));
    }

//--------------------------------------------------------------------------------
    //3
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

//--------------------------------------------------------------------------------
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