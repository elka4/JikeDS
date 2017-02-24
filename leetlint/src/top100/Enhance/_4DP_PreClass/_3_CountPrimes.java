package top100.Enhance._4DP_PreClass;

public class _3_CountPrimes {
	public int countPrimes(int n) {
        if (n <= 2)
            return 0;
        boolean[] notPrime = new boolean[n];
        int bound = (int) Math.sqrt(n); //marking end point
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                count++;
                if (i <= bound) {
                    for (int j = i * i; j < n; j = j + i) { //from i*i to n
                        if (!notPrime[j])
                            notPrime[j] = true;
                    }
                }
            }
        }
        return count;
	}
}
