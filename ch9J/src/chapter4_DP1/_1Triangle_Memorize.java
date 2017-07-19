package chapter4_DP1;

//Version 2 : Memorize Search
public class _1Triangle_Memorize {
    private int n;
    private int[][] minSum;
    private int[][] triangle;

    private int search(int x, int y) {
         //出口
       if (x >= n) {
           return 0;
       }
       //Memorize
       if (minSum[x][y] != Integer.MAX_VALUE) {
           return minSum[x][y];
       }
       //dp
       minSum[x][y] = Math.min(search(x + 1, y), search(x + 1, y + 1))
           + triangle[x][y];
       //answer
       return minSum[x][y];
    }

    public int minimumTotal(int[][] triangle) {
       if (triangle == null || triangle.length == 0) {
           return -1;
       }
       if (triangle[0] == null || triangle[0].length == 0) {
           return -1;
       }

       this.n = triangle.length;
       this.triangle = triangle;
       this.minSum = new int[n][n];

       //initialize
       for (int i = 0; i < n; i++) {
           for (int j = 0; j < n; j++) {
               minSum[i][j] = Integer.MAX_VALUE;
           }
       }

       return search(0, 0);
    }
}
