package _10_DS.Trie;
import java.util.*;

//  692. Top K Frequent Words
//  https://leetcode.com/problems/top-k-frequent-words/description/
//  Hash Table, Heap, Trie
public class _692_Top_K_Frequent_Words_M {
    //https://leetcode.com/problems/top-k-frequent-words/solution/
    //Count the frequency of each word, and sort the words with a custom ordering relation that uses these frequencies. Then take the best k of them.
    class Solution1 {
        public List<String> topKFrequent(String[] words, int k) {
            Map<String, Integer> count = new HashMap();
            for (String word : words) {
                count.put(word, count.getOrDefault(word, 0) + 1);
            }
            List<String> candidates = new ArrayList(count.keySet());
            Collections.sort(candidates, (w1, w2) -> count.get(w1) != count.get(w2) ?
                    count.get(w2) - count.get(w1) : w1.compareTo(w2));

            return candidates.subList(0, k);
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////

//Approach #2: Heap [Accepted]
    /*
    Count the frequency of each word, then add it to heap that stores the best k candidates. Here, "best" is defined with our custom ordering relation, which puts the worst candidates at the top of the heap. At the end, we pop off the heap up to k times and reverse the result so that the best candidates are first.

In Python, we instead use heapq.heapify, which can turn a list into a heap in linear time, simplifying our work.
     */
class Solution2 {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<String>(
                (w1, w2) -> count.get(w1) != count.get(w2) ?
                        count.get(w1) - count.get(w2) : w2.compareTo(w1) );

        for (String word: count.keySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll();
        }

        List<String> ans = new ArrayList();
        while (!heap.isEmpty()) ans.add(heap.poll());
        Collections.reverse(ans);
        return ans;
    }
}




//////////////////////////////////////////////////////////////////////////////////////////////////
//3
/*My simple Java solution using HashMap & PriorityQueue - O(nlogk) time & O(n) space
    The idea is to keep a count of each word in a HashMap and then insert in a Priority Queue.
    While inserting in pq, if the count of two words is same then insert based on string compare of the keys.*/

    class Solution3 {
        public List<String> topKFrequent(String[] words, int k) {

            List<String> result = new LinkedList<>();
            Map<String, Integer> map = new HashMap<>();
            for(int i=0; i<words.length; i++)
            {
                if(map.containsKey(words[i]))
                    map.put(words[i], map.get(words[i])+1);
                else
                    map.put(words[i], 1);
            }

            PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                    (a,b) -> a.getValue()==b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue()-b.getValue()
            );

            for(Map.Entry<String, Integer> entry: map.entrySet())
            {
                pq.offer(entry);
                if(pq.size()>k)
                    pq.poll();
            }

            while(!pq.isEmpty())
                result.add(0, pq.poll().getKey());

            return result;
        }
    }



//////////////////////////////////////////////////////////////////////////////////////////////////

//4

/*    Java O(n) solution using HashMap, BucketSort and Trie - 22ms Beat 81%
    This problem is quite similar to the problem Top K Frequent Elements. You can refer to this post for the solution of the problem.

    We can solve this problem with the similar idea:
    Firstly, we need to calculate the frequency of each word and store the result in a hashmap.

    Secondly, we will use bucket sort to store words. Why? Because the minimum frequency is greater than or equal to 1 and the maximum frequency is less than or equal to the length of the input string array.

            Thirdly, we can define a trie within each bucket to store all the words with the same frequency. With Trie, it ensures that the lower alphabetical word will be met first, saving the trouble to sort the words within the bucket.

    From the above analysis, we can see the time complexity is O(n).
    Here is my code:*/

    class Solution4{

        public List<String> topKFrequent(String[] words, int k) {
            // calculate frequency of each word
            Map<String, Integer> freqMap = new HashMap<>();
            for(String word : words) {
                freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
            }
            // build the buckets
            TrieNode[] count = new TrieNode[words.length + 1];
            for(String word : freqMap.keySet()) {
                int freq = freqMap.get(word);
                if(count[freq] == null) {
                    count[freq] = new TrieNode();
                }
                addWord(count[freq], word);
            }
            // get k frequent words
            List<String> list = new LinkedList<>();
            for(int f = count.length - 1; f >= 1 && list.size() < k; f--) {
                if(count[f] == null) continue;
                getWords(count[f], list, k);
            }
            return list;
        }

        private void getWords(TrieNode node, List<String> list, int k) {
            if(node == null) return;
            if(node.word != null) {
                list.add(node.word);
            }
            if(list.size() == k) return;
            for(int i = 0; i < 26; i++) {
                if(node.next[i] != null) {
                    getWords(node.next[i], list, k);
                }
            }
        }

        private boolean addWord(TrieNode root, String word) {
            TrieNode curr = root;
            for(char c : word.toCharArray()) {
                if(curr.next[c - 'a'] == null) {
                    curr.next[c - 'a'] = new TrieNode();
                }
                curr = curr.next[c - 'a'];
            }
            curr.word = word;
            return true;
        }

        class TrieNode {
            TrieNode[] next;
            String word;
            TrieNode() {
                this.next = new TrieNode[26];
                this.word = null;
            }
        }

    }


//////////////////////////////////////////////////////////////////////////////////////////////////
//5
//java two HashMap, beat 90%

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> freqmap = new HashMap<String, Integer>();
        Map<Integer, List<String>> groupmap = new HashMap<Integer, List<String>>();
        List<String> res = new ArrayList<String>();
        for(String word : words) freqmap.put(word, freqmap.getOrDefault(word, 0) + 1);
        int maxFreq = 0;
        for(String key : freqmap.keySet()){
            int freq = freqmap.get(key);
            if(freq>maxFreq) maxFreq = freq;
            if(!groupmap.containsKey(freq)) groupmap.put(freq, new ArrayList<String>());
            groupmap.get(freq).add(key);
        }
        for(int i=maxFreq; i>0; i--){
            if(!groupmap.containsKey(i)) continue;
            Collections.sort(groupmap.get(i));
            if(groupmap.get(i).size() + res.size()<=k) res.addAll(groupmap.get(i));
            else res.addAll(groupmap.get(i).subList(0,k-res.size()));
        }
        return res;
    }


//////////////////////////////////////////////////////////////////////////////////////////////////
// 9Ch

    class Pair {
        String key;
        int value;

        Pair(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public class Jiuzhang {
        /**
         * @param words an array of string
         * @param k an integer
         * @return an array of string
         */

        private Comparator<Pair> pairComparator = new Comparator<Pair>() {
            public int compare(Pair left, Pair right) {
                if (left.value != right.value) {
                    return left.value - right.value;
                }
                return right.key.compareTo(left.key);
            }
        };

        public String[] topKFrequentWords(String[] words, int k) {
            if (k == 0) {
                return new String[0];
            }

            HashMap<String, Integer> counter = new HashMap<>();
            for (String word : words) {
                if (counter.containsKey(word)) {
                    counter.put(word, counter.get(word) + 1);
                } else {
                    counter.put(word, 1);
                }
            }

            PriorityQueue<Pair> Q = new PriorityQueue<Pair>(k, pairComparator);
            for (String word : counter.keySet()) {
                Pair peak = Q.peek();
                Pair newPair = new Pair(word, counter.get(word));
                if (Q.size() < k) {
                    Q.add(newPair);
                } else if (pairComparator.compare(newPair, peak) > 0) {
                    Q.poll();
                    Q.add(newPair);
                }
            }

            String[] result = new String[k];
            int index = 0;
            while (!Q.isEmpty()) {
                result[index++] = Q.poll().key;
            }

            // reverse
            for (int i = 0; i < index / 2; i++) {
                String temp = result[i];
                result[i] = result[index - i - 1];
                result[index - i - 1] = temp;
            }

            return result;
        }
    }


//////////////////////////////////////////////////////////////////////////////////////////////////




}
/*

Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.
Follow up:
Try to solve it in O(n log k) time and O(n) extra space.
 */