package _04_Tree._Level;

import lib.TreeNode;

import java.util.*;


//  637. Average of Levels in Binary Tree
//  https://leetcode.com/problems/average-of-levels-in-binary-tree/description/
//
public class _637_Tree_Average_of_Levels_in_Binary_Tree_E {
    //https://leetcode.com/articles/average-of-levels/
    //    Approach #1 Using Depth First Search [Accepted]

    //    Approach #2 Breadth First Search [Accepted]

/////////////////////////////////////////////////////////////////
    //Java BFS Solution
    public List<Double> averageOfLevels3(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        if(root == null) return result;
        q.add(root);
        while(!q.isEmpty()) {
            int n = q.size();
            double sum = 0.0;
            for(int i = 0; i < n; i++) {
                TreeNode node = q.poll();
                sum += node.val;
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }
            result.add(sum / n);
        }
        return result;
    }

//////////////////////////////////////////////////////////////////////////////////////////

    public List<Double> averageOfLevels4(TreeNode root) {
        List<Double> sumLs = new ArrayList<Double>();
        List<Integer> cntLs = new ArrayList<Integer>();
        if (root == null) return sumLs;
        helper(root, 0, sumLs, cntLs);
        for (int i = 0; i < sumLs.size(); i++)
            sumLs.set(i, sumLs.get(i) / cntLs.get(i));
        return sumLs;
    }

    private void helper(TreeNode root, int lv, List<Double> sumLs, List<Integer> cntLs) {
        if (root != null) {
            if (sumLs.size() <= lv) {
                sumLs.add((double) root.val);
                cntLs.add(1);
            } else {
                sumLs.set(lv, sumLs.get(lv) + root.val);
                cntLs.set(lv, cntLs.get(lv) + 1);
            }
            helper(root.left, lv + 1, sumLs, cntLs);
            helper(root.right, lv + 1, sumLs, cntLs);
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////
    //Java Concise Postorder Traversal Solution
    public List<TreeNode> findDuplicateSubtrees5(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        postorder(root, new HashMap<>(), res);
        return res;
    }

    public String postorder(TreeNode cur, Map<String, Integer> map, List<TreeNode> res) {
        if (cur == null) return "#";
        String serial = cur.val + "," + postorder(cur.left, map, res) + ","
                + postorder(cur.right, map, res);
        if (map.getOrDefault(serial, 0) == 1)
            res.add(cur);
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        return serial;
    }

//////////////////////////////////////////////////////////////////////////////////////////

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

////////////////////////////////////////////////////////////////////////////////////
}
/*

Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.

Example 1:
Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
Note:
The range of node's value is in the range of 32-bit signed integer.
 */
