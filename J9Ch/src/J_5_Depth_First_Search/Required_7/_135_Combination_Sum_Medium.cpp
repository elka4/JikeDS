/**135. Combination Sum
 * Medium*/


class Solution {
private:
    const int index_count;
    vector<vector<int> > results;
public:
    Solution() : index_count(10000) {};
    void backtrace(int target, int sum, vector<int> &candidates, int index[], int n)
    {
        if (sum > target) {
            return;
        }
        if (sum == target)
        {
            vector<int> result;
            for (int i = 1; i <= n; ++i)
            {
                result.push_back(candidates[index[i]]);
            }
            results.push_back(result);
            return;
        }
        for (int i = index[n]; i < candidates.size(); ++i)
        {
            index[n+1] = i;
            backtrace(target, sum+candidates[i], candidates, index, n+1);
        }
    }
    /**
     * @param candidates: A list of integers
     * @param target:An integer
     * @return: A list of lists of integers
     */
    vector<vector<int> > combinationSum(vector<int> &candidates, int target) {
        // write your code here
        sort(candidates.begin(), candidates.end());
        int m = 0, n = candidates.size();
        for (int i = 1; i < n; ++i)
            if (candidates[i] != candidates[m])
                candidates[++m] = candidates[i];
        candidates.resize(m + 1);

        int *index = new int[index_count];
        memset(index, 0, sizeof(int)*index_count);

        results.clear();
        backtrace(target, 0, candidates, index, 0);

        delete[] index;

        return results;
    }
};

}