package _1_Array.Binary_Search;

public class First_Bad_Version {
    boolean isBadVersion(int m){
        return false;
    }
    //Java Solution 1 - Recurisve

    public int firstBadVersion(int n) {
        return helper(1, n);
    }

    public int helper(int i, int j){
        int m = i + (j-i)/2;

        if(i>=j)
            return i;

        if(isBadVersion(m)){
            return helper(i, m);
        }else{
            return helper(m+1, j); //not bad, left --> m+1
        }
    }



    //Java Solution 2 - Iterative

    public int firstBadVersion2(int n) {
        int i = 1, j = n;
        while (i < j) {
            int m = i + (j-i) / 2;
            if (isBadVersion(m)) {
                j = m;
            } else {
                i = m+1;
            }
        }

        if (isBadVersion(i)) {
            return i;
        }

        return j;
    }
}
