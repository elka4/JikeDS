package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;

//113. Path Sum II

public class _113_DFS_Path_Sum_II_M {
    //DFS with one LinkedList , accepted java solution
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


//    Simple DFS Java Solution
//    Save intermediate result into stack and save the stack into result array once its sum == required sum.

    public class Solution2 {
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


//    Java Solution: iterative and recursive
//1. iterative: Using a stack to implement DFS
//2. Recursive:

    public class Solution3 {
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



    public class Solution4 {
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




//----------------------------------------------------------------------------

    public class Jiuzhang {
        public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
            ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> solution = new ArrayList<Integer>();

            findSum(rst, solution, root, sum);
            return rst;
        }

        private void findSum(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> solution,
                             TreeNode root, int sum){
            if (root == null) {
                return;
            }

            sum -= root.val;

            if (root.left == null && root.right == null) {
                if (sum == 0){
                    solution.add(root.val);
                    result.add(new ArrayList<Integer>(solution));
                    solution.remove(solution.size()-1);
                }
                return;
            }

            solution.add(root.val);
            findSum(result, solution, root.left, sum);
            findSum(result, solution, root.right, sum);
            solution.remove(solution.size()-1);
        }
    }


//----------------------------------------------------------------------------






}
/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]

 */