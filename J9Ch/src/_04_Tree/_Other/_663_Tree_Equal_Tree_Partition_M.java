package _04_Tree._Other;
import lib.TreeNode;

import java.util.HashMap;
import java.util.Map;

//  663. Equal Tree Partition
//  https://leetcode.com/problems/equal-tree-partition/description/
public class _663_Tree_Equal_Tree_Partition_M {

    /*
    [Java/C++] Simple solution with only one HashMap<>.
    The idea is to use a hash table to record all the different sums of each subtree in the tree. If the total sum of the tree is sum, we just need to check if the hash table constains sum/2. The following code has the correct result at a special case when the tree is [0,-1,1], which many solutions dismiss. I think this test case should be added.
     */
    public boolean checkEqualTree1(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int sum = getsum(root, map);
        if(sum == 0)return map.getOrDefault(sum, 0) > 1;
        return sum%2 == 0 && map.containsKey(sum/2);
    }

    public int getsum(TreeNode root, Map<Integer, Integer> map ){
        if(root == null)return 0;
        int cur = root.val + getsum(root.left, map) + getsum(root.right, map);
        map.put(cur, map.getOrDefault(cur,0) + 1);
        return cur;
    }

//-------------------------------------------------------------------------///////////
    //Java solution, Tree traversal and Sum
    boolean equal = false;
    long total = 0;

    public boolean checkEqualTree2(TreeNode root) {
        if (root.left == null && root.right == null) return false;
        total = getTotal(root);
        checkEqual(root);
        return equal;
    }

    private long getTotal(TreeNode root) {
        if (root == null) return 0;
        return getTotal(root.left) + getTotal(root.right) + root.val;
    }

    private long checkEqual(TreeNode root) {
        if (root == null || equal) return 0;

        long curSum = checkEqual(root.left) + checkEqual(root.right) + root.val;
        if (total - curSum == curSum) {
            equal = true;
            return 0;
        }
        return curSum;
    }


//-------------------------------------------------------------------------//
}
/*

Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.

Example 1:
Input:
    5
   / \
  10 10
    /  \
   2   3

Output: True
Explanation:
    5
   /
  10

Sum: 15

   10
  /  \
 2    3

Sum: 15
Example 2:
Input:
    1
   / \
  2  10
    /  \
   2   20

Output: False
Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
Note:
The range of tree node value is in the range of [-100000, 100000].
1 <= n <= 10000

 */
