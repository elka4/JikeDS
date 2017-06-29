
class Solution {
public:
    /**
     * @param matrix: a matrix of integers
     * @param k: an integer
     * @return: the kth smallest number in the matrix
     */
    int kthSmallest(vector<vector<int> > &matrix, int k) {
        // write your code here
        int val, ind;
        vector<int> index, nums, top, last;
        int m = matrix.size(), n = matrix[0].size();
        for (int i=0; i<m; ++i) heapAdd(nums, index, matrix[i][0], i);
        for (int i=0; i<m; ++i) last.push_back(0);
        while (top.size()<k) {
            heapGet(nums, index, val, ind);
            heapDel(nums, index);
            if (last[ind]!=n-1) {
                ++last[ind];
                heapAdd(nums, index, matrix[ind][last[ind]], ind);
            }
            top.push_back(val);
        }
        return top.back();
    }
private:
    void heapAdd(vector<int> &nums, vector<int> &index, int val, int ind) {
        nums.push_back(val);
        index.push_back(ind);
        int n = nums.size()-1;
        while (n>0 && nums[n]<nums[(n-1)/2]) {
            swap(nums[n], nums[(n-1)/2]);
            swap(index[n], index[(n-1)/2]);
            n = (n-1)/2;
        }
    }
    void heapGet(vector<int> &nums, vector<int> &index, int &val, int &ind) {
        val = nums[0];
        ind = index[0];
    }
    void heapDel(vector<int> &nums, vector<int> &index) {
        int n = nums.size()-1;
        nums[0] = nums[n];
        index[0] = index[n];
        nums.pop_back();
        index.pop_back();
        n = 0;
        while (n*2+1<nums.size()) {
            int t = n*2+1;
            if (t+1<nums.size() && nums[t+1]<nums[t]) ++t;
            if (nums[n]<=nums[t]) break;
            swap(nums[n], nums[t]);
            swap(index[n], index[t]);
            n = t;
        }
    }
};