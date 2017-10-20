package _4_Tree.Traverse;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
			System.out.print(root.value + " ");
			PreOrder(root.left);
			PreOrder(root.right);
		}
		void InOrder(Node root){
			if(root == null) return;
			InOrder(root.left);
			System.out.print(root.value + " ");
			InOrder(root.right);
		}
		void PostOrder(Node root) {
			if(root == null) return;
			PostOrder(root.left);
			PostOrder(root.right);
			System.out.print(root.value + " ");
		}
	}
	/*
        0
     1    2
    3 4  5
     6 7
 */

	@Test
	public void test01() throws FileNotFoundException {
		Scanner in = new Scanner(new File(
"/Users/tianhuizhu/Downloads/uber/code/JikeDS/top100/src/_2Tree/64_67"));
		int n = in.nextInt();
		_64TreeTraversals.Traverser traverser = new _64TreeTraversals.Traverser();
		while (n != -1) {
			//build tree
			_64TreeTraversals.Node[] tree = new _64TreeTraversals.Node[n];
			for (int i = 0; i < n; ++i) tree[i] = new _64TreeTraversals.Node();

			for (int i = 0; i < n; ++i) {
				tree[i].value = in.nextInt();
				int leftId = in.nextInt();
				if(leftId != -1)  tree[i].left = tree[leftId];
				int rightId = in.nextInt();
				if(rightId != -1) tree[i].right = tree[rightId];
			}

			//calculate
			traverser.PreOrder(tree[0]);
			System.out.println();

			traverser.InOrder(tree[0]);
			System.out.println();

			traverser.PostOrder(tree[0]);
			System.out.println();

			n = in.nextInt();
		}
		in.close();
	}
	
}
