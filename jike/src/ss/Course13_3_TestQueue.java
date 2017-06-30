package ss;

import java.util.LinkedList;
import java.util.Queue;

public class Course13_3_TestQueue {
	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(0);
		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		while (!queue.isEmpty()) {
			System.out.println(queue.peek() + " " + queue.poll());
		}
	}
}
