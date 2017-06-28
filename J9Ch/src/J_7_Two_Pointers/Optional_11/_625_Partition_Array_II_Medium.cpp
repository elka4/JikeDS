/** 625 Partition Array II */


// 参考程序1
class Solution {
public:
    /**
     * @param nums an integer array
     * @param low an integer
     * @param high an integer
     * @return nothing
     */
    void partition2(vector<int>& nums, int low, int high) {
        // Write your code here
        if (nums.size() <= 1) {
            return;
        }

        int pl = 0, pr = nums.size() - 1;
        int i = 0;
        while (i <= pr) {
            if (nums[i] < low) {
                swap(nums, pl, i);
                pl++;
                i++;
            } else if (nums[i] > high) {
                swap(nums, pr, i);
                pr--;
            } else {
                i ++;
            }
        }
    }

    void swap(vector<int>& nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
};

//参考程序2
class Solution {
public:
    /**
     * @param nums an integer array
     * @param low an integer
     * @param high an integer
     * @return nothing
     */
    void partition2(vector<int>& nums, int low, int high) {
        // Write your code here
        int left = 0;
        int right = nums.size() - 1;

        // 首先把区间分为 < low 和 >= low 的两个部分
        while(left <= right) {
            while(left <= right && nums[left] < low) {
                left ++;
            }
            while(left <= right && nums[right] >= low) {
                right --;
            }

            if(left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left ++;
                right --;
            }
        }

        // 然后从 >= low 的部分里分出 <= high 和 > high 的两个部分
        right = nums.size() - 1;
        while(left <= right) {
            while(left <= right && nums[left] <= high) {
                left ++;
            }
            while(left <= right && nums[right] > high) {
                right --;
            }
            if(left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left ++;
                right --;
            }
        }
    }
};