package _04_Tree._Subtree;
import java.util.*;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

//  250. Count Univalue Subtrees
//  https://leetcode.com/problems/count-univalue-subtrees/description/
//  Tree
//  Subtree of Another Tree, Longest Univalue Path
//  4:所有的解法都一样，Divide Conquer之后返回本节点是否满足Univalue。用global max或者数组存储结果。

//结果是
public class _250_Tree_Count_Univalue_Subtrees_M {
/*
思路: 题意是说如果一个结点的左子树和右子树的值都相等, 那么就计数+1.

因此我们只需要一个递归的判定一个结点出发,是否其所有结点都相等即可.叶子结点也算是一个结果.
此题虽然简单但是非常好非常好
因为要左边是，而且右边也是，再和当前root比较才知道要不要count++，所以postorder先把左右做出来
 */
//-------------------------------------------------------------------------
    //1
    //My Concise JAVA Solution
    public int countUnivalSubtrees1(TreeNode root) {
        int[] count = new int[1];
        helper1(root, count);
        return count[0];
    }
    //postorder
    private boolean helper1(TreeNode node, int[] count) {
        if (node == null) {
            return true;
        }
        boolean left = helper1(node.left, count);
        boolean right = helper1(node.right, count);

        if (left && right) {
            if (node.left != null && node.val != node.left.val) {
                return false;
            }
            if (node.right != null && node.val != node.right.val) {
                return false;
            }
            count[0]++;
            //System.out.println("node: "+node.val);
            //node.print();
            return true;
        }
        return false;
    }

    @Test
    public void test01() {
        int[] arr = {5, 1, 5, 5, 5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.right = new TreeNode(5);
        root.print();
        System.out.println(countUnivalSubtrees1(root));
    }
/*    int[] arr = {5,3,7,1,2,6,8};
    TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(searchRange(root, 3,7));*/


//-------------------------------------------------------------------------
    //2
    //AC clean Java solution
    int count;

    public int countUnivalSubtrees5(TreeNode root) {
        count = 0;
        helper5(root);
        return count;
    }

    boolean helper5(TreeNode root) {
        if (root == null) return true;

        boolean left = helper5(root.left);
        boolean right = helper5(root.right);

        if (left && right &&
                (root.left == null || root.val == root.left.val) &&
                (root.right == null || root.val == root.right.val)) {
            count++;
            return true;
        }

        return false;
    }


//-------------------------------------------------------------------------
}
