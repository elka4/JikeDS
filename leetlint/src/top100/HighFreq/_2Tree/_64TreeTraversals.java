package top100.HighFreq._2Tree;

public class _64TreeTraversals {
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
	class Traverser{
		void PreOrder(Node root) {
			if(root == null) return;
			System.out.println(root.value + " ");
			PreOrder(root.left);
			PreOrder(root.right);
		}
		void InOrder(Node root){
			if(root == null) return;
			InOrder(root.left);
			System.out.println(root.value + " ");
		}
		void PostOrder(Node root) {
			if(root == null) return;
			PostOrder(root.left);
			PostOrder(root.right);
			System.out.println(root.value + " ");
		}
	}
	
}
