package Bit106.L01;

/**
 * You are currently leading a team to develop a new product. Unfortunately,
 * the latest version of your product fails the quality check. Since
 * each version is developed based on the previous version, all the versions
 * after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the
 * first bad one, which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which will return whether
 * version is bad. Implement a function to find the first bad version. You
 * should minimize the number of calls to the API.
 *
 * E.g.
 * 1 2 3 4 5 6 7 8 9 10
 * G G G B B B B B B B
 */

public class SolutionFirstBadVersion {
    private static int FIRST_BAD = 4;
    private static boolean isBadVersion(int version) {
        if (version >= FIRST_BAD) {
            return true;
        }
        return false;
    }

    private static int firstBadVersion(int n) {
        int low = 1;
        int high = n;
        while(low < high) {
            // avoid int overflow
            int mid = low + (high - low) / 2;
            if(isBadVersion(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int rst = firstBadVersion(10);
        System.out.println(rst);
    }
}


// Time Complexity O(logN)
// Space Complexity O(1)