package ss;

public class Course17_TreeNode {
	public int val;
	public Course17_TreeNode left;
	public Course17_TreeNode right;
	public Course17_TreeNode(int val, Course17_TreeNode left, Course17_TreeNode right) {
		super();
		this.val = val;
		this.left = left;
		this.right = right;
	}
	public Course17_TreeNode(int val) {
		super();
		this.val = val;
	}
	@Override
	public String toString() {
		return "TreeNode [val=" + val + "]";
	}

}
