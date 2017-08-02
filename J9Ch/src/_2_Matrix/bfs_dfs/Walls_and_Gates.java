package _2_Matrix.bfs_dfs;
import java.util.*;
//LeetCode – Walls and Gates (Java)

public class Walls_and_Gates {
    //Java Solution 1 - DFS

    public void wallsAndGates(int[][] rooms) {
        if(rooms==null || rooms.length==0||rooms[0].length==0)
            return;

        int m = rooms.length;
        int n = rooms[0].length;

        boolean[][] visited = new boolean[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(rooms[i][j]==0){
                    fill(rooms, i-1, j, 0, visited);
                    fill(rooms, i, j+1, 0, visited);
                    fill(rooms, i+1, j, 0, visited);
                    fill(rooms, i, j-1, 0, visited);
                    visited = new boolean[m][n];
                }
            }
        }
    }

    public void fill(int[][] rooms, int i, int j, int start, boolean[][] visited){
        int m=rooms.length;
        int n=rooms[0].length;

        if(i<0||i>=m||j<0||j>=n||rooms[i][j]<=0||visited[i][j]){
            return;
        }

        rooms[i][j] = Math.min(rooms[i][j], start+1);
        visited[i][j]=true;

        fill(rooms, i-1, j, start+1, visited);
        fill(rooms, i, j+1, start+1, visited);
        fill(rooms, i+1, j, start+1, visited);
        fill(rooms, i, j-1, start+1, visited);

        visited[i][j]=false;
    }
    //The DFS solution can be simplified as the following:

    public void wallsAndGates2(int[][] rooms) {
        if(rooms==null || rooms.length==0||rooms[0].length==0)
            return;

        int m = rooms.length;
        int n = rooms[0].length;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(rooms[i][j]==0){
                    fill(rooms, i, j, 0);
                }
            }
        }
    }

    public void fill(int[][] rooms, int i, int j, int distance){
        int m=rooms.length;
        int n=rooms[0].length;

        if(i<0||i>=m||j<0||j>=n||rooms[i][j]<distance){
            return;
        }

        rooms[i][j] = distance;

        fill(rooms, i-1, j, distance+1);
        fill(rooms, i, j+1, distance+1);
        fill(rooms, i+1, j, distance+1);
        fill(rooms, i, j-1, distance+1);
    }
    //Java Solution 2 - BFS

    public void wallsAndGates3(int[][] rooms) {
        if(rooms==null || rooms.length==0||rooms[0].length==0)
            return;

        int m = rooms.length;
        int n = rooms[0].length;

        LinkedList<Integer> queue = new LinkedList<Integer>();

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(rooms[i][j]==0){
                    queue.add(i*n+j);
                }
            }
        }

        while(!queue.isEmpty()){
            int head = queue.poll();
            int x=head/n;
            int y=head%n;

            if(x>0 && rooms[x-1][y]==Integer.MAX_VALUE){
                rooms[x-1][y]=rooms[x][y]+1;
                queue.add((x-1)*n+y);
            }

            if(x<m-1 && rooms[x+1][y]==Integer.MAX_VALUE){
                rooms[x+1][y]=rooms[x][y]+1;
                queue.add((x+1)*n+y);
            }

            if(y>0 && rooms[x][y-1]==Integer.MAX_VALUE){
                rooms[x][y-1]=rooms[x][y]+1;
                queue.add(x*n+y-1);
            }

            if(y<n-1 && rooms[x][y+1]==Integer.MAX_VALUE){
                rooms[x][y+1]=rooms[x][y]+1;
                queue.add(x*n+y+1);
            }
        }
    }

}
