package _02_Matrix.bfs_dfs;

/*
LeetCode – Number of Islands (Java)

Given a 2-d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000

Answer: 1
 */


public class Number_of_Islands {

    /*Java Solution 1 - DFS

    The basic idea of the following solution is merging adjacent lands,
    and the merging should be done recursively.
    Each element is visited once only. So time is O(m*n).*/

    public int numIslands(char[][] grid) {
        if(grid==null || grid.length==0||grid[0].length==0)
            return 0;

        int m = grid.length;
        int n = grid[0].length;

        int count=0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]=='1'){
                    count++;
                    merge(grid, i, j);
                }
            }
        }

        return count;
    }

    public void merge(char[][] grid, int i, int j){
        int m=grid.length;
        int n=grid[0].length;

        if(i<0||i>=m||j<0||j>=n||grid[i][j]!='1')
            return;

        grid[i][j]='X';

        merge(grid, i-1, j);
        merge(grid, i+1, j);
        merge(grid, i, j-1);
        merge(grid, i, j+1);
    }

//---------------------------------/////////////

/*    Java Solution 2 - Union-Find

    Time is O(m*n*log(k)).*/

    public int numIslands2(char[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0)
            return 0;

        int m = grid.length;
        int n = grid[0].length;

        int[] dx={-1, 1, 0, 0};
        int[] dy={0, 0, -1, 1};

        int[] root = new int[m*n];

        int count=0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]=='1'){
                    root[i*n+j] = i*n+j;
                    count++;
                }
            }
        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]=='1'){
                    for(int k=0; k<4; k++){
                        int x = i+dx[k];
                        int y = j+dy[k];

                        if(x>=0&&x<m&&y>=0&&y<n&&grid[x][y]=='1'){
                            int cRoot = getRoot(root, i*n+j);
                            int nRoot = getRoot(root, x*n+y);
                            if(nRoot!=cRoot){
                                //update previous node's root to be current
                                root[cRoot]=nRoot;
                                count--;
                            }

                        }
                    }
                }
            }
        }

        return count;
    }

    public int getRoot(int[] arr , int i){
        while(arr[i]!=i){
            i = arr[arr[i]];
        }

        return i;
    }
//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///
}
