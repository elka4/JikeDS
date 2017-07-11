/** 597 Build Post Office*/


//  方法一见java

//  方法二
// Time Complexity O(n * m)  n和m分别是矩阵的行和列
class Solution {
public:
    /**
     * @param grid a 2D grid
     * @return an integer
     */
    int shortestDistance(vector<vector<int>>& grid) {
        // Write your code here
        int row = grid.size(), column = grid[0].size();
        if(row == 0 || column == 0 || !haveZero(grid, row, column)) {
            return -1;
        }

        vector<int> rowSum(row);
        vector<int> columnSum(column);
        for(int i = 0; i < row; i++)
            for(int j = 0; j < column; j++)
                if (grid[i][j] == 1) {
                    rowSum[i]++;
                    columnSum[j]++;
                }

        vector<int> costRow(row);
        vector<int> costColumn(column);
        getSumDistance(rowSum,row,costRow);
        getSumDistance(columnSum,column,costColumn);

        int cost = INT_MAX;
        for(int i = 0; i < row; i++)
            for(int j = 0; j < column; j++)
                if(grid[i][j] == 0 && cost > costRow[i] + costColumn[j]) {
                    cost = costRow[i] + costColumn[j];
                }
        return cost;
    }

    void getSumDistance(vector<int>& a, int n, vector<int>& cost) {
        vector<int> prefixSum1(n);
        vector<int> prefixSum2(n);
    	/*
    	第一阶段，处理前缀。
    	prefixSum1记录数组 a 的前缀和，即:prefixSum1[i]=a[0]+a[1]+..+a[i].
    	prefixSum2记录数组 prefixSum1 前缀和，prefixSum2即为前 i 个点到第 i 个点的代价和。
    	*/
    	prefixSum1[0] = a[0];
    	for(int i = 1; i < n; i++) {
    		prefixSum1[i] = prefixSum1[i - 1] + a[i];
    	}
    	prefixSum2[0] = 0;
    	for(int i = 1; i < n; i++) {
    		prefixSum2[i] = prefixSum2[i - 1] + prefixSum1[i - 1];
     	}

     	for(int i = 0; i < n; i++) {
     		cost[i] = prefixSum2[i];
     	}

    	/*
    	第二阶段，处理后缀。
    	prefixSum1记录数组 a 的后缀和，即:prefixSum1[i]=a[n-1]+a[n-2]+..+a[i].
    	prefixSum2记录数组 prefixSum1 的后缀和，prefixSum2即为 i 之后的点到第 i 个点的代价和。
    	*/
    	prefixSum1[n - 1] = a[n - 1];
    	for(int i = n - 2; i >= 0; i--) {
    		prefixSum1[i] = prefixSum1[i + 1] + a[i];
    	}
    	prefixSum2[n - 1] = 0;
    	for(int i = n - 2; i >= 0; i--) {
    		prefixSum2[i] = prefixSum2[i + 1] + prefixSum1[i + 1];
     	}

     	for(int i = 0; i < n; i++) {
     		cost[i] += prefixSum2[i];
     	}

     	/*
     	cost[i] 即为a数组中所有点到第 i 点的代价和
     	*/
    }

    bool haveZero(vector<vector<int>>& grid, int row, int column) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
};