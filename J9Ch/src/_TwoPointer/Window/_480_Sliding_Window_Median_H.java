package _TwoPointer.Window;
import org.junit.Test;
import java.util.*;
import java.util.function.Supplier;

//  480. Sliding Window Median
//
//  https://leetcode.com/problems/sliding-window-median/description/
//  http://www.lintcode.com/problem/sliding-window-median/
//  295. Find Median from Data Stream - Heap, Design
//
//  给定一个包含 n 个整数的数组，和一个大小为 k 的滑动窗口,从左到右在数组中滑动这个窗口，
//  找到数组中每个窗口内的中位数。(如果数组个数是偶数，则在该窗口排序数字后，返回第 N/2 个数字。)
//
//
//
public class
_480_Sliding_Window_Median_H {
//------------------------------------------------------------------------------
    //https://leetcode.com/problems/sliding-window-median/solution/
    //C++
//------------------------------------------------------------------------------
    //1
    //Easy to understand O(nlogk) Java solution using TreeMap
    /*
    TreeMap is used to implement an ordered MultiSet.

    In this problem, I use two Ordered MultiSets as Heaps. One heap maintains the lowest 1/2 of the elements, and the other heap maintains the higher 1/2 of elements.

    This implementation is faster than the usual implementation that uses 2 PriorityQueues, because unlike PriorityQueue, TreeMap can remove arbitrary element in logarithmic time.
     */
    public class Solution1 {
        public double[] medianSlidingWindow(int[] nums, int k) {
            double[] res = new double[nums.length-k+1];
            TreeMap<Integer, Integer> minHeap = new TreeMap<Integer, Integer>();
            TreeMap<Integer, Integer> maxHeap = new TreeMap<Integer, Integer>(Collections.reverseOrder());

            int minHeapCap = k/2; //smaller heap when k is odd.
            int maxHeapCap = k - minHeapCap;

            for(int i=0; i< k; i++){
                maxHeap.put(nums[i], maxHeap.getOrDefault(nums[i], 0) + 1);
            }
            int[] minHeapSize = new int[]{0};
            int[] maxHeapSize = new int[]{k};
            for(int i=0; i< minHeapCap; i++){
                move1Over(maxHeap, minHeap, maxHeapSize, minHeapSize);
            }

            res[0] = getMedian(maxHeap, minHeap, maxHeapSize, minHeapSize);
            int resIdx = 1;

            for(int i=0; i< nums.length-k; i++){
                int addee = nums[i+k];
                if(addee <= maxHeap.keySet().iterator().next()){
                    add(addee, maxHeap, maxHeapSize);
                } else {
                    add(addee, minHeap, minHeapSize);
                }

                int removee = nums[i];
                if(removee <= maxHeap.keySet().iterator().next()){
                    remove(removee, maxHeap, maxHeapSize);
                } else {
                    remove(removee, minHeap, minHeapSize);
                }

                //rebalance
                if(minHeapSize[0] > minHeapCap){
                    move1Over(minHeap, maxHeap, minHeapSize, maxHeapSize);
                } else if(minHeapSize[0] < minHeapCap){
                    move1Over(maxHeap, minHeap, maxHeapSize, minHeapSize);
                }

                res[resIdx] = getMedian(maxHeap, minHeap, maxHeapSize, minHeapSize);
                resIdx++;
            }
            return res;
        }

        public double getMedian(TreeMap<Integer, Integer> bigHeap, TreeMap<Integer, Integer> smallHeap, int[] bigHeapSize, int[] smallHeapSize){
            return bigHeapSize[0] > smallHeapSize[0] ? (double) bigHeap.keySet().iterator().next() : ((double) bigHeap.keySet().iterator().next() + (double) smallHeap.keySet().iterator().next()) / 2.0;
        }

        //move the top element of heap1 to heap2
        public void move1Over(TreeMap<Integer, Integer> heap1, TreeMap<Integer, Integer> heap2, int[] heap1Size, int[] heap2Size){
            int peek = heap1.keySet().iterator().next();
            add(peek, heap2, heap2Size);
            remove(peek, heap1, heap1Size);
        }

        public void add(int val, TreeMap<Integer, Integer> heap, int[] heapSize){
            heap.put(val, heap.getOrDefault(val,0) + 1);
            heapSize[0]++;
        }

        public void remove(int val, TreeMap<Integer, Integer> heap, int[] heapSize){
            if(heap.put(val, heap.get(val) - 1) == 1) heap.remove(val);
            heapSize[0]--;
        }
    }



//------------------------------------------------------------------------------
    //2
    //Java solution using two PriorityQueues

    /*
    Almost the same idea of Find Median from Data Stream https://leetcode.com/problems/find-median-from-data-stream/

    1. Use two Heaps to store numbers. maxHeap for numbers smaller than current median, minHeap for numbers bigger than and equal to current median. A small trick I used is always make size of minHeap equal (when there are even numbers) or 1 element more (when there are odd numbers) than the size of maxHeap. Then it will become very easy to calculate current median.

    2. Keep adding number from the right side of the sliding window and remove number from left side of the sliding window. And keep adding current median to the result.

     */

    public class Solution2 {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(
                new Comparator<Integer>() {
                    public int compare(Integer i1, Integer i2) {
                        return i2.compareTo(i1);
                    }
                }
        );

        public double[] medianSlidingWindow(int[] nums, int k) {
            int n = nums.length - k + 1;
            if (n <= 0) return new double[0];
            double[] result = new double[n];

            for (int i = 0; i <= nums.length; i++) {
                if (i >= k) {
                    result[i - k] = getMedian();
                    remove(nums[i - k]);
                }
                if (i < nums.length) {
                    add(nums[i]);
                }
            }

            return result;
        }

        private void add(int num) {
            if (num < getMedian()) {
                maxHeap.add(num);
            }
            else {
                minHeap.add(num);
            }
            if (maxHeap.size() > minHeap.size()) {
                minHeap.add(maxHeap.poll());
            }
            if (minHeap.size() - maxHeap.size() > 1) {
                maxHeap.add(minHeap.poll());
            }
        }

        private void remove(int num) {
            if (num < getMedian()) {
                maxHeap.remove(num);
            }
            else {
                minHeap.remove(num);
            }
            if (maxHeap.size() > minHeap.size()) {
                minHeap.add(maxHeap.poll());
            }
            if (minHeap.size() - maxHeap.size() > 1) {
                maxHeap.add(minHeap.poll());
            }
        }

        private double getMedian() {
            if (maxHeap.isEmpty() && minHeap.isEmpty()) return 0;

            if (maxHeap.size() == minHeap.size()) {
                return ((double)maxHeap.peek() + (double)minHeap.peek()) / 2.0;
            }
            else {
                return (double)minHeap.peek();
            }
        }
    }
//------------------------------------------------------------------------------
    //3
    //Java using two Tree Sets - O(n logk)
    /*
    Inspired by this solution. to the problem: 295. Find Median from Data Stream

    However instead of using two priority queue's we use two Tree Sets as we want O(logk) for remove(element). Priority Queue would have been O(k) for remove(element) giving us an overall time complexity of O(nk) instead of O(nlogk).

    However there is an issue when it comes to duplicate values so to get around this we store the index into nums in our Tree Set. To break ties in our Tree Set comparator we compare the index.
     */
    class Solution3{
        public double[] medianSlidingWindow(int[] nums, int k) {
            Comparator<Integer> comparator = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;
            TreeSet<Integer> left = new TreeSet<>(comparator.reversed());
            TreeSet<Integer> right = new TreeSet<>(comparator);

            Supplier<Double> median = (k % 2 == 0) ?
                    () -> ((double) nums[left.first()] + nums[right.first()]) / 2 :
                    () -> (double) nums[right.first()];

            // balance lefts size and rights size (if not equal then right will be larger by one)
            Runnable balance = () -> { while (left.size() > right.size()) right.add(left.pollFirst()); };

            double[] result = new double[nums.length - k + 1];

            for (int i = 0; i < k; i++) left.add(i);
            balance.run(); result[0] = median.get();

            for (int i = k, r = 1; i < nums.length; i++, r++) {
                // remove tail of window from either left or right
                if(!left.remove(i - k)) right.remove(i - k);

                // add next num, this will always increase left size
                right.add(i); left.add(right.pollFirst());

                // rebalance left and right, then get median from them
                balance.run(); result[r] = median.get();
            }

            return result;
        }
    }


//------------------------------------------------------------------------------
    //4
    //Great idea! I re-write it using another way:
    class Solution4 {
        class myInteger{
            int val;
            int index;
            myInteger(int val,int index){
                this.val = val;
                this.index = index;
            }
        }
        public double[] medianSlidingWindow(int[] nums, int k) {
            TreeSet<myInteger> minheap = new TreeSet<>(new Comparator<myInteger>(){
                public int compare(myInteger a,myInteger b){
                    if(a.val!=b.val){
                        if(a.val<b.val){
                            return -1;
                        }else{
                            return 1;
                        }
                    }else{
                        return a.index-b.index;
                    }
                }
            });
            TreeSet<myInteger> maxheap = new TreeSet<>(new Comparator<myInteger>(){
                public int compare(myInteger a,myInteger b){
                    if(a.val!=b.val){
                        if(a.val<b.val){
                            return -1;
                        }else{
                            return 1;
                        }
                    }else{
                        return a.index - b.index;
                    }
                }
            });

            Deque<myInteger> deque  = new ArrayDeque<>();
            double[] res = new double[nums.length-k+1];
            for(int i=0;i<k;i++){
                myInteger temp = new myInteger(nums[i],i);
                deque.offer(temp);
                maxheap.add(temp);
            }
            balance(minheap,maxheap);
            //System.out.println("size of minheap :" + minheap.size() + " and the size of maxheap is : " + maxheap.size());

            res[0] = getmedian(minheap,maxheap);
            int p=1;
            for(int i=k;i<nums.length;i++){
                myInteger removeEle = deque.pollFirst();
                if(minheap.contains(removeEle)){
                    minheap.remove(removeEle);
                }else{
                    maxheap.remove(removeEle);
                }
                myInteger newEle = new myInteger(nums[i],i);
                deque.offer(newEle);
                maxheap.add(newEle);
                minheap.add(maxheap.pollLast());
                balance(minheap,maxheap);
                res[p++] = getmedian(minheap,maxheap);
            }
            return res;

        }

        public double getmedian(TreeSet<myInteger> minHeap,TreeSet<myInteger> maxHeap){
            if(minHeap.size()>maxHeap.size()){
                return (double)minHeap.first().val;
            }
            return ((double)minHeap.first().val+(double)maxHeap.last().val)/2.0;
        }


        public void balance(TreeSet<myInteger> minHeap,TreeSet<myInteger> maxHeap){
            while(maxHeap.size()>minHeap.size()){
                minHeap.add(maxHeap.pollLast());
            }
            while(maxHeap.size()<minHeap.size()-1){
                maxHeap.add(minHeap.pollFirst());
            }
        }
    }

//------------------------------------------------------------------------------

//------------------------------------------------------------------------------

//------------------------------------------------------------------------------

//------------------------------------------------------------------------------


    //5
    // 9Ch 1
    public class Ch9_1 {
        /**
         * @param nums
         *            : A list of integers.
         * @return: The median of the element inside the window at each moving.
         */
        public  ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            TreeSet<Node> minheap = new TreeSet<Node>();
            TreeSet<Node> maxheap = new TreeSet<Node>();
            ArrayList<Integer> result = new ArrayList<Integer> ();

            if (k == 0)
                return result;

            int half = (k+1)/2;
            for(int i=0; i<k-1; i++) {
                add(minheap, maxheap, half, new Node(i, nums[i]));
            }
            for(int i=k-1; i<n; i++) {
                add(minheap, maxheap, half, new Node(i, nums[i]));
                result.add(minheap.last().val);
                remove(minheap,maxheap, new Node(i-k+1, nums[i-k+1]));
            }
            return result;
        }

        void add(TreeSet<Node>minheap, TreeSet<Node> maxheap, int size, Node node) {
            if (minheap.size()<size) {
                minheap.add(node);
            }
            else {
                maxheap.add(node);
            }
            if (minheap.size()==size) {
                if (maxheap.size()>0 && minheap.last().val>maxheap.first().val) {
                    Node s = minheap.last();
                    Node b = maxheap.first();
                    minheap.remove(s);
                    maxheap.remove(b);
                    minheap.add(b);
                    maxheap.add(s);
                }
            }
        }

        void remove(TreeSet<Node>minheap, TreeSet<Node> maxheap, Node node) {
            if (minheap.contains(node)) {
                minheap.remove(node);
            }
            else {
                maxheap.remove(node);
            }
        }
    }

    class Node implements Comparable<Node>{
        int id;
        int val;
        Node(int id, int val) {
            this.id = id;
            this.val = val;
        }
        public int compareTo(Node other) {
            Node a =(Node)other;
            if (this.val == a.val) {
                return this.id - a.id;
            }
            return this.val - a.val;
        }
    }

//------------------------------------------------------------------------------
    //6
    // 9Ch 2
    // Normal heap Version
    public class Ch9_2 {
        /**
         * @param nums: A list of integers.
         * @return: The median of the element inside the window at each moving.
         */
        public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
            // write your code here
            ArrayList<Integer> result = new ArrayList<Integer>();
            int size = nums.length;
            if (size == 0 || size < k) {
                return result;
            }

            PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>();
            PriorityQueue<Integer> maxPQ = new PriorityQueue<Integer>(11, Collections.reverseOrder());

            int median = nums[0];
            int j = 0;
            if (k == 1) {
                result.add(median);
            }

            for (int i = 1; i < size; i++) {
                if (nums[i] > median) {
                    minPQ.offer(nums[i]);
                } else {
                    maxPQ.offer(nums[i]);
                }

                if (i > k - 1) {
                    if (nums[j] > median) {
                        minPQ.remove(nums[j]);
                    } else if (nums[j] < median) {
                        maxPQ.remove(nums[j]);
                    } else {
                        median = Integer.MIN_VALUE;
                    }
                    j++;
                }

                if (median == Integer.MIN_VALUE) {
                    median = minPQ.size() > maxPQ.size() ? minPQ.poll() : maxPQ.poll();
                } else {
                    while (minPQ.size() >= maxPQ.size() + 2) {
                        maxPQ.offer(median);
                        median = minPQ.poll();
                    }
                    while (maxPQ.size() >= minPQ.size() + 1) {
                        minPQ.offer(median);
                        median = maxPQ.poll();
                    }
                }
                if (i >= k - 1) {
                    result.add(median);
                }
            }

            return result;
        }
    }
//------------------------------------------------------------------------------
    //7
    // 9CH HashHeap
    // Hash Heap Version
    class HashHeap {
        ArrayList<Integer> heap;
        String mode;
        int size_t;
        HashMap<Integer, Node> hash;

        class Node {
            public Integer id;
            public Integer num;

            Node(Node now) {
                id = now.id;
                num = now.num;
            }

            Node(Integer first, Integer second) {
                this.id = first;
                this.num = second;
            }
        }

        public HashHeap(String mod) {
            // TODO Auto-generated constructor stub
            heap = new ArrayList<Integer>();
            mode = mod;
            hash = new HashMap<Integer, Node>();
            size_t = 0;
        }

        int peak() {
            return heap.get(0);
        }

        int size() {
            return size_t;
        }

        Boolean empty() {
            return (heap.size() == 0);
        }

        int parent(int id) {
            if (id == 0) {
                return -1;
            }
            return (id - 1) / 2;
        }

        int lson(int id) {
            return id * 2 + 1;
        }

        int rson(int id) {
            return id * 2 + 2;
        }

        boolean comparesmall(int a, int b) {
            if (a <= b) {
                if (mode == "min")
                    return true;
                else
                    return false;
            } else {
                if (mode == "min")
                    return false;
                else
                    return true;
            }

        }

        void swap(int idA, int idB) {
            int valA = heap.get(idA);
            int valB = heap.get(idB);

            int numA = hash.get(valA).num;
            int numB = hash.get(valB).num;
            hash.put(valB, new Node(idA, numB));
            hash.put(valA, new Node(idB, numA));
            heap.set(idA, valB);
            heap.set(idB, valA);
        }

        Integer poll() {
            size_t--;
            Integer now = heap.get(0);
            Node hashnow = hash.get(now);
            if (hashnow.num == 1) {
                swap(0, heap.size() - 1);
                hash.remove(now);
                heap.remove(heap.size() - 1);
                if (heap.size() > 0) {
                    siftdown(0);
                }
            } else {
                hash.put(now, new Node(0, hashnow.num - 1));
            }
            return now;
        }

        void add(int now) {
            size_t++;
            if (hash.containsKey(now)) {
                Node hashnow = hash.get(now);
                hash.put(now, new Node(hashnow.id, hashnow.num + 1));

            } else {
                heap.add(now);
                hash.put(now, new Node(heap.size() - 1, 1));
            }

            siftup(heap.size() - 1);
        }

        void delete(int now) {
            size_t--;
            ;
            Node hashnow = hash.get(now);
            int id = hashnow.id;
            int num = hashnow.num;
            if (hashnow.num == 1) {

                swap(id, heap.size() - 1);
                hash.remove(now);
                heap.remove(heap.size() - 1);
                if (heap.size() > id) {
                    siftup(id);
                    siftdown(id);
                }
            } else {
                hash.put(now, new Node(id, num - 1));
            }
        }

        void siftup(int id) {
            while (parent(id) > -1) {
                int parentId = parent(id);
                if (comparesmall(heap.get(parentId), heap.get(id)) == true) {
                    break;
                } else {
                    swap(id, parentId);
                }
                id = parentId;
            }
        }

        void siftdown(int id) {
            while (lson(id) < heap.size()) {
                int leftId = lson(id);
                int rightId = rson(id);
                int son;
                if (rightId >= heap.size() || (comparesmall(heap.get(leftId), heap.get(rightId)) == true)) {
                    son = leftId;
                } else {
                    son = rightId;
                }
                if (comparesmall(heap.get(id), heap.get(son)) == true) {
                    break;
                } else {
                    swap(id, son);
                }
                id = son;
            }
        }
    }

//------------------------------------------------------------------------------
    //8
    // 9Ch 3
    public class Ch9_3 {
        /**
         * @param nums
         *            : A list of integers.
         * @return: The median of the element inside the window at each moving.
         */
        public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
            // write your code here

            ArrayList<Integer> ans = new ArrayList<Integer>();
            if (nums.length == 0)
                return ans;
            int median = nums[0];
            HashHeap minheap = new HashHeap("min");
            HashHeap maxheap = new HashHeap("max");
            for (int i = 0; i < nums.length; i++) {
                if (i != 0) {
                    if (nums[i] > median) {
                        minheap.add(nums[i]);
                    } else {
                        maxheap.add(nums[i]);
                    }
                }

                if (i >= k) {
                    if (median == nums[i - k]) {
                        if (maxheap.size() > 0) {
                            median = maxheap.poll();
                        } else if (minheap.size() > 0) {
                            median = minheap.poll();
                        }

                    } else if (median < nums[i - k]) {
                        minheap.delete(nums[i - k]);
                    } else {
                        maxheap.delete(nums[i - k]);
                    }
                }

                while (maxheap.size() > minheap.size()) {
                    minheap.add(median);
                    median = maxheap.poll();
                }
                while (minheap.size() > maxheap.size() + 1) {
                    maxheap.add(median);
                    median = minheap.poll();
                }

                if (i + 1 >= k) {
                    ans.add(median);
                }
            }
            return ans;
        }
    }


//------------------------------------------------------------------------------



//------------------------------------------------------------------------------
}
/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples:
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.
------------------------------------------------------------------------------
For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].
------------------------------------------------------------------------------
Note:
You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.

Companies
Google

Similar Questions
295. Find Median from Data Stream - Heap, Design
------------------------------------------------------------------------------ */