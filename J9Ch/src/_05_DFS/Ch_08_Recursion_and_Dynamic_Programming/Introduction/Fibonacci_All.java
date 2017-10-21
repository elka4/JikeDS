package _05_DFS.Ch_08_Recursion_and_Dynamic_Programming.Introduction;

public class Fibonacci_All {

    public int fibonacci(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        return fibonacci(i - 1) + fibonacci(i - 2);
    }

//////////////////////////////////////////////////////////////////////

    public static int fibonacci2(int n) {

        return fibonacci2(n, new int[n + 1]);
    }

    public static int fibonacci2(int i, int[] memo) {
        if (i == 0) return 0;
        else if (i == 1) return 1;

        if (memo[i] == 0) {
            memo[i] = fibonacci2(i - 1, memo) + fibonacci2(i - 2, memo);
        }
        return memo[i];
    }

//////////////////////////////////////////////////////////////////////

    public static int fibonacci3(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;

        int[] memo = new int[n];
        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i < n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n - 1] + memo[n - 2];
    }

//////////////////////////////////////////////////////////////////////
    public static int fibonacci4(int n) {
        if (n == 0) return 0;
        int a = 0;
        int b = 1;
        for (int i = 2; i < n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return a + b;
    }

}
