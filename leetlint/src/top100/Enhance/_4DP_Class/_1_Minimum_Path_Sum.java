package top100.Enhance._4DP_Class;

public class _1_Minimum_Path_Sum {
    public int minPathSum2(int[][] grid) {
        int[] col = new int [grid[0].length];
        //Initiate first line
        col[0] = grid[0][0];
        for(int j = 1; j < grid[0].length; j++) {
            col[j] = grid[0][j] + col[j-1];
        }        

        for(int i = 1; i < grid.length; i++){
            col[0] += grid[i][0];//Calculate first elem
            for(int j = 1; j < grid[0].length; j++){
                col[j] = grid[i][j] + Math.min(col[j], col[j - 1]);//Find min of left and up elem
            }
        }
        return col[grid[0].length-1];
    }    
    public int minPathSum(int[][] grid) {
        int[][] minLen = new int[grid.length][grid[0].length];
        return helper(grid, 0, 0, minLen);
    }



    private int helper(int[][] grid, int row, int col, int[][] minLen) {
        if(row == grid.length - 1 && col == grid[0].length - 1) {
                return grid[row][col];
        }

    if(minLen[row][col] != 0) {
        return minLen[row][col];
    }



    int down = Integer.MAX_VALUE;
    int right = Integer.MAX_VALUE;
    if(row < grid.length - 1) {
        down = helper(grid, row + 1, col, minLen);

    }
    if(col < grid[0].length-1) {
        right = helper(grid, row, col + 1, minLen);
    }
    minLen[row][col] = Math.min(down, right) + grid[row][col];
    return minLen[row][col];
}
}
