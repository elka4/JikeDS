package Depth_first_Search_54;

/**
 * Created by tianhuizhu on 6/19/17.
 */
/*
Given a picture consisting of black and white pixels, find the number of black lonely pixels.

The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.

Example:
Input:
[['W', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]

Output: 3
Explanation: All the three 'B's are black lonely pixels.
Note:
The range of width and height of the input 2D array is [1,500].
*/
public class b_531_Lonely_Pixel_I {
// 1. Java O(nm) time with O(n+m) Space and O(1) Space Solutions

    //O(nm) Time, O(n+m) Space Solution:
    class solution1{
        public int findLonelyPixel(char[][] picture) {
            int n = picture.length, m = picture[0].length;

            int[] rowCount = new int[n], colCount = new int[m];
            for (int i=0;i<n;i++)
                for (int j=0;j<m;j++)
                    if (picture[i][j] == 'B') { rowCount[i]++; colCount[j]++; }

            int count = 0;
            for (int i=0;i<n;i++)
                for (int j=0;j<m;j++)
                    if (picture[i][j] == 'B' && rowCount[i] == 1 && colCount[j] == 1) count++;

            return count;
        }
    }
    //O(nm) Time, O(1) Space Solution:
    class solution2{
        public int findLonelyPixel(char[][] picture) {
            int n = picture.length, m = picture[0].length;

            int firstRowCount = 0;
            for (int i=0;i<n;i++)
                for (int j=0;j<m;j++)
                    if (picture[i][j] == 'B') {
                        if (picture[0][j] < 'Y' && picture[0][j] != 'V') picture[0][j]++;
                        if (i == 0) firstRowCount++;
                        else if (picture[i][0] < 'Y' && picture[i][0] != 'V') picture[i][0]++;
                    }

            int count = 0;
            for (int i=0;i<n;i++)
                for (int j=0;j<m;j++)
                    if (picture[i][j] < 'W' && (picture[0][j] == 'C' || picture[0][j] == 'X')) {
                        if (i == 0) count += firstRowCount == 1 ? 1 : 0;
                        else if (picture[i][0] == 'C' || picture[i][0] == 'X') count++;
                    }

            return count;
        }

    }
    //2. Java O(mn) time, O(m) space. 28ms
    public class Solution3 {
        public int findLonelyPixel(char[][] picture) {
            if (picture == null || picture.length == 0 || picture[0].length == 0) return 0;

            int[] colArray = new int[picture[0].length];
            for (int i = 0; i < picture.length; i++) {
                for (int j = 0; j < picture[0].length; j++) {
                    if (picture[i][j] == 'B') colArray[j]++;
                }
            }

            int ret = 0;
            for (int i = 0; i < picture.length; i++) {
                int count = 0, pos = 0;
                for (int j = 0; j < picture[0].length; j++) {
                    if (picture[i][j] == 'B') {
                        count++;
                        pos = j;
                    }
                }
                if (count == 1 && colArray[pos] == 1) ret++;
            }
            return ret;
        }
    }
//3. 1-liner Python
/*

def findLonelyPixel(self, picture):
            return sum(col.count('B') == 1 == picture[col.index('B')].count('B') for col in zip(*picture))
             */

//4. [Java] [C++] Clean Code with explanation
public class Solution4 {
    public int findLonelyPixel(char[][] pic) {
        int m = pic.length;
        int n = pic[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rows[i] += pic[i][j] == 'B' ? 1 : 0;
                cols[j] += pic[i][j] == 'B' ? 1 : 0;
            }
        }

        int lonely = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n && rows[i] > 0; j++) {
                lonely += (pic[i][j] == 'B' && rows[i] == 1 && cols[j] == 1) ? 1 : 0;
            }
        }

        return lonely;
    }
}

/*
class Solution {
public:
    int findLonelyPixel(vector<vector<char>>& pic) {
        int m = pic.size();
        int n = pic[0].size();
        vector<int> rows = vector<int>(m, 0);
        vector<int> cols = vector<int>(n, 0);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rows[i] += pic[i][j] == 'B';
                cols[j] += pic[i][j] == 'B';
            }
        }
        int lonely = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n && rows[i] > 0; j++) {
                lonely += pic[i][j] == 'B' && rows[i] == 1 && cols[j] == 1;
            }
        }
        return lonely;
    }
};

* */

//5. Python AC Solution
/*
class Solution(object):
    def findLonelyPixel(self, picture):
        """
        :type picture: List[List[str]]
        :rtype: int
        """
        if not picture:
            return 0

        bpos = []
        for row in picture:
            idx = [i for i in range(len(row)) if row[i] == 'B']
            bpos.append(idx)
        cnt = 0

        bposFlat = sum(bpos, [])

        for row in bpos:
            if len(row) == 1 and bposFlat.count(row[0]) == 1:
                cnt += 1
        return cnt
 */




}
