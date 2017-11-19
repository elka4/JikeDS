package _03_List._List;
import java.util.*;
import org.junit.Test;import lib.*;


//  138. Copy List with Random Pointer

//
//  http://www.lintcode.com/zh-cn/problem/copy-list-with-random-pointer/
public class _138_List_Copy_List_with_Random_Pointer_H {
/*    A solution with constant space complexity O(1) and linear time complexity O(N)


    Really marvelous idea! Thank you!
    After understanding your idea, I made my recursion code.*/

/**
 *Basic idea:
 * Three phases:
 * First, recursively call to merge the original list and the new list, one after the other, node by node.
 * Second, during the return of the recursion, construct the random ptr for the new list.
 * Third, split the merged list to the original one and the deep-copied one.
 * The idea is from https://discuss.leetcode.com/topic/7594/a-solution-with-constant-space-complexity-o-1-and-linear-time-complexity-o-n
 *Result:
 * 11 / 11 test cases passed.
 * Status: Accepted
 * Runtime: 2 ms
 * Your runtime beats 72.87% of java submissions.
 *Date:
 * 9/25/2016
 */

    /**
     * Definition for singly-linked list with a random pointer.
     * class RandomListNode {
     *     int label;
     *     RandomListNode next, random;
     *     RandomListNode(int x) { this.label = x; }
     * };
     */
    public class Solution1 {
        public RandomListNode copyRandomList(RandomListNode head) {
            if(head==null)
                return null;
            RandomListNode newhead = new RandomListNode(head.label);
            mergeCopyRandomList(head,newhead);
            seperateTwoList(head);
            return newhead;
        }

        private void mergeCopyRandomList(RandomListNode head, RandomListNode newhead){
            if(head==null){
                return;
            }
            RandomListNode next = head.next;
            RandomListNode newNext = next==null?null:new RandomListNode(next.label);
            head.next = newhead;
            newhead.next = next;
            mergeCopyRandomList(next,newNext);
            //so far, all the next ptrs in newhead list are good.
            newhead.random = head.random==null?null:head.random.next;
        }

        private void seperateTwoList(RandomListNode head){
            if(head==null)
                return;
            RandomListNode ori = head;
            RandomListNode cpy = head.next;
            while(ori!=null){
                ori.next = cpy.next;
                ori = ori.next;
                if(ori!=null){
                    cpy.next = ori.next;
                    cpy = cpy.next;
                }
            }
        }
    }


    //Java O(n) solution
    class Solution2{
        public RandomListNode copyRandomList(RandomListNode head) {
            if (head == null) return null;

            Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

            // loop 1. copy all the nodes
            RandomListNode node = head;
            while (node != null) {
                map.put(node, new RandomListNode(node.label));
                node = node.next;
            }

            // loop 2. assign next and random pointers
            node = head;
            while (node != null) {
                map.get(node).next = map.get(node.next);
                map.get(node).random = map.get(node.random);
                node = node.next;
            }

            return map.get(head);
        }
    }


//    In the loop2. assign next and random pointers.
//    We should check whether iter.next or iter.random is null or not!

    public class Solution3 {
        public RandomListNode copyRandomList(RandomListNode head) {
            if(head==null){
                return null;
            }

            Hashtable<RandomListNode,RandomListNode> ht= new Hashtable<RandomListNode,RandomListNode>();

            RandomListNode iter=head;
            while(iter!=null){
                ht.put(iter,new RandomListNode(iter.label));
                iter=iter.next;
            }

            iter=head;
            while(iter!=null){
                if(iter.next!=null){
                    ht.get(iter).next=ht.get(iter.next);
                }
                if(iter.random!=null){
                    ht.get(iter).random=ht.get(iter.random);
                }
                iter=iter.next;
            }

            return ht.get(head);
        }
    }



/*    My accepted Java code. O(n) but need to iterate the list 3 times

42
    S sharon2
    Reputation:  47
    The idea is:
    Step 1: create a new node for each existing node and join them together
    eg: A->B->C will be A->A'->B->B'->C->C'

    Step2: copy the random links: for each new node n', n'.random = n.random.next

    Step3: detach the list: basically n.next = n.next.next; n'.next = n'.next.next

    Here is the code:*/

    /**
     * Definition for singly-linked list with a random pointer.
     * class RandomListNode {
     *     int label;
     *     RandomListNode next, random;
     *     RandomListNode(int x) { this.label = x; }
     * };
     */
    public class Solution4 {
        public RandomListNode copyRandomList(RandomListNode head) {
            if(head==null){
                return null;
            }
            RandomListNode n = head;
            while (n!=null){
                RandomListNode n2 = new RandomListNode(n.label);
                RandomListNode tmp = n.next;
                n.next = n2;
                n2.next = tmp;
                n = tmp;
            }

            n=head;
            while(n != null){
                RandomListNode n2 = n.next;
                if(n.random != null)
                    n2.random = n.random.next;
                else
                    n2.random = null;
                n = n.next.next;
            }

            //detach list
            RandomListNode n2 = head.next;
            n = head;
            RandomListNode head2 = head.next;
            while(n2 != null && n != null){
                n.next = n.next.next;
                if (n2.next == null){
                    break;
                }
                n2.next = n2.next.next;

                n2 = n2.next;
                n = n.next;
            }
            return head2;

        }
    }


//    Using a HashMap and there are n recursive calls.

    public class Solution5 {
        public RandomListNode copyRandomList(RandomListNode head) {
            HashMap<RandomListNode, RandomListNode> nodemap = new HashMap<RandomListNode, RandomListNode>();
            return copyListHelper(head, nodemap);
        }

        public RandomListNode copyListHelper(RandomListNode head, HashMap<RandomListNode, RandomListNode> nodemap) {
            if (head == null) return null;
            if (nodemap.containsKey(head)) return nodemap.get(head);
            RandomListNode ret = new RandomListNode(head.label);
            nodemap.put(head, ret);
            ret.next = copyListHelper(head.next, nodemap);
            ret.random = copyListHelper(head.random, nodemap);
            return ret;
        }
    }


//    same idea but with concise hashmap code.

    public RandomListNode copyRandomList6(RandomListNode head) {
        //<original, clone>
        HashMap<RandomListNode, RandomListNode> maps = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode cursor = head;
        while(null != cursor){
            maps.put(cursor, new RandomListNode(cursor.label));
            cursor = cursor.next;
        }
        cursor = head;
        while(null != cursor){
            maps.get(cursor).random = maps.get(cursor.random);
            cursor = cursor.next;
        }
        cursor = head;
        while(null != cursor){
            maps.get(cursor).next = maps.get(cursor.next);
            cursor = cursor.next;
        }
        return maps.get(head);
    }


//    What @flyinghx61 said is true. It can be done in two iterations.

    public RandomListNode copyRandomList7(RandomListNode head) {
        if (head == null) return null;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode p = head;
        while (p != null) {
            map.put(p, new RandomListNode(p.label));
            p = p.next;
        }

        p = head;
        while (p!= null) {
            map.get(p).random = map.get(p.random);
            map.get(p).next = map.get(p.next);
            p = p.next;
        }
        return map.get(head);
    }


//    Using hashmap is surely simple, but it's inefficient to use an Object as key. I vote for the one without hashmap which runs much faster when testing. Here's my more concise and easy-to-understand Java code.

    public RandomListNode copyRandomList8(RandomListNode head) {
        if (head == null) return null;
        RandomListNode p = head;
        while (p != null) {
            RandomListNode pClone = new RandomListNode(p.label);
            pClone.next = p.next;
            p.next = pClone;
            p = pClone.next;
        }

        p = head;
        while (p != null) {
            p.next.random = p.random == null ? null : p.random.next;
            p = p.next.next;
        }

        RandomListNode headClone = head.next;
        p = head;
        while (p != null) {
            RandomListNode pClone = p.next;
            p.next = pClone.next;
            pClone.next = pClone.next == null ? null : pClone.next.next;
            p = p.next;
        }
        return headClone;
    }


/*    Very short JAVA solution with Map

    I realized with Map, we don't really need to care about the internal structure of the list. What we need is just deep copy exactly what the original data. So here I just create all nodes and put <old, new> pairs into a map. Then update next and random pointers for each new node.*/

    public class Solution9 {
        public RandomListNode copyRandomList(RandomListNode head) {
            if (head == null) {
                return null;
            }

            final Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

            RandomListNode cur = head;
            while(cur != null) {
                map.put(cur, new RandomListNode(cur.label));
                cur = cur.next;
            }

            for (Map.Entry<RandomListNode, RandomListNode> entry : map.entrySet()) {
                final RandomListNode newNode = entry.getValue();
                newNode.next = map.get(entry.getKey().next);
                newNode.random = map.get(entry.getKey().random);
            }

            return map.get(head);
        }
    }
//-------------------------------------------------------------------------//


//-------------------------------------------------------------------------//


//-------------------------------------------------------------------------//


//-------------------------------------------------------------------------//
//jiuzhang
//HashMap version
public class Jiuzhang1 {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
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

    /*第一遍扫的时候巧妙运用next指针， 开始数组是1->2->3->4  。 然后扫描过程中 先建立copy节点 1->1`->2->2`->3->3`->4->4`, 然后第二遍copy的时候去建立边的copy， 拆分节点, 一边扫描一边拆成两个链表，这里用到两个dummy node。第一个链表变回  1->2->3 , 然后第二变成 1`->2`->3`  */
//No HashMap version
    public class Jiuzhang2 {
        private void copyNext(RandomListNode head) {
            while (head != null) {
                RandomListNode newNode = new RandomListNode(head.label);
                newNode.random = head.random;
                newNode.next = head.next;
                head.next = newNode;
                head = head.next.next;
            }
        }

        private void copyRandom(RandomListNode head) {
            while (head != null) {
                if (head.next.random != null) {
                    head.next.random = head.random.next;
                }
                head = head.next.next;
            }
        }

        private RandomListNode splitList(RandomListNode head) {
            RandomListNode newHead = head.next;
            while (head != null) {
                RandomListNode temp = head.next;
                head.next = temp.next;
                head = head.next;
                if (temp.next != null) {
                    temp.next = temp.next.next;
                }
            }
            return newHead;
        }

        public RandomListNode copyRandomList(RandomListNode head) {
            if (head == null) {
                return null;
            }
            copyNext(head);
            copyRandom(head);
            return splitList(head);
        }
    }
    class RandomListNode {
        int label;
        RandomListNode next;
        RandomListNode random;

        RandomListNode(int lavel) {
            this.label = label;
        }
    }
//-------------------------------------------------------------------------//

}

/*
给出一个链表，每个节点包含一个额外增加的随机指针可以指向链表中的任何节点或空的节点。

返回一个深拷贝的链表。
 */

/*
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
 */