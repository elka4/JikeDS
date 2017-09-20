package J_6_Linked_List_Array.other.Subarray_Sum;

import java.util.HashMap;
import java.util.Map;
/* 325. Maximum Size Subarray Sum Equals k

tag: hash table

Given an array nums and a target value k, find the maximum length of a
subarray that sums to k. If there isn't one, return 0 instead.

Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit
signed integer range.

Example 1:
Given nums = [1, -1, 5, -2, 3], k = 3,
return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

Example 2:
Given nums = [-2, -1, 2, 1], k = 1,
return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

Follow Up:
Can you do it in O(n) time?
 */

public class _325_Maximum_Size_Subarray_Sum_Equals_k {

    //O(n) super clean 9-line Java solution with HashMap
    public int maxSubArrayLen(int[] nums, int k) {
        int sum = 0, max = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if (sum == k) max = i + 1;
            else if (map.containsKey(sum - k)) max = Math.max(max, i - map.get(sum - k));
            if (!map.containsKey(sum)) map.put(sum, i);
        }
        return max;
    }

/////////////////////////////////////////////////////////

    //Java O(n) explain how I come up with this idea
    //https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/discuss/

    public int maxSubArrayLen2(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        for (int i = 1; i < n; i++)
            nums[i] += nums[i - 1];
        Map<Integer, Integer> map = new HashMap<>();
        // add this fake entry to make sum from 0 to j consistent
        map.put(0, -1);
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i] - k))
                max = Math.max(max, i - map.get(nums[i] - k));
            // keep only 1st duplicate as we want first index as left as possible
            if (!map.containsKey(nums[i]))
                map.put(nums[i], i);
        }
        return max;
    }

/////////////////////////////////////////////////////////
    //Java HashTable Solution beats 99.77%

    public int maxSubArrayLen3(int[] nums, int k) {
        if(nums.length==0) return 0;
        if(nums.length==1){
            if(nums[0]==k) return 1;
            return 0;
        }
        int[] sum = new int[nums.length+1];
        sum[0] = 0;
        for(int i=1;i<sum.length;i++){
            sum[i] = sum[i-1] + nums[i-1];
        }
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for(int i=0;i<sum.length;i++){
            hashtable.put(sum[i], i);
        }
        int max = 0;
        for(int i=0;i<sum.length;i++){
            Integer c = hashtable.get(sum[i]+k);
            if(c != null && c.intValue() > i && c.intValue() - i > max)
                max = c.intValue() - i;
        }
        return max;
    }


/////////////////////////////////////////////////////////

    //Share my o(n) solution for max SubArray sum Length


    public int maxSubArrayLen4(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
    // the affix sum matrix would be used to store the sum from index
    // 0 to any index. In this way the subsum of

    // the array between i and j is simply subSum[j] - subSum[i-1];
    // use a hashmap to store the sub sum from index 0 to index i for the quick reference.

        long[] subSum = new long[nums.length+1];
        int maxLen = 0;
        HashMap<Long, Integer> hashedSum = new HashMap<>();
        hashedSum.put(0L, 0);
        for (int i = 1; i < subSum.length; ++i) {
            subSum[i] = subSum[i-1] + nums[i-1];
            if (hashedSum.containsKey(subSum[i] - k)) {
                maxLen = Math.max(i - hashedSum.get(subSum[i] - k), maxLen);
            }
            if (!hashedSum.containsKey(subSum[i])) {
                // only keep the first subSum. In this way,
                // only the longest subarray will be guaranteed.
                hashedSum.put(subSum[i], i);
            }
        }
        return maxLen;
    }

/////////////////////////////////////////////////////////

    //Java 22 ms O(n) beats 100% by minimizing map access

    public int maxSubArrayLen5(int[] nums, int k) {
        int[] sum = new int[nums.length + 1];
        Map<Integer, Integer> longest = new HashMap<>(nums.length + 1);
        // The longest sum that equals to 0 so far is the zero-length prefix sum.
        longest.put(0, 0);
        for (int i = 0; i < nums.length; ++i) {
            sum[i + 1] = sum[i] + nums[i];
            longest.put(sum[i + 1], i + 1);
        }
        int len = 0;
        for (int i = 0; i < nums.length; ++i) {
            // What is the largest j such as that sum[j] - sum[i] = k?
            // It's the same as the largest j such as that sum[j] = k + sum[i].
            Integer l = longest.get(k + sum[i]);
            if (l != null && l - i > len) {
                if (l >= nums.length - 1) {
                    // It doesn't get any longer than this!
                    return l - i;
                } else {
                    len = l - i;
                }
            }
        }
        return len;
    }



/////////////////////////////////////////////////////////

    //Java with newer methods
    /*
    The algorithm is pretty simple and already explained by others,
    I just want to point out putIfAbsent and getOrDefault:
     */

    public int maxSubArrayLen6(int[] nums, int k) {
        Map<Integer, Integer> index = new HashMap();
        index.put(0, -1);
        int sum = 0, max = 0;
        for (int i=0; i<nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, i - index.getOrDefault(sum - k, i));
            index.putIfAbsent(sum, i);
        }
        return max;
    }

    //Though while putIfAbsent is perfect here, I admit that my usage of
    // getOrDefault is a bit of a hack here.




/////////////////////////////////////////////////////////

    //Java 37ms HashMap

    public int maxSubArrayLen7(int[] nums, int k) {
        if(nums == null || nums.length == 0) return 0;
        int length = nums.length, sum = 0, maxSubLen = 0;
        //Using a hash map to store the sum of all the values before and include nums[i]
        Map<Integer, Integer> map = new HashMap();
        for(int i = 0; i < length; i++) {
            sum += nums[i];
            if(sum == k) {
                maxSubLen = Math.max(maxSubLen, i + 1);
            } else if(map.containsKey(sum - k)) {
                maxSubLen = Math.max(maxSubLen, i - map.get(sum - k));
            }

            if(!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxSubLen;
    }



/////////////////////////////////////////////////////////

    //*Java* Easy to understand solution with HashMap (31ms)

    public int maxSubArrayLen8(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int max = 0;
        int sum = 0;
        map.put(0,-1);
        for(int i=0, L=nums.length; i<L; i++) {
            sum += nums[i];
            if(map.containsKey(sum-k)) {
                int prev = map.get(sum-k);
                max = i-prev > max ? i-prev : max;
            }
            // this is the major modification.
            // We always store the sum unless sum already exists.
            map.putIfAbsent(sum, i);
        }
        return max;
    }



/////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////




}
