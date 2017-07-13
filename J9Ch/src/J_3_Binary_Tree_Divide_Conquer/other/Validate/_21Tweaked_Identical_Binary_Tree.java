package J_3_Binary_Tree_Divide_Conquer.other.Validate;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

public class _21Tweaked_Identical_Binary_Tree {

	public boolean isTweakedIdentical(TreeNode a, TreeNode b) {
	    if (a == null && b == null) {
	        return true;
	    }
	    if (a == null || b == null) {
	        return false;
	    }
	    if (a.val != b.val) {
	        return false;
	    }
	    
	    if (isTweakedIdentical(a.left, b.left) &&
	    		isTweakedIdentical(a.right, b.right)) {
	        return true;
	    }
	    
	    if (isTweakedIdentical(a.left, b.right) &&
	    		isTweakedIdentical(a.right, b.left)) {
	        return true;
	    }
	    
	    return false;
	}

    @Test
    public void test01() {
        int[] arr = {1,2,3};
        lib.TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.left.left = new TreeNode(4);

        int[] arr2 = {1,3,2};
        lib.TreeNode root2 = AssortedMethods.createTreeFromArray(arr2);
        root2.right.right = new TreeNode(4);

        System.out.println("root: ");
        root.print();
        root2.print();

        System.out.println(isTweakedIdentical(root,root2));
    }

    @Test
    public void test02() {
        int[] arr = {1,2,3};
        lib.TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.left.left = new TreeNode(4);

        int[] arr2 = {1,3,2};
        lib.TreeNode root2 = AssortedMethods.createTreeFromArray(arr2);
        root2.right.right = new TreeNode(4);

        System.out.println("root: ");
        root.print();
        root2.print();

        System.out.println(isTweakedIdentical(root,root2));
    }
}

/*Check two given binary trees are identical or not. Assuming any number of tweaks are allowed. A tweak is defined as a swap of the children of one node in the tree.

 Notice

There is no two nodes with the same value in the tree.

Have you met this question in a real interview? Yes
Example
    1             1
   / \           / \
  2   3   and   3   2
 /                   \
4                     4
are identical.

    1             1
   / \           / \
  2   3   and   3   2
 /             /
4             4
are not identical.

Challenge 
O(n) time

Tags 
Binary Tree
Related Problems 
Easy Identical Binary Tree 45 %*/