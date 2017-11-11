package _05_DFS._DFS_Matrix;
import java.util.*;

//  533. Lonely Pixel II
//  https://leetcode.com/problems/lonely-pixel-ii/description/
public class _533_DFS_Lonely_Pixel_II_M {
    //Verbose Java O(m*n) Solution, HashMap
    /*
The difficult parts are validating the two rules:

Row R and column C both contain exactly N black pixels.
For all rows that have a black pixel at column C, they should be exactly the same as row R
My solution:

Scan each row. If that row has N black pixels, put a string signature, which is concatenation of each characters in that row, as key and how many times we see that signature into a HashMap. Also during scan each row, we record how many black pixels in each column in an array colCount and will use it in step 2.
For input:
[['W', 'B', 'W', 'B', 'B', 'W'],
['W', 'B', 'W', 'B', 'B', 'W'],
['W', 'B', 'W', 'B', 'B', 'W'],
['W', 'W', 'B', 'W', 'B', 'B']]
We will get a HashMap:
{"WBWBBW": 3, "WWBWBB": 1}
and colCount array:
[0, 3, 1, 3, 4, 1]
Go through the HashMap and if the count of one signature is N, those rows potentially contain black pixels we are looking for. Then we validate each of those columns. For each column of them has N black pixels (lookup in colCount array), we get N valid black pixels.
For above example, only the first signature "WBWBBW" has count == 3. We validate 3 column 1, 3, 4 where char == 'B', and column 1 and 3 have 3 'B', then answer is 2 * 3 = 6.
Time complexity analysis:
Because we only scan the matrix for one time, time complexity is O(m*n). m = number of rows, n = number of columns.
     */
    public class Solution {
        public int findBlackPixel(char[][] picture, int N) {
            int m = picture.length;
            if (m == 0) return 0;
            int n = picture[0].length;
            if (n == 0) return 0;

            Map<String, Integer> map = new HashMap<>();
            int[] colCount = new int[n];

            for (int i = 0; i < m; i++) {
                String key = scanRow(picture, i, N, colCount);
                if (key.length() != 0) {
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
            }

            int result = 0;
            for (String key : map.keySet()) {
                if (map.get(key) == N) {
                    for (int j = 0; j < n; j++) {
                        if (key.charAt(j) == 'B' && colCount[j] == N) {
                            result += N;
                        }
                    }
                }
            }

            return result;
        }

        private String scanRow(char[][] picture, int row, int target, int[] colCount) {
            int n = picture[0].length;
            int rowCount = 0;
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < n; j++) {
                if (picture[row][j] == 'B') {
                    rowCount++;
                    colCount[j]++;
                }
                sb.append(picture[row][j]);
            }

            if (rowCount == target) return sb.toString();
            return "";
        }
    }

//////////////////////////////////////////////////////////////////////////////////////
    //[Java] [C++] Clean Code with Explanation - map<int, set<int>>
    public class Solution2 {
        public int findBlackPixel(char[][] pic, int N) {
            int m = pic.length;
            int n = pic[0].length;
            Map<Integer, Set<Integer>> rows = new HashMap<Integer, Set<Integer>>(); // black pixels in each row;
            Map<Integer, Set<Integer>> cols = new HashMap<Integer, Set<Integer>>(); // black pixels in each col;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (pic[i][j] == 'B') {
                        if (!rows.containsKey(i)) { rows.put(i, new HashSet<Integer>()); }
                        if (!cols.containsKey(j)) { cols.put(j, new HashSet<Integer>()); }
                        rows.get(i).add(j);
                        cols.get(j).add(i);
                    }
                }
            }
            int lonelys = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n && rows.containsKey(i); j++) {
                    if (pic[i][j] == 'B' && rows.get(i).size() == N && cols.containsKey(j) && cols.get(j).size() == N) {   // rule 1 fulfilled
                        int lonely = 1;
                        for (int row : cols.get(j)) {
                            if (!rows.get(i).equals(rows.get(row))) {
                                lonely = 0; break;
                            }
                        }
                        lonelys += lonely;
                    }
                }
            }
            return lonelys;
        }
    }
//////////////////////////////////////////////////////////////////////////////////////
}
/*
Given a picture consisting of black and white pixels, and a positive integer N, find the number of black pixels located at some specific row R and column C that align with all the following rules:

Row R and column C both contain exactly N black pixels.
For all rows that have a black pixel at column C, they should be exactly the same as row R
The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

Example:
Input:
[['W', 'B', 'W', 'B', 'B', 'W'],
 ['W', 'B', 'W', 'B', 'B', 'W'],
 ['W', 'B', 'W', 'B', 'B', 'W'],
 ['W', 'W', 'B', 'W', 'B', 'W']]

N = 3
Output: 6
Explanation: All the bold 'B' are the black pixels we need (all 'B's at column 1 and 3).
        0    1    2    3    4    5         column index
0    [['W', 'B', 'W', 'B', 'B', 'W'],
1     ['W', 'B', 'W', 'B', 'B', 'W'],
2     ['W', 'B', 'W', 'B', 'B', 'W'],
3     ['W', 'W', 'B', 'W', 'B', 'W']]
row index

Take 'B' at row R = 0 and column C = 1 as an example:
Rule 1, row R = 0 and column C = 1 both have exactly N = 3 black pixels.
Rule 2, the rows have black pixel at column C = 1 are row 0, row 1 and row 2. They are exactly the same as row R = 0.

Note:
The range of width and height of the input 2D array is [1,200].
 */