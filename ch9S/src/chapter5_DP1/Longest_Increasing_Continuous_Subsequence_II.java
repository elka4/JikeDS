package ch9S.chapter5_DP1;
@SuppressWarnings("all")
public class Longest_Increasing_Continuous_Subsequence_II {
    /**
     * @param A an integer matrix
     * @return  an integer
     */
    int [][]dp;
    int [][]flag ;
    int n ,m;
    public int longestIncreasingContinuousSubsequenceII(int[][] A) {
        // Write your code here
        if(A.length == 0)
            return 0;
        n = A.length;//行
        m  = A[0].length;//列
        int ans= 0;
        dp = new int[n][m]; //以ij作为一个子序列最长
        flag = new int[n][m];//表示这个点有没有被遍历过
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) { 
                dp[i][j] = search(i, j, A);//以ij作为结尾，最长有多长
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
    //用来访问上下左右四个位置
    int []dx = {1,-1,0,0};
    int []dy = {0,0,1,-1};

    //动态规划在这里
    int search(int x, int y, int[][] A)   {
        if(flag[x][y] != 0)    //记忆化搜索
            return dp[x][y];   //flag不是0，dp就有值
        
        int ans = 1; 
        int nx , ny;
        //访问x,y的上下左右四个方向
        for(int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if(0<= nx && nx < n && 0<= ny && ny < m ) {//判断边界
                if( A[x][y] > A[nx][ny]) {
                    ans = Math.max(ans,  search( nx, ny, A) + 1);
                }
            }
        }
        flag[x][y] = 1;//来到这一步说明已经从下一层search返还，标记xy已经被访问过
        dp[x][y] = ans;
        return ans;
    }
}


/*Give you an integer matrix (with row size n, column size m)，
 * find the longest increasing continuous subsequence in this matrix. 
 * (The definition of the longest increasing continuous subsequence here 
 * can start at any row or column and go up/down/right/left any direction).
  

Have you met this question in a real interview? Yes
Example
Given a matrix:

[
  [1 ,2 ,3 ,4 ,5],
  [16,17,24,23,6],
  [15,18,25,22,7],
  [14,19,20,21,8],
  [13,12,11,10,9]
]
return 25

Challenge 
O(nm) time and memory.

Tags 
Dynamic Programming
Related Problems 
Easy Longest Increasing Continuous Subsequence*/