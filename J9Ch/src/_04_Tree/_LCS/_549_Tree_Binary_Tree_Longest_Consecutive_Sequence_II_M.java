package _04_Tree._LCS;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

//  549. Binary Tree Longest Consecutive Sequence II
//  https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/description/
public class _549_Tree_Binary_Tree_Longest_Consecutive_Sequence_II_M {


///////////////////////////////////////////////////////////////////////

    //https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/solution/

    //Approach #1 Brute Force [Time Limit Exceeded]




///////////////////////////////////////////////////////////////////////

    //Approach #2 Single traversal [Accepted]
    //用数组代替resultType
    /*
    Algorithm
    
    This solution is very simple. With every node, we associate two values/variables named inrinr and dcrdcr, where incrincr represents the length of the longest incrementing branch below the current node including itself, and dcrdcr represents the length of the longest decrementing branch below the current node including itself.
    
    We make use of a recursive function longestPath(node) which returns an array of the form [inr, dcr][inr,dcr] for the calling node. We start off by assigning both inrinr and dcrdcr as 1 for the current node. This is because the node itself always forms a consecutive increasing as well as decreasing path of length 1.
    
    Then, we obtain the length of the longest path for the left child of the current node using longestPath[root.left]. Now, if the left child is just smaller than the current node, it forms a decreasing sequence with the current node. Thus, the dcrdcr value for the current node is stored as the left child's dcrdcr value + 1. But, if the left child is just larger than the current node, it forms an incrementing sequence with the current node. Thus, we update the current node's inrinr value as left\_child(inr) + 1left_child(inr)+1.
    
    Then, we do the same process with the right child as well. But, for obtaining the inrinr and dcrdcr value for the current node, we need to consider the maximum value out of the two values obtained from the left and the right child for both inrinr and dcrdcr, since we need to consider the longest sequence possible.
    
    Further, after we've obtained the final updated values of inrinr and dcrdcr for a node, we update the length of the longest consecutive path found so far as maxval = \text{max}(inr + dcr - 1)maxval=max(inr+dcr−1).
    
    The following animation will make the process clear:
     */
    
    /*
    Time complexity : O(n)O(n). The whole tree is traversed only once.
    Space complexity : O(n)O(n). The recursion goes upto a depth of nn in the worst case.
     */

    int maxval2 = 0;
    public int longestConsecutive1(TreeNode root) {
        longestPath1(root);
        return maxval2;
    }
    public int[] longestPath1(TreeNode root) {
        if (root == null)
            return new int[] {0,0};
        int inr = 1, dcr = 1;
        if (root.left != null) {
            int[] l = longestPath1(root.left);
            if (root.val == root.left.val + 1)
                dcr = l[1] + 1;
            else if (root.val == root.left.val - 1)
                inr = l[0] + 1;
        }
        if (root.right != null) {
            int[] r = longestPath1(root.right);
            if (root.val == root.right.val + 1)
                dcr = Math.max(dcr, r[1] + 1);
            else if (root.val == root.right.val - 1)
                inr = Math.max(inr, r[0] + 1);
        }
        maxval2 = Math.max(maxval2, dcr + inr - 1);
        return new int[] {inr, dcr};
    }

    @Test
    public void test02(){
        int[] arr = {1,2,0};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        root.left.setLeftChild(new TreeNode(3));

        System.out.println("root: ");
        root.print();
        System.out.println(longestConsecutive(root));
    }
    /*root:
               1
              / \
             /   \
             2   0
            /
            3

            4
     */
    
///////////////////////////////////////////////////////////////////////

    //Java recursively compute ascending and descending sequence
    /*
    For each subtree we recursively compute the length of longest ascending and descending path 
    starting from the subtree root. Then we can efficiently check if we could join the two subtree 
    together to get a longer child-parent-child path. In another word, for each subtree, the longest
     child-parent-child consecutive (with root being the parent) is dec+inc-1 since both the 
     ascending and descending path start from root.
     */

    private int maxlen3 = 0;
    private int[] helper3(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int inc = 1, dec = 1;
        int[] left = helper3(root.left), right = helper3(root.right);
        if (root.left != null) {
            if (root.left.val == root.val+1) 
                inc += left[0];
            if (root.left.val == root.val-1) 
                dec += left[1];
        }
        if (root.right != null) {
            if (root.right.val == root.val+1) 
                inc = Math.max(inc, 1+right[0]);
            if (root.right.val == root.val-1) 
                dec = Math.max(dec, 1+right[1]);
        }
        maxlen3 = Math.max(inc+dec-1, maxlen3);
        return new int[]{inc, dec};
    }
    public int longestConsecutive3(TreeNode root) {
        helper3(root);
        return maxlen3;
    }

    @Test
    public void test03(){
        int[] arr = {1,2,0};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        root.left.setLeftChild(new TreeNode(3));

        System.out.println("root: ");
        root.print();
        System.out.println(longestConsecutive3(root));
    }
    /*
    root:
                   1
                  / \
                 /   \
                 2   0
                /
                3

                4
     */
///////////////////////////////////////////////////////////////////////

    //Java Recursive Solution with some comments.

    int maxlen4;
    public int longestConsecutive4(TreeNode root) {
        maxlen4 = 0;
        if(root==null) return maxlen4;
        incdecPath4(root);
        return maxlen4;
    }
    private int[] incdecPath4(TreeNode curr){
        // index 0 and 1 used to record from current node to left node. 
        // index 0 used to record the length pointing from current node to current.left.
        //Of course, you can use a 2D array here using positive and negative 
        // numbers to mark the "flow" direction.
        int[] ret = {1,1,1,1}; 
        if(curr.left!=null){
            int[] leftchild = incdecPath4(curr.left);
            if(curr.val - curr.left.val == 1){
                ret[0] += Math.max(leftchild[0], leftchild[2]);
            }
            if(curr.val - curr.left.val == -1){
                ret[1] += Math.max(leftchild[1], leftchild[3]);
            }
        }
        if(curr.right!=null){
            int[] rightchild = incdecPath4(curr.right);
            if(curr.val - curr.right.val == 1){
                ret[2] += Math.max(rightchild[0], rightchild[2]);
            }
            if(curr.val - curr.right.val == -1){
                ret[3] += Math.max(rightchild[1], rightchild[3]);
            }
        }
        maxlen4 = Math.max(maxlen4, Math.max(ret[0] + ret[3], ret[1] + ret[2]) - 1);
        return ret;
    }


    @Test
    public void test04(){
        int[] arr = {1,2,0};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        root.left.setLeftChild(new TreeNode(3));

        System.out.println("root: ");
        root.print();
        System.out.println(longestConsecutive4(root));
    }
    /*
                    root:
                   1
                  / \
                 /   \
                 2   0
                /
                3

                4
     */
////////////////////////////////////////////////////////////////////////////////////

    //jiuzhang
    class ResultType {
        public int max_length, max_down, max_up;
        ResultType(int len, int down, int up) {
            max_length = len;
            max_down = down;
            max_up = up;
        }
    }
    /**
     * @param root the root of binary tree
     * @return the length of the longest consecutive sequence path
     */
    public int longestConsecutive(TreeNode root) {
        // Write your code here
        return helper(root).max_length;
    }

    ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0, 0);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        int down = 0, up = 0;
        if (root.left != null && root.left.val + 1 == root.val)
            down = Math.max(down, left.max_down + 1);

        if (root.left != null && root.left.val - 1 == root.val)
            up = Math.max(up, left.max_up + 1);

        if (root.right != null && root.right.val + 1 == root.val)
            down = Math.max(down, right.max_down + 1);

        if (root.right != null && root.right.val - 1 == root.val)
            up = Math.max(up, right.max_up + 1);

        int len = down + 1 + up;

        len = Math.max(len, Math.max(left.max_length, right.max_length));

        return new ResultType(len, down, up);
    }

    @Test
    public void test05(){
        int[] arr = {1,2,0};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        root.left.setLeftChild(new TreeNode(3));

        System.out.println("root: ");
        root.print();
        System.out.println(longestConsecutive(root));
    }
    /*
    root:
   1
  / \
 /   \
 2   0
/
3

4
     */

/////////////////////////////////////////////////////////////
}
/*
给定一棵二叉树，找到最长连续序列路径的长度。
路径起点跟终点可以为二叉树的任意节点。
 */

/*
Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:
Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
Example 2:
Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
Note: All the values of tree nodes are in the range of [-1e7, 1e7].
 */


/*
        1
       / \
      2   0
     /
    3
    Return 4 // 0-1-2-3
*/
/*
Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:
Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
Example 2:
Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
Note: All the values of tree nodes are in the range of [-1e7, 1e7].
 */
