package J_4_Breadth_First_Search.Required_10;
import java.util.*;import lib.TreeNode;import lib.AssortedMethods;import org.junit.Test;
/** 598. Zombie in Matrix
 * Medium

 * Created by tianhuizhu on 6/27/17.
 */
public class _598_Zombie_in_Matrix {

    class Coordinate {
        int x, y;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public int PEOPLE = 0;
    public int ZOMBIE = 1;
    public int WALL = 2;

    public int[] deltaX = {1, 0, 0, -1};
    public int[] deltaY = {0, 1, -1, 0};

    /**
     * @param grid a 2D integer grid
     * @return an integer
     */
    public int zombie(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int n = grid.length;
        int m = grid[0].length;

        // initialize the queue & count people
        int people = 0;
        Queue<Coordinate> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == PEOPLE) {
                    people++;
                } else if (grid[i][j] == ZOMBIE) {
                    queue.offer(new Coordinate(i, j));
                }
            }
        }

        // corner case
        if (people == 0) {
            return 0;
        }

        // bfs
        int days = 0;
        while (!queue.isEmpty()) {
            days++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Coordinate zb = queue.poll();
                for (int direction = 0; direction < 4; direction++) {
                    Coordinate adj = new Coordinate(
                            zb.x + deltaX[direction],
                            zb.y + deltaY[direction]
                    );

                    if (!isPeople(adj, grid)) {
                        continue;
                    }

                    grid[adj.x][adj.y] = ZOMBIE;
                    people--;
                    if (people == 0) {
                        return days;
                    }
                    queue.offer(adj);
                }
            }
        }

        return -1;
    }

    private boolean isPeople(Coordinate coor, int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        if (coor.x < 0 || coor.x >= n) {
            return false;
        }
        if (coor.y < 0 || coor.y >= m) {
            return false;
        }
        return (grid[coor.x][coor.y] == PEOPLE);
    }

/*
0 1 2 0 0
1 0 0 2 1
0 1 0 0 0
return 2
 */
    @Test
    public void test01(){
        int[][] edges = {
                {0,1,2,0,0},
                {1,0,0,2,1},
                {0,1,0,0,0},
        };

        System.out.println(zombie(edges));
    }
}
