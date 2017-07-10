
// version 2: Recursion
class Solution {
public:
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    vector<vector<int> > permute(vector<int> nums) {

        vector<vector<int> > res;
        int n = nums.size();
        if (n == 0) {
            res.push_back(vector<int>());
            return res;
        }

        helper(res, nums, n - 1);

        return res;
    }

    void helper(vector<vector<int> > &res, vector<int> nums, int n){

        if(n == 0){
            res.push_back(nums);
        }

        for(int i = 0 ; i <= n; i++){
            swap(nums[i], nums[n]);
            helper(res, nums, n - 1);
            swap(nums[i], nums[n]);
        }
    }
};


// version 1: Non-Recursion
class Solution {
public:
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    vector<vector<int> > permute(vector<int> nums) {
        vector<vector<int> > permutations;
        if (nums.size() == 0) {
            permutations.push_back(vector<int>());
            return permutations;
        }

        int n = nums.size();
        vector<int> stack;
        bool inStack[n];
        for (int i = 0; i < n; i++) {
            inStack[i] = false;
        }

        stack.push_back(-1);
        while (stack.size() != 0) {
            // pop the last
            int last = stack[stack.size() - 1];
            stack.pop_back();
            if (last != -1) {
                inStack[last] = false;
            }

            // increase the last, find the next bigger & avaiable number
            int next = -1;
            for (int i = last + 1; i < n; i++) {
                if (inStack[i] == false) {
                    next = i;
                    break;
                }
            }
            if (next == -1) {
                continue;
            }

            // generate the next permutation
            stack.push_back(next);
            inStack[next] = true;
            for (int i = 0; i < n; i++) {
                if (!inStack[i]) {
                    stack.push_back(i);
                    inStack[i] = true;
                }
            }

            // generate real permutation from index
            vector<int> permutation;
            for (int i = 0; i < n; i++) {
                permutation.push_back(nums[stack[i]]);
            }
            permutations.push_back(permutation);
        }

        return permutations;
    }
};