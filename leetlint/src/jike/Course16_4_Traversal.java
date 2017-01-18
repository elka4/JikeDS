package jike;

import java.util.ArrayList;
import java.util.List;


/**
 * 解决先序、中序、后序三道面试题
 */
public class Course16_4_Traversal {
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	  public List<Integer> preorderTraversal(TreeNode root) {
	        List<Integer> list=new ArrayList<Integer>();
	        preOrder(root, list);
	        return list;
	    }
	  private void preOrder(TreeNode p,List<Integer> list){
		  if(p!=null){
			  list.add(p.val);
			  preOrder(p.left, list);
			  preOrder(p.right, list);
		  }
	  }
	  public List<Integer> list=null;
	  public List<Integer> inorderTraversal(TreeNode root) {
		  list=new ArrayList<Integer>();
		  inOrder(root);
		  return list;
	    }
	  private void inOrder(TreeNode p){
		  if(p!=null){
			  inOrder(p.left);
			  list.add(p.val);
			  inOrder(p.right);
		  }
	  }
	  public List<Integer> postorderTraversal(TreeNode root) {
		  list=new ArrayList<Integer>();
		  postOrder(root);
		  return list;
	    }
	  private void postOrder(TreeNode p){
		  if(p!=null){
			  postOrder(p.left);
			  postOrder(p.right);
			  list.add(p.val);
		  }
	  }
}
