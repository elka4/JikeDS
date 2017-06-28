/** 608 Two Sum - Input array is sorted
 * Medium*/


class Solution {
public:
    /*
     * @param nums an array of Integer
     * @param target = nums[index1] + nums[index2]
     * @return [index1 + 1, index2 + 1] (index1 < index2)
     */
    vector<int> twoSum(vector<int> &nums, int target) {
        // write your code here
        int l = 0;
        int r = nums.size() -1;
        while(l < r) {
            if(nums[l] + nums[r] == target){
                vector<int> indexs{l+1,r+1};
                return indexs;
            } else if(nums[l] + nums[r] > target){
                r--;
            } else{
                l++;
            }
        }
    }
};