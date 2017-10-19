package _5_Bit;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/*
LeetCode – Gray Code

The gray code is a binary numeral system where two successive
values differ in only one bit.

Given a non-negative integer n representing the total number
of bits in the code, print the sequence of gray code.

A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2].
Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
 */

//

//DFS
//每次 +1就好了
public class Gray_Code {
    public List<Integer> grayCode(int n) {
        if (n == 0) {
            List<Integer> result = new ArrayList<Integer>();
            result.add(0);
            return result;
        }

        List<Integer> result = grayCode(n - 1);

        int numToAdd = 1 << (n - 1);

        for (int i = result.size() - 1; i >= 0; i--) {
            result.add(numToAdd + result.get(i));
        }

        return result;
    }

    @Test
    public void test01() {
        System.out.println(grayCode(2));
        System.out.println(grayCode(3));
        for (int i : grayCode(3)
                ) {
            System.out.println(Integer.toBinaryString(i));
        }
    }

/////////////////////////////////////////////////////////////

    //iterative 做法, 简单美观, 最好的做法
    //The idea is simple. G(i) = i^ (i/2).
    //要理解为什么 i ^ i>>1 和前一个刚好只差一个1 ！！！？？？？
    /*
    i   ^     i>>1
    i-1 ^ (i-1)>>1
     */

    /*
    可以记住这个结论，i ^ i>>1， 和i--或者i++相比，只差一个1
     */

    public List<Integer> grayCode2(int n) {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < 1<<n; i++){
            System.out.println("i        " + Integer.toBinaryString(i));
            System.out.println("i>>1      " + Integer.toBinaryString(i>>1));
            System.out.println("i ^ i>>1 " + Integer.toBinaryString(i ^ i>>1));
            result.add(i ^ i>>1);
            System.out.println("-----------------");
        }
        return result;
    }

    @Test
    public void test02() {
        System.out.println(grayCode(2));
        System.out.println(grayCode(3));
        for (int i : grayCode2(3)
                ) {
            System.out.println(Integer.toBinaryString(i));
        }
        /*
i         0
i>>1      0
i ^ i>>1  0
-----------------
i         1
i>>1      0
i ^ i>>1  1
-----------------
i        10
i>>1     01
i ^ i>>1 11
-----------------
i        11
i>>1     01
i ^ i>>1 10
-----------------
i        100
i>>1     010
i ^ i>>1 110
-----------------
i        101
i>>1     010
i ^ i>>1 111
-----------------
i        110
i>>1     011
i ^ i>>1 101
-----------------
i        111
i>>1     011
i ^ i>>1 100
-----------------
0
1
11
10
110
111
101
100
         */
    }


/////////////////////////////////////////////////////////////

    //JAVA-----------Easy Version To Understand!!!!!!

    public List<Integer> grayCode4(int n) {
        if (n < 0)
            return new ArrayList<Integer>();
        if (n == 0) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(0);
            return list;
        }

        List<Integer> tmp = grayCode4(n - 1);

        List<Integer> result = new ArrayList<Integer>(tmp);
        int addNumber = 1 << (n - 1);
        for (int i = tmp.size() - 1; i >= 0; i--) {
            result.add(addNumber + tmp.get(i));
        }

        return result;
    }


/////////////////////////////////////////////////////////////
    //1ms Java Solution with explaination

    //analyze the pattern
    //n=0  -> 0
    //n=1  -> 0, 1
    //n=2  -> (00,  01),  (10,  11)
    //n=3  -> (000, 001, 010, 011), (111, 110, 101, 100)

    //so the pattern is when n=n  -> add 0 in front of all the
    //  result of (n-1)'s binary value (This is just same as all
    // the result of (n-1)

    // and add 1 in front of all the result of(n-1)'s binary
    // value (This need to calculate.)


    public List<Integer> grayCode6(int n) {
        List<Integer> result = new ArrayList();
        result.add(0);

        for(int i=1; i<=n; i++){
            int front=1;
            //Create the correct value for binary format (10...0) which the value has i digi
            //so shift 1 to right (i-1) times
            for(int j=1; j<i; j++){
                front = front<<1;
            }

            //add the new generated value to the result list
            //the new generated value is the last result add front value
            int size=result.size();
            //we want to loop through the (n-1) result from end to start.
            // This is just because want to make the test case match the Leetcode answer.
            // You can use other way loop through the (n-1) result.
            for(int k=size-1; k>=0; k--){
                result.add(result.get(k)+front);
            }
        }

        return result;
    }

    /*
    I noticed 3 things while reading your code:

    Tip: size == front in every iteration, because you double the elements in result each time and i advances by one which results in one more front << 1 == front * 2.

    so shift 1 to right (i-1) times
    VS the operator shifiting left

    front<<1;
    and also there's a direct operation for it, no need to loop

    int front = 1 << (i - 1);
    This is just because want to make the test case match the Leetcode answer. You can use other way loop through the (n-1) result.
    This is simply not true. If you don't reverse the walk you simply ignore the reflective property of the Grey code, notice how after the kth line the last k-1 bits are reflections of the elements before.

    0000 ----\
    ----     |
    0001 ---\|
    ----    ||
    0011 --\||
    0010 -\|||
    ----  |||| k = 3, reflection above and below
    0110 -/|||
    0111 --/||
    0101 ---/|
    0100 ----/
    This relfectivity makes sure that only one bit is flipped, consider what happens if we don't do a reverse walk when adding the prefix (front == 0b100):

    000, 001, 011, 010, !, 100, 101, 111, 110
    at ! the numbers differ in two bits: counting from right startin from 0 you need to flip bit 1 and bit 2 to go from 010 to 100. Moving a bit is not allowed here. In other words XOR of two consecutive gray code elements must be a power of 2.
     */


    //Some simplification:
    public List<Integer> grayCode7(int n) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(0);
        if(n == 0) return list;
        list.add(1);
        if(n == 1) return list;
        int reflect_adder = 1;
        for(int i = 1; i < n; ++i){
            reflect_adder = reflect_adder << 1;
            for(int j = reflect_adder - 1; j >= 0; j--)
                list.add(list.get(j) + reflect_adder);
        }
        return list;
    }
/////////////////////////////////////////////////////////////

    /*
    My idea is to generate the sequence iteratively. For example,
    when n=3, we can get the result based on n=2.
    00,01,11,10 -> (000,001,011,010 ) (110,111,101,100).
    The middle two numbers only differ at their highest bit,
    while the rest numbers of part two are exactly symmetric of part one.
    It is easy to see its correctness.
    Code is simple:
     */

    public List<Integer> grayCode3(int n) {
        List<Integer> rs=new ArrayList<Integer>();
        rs.add(0);
        for(int i=0;i<n;i++){
            int size=rs.size();
            for(int k=size-1;k>=0;k--)
                rs.add(rs.get(k) | 1<<i);
        }
        return rs;
    }




/////////////////////////////////////////////////////////////




/////////////////////////////////////////////////////////////

}