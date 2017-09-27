package s_6_DP2;

public class _02Stone_Game_For {
	/**
     * @param A an integer array
     * @return an integer
     */
    public int stoneGame(int[] A) {
        // Write your code here
        if(A.length==0) {
            return 0;
        }
        int[][] dp=new int[A.length][A.length];
        int[] sums=new int[A.length+1];
        sums[0]=0;
        for(int i=0;i<A.length;i++){
            for(int j=i;j<A.length;j++){
                dp[i][j]=Integer.MAX_VALUE;
            }
        }
        for(int i=0;i<A.length;i++){
            dp[i][i]=0;
            sums[i+1]=sums[i]+A[i];
        }
        
        return search(0,A.length-1,dp,sums);
    }
    
    private int search(int start, int end, int[][] dp, int[] sums){
        if(dp[start][end]!=Integer.MAX_VALUE){
            return dp[start][end];
        }
        int min=Integer.MAX_VALUE;
        for(int k=start;k<end;k++){
            int left = search(start,k,dp,sums);
            int right = search(k+1,end,dp,sums);
            int now = sums[end+1]-sums[start];
            min=Math.min(min,left+right+now);
        }
        dp[start][end]=min;
        return min;
    }
}
/*死胡同:容易想到的一个思路从小往大,枚举第一次合并是在哪?
• 记忆化搜索的思路,从大到小,先考虑最后的0-n-1 合并的总花费
• State:
• dp[i][j] 表示把第i到第j个石子合并到一起的最小花费
• Function:
• 预处理sum[i,j] 表示i到j所有石子价值和
• dp[i][j] = min(dp[i][k]+dp[k+1][j]+sum[i,j]) 对于所有k属于{i,j}
• Intialize:
• for each i
• dp[i][i] = 0
• Answer:
• dp[0][n-1]*/

/*There is a stone game.At the beginning of the game the player picks 
 * n piles of stones in a line.
 

The goal is to merge the stones in one pile observing the following rules:

At each step of the game,the player can merge two adjacent piles to a new pile.
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