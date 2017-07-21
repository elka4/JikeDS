package chapter2_DataStructure1;

import java.util.HashMap;

public class UnionFind {
	UnionFind(){}
	HashMap<Integer, Integer> father = new HashMap<Integer,Integer>();
	
	int find(int x) {
		int parent = x;
		while (parent != father.get(parent)) {
			parent = father.get(parent);
		}
		return parent;
	}
	
	void union(int x, int y){
		int fa_x = find(x);
		int fa_y = find(y);
		if (fa_x != fa_y) {
			father.put(fa_x, fa_y);
		}
	}
	
	//average O(1)
	int compressed_find(int x){
		int parent = father.get(x);
		while (parent != father.get(parent)) {
			parent = father.get(parent);
		}
		int next;
		while (x != father.get(x)) {
			next = father.get(x);
			father.put(x, parent);
			x = next;
		}
		return parent;
	}
	
}
