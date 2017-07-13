package J_3_Binary_Tree_Divide_Conquer.other.Traverse;
import lib.TreeNode;

import java.util.ArrayList;

//Version 2: Divide & Conquer
@SuppressWarnings("all")

//递归的定义：找到root为根的preorder并return
public class _66Preorder_Traversal_DivideConquer {
	public ArrayList<Integer> preorderTraversal(TreeNode root) {
	    ArrayList<Integer> result = new ArrayList<>();
	    // 递归的出口  // null or leaf? null!
	    if (root == null) {
	        return result;
	    }
	    // 递归的拆解：left，right // 先divide，再想每个具体做什么
	    // Divide
	    ArrayList<Integer> left = preorderTraversal(root.left);
	    ArrayList<Integer> right = preorderTraversal(root.right);
	
	    // Conquer
	    result.add(root.val);
	    result.addAll(left);
	    result.addAll(right);
	    return result;
	}
}
/* inorder
result.addAll(left);
result.add(root.val);
result.addAll(right);*/

/* postorder
result.addAll(left);
result.addAll(right);
result.add(root.val);*/