package j_8_DataStructure;

import lib.TreeNode;
import org.junit.Test;

import java.util.Stack;

public class _04Max_Tree_1 {
/**
   * @param A
   * : Given an integer array with no duplicates.
   * @return: The root of max tree.
   */
    public static TreeNode maxTree(int[] A) {
        // write your code here
        Stack<TreeNode> stack = new Stack<TreeNode>();

        //TreeNode root = null;
        for (int i = 0; i <= A.length; i++) {
            TreeNode right = i == A.length ? new TreeNode(Integer.MAX_VALUE)
              : new TreeNode(A[i]);

            //只要栈顶node比right node小，就弹出
            while (!stack.isEmpty()) {
                System.out.println(stack);
                if (right.val > stack.peek().val) {
                    TreeNode nodeNow = stack.pop();

                    if (stack.isEmpty()) {
                        right.left = nodeNow;
                    } else {
                        TreeNode left = stack.peek();
                        if (left.val > right.val) {
                            right.left = nodeNow;
                        } else {
                            left.right = nodeNow;
                        }
                    }
                } else
                    break;
            }

            right.print();
            stack.push(right);
        }
        return stack.peek().left;
    }

    @Test
    public void test01(){
        int[] nums = {2, 5, 6, 0, 3, 1};
        maxTree(nums).print();
    }


///////////////////////////////////////////////////////////////

    public TreeNode maxTree2(int[] A) {
        // write your code here
        int len = A.length;
        TreeNode[] stk = new TreeNode[len];

        for (int i = 0; i < len; ++i)
            stk[i] = new TreeNode(0);
        int cnt = 0;

        for (int i = 0; i < len; ++i) {
            TreeNode tmp = new TreeNode(A[i]);
            while (cnt > 0 && A[i] > stk[cnt-1].val) {
                tmp.left = stk[cnt-1];
                cnt --;
            }
            if (cnt > 0)
                stk[cnt - 1].right = tmp;
            stk[cnt++] = tmp;
        }
        return stk[0];
    }

    @Test
    public void test02(){
        int[] nums = {2, 5, 6, 0, 3, 1};
        maxTree2(nums).print();
    }
}

/*Given an integer array with no duplicates. 
 * A max tree building on this array is defined as follow:
 

The root is the maximum number in the array
The left subtree and right subtree are the max trees of the subarray
divided by the root number.
Construct the max tree by the given array.

Have you met this question in a real interview? Yes
Example
Given [2, 5, 6, 0, 3, 1], the max tree constructed by this array is:

    6
   / \
  5   3
 /   / \
2   0   1
Challenge 
O(n) time and memory.

Tags 
LintCode Copyright Stack Cartesian Tree
Related Problems 
Hard Largest Rectangle in Histogram 25 %
Medium Min Stack 31 %*/