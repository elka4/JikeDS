

class Solution {
public:
    /*
     * param k : description of k
     * param nums : description of array and index 0 ~ n-1
     * return: description of return
     */
    int kthLargestElement(int k, vector<int> nums){
        return helper(nums, 0, nums.size() - 1, nums.size() - k + 1);
    }

    int helper(vector<int> &nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }

        int i = left, j = right;
        int pivot = nums[(i + j) / 2];
        while (i <= j) {
            while (i <= j && nums[i] < pivot) {
                i++;
            }
            while (i <= j && nums[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swap(nums[i], nums[j]);
                i++;
                j--;
            }
        }


        if (left + k - 1 <= j) {
            return helper(nums, left, j, k);
        }

        if (left + k - 1 < i) {
            return nums[left + k - 1];
        }

        return helper(nums, i, right, k - (i - left));
    }
};