package J_2_Binary_Search.Required_10;
import java.util.*;
/**74. First Bad Version
 * Medium
 * Created by tianhuizhu on 6/27/17.
 */
class GitRepo {
    public static boolean isBadVersion(int k){
     return false;
    }
}
public class _74_First_Bad_Version_Medium {

    /**
     * public class GitRepo {
     *     public static boolean isBadVersion(int k);
     * }
     * you can use GitRepo.isBadVersion(k) to judge whether
     * the kth code version is bad or not.
     */
    class Solution {
        /**
         * @param n: An integers.
         * @return: An integer which is the first bad version.
         */
        public int findFirstBadVersion(int n) {
            int start = 1, end = n;
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (GitRepo.isBadVersion(mid)) {
                    end = mid;
                } else {
                    start = mid;
                }
            }

            if (GitRepo.isBadVersion(start)) {
                return start;
            }
            return end;
        }
    }

}
