package HF.HF3_Algo_DS_II_1BinarySearch;

//First Bad Version
public class _9FirstBadVersion {
    /**
     * @param n: An integers.
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {
        int start = 1, end = n;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (SVNRepo.isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (SVNRepo.isBadVersion(start)) {
            return start;
        }
        return end;
    }
//------------------------------------------------------------------------------///////////


//------------------------------------------------------------------------------///////////
}

class SVNRepo{
    static boolean isBadVersion(int i){
        return true;
    }
}
/*

 */
