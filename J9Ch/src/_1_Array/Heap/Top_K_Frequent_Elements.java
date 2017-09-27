package _1_Array.Heap;
import java.util.*;

/*
Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 */


// Top K Frequent Elements
public class Top_K_Frequent_Elements {
    //jiuzhang
    public List<Integer> topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> hashmap = new HashMap<Integer, Integer>();

        PriorityQueue<Map.Entry<Integer, Integer>> queue =
                new PriorityQueue<Map.Entry<Integer, Integer>>(
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
/////////////////////////////////////////////////////////////////////////////////////

    //Java Solution 1 - Using HashMap and Heap

    //Time is O(n*log(k)).

    class Pair{
        int num;
        int count;
        public Pair(int num, int count){
            this.num=num;
            this.count=count;
        }
    }
    public List<Integer> topKFrequent1(int[] nums, int k) {
        //count the frequency for each element
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int num: nums){
            if(map.containsKey(num)){
                map.put(num, map.get(num)+1);
            }else{
                map.put(num, 1);
            }
        }

        // create a min heap
        PriorityQueue<Pair> queue = new PriorityQueue<Pair>(new Comparator<Pair>(){
            public int compare(Pair a, Pair b){
                return a.count-b.count;
            }
        });

        //maintain a heap of size k.
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            Pair p = new Pair(entry.getKey(), entry.getValue());
            queue.offer(p);
            if(queue.size()>k){
                queue.poll();
            }
        }

        //get all elements from the heap
        List<Integer> result = new ArrayList<Integer>();
        while(queue.size()>0){
            result.add(queue.poll().num);
        }
        //reverse the order
        Collections.reverse(result);

        return result;
    }

///////////////////////////////////////////////////////////////////////////////////

    //Java Solution 2 - Bucket Sort

    //Time is O(n).

    public List<Integer> topKFrequent2(int[] nums, int k) {
        //count the frequency for each element
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int num: nums){
            if(map.containsKey(num)){
                map.put(num, map.get(num)+1);
            }else{
                map.put(num, 1);
            }
        }

        //get the max frequency
        int max = 0;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            max = Math.max(max, entry.getValue());
        }

        //initialize an array of ArrayList. index is frequency, value is list of numbers
        ArrayList<Integer>[] arr = (ArrayList<Integer>[]) new ArrayList[max+1];
        for(int i=1; i<=max; i++){
            arr[i]=new ArrayList<Integer>();
        }

        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            int count = entry.getValue();
            int number = entry.getKey();
            arr[count].add(number);
        }

        List<Integer> result = new ArrayList<Integer>();

        //add most frequent numbers to result
        for(int j=max; j>=1; j--){
            if(arr[j].size()>0){
                for(int a: arr[j]){
                    result.add(a);
                }
            }

            if(result.size()==k)
                break;
        }

        return result;
    }

///////////////////////////////////////////////////////////////////////////////////

    //Java Solution 3 - A Regular Counter (Deprecated)

    //We can solve this problem by using a regular counter, and then sort the counter by value.
    public List<Integer> topKFrequent3(int[] nums, int k) {
        List<Integer> result = new ArrayList<Integer>();

        HashMap<Integer, Integer> counter = new HashMap<Integer, Integer>();

        for(int i: nums){
            if(counter.containsKey(i)){
                counter.put(i, counter.get(i)+1);
            }else{
                counter.put(i, 1);
            }
        }

        TreeMap<Integer, Integer> sortedMap = new TreeMap<Integer, Integer>(new ValueComparator(counter));
        sortedMap.putAll(counter);

        int i=0;
        for(Map.Entry<Integer, Integer> entry: sortedMap.entrySet()){
            result.add(entry.getKey());
            i++;
            if(i==k)
                break;
        }

        return result;
    }

    class ValueComparator implements Comparator<Integer>{
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        public ValueComparator(HashMap<Integer, Integer> m){
            map.putAll(m);
        }

        public int compare(Integer i1, Integer i2){
            int diff = map.get(i2)-map.get(i1);

            if(diff==0){
                return 1;
            }else{
                return diff;
            }
        }
    }

///////////////////////////////////////////////////////////////////////////////////

}
