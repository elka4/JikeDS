/**600. Smallest Rectangle Enclosing Black Pixels
 * Hard*/

class Solution {
public:
    /**
     * @param image a binary matrix with '0' and '1'
     * @param x, y the location of one of the black pixels
     * @return an integer
     */
    int minArea(vector<vector<char>>& image, int x, int y) {
        // Write your code here
        int m = image.size();
        if (m == 0)
            return 0;
        int n = image[0].size();
        if (n == 0)
            return 0;

        int start = y;
        int end = n - 1;
        int mid;
        while (start < end) {
            mid = start + (end - start) / 2 + 1;
            if (checkColumn(image, mid)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        int right = start;

        start = 0;
        end = y;
        while (start < end) {
            mid = start + (end - start) / 2;
            if (checkColumn(image, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        int left = start;

        start = x;
        end = m - 1;
        while (start < end) {
            mid = start + (end - start) / 2 + 1;
            if (checkRow(image, mid)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        int down = start;

        start = 0;
        end = x;
        while (start < end) {
            mid = start + (end - start) / 2;
            if (checkRow(image, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        int up = start;

        return (right - left + 1) * (down - up + 1);
    }

    bool checkColumn(vector<vector<char>>& image, int col) {
        for (int i = 0; i < image.size(); i++) {
            if (image[i][col] == '1') {
                return true;
            }
        }
        return false;
    }

    bool checkRow(vector<vector<char>>& image, int row) {
        for (int j = 0; j < image[0].size(); j++) {
            if (image[row][j] == '1') {
                return true;
            }
        }
        return false;
    }
};