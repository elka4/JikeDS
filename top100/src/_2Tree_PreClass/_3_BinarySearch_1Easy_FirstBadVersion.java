package _2Tree_PreClass;

/**
 * Created by tzh on 1/27/17.
 */
import CtCILibrary.BTreePrinter;
import CtCILibrary.TreeNode;
import org.junit.Test;

public class _3_BinarySearch_1Easy_FirstBadVersion {
    public int firstBadVersion(int n){
        if(n <= 0){
            return Integer.MIN_VALUE;
        }
        int start = 1;
        int end = n;

        while(start < end - 1){
            int mid = start + (end - start) / 2;
            if(!isBadVersion(mid)){
                start = mid;
            } else {
                end = mid;
            }
        }
        return isBadVersion(start) ? start : end;
    }
    private boolean isBadVersion(int n){
        return true;
    }
}
