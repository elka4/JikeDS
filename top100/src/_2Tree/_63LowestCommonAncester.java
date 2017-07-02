package _2Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class _63LowestCommonAncester {

	static class Finder{
		Node findFromRoot(Node a, Node b, Node root){
			if(root == null || root == a || root == b) return root;
			Node left = findFromRoot(a, b, root.left);
			Node right = findFromRoot(a, b, root.right);
			if(left != null && right != null) return root;
			if(left != null) return left;
			return right;
		}
	}
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File(
"/Users/tianhuizhu/Downloads/uber/code/JikeDS/top100/src/_2Tree/input_63"));
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
			int m = in.nextInt();//3， 总共找几对node的公共祖先, 6和3， 2和5， 2和7
			while(m-- != 0) {
				System.out.println(finder.findFromRoot(
						tree[in.nextInt()], tree[in.nextInt()], tree[0]).id);
			}
			n = in.nextInt();
		}
		in.close();
	}
}
