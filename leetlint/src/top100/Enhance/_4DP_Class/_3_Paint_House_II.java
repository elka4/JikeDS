package top100.Enhance._4DP_Class;

public class _3_Paint_House_II {
	public int minCostII(int[][] costs) {
        //Method DP: Using an int[k] last representing the last min costs in k tracks
        //Using an int[k] cur representing the current min costs
        if(costs == null || costs.length == 0)
            return 0;
        if(costs[0] == null || costs[0].length == 0)
            return 0;
        int col = costs[0].length;
        int[] last = new int[col];
        int[] cur = new int[col];
        for(int[] cost : costs) {
            for(int i = 0; i < col; i++) {
                cur[i] = cost[i] + findMin(last, i);
            }
            int[] tmp = cur;
            cur = last;
            last = tmp;
        }
        return findMin(last, last.length);
    }
    private int findMin(int[] arr, int except) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++) {
            if(i != except) {
                min = Math.min(min, arr[i]);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    public int minCostII2(int[][] costs) {
        if(costs == null || costs.length == 0) return 0;
          int m = costs.length, n = costs[0].length;
          int lastMin = 0;
          int lastSec = 0;
          int lastIndex = -1;
          for (int[] cost : costs) {
              int curMin = Integer.MAX_VALUE;
              int curSec = m;
              int curIndex = -1;
              for(int j = 0; j < n; j++) {
                  int val = cost[j] + (j == lastIndex ? lastSec : lastMin);
                  if(val < curMin) {               
                      curSec = curMin;
                      curMin = val;
                      curIndex = j;
                  } else if(val < curSec) {        
                      curSec = val;
                  }  
            }
            lastMin = curMin;
            lastSec = curSec;
            lastIndex = curIndex;
        }
        return lastMin;   
    }
}
