package j_2_BinarySearch.Array_2D_J;

//  Smallest Rectangle Enclosing Black Pixels

// leetcode 302. Smallest Rectangle Enclosing Black Pixels
//  https://leetcode.com/articles/smallest-rectangle-enclosing-black-pixels/
//
public class _600_Smallest_Rectangle_Enclosing_Black_Pixels {
    //Approach #3 (Binary Search) [Accepted]



    //C++/Java/Python Binary Search solution with explanation
    class Solution4{
        private char[][] image;
        public int minArea(char[][] iImage, int x, int y) {
            image = iImage;
            int m = image.length, n = image[0].length;
            int left = searchColumns(0, y, 0, m, true);
            int right = searchColumns(y + 1, n, 0, m, false);
            int top = searchRows(0, x, left, right, true);
            int bottom = searchRows(x + 1, m, left, right, false);
            return (right - left) * (bottom - top);
        }
        private int searchColumns(int i, int j, int top, int bottom, boolean opt) {
            while (i != j) {
                int k = top, mid = (i + j) / 2;
                while (k < bottom && image[k][mid] == '0') ++k;
                if (k < bottom == opt)
                    j = mid;
                else
                    i = mid + 1;
            }
            return i;
        }
        private int searchRows(int i, int j, int left, int right, boolean opt) {
            while (i != j) {
                int k = left, mid = (i + j) / 2;
                while (k < right && image[mid][k] == '0') ++k;
                if (k < right == opt)
                    j = mid;
                else
                    i = mid + 1;
            }
            return i;
        }
    //  Runtime: 1 ms
    }

    //Java (DRY)
    class Solution5{
        private char[][] image;
        public int minArea(char[][] iImage, int x, int y) {
            image = iImage;
            int m = image.length, n = image[0].length;
            int top = search(0, x, 0, n, true, true);
            int bottom = search(x + 1, m, 0, n, false, true);
            int left = search(0, y, top, bottom, true, false);
            int right = search(y + 1, n, top, bottom, false, false);
            return (right - left) * (bottom - top);
        }
        private boolean isWhite(int mid, int k, boolean isRow) {
            return ((isRow) ? image[mid][k] : image[k][mid]) == '0';
        }
        private int search(int i, int j, int low, int high, boolean opt, boolean isRow) {
            while (i != j) {
                int k = low, mid = (i + j) / 2;
                while (k < high && isWhite(mid, k, isRow)) ++k;
                if (k < high == opt)
                    j = mid;
                else
                    i = mid + 1;
            }
            return i;
        }
    //  Runtime: 2 ms
    }


    //1ms Concise Java Binary Search (DFS is 4ms)
    /*
    If we don't know programming, how do we find the 4 boundaries given a black pixel?

Do we need to search every black cell? Absolutely not.

Intuitively, we would expand from the given 1 * 1 black cell, "aggressively" expand to the 4 boundaries, roughly half of the remaining space. If we don't "cut" any black pixel, we know we go too far and should go back half.

This is exactly the process of binary search.

One simple way without any worry about boundary, is as follows:

Use a vertical line, to jump to the leftmost black pixel , in the range of [0, y]
Use a vertical line, to jump to the rightmost black pixel, in the range of [y, n - 1]
Use a horizontal line, to jump to the topmost black pixel, in the range of [0, x]
Use a horizontal line, to jump to the bottommost black pixel, in the range of [x, m - 1]
     */
    class Solution6{
        public int minArea(char[][] image, int x, int y) {
            int left = leftmost(image, 0, y, true);
            int right = rightmost(image, y, image[0].length - 1, true);
            int top = leftmost(image, 0, x, false);
            int bottom = rightmost(image, x, image.length - 1, false);
            return (right - left + 1) * (bottom - top + 1);
        }

        int leftmost(char[][] image, int min, int max, boolean horizontal) {
            int l = min, r = max;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (!hasBlack(image, mid, horizontal)) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return l;
        }

        int rightmost(char[][] image, int min, int max, boolean horizontal) {
            int l = min, r = max;
            while (l < r) {
                int mid = l + (r - l + 1) / 2;
                if (!hasBlack(image, mid, horizontal)) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
            return r;
        }

        boolean hasBlack(char[][] image, int mid, boolean horizontal) {
            if (horizontal) {
                for (int i = 0; i < image.length; i++) {
                    if (image[i][mid] == '1') {
                        return true;
                    }
                }
            } else {
                for (int j = 0; j < image[0].length; j++) {
                    if (image[mid][j] == '1') {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    //Version 2: Another harder but more compact way is as follows:
    class Solution7{
        public int minArea(char[][] image, int x, int y) {
            int m = image.length, n = image[0].length;
            int colMin = binarySearch(image, true, 0, y, 0, m, true);
            int colMax = binarySearch(image, true, y + 1, n, 0, m, false);
            int rowMin = binarySearch(image, false, 0, x, colMin, colMax, true);
            int rowMax = binarySearch(image, false, x + 1, m, colMin, colMax, false);
            return (rowMax - rowMin) * (colMax - colMin);
        }

        public int binarySearch(char[][] image, boolean horizontal, int lower, int upper, int min, int max, boolean goLower) {
            while(lower < upper) {
                int mid = lower + (upper - lower) / 2;
                boolean inside = false;
                for(int i = min; i < max; i++) {
                    if((horizontal ? image[i][mid] : image[mid][i]) == '1') {
                        inside = true;
                        break;
                    }
                }
                if(inside == goLower) {
                    upper = mid;
                } else {
                    lower = mid + 1;
                }
            }
            return lower;
        }

    }

    //Java DFS Solution and seeking for a binary search solution.
    /*
    DFS or BFS is the intuitive solution for this problem while the problem is with a tag "binary search". So can anyone provide a binary search answer. DFS complexity is O(m * n) and if binary search it would be O(n * lgm + m * lgn)
     */
    class Solution8{
        private int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = 0, maxY = 0;
        public int minArea(char[][] image, int x, int y) {
            if(image == null || image.length == 0 || image[0].length == 0) return 0;
            dfs(image, x, y);
            return(maxX - minX + 1) * (maxY - minY + 1);
        }
        private void dfs(char[][] image, int x, int y){
            int m = image.length, n = image[0].length;
            if(x < 0 || y < 0 || x >= m || y >= n || image[x][y] == '0') return;
            image[x][y] = '0';
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
            dfs(image, x + 1, y);
            dfs(image, x - 1, y);
            dfs(image, x, y - 1);
            dfs(image, x, y + 1);
        }
    }

    //Very easy DFS JAVA solution with comments
    class Solution9{
        int[][] dir = {{1,0}, {0,1}, {-1,0}, {0,-1}};
        boolean[][] flag;
        int rowMin, rowMax, colMin, colMax;
        int m, n;

        public int minArea(char[][] image, int x, int y) {
            if (image == null || image.length == 0 || image[0].length == 0) return 0;
            rowMin = x; rowMax = x; colMin = y; colMax = y;
            m = image.length;
            n = image[0].length;
            flag = new boolean[m][n];
            dfs(image, x, y);
            System.out.print(rowMin + " " + rowMax + " " + colMin + " " + colMax);
            System.out.println();
            return (rowMax - rowMin + 1) * (colMax - colMin + 1);
        }

        private void dfs(char[][] image, int x, int y) {
            if (x < 0 || x >= m || y < 0 || y >= n || image[x][y] == '0' || flag[x][y]) return;
            rowMax = x > rowMax ? x : rowMax;
            rowMin = x < rowMin ? x : rowMin;
            colMax = y > colMax ? y : colMax;
            colMin = y < colMin ? y : colMin;
            flag[x][y] = true;
            for (int d = 0; d < 4; d++)
                dfs(image, x + dir[d][0], y + dir[d][1]);
        }
    }


//-------------------------------------------------------------------------//////////////
    /**
     * @param image a binary matrix with '0' and '1'
     * @param x, y the location of one of the black pixels
     * @return an integer
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
/*
一个由二进制矩阵表示的图，0 表示白色像素点，1 表示黑色像素点。

黑色像素点是联通的，即只有一块黑色区域。

像素是水平和竖直连接的，给一个黑色像素点的坐标 (x, y) ，返回囊括所有黑色像素点的矩阵的最小面积。
 */

/*
An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.


 */