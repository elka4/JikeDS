package _3_Linked_List;
import java.util.*;
/*
LeetCode – Copy List with Random Pointer

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.


 */
public class Copy_List_with_Random_Pointer {

    /*
    Java Solution 1

We can solve this problem by doing the following steps:

copy every node, i.e., duplicate every node, and insert it to the list
copy random pointers for all newly created nodes
break the list to two
     */
    public RandomListNode copyRandomList(RandomListNode head) {

        if (head == null)
            return null;

        RandomListNode p = head;

        // copy every node and insert to list
        while (p != null) {
            RandomListNode copy = new RandomListNode(p.label);
            copy.next = p.next;
            p.next = copy;
            p = copy.next;
        }

        // copy random pointer for each new node
        p = head;
        while (p != null) {
            if (p.random != null)
                p.next.random = p.random.next;
            p = p.next.next;
        }

        // break list to two
        p = head;
        RandomListNode newHead = head.next;
        while (p != null) {
            RandomListNode temp = p.next;
            p.next = temp.next;
            if (temp.next != null)
                temp.next = temp.next.next;
            p = p.next;
        }

        return newHead;
    }
/*    The break list part above move pointer 2 steps each time, you can also move one at a time which is simpler, like the following:*/

/*            while(p != null && p.next != null){
        RandomListNode temp = p.next;
        p.next = temp.next;
        p = temp;
    }*/

//////////////////////////////////////////
/*
    Java Solution 2 - Using HashMap

    From Xiaomeng's comment below, we can use a HashMap which makes it simpler.*/

    public RandomListNode copyRandomList2(RandomListNode head) {
        if (head == null)
            return null;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode newHead = new RandomListNode(head.label);

        RandomListNode p = head;
        RandomListNode q = newHead;
        map.put(head, newHead);

        p = p.next;
        while (p != null) {
            RandomListNode temp = new RandomListNode(p.label);
            map.put(p, temp);
            q.next = temp;
            q = temp;
            p = p.next;
        }

        p = head;
        q = newHead;
        while (p != null) {
            if (p.random != null)
                q.random = map.get(p.random);
            else
                q.random = null;

            p = p.next;
            q = q.next;
        }

        return newHead;
    }

}