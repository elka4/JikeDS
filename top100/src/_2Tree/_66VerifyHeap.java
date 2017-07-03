package _2Tree;
import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class _66VerifyHeap {
	static class Node{
		int value;
		Node left;
		Node right;
		Node(){
			left = null;
			right = null;
			value = -1;
		}

	}

    static class  Checker{
		boolean isHeap(Node root) {
			if(root == null) return true;
			if(root.left != null && root.value > root.left.value) return false;
			if(root.right != null && root.value > root.right.value) return false;
			return isHeap(root.left) && isHeap(root.right);
		}
	}

	public static void main(String[] args)throws FileNotFoundException {

        Scanner in = new Scanner(new File(
"/Users/tianhuizhu/Downloads/uber/code/JikeDS/top100/src/_2Tree/_66VerifyHeap"));
		int n = in.nextInt();
		Checker checker = new Checker();
		while(n != -1){
		    //build tree
            Node[] tree = new Node[n];
            for (int i = 0; i < n; i++) {
                tree[i] = new Node();
            }
            for (int i = 0; i < n; ++i) {
                int leftId = in.nextInt();
                if(leftId != -1) tree[i].left = tree[leftId];
                int rightId = in.nextInt();
                if(rightId != -1) tree[i].right = tree[rightId];
            }
            System.out.println(checker.isHeap(tree[0]));
            n = in.nextInt();
        }
		in.close();
	}
}
/*
*            0
*        1       2
*     3    4   5    6
*   7
*
*
8
0 1 2
1 3 4
2 5 6
3 7 -1
4 -1 -1
5 -1 -1
6 -1 -1
7 -1-1
3
* */