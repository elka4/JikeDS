/** 42 Maximum Subarray II
*/


#define __STDC_LIMIT_MACROS
#include <stdint.h>

using namespace std;


class Solution {
public:
    /**
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    int maxTwoSubArrays(vector<int> &nums) {
        if (nums.size() == 0) {
            return 0;
        }

        int result = -2147483648;
        int n = (int)nums.size();
        vector<int> maxLeft(n + 1, -2147483648);
        vector<int> maxRight(n + 1, -2147483648);
        vector<int> profit(n + 1, 0);

        vector<int> sum(n + 1, 0);

        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        maxLeft[0] = -2147483648;
        for (int i = 1, valley = 0; i < sum.size(); i++) {
            maxLeft[i] = max(maxLeft[i - 1], sum[i] - valley);
            valley = min(valley, sum[i]);
        }


        for (int i = n, peak = sum[n]; i > 0; i--) {
            if (i == n) {
                maxRight[i] = peak - sum[i - 1];
            } else {
                maxRight[i]= max(maxRight[i + 1], peak - sum[i - 1]);
            }
            peak = max(peak, sum[i - 1]);
        }

        for (int i = 1; i < n; i++) {
            profit[i] = maxLeft[i] + maxRight[i + 1];
            result = max(result, profit[i]);
        }

        return result;
    }
};

首页联系我们
Copyright © 2013-2017 九章算法 All Rights Reserved.  京ICP备16004690号-1
