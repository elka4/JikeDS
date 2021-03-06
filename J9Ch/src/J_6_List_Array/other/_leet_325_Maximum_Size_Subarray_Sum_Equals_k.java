package J_6_List_Array.other;

import java.util.HashMap;
import java.util.Map;

public class _leet_325_Maximum_Size_Subarray_Sum_Equals_k {

    /*
    Given an array nums and a target value k,
    find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

Example 1:
Given nums = [1, -1, 5, -2, 3], k = 3,
return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

Example 2:
Given nums = [-2, -1, 2, 1], k = 1,
return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

Follow Up:
Can you do it in O(n) time?


     */


    /*
    找出数组里面最长的一段数，和等于k。
这题其实是 prefix sum array + two sum，利用前缀和数组实现快速区间和查询，同时 two sum 的方法快速地位 index.
这种 prefix sum 的下标要格外小心，很容易标错。。target value 差也是，写之前多手动过几个 case 保平安。

     */

    public int maxSubArrayLen(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int maxSize = 0;
        // 前i项sum
        // 需要最大长度，所以需要记录index
        HashMap<Integer, Integer> map = new HashMap<>();
        // 前0项的和是0
        map.put(0,0);
        int sum = 0;

        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(map.containsKey(sum - k)) {
                maxSize = Math.max(maxSize, i - map.get(sum - k) + 1);
            }

            if(!map.containsKey(sum)) map.put(sum, i + 1);
        }

        return maxSize;
    }

//------------------------------------------------------------------------------

    //O(n) super clean 9-line Java solution with HashMap
    public int maxSubArrayLen2(int[] nums, int k) {
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
    //The HashMap stores the sum of all elements before index i as key, and i as value.
    // For each i, check not only the current sum but also (currentSum - previousSum)
    // to see if there is any that equals k, and update max length.

      //      PS: An "else" is added. Thanks to beckychiu1988 for comment.

//------------------------------------------------------------------------------
//Java O(n) explain how I come up with this idea

    /*
    The subarray sum reminds me the range sum problem.
    Preprocess the input array such that you get
the range sum in constant time.
sum[i] means the sum from 0 to i inclusively
the sum from i to j is sum[j] - sum[i - 1] except that from 0 to j is sum[j].

j-i is equal to the length of subarray of original array. we want to find the max(j - i)
for any sum[j] we need to find if there is a previous sum[i] such that sum[j] - sum[i] = k
Instead of scanning from 0 to j -1 to find such i, we use hashmap to do the job in constant time.
However, there might be duplicate value of of sum[i] we should avoid overriding its
index as we want the max j - i, so we want to keep i as left as possible.


     */
    public int maxSubArrayLen3(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        for (int i = 1; i < n; i++)
            nums[i] += nums[i - 1];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // add this fake entry to make sum from 0 to j consistent
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

//------------------------------------------------------------------------------

//Java with newer methods
//The algorithm is pretty simple and already explained by others,
// I just want to point out putIfAbsent and getOrDefault:

    public int maxSubArrayLen4(int[] nums, int k) {
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



//------------------------------------------------------------------------------
//Java 37ms HashMap

    public int maxSubArrayLen5(int[] nums, int k) {
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


//------------------------------------------------------------------------------




}
