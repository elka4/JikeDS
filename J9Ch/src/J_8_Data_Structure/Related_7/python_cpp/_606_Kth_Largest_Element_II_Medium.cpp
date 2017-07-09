/** 606 Kth Largest Element II */


class Solution {
public:
    /**
     * @param nums an integer unsorted array
     * @param k an integer from 1 to n
     * @return the kth largest element
     */
    int kthLargestElement2(vector<int> nums, int k) {
        // Write your code here
        priority_queue<int, vector<int>, greater<int> > que;
        int n = nums.size();
        for(int i = 0; i < n; i ++) {
            if(que.size() == k) {
                if(nums[i] > que.top()) {
                    que.pop();
                    que.push(nums[i]);
                }
            }else {
                que.push(nums[i]);
            }
        }
        return que.top();
    }
};