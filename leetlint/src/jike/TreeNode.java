package jike;

class TreeNode<T> {
	T value;
	TreeNode<T> left;
	TreeNode<T> right;
	public TreeNode(T value, TreeNode<T> left, TreeNode<T> right) {
		super();
		this.value = value;
		this.left = left;
		this.right = right;
	}
	public TreeNode(T value) {
		super();
		this.value = value;
	}
}

