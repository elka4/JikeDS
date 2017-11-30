package _04_Tree._PathSum;
import lib.TreeNode;
import java.util.*;
import java.util.Map;

//  437. Path Sum III
//  https://leetcode.com/problems/path-sum-iii/
//
public class _437_Path_Sum_III_E {


/*    Simple Java DFS
    Typical recursive DFS.
            Space: O(n) due to recursion.
    Time: O(n^2) in worst case (no branching); O(nlogn) in best case (balanced tree).*/

    public class Solution1 {
        public int pathSum(TreeNode root, int sum) {
            if (root == null) return 0;
            return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
        }

        private int pathSumFrom(TreeNode node, int sum) {
            if (node == null) return 0;
            return (node.val == sum ? 1 : 0)
                    + pathSumFrom(node.left, sum - node.val) + pathSumFrom(node.right, sum - node.val);
        }
    }


    /*
    17 ms O(n) java Prefix sum method
So the idea is similar as Two sum, using HashMap to store ( key : the prefix sum, value : how many ways get to this prefix sum) , and whenever reach a node, we check if prefix sum - target exists in hashmap or not, if it does, we added up the ways of prefix sum - target into res.
For instance : in one path we have 1,2,-1,-1,2, then the prefix sum will be: 1, 3, 2, 1, 3, let's say we want to find target sum is 2, then we will have{2}, {1,2,-1}, {2,-1,-1,2} and {2}ways.

I used global variable count, but obviously we can avoid global variable by passing the count from bottom up. The time complexity is O(n). This is my first post in discuss, open to any improvement or criticism. :)
     */

    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap();
        preSum.put(0,1);
        helper(root, 0, sum, preSum);
        return count;
    }
    int count = 0;
    public void helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return;
        }

        currSum += root.val;

        if (preSum.containsKey(currSum - target)) {
            count += preSum.get(currSum - target);
        }

        if (!preSum.containsKey(currSum)) {
            preSum.put(currSum, 1);
        } else {
            preSum.put(currSum, preSum.get(currSum)+1);
        }

        helper(root.left, currSum, target, preSum);
        helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);
    }


    class solution3{
        public int pathSum(TreeNode root, int sum) {
            HashMap<Integer, Integer> preSum = new HashMap();
            preSum.put(0,1);
            return helper(root, 0, sum, preSum);
        }

        public int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
            if (root == null) {
                return 0;
            }

            currSum += root.val;
            int res = preSum.getOrDefault(currSum - target, 0);
            preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

            res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
            preSum.put(currSum, preSum.get(currSum) - 1);
            return res;
        }
    }
//------------------------------------------------------------------------------
    /*
    Simple AC Java Solution DFS
Each time find all the path start from current node
Then move start node to the child and repeat.
Time Complexity should be O(N^2) for the worst case and O(NlogN) for balanced binary Tree.
     */
    public class Solution3 {
        public int pathSum(TreeNode root, int sum) {
            if(root == null)
                return 0;
            return findPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
        }

        public int findPath(TreeNode root, int sum){
            int res = 0;
            if(root == null)
                return res;
            if(sum == root.val)
                res++;
            res += findPath(root.left, sum - root.val);
            res += findPath(root.right, sum - root.val);
            return res;
        }

    }

    /*
    A better solution is suggested in 17ms O(n) java prefix sum by tankztc. It use a hash map to store all the prefix sum and each time check if the any subarray sum to the target, add with some comments:
     */
    class Soulutoin4{
        public int pathSum(TreeNode root, int sum) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);  //Default sum = 0 has one count
            return backtrack(root, 0, sum, map);
        }
        //BackTrack one pass
        public int backtrack(TreeNode root, int sum, int target, Map<Integer, Integer> map){
            if(root == null)
                return 0;
            sum += root.val;
            int res = map.getOrDefault(sum - target, 0);    //See if there is a subarray sum equals to target
            map.put(sum, map.getOrDefault(sum, 0)+1);
            //Extend to left and right child
            res += backtrack(root.left, sum, target, map) + backtrack(root.right, sum, target, map);
            map.put(sum, map.get(sum)-1);   //Remove the current node so it wont affect other path
            return res;
        }
    }
//-------------------------------------------------------------------------
class Jiuzhang {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        int count = 0;

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
            count += helper(cur, sum);
        }

        return count;
    }

    private int helper(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return 0;
        }

        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        dfsHelper(res, list, root, sum - root.val);

        return res.size();
    }

    private void dfsHelper(List<List<Integer>> res,
                           List<Integer> path,
                           TreeNode root,
                           int sum) {
        if (root == null) {
            return;
        }

        if (sum == 0) {
            res.add(new ArrayList<>(path));
            // return;
        }

        if (root.left != null) {
            path.add(root.left.val);
            dfsHelper(res, path, root.left, sum - root.left.val);
            path.remove(path.size() - 1);
        }

        if (root.right != null) {
            path.add(root.right.val);
            dfsHelper(res, path, root.right, sum - root.right.val);
            path.remove(path.size() - 1);
        }

    }
}
//-------------------------------------------------------------------------
}
/*

You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

 */
