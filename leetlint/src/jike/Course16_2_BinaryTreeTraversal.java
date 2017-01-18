package jike;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

/**
 * 二叉树的遍历类
 *
 */
public class Course16_2_BinaryTreeTraversal {
/**
 * 先序遍历
 */
public static <T> void preOrder(TreeNode<T> node){
	if(node==null){
		return;
	}else{
	/*	if(node.value.equals(new Integer(7))){
			System.out.println("Hello!");
		}*/
		System.out.println(node.value);
		preOrder(node.left);
		preOrder(node.right);
	}
}
/**
 * 中序遍历
 */
public static <T> void inOrder(TreeNode<T> node){
	if(node!=null){
		inOrder(node.left);
		System.out.println(node.value);
		inOrder(node.right);
	}
}
/**
 * 后序遍历
 */
public static <T> void postOrder(TreeNode<T> node){
	if(node!=null){
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.value);
	}
}
/**
 * 层序遍历
 */
public static <T> void levelOrder(TreeNode<T> root){
	Queue<TreeNode<T>> queue=new LinkedList<TreeNode<T>>();
	queue.offer(root);
	while(!queue.isEmpty()){
		TreeNode<T> node=queue.poll();
		System.out.println(node.value);
		if(node.left!=null){
			queue.offer(node.left);
		}
		if(node.right!=null){
			queue.offer(node.right);
		}
	}
}
@Test
public void testPreOrder(){
	Integer[] array={1,2,3,4,5,null,6,null,null,null,null,7};
	Integer nullValue=null;
	TreeNode<Integer> root=Course16_2_CreateBinaryTree.arrayToBinaryTree(array, nullValue);
	preOrder(root);
}
@Test
public void testInOrder(){
	String[] array={"1","2","3","4","5","#","6","#","#","#","#","7"};
	String nullValue="#";
	TreeNode<String> root=Course16_2_CreateBinaryTree.arrayToBinaryTree(array, nullValue);
	inOrder(root);
}
@Test
public void testPostOrder(){
	Course16_2_Department[] array={new Course16_2_Department(1),new Course16_2_Department(2),new Course16_2_Department(3),
			new Course16_2_Department(4),new Course16_2_Department(5),null,new Course16_2_Department(6),
			null,null,null,null,new Course16_2_Department(7)};
	Course16_2_Department nullValue=null;
	TreeNode<Course16_2_Department> root=Course16_2_CreateBinaryTree.arrayToBinaryTree(array, nullValue);
	postOrder(root);
}
@Test
public void testLevelOrder(){
	Integer[] array={1,2,3,4,5,null,6,null,null,null,null,7};
	Integer nullValue=null;
	TreeNode<Integer> root=Course16_2_CreateBinaryTree.arrayToBinaryTree(array, nullValue);
	levelOrder(root);
}
}
