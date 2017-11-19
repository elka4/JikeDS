package Classification.Bit_Manipulation_26;
import java.util.*;

public class b_201_Bitwise_AND_of_Numbers_Range {

/*    Bit operation solution(JAVA)
    The idea is very simple:

    last bit of (odd number & even number) is 0.
    when m != n, There is at least an odd number and an even number, so the last bit position result is 0.
    Move m and n rigth a position.
    Keep doing step 1,2,3 until m equal to n, use a factor to record the iteration time.*/

    public class Solution {
        public int rangeBitwiseAnd(int m, int n) {
            if(m == 0){
                return 0;
            }
            int moveFactor = 1;
            while(m != n){
                m >>= 1;
                n >>= 1;
                moveFactor <<= 1;
            }
            return m * moveFactor;
        }
    }

//-------------------------------------------------------------------------////////////


    /*
2 line Solution with detailed explanation
 public int rangeBitwiseAnd(int m, int n) {
        while(m<n) n = n & (n-1);
        return n;
    }
The key point: reduce n by removing the rightest '1' bit until n<=m;

(1)if n>m,suppose m = yyyzzz, n = xxx100, because m is less than n, m can be equal to three cases:

(a) xxx011 (if yyy==xxx)
(b) less than xxx011 (if yyy==xxx)
(c) yyyzzz (if yyy<xxx)
for case (a), and (b), xxx011 will always be ANDed to the result, which results in xxx011 & xxx100 = uuu000(uuu == yyy&xxx == xxx);

for case (c), xxx000/xxx011 will always be ANDed to the result, which results in yyyzzz & xxx000 & xxx011 & xxx100 = uuu000 (uuu <= yyy & xxx)

=> for any case, you will notice that: rangBitWiseAnd(vvvzzz,xxx100) == uuu000 == rangBitWiseAnd(vvvzzz,xxx000), (not matter what the value of"uuu" will be, the last three digits will be all zero)

=> This is why the rightest '1' bit can be removed by : n = n & (n-1);

(2)when n==m, obviously n is the result.

(3)when n < m, suppose we reduce n from rangBitWiseAnd(yyyzzz,xxx100) to rangBitWiseAnd(yyyzzz,xxx000);

i) xxx100 >yyyzzz => xxx >= yyy;

ii) xxx000 < yyyzzz => xxx <= yyy;

=> xxx == yyy;

=> rangBitWiseAnd(yyyzzz,xxx000) == rangeBitWiseAnd(xxxzzz,xxx000);

=>result is xxx000, which is also n;     */



//-------------------------------------------------------------------------///////////////
/*
    My simple java solution(3 lines)
    The idea is to use a mask to find the leftmost common digits of m and n.
            Example: m=1110001, n=1110111, and you just need to find 1110000 and it will be the answer.*/
public int rangeBitwiseAnd(int m, int n) {
    int r=Integer.MAX_VALUE;
    while((m&r)!=(n&r))  r=r<<1;
    return n&r;
}


}

/*Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.*/