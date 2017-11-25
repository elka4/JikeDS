package _06_BFS._BFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _407_BFS_Trapping_Rain_Water_II_H {
//Java solution using PriorityQueue

    public class Solution1 {

        public class Cell {
            int row;
            int col;
            int height;
            public Cell(int row, int col, int height) {
                this.row = row;
                this.col = col;
                this.height = height;
            }
        }

        public int trapRainWater(int[][] heights) {
            if (heights == null || heights.length == 0 || heights[0].length == 0)
                return 0;

            PriorityQueue<Cell> queue = new PriorityQueue<>(1, new Comparator<Cell>(){
                public int compare(Cell a, Cell b) {
                    return a.height - b.height;
                }
            });

            int m = heights.length;
            int n = heights[0].length;
            boolean[][] visited = new boolean[m][n];

            // Initially, add all the Cells which are on borders to the queue.
            for (int i = 0; i < m; i++) {
                visited[i][0] = true;
                visited[i][n - 1] = true;
                queue.offer(new Cell(i, 0, heights[i][0]));
                queue.offer(new Cell(i, n - 1, heights[i][n - 1]));
            }

            for (int i = 0; i < n; i++) {
                visited[0][i] = true;
                visited[m - 1][i] = true;
                queue.offer(new Cell(0, i, heights[0][i]));
                queue.offer(new Cell(m - 1, i, heights[m - 1][i]));
            }

            // from the borders, pick the shortest cell visited and check its neighbors:
            // if the neighbor is shorter, collect the water it can trap and update its height as its height plus the water trapped
            // add all its neighbors to the queue.
            int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            int res = 0;
            while (!queue.isEmpty()) {
                Cell cell = queue.poll();
                for (int[] dir : dirs) {
                    int row = cell.row + dir[0];
                    int col = cell.col + dir[1];
                    if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
                        visited[row][col] = true;
                        res += Math.max(0, cell.height - heights[row][col]);
                        queue.offer(new Cell(row, col, Math.max(heights[row][col], cell.height)));
                    }
                }
            }

            return res;
        }
    }

//------------------------------------------------------------------------------
    /*
    Alternative approach using Dijkstra in O(rc max(log r, log c)) time
This problem can also be solved in a more general approach way using Dijkstra.

Construct a graph G = (V, E) as follows:
V = all cells plus a dummy vertex, v, corresponding to the outside region.
If cell(i, j) is adjacent to cell(i', j'), then add an direct edge from (i, j) to (i', j') with weight height(i', j').
Add an edge with zero weight from any boundary cell to the dummy vertex v.

The weight of a path is defined as the weight of the heaviest edge along it. Then, for any cell (i, j), the height of water it can save is equal to the weight, denoted by dist(i, j), of the shortest path from (i, j) to v. (If the weight is less than or equal to height(i, j), no water can be accumulated at that particular position.)

We want to compute the dist(i, j) for all pairs of (i, j). Here, we have multiple sources and one destination, but this problem essentially can be solved using one pass of Dijkstra algorithm if we reverse the directions of all edges. The graph is sparse, i.e., there are O(rc) edges, resulting an O(rc log(rc)) = O(rc max(log r, log c)) runtime and using O(rc) space.
     */

    public class Solution2 {

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        List<int[]>[] g;
        int start;

        private int[] dijkstra() {
            int[] dist = new int[g.length];
            Arrays.fill(dist, Integer.MAX_VALUE / 2);
            dist[start] = 0;
            TreeSet<int[]> tree = new TreeSet<>((u, v) -> u[1] == v[1] ? u[0] - v[0] : u[1] - v[1]);
            tree.add(new int[]{start, 0});
            while (!tree.isEmpty()) {
                int u = tree.first()[0], d = tree.pollFirst()[1];
                for (int[] e : g[u]) {
                    int v = e[0], w = e[1];
                    if (Math.max(d, w) < dist[v]) {
                        tree.remove(new int[]{v, dist[v]});
                        dist[v] = Math.max(d, w);
                        tree.add(new int[]{v, dist[v]});
                    }
                }
            }
            return dist;
        }

        public int trapRainWater(int[][] a) {
            if (a == null || a.length == 0 || a[0].length == 0) return 0;
            int r = a.length, c = a[0].length;

            start = r * c;
            g = new List[r * c + 1];
            for (int i = 0; i < g.length; i++) g[i] = new ArrayList<>();
            for (int i = 0; i < r; i++)
                for (int j = 0; j < c; j++) {
                    if (i == 0 || i == r - 1 || j == 0 || j == c - 1) g[start].add(new int[]{i * c + j, 0});
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k], y = j + dy[k];
                        if (x >= 0 && x < r && y >= 0 && y < c) g[i * c + j].add(new int[]{x * c + y, a[i][j]});
                    }
                }

            int ans = 0;
            int[] dist = dijkstra();
            for (int i = 0; i < r; i++)
                for (int j = 0; j < c; j++) {
                    int cb = dist[i * c + j];
                    if (cb > a[i][j]) ans += cb - a[i][j];
                }

            return ans;
        }
    }
//------------------------------------------------------------------------------
    //Java solution beating 100%
/*My method is still based on PriorityQueue(heap). But it is combined with flood fill.
    Maintain a heap which contains the current walls, the boundary of the water pool.
    Every time we pick the lowest wall as a bar, then recursively travel from this wall to its neighbors to find if they can trap the water.
    If we meet a position which is lower than the bar, we trap some water, else we meet a new wall and put it into the heap.
    We can use a 2-D array to memorize the visited position and a member variable to records the water we have trapped.*/

    public class Solution3 {
        private class Cell implements Comparable<Cell> {
            private int row;
            private int col;
            private int value;
            public Cell(int r, int c, int v) {
                this.row = r;
                this.col = c;
                this.value = v;
            }
            @Override
            public int compareTo(Cell other) {
                return value - other.value;
            }
        }
        private int water;
        private boolean[][] visited1;
        public int trapRainWater(int[][] heightMap) {
            if (heightMap.length == 0) return 0;
            PriorityQueue<Cell> walls = new PriorityQueue<Cell>();
            water = 0;
            visited1 = new boolean[heightMap.length][heightMap[0].length];
            int rows = heightMap.length, cols = heightMap[0].length;
            //build wall;
            for (int c = 0; c < cols; c++) {
                walls.add(new Cell(0, c, heightMap[0][c]));
                walls.add(new Cell(rows - 1, c, heightMap[rows - 1][c]));
                visited1[0][c] = true;
                visited1[rows - 1][c] = true;
            }
            for (int r = 1; r < rows - 1; r++) {
                walls.add(new Cell(r, 0, heightMap[r][0]));
                walls.add(new Cell(r, cols - 1, heightMap[r][cols - 1]));
                visited1[r][0] = true;
                visited1[r][cols - 1] = true;
            }
            //end build wall;
            while(walls.size() > 0) {
                Cell min = walls.poll();
                visit(heightMap, min, walls);
            }
            return water;
        }
        private void visit(int[][] height, Cell start, PriorityQueue<Cell> walls) {
            fill(height, start.row + 1, start.col, walls, start.value);
            fill(height, start.row - 1, start.col, walls, start.value);
            fill(height, start.row, start.col + 1, walls, start.value);
            fill(height, start.row, start.col - 1, walls, start.value);
        }
        private void fill(int[][] height, int row, int col, PriorityQueue<Cell> walls, int min) {
            if (row < 0 || col < 0) return;
            else if (row >= height.length || col >= height[0].length) return;
            else if (visited1[row][col]) return;
            else if (height[row][col] >= min) {
                walls.add(new Cell(row, col, height[row][col]));
                visited1[row][col] = true;
                return;
            } else {
//        	System.out.println(row + ", " + col + " height = " + height[row][col] + ", bar = " + min);
                water += min - height[row][col];
                visited1[row][col] = true;
                fill(height, row + 1, col, walls, min);
                fill(height, row - 1, col, walls, min);
                fill(height, row, col + 1, walls, min);
                fill(height, row, col - 1, walls, min);
            }
        }
    }


//------------------------------------------------------------------------------
    //Java Solution with PriorityQueue
public class Solution4 {
    private class Point implements Comparable<Point>{
        int x, y;
        int h;
        public Point(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
        @Override
        public int compareTo(Point b) {
            return this.h - b.h;
        }
    }
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0) return 0;
        int m = heightMap.length, n = heightMap[0].length;
        Queue<Point> q = new PriorityQueue<>();
        boolean[][] vis = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            q.offer(new Point(i, 0, heightMap[i][0]));
            q.offer(new Point(i, n - 1, heightMap[i][n - 1]));
            vis[i][0] = vis[i][n - 1] = true;
        }
        for (int j = 1; j < n - 1; ++j) {
            q.offer(new Point(0, j, heightMap[0][j]));
            q.offer(new Point(m - 1, j, heightMap[m - 1][j]));
            vis[0][j] = vis[m - 1][j] = true;
        }
        int sum = 0;
        int max = 0;
        int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
        while (!q.isEmpty()) {
            Point p = q.poll();
            max = Math.max(max, p.h);
            sum += max - heightMap[p.x][p.y];
            for (int k = 0; k < dx.length; ++k) {
                int x = p.x + dx[k], y = p.y + dy[k];
                if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y]) {
                    q.offer(new Point(x, y, Math.max(max, heightMap[x][y])));
                    vis[x][y] = true;
                }
            }
        }
        return sum;
    }
}


//------------------------------------------------------------------------------

//    https://www.youtube.com/watch?v=cJayBq38VYw

//------------------------------------------------------------------------------
    // 9Ch


    class Jiuzhang {
        class Cell{
            public int x,y, h;
            Cell(){}
            Cell(int xx,int yy, int hh){
                x = xx;
                y = yy;
                h = hh;
            }
        }
        class CellComparator implements Comparator<Cell> {
            @Override
            public int compare(Cell x, Cell y)
            {
                if(x.h > y.h)
                    return 1;
                else if(x.h == y.h){
                    return 0;
                }
                else {
                    return -1;
                }
            }
        }

        int []dx = {1,-1,0,0};
        int []dy = {0,0,1,-1};
        public  int trapRainWater(int[][] heights) {
            // write your code here
            if(heights.length == 0)
                return 0;
            PriorityQueue<Cell> q =  new PriorityQueue<Cell>(new CellComparator());
            int n = heights.length;
            int m = heights[0].length;
            int [][]visit = new int[n][m];

            for(int i = 0; i < n; i++) {
                q.offer(new Cell(i,0,heights[i][0]));

                q.offer(new Cell(i,m-1,heights[i][m-1]));
                visit[i][0] = 1;
                visit[i][m-1] = 1;
            }
            for(int i = 0; i < m; i++) {
                q.offer(new Cell(0,i,heights[0][i]));

                q.offer(new Cell(n-1,i,heights[n-1][i]));
                visit[0][i] = 1;
                visit[n-1][i] = 1;

            }
            int ans = 0 ;
            while(!q.isEmpty()) {

                Cell now = q.poll();

                for(int i = 0; i < 4; i++) {

                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];
                    if(0<=nx && nx < n && 0 <= ny && ny < m && visit[nx][ny] == 0) {
                        visit[nx][ny] = 1;
                        q.offer(new Cell(nx,ny,Math.max(now.h,heights[nx][ny])));
                        ans = ans + Math.max(0,now.h - heights[nx][ny]);
                    }

                }
            }
            return ans;
        }
    }
//------------------------------------------------------------------------------
}
/*
Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

Note:
Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.
 */