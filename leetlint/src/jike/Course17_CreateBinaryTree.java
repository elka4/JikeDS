package jike;

import java.util.ArrayList;
import java.util.List;

public class Course17_CreateBinaryTree {
	public static Course17_TreeNode arrayToBinaryTree(Integer[] array) {
		if (array == null || array.length == 0) {
			return null;
		} else {
			List<Course17_TreeNode> tempList = new ArrayList<Course17_TreeNode>();
			Course17_TreeNode root = new Course17_TreeNode(array[0]);
			tempList.add(root);
			int index = 1;
			loop : while (tempList.size() > 0 && index < array.length) {
				List<Course17_TreeNode> newList = new ArrayList<Course17_TreeNode>();
				for (Course17_TreeNode node : tempList) {
					if (index == array.length) {
						break loop;
					}
					if (array[index] != null) {
						node.left = new Course17_TreeNode(array[index]);
						newList.add(node.left);
					}
					index++;
					if (index == array.length) {
						break loop;
					}
					if (array[index] != null) {
						node.right = new Course17_TreeNode(array[index]);
						newList.add(node.right);
					}
					index++;
				}
				tempList = newList;
			}
			return root;
		}
	}
}
