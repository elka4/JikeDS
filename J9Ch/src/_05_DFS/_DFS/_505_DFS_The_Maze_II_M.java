package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _505_DFS_The_Maze_II_M {
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
//-------------------------------------------------------------------------///




//-------------------------------------------------------------------------///






}
/*

 */