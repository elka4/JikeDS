package jike;

/**
 * 二叉树的节点
 */
public class Course16_2_TreeNode<T>{
	public T value;
	public Course16_2_TreeNode<T> leftChild;
	public Course16_2_TreeNode<T> rightChild;
	public Course16_2_TreeNode( T value,Course16_2_TreeNode<T> leftChild, Course16_2_TreeNode<T> rightChild) {
		super();
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.value = value;
	}
	public Course16_2_TreeNode(T value) {
		super();
		this.value = value;
	}
	@Override
	public String toString() {
		return "TreeNode [value=" + value + "]";
	}
}