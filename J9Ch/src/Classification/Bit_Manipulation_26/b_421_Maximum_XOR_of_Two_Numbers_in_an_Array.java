package Classification.Bit_Manipulation_26;
import java.util.*;

public class b_421_Maximum_XOR_of_Two_Numbers_in_an_Array {
    //Java O(n) solution using bit manipulation and HashMap
    public class Solution {
        public int findMaximumXOR(int[] nums) {
            int max = 0, mask = 0;
            for(int i = 31; i >= 0; i--){
                mask = mask | (1 << i);
                Set<Integer> set = new HashSet<>();
                for(int num : nums){
                    set.add(num & mask);
                }
                int tmp = max | (1 << i);
                for(int prefix : set){
                    if(set.contains(tmp ^ prefix)) {
                        max = tmp;
                        break;
                    }
                }
            }
            return max;
        }
    }

//-----------------------------------------------------------------------------//

    //Java O(n) solution using Trie
    class Trie {
        Trie[] children;
        public Trie() {
            children = new Trie[2];
        }
    }

    public int findMaximumXOR(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        // Init Trie.
        Trie root = new Trie();
        for(int num: nums) {
            Trie curNode = root;
            for(int i = 31; i >= 0; i --) {
                int curBit = (num >>> i) & 1;
                if(curNode.children[curBit] == null) {
                    curNode.children[curBit] = new Trie();
                }
                curNode = curNode.children[curBit];
            }
        }
        int max = Integer.MIN_VALUE;
        for(int num: nums) {
            Trie curNode = root;
            int curSum = 0;
            for(int i = 31; i >= 0; i --) {
                int curBit = (num >>> i) & 1;
                if(curNode.children[curBit ^ 1] != null) {
                    curSum += (1 << i);
                    curNode = curNode.children[curBit ^ 1];
                }else {
                    curNode = curNode.children[curBit];
                }
            }
            max = Math.max(curSum, max);
        }
        return max;
    }

}

/*
Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.
 */