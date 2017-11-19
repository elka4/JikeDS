package _10_DS.Trie;
import java.util.*;

//  421. Maximum XOR of Two Numbers in an Array
//  https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/description/
public class _421_Maximum_XOR_of_Two_Numbers_in_an_Array_M {
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

//-------------------------------------------------------------------------//////////////
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
//-------------------------------------------------------------------------//////////////
//31ms O(n) Java solution using Trie
/*
We add the number into the trie and find the max possible XOR result at the same time.
Node.set() method will set the new node in the trie if needed and return the new node.
After setting the node, find the opposite bit in the trie to maximize the possible XOR result.
 */

    public class Solution3 {
        public class Node {
            Node one;
            Node zero;
            Node() {
                this.one = null;
                this.zero = null;
            }
            Node set(int n) {
                if (n == 0) {
                    if (this.one == null) this.one = new Node();
                    return this.one;
                }
                if (this.zero == null) this.zero = new Node();
                return this.zero;
            }
        }

        public int findMaximumXOR(int[] nums) {
            Node root = new Node();
            int re = 0;
            for (int num : nums) {
                int digit = num;
                int tmp = 0;
                Node setNode = root;
                Node findNode = root;
                int pos = 30;
                while (pos >= 0) {
                    digit = (num >> pos) & 1;
                    setNode = setNode.set(digit);
                    if (digit == 1) {
                        if (findNode.one != null) {
                            tmp = tmp | (1 << pos);
                            findNode = findNode.one;
                        } else {
                            findNode = findNode.zero;
                        }
                    } else {
                        if (findNode.zero != null) {
                            tmp = tmp | (1 << pos);
                            findNode = findNode.zero;
                        } else {
                            findNode = findNode.one;
                        }
                    }
                    pos--;
                }
                re = Math.max(re, tmp);
            }
            return re;
        }
    }
//-------------------------------------------------------------------------//////////////

    class Solution4{
//        Thank you~ This solution is truely fast.
//
//        Here I cleaned up some redundant codes:

        public class TrieNode {
            TrieNode[] next = new TrieNode[2];

            public TrieNode goTo(int bit) {
                if (next[bit] == null)
                    next[bit] = new TrieNode();
                return next[bit];
            }
        }

        public int findMaximumXOR(int[] nums) {
            int maxans = 0;
            TrieNode root = new TrieNode();

            for (int n : nums) {
                int maxTmp = 0;
                TrieNode i = root, j = root;

                for (int k = 30; k >= 0; k--) {
                    int bit = (n >> k) & 1;
                    i = i.goTo(bit);
                    if (j != null) {
                        if (j.next[1 ^ bit] != null) {
                            j = j.next[1 ^ bit]; // i, j better go along opposite path in trie tree.
                            maxTmp |= 1 << k;
                        } else
                            j = j.next[bit]; // i, j go same direction -> no maxTmp increase.
                    }
                }

                maxans = Math.max(maxans, maxTmp);
            }
            return maxans;
        }
    }

//-------------------------------------------------------------------------//////////////


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