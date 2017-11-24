package _08_Bit;
import java.util.*;
import org.junit.Test;

//  421. Maximum XOR of Two Numbers in an Array

//  https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/description/
//
public class _421_Bit_Maximum_XOR_of_Two_Numbers_in_an_Array_M {

//    Java O(n) solution using bit manipulation and HashMap
    public class Solution1 {
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
//------------------------------------------------------------------------------////
//Java O(n) solution using Trie
    class Trie {
        Trie[] children;
        public Trie() {
            children = new Trie[2];
        }
    }

    public int findMaximumXOR2(int[] nums) {
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

//------------------------------------------------------------------------------////

//    Java, bit manipulation and divide into two groups.

    public class Solution3 {
        public int findMaximumXOR(int[] nums) {
            int max = Integer.MIN_VALUE;
            int highBit = 0, count = 0;
            for(int num: nums) max = Math.max(max, num);
            while(count<=31){
                if((max & (1<<count)) != 0) highBit = count;
                count++;
            }
            List<Integer> isOne = new ArrayList<>();
            List<Integer> notOne = new ArrayList<>();

            for(int num: nums){
                if(((num>>highBit) & 1) == 1) isOne.add(num);
                else notOne.add(num);
            }

            return recur(isOne, notOne, highBit-1);
        }
        private int recur(List<Integer> isOne, List<Integer> notOne, int highBit){
            if(isOne.size()==1 && notOne.size()==1) return isOne.get(0) ^ notOne.get(0);

            List<Integer> l11 = new ArrayList<>();
            List<Integer> l10 = new ArrayList<>();
            List<Integer> l01 = new ArrayList<>();
            List<Integer> l00 = new ArrayList<>();

            for(int num: isOne){
                if(((num>>highBit) & 1) != 0) l11.add(num);
                else l10.add(num);
            }

            for(int num: notOne){
                if(((num>>highBit) & 1) != 0) l01.add(num);
                else l00.add(num);
            }

            int max = 0;
            if(l11.size()!=0 && l00.size()!=0) max = recur(l11, l00, highBit-1);
            if(l10.size()!=0 && l01.size()!=0) max = Math.max(max, recur(l10,l01,highBit-1));
            return max;
        }
    }
//------------------------------------------------------------------------------////


//------------------------------------------------------------------------------////
}
/*
Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.
 */

/*

 */