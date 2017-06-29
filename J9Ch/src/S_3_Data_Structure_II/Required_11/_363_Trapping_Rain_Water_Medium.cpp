
class Solution {
public:
    int trap(vector<int>& heights) {
        int maxHeight = 0, maxIndex;
        for (int i = 0; i < heights.size(); i++) {
            if (heights[i] > maxHeight) {
                maxHeight = heights[i];
                maxIndex = i;
            }
        }

        int sum = 0;
        maxHeight = 0;
        for (int i = 0; i < maxIndex; i++) {
            if (maxHeight > heights[i]) {
                sum += maxHeight - heights[i];
            }
            maxHeight = max(maxHeight, heights[i]);
        }

        maxHeight = 0;
        for (int i = heights.size() - 1; i > maxIndex; i--) {
            if (maxHeight > heights[i]) {
                sum += maxHeight - heights[i];
            }
            maxHeight = max(maxHeight, heights[i]);
        }

        return sum;
    }
};