package _4_Tree.Search_Inseart_Delete;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

public class Search_Inseart {
	//Iteration
	public TreeNode search (TreeNode root, int val) {
		if(root == null || root.val == val)
			return root;
		TreeNode cur = root;
		while(cur != null) {
			if(cur.val == val)
				return cur;
			else if (cur.val < val)
				cur = cur.right;
			else 
				cur = cur.left;
		}
		return null;
		
	}
	public TreeNode search2 (TreeNode root, int val) {
		if(root == null || root.val == val){
			return root;
		} else if (root.val < val){
			return search2(root.right, val);
		} else {
			return search2(root.left, val);
		}
	}

    @Test
    public void test01() {
        int[] arr = {5, 3, 7,1,2,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        search(root, 3).print();
        search2(root, 6).print();

    }

    @Test
    public void test02() {
        int[] arr = {5, 3, 7,1,2,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        search(root, 3).print();
        search2(root, 6).print();
        search2(root, 7).print();

    }
///////////////////////////////////////////////////////////////

	//Recursion
	public TreeNode insert(TreeNode root, int val) {
		if(root == null){
			return new TreeNode(val);
		}
		TreeNode cur = root;
		TreeNode prev = null;
		//1.Search the position to insert
		while(cur != null && cur.val != val){
			prev = cur;
			if(cur.val < val){
				cur = cur.right;
			} else {
				cur = cur.left;
			}
		}
		//2.Insert the large node
		if(prev.val < val){
			prev.right = new TreeNode(val);
		} else {
			prev.left = new TreeNode(val);
		}
		return root;

	}

	public TreeNode insert2(TreeNode root, int val) {
		//Base Case
		if(root == null) return new TreeNode(val);

		if(root.val > val) {
			//Assume all nodes are handled in left subtree
			root.left = insert2(root.left, val);
		} else if (root.val < val) {
			//assume all nodes are handled in right subtree
			root.right = insert2(root.right, val);
		}
		return root;
	}

    @Test
    public void test03() {
        int[] arr = {3, 2, 5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        insert(root, 4);
        insert2(root, 1);
        root.print();
    }

    @Test
    public void test04() {
        int[] arr = {5, 3, 7,1,2,6};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        insert(root, 4);
        insert2(root, 8);
        root.print();
    }
////////////////////////////////////////////////////////////




}
