
// version 1: O(n^2)
class Solution {
public:
    /**
     * @param matrix a boolean 2D matrix
     * @return an integer
     */
    int maximalRectangle(vector<vector<bool> > &matrix) {
        // Write your code here
                if(matrix.empty() || matrix[0].empty()) {
            return 0;
        }

        int m = matrix.size();
        int n = matrix[0].size();

        vector<vector<int> > height(m, vector<int>(n, 0));
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(!matrix[i][j]) {
                    height[i][j] = 0;
                } else {
                    height[i][j] = (i == 0) ? 1 : height[i - 1][j] + 1;
                }
            }
        }

        int maxArea = 0;
        for(int i = 0; i < m; i++) {
            maxArea = max(maxArea, largestRectangleArea(height[i]));
        }
        return maxArea;
    }

    int largestRectangleArea(vector<int> &height) {
        vector<int> s;
        height.push_back(0);

        int sum = 0;
        int i = 0;
        while(i < height.size()) {
            if(s.empty() || height[i] > height[s.back()]) {
                s.push_back(i);
                i++;
            } else {
                int t = s.back();
                s.pop_back();
                sum = max(sum, height[t] * (s.empty() ? i : i - s.back() - 1));
            }
        }

        return sum;
    }
};

// version 2, O(n^3)
class Solution {
public:
    int getMaxLength(vector<char> &r1, vector<char> &r2) {
        // r1 = r1 and r2
        int lastIndex = -1;
        int maxLength = 0;
        for (int i = 0; i < r1.size(); i++) {
            r1[i] = (r1[i] == '1' && r2[i] == '1') ? '1' : '0';
            if (r1[i] == '0') {
                if (lastIndex == -1) {
                    continue;
                }
                maxLength = max(maxLength, i - lastIndex);
                lastIndex = -1;
            } else {
                if (lastIndex == -1) {
                    lastIndex = i;
                } else {
                    continue;
                }
            }
        }
        if (lastIndex != -1) {
            maxLength = max(maxLength, (int)(r1.size()) - lastIndex);
        }
        return maxLength;
    }

    int maximalRectangle(vector<vector<char> > &matrix) {
        if (matrix.size() == 0) {
            return 0;
        }

        int n = matrix.size();
        int m = matrix[0].size();
        vector<char> row;

        int result = 0;
        for (int i = 0; i < n; i++) {
            row = matrix[i];
            for (int j = i; j < n; j++) {
                int maxLength = getMaxLength(row, matrix[j]);
                result = max(result, maxLength * (j - i + 1));
            }
        }

        return result;
    }
};