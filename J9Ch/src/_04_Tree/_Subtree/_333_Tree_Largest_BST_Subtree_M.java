package _04_Tree._Subtree;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;


//  333. Largest BST Subtree
//  https://leetcode.com/problems/largest-bst-subtree/description/
//
public class _333_Tree_Largest_BST_Subtree_M {

    // (size, rangeLower, rangeUpper) -- size of current tree,
    // range of current tree [rangeLower, rangeUpper]
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

    int max1 = 0;

    public int largestBSTSubtree1(TreeNode root) {
        if (root == null) { return 0; }
        traverse1(root);
        return max1;
    }

    private Result traverse1(TreeNode root) {
        if (root == null) { return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE); }
        Result left = traverse1(root.left);
        Result right = traverse1(root.right);
        if (left.size == -1 || right.size == -1 || root.val <= left.upper || root.val >= right.lower) {
            return new Result(-1, 0, 0);
        }
        int size = left.size + 1 + right.size;
        max1 = Math.max(size, max1);
        return new Result(size, Math.min(left.lower, root.val), Math.max(right.upper, root.val));
    }
///////////////////////////////////////////////////////////////////////////////////////////////////

    /*
        in brute-force solution, we get information in a top-down manner.
        for O(n) solution, we do it in bottom-up manner, meaning we collect information during backtracking.
    */
    public class Solution2 {
        // (size, rangeLower, rangeUpper) -- size of current tree,
        // range of current tree [rangeLower, rangeUpper]
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
            traverse(root, null);
            return max;
        }

        private Result traverse(TreeNode root, TreeNode parent) {
            if (root == null) { return new Result(0, parent.val, parent.val); }
            Result left = traverse(root.left, root);
            Result right = traverse(root.right, root);
            if (left.size==-1 || right.size==-1 || root.val<left.upper || root.val>right.lower) {
                return new Result(-1, 0, 0);
            }
            int size = left.size + 1 + right.size;
            max = Math.max(size, max);
            return new Result(size, left.lower, right.upper);
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////

    public class Solution3 {
        class Result {
            int res;
            int min;
            int max;
            public Result(int res, int min, int max) {
                this.res = res;
                this.min = min;
                this.max = max;
            }
        }

        public int largestBSTSubtree(TreeNode root) {
            Result res = BSTSubstree(root);
            return Math.abs(res.res);
        }

        private Result BSTSubstree(TreeNode root) {
            if (root == null) return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
            Result left = BSTSubstree(root.left);
            Result right = BSTSubstree(root.right);
            if (left.res < 0 || right.res < 0 || root.val < left.max || root.val > right.min) {
                return new Result(Math.max(Math.abs(left.res),
                        Math.abs(right.res)) * -1, 0, 0);
            } else {
                return new Result(left.res + right.res + 1,
                        Math.min(root.val, left.min), Math.max(root.val, right.max));
            }
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////

    public class Solution4 {
        public int largestBSTSubtree(TreeNode root) {
            int[] ans = new int[]{0};
            findBST(root, ans);
            return ans[0];
        }

        private class Res {
            int min, max, size;

            public Res(int l, int r, int k) {
                min = l; max = r; size = k;
            }
        }

        private Res findBST(TreeNode rt, int[] ans) {
            if (rt == null) return null;
            boolean isBST = true;
            int min = rt.val, max = rt.val, size = 1;
            Res l = findBST(rt.left, ans), r = findBST(rt.right, ans);
            if (rt.left != null) {
                if (l == null || l.max > rt.val) return null;
                min = l.min;
                size += l.size;
            }
            if (rt.right != null) {
                if (r == null || r.min < rt.val) return null;
                max = r.max;
                size += r.size;
            }
            ans[0] = Math.max(ans[0], size);
            return new Res(min, max, size);
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////


//    Share my O(n) Java code with brief explanation and comments
//    edited code: thanks @hyj143 and @petrichory
    //Runtime: 7 ms
//Your runtime beats 53.14 % of java submissions.

    public class Solution5 {
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

            if (left.size == -1 || right.size == -1 || root.val <= left.upper || root.val >= right.lower) {
                return new Result(-1, 0, 0);
            }

            int size = left.size + 1 + right.size;
            max = Math.max(size, max);
            return new Result(size, Math.min(left.lower, root.val), Math.max(right.upper, root.val));
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////

    //Clean and easy to understand Java Solution
    public int largestBSTSubtree6(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (isValid6(root, null, null)) return countNode6(root);
        return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
    }

    public boolean isValid6(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && min >= root.val) return false;
        if (max != null && max <= root.val) return false;
        return isValid6(root.left, min, root.val) && isValid6(root.right, root.val, max);
    }

    public int countNode6(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return 1 + countNode6(root.left) + countNode6(root.right);
    }
////////////////////////////////////////////////////////////////////////////////////////////
    //jiuhang
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

            if (left.small < node.val) {
                now.small = left.small;
            } else {
                now.small = node.val;
            }

            now.large = Math.max(right.large,node.val);
//            if (left.isBST && right.isBST && left.large <= node.val && right.small >= node.val) {
            if (left.isBST && right.isBST && left.large < node.val && right.small > node.val) {
                now.ans = left.ans + right.ans +1;
                now.isBST = true;
            } else {
                now.ans=Math.max(left.ans,right.ans);
                now.isBST = false;
            }
            return now;
        }
    }

    public class Jiuzhang2 {
        public class SuperNode {
            int ans;
            int small, large;
            boolean isBST;
            public SuperNode() {
                ans = 0;
                isBST = true;
                small = Integer.MAX_VALUE;
                large = -Integer.MAX_VALUE;
            }
        }
        public int largestBSTSubtree(TreeNode root) {
            return dfs(root).ans;
        }
        public SuperNode dfs(TreeNode node) {
            if (node == null) {
                return new SuperNode();
            }
            SuperNode now = new SuperNode();
            SuperNode left = dfs(node.left);
            SuperNode right = dfs(node.right);
            if (left.small < node.val) {
                now.small = left.small;
            } else {
                now.small = node.val;
            }
            now.large = Math.max(right.large,node.val);
            if (left.isBST && right.isBST && left.large <= node.val && right.small >= node.val) {
                now.ans = left.ans + right.ans +1;
                now.isBST = true;
            } else {
                now.ans=Math.max(left.ans,right.ans);
                now.isBST = false;
            }
            return now;
        }
    }


//////////////////////////////////////////////////////////////////////
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

    @Test
    public void test01(){
        int[] arr = {5,3,6,1,4,7,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(largestBSTSubtree(root));
    }
    /*
                        root:
                       5
                      / \
                     /   \
                     3   6
                    / \ / \
                    1 4 7 8

                    3
     */

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
    }


    /*
            root:
               10
              / \
             /   \
             5   15
            / \   \
            1 8   7

            3
     */
////////////////////////////////////////////////////////////////////////////

    //这个算法有问题，不能返回subtree的正确node数
    //有可能是 //int[] max = {0};
    class Solution{
        class Result {
            int size;
            int min;
            int max;

            public Result (int size, int min, int max) {
                this.size = size;
                this.min = min;
                this.max = max;
            }
            public String toString(){
                return "Result: " + "size  "+ size + " min  "+ min + " max  "+ max;
            }
        }

        public int largestBSTSubtree2(TreeNode root) {
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

            root.print();
//        System.out.println("root: " + root.val);
//        System.out.println("left: " + left);
//        System.out.println("right: " + right);


            if (left.size == -1 || right.size == -1 ||
                    root.val <= left.max || root.val >= right.min) {

//            System.out.println("left.size == -1 || right.size == -1 ||");

                return new Result(-1, 0, 0);
            }

            max[0] = Math.max(max[0], left.size + right.size + 1);

            return new Result(left.size + right.size + 1,
                    Math.min(left.min, root.val), Math.max(root.val, right.max));
        }
    }


    @Test
    public void test03(){
        //int[] arr = {5,3,6,1,4,8};
        int[] arr = {5,2,7,1,3,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println("largestBSTSubtree2 " );
    }


///////////////////////////////////////////////////////////////////
    class Solution33{
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

        public int largestBSTSubtree3(TreeNode root) {
            int [] res = {0};
            helper2(root, res);
            return res[0];
        }

        private Node helper2(TreeNode root, int [] res){
            Node cur = new Node();

            if(root == null){
                cur.isBST = true;
                return cur;
            }

            Node left = helper2(root.left, res);
            Node right = helper2(root.right, res);

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



////////////////////////////////////////////////////////////////////////////////////////

    //Clean and easy to understand Java Solution
    public int largestBSTSubtree333(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (isValid33(root, null, null)) return countNode(root);
        return Math.max(largestBSTSubtree333(root.left), largestBSTSubtree333(root.right));
    }

    public boolean isValid33(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && min >= root.val) return false;
        if (max != null && max <= root.val) return false;
        return isValid33(root.left, min, root.val) && isValid33(root.right, root.val, max);
    }

    public int countNode(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return 1 + countNode(root.left) + countNode(root.right);
    }

////////////////////////////////////////////////////////////////////////////////////////

    //Java 1ms solution, by passing a three-element array up to parent

    private int largestBSTSubtreeSize = 0;
    public int largestBSTSubtree4(TreeNode root) {
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

////////////////////////////////////////////////////////////////////////////////////////

    class Wrapper{
        int size;
        int lower, upper;
        boolean isBST;

        public Wrapper(){
            lower = Integer.MAX_VALUE;
            upper = Integer.MIN_VALUE;
            isBST = false;
            size = 0;
        }
    }


    public int largestBSTSubtree5(TreeNode root) {
        return helper5(root).size;
    }


    public Wrapper helper5(TreeNode node){
        Wrapper curr = new Wrapper();

        if(node == null){
            curr.isBST= true;
            return curr;
        }

        Wrapper l = helper5(node.left);
        Wrapper r = helper5(node.right);

        //current subtree's boundaries
        curr.lower = Math.min(node.val, l.lower);
        curr.upper = Math.max(node.val, r.upper);

        //check left and right subtrees are BST or not
        //check left's upper again current's value and
        // right's lower against current's value
        if(l.isBST && r.isBST && l.upper<=node.val && r.lower>=node.val){
            curr.size = l.size+r.size+1;
            curr.isBST = true;
        }else{
            curr.size = Math.max(l.size, r.size);
            curr.isBST  = false;
        }

        return curr;
    }


////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param root the root of binary tree
     * @return the maximum weight node
     */
    public TreeNode result = null;
    public int maximum_weight = Integer.MIN_VALUE;

    public TreeNode findSubtree(TreeNode root) {
        // Write your code here
        helper3(root);

        return result;
    }

    public int helper3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left_weight = helper3(root.left);
        int right_weight = helper3(root.right);

        if (result == null || left_weight + right_weight + root.val > maximum_weight) {
            maximum_weight = left_weight + right_weight + root.val;
            result = root;
        }

        return left_weight + right_weight + root.val;
    }


    @Test
    public void test(){
        int[] arr = {0,-5,3,1,-4,2,-5};
        TreeNode root = TreeNode.createMinimalBST(arr);
//        root.right.setLeftChild(new TreeNode(15));
//        root.right.setRightChild(new TreeNode(7));
        root.print();
        findSubtree(root).print();

//        List<List<Integer>> result = verticalOrder2(root);

//        System.out.println(result);
    }

    /*
                   1
                  / \
                 /   \
                 -5   2
                / \ / \
                0 3 -4 -5

                3
     */

/////////////////////////////////////////////////////////////////////////////////////////////
    int max = Integer.MAX_VALUE;
    public TreeNode result2 = null;
    public TreeNode findSubtree2(TreeNode root) {
        // write your code here
        helper2(root);
        return result2;
    }

    private int helper2(TreeNode root){
        if(root == null) {
            return 0;
        }
        int left = helper2(root.left);
        int right = helper2(root.right);
        int currentTotal = root.val + left + right;
        if(result2 == null || currentTotal > max){
            result2 = root;
            max = currentTotal;
        }
        return currentTotal;
    }


/////////////////////////////////////////////////////////////////////////////////////////////
    //Clean and easy to understand Java Solution
    class Solution22 {
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
/////////////////////////////////////////////////////////////////////////////////////////////
    //Java 1ms solution, by passing a three-element array up to parent
    class Solution331 {
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
/////////////////////////////////////////////////////////////////////////////////////////////



}
/*

 */
/*
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

/*
Given a binary tree, find the subtree with maximum sum. Return the root of the subtree.

 Notice

LintCode will print the subtree which root is your return node.
It's guaranteed that there is only one subtree with maximum sum and the given binary tree is not an empty tree.

Have you met this question in a real interview? Yes
Example
Given a binary tree:

     1
   /   \
 -5     2
 / \   /  \
0   3 -4  -5
return the node with value 3.
 */

/*
给你一棵二叉树，找二叉树中的一棵子树，他的所有节点之和最大。

返回这棵子树的根节点。

 注意事项

LintCode 会把你返回的节点作为最优子树来打印。

数据保证有且仅有唯一的解。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出如下二叉树

     1
   /   \
 -5     2
 / \   /  \
0   3 -4  -5
返回值为 3 的节点。
 */

/*
leetcode

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