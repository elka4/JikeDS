package J_3_Binary_Tree_Divide_Conquer.other.Binary_Tree_Longest_Consecutive_Sequence;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.List;

/**
 * 619
 * Binary Tree Longest Consecutive Sequence III
 * Created by tianhuizhu on 6/28/17.
 */
// k-ary tree, any to any
public class _619_Binary_Tree_Longest_Consecutive_Sequence_III_Medium {

    class MultiTreeNode {
        int val;
        List<MultiTreeNode> children;
        MultiTreeNode(int x) { val = x; }
    }
    class ResultType {
        public int max_len, max_down, max_up;
        ResultType(int len, int down, int up) {
            max_len = len;
            max_down = down;
            max_up = up;
        }
    }

    public int longestConsecutive3(MultiTreeNode root) {
        // Write your code here
        return helper(root).max_len;
    }

    ResultType helper(MultiTreeNode root) {
        if (root == null) {
            return new ResultType(0, 0, 0);
        }

        int down = 0, up = 0, max_len = 1;
        for (MultiTreeNode node : root.children) {
            ResultType type = helper(node);
            if (node.val + 1 == root.val)
                down = Math.max(down, type.max_down + 1);
            if (node.val - 1 == root.val)
                up = Math.max(up, type.max_up + 1);
            max_len = Math.max(max_len, type.max_len);
        }

        max_len = Math.max(down + 1 + up, max_len);
        return new ResultType(max_len, down, up);
    }


    @Test
    public void test01(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();

    }
}
