package J_9_Dynamic_Programming.Optional_5;
import java.util.*;
/** 117 Jump Game II


 * Created by tianhuizhu on 6/28/17.
 */
public class _117_Jump_Game_II_Medium {

    // version 1: Dynamic Programming
// 这个方法，复杂度是 O(n^2)，会超时，但是依然需要掌握。
    public class Solution1 {
        public int jump(int[] A) {
            // state
            int[] steps = new int[A.length];

            // initialize
            steps[0] = 0;
            for (int i = 1; i < A.length; i++) {
                steps[i] = Integer.MAX_VALUE;
            }

            // function
            for (int i = 1; i < A.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (steps[j] != Integer.MAX_VALUE && j + A[j] >= i) {
                        steps[i] = Math.min(steps[i], steps[j] + 1);
                    }
                }
            }

            // answer
            return steps[A.length - 1];
        }
    }


    // version 2: Greedy
    public class Solution2 {
        public int jump(int[] A) {
            if (A == null || A.length == 0) {
                return -1;
            }
            int start = 0, end = 0, jumps = 0;
            while (end < A.length - 1) {
                jumps++;
                int farthest = end;
                for (int i = start; i <= end; i++) {
                    if (A[i] + i > farthest) {
                        farthest = A[i] + i;
                    }
                }
                start = end + 1;
                end = farthest;
            }
            return jumps;
        }
    }
}
