

class Solution {
public:
    /*
     * @param k an integer
     * @param nums an integer array
     * @return kth smallest element
     */
    int kthSmallest(int k, vector<int> nums) {
        // write your code here
        return quickSelect(nums, 0, nums.size() - 1, k - 1);
    }

    int quickSelect(vector<int>& A, int start, int end , int k) {

        if (start == end)
            return A[start];

        int left = start, right = end;

        int pivot = A[(start + end) / 2];

        while (left <= right) {
            while (left <= right && A[left] < pivot) {
                left++;
            }

            while (left <= right && A[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;

                left++;
                right--;
            }
        }
        if (right >= k && start <= right)
            return quickSelect(A, start, right, k);
        else if (left <= k && left <= end)
            return quickSelect(A, left, end, k);
        else
            return A[k];
    }
};