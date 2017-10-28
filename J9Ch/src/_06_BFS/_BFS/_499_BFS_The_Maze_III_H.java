package _06_BFS._BFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _499_BFS_The_Maze_III_H {

//Similar to The Maze II. Easy-understanding Java bfs solution.
/*
Solution of The Maze: https://discuss.leetcode.com/topic/77471/easy-understanding-java-bfs-solution
Solution of The Maze II: https://discuss.leetcode.com/topic/77472/similar-to-the-maze-easy-understanding-java-bfs-solution

We just need to implement Comparable of Point, and record the route of every point.
 */


/*    Solution of The Maze: https://discuss.leetcode.com/topic/77471/easy-understanding-java-bfs-solution
    Solution of The Maze II: https://discuss.leetcode.com/topic/77472/similar-to-the-maze-easy-understanding-java-bfs-solution

    We just need to implement Comparable of Point, and record the route of every point.*/

    public class Solution {
        class Point implements Comparable<Point> {
            int x,y,l;
            String s;
            public Point(int _x, int _y) {x=_x;y=_y;l=Integer.MAX_VALUE;s="";}
            public Point(int _x, int _y, int _l,String _s) {x=_x;y=_y;l=_l;s=_s;}
            public int compareTo(Point p) {return l==p.l?s.compareTo(p.s):l-p.l;}
        }
        public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
            int m=maze.length, n=maze[0].length;
            Point[][] points=new Point[m][n];
            for (int i=0;i<m*n;i++) points[i/n][i%n]=new Point(i/n, i%n);
            int[][] dir=new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
            String[] ds=new String[] {"u","r","d","l"};
            PriorityQueue<Point> list=new PriorityQueue<>(); // using priority queue
            list.offer(new Point(ball[0], ball[1], 0, ""));
            while (!list.isEmpty()) {
                Point p=list.poll();
                if (points[p.x][p.y].compareTo(p)<=0) continue; // if we have already found a route shorter
                points[p.x][p.y]=p;
                for (int i=0;i<4;i++) {
                    int xx=p.x, yy=p.y, l=p.l;
                    while (xx>=0 && xx<m && yy>=0 && yy<n && maze[xx][yy]==0 && (xx!=hole[0] || yy!=hole[1])) {
                        xx+=dir[i][0];
                        yy+=dir[i][1];
                        l++;
                    }
                    if (xx!=hole[0] || yy!=hole[1]) { // check the hole
                        xx-=dir[i][0];
                        yy-=dir[i][1];
                        l--;
                    }
                    list.offer(new Point(xx, yy, l, p.s+ds[i]));
                }
            }
            return points[hole[0]][hole[1]].l==Integer.MAX_VALUE?"impossible":points[hole[0]][hole[1]].s;
        }
    }

/*
BFS:

Start from ball, adding 4 directions of it into queue(order by D->L->R->U), if it is not boundary or 1.
Poll one path from queue while the queue has path:
...a) if the path reach hole, return the path.
...b) follow the previous direction, adding it to queue.
...c) If the next cell on the direction is boundary or 1, adding other two directions into queue.
 */
    class Solution2{
        public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
            if(maze.length == 0) return "";
            int m = maze.length, n = maze[0].length;
            Queue<Cell> q = new LinkedList();

            int bx = ball[0], by = ball[1];
            if(!isTurn(maze, bx + 1, by)) q.offer(new Cell(bx + 1, by, "d"));
            turnLeftRight(q, maze, bx, by, "");
            if(!isTurn(maze, bx - 1, by)) q.offer(new Cell(bx - 1, by, "u"));

            while(!q.isEmpty()){
                Cell c = q.poll();
                if(c.x == hole[0] && c.y == hole[1]) return c.path;
                char d = c.path.charAt(c.path.length() - 1);

                switch(d){
                    case 'd':
                        if(!isTurn(maze, c.x + 1, c.y)){
                            q.offer(new Cell(c.x + 1, c.y, c.path));
                        } else {
                            if(maze[c.x][c.y] == -1) continue;
                            maze[c.x][c.y] = -1;
                            turnLeftRight(q, maze, c.x, c.y, c.path);
                        }
                        break;
                    case 'l':
                        if(!isTurn(maze, c.x, c.y - 1)){
                            q.offer(new Cell(c.x, c.y - 1, c.path));
                        } else {
                            if(maze[c.x][c.y] == -1) continue;
                            maze[c.x][c.y] = -1;
                            turnUpDown(q, maze, c.x, c.y, c.path);
                        }
                        break;
                    case 'r':
                        if(!isTurn(maze, c.x, c.y + 1)){
                            q.offer(new Cell(c.x, c.y + 1, c.path));
                        } else {
                            if(maze[c.x][c.y] == -1) continue;
                            maze[c.x][c.y] = -1;
                            turnUpDown(q, maze, c.x, c.y, c.path);
                        }
                        break;
                    case 'u':
                        if(!isTurn(maze, c.x - 1, c.y)){
                            q.offer(new Cell(c.x - 1, c.y, c.path));
                        } else {
                            if(maze[c.x][c.y] == -1) continue;
                            maze[c.x][c.y] = -1;
                            turnLeftRight(q, maze, c.x, c.y, c.path);
                        }
                        break;
                }
            }
            return "impossible";
        }

        void turnLeftRight(Queue<Cell> q, int[][] maze, int x, int y, String path){
            if(!isTurn(maze, x, y - 1)) q.offer(new Cell(x, y - 1, path + "l"));
            if(!isTurn(maze, x, y + 1)) q.offer(new Cell(x, y + 1, path + "r"));
        }

        void turnUpDown(Queue<Cell> q, int[][] maze, int x, int y, String path){
            if(!isTurn(maze, x + 1, y)) q.offer(new Cell(x + 1, y, path + "d"));
            if(!isTurn(maze, x - 1, y)) q.offer(new Cell(x - 1, y, path + "u"));
        }

        boolean isTurn(int[][] maze, int x, int y){
            return x < 0 || x >= maze.length || y < 0 || y >= maze[0].length || maze[x][y] == 1;
        }

        class Cell{
            int x, y;
            String path;
            public Cell(int xx, int yy, String p){
                x = xx;
                y = yy;
                path = p;
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////
//Clear Java Accepted DFS Solution with Explanation
public class Solution3 {
    int min; //min distance to hole
    String minS; //min distance's path string
    int[] hole;
    int[][] maze;
    int[][] map; //shortest distant traveling from ball to this point
    int[][] dirs = {{0,1},{-1,0},{1,0},{0,-1}}; //r, u, d, l
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        this.min = Integer.MAX_VALUE;
        this.minS = null;
        this.hole = hole;
        this.maze = maze;
        this.map = new int[maze.length][maze[0].length];
        for(int i = 0; i<map.length; i++) Arrays.fill(map[i], Integer.MAX_VALUE);

        move(ball[0], ball[1], 0, "", -1);
        return (minS==null) ? "impossible" : minS;
    }

    private void move(int r, int c, int cnt, String path, int dir){//dir is a index of dirs
        if(cnt > min || cnt > map[r][c]) return; //not a shortest route for sure
        if(dir!=-1){//if not from start point
            //add path
            if(dir==0) path+='r';
            else if(dir==1) path+='u';
            else if(dir==2) path+='d';
            else path+='l';

            //roll along dir
            while(r>=0 && r<maze.length && c>=0 && c<maze[0].length && maze[r][c]==0){
                map[r][c] = Math.min(map[r][c], cnt);
                if(r==hole[0] && c==hole[1]){//check hole
                    if(cnt==min && path.compareTo(minS)<0){
                        minS=path;
                    }else if(cnt<min){
                        min = cnt;
                        minS = path;
                    }
                    return;
                }
                r += dirs[dir][0];
                c += dirs[dir][1];
                cnt++;
            }
            r -= dirs[dir][0];//[r,c] is wall, need to walk back 1 step
            c -= dirs[dir][1];
            cnt--;
        }

        //hit wall (or start) -> try to turn
        for(int i = 0; i<dirs.length; i++){
            if(dir == i) continue;//dont keep going
            if(dir == (3-i)) continue;//dont go back
            int newR = r + dirs[i][0];
            int newC = c + dirs[i][1];
            if(newR>=0 && newR<maze.length && newC>=0 && newC<maze[0].length && maze[newR][newC]==0){//can go
                move(r, c, cnt, path, i);
            }
        }
    }
}
/*
Each time, first add the direction to the path, and then go with that direction, checking for hole along the way. When hit a wall, try to turn, and go with the new direction. For the starting point, don't "go", jump directly to "turn" part.
 */



//    Nice code. Here is my DFS solution.

    public class Solution4 {
        int minStep;
        int m, n;
        String res;
        int[][] dirs = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        String[] dirc = {"d", "r", "l", "u"}; // 0123
        public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
            this.m = maze.length;
            this.n = maze[0].length;
            this.minStep = Integer.MAX_VALUE;
            this.res = null;
            boolean[][] vis = new boolean[m][n];
            vis[ball[0]][ball[1]] = true;

            dfs(ball[0], ball[1], maze, hole, vis, "", 0);

            return res == null ? "impossible" : res;
        }

        private void dfs(int i, int j, int[][] maze, int[] hole, boolean[][] vis, String route, int step) {
            if (step > minStep) return;
            if (i == hole[0] && j == hole[1]) {
                if (step == minStep && route.compareTo(res) < 0) {
                    res = route;
                } else if (step < minStep) {
                    minStep = step;
                    res = route;
                }
                vis[i][j] = false;
                return;
            }

            for (int d = 0; d < 4; d++) {
                // roll to the wall
                int x = i, y = j;
                while (x + dirs[d][0] >= 0 && x + dirs[d][0] < m && y + dirs[d][1] >= 0 && y + dirs[d][1] < n
                        && maze[x + dirs[d][0]][y + dirs[d][1]] != 1) {
                    x += dirs[d][0];
                    y += dirs[d][1];
                    if (x == hole[0] && y == hole[1] || vis[x][y])  break;
                }
                if (!vis[x][y] && maze[x][y] == 0) {
                    vis[x][y] = true;
                    dfs(x, y, maze, hole, vis, route + dirc[d], step + Math.abs(x - i) + Math.abs(y - j));
                    vis[x][y] = false;
                }
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////

/*    I really appreciated your int[][] map and use index of int[][] dirs to represent r,u,l,d, it simplified many works for me.

    Below is my BFS solution.

    But I am curious about why BFS is slower than DFS, I think this BFS solution is similar with Dijkstra's algorithm, it cannot be that slow theoretically speaking. Can you give me some hints, or maybe my implementation is not good enough to speed this BFS solution up?

    Ignore what I just said... This dame line slow my solution System.out.println(next.route);. Actually we can see the outputs from this line, that there are much less outputs by BFS solution.*/

    public class Solution5 {
        int[][] dirs = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
        char[] dirc = {'d', 'l', 'r', 'u'};
        public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
            int m = maze.length, n = maze[0].length;
            String minS = null;
            int min = Integer.MAX_VALUE;

            int[][] map = new int[m][n]; // min distance till this node
            for (int i = 0; i < m; i++) Arrays.fill(map[i], Integer.MAX_VALUE);

            Node start = new Node(ball[0], ball[1], 0, ""); // start point
            PriorityQueue<Node> q = new PriorityQueue();
            q.add(start);

            boolean[][] vis = new boolean[m][n]; // visited nodes
            while (!q.isEmpty()) {
                // extract min, get the cur position
                Node cur = q.poll();
                vis[cur.x][cur.y] = true;
                // try 4 dirs
                for (int d = 0; d < 4; d++) {
                    int x = cur.x, y = cur.y;

                    // start point, or get the end point
                    while (x + dirs[d][0] < m && x + dirs[d][0] >= 0 && y + dirs[d][1] < n && y + dirs[d][1] >= 0
                            && maze[x + dirs[d][0]][y + dirs[d][1]] != 1) {
                        x += dirs[d][0];
                        y += dirs[d][1];
                        if (vis[x][y] || (x == hole[0] && y == hole[1])) break;
                    }
                    int step = cur.step + Math.abs(x - cur.x) + Math.abs(y - cur.y);
                    if (vis[x][y] || step > map[x][y]) continue;
                    // update distance
                    map[x][y] = step;
                    // next node
                    Node next = new Node(x, y, step, cur.route + dirc[d]);

                    // System.out.println(next.route);  /// this damn line!!! slowed my solution...

                    // reach the end
                    if (x == hole[0] && y == hole[1]) {
                        if (step == min && (minS == null || next.route.compareTo(minS) < 0)) {
                            minS = next.route;
                        } else if (step < min) {
                            min = step;
                            minS = next.route;
                        }
                        // if reach the end in this direction, we don't need to try other directions
                        break;
                    }

                    q.add(next);
                }
            }
            return minS == null ? "impossible" : minS;
        }

        class Node implements Comparable<Node> {
            int x, y, step;
            String route; // a string formed by directions along the way
            public Node(int x, int y, int step, String route) {
                this.x = x;
                this.y = y;
                this.step = step;
                this.route = route;
            }

            public boolean equals(Node a, Node b) {
                return a.x == b.x && a.y == b.y;
            }

            public int compareTo(Node that) {
                return this.step - that.step;
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////
//Java BFS solution with Queue, standard BFS 15ms(beats 85.71%)
public class Solution6 {
    public class Element {
        int direction;
        int row, col;
        String moves;

        Element(int row, int col, String moves, int direction) {
            this.row = row;
            this.col = col;
            this.moves = moves;
            this.direction = direction;
        }
    }

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        //initialization
        int m = maze.length, n = maze[0].length;
        Queue<Element> path = new LinkedList<>();
        char[] directions = {'d', 'l', 'r', 'u'};
        int[] deltaRow = {1, 0, 0, -1};
        int[] deltaCol = {0, -1, 1, 0};
        boolean[][][] visited = new boolean[m][n][4];

        //add start point
        for (int i = 0; i < 4; i++) {
            int row = ball[0] + deltaRow[i], col = ball[1] + deltaCol[i];
            if (row >= 0 && row < m && col >= 0 && col < n && maze[row][col] == 0) {
                path.add(new Element(row, col, String.valueOf(directions[i]), i));
            }
        }

        while (!path.isEmpty()) {
            Element top = path.poll();
            visited[top.row][top.col][top.direction] = true;
            if (top.row == hole[0] && top.col == hole[1]) {
                return top.moves;
            }
            //go with same direction
            int nextRow = top.row + deltaRow[top.direction];
            int nextCol = top.col + deltaCol[top.direction];
            if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n && maze[nextRow][nextCol] == 0) {
                //no hit wall
                if (!visited[nextRow][nextCol][top.direction]) {
                    path.offer(new Element(nextRow, nextCol, top.moves, top.direction));
                }
            } else {
                //hit the wall, change direction
                for (int direction = 0; direction < 4; direction++) {
                    if (direction != top.direction) {
                        nextRow = top.row + deltaRow[direction];
                        nextCol = top.col + deltaCol[direction];
                        if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n && maze[nextRow][nextCol] == 0
                                && !visited[nextRow][nextCol][direction]) {
                            path.offer(new Element(nextRow, nextCol, top.moves + directions[direction], direction));
                        }
                    }
                }
            }
        }
        return "impossible";
    }
}


////////////////////////////////////////////////////////////////////////////////////////////////
//Short,clean and straight forward BFS solution with PriorityQueue
/*The idea is just using BFS with a PriorityQueue(dijkstra's algorithm), PriorityQueue polls out the Coordinate with the minimum distance, if there are two with same distance, we compare their lexicographical order, by this way, we can ensure that we get the lexicographically smallest way in the end.*/

    public class Solution7 {
        class Coordinate implements Comparable<Coordinate> {
            int x, y, dist;
            String moves;

            public Coordinate(int x, int y, int dist, String moves) {
                this.x = x;
                this.y = y;
                this.dist = dist;
                this.moves = moves;
            }

            public int compareTo(Coordinate that) {
                if (this.dist != that.dist)     return this.dist - that.dist;
                return this.moves.compareTo(that.moves);
            }
        }

        int[][] dirs = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
        char[] dirc = {'d', 'l', 'r', 'u'};

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length;

        boolean[][] visited = new boolean[m][n];

        PriorityQueue<Coordinate> pq = new PriorityQueue<>();
        pq.add(new Coordinate(ball[0], ball[1], 0, ""));

        while(!pq.isEmpty()) {
            Coordinate curr = pq.poll();

            if (curr.x == hole[0] && curr.y == hole[1]) {
                return curr.moves;
            }

            if (!visited[curr.x][curr.y]) {
                visited[curr.x][curr.y] = true;
                for (int direction = 0; direction < 4; direction++) {
                    Coordinate next = moveForward(maze, curr, direction, hole);
                    pq.add(new Coordinate(next.x, next.y, next.dist, next.moves + dirc[direction]));
                }
            }
        }
        return "impossible";
    }

    /*
        Start from current position move forward in one direction until hit the wall, return the last position before hitting the wall
    */
    private Coordinate moveForward(int[][] maze, Coordinate curr, int direction, int[] hole) {
        int m = maze.length, n = maze[0].length;
        int nx = curr.x, ny = curr.y, dis = curr.dist;
        while (nx >= 0 && nx < m && ny >= 0 && ny < n && maze[nx][ny] == 0) {
            nx += dirs[direction][0];
            ny += dirs[direction][1];
            dis++;
            if (nx == hole[0] && ny == hole[1]) {
                return new Coordinate(nx, ny, dis, curr.moves);
            }
        }
        // back up one step from wall
        nx -= dirs[direction][0];
        ny -= dirs[direction][1];
        dis--;
        return new Coordinate(nx, ny, dis, curr.moves);
    }
}


////////////////////////////////////////////////////////////////////////////////////////////////



}
/*
Example 1

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (0, 1)

Output: "lul"
Explanation: There are two shortest ways for the ball to drop into the hole.
The first way is left -> up -> left, represented by "lul".
The second way is up -> left, represented by 'ul'.
Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l'
 */

/*
Example 2

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (3, 0)
Output: "impossible"
Explanation: The ball cannot reach the hole.
 */