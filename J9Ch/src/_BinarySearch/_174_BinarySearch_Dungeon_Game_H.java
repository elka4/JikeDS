package _BinarySearch;
import java.util.*;
import org.junit.Test;
public class _174_BinarySearch_Dungeon_Game_H {

//-------------------------------------------------------------------------///////////////////
    //jiuzhang
public class Jiuzhang {
    private boolean canSurvive(int health, int[][] dungeon) {
        int n = dungeon.length, m = dungeon[0].length;
        int[][] f = new int[n][m];

        f[0][0] = dungeon[0][0] + health;
        if (f[0][0] <= 0) {
            return false;
        }

        for (int i = 1; i < n; i++) {
            f[i][0] = f[i - 1][0] == Integer.MIN_VALUE
                    ? Integer.MIN_VALUE
                    : f[i - 1][0] + dungeon[i][0];
            if (f[i][0] <= 0) {
                f[i][0] = Integer.MIN_VALUE;
            }
        }
        for (int i = 1; i < m; i++) {
            f[0][i] = f[0][i - 1] == Integer.MIN_VALUE
                    ? Integer.MIN_VALUE
                    : f[0][i - 1] + dungeon[0][i];
            if (f[0][i] <= 0) {
                f[0][i] = Integer.MIN_VALUE;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                if (f[i][j] == Integer.MIN_VALUE) {
                    continue;
                }
                f[i][j] += dungeon[i][j];
                if (f[i][j] <= 0) {
                    f[i][j] = Integer.MIN_VALUE;
                }
            }
        }

        return f[n - 1][m - 1] > 0;
    }

    public int calculateMinimumHP(int[][] dungeon) {
        int start = 1, end = Integer.MAX_VALUE - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (canSurvive(mid, dungeon)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (canSurvive(start, dungeon)) {
            return start;
        }
        return end;
    }
}

}
/*
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.


Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2 (K)	-3	3
-5	-10	1
10	30	-5 (P)

Notes:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 */