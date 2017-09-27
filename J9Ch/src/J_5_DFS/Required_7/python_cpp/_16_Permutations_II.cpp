/** 16. Permutations II
 * Medium*/


class Solution {
private:
    void helper(vector<vector<int> > &results,
                vector<int> &permutation,
                vector<int> &nums,
                bool used[]) {
        if (nums.size() == permutation.size()) {
            results.push_back(permutation);
            return;
        }

        for (int i = 0; i < nums.size(); i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && used[i - 1] == false && nums[i] == nums[i-1]) {
                continue;
            }

            used[i] = true;
            permutation.push_back(nums[i]);
            helper(results, permutation, nums, used);
            permutation.pop_back();
            used[i] = false;
        }
    }

public:
    vector<vector<int> > permuteUnique(vector<int> &nums) {
        vector<vector<int> > results;
        vector<int> permutation;
        bool used[nums.size()];

        for (int i = 0; i < nums.size(); i++) {
            used[i] = false;
        }

        sort(nums.begin(), nums.end());

        helper(results, permutation, nums, used);
        return results;
    }
};