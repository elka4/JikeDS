package j_8_DataStructure;

import java.util.LinkedList;
import java.util.Queue;

public class _05Implement_Stack_by_Two_Queues {
	private Queue<Integer> queue1;
    private Queue<Integer> queue2;
    
    public _05Implement_Stack_by_Two_Queues() {
        queue1 = new LinkedList<Integer>();
        queue2 = new LinkedList<Integer>();
    }
    
    private void moveItems() {
        while (queue1.size() != 1) {
            queue2.offer(queue1.poll());
        }
    }
    
    private void swapQueues() {
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }
    
    /**
     * push a new item into the stack
     */
    public void push(int value) {
        queue1.offer(value);
    }
    
    /**
     * return the top of the stack
     */
    public int top() {
        moveItems();
        int item = queue1.poll();
        swapQueues();
        queue1.offer(item);
        return item;
    }
    
    /**
     * pop the top of the stack and return it
     */
    public void pop() {
        moveItems();
        queue1.poll();
        swapQueues();
    }
    
    /**
     * check the stack is empty or not.
     */
    public boolean isEmpty() {
        return queue1.isEmpty();
    }
}

/*Implement a stack by two queues. 
 * The queue is first in first out (FIFO). 
 * That means you can not directly pop the last element in a queue.
 

Have you met this question in a real interview? Yes
Example
push(1)
pop()
push(2)
isEmpty() // return false
top() // return 2
pop()
isEmpty() // return true
Tags 
Stack Queue
Related Problems 
Easy Implement Stack 40 %*/