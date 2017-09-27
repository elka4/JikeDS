package J_3_BTree_Divide.other.Validate;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

// Tweaked Identical Binary Tree
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
        root2.left.left = new TreeNode(4);

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

/*
原题

检查两棵二叉树是否在经过若干次扭转后可以等价。扭转的定义是，交换任意节点的左右子树。
等价的定义是，两棵二叉树必须为相同的结构，并且对应位置上的节点的值要相等。
注意：你可以假设二叉树中不会有重复的节点值。
样例

    1             1
   / \           / \
  2   3   and   3   2
 /                   \
4                     4
是扭转后可等价的二叉树。

    1             1
   / \           / \
  2   3   and   3   2
 /             /
4             4
就不是扭转后可以等价的二叉树。

解题思路

Recursion - 递归求解，分治的思路。
注意，题目中说的是经过若干次扭转后可以等价，所以不要忘记考虑完全identical的情况，
某一个节点的左右子树翻转一次对称，反转两次还原。

作者：Jason_Yuan
链接：http://www.jianshu.com/p/0623cf8ad71b
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */