package _3Graph_PreClass;

import java.util.Comparator;
import java.util.PriorityQueue;

//time O(klogm)
//space O(m)
public class _2_KthSmallestinSortedMatrix_2_Heap_BestFirstSearch {
    public int kthSmallest(int[][] matrix, int k){
        if(matrix == null){
            return Integer.MIN_VALUE;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        Point cur = null;
        PriorityQueue<Point> minHeap = new PriorityQueue<Point>(
                k, new myComparator()
        );

        //offer the first col
        for (int i = 0; i < row; i++) {
            minHeap.offer(new Point(i, 0, matrix[i][0]));
        }


        //Only go right for next node
        while(k > 0){
            cur = minHeap.poll();
            if (cur.y + 1 < col){
                minHeap.offer(new Point(cur.x, cur.y + 1,
                        matrix[cur.x][cur.y + 1]));
            }
            k--;
        }


        return cur.val;
    }



    class Point {
        int x;
        int y;
        int val;

        Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    class myComparator implements Comparator<Point> {
        @Override
        public int compare (Point o1, Point o2) {
            if (o1.val == o2.val)
                return 0;
            return o1.val - o2.val;
        }
    }
}

