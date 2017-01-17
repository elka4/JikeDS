package top100.Enhance._3Graph_PreClass;

public class _3_NumberOfIsland_DFS {
	public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;
        if(grid[0] == null || grid[0].length == 0)
            return 0;

        int count = 0;
        int row = grid.length;
        int col = grid[0].length;

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == '1') {
                    removeIsland(grid, row, col, i, j);
                    count++;
                }
            }
        }
        return count;
}

private void removeIsland(char[][] grid, int row, int col, int x, int y) {

        grid[x][y] = '0';

        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                if(isValid(i, j, x, y, row, col) && grid[x + i][y + j] == '1') {
                    removeIsland(grid, row, col, x + i, y + j);
                }
            }
        }
}

private boolean isValid(int di, int dj, int i, int j, int row, int col) {
        return Math.abs(di) != Math.abs(dj) && i + di >= 0 &&
        		i + di < row && j + dj >= 0 && j + dj < col;
}
}
