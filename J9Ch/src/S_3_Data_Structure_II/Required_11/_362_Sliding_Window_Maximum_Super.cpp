
class Solution {
public:
    /**
     * @param nums: A list of integers.
     * @return: The maximum number inside the window at each moving.
     */

    void inQueue(deque<int> &Q, int num) {
        while (!Q.empty() && Q.back() < num) {
            Q.pop_back();
        }
        Q.push_back(num);
    }

    void outQueue(deque<int> &Q, int num) {
        if (Q.front() == num) {
            Q.pop_front();
        }
    }

    vector<int> maxSlidingWindow(vector<int> &nums, int k) {
        deque<int> Q;
        vector<int> result;

        for (int i = 0; i < k - 1; i++) {
            inQueue(Q, nums[i]);
        }

        for (int i = k - 1; i < nums.size(); i++) {
            inQueue(Q, nums[i]);
            result.push_back(Q.front());
            outQueue(Q, nums[i - k + 1]);
        }

        return result;
    }
};
