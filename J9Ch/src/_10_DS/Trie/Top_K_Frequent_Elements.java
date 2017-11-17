package _10_DS.Trie;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Top_K_Frequent_Elements {

    // leet 347. Top K Frequent Elements

//    这种问题一般有两种做法，一是建立一个wrapper class统计频率,然后用heap得到topK.
//    二是bucket sort.bucket的index就是频率，里面放的是对应的值。
//    PQ的做法

    //Given [1,1,1,2,2,3] and k = 2, return [1,2].

    // error-prone Comparable<Node>
    class Node implements Comparable<Node> {
        int key;
        int freq;
        public Node(int key, int freq) {
            this.key = key;
            this.freq = freq;
        }

        public int compareTo(Node a) {
            return a.freq - this.freq;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>(k);
        PriorityQueue<Node> maxHeap = new PriorityQueue<>();
        HashMap<Integer, Node> map = new HashMap<>();

        for(int num : nums) {
            if(!map.containsKey(num)) {
                map.put(num, new Node(num, 1));
            } else {
                Node t = map.get(num);
                t.freq++;
                map.put(num, t);
            }
        }

        for(Integer key : map.keySet()) {
            maxHeap.offer(map.get(key));
        }

        for(int i = 0; i < k; i++) {
            result.add(maxHeap.poll().key);
        }

        return result;
    }

    @Test
    public void test01(){
        int[] nums = {1,1,1,2,2,3};
        List<Integer>  result = topKFrequent(nums,2);
        System.out.println(result);
    }

////////////////////////////////////////////////////////////////////

    // Java O(n) Solution - Bucket Sort
    // Idea is simple.
    // Build a array of list to be buckets with length 1 to sort.

    public List<Integer> topKFrequent2(int[] nums, int k) {

        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();

        //倒序，所以frequency越高越先出来
        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }

    @Test
    public void test02(){
        int[] nums = {1,1,1,2,2,3};
        List<Integer>  result = topKFrequent(nums,2);
        System.out.println(result);
    }

////////////////////////////////////////////////////////////////////
    //O(n) 32ms Java Solution - Bucket Sort

    public List<Integer> topKFrequent22(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> h = new HashMap();
        for (int i : nums)
            if (h.containsKey(i))
                h.put(i, h.get(i) + 1);
            else
                h.put(i, 1);

        List<Integer>[] fc = new ArrayList[n + 1];
        for (int i : h.keySet()) {
            int f = h.get(i);       //System.out.println(f + " times of " + i);
            if (fc[f] == null) fc[f] = new ArrayList();
            fc[f].add(i);
        }

        List<Integer> ans = new ArrayList();
        for (int i = n, j = 0; k > 0; k--) {
            for (; fc[i] == null || j == fc[i].size(); j = 0, i--);
            ans.add(fc[i].get(j++));
        }

        return ans;
    }
////////////////////////////////////////////////////////////////////

//    3 Java Solution using Array, MaxHeap, TreeMap

    // use an array to save numbers into different
    // bucket whose index is the frequency
    public List<Integer> topKFrequent3(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }

        // corner case: if there is only one number in nums,
        // we need the bucket has index 1.
        List<Integer>[] bucket = new List[nums.length+1];
        for(int n:map.keySet()){
            int freq = map.get(n);
            if(bucket[freq]==null)
                bucket[freq] = new LinkedList<>();
            bucket[freq].add(n);
        }

        List<Integer> res = new LinkedList<>();
        for(int i=bucket.length-1; i>0 && k>0; --i){
            if(bucket[i]!=null){
                List<Integer> list = bucket[i];
                res.addAll(list);
                k-= list.size();
            }
        }

        return res;
    }




    // use maxHeap. Put entry into maxHeap so we can always
    // poll a number with largest frequency
    public List<Integer> topKFrequent4(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));
        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            maxHeap.add(entry);
        }

        List<Integer> res = new ArrayList<>();
        while(res.size()<k){
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            res.add(entry.getKey());
        }
        return res;
    }
    public class Solution {

    }



    // use treeMap. Use freqncy as the key so we can get all freqencies in order
    public List<Integer> topKFrequent5(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }

        TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>();
        for(int num : map.keySet()){
            int freq = map.get(num);
            if(!freqMap.containsKey(freq)){
                freqMap.put(freq, new LinkedList<>());
            }
            freqMap.get(freq).add(num);
        }

        List<Integer> res = new ArrayList<>();
        while(res.size()<k){
            Map.Entry<Integer, List<Integer>> entry = freqMap.pollLastEntry();
            res.addAll(entry.getValue());
        }
        return res;
    }



////////////////////////////////////////////////////////////////////

    //*Java* straightforward O(N + (N-k)lg k) solution

    /*
    Idea is very straightforward:

    build a counter map that maps a num to its frequency
    build a heap/priority queue that keeps track of k most significant entries
    iterate through the final heap and get the keys
     */
    public List<Integer> topKFrequent6(int[] nums, int k) {
        Map<Integer, Integer> counterMap = new HashMap<>();
        for(int num : nums) {
            int count = counterMap.getOrDefault(num, 0);
            counterMap.put(num, count+1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq =
                new PriorityQueue<>((a, b) -> a.getValue()-b.getValue());

        for(Map.Entry<Integer, Integer> entry : counterMap.entrySet()) {
            pq.offer(entry);
            if(pq.size() > k) pq.poll();
        }

        List<Integer> res = new LinkedList<>();
        while(!pq.isEmpty()) {
            res.add(0, pq.poll().getKey());
        }
        return res;
    }
////////////////////////////////////////////////////////////////////
    //Java8 functional solution
    public static List<Integer> topKFrequent7(int[] nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.putIfAbsent(num, 0);
            counter.computeIfPresent(num, (key, oldVal) -> oldVal + 1);
        }
        return counter.entrySet()
            .stream()
            .sorted(
                    Comparator.comparing(
                            Map.Entry<Integer, Integer>::getValue).
                            reversed())

            .limit(k)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }


////////////////////////////////////////////////////////////////////
//O(n) Solution works for stream dynamic Top K query as well
    public List<Integer> topKFrequent8(int[] nums, int k) {
        Map<Integer, Integer> numToCount = new HashMap<>();
        Map<Integer, Integer> numToIndex = new HashMap<>();
        Map<Integer, Integer> countToRank = new HashMap<>();
        List<Integer> arr = new ArrayList<>(); //sorted
        for (int num : nums) {
            if (numToCount.containsKey(num)) {
                int count = numToCount.get(num);
                numToCount.put(num, count + 1);
                int j = numToIndex.get(num);
                int i = countToRank.get(count);
                swap(arr, i, j);
                numToIndex.put(arr.get(i), i);
                numToIndex.put(arr.get(j), j);
                if (i + 1 < arr.size()
                        && numToCount.get(arr.get(i + 1)) == count)
                    countToRank.put(count, i + 1);
                else countToRank.remove(count);
                if (!countToRank.containsKey(count + 1))
                    countToRank.put(count + 1, i);
            }
            else {
                numToCount.put(num, 1);
                arr.add(num);
                numToIndex.put(num, arr.size() - 1);
                if (!countToRank.containsKey(1))
                    countToRank.put(1, arr.size() - 1);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) res.add(arr.get(i));
        return res;
    }

    private void swap(List<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }




////////////////////////////////////////////////////////////////////

//Java Solution. Use HashMap and PriorityQueue

    // supply a new implementation for Map.Entry Comparator
    class EntryComparator<K, V extends Comparable<V>>
            implements Comparator<Map.Entry<?, V>> {
        public int compare(Map.Entry<?, V> left, Map.Entry<?, V> right) {
            // Call compareTo() on V, which is known to be a Comparable<V>
            return right.getValue().compareTo(left.getValue());
        }
    }

    public List<Integer> topKFrequent9(int[] nums, int k) {
        // Priority Queue's size is k,
        // hence the run time for this case is just O(lgK).
        PriorityQueue<Map.Entry<Integer, Integer>> kFrequent =
                new PriorityQueue<>(k,
                // override Comprator class
                new Comparator<Map.Entry<Integer, Integer>>() {
                    public int compare(Map.Entry<Integer, Integer> left,
                                       Map.Entry<Integer, Integer>right){
                        return right.getValue().compareTo(left.getValue());
                    }
                }
        );
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : nums){
            map.putIfAbsent(i, 0);
            // if key is already in the map, then increase the counter
            map.computeIfPresent(i, (key, oldVal) -> oldVal + 1);
        }
        //use priority queue to find kFrequent
        for(Map.Entry<Integer, Integer> mapEntry : map.entrySet()){
            kFrequent.offer(mapEntry);
        }
        List<Integer> returnList = new ArrayList<>();
        for(int i = 0; i < k; i++){
            // in practice, we need check operation is null or not
            //System.out.println(kFrequent.poll());
            returnList.add(kFrequent.poll().getKey());
        }
        return returnList;
    }

}