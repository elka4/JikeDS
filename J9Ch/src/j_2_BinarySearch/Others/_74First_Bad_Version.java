package j_2_BinarySearch.Others;

///First Bad Version
class SVNRepo{
    public static boolean isBadVersion(int i){
        return true;
    }
}

public class _74First_Bad_Version {


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
        //only this condition will not call SVNRepo too many times
        return end;
    }
}


/*
 * The code base version is an integer start from 1 to n. 
 * One day, someone committed a bad version in the code case, 
 * so it caused this version and the following versions are all failed in the unit tests. 
 * Find the first bad version.

You can call isBadVersion to help you determine which version is the first bad one. 
The details interface can be found in the code's annotation part.

 Notice

Please read the annotation in code area to get the correct way to call isBadVersion in 
different language. For example, Java is SVNRepo.isBadVersion(v)

Have you met this question in a real interview? Yes
Example
Given n = 5:

isBadVersion(3) -> false
isBadVersion(5) -> true
isBadVersion(4) -> true
Here we are 100% sure that the 4th version is the first bad version.

Challenge 
You should call isBadVersion as few as possible.

Tags 
Binary Search LintCode Copyright Facebook
Related Problems 
Medium Nuts & Bolts Problem 15 %
 * */

