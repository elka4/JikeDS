package top100.HighFreq._1Linear;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


class LRUCache{
	class Node {
		Integer key;
		Integer value;
		Node prev;
		Node next;
		Node (Integer key, Integer value) {
			this.key = key;
			this.value = value;
			prev = null;
			next = null;
		}
	}
	
	private HashMap<Integer, Node> map;
	private int capacity;
	private Node head;
	private Node tail;
	
	public LRUCache(int capacity) {
		map = new HashMap<Integer, Node>();
		this.capacity = capacity;
		head = new Node(null, null);
		tail = new Node(null, null);
		head.prev = tail;
		head.next = tail;
		tail.prev = head;
		tail.next = head;
	}
	public void put (int key, int value) {
		Node node = map.get(key);
		if (node == null) {
			if (map.size() == capacity) {
				map.remove(head.next.key);
				detach(head.next	);
			}
			node = new Node(key, value);
			map.put(key, node);
			attach(node);
		} else {
			node.value = value;
			detach(node);
			attach(node);
		}
	}
	Integer get (int key) {
		Node node = map.get(key);
		if (node != null) {
			detach(node);
			attach(node);
		}
		return node == null ? null : node.value;
	}
	void detach(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node.prev = null;
		node.next = null;
	}
	void attach (Node node) {
		node.next = tail;
		node.prev = tail.prev;
		tail.prev.next = node;
		tail.prev = node;
	}
	
}
public class _62LRUCache {
	public static void main (String[] args) throws FileNotFoundException{
		Scanner in = new Scanner(new File("input.txt"));
		int n = in.nextInt();
		while (n != -1) {
			LRUCache cache = new LRUCache(n);
			int m = in.nextInt();
			while (m-- != 0) {
				in.nextLine();
				String op = in.next();
				if(op.equals("SET")) {
					cache.put(in.nextInt(), in.nextInt());
				} else {
					System.out.println(cache.get(in.nextInt()));
				}
			}
			n = in.nextInt();
		}
		in.close();
	}
}
