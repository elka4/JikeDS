package J_2_Binary_Search.Optional_7.all;

/**74. First Bad Version
 * Medium
 * Created by tianhuizhu on 6/27/17.
 */

public class _74_First_Bad_Version {
    class GitRepo {
        public boolean isBadVersion(int k){
            return false;
        }
    }
    /**
     * public class GitRepo {
     *     public static boolean isBadVersion(int k);
     * }
     * you can use GitRepo.isBadVersion(k) to judge whether
     * the kth code version is bad or not.
     */

    /**
     * @param n: An integers.
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {
        int start = 1, end = n;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (new GitRepo().isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (new GitRepo().isBadVersion(start)) {
            return start;
        }
        return end;
    }


}
