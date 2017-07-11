

class Solution {
public:
    /**
     * @param nums an array containing n + 1 integers which is between 1 and n
     * @return the duplicate one
     */
    int findDuplicate(vector<int>& nums) {
        // Write your code here ĺĺ
        if (nums.size() <= 1)
            return -1;

        int slow = nums[0];
		int fast = nums[nums[0]];
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[nums[fast]];
		}

		fast = 0;
		while (fast != slow) {
			fast = nums[fast];
			slow = nums[slow];
		}
		return slow;
    }
};