package top100.HighFreq._0Linear;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


class BFSNode{
	int value;
	BFSNode left;
	BFSNode right;
}

class Pair{
	BFSNode node;
	int pos;
	Pair(BFSNode ptr, int p) {
		node = ptr;
		pos = p;
	}
	
}

class BFS{
	HashMap<Integer, LinkedList> rank;
	BFS() {
		rank = new HashMap<Integer, LinkedList>();
	}
	void traverse(BFSNode ptr, int pos) {
		Queue process = new LinkedList<>();
		
		process.add(new Pair(ptr, pos));
		while(process.isEmpty() == false) {
			Pair pair = (Pair)process.poll();
			if (pair.node != null) {
				ptr = pair.node;
				pos = pair.pos;
				if (rank.containsKey(pos) == false){
					rank.put(pos, new LinkedList());
				}
				rank.get(pos).add(ptr.value);
				
				process.add(new Pair(ptr.left, pos - 1));
				process.add(new Pair(ptr.right, pos - 1));
				
			}
		}
	}
	
	void display() {
		int n = 0;
		while (rank.containsKey(n) == true) {
			--n;
		}
		for (int i = n + 1; rank.containsKey(i); ++i) {
			LinkedList list = rank.get(i);
			list.forEach((value) -> System.out.print(" " + value));
		}
		System.out.println();
	}
}

public class _7BinaryTreeVerticalTraversal_BFS {
	public static void main(String[] args) throws FileNotFoundException{
		Scanner in = new Scanner(new File("input.txt"));
		int n = in.nextInt();
		while(n != -1) {
			
			
		}
		
		
	}
}
