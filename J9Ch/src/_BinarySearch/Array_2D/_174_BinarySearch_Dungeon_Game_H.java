package _BinarySearch.Array_2D;

//  174. Dungeon Game
//  https://leetcode.com/problems/dungeon-game/description/
//  Binary Search, Dynamic Programming
//  Unique Paths
//  Minimum Path Sum
//  5:
public class _174_BinarySearch_Dungeon_Game_H {

//-----------------------------------------------------------------------------

    // 9Ch
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

//-----------------------------------------------------------------------------
/*  http://bookshadow.com/weblog/2015/01/07/leetcode-dungeon-game/
题目大意：
恶魔抓走了公主(P)并把她囚禁在地牢的右下角。地牢包含M x N个房间，排列成一个二维的格子。我们英勇的骑士(K)一开始位于左上角的房间里，需要一路披荆斩棘营救公主。

骑士拥有一个正整数的初始生命值。如果在任何一点其生命值≤0，他立刻会死去。

一些房间由恶魔守卫着，因此当骑士进入这些房间时就会损失生命值（负整数）；其他房间或者是空的（数值为0），或者包含一些魔力宝珠（magic orbs）可以增加骑士的生命值（正整数）。

为了尽可能快的解救公主，骑士决定每一步只向右或者向下移动。

编写一个函数决定骑士的最小初始生命值，确保他可以成功营救公主。

例如，给定下面的地牢，骑士的初始生命值至少应当为7，如果他按照下面的最优路线行进：右 -> 右 -> 下 -> 下.

-2 (K)    -3    3
-5    -10    1
10    30    -5 (P)

备注：

骑士的生命值没有上界

任何房间都可能包含威胁或者补给，即使骑士进入的第一个房间或者囚禁公主的最后一个房间也一样。

解题思路：
乍一看，这个问题和"Maximum/Minimum Path Sum"问题很相似。然而，具有全局最大HP（生命值）收益的路径并不一定可以保证最小的初始HP，因为题目中具有限制条件：HP不能≤0。例如，考虑下面的两条路径：0 -> -300 -> 310 -> 0 和 0 -> -1 -> 2 -> 0。这两条路径的净HP收益分别是-300 + 310 = 10 与 -1 + 2 = 1。第一条路径的净收益更高，但是它所需的初始HP至少是301，才能抵消第二个房间的-300HP损失，而第二条路径只需要初始HP为2就可以了。

幸运的是，这个问题可以通过“table-filling”（表格填充）动态规划算法解决，与其他"grid walking"（格子行走）问题类似：

首先，我们应该维护一个2维数组D，与地牢数组的大小相同，其中D[i][j]代表可以保证骑士在进入房间(i,j)之前，探索其余地牢时能够存活下来的最小HP。显然D[0][0]就是我们随后需要的最终答案。因此，对于这个问题，我们需要从右下角到左上角填充表格。

然后，我们来计算离开房间(i,j)时的最小HP。从这一点出发只有两条路径可选：(i + 1, j)和(i, j + 1)。当然我们会选择拥有更小D值的那个房间，换言之，骑士完成剩下的旅途所需的较小HP。因此我们有：

  min_HP_on_exit = min(D[i+1][j], D[i][j+1])

现在D[i][j]可以通过dungeon[i][j]和min_HP_on_exit，根据下面的情景得出：

如果dungeon[i][j] == 0，那么在这个房间里很安全。 骑士离开这个房间时的HP和他进入房间时的HP保持一致， 也就是说 D[i][j] = min_HP_on_exit.

如果dungeon[i][j] < 0，那么骑士在进入该房间之前的HP > 离开房间时的HP，min_HP_on_exit才能抵消他在该房间中的HP损失。 最小HP花费就是 "-dungeon[i][j]"， 因此我们有公式 D[i][j] = min_HP_on_exit - dungeon[i][j].

如果dungeon[i][j] > 0, 那么骑士在进入房间(i, j) 时的HP只需为min_HP_on_exit - dungeon[i][j]，因为他可以在该房间内获得数值为"dungeon[i][j]"的HP收益。 不过，这种情况下min_HP_on_exit - dungeon[i][j]的数值可能≤0。 此时，我们需要把值置为1以确保D[i][j]为正整数: D[i][j] = max(min_HP_on_exit - dungeon[i][j], 1)。

注意 dungeon[i][j] > 0 条件下的等式实际上可以覆盖其他两种情况。 因此我们可以把三种情况归纳为同一个等式： 亦即:

D[i][j] = max(min_HP_on_exit - dungeon[i][j], 1)
dungeon[i][j]可以为任意值。

D[0][0]就是最终答案。 此外，像许多其他"table-filling"问题一样，二维数组D可以用一维滚动数组替代。
 */
//-----------------------------------------------------------------------------
    //2
    //https://www.bbsmax.com/A/D854ej8xdE/
/*
题意说的是，从一个矩阵的左上角走到右下角，每个格子里面代表的是血量需要加多少或者减多少。

然后需要血量在图中保持正数，在只能向右和向下的情况下，求从左上角到右下角需要的最小血量。

很明显用DP，但是如何用DP，刚开始的想法是，

1、用两个数组，一个数组记录到当前位置需要的最小血量min[len]，另一个是在最小血量的情况下路径上的数的和sum[len]。

2、第一行因为只能一直向右走，所以很简单。

3、从第二行开始，有两种选项，从上面来还是从左边来（第一个单独算），然后比较这两种选项需要的最小血量，选择较小的那条路。

4、在两种选项的血量一样的时候，选择sum较大的走。

但是这样会出错。

[[1,-3,3],[0,-2,0],[-3,-3,-3]]这一组就会出错，因此这样是不对的。局部最优不等于全局最优。
 */
    public class Solution2 {//错误代码
        public int calculateMinimumHP(int[][] dungeon) {
            if (dungeon.length == 1 && dungeon[0].length == 1){
                if (dungeon[0][0] > 0){
                    return 1;
                } else {
                    return -dungeon[0][0]+1;
                }
            }
            int len = dungeon[0].length;
            int[] min = new int[len];
            int[] sum = new int[len];
            int num = dungeon[0][0];
            min[0] = Math.max(1, -num + 1);
            sum[0] = num;
            for (int i = 1; i < len; i++){
                num += dungeon[0][i];
                sum[i] = num;
                min[i] = Math.max(-num + 1, min[i - 1]);
            }
            int flag = 0;
            int flag2 = 1;
            for (int i = 1;i < dungeon.length; i++){
                sum[0] += dungeon[i][0];
                min[0] = Math.max(min[0], -sum[0] + 1);
                for (int j = 1;j < len; j++){
                    if (min[j - 1] < min[j] || (min[j - 1] == min[j] && sum[j - 1] > sum[j])){
                        sum[j] = sum[j - 1] + dungeon[i][j];
                        min[j] = Math.max(min[j - 1], -sum[j] + 1);
                    } else {
                        sum[j] += dungeon[i][j];
                        min[j] = Math.max(min[j], -sum[j] + 1);
                    }
                }
            }
            return min[len - 1];
        }
    }

//-----------------------------------------------------------------------------
    //3
    //2、反过来，从结尾开始，表示当前位置到结尾最少需要的血量
    public class Solution3 {
        public int calculateMinimumHP(int[][] dungeon) {
            if (dungeon.length == 1 && dungeon[0].length == 1){
                if (dungeon[0][0] > 0){
                    return 1;
                } else {
                    return -dungeon[0][0] + 1;
                }
            }
            int len = dungeon[0].length;
            int row = dungeon.length;
            int[] min = new int[len];
            min[len - 1] = -dungeon[row - 1][len - 1] + 1;
            min[len - 1] = Math.max(min[len - 1], 1);
            for (int i = len - 2; i >= 0; i--){
                min[i] = min[i + 1] - dungeon[row - 1][i];
                min[i] = Math.max(min[i], 1);
            }
            for (int i = row - 2; i >= 0; i--){
                min[len - 1] = min[len - 1] - dungeon[i][len - 1];
                min[len - 1] = Math.max(min[len - 1], 1);
                for (int j = len - 2; j >= 0; j--){
                    min[j] = Math.min(min[j + 1], min[j]) - dungeon[i][j];
                    min[j] = Math.max(min[j], 1);
                }
            }
            return min[0];
        }
    }

//-----------------------------------------------------------------------------
    //4
    //My AC Java Version, Suggestions are welcome
    class Solution4{
        public int calculateMinimumHP(int[][] dungeon) {
            if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;

            int m = dungeon.length;
            int n = dungeon[0].length;

            int[][] health = new int[m][n];

            health[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);

            for (int i = m - 2; i >= 0; i--) {
                health[i][n - 1] = Math.max(health[i + 1][n - 1] - dungeon[i][n - 1], 1);
            }

            for (int j = n - 2; j >= 0; j--) {
                health[m - 1][j] = Math.max(health[m - 1][j + 1] - dungeon[m - 1][j], 1);
            }

            for (int i = m - 2; i >= 0; i--) {
                for (int j = n - 2; j >= 0; j--) {
                    int down = Math.max(health[i + 1][j] - dungeon[i][j], 1);
                    int right = Math.max(health[i][j + 1] - dungeon[i][j], 1);
                    health[i][j] = Math.min(right, down);
                }
            }

            return health[0][0];
        }
    }


//-----------------------------------------------------------------------------
/*My java solution with explanation in detail
    With a health array to store each grid's health, we should get the result at [0][0].

    Now the question become to how to create a health array using dungeon.

            dungeon

-2,-3,3
        -5,-10,1
        10,30,-5
    From the Dungeon grid, we can simply compute health for the [last row][last column].

    Now we get

?,?,?
        ?,?,?
        ?,?,6
    Now because the knight can only move rightward or downward in each step, we can compute all the health value for last row from right to left using its rightward neighbor. we can also compute all the health value for last column from bottom to up using its downward neighbor.

            ?,?,2
            ?,?,5
            1,1,6
    Now, we can compute all the health value using its downward neighbor and rightward neighbor(we use the min value of these 2 health value).

            7,5,2
            6,11,5
            1,1,6
    Now we get the answer [0][0], which is 7.*/
    class Solution5{
        public int calculateMinimumHP(int[][] dungeon) {

            int row = dungeon.length;
            int column = dungeon[0].length;

            int[][] tem = new int[row][];
            for (int i = 0; i < tem.length; i++) {
                tem[i] = new int[column];
            }

            if (dungeon[row - 1][column - 1] >= 0) {
                tem[row - 1][column - 1] = 1;
            } else {
                tem[row - 1][column - 1] = 1 - dungeon[row - 1][column - 1];
            }

            for (int i = row - 2; i >= 0; i--) {
                tem[i][column - 1] = c(dungeon[i][column - 1],
                        tem[i + 1][column - 1]);
            }

            for (int j = column - 2; j >= 0; j--) {
                tem[row - 1][j] = c(dungeon[row - 1][j], tem[row - 1][j + 1]);
            }

            for (int i = row - 2; i >= 0; i--) {
                for (int j = column - 2; j >= 0; j--) {
                    tem[i][j] = Math.min(c(dungeon[i][j], tem[i + 1][j]),
                            c(dungeon[i][j], tem[i][j + 1]));
                }
            }

            return tem[0][0];
        }

        private int c(int value, int preResult) {
            if (value == 0)
                return preResult;

            if (value > 0) {
                if (value >= preResult)
                    return 1;
                return preResult - value;
            }

            return preResult - value;
        }
    }
//-----------------------------------------------------------------------------
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