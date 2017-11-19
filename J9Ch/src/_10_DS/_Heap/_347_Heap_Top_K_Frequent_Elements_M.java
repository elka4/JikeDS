package _10_DS._Heap;
import java.util.*;
import org.junit.Test;
public class _347_Heap_Top_K_Frequent_Elements_M {

//Java O(n) Solution - Bucket Sort
//Idea is simple. Build a array of list to be buckets with length 1 to sort.

    public List<Integer> topKFrequent(int[] nums, int k) {

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

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }



//-------------------------------------------------------------------------////////

//    3 Java Solution using Array, MaxHeap, TreeMap
    // use an array to save numbers into different bucket whose index is the frequency
    public class Solution2 {
        public List<Integer> topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            for(int n: nums){
                map.put(n, map.getOrDefault(n,0)+1);
            }

            // corner case: if there is only one number in nums, we need the bucket has index 1.
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
    }



    // use maxHeap. Put entry into maxHeap so we can always poll a number with largest frequency
    public class Solution3 {
        public List<Integer> topKFrequent(int[] nums, int k) {
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
    }



    // use treeMap. Use freqncy as the key so we can get all freqencies in order
    public class Solution4 {
        public List<Integer> topKFrequent(int[] nums, int k) {
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
    }
//-------------------------------------------------------------------------////////

/*    *Java* straightforward O(N + (N-k)lg k) solution
    Idea is very straightforward:

    build a counter map that maps a num to its frequency
    build a heap/priority queue that keeps track of k most significant entries
    iterate through the final heap and get the keys
    Code in Java:*/

    public List<Integer> topKFrequent5(int[] nums, int k) {
        Map<Integer, Integer> counterMap = new HashMap<>();
        for(int num : nums) {
            int count = counterMap.getOrDefault(num, 0);
            counterMap.put(num, count+1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue()-b.getValue());
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
//-------------------------------------------------------------------------////////
    //jiuzhang
    public class Jiuzhang {
        public List<Integer> topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
            PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<Map.Entry<Integer, Integer>>(
                    new Comparator<Map.Entry<Integer, Integer>>() {
                        public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
                            return e1.getValue() - e2.getValue();
                        }
                    });
            for (int i = 0; i < nums.length; i++) {
                if (!hashmap.containsKey(nums[i])) {
                    hashmap.put(nums[i], 1);
                } else {
                    hashmap.put(nums[i], hashmap.get(nums[i]) + 1);
                }
            }

            for (Map.Entry<Integer, Integer> entry : hashmap.entrySet()) {
                if (queue.size() < k) {
                    queue.offer(entry);
                } else if (queue.peek().getValue() < entry.getValue()) {
                    queue.poll();
                    queue.offer(entry);
                }
            }

            List<Integer> ans = new ArrayList<Integer>();
            for (Map.Entry<Integer, Integer> entry : queue)
                ans.add(entry.getKey());
            return ans;
        }
    }
}
/*
Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 */