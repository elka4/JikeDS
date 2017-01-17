package top100.HighFreq._1Linear;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


@SuppressWarnings("serial")
class LRUCache_2<K, V> extends LinkedHashMap<K, V> {
	private int capacity;
	LRUCache_2 (int capacity) {
		super(128, 0.75f, true);
		this.capacity = capacity;
	}
	@Override
	public boolean removeEldestEntry (Map.Entry<K, V> eldest) {
		return size() > capacity;
	}
	
}
public class _62LRUCache_LinkedHashMap {
	public static void main (String[] args) throws FileNotFoundException{
		Scanner in = new Scanner(new File("input.txt"));
		int n = in.nextInt();
		while (n != -1) {
			LRUCache_2<Integer, Integer> cache = new LRUCache_2<>(n);
			int m = in.nextInt();
			while (m-- != 0) {
				in.nextLine();
				String op = in.next();
				if (op.equals("SET")) {
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
