package ss;

import java.util.LinkedList;
import java.util.Queue;


public class Course16_3_CountNode<T> {
	public int count=0;
	/**
	 * 统计结点个数，先序遍历法
	 */
	public void preOrder(TreeNode<T> node){
		if(node!=null){
			count++;
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	/**
	 * 统计结点个数，后序遍历法
	 */
	public int postOrder(TreeNode<T> node){
		if(node==null){
			return 0;
		}else{
			int leftCnt=postOrder(node.left);
			int rightCnt=postOrder(node.right);
			return leftCnt+rightCnt+1;
		}
	}

	/**
	 * 统计叶子节点个数，中序遍历法
	 */
	public void inOrder(TreeNode<T> node){
		if(node!=null){
			inOrder(node.left);
			if(node.left==null&&node.right==null){
				count++;
			}
			inOrder(node.right);
		}
	}

	/**
	 * 统计叶子节点个数，先序遍历法
	 */
	public int countLeaf(TreeNode<T> node){
		if(node==null){
			return 0;
		}else if(node.left==null&&node.right==null){
			return 1;
		}else{
			int leftLeaf=countLeaf(node.left);
			int rightLeaf=countLeaf(node.right);
			return leftLeaf+rightLeaf;
		}
	}

	/**
	 * 统计值为x的节点个数，层序遍历法
	 */
	public void levelOrder(TreeNode<T> root,T x){
		Queue<TreeNode<T>> queue=new LinkedList<TreeNode<T>>();
		queue.offer(root);
		int cnt=0;
		while(!queue.isEmpty()){
			TreeNode<T> node=queue.poll();
			if(node.value.equals(x)){
				cnt++;
			}
			if(node.left!=null){
				queue.offer(node.left);
			}
			if(node.right!=null){
				queue.offer(node.right);
			}
		}
		System.out.println(cnt);
	}
}
