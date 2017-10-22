package _04_Tree.Path_Sum;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


// binary tree path sum
// 113
// Path Sum II
public class Binary_Tree_Path_Sum {
    // jiuzhang
    /**
     * @param root the root of binary tree
     * @param target an integer
     * @return all valid paths
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // Algorithm: Traverse
        // Use recursion to traverse the tree in preorder, pass with a parameter
        // `sum` as the sum of each node from root to current node.
        // Put the whole path into result if the leaf has a sum equal to target.

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(root.val);
        helper(root, path, root.val, target, result);
        return result;
    }

    private void helper(TreeNode root,
                        ArrayList<Integer> path,
                        int sum,
                        int target,
                        List<List<Integer>> result) {

        // meet leaf
        if (root.left == null && root.right == null) {
            if (sum == target) {
                result.add(new ArrayList<Integer>(path));
            }
            return;
        }

        // go left
        if (root.left != null) {
            path.add(root.left.val);
            helper(root.left, path, sum + root.left.val, target, result);
            path.remove(path.size() - 1);
        }

        // go right
        if (root.right != null) {
            path.add(root.right.val);
            helper(root.right, path, sum + root.right.val, target, result);
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void test01() {
        int[] arr = {1, 2, 4,2,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(binaryTreePathSum(root, 5));
    }
/*
root:
   1
  / \
 /   \
 2   4
/ \
2 3

[[1, 2, 2], [1, 4]]
 */
    @Test
    public void test02() {
        int[] arr = {1, 2, 4,2,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(binaryTreePathSum(root, 6));
    }
    /*
    root:
   1
  / \
 /   \
 2   4
/ \
2 3

[[1, 2, 3]]
     */

    @Test
    public void test03() {
        int[] arr = {1, 2, 4,2,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(binaryTreePathSum(root, 4));
    }
//////////////////////////////////////////////////////////////////////////////////////////
//My simple java solution
    /*
    这个方法每次传到下一层的都是一个new出来的新的list，所以不需要在回到某一层时remove！！！！
     */
class solution5 {
    private List<List<Integer>> result = new ArrayList<List<Integer>>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        helper(new ArrayList<Integer>(), root, sum);
        return result;
    }

    private void helper(List<Integer> list, TreeNode root, int sum) {
        if (root == null) return;
        list.add(root.val);
        sum -= root.val;
        if (root.left == null && root.right == null) {
            if (sum == 0) result.add(list);
            return;
        }
        helper(new ArrayList<Integer>(list), root.left, sum);
        helper(new ArrayList<Integer>(list), root.right, sum);
    }
}


//////////////////////////////////////////////////////////////////////////////////////////
    // the result does not have to be global
    class Solution55 {
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            helper(new ArrayList<Integer>(), root, sum, result);
            return result;
        }

        private void helper(List<Integer> list, TreeNode root, int sum, List<List<Integer>> result) {
            if (root == null) return;
            list.add(root.val);
            sum -= root.val;
            if (root.left == null && root.right == null) {
                if (sum == 0) result.add(list);
                return;
            }
            helper(new ArrayList<Integer>(list), root.left, sum, result);
            helper(new ArrayList<Integer>(list), root.right, sum, result);
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////

    // DFS with one LinkedList , accepted java solution
    public List<List<Integer>> pathSum(TreeNode root, int sum){
        List<List<Integer>> result  = new LinkedList<List<Integer>>();
        List<Integer> currentResult  = new LinkedList<Integer>();
        pathSum(root,sum,currentResult,result);
        return result;
    }

    public void pathSum(TreeNode root, int sum, List<Integer> currentResult,
                        List<List<Integer>> result) {

        if (root == null)
            return;

        currentResult.add(new Integer(root.val));

        if (root.left == null && root.right == null && sum == root.val) {
            result.add(new LinkedList(currentResult));
            currentResult.remove(currentResult.size() - 1);//don't forget to remove the last integer
            return;
        } else {
            pathSum(root.left, sum - root.val, currentResult, result);
            pathSum(root.right, sum - root.val, currentResult, result);
        }

        currentResult.remove(currentResult.size() - 1);
    }

//////////////////////////////////////////////////////////////////////////////////////////

    //Simple DFS Java Solution
    // Save intermediate result into stack and save the stack into result array once its sum == required sum.

    public class Solution {
        private List<List<Integer>> resultList = new ArrayList<List<Integer>>();

        public void pathSumInner(TreeNode root, int sum, Stack<Integer>path) {
            path.push(root.val);

            if(root.left == null && root.right == null)
                if(sum == root.val) resultList.add(new ArrayList<Integer>(path));

            if(root.left!=null) pathSumInner(root.left, sum-root.val, path);
            if(root.right!=null)pathSumInner(root.right, sum-root.val, path);

            path.pop();
        }

        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            if(root==null) return resultList;
            Stack<Integer> path = new Stack<Integer>();
            pathSumInner(root, sum, path);
            return resultList;
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////
    // Java Solution: iterative and recursive

//1. iterative: Using a stack to implement DFS
//2. Recursive:

    public class Solution2 {
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<TreeNode>();
            int SUM = 0;
            TreeNode cur = root;
            TreeNode pre = null;
            while(cur!=null || !stack.isEmpty()){
                while(cur!=null){
                    stack.push(cur);
                    path.add(cur.val);
                    SUM+=cur.val;
                    cur=cur.left;
                }
                cur = stack.peek();
                if(cur.right!=null && cur.right!=pre){
                    cur = cur.right;
                    continue;
                }
                if(cur.left==null && cur.right==null && SUM==sum)
                    res.add(new ArrayList<Integer>(path));

                pre = cur;
                stack.pop();
                path.remove(path.size()-1);
                SUM-=cur.val;
                cur = null;

            }
            return res;
        }
    }


    // I like this one
    public class Solution3 {
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            dfs(root, sum, res, path);
            return res;
        }

        public void dfs(TreeNode root, int sum, List<List<Integer>> res, List<Integer> path){
            if(root==null) return;

            path.add(root.val);

            if(root.left==null && root.right==null ){
                if(root.val==sum)
                    res.add(new ArrayList<Integer>(path));
                return;
            }

            if(root.left!=null) {
                dfs(root.left,sum-root.val,res,path);
                path.remove(path.size()-1);
            }
            if(root.right!=null) {
                dfs(root.right,sum-root.val,res,path);
                path.remove(path.size()-1);
            }

        }
    }


//////////////////////////////////////////////////////////////////////////////////////////
    //Another accepted Java solution
    public class Solution4 {
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            pathSum(root, sum, new ArrayList<Integer>(), res);
            return res;
        }

        void pathSum(TreeNode root, int sum, List<Integer> sol, List<List<Integer>> res) {
            if (root == null) {
                return;
            }

            sol.add(root.val);

            if (root.left == null && root.right == null && sum == root.val) {
                res.add(new ArrayList<Integer>(sol));
            } else {
                pathSum(root.left, sum - root.val, sol, res);
                pathSum(root.right, sum - root.val, sol, res);
            }

            sol.remove(sol.size() - 1);
        }
    }


//////////////////////////////////////////////////////////////////////////////////////////






}

/*Given a binary tree, and target = 5:

     1
    / \
   2   4
  / \
 2   3
return

[
  [1, 2, 2],
  [1, 4]
]
 */


