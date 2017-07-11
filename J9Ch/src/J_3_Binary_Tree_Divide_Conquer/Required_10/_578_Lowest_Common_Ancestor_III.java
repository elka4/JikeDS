package J_3_Binary_Tree_Divide_Conquer.Required_10;
import java.util.*;import lib.TreeNode;import lib.AssortedMethods;import org.junit.Test;
import lib.TreeNode;
import lib.AssortedMethods;
import org.junit.Test;
/** 578. Lowest Common Ancestor III
 * Medium

 * Created by tianhuizhu on 6/27/17.
 */
public class _578_Lowest_Common_Ancestor_III {
    class ResultType {
        public boolean a_exist, b_exist;
        public TreeNode node;
        ResultType(boolean a, boolean b, TreeNode n) {
            a_exist = a;
            b_exist = b;
            node = n;
        }
    }

    /**
     * @param root The root of the binary tree.
     * @param A and B two nodes
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        ResultType rt = helper(root, A, B);
        if (rt.a_exist && rt.b_exist)
            return rt.node;
        else
            return null;
    }

    public ResultType helper(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null)
            return new ResultType(false, false, null);

        ResultType left_rt = helper(root.left, A, B);
        ResultType right_rt = helper(root.right, A, B);

        boolean a_exist = left_rt.a_exist || right_rt.a_exist || root == A;
        boolean b_exist = left_rt.b_exist || right_rt.b_exist || root == B;

        if (root == A || root == B)
            return new ResultType(a_exist, b_exist, root);

        if (left_rt.node != null && right_rt.node != null)
            return new ResultType(a_exist, b_exist, root);
        if (left_rt.node != null)
            return new ResultType(a_exist, b_exist, left_rt.node);
        if (right_rt.node != null)
            return new ResultType(a_exist, b_exist, right_rt.node);

        return new ResultType(a_exist, b_exist, null);
    }

    /*

  4
 / \
3   7
   / \
  5   6
LCA(3, 5) = 4

LCA(5, 6) = 7

LCA(6, 7) = 7


     */
    @Test
    public void test01() {
        int[] arr = {4, 3, 7};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.setLeftChild(new TreeNode(5));
        root.right.setRightChild(new TreeNode(6));
        System.out.println("root: ");
        root.print();
        TreeNode node4 = root;
        TreeNode node3 = root.left;
        TreeNode node7 = root.right;
        TreeNode node5 = root.right.left;
        TreeNode node6 = root.right.right;
        lowestCommonAncestor3(root, node3,node5).print();
        lowestCommonAncestor3(root, node5,node6).print();
        lowestCommonAncestor3(root, node6,node7).print();

    }
}
