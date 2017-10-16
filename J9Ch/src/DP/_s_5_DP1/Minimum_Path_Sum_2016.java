package DP._s_5_DP1;

/*
 * Minimum Path Sum
￼￼• state: f[x][y]从起点走到x,y的最短路径
• function: f[x][y] = min(f[x-1][y], f[x][y-1]) + A[x][y]
• intialize: f[i][0] = sum(0,0 ~ i,0)
		     f[0][i] = sum(0,0 ~ 0,i)
• answer: f[n-1][m-1]
 */

//9ch summer 2016
public class Minimum_Path_Sum_2016{
public int minPathSum(int[][] grid){
	if(grid == null){
		return 0;
	}
	int n = grid.length;
	
	if(grid[0] == null){
		return 0;
	}
	int m = grid[0].length;
	int [][] f = new int[n][m];
	
	//initialize
	f[0][0] = grid[0][0];
	//f[0][0] = 1;// 不用动
	//特殊情况，先算好
	for(int i = 1; i< n ; i++){
		f[i][0] = f[i - 1][0] + grid[i][0];
		//f[i][0] = 1;
	}
	for(int i = 1; i < m; i++){
		f[0][i] = f[0][i - 1] + grid[0][i];
		//f[0][i] = 1;
	}
	
	//dp
	//普通情况
	for(int i = 1; i < n; i++){
		for(int j = 1; j < m; j++){
			f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + grid[i][j];
			//f[i][j] = f[i - 1][j] +f[i][j - 1];
		}
	}
	//answer
	return f[n - 1][m - 1];
}
}