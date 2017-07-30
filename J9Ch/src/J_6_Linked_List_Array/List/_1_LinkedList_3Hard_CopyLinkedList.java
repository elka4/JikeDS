package J_6_Linked_List_Array.List;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianhuizhu on 7/15/17.
 */
public class _1_LinkedList_3Hard_CopyLinkedList {

    public class RandomNode{
        int value;
        RandomNode next;
        RandomNode random;
        RandomNode (int value){
            this.value = value;
        }
    }

    public RandomNode copy(RandomNode head){
        if(head == null){
            return head;
        }
        Map<RandomNode, RandomNode> map = new HashMap<>();
        RandomNode cur = head;
        while(cur != null){
            // 1.Create nodes of cur, next, ran
            if (!map.containsKey(cur)) {
                RandomNode curCopy = new RandomNode(cur.value);
                map.put(cur, curCopy);
            }

            if (cur.next != null && !map.containsKey(cur.next)){
                RandomNode nextCopy = new RandomNode(cur.next.value);
                map.put(cur.next, nextCopy);
            }

            if(cur.random != null && !map.containsKey(cur.random)){
                RandomNode randomCopy = new RandomNode(cur.random.value);
                map.put(cur.random, randomCopy);
            }

            // 2.connect curcopy with nextcopy, rancopy
            if(cur.next != null){
                map.get(cur).next = map.get(cur.next);
            }
            if (cur.random != null){
                map.get(cur).random = map.get(cur.random);
            }

            // 3.next round
            cur = cur.next;
        }
        return map.get(head);
    }

///////////////////////////////////////////////////////////////////

    //No HashMap version

    private void copyNext(RandomNode head) {
        while (head != null) {
            RandomNode newNode = new RandomNode(head.value);
            newNode.random = head.random;
            newNode.next = head.next;
            head.next = newNode;
            head = head.next.next;
        }
    }

    private void copyRandom(RandomNode head) {
        while (head != null) {
            if (head.next.random != null) {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
    }

    private RandomNode splitList(RandomNode head) {
        RandomNode newHead = head.next;
        while (head != null) {
            RandomNode temp = head.next;
            head.next = temp.next;
            head = head.next;
            if (temp.next != null) {
                temp.next = temp.next.next;
            }
        }
        return newHead;
    }

    public RandomNode copyRandomList2(RandomNode head) {
        if (head == null) {
            return null;
        }
        copyNext(head);
        copyRandom(head);
        return splitList(head);
    }
    
    
}
