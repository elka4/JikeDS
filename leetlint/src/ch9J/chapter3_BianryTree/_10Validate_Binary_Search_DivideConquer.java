package ch9J.chapter3_BianryTree;


//version 2  Divide and Conquer
public class _10Validate_Binary_Search_DivideConquer {
class ResultType {
	 boolean is_bst;
	 int maxValue, minValue;
	 ResultType(boolean is_bst, int maxValue, int minValue) {
	     this.is_bst = is_bst;
	     this.maxValue = maxValue;
	     this.minValue = minValue;
	 }
}
/**
* @param root: The root of binary tree.
* @return: True if the binary tree is BST, or false
*/
public boolean isValidBST(TreeNode root) {
   ResultType r = validateHelper(root);
   return r.is_bst;
}

private ResultType validateHelper(TreeNode root) {
   if (root == null) {
       return new ResultType(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
   }
   
   ResultType left = validateHelper(root.left);
   ResultType right = validateHelper(root.right);
   
   if (!left.is_bst || !right.is_bst) {
       // if is_bst is false then minValue and maxValue are useless
       return new ResultType(false, 0, 0);
   }
   
   if (root.left != null && left.maxValue >= root.val || 
         root.right != null && right.minValue <= root.val) {
       return new ResultType(false, 0, 0);
   }
   
   //前面都是不正常，这里时正常
   return new ResultType(true,
                         Math.max(root.val, right.maxValue),
                         Math.min(root.val, left.minValue));
}
}
