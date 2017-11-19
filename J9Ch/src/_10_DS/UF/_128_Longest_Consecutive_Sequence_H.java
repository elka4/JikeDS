package _10_DS.UF;
import java.util.*;

//  128. Longest Consecutive Sequence
//  https://leetcode.com/problems/longest-consecutive-sequence/description/
//  http://www.lintcode.com/en/problem/longest-consecutive-sequence/
//  Array, Union Find
public class _128_Longest_Consecutive_Sequence_H {

//https://leetcode.com/problems/longest-consecutive-sequence/solution/

    //Approach #1 Brute Force [Time Limit Exceeded]
    class Solution1 {
        private boolean arrayContains(int[] arr, int num) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == num) {
                    return true;
                }
            }

            return false;
        }
        public int longestConsecutive(int[] nums) {
            int longestStreak = 0;

            for (int num : nums) {
                int currentNum = num;
                int currentStreak = 1;

                while (arrayContains(nums, currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }

            return longestStreak;
        }
    }

//-------------------------------------------------------------------------////
//Approach #2 Sorting [Accepted]

    class Solution2 {
        public int longestConsecutive(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }

            Arrays.sort(nums);

            int longestStreak = 1;
            int currentStreak = 1;

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[i-1]) {
                    if (nums[i] == nums[i-1]+1) {
                        currentStreak += 1;
                    }
                    else {
                        longestStreak = Math.max(longestStreak, currentStreak);
                        currentStreak = 1;
                    }
                }
            }

            return Math.max(longestStreak, currentStreak);
        }
    }

//-------------------------------------------------------------------------////
//Approach #3 HashSet and Intelligent Sequence Building [Accepted]
class Solution3 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum+1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}

//-------------------------------------------------------------------------////
//4
//My Java Solution using UnionFound

    public class Solution4 {
        public int longestConsecutive(int[] nums) {
            UF uf = new UF(nums.length);
            Map<Integer,Integer> map = new HashMap<Integer,Integer>(); // <value,index>
            for(int i=0; i<nums.length; i++){
                if(map.containsKey(nums[i])){
                    continue;
                }
                map.put(nums[i],i);
                if(map.containsKey(nums[i]+1)){
                    uf.union(i,map.get(nums[i]+1));
                }
                if(map.containsKey(nums[i]-1)){
                    uf.union(i,map.get(nums[i]-1));
                }
            }
            return uf.maxUnion();
        }
    }

    class UF{
        private int[] list;
        public UF(int n){
            list = new int[n];
            for(int i=0; i<n; i++){
                list[i] = i;
            }
        }

        private int root(int i){
            while(i!=list[i]){
                list[i] = list[list[i]];
                i = list[i];
            }
            return i;
        }

        public boolean connected(int i, int j){
            return root(i) == root(j);
        }

        public void union(int p, int q){
            int i = root(p);
            int j = root(q);
            list[i] = j;
        }

        // returns the maxium size of union
        public int maxUnion(){ // O(n)
            int[] count = new int[list.length];
            int max = 0;
            for(int i=0; i<list.length; i++){
                count[root(i)] ++;
                max = Math.max(max, count[root(i)]);
            }
            return max;
        }
    }
//-------------------------------------------------------------------------////
    //5
    /*
    I implemented the following code which is a slight improvement over the one suggested in the original post. I have optimized the retrieval of the maxUnion (getHighestRank in my case) to O(1).

Also, @liangyue268 Weighted Union Find with Path Compression isn't exactly O(nlogn) but more close to O(n). This is because the path compression function (see find operation) exhibits growth which follows the Inverse Ackermann Function. Both union and find are not exactly 1 but are very very very close to 1, visit here for more details.
     */
public class Solution5 {
    public int longestConsecutive(int[] nums) {
        final int length = nums.length;
        if (length <= 1) return length;

        final Map<Integer, Integer> elementIndexMap = new HashMap();
        final UnionFind uf = new UnionFind(length);
        for (int p = 0; p < length; p++) {
            final int i = nums[p];
            if (elementIndexMap.containsKey(i)) continue;
            if (elementIndexMap.containsKey(i+1)) uf.union(p, elementIndexMap.get(i+1));
            if (elementIndexMap.containsKey(i-1)) uf.union(p, elementIndexMap.get(i-1));
            elementIndexMap.put(i, p);
        }
        return uf.getHighestRank();
    }

    private final class UnionFind {
        final private int[] sequenceTree;
        final private int[] rank;
        private int highestRank;

        UnionFind(int length) {
            sequenceTree = new int[length];
            rank = new int[length];
            highestRank = 1;
            for (int i = 0; i < length; i++) {
                sequenceTree[i] = i;
                rank[i] = 1;
            }
        }

        void union(int p, int q) {
            final int pId = find(p); final int qId = find(q);

            if (pId == qId) return;

            int localHighestRank = 1;
            if (rank[pId] < rank[qId]) {
                sequenceTree[pId] = qId;
                rank[qId] += rank[pId];
                localHighestRank = rank[qId];
            } else {
                sequenceTree[qId] = pId;
                rank[pId] += rank[qId];
                localHighestRank = rank[pId];
            }
            highestRank = Math.max(highestRank, localHighestRank);
        }

        int find(int p) {
            while (p != sequenceTree[p]) {
                sequenceTree[p] = sequenceTree[sequenceTree[p]];
                p = sequenceTree[p];
            }
            return p;
        }

        int getHighestRank() { return highestRank; }
    }
}

//-------------------------------------------------------------------------////
    class Solution6{
        public int longestConsecutive2(int[] nums) {
            int n = nums.length;
            UnionFind uf = new UnionFind(n);

            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) map.put(nums[i], i);

            for (int num : nums)
                if (map.containsKey(num+1))
                    uf.union(map.get(num), map.get(num+1));

            return uf.getMaxSize();
        }

        private class UnionFind {
            private int[] id;
            private int[] sz;
            private int maxSize;

            public UnionFind(int n) {
                id = new int[n];
                sz = new int[n];
                for (int i = 0; i < n; i++) {
                    id[i] = i;
                    sz[i] = 1;
                }
                maxSize = n > 0 ? 1 : 0;
            }

            public int findRoot(int i) {
                if (i == id[i])  return i;
                id[i] = findRoot(id[i]);
                return id[i];
            }

            public void union(int p, int q) {
                int rootP = findRoot(p);
                int rootQ = findRoot(q);

                if (rootP == rootQ)  return;
                if (sz[rootP] < sz[rootQ]) {
                    id[rootP] = rootQ;
                    sz[rootQ] += sz[rootP];
                    maxSize = Math.max(maxSize, sz[rootQ]);
                } else {
                    id[rootQ] = rootP;
                    sz[rootP] += sz[rootQ];
                    maxSize = Math.max(maxSize, sz[rootP]);
                }
            }

            public int getMaxSize() {
                return this.maxSize;
            }
        }
    }
//-------------------------------------------------------------------------////
    //  9CH
    public class Jiuzhang1 {
        /**
         * @param nums: A list of integers
         * @return an integer
         */
        public int longestConsecutive(int[] nums) {
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                set.add(nums[i]);
            }

            int longest = 0;
            for (int i = 0; i < nums.length; i++) {
                int down = nums[i] - 1;
                while (set.contains(down)) {
                    set.remove(down);
                    down--;
                }

                int up = nums[i] + 1;
                while (set.contains(up)) {
                    set.remove(up);
                    up++;
                }

                longest = Math.max(longest, up - down - 1);
            }

            return longest;
        }
    }
//-------------------------------------------------------------------------////
    // version: 高频题班
    public class Jiuzhang2 {
        /**
         * @param num: A list of integers
         * @return an integer
         */
        public int longestConsecutive(int[] num) {
            // write you code here
            Set<Integer> set = new HashSet<>();
            for (int item : num) {
                set.add(item);
            }

            int ans = 0;
            for (int item : num) {
                if (set.contains(item)) {
                    set.remove(item);

                    int pre = item - 1;
                    int next = item + 1;
                    while (set.contains(pre)) {
                        set.remove(pre);
                        pre--;
                    }
                    while (set.contains(next)) {
                        set.remove(next);
                        next++;
                    }
                    ans = Math.max(ans, next - pre - 1);
                }
            }
            return ans;
        }
    }
//-------------------------------------------------------------------------////
}
/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.


 */


/*
124. 最长连续序列

 描述
 笔记
 数据
 评测
给定一个未排序的整数数组，找出最长连续序列的长度。

您在真实的面试中是否遇到过这个题？ Yes
说明
要求你的算法复杂度为O(n)

样例
给出数组[100, 4, 200, 1, 3, 2]，这个最长的连续序列是 [1, 2, 3, 4]，返回所求长度 4

标签
数组

 */