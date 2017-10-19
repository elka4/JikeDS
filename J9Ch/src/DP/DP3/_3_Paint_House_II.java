package DP.DP3;

public class _3_Paint_House_II {

    // 9CH DP

////////////////////////////////////////////////////////////////////////////////////////////////
	public int minCostII(int[][] costs) {
        //Method DP: Using an int[k] last representing the last min costs in k tracks
        //Using an int[k] cur representing the current min costs

        //TimeO(kn) Space O(k)
        //use heap, nlogk
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

////////////////////////////////////////////////////////////////////////////////////////////////

    //time O(kn), space O(1)
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
////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////
}
/*


这里有n个房子在一列直线上，现在我们需要给房屋染色，共有k种颜色。

每个房屋染不同的颜色费用也不同，你需要设计一种染色方案使得相邻的房屋颜色不同，并且费用最小。

费用通过一个nxk 的矩阵给出，比如cost[0][0]表示房屋0染颜色0的费用，cost[1][2]表示房屋1染颜色2的费用。
注意事项

所有费用都是正整数
您在真实的面试中是否遇到过这个题？
样例

costs = [[14,2,11],[11,14,5],[14,3,10]] return 10

房屋 0 颜色 1, 房屋 1 颜色 2, 房屋 2 颜色 1， 2 + 5 + 3 = 10

 */
