import java.util.HashMap;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ContainsDuplicateII
 * Creator : Edward
 * Date : Oct, 2017
 * Description : 219. Contains Duplicate II
 */
public class _219_ContainsDuplicateII {
    //time : O(n) space : O(n)
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if ((i - map.get(nums[i])) <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
