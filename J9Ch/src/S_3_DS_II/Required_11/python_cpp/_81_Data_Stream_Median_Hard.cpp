
class Solution {
public:
    /**
     * @param nums: A list of integers.
     * @return: The median of numbers
     */
    priority_queue<int> maxHeap, minHeap;
    vector<int> ans;
    int numOfElements = 0;
    vector<int> medianII(vector<int> &nums) {
        // write your code here
        int cnt = nums.size();
        for (int i = 0; i < cnt; ++i) {
		    addNumber(nums[i]);
		    ans.push_back(getMedian());
		}
        return ans;
    }
    void addNumber(int value) {
		maxHeap.push(value);
		if (numOfElements%2 == 0) {
			if (minHeap.empty()) {
				numOfElements++;
				return;
			}
			else if (maxHeap.top() > -minHeap.top()) {
				int maxHeapRoot = maxHeap.top();
			    int minHeapRoot = -minHeap.top();
                maxHeap.pop();
                minHeap.pop();
				maxHeap.push(minHeapRoot);
				minHeap.push(-maxHeapRoot);
			}
		}
		else {
			minHeap.push(-maxHeap.top());
            maxHeap.pop();
		}
		numOfElements++;
	}
	int getMedian() {
		return maxHeap.top();
	}
};
