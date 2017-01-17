package ch9S.chapter2_DataStructure1;

import java.util.*;

public class _04Number_of_Islands_II_2 {
public List<Integer> numIslands2(int m, int n, int[][] positions) {
    boolean[][] map = new boolean[m][n];
    int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    List<Integer> list = new ArrayList<Integer>();
    int island = 0;
    int[] fa = new int[m * n];
    
    //initialization
    for (int i = 0; i < m * n; i++) {
        fa[i] = i;
    }
    
    for (int i = 0; i < positions.length; i++) {
        island++;
        map[positions[i][0]][positions[i][1]] = true;
        int x, y;
        int f = positions[i][0] * n + positions[i][1];
        for (int k = 0; k < 4; k++) {
            x = positions[i][0] + dir[k][0];
            y = positions[i][1] + dir[k][1];
            if (x >= 0 && x < m && y >=0 && y < n && map[x][y]
            		&& getfather(fa, x * n + y) != f) {
                fa[getfather(fa, x * n + y)] = f;
                island--;
            }
        }
        list.add(island);
    }
    return list;
}

//disjoint-set and path compression
public int getfather(int[] fa, int i) {
    if (fa[i] == i) {
        return i;
    }
    fa[i] = getfather(fa, fa[i]);//path compression here
    return fa[i];
}
}

/*Given a n,m which means the row and column of the 2D matrix
 *  and an array of pair A( size k). Originally, the 2D matrix 
 *  is all 0 which means there is only sea in the matrix. 
 *  The list pair has k operator and each operator has two integer 
 *  A[i].x, A[i].y means that you can change 
 *  the grid matrix[A[i].x][A[i].y] from sea to island. 
 *  Return how many island are there in the matrix after each operator.
 

 Notice

0 is represented as the sea, 1 is represented as the island. 
If two 1 is adjacent, we consider them in the same island. 
We only consider up/down/left/right adjacent.

Have you met this question in a real interview? Yes
Example
Given n = 3, m = 3, array of pair A = [(0,0),(0,1),(2,2),(2,1)].

return [1,1,2,2].

Tags 
Google Union Find
Related Problems 
Medium Surrounded Regions 20 %
Easy Number of Islands 22 %
Medium Find the Weak Connected Component in the Directed Graph 22 %*/