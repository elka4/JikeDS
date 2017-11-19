package _01_Array.Other;

import java.util.ArrayList;
import java.util.List;
/*
LeetCode – Pascal’s Triangle II (Java)

Given an index k, return the kth row of the Pascal's triangle. For example, when k = 3, the row is [1,3,3,1].

Analysis

This problem is related to Pascal's Triangle which gets all rows of Pascal's triangle. In this problem, only one row is required to return.
 */
public class Pascals_TriangleII {

    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        if (rowIndex < 0)
            return result;

        result.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = result.size() - 2; j >= 0; j--) {
                result.set(j + 1, result.get(j) + result.get(j + 1));
            }
            result.add(1);
        }
        return result;
    }

//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///
}
