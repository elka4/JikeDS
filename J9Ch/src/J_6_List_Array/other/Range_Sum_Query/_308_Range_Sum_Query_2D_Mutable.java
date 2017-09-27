package J_6_List_Array.other.Range_Sum_Query;

/*
Given a 2D matrix matrix, find the sum of the elements inside the rectangle
 defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2.
 */

public class _308_Range_Sum_Query_2D_Mutable {

    //Java 2D Binary Indexed Tree Solution clean and short 17ms

    public class NumMatrix {

        int[][] tree;
        int[][] nums;
        int m;
        int n;

        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) return;
            m = matrix.length;
            n = matrix[0].length;
            tree = new int[m+1][n+1];
            nums = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    update(i, j, matrix[i][j]);
                }
            }
        }

        public void update(int row, int col, int val) {
            if (m == 0 || n == 0) return;
            int delta = val - nums[row][col];
            nums[row][col] = val;
            for (int i = row + 1; i <= m; i += i & (-i)) {
                for (int j = col + 1; j <= n; j += j & (-j)) {
                    tree[i][j] += delta;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (m == 0 || n == 0) return 0;
            return sum(row2+1, col2+1) + sum(row1, col1) -
                    sum(row1, col2+1) - sum(row2+1, col1);
        }

        public int sum(int row, int col) {
            int sum = 0;
            for (int i = row; i > 0; i -= i & (-i)) {
                for (int j = col; j > 0; j -= j & (-j)) {
                    sum += tree[i][j];
                }
            }
            return sum;
        }
    }
// time should be O(log(m) * log(n))

/*
Explanation of Binary Indexed Tree :
https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/
 */
//////////////////////////////////////////////////////////////////////

    //15ms easy to understand java solution

    public class NumMatrix2 {
        private int[][] colSums;
        private int[][] matrix;

        public NumMatrix2(int[][] matrix) {
            if(   matrix           == null
                    || matrix.length    == 0
                    || matrix[0].length == 0   ){
                return;
            }

            this.matrix = matrix;

            int m   = matrix.length;
            int n   = matrix[0].length;
            colSums = new int[m + 1][n];
            for(int i = 1; i <= m; i++){
                for(int j = 0; j < n; j++){
                    colSums[i][j] = colSums[i - 1][j] + matrix[i - 1][j];
                }
            }
        }
        //time complexity for the worst case scenario: O(m)
        public void update(int row, int col, int val) {
            for(int i = row + 1; i < colSums.length; i++){
                colSums[i][col] = colSums[i][col] - matrix[row][col] + val;
            }

            matrix[row][col] = val;
        }
        //time complexity for the worst case scenario: O(n)
        public int sumRegion(int row1, int col1, int row2, int col2) {
            int ret = 0;

            for(int j = col1; j <= col2; j++){
                ret += colSums[row2 + 1][j] - colSums[row1][j];
            }

            return ret;
        }
    }



//////////////////////////////////////////////////////////////////////

    //Segment Tree Solution in Java
    public class NumMatrix3 {
        TreeNode root;
        public NumMatrix3(int[][] matrix) {
            if (matrix.length == 0) {
                root = null;
            } else {
                root = buildTree(matrix, 0, 0, matrix.length-1, matrix[0].length-1);
            }
        }

        public void update(int row, int col, int val) {
            update(root, row, col, val);
        }

        private void update(TreeNode root, int row, int col, int val) {
            if (root.row1 == root.row2 && root.row1 == row && root.col1 == root.col2 && root.col1 == col) {
                root.sum = val;
                return;
            }
            int rowMid = (root.row1 + root.row2) / 2;
            int colMid = (root.col1 + root.col2) / 2;
            TreeNode next;
            if (row <= rowMid) {
                if (col <= colMid) {
                    next = root.c1;
                } else {
                    next = root.c2;
                }
            } else {
                if (col <= colMid) {
                    next = root.c3;
                } else {
                    next = root.c4;
                }
            }
            root.sum -= next.sum;
            update(next, row, col, val);
            root.sum += next.sum;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sumRegion(root, row1, col1, row2, col2);
        }

        private int sumRegion(TreeNode root, int row1, int col1, int row2, int col2) {
            if (root.row1 == row1 && root.col1 == col1 &&
                    root.row2 == row2 && root.col2 == col2)
                return root.sum;

            int rowMid = (root.row1 + root.row2) / 2;
            int colMid = (root.col1 + root.col2) / 2;
            if (rowMid >= row2) {
                if (colMid >= col2) {
                    return sumRegion(root.c1, row1, col1, row2, col2);
                } else if (colMid + 1 <= col1) {
                    return sumRegion(root.c2, row1, col1, row2, col2);
                } else {
                    return sumRegion(root.c1, row1, col1, row2, colMid)
                            + sumRegion(root.c2, row1, colMid+1, row2, col2);
                }
            } else if (rowMid + 1 <= row1) {
                if (colMid >= col2) {
                    return sumRegion(root.c3, row1, col1, row2, col2);
                } else if (colMid + 1 <= col1) {
                    return sumRegion(root.c4, row1, col1, row2, col2);
                } else {
                    return sumRegion(root.c3, row1, col1, row2, colMid)
                            + sumRegion(root.c4, row1, colMid+1, row2, col2);
                }
            } else {
                if (colMid >= col2) {
                    return sumRegion(root.c1, row1, col1, rowMid, col2)
                            + sumRegion(root.c3, rowMid+1, col1, row2, col2);
                } else if (colMid + 1 <= col1) {
                    return sumRegion(root.c2, row1, col1, rowMid, col2)
                            + sumRegion(root.c4, rowMid+1, col1, row2, col2);
                } else {
                    return sumRegion(root.c1, row1, col1, rowMid, colMid)
                            + sumRegion(root.c2, row1, colMid+1, rowMid, col2)
                            + sumRegion(root.c3, rowMid+1, col1, row2, colMid)
                            + sumRegion(root.c4, rowMid+1, colMid+1, row2, col2);
                }
            }
        }

        private TreeNode buildTree(int[][] matrix, int row1, int col1, int row2, int col2) {
            if (row2 < row1 || col2 < col1)
                return null;
            TreeNode node = new TreeNode(row1, col1, row2, col2);
            if (row1 == row2 && col1 == col2) {
                node.sum = matrix[row1][col1];
                return node;
            }
            int rowMid = (row1 + row2) / 2;
            int colMid = (col1 + col2) / 2;
            node.c1 = buildTree(matrix, row1, col1, rowMid, colMid);
            node.c2 = buildTree(matrix, row1, colMid+1, rowMid, col2);
            node.c3 = buildTree(matrix, rowMid+1, col1, row2, colMid);
            node.c4 = buildTree(matrix, rowMid+1, colMid+1, row2, col2);
            node.sum += node.c1 != null ? node.c1.sum : 0;
            node.sum += node.c2 != null ? node.c2.sum : 0;
            node.sum += node.c3 != null ? node.c3.sum : 0;
            node.sum += node.c4 != null ? node.c4.sum : 0;
            return node;
        }

        public class TreeNode {
            int row1, row2, col1, col2, sum;
            TreeNode c1, c2, c3, c4;
            public TreeNode (int row1, int col1, int row2, int col2) {
                this.row1 = row1;
                this.col1 = col1;
                this.row2 = row2;
                this.col2 = col2;
                this.sum = 0;
            }
        }
    }




//////////////////////////////////////////////////////////////////////

    //15ms Very Concise Java Code Using BIT

    public class NumMatrix4 {
        // Using 2D Binary Indexed Tree, 2D BIT Def:
        // bit[i][j] saves the rangeSum of [i-(i&-i), i] x [j-(j&-j), j]
        // note bit index == matrix index + 1
        int n, m;
        int[][] bit, a;

        public NumMatrix4(int[][] matrix) {
            if (matrix.length < 1) return;
            n = matrix.length; m = matrix[0].length;
            bit = new int[n + 1][m + 1]; a = new int[n][m];

            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    update(i, j, matrix[i][j]);
        }

        public void update(int row, int col, int val) {
            int diff = val - a[row][col];
            a[row][col] = val;
            for (int i = row + 1; i <= n; i += i & -i)
                for (int j = col + 1; j <= m; j += j & -j)
                    bit[i][j] += diff;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sum(row2, col2) + sum(row1 - 1, col1 - 1) - sum(row1 - 1, col2) - sum(row2, col1 - 1);
        }

        public int sum(int row, int col) {
            int tot = 0;
            for (int i = row + 1; i > 0; i -= i & -i)
                for (int j = col + 1; j > 0; j -= j & -j)
                    tot += bit[i][j];
            return tot;
        }
    }



//////////////////////////////////////////////////////////////////////





//////////////////////////////////////////////////////////////////////





}
