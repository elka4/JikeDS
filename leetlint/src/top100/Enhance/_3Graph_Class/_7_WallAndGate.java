package top100.Enhance._3Graph_Class;

import java.util.*;

public class _7_WallAndGate {
public void wallsAndGates_BFS(int[][] rooms) {
    if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
        return;
    }
    for (int i = 0; i < rooms.length; ++i) {
        for (int j = 0; j < rooms[0].length; ++j) {
            if (rooms[i][j] == 0) {
                bfsHelper(rooms, i, j);
            }
        }
    }
}

private void bfsHelper(int[][] rooms, int i, int j) {
    int row = rooms.length;
    int col = rooms[0].length;
    Deque<int[]> queue = new LinkedList<int[]>();
    queue.offer(new int[]{i, j}); // Put gate in the queue
    while (!queue.isEmpty()) {
        int[] cur = queue.pollFirst();
        int x = cur[0];
        int y = cur[1];        
        if (isValid(x + 1, y, row, col) && rooms[x + 1][y] > rooms[x][y] + 1) {
           rooms[x + 1][y] = rooms[x][y] + 1;
           queue.offerLast(new int[]{x + 1, y});
        }
        if (isValid(x, y + 1, row, col) && rooms[x][y + 1] > rooms[x][y] + 1) {
           rooms[x][y + 1] = rooms[x][y] + 1;
           queue.offerLast(new int[]{x, y + 1});
        }
        if (isValid(x - 1, y, row, col) && rooms[x - 1][y] > rooms[x][y] + 1) {
           rooms[x - 1][y] = rooms[x][y] + 1;
           queue.offerLast(new int[]{x - 1, y});
        }
        if (isValid(x, y - 1, row, col) && rooms[x][y - 1] > rooms[x][y] + 1) {
           rooms[x][y - 1] = rooms[x][y] + 1;
           queue.offerLast(new int[]{x, y - 1});
        }
    }
}
private boolean isValid(int x, int y, int row, int col) {
  return x >= 0 && y >= 0 && x < row && y < col;
}
public void wallsAndGates_MultiEndBFS(int[][] rooms) {
    if (rooms == null || rooms.length == 0 ||
                  rooms[0] == null || rooms[0].length == 0) {
        return;
    }
    Deque<int[]> queue = new LinkedList<int[]>();
    for (int i = 0; i < rooms.length; ++i) {
        for (int j = 0; j < rooms[0].length; ++j) {
            if (rooms[i][j] == 0) {
                //Put all gates into queue
                queue.offerLast(new int[]{i, j});
            }
        }
    }
    multiEndbfsHelper(queue, rooms);
}
private void multiEndbfsHelper(Deque<int[]> queue, int[][] rooms) {
  int row = rooms.length;
  int col = rooms[0].length;
  while (!queue.isEmpty()) {
      int[] cur = queue.pollFirst();
      int x = cur[0];
      int y = cur[1];
      if (isValid(x + 1, y, row, col) && rooms[x + 1][y] == Integer.MAX_VALUE) {
         rooms[x + 1][y] = rooms[x][y] + 1;
         queue.offerLast(new int[]{x + 1, y});
      }
      if (isValid(x, y + 1, row, col) && rooms[x][y + 1] == Integer.MAX_VALUE) {
         rooms[x][y + 1] = rooms[x][y] + 1;
         queue.offerLast(new int[]{x, y + 1});
      }

      if (isValid(x - 1, y, row, col) && rooms[x - 1][y] == Integer.MAX_VALUE) {
         rooms[x - 1][y] = rooms[x][y] + 1;
         queue.offerLast(new int[]{x - 1, y});
      }
      if (isValid(x, y - 1, row, col) && rooms[x][y - 1] == Integer.MAX_VALUE) {
         rooms[x][y - 1] = rooms[x][y] + 1;
         queue.offerLast(new int[]{x, y - 1});
      }
  }
}
}
