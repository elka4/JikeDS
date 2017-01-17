package top100.Enhance._4DP_PreClass;

public class _4_NumTrees {
//Method I
public int numTrees(int n) {
    if (n <= 0) {
            return 0;
        }
        int[] root = new int[n + 1];
        return count(n, root);

    }
    public int count(int n, int[] root) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (root[n] != 0) {
            return root[n];
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += count(i, root) * count(n - i - 1, root);
        }
        root[n] = sum;
        return sum;
}
//Method II
public int numTrees2(int n) {

        if (n <= 0) {
            return 0;
        }
        int[] count = new int[n + 1];
        count[0] = 1;

        for (int i = 1; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                count[i] += count[j] * count[i - j - 1];
            }
        }
        return count[n];
}
}
