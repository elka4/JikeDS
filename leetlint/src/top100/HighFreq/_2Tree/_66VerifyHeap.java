package top100.HighFreq._2Tree;


public class _66VerifyHeap {
	class Node{
		int value;
		Node left;
		Node right;
		Node(){
			left = null;
			right = null;
			value = -1;
		}
	}
	
	class Checker{
		boolean isHeap(Node root) {
			if(root == null) return true;
			if(root.left != null && root.value > root.left.value) return false;
			if(root.right != null && root.value > root.right.value) return false;
			return isHeap(root.left) && isHeap(root.right);
		}
	}
}
