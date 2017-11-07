package _04_Tree._BST;
import java.util.*;

//  220. Contains Duplicate III
//  https://leetcode.com/problems/contains-duplicate-iii/description/
public class _220_Contains_Duplicate_III {
    //https://leetcode.com/articles/contains-duplicate-iii/

    //Approach #1 (Naive Linear Search) [Time Limit Exceeded]
    // Time limit exceeded.
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; ++i) {
            for (int j = Math.max(i - k, 0); j < i; ++j) {
                if (Math.abs(nums[i] - nums[j]) <= t) return true;
            }
        }
        return false;
    }

////////////////////////////////////////////////////////////////////////////

    //Approach #2 (Binary Search Tree) [Accepted]
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            // Find the successor of current element
            Integer s = set.ceiling(nums[i]);
            if (s != null && s <= nums[i] + t) return true;

            // Find the predecessor of current element
            Integer g = set.floor(nums[i]);
            if (g != null && nums[i] <= g + t) return true;

            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

////////////////////////////////////////////////////////////////////////////
    //Approach #3 (Buckets) [Accepted]
    // Get the ID of the bucket from element value x and bucket width w
    // In Java, `-3 / 5 = 0` and but we need `-3 / 5 = -1`.
    private long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }

    public boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        long w = (long)t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);
            // check if bucket m is empty, each bucket may contain at most one element
            if (d.containsKey(m))
                return true;
            // check the neighbor buckets for almost duplicate
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            // now bucket m is empty and no almost duplicate in neighbor buckets
            d.put(m, (long)nums[i]);
            if (i >= k) d.remove(getID(nums[i - k], w));
        }
        return false;
    }

////////////////////////////////////////////////////////////////////////////
}
/*
Given an array of integers, find out whether there are two distinct indices i and j
in the array such that the absolute difference between nums[i] and nums[j] is at most t
and the absolute difference between i and j is at most k.
 */

