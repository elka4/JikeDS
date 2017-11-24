package _06_BFS._BFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _505_BFS_The_Maze_II_M {

//Similar to The Maze. Easy-understanding Java bfs solution.

//    Solution of The Maze: https://discuss.leetcode.com/topic/77471/easy-understanding-java-bfs-solution
//    Solution of The Maze III: https://discuss.leetcode.com/topic/77474/similar-to-the-maze-ii-easy-understanding-java-bfs-solution
//
//    We need to use PriorityQueue instead of standard queue, and record the minimal length of each point.

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
//    Modified 2017/3/27:
//    Why using PriorityQueue?
//
//    We can consider this question as a shortest-route graph problem, that is, each stoppable point is a vertical, where every possible path between two nodes is an edge.
//    In this way, we can using Dijkstra algorithm to solve this problem, and that's why we use PriorityQueue.


//Java BFS with Some optimizations, 19 ms (with comments)
//The idea is similar to the Dijkstra's shortest Path Algorithm.

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

//-------------------------------------------------------------------------/////

//    BFS intuitive Java solution
/*
 Jerry
Reputation:  747
My answer is not short, but easy to understand. The basic idea is simple:
According to the rule of the question, the ball will start rolling from start. So it will roll to any directions if there is no wall and not boundary.

If it is starting rolling, it will not stop until hitting a wall or boundary. So we need to record the direction of rolling in the Node object, then we will first try the direction of last movement.

While it hitting a wall or boundary, we will try the other two directions. At the same time, we mark this turning point as visited.

There is another rule, so we only check if is the destination when it is a turning point.

After we traverse all the paths in the maze, if the destination cannot be found on any turning point, we just return -1.


 */
class Solution3{
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {

        int m = maze.length;
        if(m ==  0) return -1;
        int n = maze[0].length, level = 0;

        Queue<Node> q = new LinkedList();
        Node snode = new Node(start[0],  start[1]), dnode = new Node(destination[0], destination[1]);
        Set<Node> visited = new HashSet<>();
        q.offer(snode);
        visited.add(snode);
        while(!q.isEmpty()){

            int size = q.size();
            for(int i = 0; i < size; i++){
                Node node = q.poll();
                String chooseDirection = null;
                if("left".equals(node.direction)){
                    if(!offerNode(maze, node.x - 1, node.y, m, n, q, "left")) chooseDirection = "left";
                } else if("right".equals(node.direction)){
                    if(!offerNode(maze, node.x + 1, node.y, m, n, q, "right")) chooseDirection = "right";
                } else if("up".equals(node.direction)){
                    if(!offerNode(maze, node.x, node.y + 1, m, n, q, "up")) chooseDirection = "up";
                } else if("down".equals(node.direction)){
                    if(!offerNode(maze, node.x, node.y - 1, m, n, q, "down")) chooseDirection = "down";
                } else {
                    offerNode(maze, node.x + 1, node.y, m, n, q, "right");
                    offerNode(maze, node.x - 1, node.y, m, n, q, "left");
                    offerNode(maze, node.x, node.y + 1, m, n, q, "up");
                    offerNode(maze, node.x, node.y - 1, m, n, q, "down");
                }

                if(chooseDirection != null){
                    if(dnode.equals(node)) return level;
                    if(visited.contains(node)) continue;
                    visited.add(node);
                    if(chooseDirection.equals("left") || chooseDirection.equals("right")) {
                        offerNode(maze, node.x, node.y + 1, m, n, q, "up");
                        offerNode(maze, node.x, node.y - 1, m, n, q, "down");
                    }  else {
                        offerNode(maze, node.x + 1, node.y, m, n, q, "right");
                        offerNode(maze, node.x - 1, node.y, m, n, q, "left");
                    }
                }

            }
            level++;
        }
        return -1;
    }

    private boolean offerNode(int[][] maze, int x, int y, int m, int n, Queue<Node> q, String direction) {
        Node node = new Node(x, y);
        node.direction = direction;
        if(0 <= x && x < m && 0 <= y && y < n && maze[x][y] == 0) {
            q.offer(node);
            return true;
        } else return false;
    }

    class Node{
        String direction;
        int x, y;
        Node(int xx, int yy){
            x = xx;
            y = yy;
        }

        @Override
        public int hashCode() {
            return x * 31 + y;
        }

        @Override
        public boolean equals(Object nn){
            Node n = (Node) nn;
            return (n.x == x) && (n.y == y);
        }
    }
}


//-------------------------------------------------------------------------/
//clean java bfs solution
    public class Solution4 {
        class point{
            int y;
            int x;
            int step;
            point(int y, int x, int step){
                this.y = y;
                this.x = x;
                this.step = step;
            }
        }

        public int shortestDistance(int[][] maze, int[] start, int[] destination) {
            int h = maze.length;
            int w = maze[0].length;
            int[][] matrix = new int[h+2][w+2];
            int[][] distances = new int[h+2][w+2];


            for(int i=0;i<h+2;i++){
                for(int j=0;j<w+2;j++){
                    matrix[i][j]=1;

                }
            }

            for(int i=1;i<h+1;i++){
                for(int j=1;j<w+1;j++){
                    matrix[i][j]=maze[i-1][j-1];
                    distances[i][j]=99999;
                }
            }
            int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
            Queue<point> qq = new LinkedList<point>();

            qq.add(new point(start[0]+1, start[1]+1, 0));

            while(!qq.isEmpty()){
                point temp = qq.poll();

                if(temp.step >= distances[temp.y][temp.x]){
                    continue;
                }

                distances[temp.y][temp.x]=temp.step;

                for(int[]dir:dirs){
                    int tpy = temp.y;
                    int tpx = temp.x;
                    int tpcurstep = temp.step;
                    while(matrix[tpy+dir[0]][tpx+dir[1]]!=1){
                        tpy +=dir[0];
                        tpx +=dir[1];
                        tpcurstep++;
                    }
                    qq.add(new point(tpy,tpx,tpcurstep));

                }

            }
            return distances[destination[0]+1][destination[1]+1]==99999?
                    -1:distances[destination[0]+1][destination[1]+1];
        }
    }
//-------------------------------------------------------------------------//
//Simple Java Solution BFS
class Solution5{
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        Queue<int[]> q = new LinkedList<>();
        int m = maze.length, n = maze[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        int[] dx = new int[] {-1, 0, 1, 0};
        int[] dy = new int[] { 0, 1, 0, -1};

        q.offer(start);
        dist[start[0]][start[1]] = 0;

        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int i = 0; i < 4; i++) {
                int x = p[0] + dx[i], y = p[1] + dy[i];
                int cnt = 1;

                while (x >=0 && x < m && y >= 0 && y < n && maze[x][y] != 1) {
                    x += dx[i];
                    y += dy[i];
                    cnt++;
                }
                x -= dx[i];
                y -= dy[i];
                cnt--;
                if (dist[p[0]][p[1]] + cnt < dist[x][y]) {
                    dist[x][y] = dist[p[0]][p[1]] + cnt;
                    q.offer(new int[] {x, y});
                }
            }
        }
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ?
                -1 : dist[destination[0]][destination[1]];
    }
}

//------------------------------------------------------------------------------///////
    //JAVA, Accepted DFS
    class Solution6{
        final int[] DIRS = {0, 1, 0, -1, 0};
        public int shortestDistance(int[][] maze, int[] start, int[] destination) {
            int[][] dist = new int[maze.length][maze[0].length];
            dist[start[0]][start[1]] = 1;
            dfs(start[0], start[1], destination, maze, dist);
            return dist[destination[0]][destination[1]] - 1;
        }
        void dfs(int row, int col, int[] dest, int[][] maze, int[][] dist) {
            if (row == dest[0] && col == dest[1]) return;
            int m = maze.length, n = maze[0].length;
            for (int d = 0; d < 4; ++d) {
                int i = row, j = col, p = DIRS[d], q = DIRS[d + 1], len = dist[row][col];
                while (i + p >= 0 && i + p < m && j + q >= 0 && j + q < n && maze[i + p][j + q] == 0) {
                    i += p;
                    j += q;
                    len++;
                }
                if (dist[i][j] > 0 && len >= dist[i][j]) continue;
                dist[i][j] = len;
                dfs(i, j, dest, maze, dist);
            }
        }
    }

//------------------------------------------------------------------------------///////

}
/*
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
 */


/*
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
 */