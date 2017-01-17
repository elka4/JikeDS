package top100.HighFreq._0Linear;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

 
class DFSNode{
	int value;
	DFSNode left;
	DFSNode right;
}

class DFS {
	
	HashMap<Integer, LinkedList> rank;
	
	DFS() {
		rank = new HashMap<Integer, LinkedList>();
	}
	
	@SuppressWarnings("unchecked")
	void traverse(DFSNode ptr, int pos){
		if (ptr == null) return;
		if (rank.containsKey(pos) == false) rank.put(pos, new LinkedList());
		rank.get(pos).add(ptr.value);
		traverse(ptr.left, pos - 1);
		traverse(ptr.right, pos + 1);
	}
	
	@SuppressWarnings("unchecked")
	void display(){
		int n = 0;
		while (rank.containsKey(n) == true){
			--n;
		}
		for (int i = n + 1; rank.containsKey(i); ++i) {
			@SuppressWarnings("rawtypes")
			LinkedList list = rank.get(i);
			list.forEach((value) -> System.out.println(" " + value));
		}
		System.out.println();
	}	
}

public class _7BinaryTreeVerticalTraversal_DFS{
	@SuppressWarnings("unused")
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input.txt"));		
		DFS solution = new DFS();
		int n = in.nextInt();
		while(n != -1) {
			
			DFSNode[] tree = new DFSNode[n];
			for(int i = 0; i < n; ++i) tree[i] = new DFSNode();
				for(int i = 0; i < n; ++i) {
					tree[i].value= in.nextInt();
					
					int left = in.nextInt();
					if (left != -1) tree[i].left = tree[left];
					int right = in.nextInt();
					if (right != -1) tree[i].right = tree[right];
				}
				
				DFS dfs = new DFS();
				dfs.traverse(tree[0], 0);
				dfs.display();

				n = in.nextInt();
		}
		in.close();
	}
}
