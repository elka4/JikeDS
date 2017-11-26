package _05_DFS._DFS_Matrix;

//  531. Lonely Pixel I
//  https://leetcode.com/problems/lonely-pixel-i/
public class _531_DFS_Lonely_Pixel_I_M {
    //    O(nm) Time, O(n+m) Space Solution:
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

//-------------------------------------------------------------------------------
    //    O(nm) Time, O(1) Space Solution:
    public int findLonelyPixel2(char[][] picture) {
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

//-------------------------------------------------------------------------------

    /*
    thought is very simple, we can easily count how many times B occurs in each row. But how can we know if this col has existing B?
for example, input is
W B B B
B W W W
W W W B
W W W B
we can maintain an array calls colArray[], which is used to record how many times the B occurs in each column. Then solution is simple
     */
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

//-------------------------------------------------------------------------------
    //[Java] [C++] Clean Code with explanation
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

//----------------------------------------------------------------------------
}
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
