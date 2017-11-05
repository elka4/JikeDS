package _BinarySearch.Count;


//  302. Smallest Rectangle Enclosing Black Pixels

//  https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/description/
//
public class _302_BinarySearch_Smallest_Rectangle_Enclosing_Black_Pixels_H {
    //https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/solution/
    //  Approach #1 (Naive Linear Search) [Accepted]
    public class Solution1 {
        public int minArea(char[][] image, int x, int y) {
            int top = x, bottom = x;
            int left = y, right = y;
            for (x = 0; x < image.length; ++x) {
                for (y = 0; y < image[0].length; ++y) {
                    if (image[x][y] == '1') {
                        top = Math.min(top, x);
                        bottom = Math.max(bottom, x + 1);
                        left = Math.min(left, y);
                        right = Math.max(right, y + 1);
                    }
                }
            }
            return (right - left) * (bottom - top);
        }
    }


    //  Approach #2 (DFS or BFS) [Accepted]
    public class Solution2 {
        private int top, bottom, left, right;
        public int minArea(char[][] image, int x, int y) {
            if(image.length == 0 || image[0].length == 0)
                return 0;
            top = bottom = x; left = right = y;
            dfs(image, x, y);
            return (right - left) * (bottom - top);
        }
        private void dfs(char[][] image, int x, int y){
            if(x < 0 || y < 0 || x >= image.length ||
               y >= image[0].length || image[x][y] == '0')
                return;

            image[x][y] = '0'; // mark visited black pixel as white

            top = Math.min(top, x);
            bottom = Math.max(bottom, x + 1);
            left = Math.min(left, y);
            right = Math.max(right, y + 1);

            dfs(image, x + 1, y);
            dfs(image, x - 1, y);
            dfs(image, x, y - 1);
            dfs(image, x, y + 1);
        }
    }

    //  Approach #3 (Binary Search) [Accepted]

    class Solution3 {
        public int minArea(char[][] image, int x, int y) {
            int m = image.length, n = image[0].length;

            int left = searchColumns(image, 0, y, 0, m, true);
            int right = searchColumns(image, y + 1, n, 0, m, false);

            int top = searchRows(image, 0, x, left, right, true);
            int bottom = searchRows(image, x + 1, m, left, right, false);

            return (right - left) * (bottom - top);
        }


        private int searchColumns(char[][] image, int i, int j,
                                  int top, int bottom, boolean whiteToBlack) {
            while (i != j) {
                int k = top, mid = (i + j) / 2;
                while (k < bottom && image[k][mid] == '0') ++k;
                if (k < bottom == whiteToBlack) // k < bottom means the column mid has black pixel
                    j = mid; // search the boundary in the smaller half
                else
                    i = mid + 1; //search the boundary in the greater half
            }
            return i;
        }


        private int searchRows(char[][] image, int i, int j,
                               int left, int right, boolean whiteToBlack) {
            while (i != j) {
                int k = left, mid = (i + j) / 2;
                while (k < right && image[mid][k] == '0') ++k;
                if (k < right == whiteToBlack) // k < right means the row mid has black pixel
                    j = mid;
                else
                    i = mid + 1;
            }
            return i;
        }
    }

////////////////////////////////////////////////////////////////////////////
    //jiuzhang
public class Jiuzhang {
    /**
     * @param image a binary matrix with '0' and '1'
     * @param x, y the location of one of the black pixels
     * @return an integer
     */
    /*
    虽然不是有序数组，但是应该是000011110000这样子的序列，所以可以找到两端的index
    同样方法可以找到上下左右四个位置的index
    关键是要想清楚在某一个位置上，确定的条件, 其实就是找第一个出现的元素，和找最后一个出现的元素
     */
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
        }

        int n = image.length;
        int m = image[0].length;

        int left = findLeft(image, 0, y);
        int right = findRight(image, y, m - 1);
        int top = findTop(image, 0, x);
        int bottom = findBottom(image, x, n - 1);

        return (right - left + 1) * (bottom - top + 1);
    }

    private int findLeft(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isEmptyColumn(image, mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (isEmptyColumn(image, start)) {
            return end;
        }

        return start;
    }

    private int findRight(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isEmptyColumn(image, mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (isEmptyColumn(image, end)) {
            return start;
        }

        return end;
    }

    private int findTop(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isEmptyRow(image, mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (isEmptyRow(image, start)) {
            return end;
        }

        return start;
    }

    private int findBottom(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isEmptyRow(image, mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (isEmptyRow(image, end)) {
            return start;
        }

        return end;
    }

    private boolean isEmptyColumn(char[][] image, int col) {
        for (int i = 0; i < image.length; i++) {
            if (image[i][col] == '1') {
                return false;
            }
        }
        return true;
    }

    private boolean isEmptyRow(char[][] image, int row) {
        for (int j = 0; j < image[0].length; j++) {
            if (image[row][j] == '1') {
                return false;
            }
        }
        return true;
    }
}
}
/*
An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel.

The black pixels are connected, i.e., there is only one black region.

Pixels are connected horizontally and vertically.

Given the location (x, y) of one of the black pixels,
return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.
 */