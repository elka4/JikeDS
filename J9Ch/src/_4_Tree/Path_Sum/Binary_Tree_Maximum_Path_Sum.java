package _4_Tree.Path_Sum;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

public class Binary_Tree_Maximum_Path_Sum {
    private class ResultType {
        // singlePath: 从root往下走到任意点的最大路径，这条路径可以不包含任何点
        // maxPath: 从树中任意到任意点的最大路径，这条路径至少包含一个点
        int singlePath, maxPath;
        ResultType(int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }

    public int maxPathSum(TreeNode root) {
            ResultType result = helper(root);
            return result.maxPath;
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, Integer.MIN_VALUE);
        }
        // Divide
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        // Conquer
        int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
        singlePath = Math.max(singlePath, 0);

        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);

        return new ResultType(singlePath, maxPath);
    }

    @Test
    public void test01(){
        int[] arr = {1,2,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(maxPathSum(root));
    }

    @Test
    public void test02(){
        int[] arr = {1,-5,11,1,20,4,-2};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(maxPathSum(root));
    }
    
///////////////////////////////////////////////////////

/*
  1
 / \
2   3
return 6.
 */

    // Version 2:
// SinglePath也定义为，至少包含一个点。
    private class ResultType2 {
        int singlePath, maxPath;
        ResultType2(int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }

    public int maxPathSum2(TreeNode root) {
        ResultType2 result = helper2(root);
        return result.maxPath;
    }

    private ResultType2 helper2(TreeNode root) {
        if (root == null) {
            return new ResultType2(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }
        // Divide
        ResultType2 left = helper2(root.left);
        ResultType2 right = helper2(root.right);

        // Conquer
        int singlePath =
                Math.max(0, Math.max(left.singlePath, right.singlePath)) + root.val;

        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath,
                Math.max(left.singlePath, 0) +
                        Math.max(right.singlePath, 0) + root.val);

        return new ResultType2(singlePath, maxPath);
    }

    @Test
    public void test03(){
        int[] arr = {1,2,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(maxPathSum2(root));
    }

    @Test
    public void test04(){
        int[] arr = {1,-5,11,1,20,4,-2};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(maxPathSum2(root));
    }
////////////////////////////////////////////////////////////////////////////////
    //Ch9 2016summer code
    public class _94Binary_Tree_Maximum_Path_Sum_2016 {
        private class ResultType3{
            int root2Any, any2Any;
            ResultType3(int root2Any, int any2Any){
                this.root2Any = root2Any;
                this.any2Any = any2Any;
            }
        }

        public int maxPathSum(TreeNode root){
            ResultType3 result = helper(root);
            return result.any2Any;
        }

        private ResultType3 helper(TreeNode root){
            //illegal
            if (root == null){
                return new ResultType3(Integer.MIN_VALUE, Integer.MIN_VALUE);
            }

            //Divide
            ResultType3 left = helper(root.left);
            ResultType3 right = helper(root.right);

            //conquer
            //root2Any就是为了接下来计算跨过root的any2Any
            int root2Any = Math.max(0, Math.max(left.root2Any, right.root2Any)) + root.val;

            //3 any2any candidates
            int any2Any = Math.max(left.any2Any, right.any2Any);

            any2Any = Math.max(any2Any,
                    Math.max(0, left.root2Any) + Math.max(0, right.root2Any) + root.val);
            //上面这行是跨过root的，但是不一定最大，要和左右两侧的any2any比较

            return new ResultType3(root2Any, any2Any);
        }
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    //We can also use an array to store value for recursive methods.

    public int maxPathSum11(TreeNode root) {
        int max[] = new int[1];
        max[0] = Integer.MIN_VALUE;
        calculateSum(root, max);
        return max[0];
    }

    public int calculateSum(TreeNode root, int[] max) {
        if (root == null)
            return 0;

        int left = calculateSum(root.left, max);
        int right = calculateSum(root.right, max);

        int current = Math.max(root.val, Math.max(root.val + left, root.val + right));

        max[0] = Math.max(max[0], Math.max(current, left + root.val + right));

        return current;
    }

///////////////////////////////////////////////////////////////////
/*    Accepted short solution in Java
    Here's my ideas:

    A path from start to end, goes up on the tree for 0 or more steps, then goes down for 0 or more steps. Once it goes down, it can't go up. Each path has a highest node, which is also the lowest common ancestor of all other nodes on the path.
    A recursive method maxPathDown(TreeNode node) (1) computes the maximum path sum with highest node is the input node, update maximum if necessary (2) returns the maximum sum of the path that can be extended to input node's parent.
    Code:*/

    public class Solution2 {
        int maxValue;

        public int maxPathSum(TreeNode root) {
            maxValue = Integer.MIN_VALUE;
            maxPathDown(root);
            return maxValue;
        }

        private int maxPathDown(TreeNode node) {
            if (node == null) return 0;
            int left = Math.max(0, maxPathDown(node.left));
            int right = Math.max(0, maxPathDown(node.right));
            maxValue = Math.max(maxValue, left + right + node.val);
            return Math.max(left, right) + node.val;
        }
    }


    ///////////////////////////////////////////////////////////////////
    //Elegant Java solution
    public class Solution3 {
        int max = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            helper(root);
            return max;
        }

        // helper returns the max branch
        // plus current node's value
        int helper(TreeNode root) {
            if (root == null) return 0;

            int left = Math.max(helper(root.left), 0);
            int right = Math.max(helper(root.right), 0);

            max = Math.max(max, root.val + left + right);

            return root.val + Math.max(left, right);
        }
    }


///////////////////////////////////////////////////////////////////

    //Sharing a simple JAVA solution

    class Solution4{
        int max = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            helper(root);
            return max;
        }

        public int helper(TreeNode root) {
            if(root == null)
                return Integer.MIN_VALUE;
            int left = Math.max(0, helper(root.left));
            int right = Math.max(0, helper(root.right));
            max = Math.max(max, root.val + left + right);
            return root.val + Math.max(left, right);
        }
    }

///////////////////////////////////////////////////////////////////

    //A recursive solution with comment

    class Solution5{
        // global max
        int max = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            dfs(root);
            return max;
        }

        private int dfs(TreeNode root) {
            if (root == null) return 0;
            // 2 possible choices
            // 1.Already calculated in left or right child
            // 2.left max path + right max path + root
            int lMax = dfs(root.left);
            int rMax = dfs(root.right);
            if (lMax + rMax + root.val > max) max = lMax + rMax + root.val;
            // if the below path is negative, just make it 0 so that we could 'ignore' it
            return Math.max(0, root.val + Math.max(lMax, rMax));
        }
    }


///////////////////////////////////////////////////////////////////

    //My AC java recursive solution

/*    max1 is the max value of the current node to pass to the upper level node.

    max is the global max value that could be max1 or the sum of root and left max and right max*/

    public class Solution6 {

        int max = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            maxPathSumR(root);
            return max;
        }

        public int maxPathSumR(TreeNode root) {

            if (root == null) return 0;
            int left = maxPathSumR(root.left);
            int right = maxPathSumR(root.right);

            int max1 = Math.max(root.val,
                    Math.max(root.val + left, root.val + right));

            max = Math.max(max, Math.max(max1, left + right + root.val));
            return max1;
        }
    }
///////////////////////////////////////////////////////////////////

/*        Accepted Java code [19 lines, simple and effective]

        The thought is bottom-up, first we need to get left and right tree max value and make sure that they need to be larger than 0. Next add them to current node value, compare to the total max value and update the total max value. Finally return current value with one of left or right max value (>=0).*/

    public class Solution7 {
        int max = 0;
        public int maxPathSum(TreeNode root) {
            if(root == null) return 0;
            max = root.val;
            helper(root);
            return max;
        }
        public int helper(TreeNode node)
        {
            if(node == null) return 0;
            int left = helper(node.left);
            int right = helper(node.right);
            left = left > 0 ? left : 0;
            right = right > 0 ? right : 0;
            int curMax = node.val + left + right;
            max = Math.max(max, curMax);
            return node.val + Math.max(left, right);
        }
    }
////////////////////////////////////////////////////////////////////////////////
}

/*Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

Example: Given the below binary tree:

  1
 / \
2   3
return 6.

Tags: Divide and Conquer Dynamic Programming Recursion
Related Problems: Medium Binary Tree Maximum Path Sum II 42 %
				  Easy Minimum Path Sum 34 %
*/