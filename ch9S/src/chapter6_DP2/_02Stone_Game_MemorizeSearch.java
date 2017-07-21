package chapter6_DP2;

public class _02Stone_Game_MemorizeSearch {
	/**
     * @param A an integer array
     * @return an integer
     */
    int search(int l, int r, int[][] f, int[][] visit, int[][] sum) {
        
        if(visit[l][r] == 1)
            return f[l][r];
        if(l == r) {//一个石子，不需要合并
            visit[l][r] = 1;
            return f[l][r];
        }
        
        f[l][r] = Integer.MAX_VALUE;
        for (int k = l; k < r; k++) {
            f[l][r] = Math.min(f[l][r],
                            //l到k区间，k+1到r区间，以及合并这两个区间 分别的代价
                            search(l, k, f, visit, sum) + search(k + 1, r, f, visit, sum) + sum[l][r]);

        }
        visit[l][r] = 1;
        return f[l][r];
    }
    
    public int stoneGame(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int n = A.length;
        
        // initialize
        int[][] f = new int[n][n];
        int[][] visit = new int[n][n];
        
        for (int i = 0; i < n; i++) {//一个硬币，不需要合并
            f[i][i] = 0;
        }
        
        // preparation。计算每个区间和。
        int[][] sum = new int[n][n];
        for (int i = 0; i < n; i++) {
            sum[i][i] = A[i];
            for (int j = i + 1; j < n; j++) {
                sum[i][j] = sum[i][j - 1] + A[j];
            }
        }
        
        return search(0, n-1, f, visit, sum);
        
    }
}


/*There is a stone game.At the beginning of the game the player 
 * picks n piles of stones in a line.
 

The goal is to merge the stones in one pile observing the following 
rules:

At each step of the game,the player can merge two adjacent piles to
 a new pile.
The score is the number of stones in the new pile.
You are to determine the minimum of the total score.

Have you met this question in a real interview? Yes
Example
For [4, 1, 1, 4], in the best solution, the total score is 18:

1. Merge second and third piles => [4, 2, 4], score +2
2. Merge the first two piles => [6, 4]，score +6
3. Merge the last two piles => [10], score +10
Other two examples:
[1, 1, 1, 1] return 8
[4, 4, 5, 9] return 43

Tags 
Related Problems 
Hard Burst Balloons 29 %*/