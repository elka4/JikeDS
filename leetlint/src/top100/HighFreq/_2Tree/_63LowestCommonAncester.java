package top100.HighFreq._2Tree;
import java.io.*;
import java.util.*;

class Node{
	int id;
	Node left;
	Node right;
	Node(int id) {
		left = null;
		right = null;
		this.id = id;
	}
}

class Finder{
	Node findFromRoot(Node a, Node b, Node root){
		if(root == null || root == a || root == b) return root;
		Node left = findFromRoot(a, b, root.left);
		Node right = findFromRoot(a, b, root.right);
		if(left != null && right != null) return root;
		if(left != null) return left;
		return right;
	}
}

public class _63LowestCommonAncester {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input.txt"));
		int n = in.nextInt();
		Finder finder = new Finder();
		while (n != -1) {
				//build tree
			Node[] tree = new Node[n];
			for (int i = 0; i < n; ++i) tree[i] = new Node(i);
			for (int i = 0; i < n; ++i) {
				int leftId = in.nextInt();
				if(leftId != -1) tree[i].left = tree[leftId];
				int rightId = in.nextInt();
				if(rightId != -1) tree[i].right = tree[rightId];
			}
			int m = in.nextInt();
			while(m-- != 0) {
				System.out.println(finder.findFromRoot(
						tree[in.nextInt()], tree[in.nextInt()], tree[0]).id);
			}
			n = in.nextInt();
		}
		in.close();
	}
}
