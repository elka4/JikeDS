package _05_DFS._Recursion;
import java.util.*;

//  https://leetcode.com/problems/strobogrammatic-number-ii/description/
public class _247_Strobogrammatic_Number_II_M {
//////////////////////////////////////////////////////////////////////////////////////////////////
    //Accepted Java solution using recursion
    public List<String> findStrobogrammatic(int n) {
        findStrobogrammaticHelper(new char[n], 0, n - 1);
        return res;
    }

    List<String> res = new ArrayList<String>();

    public void findStrobogrammaticHelper(char[] a, int l, int r) {
        if (l > r) {
            res.add(new String(a));
            return;
        }
        if (l == r) {
            a[l] = '0'; res.add(new String(a));
            a[l] = '1'; res.add(new String(a));
            a[l] = '8'; res.add(new String(a));
            return;
        }

        if (l != 0) {
            a[l] = '0'; a[r] = '0';
            findStrobogrammaticHelper(a, l+1, r-1);
        }
        a[l] = '1'; a[r] = '1';
        findStrobogrammaticHelper(a, l+1, r-1);
        a[l] = '8'; a[r] = '8';
        findStrobogrammaticHelper(a, l+1, r-1);
        a[l] = '6'; a[r] = '9';
        findStrobogrammaticHelper(a, l+1, r-1);
        a[l] = '9'; a[r] = '6';
        findStrobogrammaticHelper(a, l+1, r-1);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////
}
/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].
 */