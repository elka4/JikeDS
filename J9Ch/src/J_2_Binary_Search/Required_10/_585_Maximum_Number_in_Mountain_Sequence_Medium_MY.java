package J_2_Binary_Search.Required_10;

/**585. Maximum Number in Mountain Sequence
 * Medium
 * Created by tianhuizhu on 6/27/17.
 */
public class _585_Maximum_Number_in_Mountain_Sequence_Medium_MY {

    public int mountainSequence1(int[] nums) {
        // Write your code here
        int start = 0;
        int end = nums.length - 1;
        while(start + 1 < end){
            int mid = start + (end - start)/2;
            if(nums[mid] < nums[mid + 1]){
                start = mid;
            }else {
                end = mid;
            }
        }
        return nums[start] > nums[end] ? nums[start] : nums[end];
    }

    public int mountainSequence2(int[] nums) {
        // Write your code here
        int start = 0;
        int end = nums.length - 1;
        while(start + 1 < end){
            int mid = start + (end - start)/2;
            if(nums[mid] > nums[mid + 1]){
                end = mid;
            }else {
                start = mid;
            }
        }
        return nums[start] > nums[end] ? nums[start] : nums[end];
    }

    public int mountainSequence3(int[] nums) {
        // Write your code here
        int start = 0;
        int end = nums.length - 1;
        while(start + 1 < end){
            int mid = start + (end - start)/2;
            if(nums[mid] > nums[mid - 1]){
                start = mid;
            }else {
                end = mid;
            }
        }
        return nums[start] > nums[end] ? nums[start] : nums[end];
    }

}
