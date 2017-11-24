package _01_Array.TreeSet;

import java.util.SortedSet;
import java.util.TreeSet;


/*
LeetCode – Contains Duplicate III (Java)

Given an array of integers, find out whether there are two distinct
indices i and j in the array such that the difference between nums[i]
and nums[j] is at most t and the difference between i and j is at most k.
 */


public class Contains_DuplicateIII {


    //Java Solution 1 - Simple
    //This solution simple. Its time complexity is O(nlog(k)).

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums==null||nums.length<2||k<0||t<0)
            return false;

        TreeSet<Long> set = new TreeSet<Long>();
        for(int i=0; i<nums.length; i++){
            long curr = (long) nums[i];

            long leftBoundary = (long) curr-t;
            long rightBoundary = (long) curr+t+1; //right boundary is exclusive, so +1
            SortedSet<Long> sub = set.subSet(leftBoundary, rightBoundary);
            if(sub.size()>0)
                return true;

            set.add(curr);

            if(i>=k){ // or if(set.size()>=k+1)
                set.remove((long)nums[i-k]);
            }
        }

        return false;
    }

//-------------------------------------------------------------------------//

    //Java Solution 2 - Deprecated

    /*The floor(x) method returns the greatest value that is less than x.
    The ceiling(x) methods returns the least value that is greater than x.
    The following is an example.  contains-duplicate-iii*/

    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        if (k < 1 || t < 0)
            return false;

        TreeSet<Integer> set = new TreeSet<Integer>();

        for (int i = 0; i < nums.length; i++) {
            int c = nums[i];
            if ((set.floor(c) != null && c <= set.floor(c) + t)
                    || (set.ceiling(c) != null && c >= set.ceiling(c) -t))
                return true;

            set.add(c);

            if (i >= k)
                set.remove(nums[i - k]);
        }

        return false;
    }


//------------------------------------------------------------------------------///////////







}
