/** 610 Two Sum - Difference equals to target
*/


class Solution {
public:
    /*
     * @param nums an array of Integer
     * @param target an integer
     * @return [index1 + 1, index2 + 1] (index1 < index2)
     */
    vector<int> twoSum7(vector<int> &nums, int target) {
        // write your code here
        vector<int> result;
        unordered_map<int, int> hashMap;
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            int tmp = nums[i] - target;
            if (hashMap.find(tmp) != hashMap.end()) {
                result.push_back(hashMap[tmp] + 1);
                result.push_back(i + 1);
                return result;
            }

            tmp = nums[i] + target;
            if (hashMap.find(tmp) != hashMap.end()) {
                result.push_back(hashMap[tmp] + 1);
                result.push_back(i + 1);
                return result;
            }

            hashMap[nums[i]] = i;
        }
     }
};

首页联系我们
Copyright © 2013-2017 九章算法 All Rights Reserved.  京ICP备16004690号-1
