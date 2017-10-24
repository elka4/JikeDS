package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _546_DFS_Remove_Boxes_H {
/*
When facing this problem, I am keeping thinking how to simulate the case when boxes[i] == boxes[j] when i and j are not consecutive. It turns out that the dp matrix needs one more dimension to store such state. So we are going to define the state as

dp[i][j][k] represents the max points from box[i] to box[j] with k boxes whose values equal to box[i]
The transformation function is as below

dp[i][j][k] = max(dp[i+1][m-1][1] + dp[m][j][k+1]) when box[i] = box[m]
 */
    public int removeBoxes(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }

        int size = boxes.length;
        int[][][] dp = new int[size][size][size];

        return get(dp, boxes, 0, size-1, 1);
    }

    private int get(int[][][] dp, int[] boxes, int i, int j, int k) {
        if (i > j) {
            return 0;
        } else if (i == j) {
            return k * k;
        } else if (dp[i][j][k] != 0) {
            return dp[i][j][k];
        } else {
            int temp = get(dp, boxes, i + 1, j, 1) + k * k;

            for (int m = i + 1; m <= j; m++) {
                if (boxes[i] == boxes[m]) {
                    temp = Math.max(temp, get(dp, boxes, i + 1, m - 1, 1) + get(dp, boxes, m, j, k + 1));
                }
            }

            dp[i][j][k] = temp;
            return temp;
        }


    }



//    Top-down DP:

    public int removeBoxes1(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return removeBoxesSub(boxes, 0, n - 1, 0, dp);
    }

    private int removeBoxesSub(int[] boxes, int i, int j, int k, int[][][] dp) {
        if (i > j) return 0;
        if (dp[i][j][k] > 0) return dp[i][j][k];

        for (; i + 1 <= j && boxes[i + 1] == boxes[i]; i++, k++); // optimization: all boxes of the same color counted continuously from the first box should be grouped together
        int res = (k + 1) * (k + 1) + removeBoxesSub(boxes, i + 1, j, 0, dp);

        for (int m = i + 1; m <= j; m++) {
            if (boxes[i] == boxes[m]) {
                res = Math.max(res, removeBoxesSub(boxes, i + 1, m - 1, 0, dp) + removeBoxesSub(boxes, m, j, k + 1, dp));
            }
        }

        dp[i][j][k] = res;
        return res;
    }


//    Bottom-up DP:

    public int removeBoxes2(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];

        for (int j = 0; j < n; j++) {
            for (int k = 0; k <= j; k++) {
                dp[j][j][k] = (k + 1) * (k + 1);
            }
        }

        for (int l = 1; l < n; l++) {
            for (int j = l; j < n; j++) {
                int i = j - l;

                for (int k = 0; k <= i; k++) {
                    int res = (k + 1) * (k + 1) + dp[i + 1][j][0];

                    for (int m = i + 1; m <= j; m++) {
                        if (boxes[m] == boxes[i]) {
                            res = Math.max(res, dp[i + 1][m - 1][0] + dp[m][j][k + 1]);
                        }
                    }

                    dp[i][j][k] = res;
                }
            }
        }

        return (n == 0 ? 0 : dp[0][n - 1][0]);
    }
//    Side notes: In case you are curious, for the problem "leet



//    Great Solution and explanation! Add one simple optimization to beat 99%.
//    Continuous same color boxes should always been used together

    public class Solution {
        public int removeBoxes(int[] boxes) {
            int n = boxes.length;
            //i, j, k store the max points from i to j, when there are k same color boxes (with i) adjacent to the left of i;
            int[][][] dp = new int[n][n][n];
            return removeBoxes(boxes, 0, n-1, 0, dp);
        }

        private int removeBoxes(int[] boxes, int i, int j, int k, int[][][] dp){
            int n = boxes.length;
            //Base case
            if(j < i) {
                return 0;
            }else if (i == j){
                return (k+1)*(k+1);
            }else if (dp[i][j][k] > 0){
                return dp[i][j][k];
            }
            //recurrence
            int tmpK=k, tmpI=i;
            //continuous same color boxes should always been used together
            while(tmpI<=j && boxes[tmpI]==boxes[i]){
                tmpK++;
                tmpI++;
            }
            int res=tmpK*tmpK+removeBoxes(boxes, tmpI, j, 0, dp);
            for(int l=tmpI+1; l<=j; l++){
                if(boxes[l]==boxes[i] && boxes[l-1]!=boxes[i]){//continuous same color boxes should always been used together
                    res=Math.max(res, removeBoxes(boxes, tmpI, l-1, 0, dp)+
                            removeBoxes(boxes, l, j, tmpK, dp));
                }
            }
            dp[i][j][k]=res;
            return res;
        }
    }

}
