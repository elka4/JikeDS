package J_8_DataStructure;

import java.util.Stack;

public class _03Maximal_Rectangle {
public int maximalRectangle(char[][] matrix) {
    if (matrix.length < 1) return 0;
    int n = matrix.length;
    if (n == 0) return 0;
    int m = matrix[0].length;
    if (m == 0) return 0;
    int[][] height = new int[n][m];

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            if (i == 0)
                height[i][j] = ((matrix[i][j] == '1') ? 1 : 0);
            else
                height[i][j] += ((matrix[i][j] == '1') ?
                        height[i-1][j] + 1 : 0);
        }
    }

    int answer = 0;
    for (int i = 0; i < n; ++i) {
        Stack<Integer> S = new Stack<Integer>();  
        for (int j = 0; j < m; j++) {
            while (!S.empty() && height[i][j] < height[i][S.peek()]) {
                int pos = S.peek();
                S.pop();
                answer = Math.max(answer,
                        height[i][pos]*(S.empty() ? j : j-S.peek()-1));
            }
            S.push(j);
        }
        while (!S.empty()) {
            int pos = S.peek();
            S.pop();
            answer = Math.max(answer, height[i][pos]*(S.empty()?
                    m : m-S.peek()-1));
        }
    }
    return answer;
}
}
/*Given a 2D boolean matrix filled with False and True, 
find the largest rectangle containing all True and return its area.


Have you met this question in a real interview? Yes
Example
Given a matrix:

[
  [1, 1, 0, 0, 1],
  [0, 1, 0, 0, 1],
  [0, 0, 1, 1, 1],
  [0, 0, 1, 1, 1],
  [0, 0, 0, 0, 1]
]
return 6.

Tags 
Dynamic Programming Array Stack
Related Problems 
Medium Maximal Square 26 %
Hard Largest Rectangle in Histogram 25 %*/