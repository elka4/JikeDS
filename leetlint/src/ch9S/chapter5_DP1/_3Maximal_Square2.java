package ch9S.chapter5_DP1;

public class _3Maximal_Square2 {
    
    public int maxSquare2(int[][] matrix) {
        // write your code here
        
    	
    	
    	int ans = 0;
        int n = matrix.length;
        int m;
        if(n > 0)
            m = matrix[0].length;
        else 
            return ans;
        int [][]res = new int [2][m];
        for(int i = 0; i < n; i++){
            res[i%2][0] = matrix[i][0];
            ans = Math.max(res[i%2][0] , ans);
            for(int j = 1; j < m; j++) {
                if(i > 0) {
                    if(matrix[i][j] > 0) {
                        res[i%2][j] = Math.min(res[(i - 1)%2][j],
                        		Math.min(res[i%2][j-1], res[(i-1)%2][j-1])) + 1;
                    } else {
                        res[i%2][j] = 0;
                    }
                    
                }
                else {
                    res[i%2][j] = matrix[i%2][j];
                }
                ans = Math.max(res[i%2][j], ans);
            }
        }
        return ans*ans;
    }
}
