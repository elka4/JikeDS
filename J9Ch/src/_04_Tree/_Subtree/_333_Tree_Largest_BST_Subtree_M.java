package _04_Tree._Subtree;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;


//  333. Largest BST Subtree
//  https://leetcode.com/problems/largest-bst-subtree/description/
//  Tree
//  6:两种解法，1-4用result type，5-6用双recursion
//  以下解法中最大特征就是使用result type
//  要注意这个是BST, 注意这个和另一个subtree和最大的题的区别。哪题？
//  既然是子树，那就是从一个node为root以下全部的ndoe都要算进去
public class _333_Tree_Largest_BST_Subtree_M {
//  1-4 都是用postorder的DFS。然后分成两种情况，一个是current node满足BST条件，一个是不满足。
//  得到result type的参数后返回result type
//  传递结果的方法：global max，数组，result type。
//  5-6这两个方法使用了三个recursion，是recursion套recursion的方法，虽然性能差，但是每个函数都很简单。
//-------------------------------------------------------------------------------------
    //1
    //    Share my O(n) Java code with brief explanation and comments
    public class Solution1{
        // (size, rangeLower, rangeUpper) -- size of current tree, range of current tree [rangeLower, rangeUpper]
        class Result {
            int size;
            int lower;
            int upper;
            Result(int size, int lower, int upper) {
                this.size = size;
                this.lower = lower;
                this.upper = upper;
            }
        }

        int max = 0;

        public int largestBSTSubtree(TreeNode root) {
            if (root == null) { return 0; }
            traverse(root);
            return max;
        }

        private Result traverse(TreeNode root) {
            if (root == null) { return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE); }

            Result left = traverse(root.left);
            Result right = traverse(root.right);

            //不是BST的四种可能：左边不是，右边不是，当前小于等于左边最大，当前大于等于左右边最小。
            if (left.size == -1 || right.size == -1 || root.val <= left.upper || root.val >= right.lower) {
                return new Result(-1, 0, 0);
            }else {//是BST，所以取得size，max，返回result
                int size = left.size + 1 + right.size;
                max = Math.max(size, max);
                return new Result(size, Math.min(left.lower, root.val), Math.max(right.upper, root.val));
            }
        }
    }

//-------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------
    //2
    //和上题的区别就是上题用Global，这题用数组传递。其他全一样。
    class Solution2{
        class Result {
            int size;
            int min;
            int max;
            public Result (int size, int min, int max) {
                this.size = size;
                this.min = min;
                this.max = max;
            }
        }

        public int largestBSTSubtree(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int[] max = {0};
            Result res = helper(root, max);
            return max[0];
        }

        private Result helper(TreeNode root, int[] max) {
            if (root == null) {
                return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
            }

            Result left = helper(root.left, max);
            Result right = helper(root.right, max);

            if (left.size == -1 || right.size == -1 ||
                    root.val <= left.max || root.val >= right.min) {
                return new Result(-1, 0, 0);
            }

            max[0] = Math.max(max[0], left.size + right.size + 1);

            return new Result(left.size + right.size + 1,
                    Math.min(left.min, root.val), Math.max(root.val, right.max));
        }
    }


//-------------------------------------------------------------------------------------
    //3
    //和上面一样用数组传递，但是满足BST的条件反着来。
class Solution3{
    class Node {
        boolean isBST;
        int min;
        int max;
        int size;

        public Node() {
            isBST = false;
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            size = 0;
        }
    }

    public int largestBSTSubtree(TreeNode root) {
        int [] res = {0};
        helper(root, res);
        return res[0];
    }

    private Node helper(TreeNode root, int [] res){
        Node cur = new Node();

        if(root == null){
            cur.isBST = true;
            return cur;
        }

        Node left = helper(root.left, res);
        Node right = helper(root.right, res);

        //满足BST的全部条件。和上面反着来。
        if(left.isBST && root.val > left.max && right.isBST && root.val < right.min){
            cur.isBST = true;

            cur.min = Math.min(root.val, left.min);
            cur.max = Math.max(root.val, right.max);

            cur.size = left.size + right.size + 1;

            if(cur.size > res[0]){
                res[0] = cur.size;
            }
        }
        return cur;
    }
}


//-------------------------------------------------------------------------------------
    //4
    //jiuhang
    //结果放在result type里
    public class Jiuzhang{
        private class Type {
            int ans;
            int small, large;
            boolean isBST;
            public Type() {
                ans = 0;
                isBST = true;
                small = Integer.MAX_VALUE;
                large = -Integer.MAX_VALUE;
            }
        }

        public int largestBSTSubtree(TreeNode root) {
            return dfs(root).ans;
        }

        private Type dfs(TreeNode node) {
            if (node == null) {
                return new Type();
            }
            Type now = new Type();

            Type left = dfs(node.left);
            Type right = dfs(node.right);

            //反正是要返回一个有4个参数的Type，那么就把这四个参数分别算出来。
            //1.small
            if (left.small < node.val) {
                now.small = left.small;
            } else {
                now.small = node.val;
            }
            //2.large
            now.large = Math.max(right.large,node.val);

            //留意 = 符号出现的情况
            //if (left.isBST && right.isBST && left.large <= node.val && right.small >= node.val) {
            //3.isBST   4.ans
            //满足BST的条件
            if (left.isBST && right.isBST && left.large < node.val && right.small > node.val) {
                now.ans = left.ans + right.ans +1;
                now.isBST = true;
            } else {//不满足的条件
                now.ans=Math.max(left.ans,right.ans);
                now.isBST = false;
            }
            return now;
        }
    }

//--------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------
    //5
    //Clean and easy to understand Java Solution
    class Solution5{
        public int largestBSTSubtree(TreeNode root) {
            if (root == null) return 0;
            if (root.left == null && root.right == null) return 1;
            if (isValid(root, null, null)) return countNode(root);
            return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
        }

        public boolean isValid(TreeNode root, Integer min, Integer max) {
            if (root == null) return true;
            if (min != null && min >= root.val) return false;
            if (max != null && max <= root.val) return false;
            return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
        }

        public int countNode(TreeNode root) {
            if (root == null) return 0;
            if (root.left == null && root.right == null) return 1;
            return 1 + countNode(root.left) + countNode(root.right);
        }
    }


//-------------------------------------------------------------------------------------
    //6
    class Solution6{
        public int largestBSTSubtree(TreeNode root) {
            if (root == null) {
                return 0;
            }
            if (isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
                return count(root);
            }
            return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
        }

        public boolean isValid(TreeNode root, Integer min, Integer max) {
            if (root == null) {
                return true;
            }
            if (root.val >= max || root.val <= min) {
                return false;
            }

            return isValid(root.left, min, root.val)
                    && isValid(root.right, root.val, max);
        }

        public int count(TreeNode root) {
            if (root == null) {
                return 0;
            }

            return count(root.left) + count(root.right) + 1;
        }
    }
//--------------------------------------------------------------------------------------
/*
    @Test
    public void test01(){
        int[] arr = {5,3,6,1,4,7,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(largestBSTSubtree(root));
    }

    @Test
    public void test02(){
        int[] arr = {10, 5, 15};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right.right = new TreeNode(7);
        root.print();
        System.out.println(largestBSTSubtree(root));
    }*/
//--------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------
}

/* leetcode
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here's an example:
    10
    / \
   5  15
  / \   \ 
 1   8   7
The Largest BST Subtree in this case is the highlighted one. 
The return value is the subtree's size, which is 3.
Follow up:
Can you figure out ways to solve it with O(n) time complexity?
 */
//---------------------------------------------------------------------------------------

