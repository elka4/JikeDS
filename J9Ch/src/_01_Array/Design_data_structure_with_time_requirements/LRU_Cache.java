package _01_Array.Design_data_structure_with_time_requirements;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

/* 146. LRU Cache

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?
 */

/*
Example:

LRUCache cache = new LRUCache( 2 / capacity / );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4

 */


public class LRU_Cache {
    class Node{
        int key;
        int value;
        Node pre;
        Node next;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    public class LRUCache {
        int capacity;
        HashMap<Integer, Node> map = new HashMap<Integer, Node>();
        Node head=null;
        Node end=null;

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            if(map.containsKey(key)){
                Node n = map.get(key);
                remove(n);
                setHead(n);
                return n.value;
            }

            return -1;
        }

        public void remove(Node n){
            if(n.pre!=null){
                n.pre.next = n.next;
            }else{
                head = n.next;
            }

            if(n.next!=null){
                n.next.pre = n.pre;
            }else{
                end = n.pre;
            }

        }

        public void setHead(Node n){
            n.next = head;
            n.pre = null;

            if(head!=null)
                head.pre = n;

            head = n;

            if(end ==null)
                end = head;
        }

        public void set(int key, int value) {
            if(map.containsKey(key)){
                Node old = map.get(key);
                old.value = value;
                remove(old);
                setHead(old);
            }else{
                Node created = new Node(key, value);
                if(map.size()>=capacity){
                    map.remove(end.key);
                    remove(end);
                    setHead(created);

                }else{
                    setHead(created);
                }

                map.put(key, created);
            }
        }
    }


//-------------------------------------------------------------------------//////
    //[Java] Hashtable + Double linked list (with a touch of pseudo nodes)

    /*
    The problem can be solved with a hashtable that keeps track of the keys and its values in the double linked list. One interesting property about double linked list is that the node can remove itself without other reference. In addition, it takes constant time to add and remove nodes from the head or tail.

One particularity about the double linked list that I implemented is that I create a pseudo head and tail to mark the boundary, so that we don't need to check the NULL node during the update. This makes the code more concise and clean, and also it is good for the performance as well.

Voila, here is the code.
     */
    class LRU_Cache2{
        class DLinkedNode {
            int key;
            int value;
            DLinkedNode pre;
            DLinkedNode post;
        }

        /**
         * Always add the new node right after head;
         */
        private void addNode(DLinkedNode node){
            node.pre = head;
            node.post = head.post;

            head.post.pre = node;
            head.post = node;
        }

        /**
         * Remove an existing node from the linked list.
         */
        private void removeNode(DLinkedNode node){
            DLinkedNode pre = node.pre;
            DLinkedNode post = node.post;

            pre.post = post;
            post.pre = pre;
        }

        /**
         * Move certain node in between to the head.
         */
        private void moveToHead(DLinkedNode node){
            this.removeNode(node);
            this.addNode(node);
        }

        // pop the current tail.
        private DLinkedNode popTail(){
            DLinkedNode res = tail.pre;
            this.removeNode(res);
            return res;
        }

        private Hashtable<Integer, DLinkedNode>
                cache = new Hashtable<Integer, DLinkedNode>();
        private int count;
        private int capacity;
        private DLinkedNode head, tail;

        public LRU_Cache2(int capacity) {
            this.count = 0;
            this.capacity = capacity;

            head = new DLinkedNode();
            head.pre = null;

            tail = new DLinkedNode();
            tail.post = null;

            head.post = tail;
            tail.pre = head;
        }

        public int get(int key) {

            DLinkedNode node = cache.get(key);
            if(node == null){
                return -1; // should raise exception here.
            }

            // move the accessed node to the head;
            this.moveToHead(node);

            return node.value;
        }


        public void set(int key, int value) {
            DLinkedNode node = cache.get(key);

            if(node == null){

                DLinkedNode newNode = new DLinkedNode();
                newNode.key = key;
                newNode.value = value;

                this.cache.put(key, newNode);
                this.addNode(newNode);

                ++count;

                if(count > capacity){
                    // pop the tail
                    DLinkedNode tail = this.popTail();
                    this.cache.remove(tail.key);
                    --count;
                }
            }else{
                // update the value.
                node.value = value;
                this.moveToHead(node);
            }

        }
    }



//-------------------------------------------------------------------------//////

    //Laziest implementation: Java's LinkedHashMap takes care of everything


    public class LRUCache3 {
        private LinkedHashMap<Integer, Integer> map;
        private final int CAPACITY;

        public LRUCache3(int capacity) {
            CAPACITY = capacity;
            map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true){
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > CAPACITY;
                }
            };
        }

        public int get(int key) {
            return map.getOrDefault(key, -1);
        }

        public void set(int key, int value) {
            map.put(key, value);
        }
    }


//-------------------------------------------------------------------------//////






//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////





}
