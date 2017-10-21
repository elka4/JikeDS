package _01_Array;

import org.junit.Test;

public class Matrix_Zigzag_Traversal {
    //Given a matrix of m x n elements (m rows, n columns),
    // return all elements of the matrix in ZigZag-order.

    /*
    [
  [1, 2,  3,  4],
  [5, 6,  7,  8],
  [9,10, 11, 12]
]
return [1, 2, 5, 9, 6, 3, 4, 7, 10, 11, 8, 12]
     */

    /*
    按之字形遍历矩阵，纯粹找下标规律。以题中所给范例为例，
    设(x, y)为矩阵坐标，按之字形遍历有如下规律：
(0, 0)
(0, 1), (1, 0)
(2, 0), (1, 1), (0, 2)
(0, 3), (1, 2), (2, 1)
(2, 2), (1, 3)
(2, 3)
可以发现其中每一行的坐标之和为常数，坐标和为奇数时 x 递增，为偶数时 x 递减。
     */

    public int[] printZigZag(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] result = new int[m * n];
        int index = 0;

        // 变换了m + n次方向
        for(int i = 0; i < m + n; i++) {
            if(i % 2 == 0) {
                for(int j = i; j >= 0; j--) {
                    // valid matrix index
                    if(j < m && i - j < n) {
                        result[index++] = matrix[j][i - j];
                    }
                }
            }

            if(i % 2 == 1) {
                for(int j = 0; j <= i; j++) {
                    if(j < m && i - j < n) {
                        result[index++] = matrix[j][i - j];
                    }
                }
            }
        }

        return result;
    }

    @Test
    public void test01(){
        int[][] matrix = {{1, 2,  3,  4},
                          {5, 6,  7,  8},
                            {9,10, 11, 12}};
        int[] result = printZigZag(matrix);
        for (int i : result
             ) {
            System.out.print(i + " ");
        }
    }
}
