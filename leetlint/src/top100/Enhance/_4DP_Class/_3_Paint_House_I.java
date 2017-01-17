package top100.Enhance._4DP_Class;

public class _3_Paint_House_I {
	public int minCost(int[][] costs) {
        //Method: DP m[i][k] (k = 0 ~ 2) represents min cost till
         // house i when i using color k
        if(costs == null || costs.length == 0)
            return 0;
        if(costs[0] == null || costs[0].length != 3)
            return 0;
        //Init
        int[] last = new int[3];
        int[] cur = new int[3];
        //Induction Rule: m[i][k] = costs[i][k] + Math.min(
           //                     m[i - 1][(k + 1) % 3], m[i - 1][(k + 2) % 3]);
        for (int[] cost : costs) {
            for (int i = 0; i < 3; i++) {
                cur[i] = cost[i] + Math.min(last[(i + 1) % 3], last[(i + 2) % 3]);
            }
            int[] tmp = cur;
            cur = last;
            last = tmp;
        }
        return Math.min(last[0], Math.min(last[1], last[2]));
    }

    public int minCost2(int[][] costs) {
        //Method 2: DP, using an int[3] last represent the last min costs in 3 diff track
        //using an int[3] next represent the cur min costs in 3 diff track
        if(costs == null || costs.length == 0)
            return 0;
        if(costs[0] == null || costs[0].length != 3)
            return 0;

        int[] last = new int[3];
        int[] cur = new int[3];

        for(int[] curCosts : costs) {
            for(int i = 0; i < 3; i++) {
                cur[i] = curCosts[i] + Math.min(last[(i + 1) % 3], last[(i + 2) % 3]);
            }
            //Swap cur and last
            int[] tmp = cur;
            cur = last;
            last = tmp;
        }
        return Math.min(last[0], Math.min(last[1], last[2]));
    }
}
