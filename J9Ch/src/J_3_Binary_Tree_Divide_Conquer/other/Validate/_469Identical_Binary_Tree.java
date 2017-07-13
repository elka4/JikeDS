package J_3_Binary_Tree_Divide_Conquer.other.Validate;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

public class _469Identical_Binary_Tree {
	/**
     * @param a, b, the root of binary trees.
     * @return true if they are identical, or false.
     */
    public boolean isIdentical(TreeNode a, TreeNode b) {
        // Write your code here
        if (a == null && b == null)
            return true;

        if (a != null && b != null) {
            return a.val == b.val && isIdentical(a.left, b.left)
                    && isIdentical(a.right, b.right);
        }
        return false;
    }


    @Test
    public void test01(){
        int[] arr = {2,1,4};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode root2 = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(isIdentical(root, root2));
    }

//////////////////////////////////////////////////////////

    public boolean isIdentical2(TreeNode a, TreeNode b) {
        // Write your code here
        if (a == null && b == null)
            return true;

        if (a == null || b == null)
            return false;

        return a.val == b.val && isIdentical(a.left, b.left)
                && isIdentical(a.right, b.right);
    }

    @Test
    public void test02(){
        int[] arr = {2,1,4};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode root2 = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(isIdentical2(root, root2));
    }
}

/*Check if two binary trees are identical. Identical means the two binary 
 trees have the same structure and every identical position has the same value.


Have you met this question in a real interview? Yes
Example
    1             1
   / \           / \
  2   2   and   2   2
 /             /
4             4
are identical.

    1             1
   / \           / \
  2   3   and   2   3
 /               \
4                 4
are not identical.

Tags 
Binary Tree
Related Problems 
Easy Tweaked Identical Binary Tree 38 %
Easy Symmetric Binary Tree 38 %
Easy Complete Binary Tree 25 %*/