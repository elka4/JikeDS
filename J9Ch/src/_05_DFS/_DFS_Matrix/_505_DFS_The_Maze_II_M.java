package _05_DFS._DFS_Matrix;
import java.util.*;

//  505. The Maze II
//  https://leetcode.com/problems/the-maze-ii/description/
public class _505_DFS_The_Maze_II_M {

    //https://leetcode.com/problems/the-maze-ii/solution/

    //Approach #1 Depth First Search [Accepted]
    public class Solution01 {
        public int shortestDistance(int[][] maze, int[] start, int[] dest) {
            int[][] distance = new int[maze.length][maze[0].length];
            for (int[] row: distance)
                Arrays.fill(row, Integer.MAX_VALUE);
            distance[start[0]][start[1]] = 0;
            dfs(maze, start, distance);
            return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
        }

        public void dfs(int[][] maze, int[] start, int[][] distance) {
            int[][] dirs={{0,1}, {0,-1}, {-1,0}, {1,0}};
            for (int[] dir: dirs) {
                int x = start[0] + dir[0];
                int y = start[1] + dir[1];
                int count = 0;
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (distance[start[0]][start[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[start[0]][start[1]] + count;
                    dfs(maze, new int[]{x - dir[0],y - dir[1]}, distance);
                }
            }
        }
    }

    //Approach #2 Using Breadth First Search [Accepted]
    public class Solution02 {
        public int shortestDistance(int[][] maze, int[] start, int[] dest) {
            int[][] distance = new int[maze.length][maze[0].length];
            for (int[] row: distance)
                Arrays.fill(row, Integer.MAX_VALUE);
            distance[start[0]][start[1]] = 0;
            int[][] dirs={{0, 1} ,{0, -1}, {-1, 0}, {1, 0}};
            Queue < int[] > queue = new LinkedList < > ();
            queue.add(start);
            while (!queue.isEmpty()) {
                int[] s = queue.remove();
                for (int[] dir: dirs) {
                    int x = s[0] + dir[0];
                    int y = s[1] + dir[1];
                    int count = 0;
                    while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                        x += dir[0];
                        y += dir[1];
                        count++;
                    }
                    if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                        distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
                        queue.add(new int[] {x - dir[0], y - dir[1]});
                    }
                }
            }
            return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
        }
    }

    //Approach #3 Using Dijkstra Algorithm [Accepted]
    public class Solution03 {
        public int shortestDistance(int[][] maze, int[] start, int[] dest) {
            int[][] distance = new int[maze.length][maze[0].length];
            boolean[][] visited = new boolean[maze.length][maze[0].length];
            for (int[] row: distance)
                Arrays.fill(row, Integer.MAX_VALUE);
            distance[start[0]][start[1]] = 0;
            dijkstra(maze, distance, visited);
            return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
        }
        public int[] minDistance(int[][] distance, boolean[][] visited) {
            int[] min={-1,-1};
            int min_val = Integer.MAX_VALUE;
            for (int i = 0; i < distance.length; i++) {
                for (int j = 0; j < distance[0].length; j++) {
                    if (!visited[i][j] && distance[i][j] < min_val) {
                        min = new int[] {i, j};
                        min_val = distance[i][j];
                    }
                }
            }
            return min;
        }
        public void dijkstra(int[][] maze, int[][] distance, boolean[][] visited) {
            int[][] dirs={{0,1},{0,-1},{-1,0},{1,0}};
            while (true) {
                int[] s = minDistance(distance, visited);
                if (s[0] < 0)
                    break;
                visited[s[0]][s[1]] = true;
                for (int[] dir: dirs) {
                    int x = s[0] + dir[0];
                    int y = s[1] + dir[1];
                    int count = 0;
                    while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                        x += dir[0];
                        y += dir[1];
                        count++;
                    }
                    if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                        distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
                    }
                }
            }
        }
    }


    //Approach #4 Using Dijkstra Algorithm and Priority Queue[Accepted]
    public class Solution04 {
        public int shortestDistance(int[][] maze, int[] start, int[] dest) {
            int[][] distance = new int[maze.length][maze[0].length];
            for (int[] row: distance)
                Arrays.fill(row, Integer.MAX_VALUE);
            distance[start[0]][start[1]] = 0;
            dijkstra(maze, start, distance);
            return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
        }

        public void dijkstra(int[][] maze, int[] start, int[][] distance) {
            int[][] dirs={{0,1},{0,-1},{-1,0},{1,0}};
            PriorityQueue < int[] > queue = new PriorityQueue < > ((a, b) -> a[2] - b[2]);
            queue.offer(new int[]{start[0],start[1],0});
            while (!queue.isEmpty()) {
                int[] s = queue.poll();
                if(distance[s[0]][s[1]] < s[2])
                    continue;
                for (int[] dir: dirs) {
                    int x = s[0] + dir[0];
                    int y = s[1] + dir[1];
                    int count = 0;
                    while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                        x += dir[0];
                        y += dir[1];
                        count++;
                    }
                    if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                        distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
                        queue.offer(new int[]{x - dir[0], y - dir[1], distance[x - dir[0]][y - dir[1]]});
                    }
                }
            }
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////
    public class Solution {
        class Point {
            int x,y,l;
            public Point(int _x, int _y, int _l) {x=_x;y=_y;l=_l;}
        }
        public int shortestDistance(int[][] maze, int[] start, int[] destination) {
            int m=maze.length, n=maze[0].length;
            int[][] length=new int[m][n]; // record length
            for (int i=0;i<m*n;i++) length[i/n][i%n]=Integer.MAX_VALUE;
            int[][] dir=new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
            PriorityQueue<Point> list=new PriorityQueue<>((o1,o2)->o1.l-o2.l); // using priority queue
            list.offer(new Point(start[0], start[1], 0));
            while (!list.isEmpty()) {
                Point p=list.poll();
                if (length[p.x][p.y]<=p.l) continue; // if we have already found a route shorter
                length[p.x][p.y]=p.l;
                for (int i=0;i<4;i++) {
                    int xx=p.x, yy=p.y, l=p.l;
                    while (xx>=0 && xx<m && yy>=0 && yy<n && maze[xx][yy]==0) {
                        xx+=dir[i][0];
                        yy+=dir[i][1];
                        l++;
                    }
                    xx-=dir[i][0];
                    yy-=dir[i][1];
                    l--;
                    list.offer(new Point(xx, yy, l));
                }
            }
            return length[destination[0]][destination[1]]==Integer.MAX_VALUE?-1:length[destination[0]][destination[1]];
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////
    public class Solution2 {
        private int[][] maze;
        private int[][] minSteps;//memorize the minimus steps to reach each position
        private int[][] dirs={{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int shortestDistance(int[][] maze, int[] start, int[] destination) {
            this.maze=maze;
            this.minSteps=new int[maze.length][maze[0].length];
        /*Initiallize minSteps matrix*/
            for(int i=0; i<maze.length; i++){
                for(int j=0; j<maze[0].length; j++){
                    minSteps[i][j]=Integer.MAX_VALUE;
                }
            }
        /*Optimization: check if the destination is impossible to Reach*/
            boolean desL=canRoll(destination[0], destination[1], dirs[0]);
            boolean desR=canRoll(destination[0], destination[1], dirs[1]);
            boolean desD=canRoll(destination[0], destination[1], dirs[2]);
            boolean desU=canRoll(destination[0], destination[1], dirs[3]);
            if(desL && desR && desD && desU) return -1; //all neighbors are walls
            else if(!(desL||desR||desD||desU)) return -1; //all neighbors are empty spaces
            else if(desL && desR && !desU && !desD) return -1; //two opposite neigbhors are walls, and the other two are empty spaces
            else if(!desL && !desR && desU && desD) return -1;//two opposite neigbhors are walls, and the other two are empty spaces

            minSteps[start[0]][start[1]]=0;
        /*BFS; Optimization: use PriorityQueue based on the steps instead of Queue*/
            PriorityQueue<Position> pq=new PriorityQueue<>();
            pq.offer(new Position(start[0], start[1], 0));
            while(!pq.isEmpty()){
                Position pos=pq.poll();
            /*optimization: if the destination is at the head of the queue, we are done*/
                if(pos.r==destination[0] && pos.c==destination[1]) return pos.steps;
                for(int[] dir: dirs){
                    int r=pos.r, c=pos.c, currSteps=0;
                    while(canRoll(r, c, dir)){
                        r+=dir[0];
                        c+=dir[1];
                        currSteps++;
                    }
                    int totalSteps=pos.steps+currSteps;
                    if(totalSteps<minSteps[r][c] && totalSteps<minSteps[destination[0]][destination[1]]){
                        minSteps[r][c]=totalSteps;
                        pq.offer(new Position(r, c, totalSteps));
                    }
                }
            }
        /*May not be able to reach the destination*/
            return minSteps[destination[0]][destination[1]]==Integer.MAX_VALUE? -1: minSteps[destination[0]][destination[1]];
        }

        private boolean canRoll(int r, int c, int[] dir){
            r+=dir[0];
            c+=dir[1];
            if(r<0 || c<0 || r>=maze.length || c>=maze[0].length || maze[r][c]==1) return false;
            return true;
        }
    }

    class Position implements Comparable<Position>{
        public int r;
        public int c;
        public int steps;

        public Position(int r, int c, int s){
            this.r=r;
            this.c=c;
            this.steps=s;
        }
        @Override
        public int compareTo(Position other){
            return this.steps-other.steps;
        }
    }

//////////////////////////////////////////////////////////////////////////////////////
}
/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: 12
Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1
Explanation: There is no way for the ball to stop at the destination.

Note:
There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */