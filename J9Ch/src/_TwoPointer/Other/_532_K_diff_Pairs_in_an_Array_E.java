package _TwoPointer.Other;
import java.util.*;

//  532. K-diff Pairs in an Array
//  https://leetcode.com/problems/k-diff-pairs-in-an-array/description/
//
public class _532_K_diff_Pairs_in_an_Array_E {
//---------------------------------------------------------------------------
    //1
    //[C++] [Java] Clean Code with Explanation [set] [map]
    public class Solution1 {
        public int findPairs(int[] nums, int k) {
            if (k < 0) { return 0; }

            Set<Integer> starters = new HashSet<Integer>();
            Set<Integer> uniqs = new HashSet<Integer>();

            for (int i = 0; i < nums.length; i++) {
                if (uniqs.contains(nums[i] - k)) starters.add(nums[i] - k);
                if (uniqs.contains(nums[i] + k)) starters.add(nums[i]);
                uniqs.add(nums[i]);
            }

            return starters.size();
        }
    }
//---------------------------------------------------------------------------
    //2
    //    Thanks for your concise code. Here's my java solution,
    // but it is really slow, O(n*n) complexity :--(
    public static int findPairs2(int []nums, int k){
        int count=0;
        Arrays.sort(nums);//sort at first
        for(int i=0;i<nums.length-1;i++){
            if(i>0 && nums[i]==nums[i-1]){// first loop, skip the same elements
                continue;
            }
            for(int j=i+1;j<nums.length;j++){
                //second loop, skip the same elements
                if(j>i+1 && nums[j]==nums[j-1]){
                    continue;
                }
                //if the firts and second elements' diff is k, we count
                if(nums[j]-nums[i]==k){
                    count++;
                }
            }
        }
        return count;
    }
//---------------------------------------------------------------------------
    //3
    //Two-pointer Approach
    /*The problem is just a variant of 2-sum.
                Update: Fixed a bug that can cause integer subtraction overflow.
                Update: The code runs in O(n log n) time, using O(1) space.*/
    public int findPairs3(int[] nums, int k) {
        int ans = 0;
        Arrays.sort(nums);
        for (int i = 0, j = 0; i < nums.length; i++) {
            for (j = Math.max(j, i + 1); j < nums.length && (long) nums[j] - nums[i] < k; j++) ;
            if (j < nums.length && (long) nums[j] - nums[i] == k) ans++;
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) i++;
        }
        return ans;
    }
//---------------------------------------------------------------------------
    //4
    //great! here is my two-pointers on array solution:
    public class Solution4 {
        public int findPairs(int[] nums, int k) {
            Arrays.sort(nums);

            int start = 0, end = 1, result = 0;
            while (start < nums.length && end < nums.length) {
                if (start == end || nums[start] + k > nums[end]) {
                    end++;
                } else if (nums[start] + k < nums[end]) {
                    start++;
                } else {
                    start++;
                    result++;
                    // start
                    //  |
                    // [1, 1, ...., 8, 8]
                    //              |
                    //             end
                    while (start < nums.length && nums[start] == nums[start - 1])
                        start++;

                    end = Math.max(end + 1, start + 1);
                }
            }
            return result;
        }
    }
    /*
            if (k < 0) return 0;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0, j = 1; j < nums.length;) {
            if (j <= i || nums[i] + k > nums[j]) {
                j++;
            } else if (i > 0 && nums[i] == nums[i - 1] || nums[i] + k < nums[j]) {
                i++;
            } else {
                ans++;
                i++;
            }
        }
        return ans;
     */
//---------------------------------------------------------------------------
    //5
    //    Time complexity: O(k * n) or n * logn
    public int findPairs6(int[] nums, int k) {
        int n = nums.length;
        if(n < 2) return 0;
        Arrays.sort(nums);
        int s = 0, e = 1, res = 0;
        while(s < e && e < n){
            if(nums[e] - nums[s] < k) e++;
            else{
                if(nums[e] - nums[s] == k){
                    if(s == 0 || nums[s] != nums[s - 1]) res++;
                }
                s++;
                if(s == e) e++;
            }
        }
        return res;
    }
//---------------------------------------------------------------------------
    //6
    public int findPairs7(int[] nums, int k) {
        Arrays.sort(nums);

        int left=0, right=1, count=0;
        while(right<nums.length){
            int diff = Math.abs(nums[right]-nums[left]);
            if(diff==k){
                count++;
                // skip all duplicates at right pointer
                while(++right<nums.length&&nums[right]==nums[right-1]);
            }else if(diff<k){
                right++;
            }else{
                //skip all duplicates at left pointer
                while(++left<nums.length&&nums[left]==nums[left-1]);
                right = left+1;
            }
        }

        return count;
    }

//---------------------------------------------------------------------------
    //7
/*
    @stevenli, Inspired by your solution, I wrote a different approach where we move either of the pointers just once. I don't like to move pointers with different steps in every iteration.

    What do you think?
*/

    public int FindPairs8(int[] nums, int k) {
        Arrays.sort(nums);
        int diffCount = 0;
        if(nums.length == 0 || nums.length == 1)
        {
            return diffCount;
        }
        int left = 0;
        int right = 1;
        while(right < nums.length)
        {
            int diff = nums[right] - nums[left];
            if(diff == k)
            {
                if(left == right - 1 || nums[right] != nums[right - 1])
                {
                    diffCount++;
                }
                right++;
            }
            else if(diff > k)
            {
                left++;
                if(right <= left)
                {
                    right++;
                }
            }
            else
            {
                right++;
            }
        }
        return diffCount;
    }
//---------------------------------------------------------------------------
    //8
    //Java O(n) solution - one Hashmap, easy to understand
    public class Solution8 {
        public int findPairs(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k < 0)   return 0;

            Map<Integer, Integer> map = new HashMap<>();
            int count = 0;
            for (int i : nums) {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (k == 0) {
                    //count how many elements in the array that appear more than twice.
                    if (entry.getValue() >= 2) {
                        count++;
                    }
                } else {
                    if (map.containsKey(entry.getKey() + k)) {
                        count++;
                    }
                }
            }

            return count;
        }
    }
//---------------------------------------------------------------------------
    //9
    //    Similar solution
    public class Solution9 {
        public int findPairs(int[] nums, int k) {
            Map<Integer,Integer> mp = new HashMap<Integer,Integer>();
            if(k<0)
                return 0;

            for(int i=0;i<nums.length;i++) {
                mp.put(nums[i],i);
            }

            int cnt=0;
            for(int i=0;i<nums.length;i++) {
                if(mp.containsKey(k+nums[i]) && mp.get(k+nums[i])!=i) {
                    cnt++;
                    mp.remove(k+nums[i]);
                }
            }
            return cnt;
        }
    }
//---------------------------------------------------------------------------
    //10
    //    Thanks for sharing! But duplicate solution need to be handled, right?
    public int findPairs10(int[] nums, int k) {
        if (nums.length == 0 || k < 0) return 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int cnt = 0;
        for (int num : nums) {
            if (k == 0) {
                if (map.containsKey(num) && map.remove(num) >= 2) cnt++;
            } else {
                if (map.remove(num + k) != null) cnt++;
            }
        }
        return cnt;
    }
//---------------------------------------------------------------------------
    //11
    public class Solution12 {
        public int findPairs(int[] nums, int k) {
            if(nums.length == 0 || k < 0) return 0;
            int count = 0;
            HashSet<Integer> set = new HashSet<Integer>();
            HashSet<Integer> used = new HashSet<Integer>();
            if(k==0){
                for(int num:nums){
                    if(set.contains(num) && !used.contains(num)){
                        count++;
                        used.add(num);
                    }else{
                        set.add(num);
                    }
                }
                return count;
            }
            for(int num:nums){
                set.add(num);
            }
            for(int num: nums){
                if(!used.contains(num) && set.contains(num+k)){
                    count++;
                    used.add(num);
                }
            }
            return count;
        }
    }
//---------------------------------------------------------------------------
    //12
    //Self-explained AC Java Sliding Window
    public  int findPairs13(int[] nums, int k) {
        if(k<0 || nums.length<=1){
            return 0;
        }

        Arrays.sort(nums);
        int count = 0;
        int left = 0;
        int right = 1;

        while(right<nums.length){
            int firNum = nums[left];
            int secNum = nums[right];
            // If less than k, increase the right index
            if(secNum-firNum<k){
                right++;
            }
            // If larger than k, increase the left index
            else if(secNum - firNum>k){
                left++;
            }
            // If equal, move left and right to next different number
            else{
                count++;
                while(left<nums.length && nums[left]==firNum){
                    left++;
                }
                while(right<nums.length && nums[right]==secNum){
                    right++;
                }

            }
            //left and right should not be the same number
            if(right==left){
                right++;
            }
        }
        return count;
    }

//---------------------------------------------------------------------------
}
/*
Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

Example 1:
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.


Example 2:
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).


Example 3:
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).


Note:
The pairs (i, j) and (j, i) count as the same pair.
The length of the array won't exceed 10,000.
All the integers in the given input belong to the range: [-1e7, 1e7].

 */