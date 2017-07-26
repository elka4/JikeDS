package _1Linear_Adv;
import org.junit.Test;
import java.util.*;

//基本上就是要先走一遍统计次数
public class _6TopKFrequentElements {
    //Java O(n) Solution - Bucket Sort
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

////////////////////////////////////////////////////////////////////////////////////////

//3 Java Solution using Array, MaxHeap, TreeMap

    // use an array to save numbers into different
    // bucket whose index is the frequency
    public List<Integer> topKFrequent2(int[] nums, int k) {
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

/////////////////////////////////////////////////////////////////////////


    // use maxHeap. Put entry into maxHeap so we can always
    // poll a number with largest frequency
    public List<Integer> topKFrequent3(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }
        //value是frequency
        //要学习这种用lambda表示comparator的做法
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

/////////////////////////////////////////////////////////////////////////


    // use treeMap. Use freqncy as the key so we can get all freqencies in order
    public List<Integer> topKFrequent4(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }

        TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>();

        //treemap key位置上放freq
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

    /*
    The pollLastEntry() method is used to remove and returns a key-value mapping
    associated with the greatest key in this map, or null if the map is empty.
     */

    @Test
    public void test01(){
        // creating tree map
        TreeMap<Integer, String> treemap = new TreeMap<Integer, String>();

        // populating tree map
        treemap.put(2, "two");
        treemap.put(1, "one");
        treemap.put(3, "three");
        treemap.put(6, "six");
        treemap.put(5, "five");

        // polling last entry
        System.out.println("Value before poll: "+ treemap);
        System.out.println("Value returned: "+ treemap.pollLastEntry());
        System.out.println("Value after poll: "+ treemap);
    }



/////////////////////////////////////////////////////////////////////////

//*Java* straightforward O(N + (N-k)lg k) solution
/*
build a counter map that maps a num to its frequency
build a heap/priority queue that keeps track of k most significant entries
iterate through the final heap and get the keys
 */

    public List<Integer> topKFrequent5(int[] nums, int k) {
        Map<Integer, Integer> counterMap = new HashMap<>();
        for(int num : nums) {
            int count = counterMap.getOrDefault(num, 0);
            counterMap.put(num, count+1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue()-b.getValue());

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

////////////////////////////////////////////




}
