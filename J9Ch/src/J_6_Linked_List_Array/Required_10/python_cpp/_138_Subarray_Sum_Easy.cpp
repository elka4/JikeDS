/** 138 Subarray Sum
 * Easy*/


class Solution {
public:
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */
    vector<int> subarraySum(vector<int> nums){
        unordered_map<int, int> hash;

        int sum = 0;
        hash[0] = -1;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums[i];
            if (hash.find(sum) != hash.end()) {
                vector<int> result;
                result.push_back(hash[sum] + 1);
                result.push_back(i);
                return result;
            }
            hash[sum] = i;
        }

        vector<int> result;
        return result;
    }
};