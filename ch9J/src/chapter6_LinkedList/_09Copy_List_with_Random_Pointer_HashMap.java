package chapter6_LinkedList;

import java.util.HashMap;

public class _09Copy_List_with_Random_Pointer_HashMap {
	public RandomListNode copyRandomList(RandomListNode head) {
	    if (head == null) {
	        return null;
	    }
	
	    HashMap<RandomListNode, RandomListNode> map = 
	    		new HashMap<RandomListNode, RandomListNode>();
	    RandomListNode dummy = new RandomListNode(0);
	    RandomListNode pre = dummy, newNode;
	    
	    while (head != null) {
	        if (map.containsKey(head)) {
	            newNode = map.get(head);
	        } else {
	            newNode = new RandomListNode(head.label);
	            map.put(head, newNode);
	        }
	        pre.next = newNode;
	
	        if (head.random != null) {
	            if (map.containsKey(head.random)) {
	                newNode.random = map.get(head.random);
	            } else {
	                newNode.random = new RandomListNode(head.random.label);
	                map.put(head.random, newNode.random);
	            }
	        }
	
	        pre = newNode;
	        head = head.next;
	    }
	
	    return dummy.next;
    }
}


/*
 * A linked list is given such that each node contains an additional
 *  random pointer which could point to any node in the list or null.

Return a deep copy of the list.

Have you met this question in a real interview? Yes
Example
Challenge 
Could you solve it with O(1) space?

Tags 
Hash Table Linked List Uber
Related Problems 
 */