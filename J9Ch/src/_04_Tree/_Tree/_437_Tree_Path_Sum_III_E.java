package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _437_Tree_Path_Sum_III_E {

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


    class solution{
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
}
