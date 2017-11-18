package _10_DS.SegmentTree;
import java.util.*;

//  308. Range Sum Query 2D - Mutable
//  https://leetcode.com/problems/range-sum-query-2d-mutable/description/
//  Binary Indexed Tree Segment Tree
public class _308_Range_Sum_Query_2D_Mutable_H {


////////////////////////////////////////////////////////////////////////////////////
    //1
class NumMatrix{
    TreeNode root;
    public NumMatrix(int[][] matrix) {
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
        if (root.row1 == row1 && root.col1 == col1 && root.row2 == row2 && root.col2 == col2)
            return root.sum;
        int rowMid = (root.row1 + root.row2) / 2;
        int colMid = (root.col1 + root.col2) / 2;
        if (rowMid >= row2) {
            if (colMid >= col2) {
                return sumRegion(root.c1, row1, col1, row2, col2);
            } else if (colMid + 1 <= col1) {
                return sumRegion(root.c2, row1, col1, row2, col2);
            } else {
                return sumRegion(root.c1, row1, col1, row2, colMid) + sumRegion(root.c2, row1, colMid+1, row2, col2);
            }
        } else if (rowMid + 1 <= row1) {
            if (colMid >= col2) {
                return sumRegion(root.c3, row1, col1, row2, col2);
            } else if (colMid + 1 <= col1) {
                return sumRegion(root.c4, row1, col1, row2, col2);
            } else {
                return sumRegion(root.c3, row1, col1, row2, colMid) + sumRegion(root.c4, row1, colMid+1, row2, col2);
            }
        } else {
            if (colMid >= col2) {
                return sumRegion(root.c1, row1, col1, rowMid, col2) + sumRegion(root.c3, rowMid+1, col1, row2, col2);
            } else if (colMid + 1 <= col1) {
                return sumRegion(root.c2, row1, col1, rowMid, col2) + sumRegion(root.c4, rowMid+1, col1, row2, col2);
            } else {
                return sumRegion(root.c1, row1, col1, rowMid, colMid) + sumRegion(root.c2, row1, colMid+1, rowMid, col2) + sumRegion(root.c3, rowMid+1, col1, row2, colMid) + sumRegion(root.c4, rowMid+1, colMid+1, row2, col2);
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

////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////
    //2
    //A Segment Tree Solution in Java

    public class NumMatrix2 {

        class TreeNode {
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

        TreeNode myroot;

        public NumMatrix2(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) return;

            myroot = buildTree(matrix, 0, 0, matrix.length-1, matrix[0].length-1);
        }

        private TreeNode buildTree(int[][] matrix, int row1, int col1, int row2, int col2) {
            if (row2 < row1 || col2 < col1) return null;

            TreeNode node = new TreeNode(row1, col1, row2, col2);
            if (row1 == row2 && col1 == col2) {
                node.sum = matrix[row1][col1];
                return node;
            }

            int rowMid = row1 + (row2 - row1) / 2;
            int colMid = col1 + (col2 - col1) / 2;
            node.c1 = buildTree(matrix, row1, col1, rowMid, colMid);
            node.c2 = buildTree(matrix, row1, colMid+1, rowMid, col2);
            node.c3 = buildTree(matrix, rowMid+1, col1, row2, colMid);
            node.c4 = buildTree(matrix, rowMid+1, colMid+1, row2, col2);

            node.sum += (node.c1 == null) ? 0 : node.c1.sum;
            node.sum += (node.c2 == null) ? 0 : node.c2.sum;
            node.sum += (node.c3 == null) ? 0 : node.c3.sum;
            node.sum += (node.c4 == null) ? 0 : node.c4.sum;
            return node;
        }

        public void update(int row, int col, int val) {
            updateTree(myroot, row, col, val);
        }

        private void updateTree(TreeNode root, int row, int col, int val) {
            if (root == null) return;

            if (row < root.row1 || row > root.row2 || col < root.col1 || col > root.col2)
                return;

            if (root.row1 == root.row2 && root.row1 == row && root.col1 == root.col2 && root.col1 == col) {
                root.sum = val;
                return;
            }

            updateTree(root.c1, row, col, val);
            updateTree(root.c2, row, col, val);
            updateTree(root.c3, row, col, val);
            updateTree(root.c4, row, col, val);

            root.sum = 0;
            root.sum += (root.c1 == null) ? 0 : root.c1.sum;
            root.sum += (root.c2 == null) ? 0 : root.c2.sum;
            root.sum += (root.c3 == null) ? 0 : root.c3.sum;
            root.sum += (root.c4 == null) ? 0 : root.c4.sum;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sumRegionTree(myroot, row1, col1, row2, col2);
        }

        private int sumRegionTree(TreeNode root, int row1, int col1, int row2, int col2) {
            if (root == null) return 0;

            if (root.row2 < row1 || root.row1 > row2 || root.col2 < col1 || root.col1 > col2)
                return 0;

            if (root.row1 >= row1 && root.row2 <= row2 && root.col1 >= col1 && root.col2 <= col2)
                return root.sum;

            return sumRegionTree(root.c1, row1, col1, row2, col2) +
                    sumRegionTree(root.c2, row1, col1, row2, col2) +
                    sumRegionTree(root.c3, row1, col1, row2, col2) +
                    sumRegionTree(root.c4, row1, col1, row2, col2);
        }
    }

////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////
}
/*
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
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