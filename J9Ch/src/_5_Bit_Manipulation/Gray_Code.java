package _5_Bit_Manipulation;
import java.util.*;
/*
LeetCode – Gray Code (Java)

The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
 */
public class Gray_Code {
    public List<Integer> grayCode(int n) {
        if(n==0){
            List<Integer> result = new ArrayList<Integer>();
            result.add(0);
            return result;
        }

        List<Integer> result = grayCode(n-1);
        int numToAdd = 1<<(n-1);

        for(int i=result.size()-1; i>=0; i--){
            result.add(numToAdd+result.get(i));
        }

        return result;
    }
}
