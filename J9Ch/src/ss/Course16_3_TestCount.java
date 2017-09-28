package ss;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class Course16_3_TestCount {
	private Course16_3_CountNode<Integer> countNode;
	@Before
	public void init(){
		countNode=new Course16_3_CountNode<Integer>();
	}
	@Test
	public void testCount01(){
		Integer[] array={8,9,5,null,1,7};
		TreeNode<Integer> root=Course16_2_CreateBinaryTree.arrayToBinaryTree(array, null);
		countNode.preOrder(root);
		System.out.println(countNode.count);
	}
	@Test
	public void testCount02(){
		TreeNode<Integer> root=new TreeNode<Integer>(8, 
				new TreeNode<Integer>(9, null, new TreeNode<Integer>(1)), 
				new TreeNode<Integer>(5, new TreeNode<Integer>(7), null));
		countNode.preOrder(root);
		System.out.println(countNode.count);
	}
	@Test
	public void testCount03(){
		TreeNode<Integer> node1=new TreeNode<Integer>(1);
		TreeNode<Integer> node7=new TreeNode<Integer>(7);
		TreeNode<Integer> node9=new TreeNode<Integer>(9);
		TreeNode<Integer> node5=new TreeNode<Integer>(5);
		TreeNode<Integer> root=new TreeNode<Integer>(8);
		node9.right=node1;
		node5.left=node7;
		root.left=node9;
		root.right=node5;
		countNode.preOrder(root);
		System.out.println(countNode.count);
	}
	@Test
	public void testCount04(){
		Integer[] array={8,9,5,null,1,7};
		TreeNode<Integer> root=Course16_2_CreateBinaryTree.arrayToBinaryTree(array, null);
		int count=countNode.postOrder(root);
		System.out.println(count);
	}
	@Test
	public void testCount05(){
		Integer[] array={1,2,3,null,null,4,5,6,7};
		TreeNode<Integer> root=Course16_2_CreateBinaryTree.arrayToBinaryTree(array, null);
		countNode.inOrder(root);
		int count=countNode.countLeaf(root);
		Assert.assertTrue(countNode.count==count);
	}
	@Test
	public void testCount06(){
		Integer[] array={4,2,3,null,null,4,1,4,2};
		TreeNode<Integer> root=Course16_2_CreateBinaryTree.arrayToBinaryTree(array, null);
		countNode.levelOrder(root, 4);
	}
}
