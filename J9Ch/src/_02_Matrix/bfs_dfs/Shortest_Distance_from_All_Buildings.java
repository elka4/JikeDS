package _02_Matrix.bfs_dfs;

import java.util.LinkedList;

/*
LeetCode – Shortest Distance from All Buildings (Java)

You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.

For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2). The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
 */


public class Shortest_Distance_from_All_Buildings {

    /*
    This problem can be solve by BFS. We define one matrix for
    tracking the distance from each building, and another matrix
    for tracking the number of buildings which can be reached.
     */

    int[][] numReach;
    int[][] distance;

    public int shortestDistance(int[][] grid) {
        if(grid==null||grid.length==0||grid[0].length==0)
            return 0;

        int m = grid.length;
        int n = grid[0].length;

        numReach = new int[m][n];
        distance = new int[m][n];


        int numBuilding = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==1){
                    boolean[][] visited = new boolean[m][n];
                    LinkedList<Integer> queue = new LinkedList<Integer>();
                    dfs(grid, i, j, i, j, 0, visited, queue);

                    numBuilding++;
                }
            }
        }

        int result=Integer.MAX_VALUE;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 0 && numReach[i][j]==numBuilding){
                    result = Math.min(result, distance[i][j]);

                }
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public void dfs(int[][] grid, int ox, int oy, int i, int j,
                    int distanceSoFar, boolean[][] visited,
                    LinkedList<Integer> queue){

        visit(grid, i, j, i, j, distanceSoFar, visited, queue);
        int n = grid[0].length;

        while(!queue.isEmpty()){
            int size = queue.size();
            distanceSoFar++;

            for(int k=0; k<size; k++){
                int top = queue.poll();
                i=top/n;
                j=top%n;

                visit(grid, ox, oy, i-1, j, distanceSoFar, visited, queue);
                visit(grid, ox, oy, i+1, j, distanceSoFar, visited, queue);
                visit(grid, ox, oy, i, j-1, distanceSoFar, visited, queue);
                visit(grid, ox, oy, i, j+1, distanceSoFar, visited, queue);
            }

        }
    }

    public void visit(int[][] grid, int ox, int oy,  int i, int j,
                      int distanceSoFar, boolean[][] visited,
                      LinkedList<Integer> queue){
        int m = grid.length;
        int n = grid[0].length;

        if(i<0 || i>=m || j<0 || j>=n || visited[i][j])
            return;

        if((i!=ox || j!=oy) && grid[i][j]!=0){
            return;
        }


        visited[i][j]=true;
        numReach[i][j]++;
        distance[i][j]+= distanceSoFar;
        queue.offer(i*n+j);
    }


//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///

}
