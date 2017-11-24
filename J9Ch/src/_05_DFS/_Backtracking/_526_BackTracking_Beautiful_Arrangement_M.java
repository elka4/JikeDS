package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;

//526. Beautiful Arrangement

public class _526_BackTracking_Beautiful_Arrangement_M {

    public class Solution {
        int count = 0;

        public int countArrangement(int N) {
            if (N == 0) return 0;
            helper(N, 1, new int[N + 1]);
            return count;
        }

        private void helper(int N, int pos, int[] used) {
            if (pos > N) {
                count++;
                return;
            }

            for (int i = 1; i <= N; i++) {
                if (used[i] == 0 && (i % pos == 0 || pos % i == 0)) {
                    used[i] = 1;
                    helper(N, pos + 1, used);
                    used[i] = 0;
                }
            }
        }
    }


    public class Solution2 {
        public int countArrangement(int N) {
            int[] cnt = new int[1];
            int bit = 0;
            dfs(N, cnt, bit, 1);
            return cnt[0];
        }

        private void dfs(int N, int[] cnt, int bit, int step) {
            if (step == N + 1) { cnt[0] ++; return; }
            for (int i = 1; i<= N; i ++) {
                if (((1 << i) & bit) > 0) continue;
                if (step % i == 0 || i % step == 0)
                    dfs(N, cnt, (1 << i) | bit, step + 1);
            }
        }
    }

    public class Solution3 {
        public int countArrangement(int N) {
            // arr[0] is reserved for sum
            int[] array = new int[N + 1];
            settle(N, array);
            return array[0];
        }

        private void settle(int n, int[] array) {
            if (n == 1) {
                // 1 could be settled anywhere, sum++
                array[0] ++;
                return;
            }
            for (int i = 1; i < array.length; i ++) {
                // check i is not occupied and fit n
                if (array[i] == 0 && (n % i == 0 || i % n == 0)) {
                    // n is settled to i
                    array[i] = n;
                    // get n - 1 settled
                    settle(n - 1, array);
                    // backtrack
                    array[i] = 0;
                }
            }
        }

    }

    public class Solution4 {
        public int countArrangement(int n) {
            boolean[] used = new boolean[n + 1];
            return dfs(1, n, used);
        }

        private int dfs(int position, int n, boolean[] used) {
            if (position == (n + 1)) {
                return 1;
            }

            int count = 0;
            for (int test = 1; test <= n; test ++) {
                if (!used[test] && isBeautiful(position, test)) {
                    used[test] = true;
                    count += dfs(position + 1, n, used);
                    used[test] = false;
                }
            }

            return count;
        }

        private boolean isBeautiful(int position, int value) {
            return (position % value == 0) || (value % position == 0);
        }
    }

    class Solution5{
        public int countArrangement(int N) {
            return dfs(N, new boolean[N + 1], 1);
        }

        private int dfs(int n, boolean[] used, int k) {
            if (k == n + 1) return 1;
            int cnt = 0;
            // If not used and "beautiful", put number i in position k
            for (int i = 1; i <= n; i++) {
                if (used[i] || !(i % k == 0 || k % i == 0)) continue;
                used[i] = true;
                cnt += dfs(n, used, k + 1);
                used[i] = false;
            }
            return cnt;
        }
    }

    public class Solution6 {
        int count = 0;

        public int countArrangement(int N) {
            if (N == 0) return 0;
            helper(N, 1, new int[N + 1]);
            return count;
        }

        private void helper(int N, int pos, int[] used) {
            if (pos > N) {
                count++;
                return;
            }

            for (int i = 1; i <= N; i++) {
                if (used[i] == 0 && (i % pos == 0 || pos % i == 0)) {
                    used[i] = 1;
                    helper(N, pos + 1, used);
                    used[i] = 0;
                }
            }
        }
    }

    public class Solution7 {
        public int countArrangement(int N) {
            dfs(N, N, new boolean[N + 1]);
            return count;
        }

        int count = 0;

        void dfs(int N, int k, boolean[] visited) {
            if (k == 0) {
                count++;
                return;
            }
            for (int i = 1; i <= N; i++) {
                if (visited[i] || k % i != 0 && i % k != 0) {
                    continue;
                }
                visited[i] = true;
                dfs(N, k - 1, visited);
                visited[i] = false;
            }
        }
    }

    //Approach #3 Backtracking [Accepted]
    public class Solution8 {
        int count = 0;
        public int countArrangement(int N) {
            boolean[] visited = new boolean[N + 1];
            calculate(N, 1, visited);
            return count;
        }
        public void calculate(int N, int pos, boolean[] visited) {
            if (pos > N)
                count++;
            for (int i = 1; i <= N; i++) {
                if (!visited[i] && (pos % i == 0 || i % pos == 0)) {
                    visited[i] = true;
                    calculate(N, pos + 1, visited);
                    visited[i] = false;
                }
            }
        }
    }
//------------------------------------------------------------------------------/


//------------------------------------------------------------------------------/


//------------------------------------------------------------------------------/



}
/*
Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:

The number at the ith position is divisible by i.
i is divisible by the number at the ith position.
Now given N, how many beautiful arrangements can you construct?

Example 1:
Input: 2
Output: 2
Explanation:

The first beautiful arrangement is [1, 2]:

Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).

Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).

The second beautiful arrangement is [2, 1]:

Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).

Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
Note:
N is a positive integer and will not exceed 15.

 */