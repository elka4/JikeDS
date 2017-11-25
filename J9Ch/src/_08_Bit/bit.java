package _08_Bit;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class bit {

    //if a given number is a power of 2
    //O(logN)
    boolean isPowerOfTwo(int x)
    {
        if(x == 0)
            return false;
        else
        {
            while(x % 2 == 0) x /= 2;
            return (x == 1);
        }
    }


//------------------------------------------------------------------------------/
//    i ^ i>>1 操作得到的相邻两个二进制数字在一位上为0或1
//------------------------------------------------------------------------------/

    // x & -x

    /*
    http://javarevisited.blogspot.com/2013/05/how-to-check-if-integer
    -number-is-power-of-two-example.html

     * checking if number is power of 2 using bit shift operator in java
     * e.g. 4 in binary format is "0000 0000 0000 0000 0000 0000 0000 0100";
     * and -4 is                  "1111 1111 1111 1111 1111 1111 1111 1100";
     * and 4&-4 will be           "0000 0000 0000 0000 0000 0000 0000 0100"
     * 3
     *        in binary format is "0000 0000 0000 0000 0000 0000 0000 0011";
     * and -3 is                  "1111 1111 1111 1111 1111 1111 1111 1101";
     * and 3 & -3 will be         "0000 0000 0000 0000 0000 0000 0000 0001"
     *
      就是说，2的幂的二进制表示的特点是，只有一个1，后面必然全是0。（2的幂必然是正数）
    如果一个数不是0也不是2的幂，那必然有不止一个1

    所以本题解法是，假设x是2的幂，利用-x，将x中1之前的位置全部反转，这样x和-x
    在唯一1位置之前相反，1位置相同，1位置之后相同都为0， x & (-x) 为 x。否则反之。
     *
     */
    @Test
    public void test00(){
        System.out.println(Integer.toBinaryString(4));
        System.out.println(Integer.toBinaryString(-4));
        System.out.println(Integer.toBinaryString(4 &(-4)));
    }
    @Test
    public void test1(){
        System.out.println(Integer.toBinaryString(3));
        System.out.println(Integer.toBinaryString(-3));
        System.out.println(Integer.toBinaryString(3 &(-3)));
    }
    @Test
    public void test11(){
        System.out.println(Integer.toBinaryString(16));
        System.out.println(Integer.toBinaryString(-16));
        System.out.println(Integer.toBinaryString(16 &(-16)));
    }


    boolean isPowerOfTwo2(int x)
    {
        // x will check if x == 0 and !(x & (x - 1))
        // will check if x is a power of 2 or not
        return (x & -x) == x;
    }

    @Test
    public void test2(){
        System.out.println(Integer.toBinaryString(4));
        System.out.println(Integer.toBinaryString(4 - 1));
        System.out.println(Integer.toBinaryString(4 &(4 - 1)));
    }
    @Test
    public void test3(){
        System.out.println(Integer.toBinaryString(3));
        System.out.println(Integer.toBinaryString(-3));
        System.out.println(Integer.toBinaryString(3 &(-3)));
    }


//-----------------------------------------------------------------------------//

    // x & (x - 1)

    /*
    (x-1) will have all the bits same as x, except for
    the rightmost 1 in x and
    all the bits to the right of the rightmost 1 in x - 1.

    binary representation of (x-1) can be obtained by simply
    flipping all the bits to the right of rightmost 1 in x
    and also including the rightmost 1

    Properties for numbers which are powers of 2,
    is that they have one and only one bit set in their binary representation.

    If the number is neither zero nor a power of two,
    it will have 1 in more than one place.

    So if x is a power of 2 then x & (x-1) will be 0.

    就是说，2的幂的二进制表示的特点是，只有一个1，后面必然全是0。（2的幂必然是正数）
    如果一个数不是0也不是2的幂，那必然有不止一个1

    所以本题解法是，假设x是2的幂，利用x-1，将x中1和1之后的位置全部反转，这样x和x-1
    在所有位置上都不同， x & (x - 1) 为 0。否则反之。


     * checking if number is power of 2 using bit shift operator in java
     * e.g. 4 in binary format is "0000 0000 0000 0000 0000 0000 0000 0100";
     * and  4 - 1 is              "0000 0000 0000 0000 0000 0000 0000 0011";
     * and 4 & (4 - 1) will be    "0000 0000 0000 0000 0000 0000 0000 0000"
     *
     * 3 in binary format is      "0000 0000 0000 0000 0000 0000 0000 0011";
     * and 3 - 1 is               "0000 0000 0000 0000 0000 0000 0000 0010";
     * and 3 & (3 - 1) will b     "0000 0000 0000 0000 0000 0000 0000 0010"
     *
 *     10 in binary format is     "0000 0000 0000 0000 0000 0000 0000 1010";
     * and 10 - 1 is              "0000 0000 0000 0000 0000 0000 0000 1001";
     * and 10 & (10 - 1) will     "0000 0000 0000 0000 0000 0000 0000 1000"
     */

    @Test
    public void test4(){
        System.out.println(Integer.toBinaryString(4));
        System.out.println(Integer.toBinaryString(4 - 1));
        System.out.println(Integer.toBinaryString(4 & (4 - 1)));
    }
    @Test
    public void test5(){
        System.out.println(Integer.toBinaryString(3));
        System.out.println(Integer.toBinaryString(3 - 1));
        System.out.println(Integer.toBinaryString(3 & (3 - 1)));
    }
    @Test
    public void test6(){
        System.out.println(Integer.toBinaryString(10));
        System.out.println(Integer.toBinaryString(10 - 1));
        System.out.println(Integer.toBinaryString(10 & (10 - 1)));
    }
    @Test
    public void test7(){
        System.out.println(Integer.toBinaryString(16));
        System.out.println(Integer.toBinaryString(16 - 1));
        System.out.println(Integer.toBinaryString(16 & (16 - 1)));
    }

    boolean isPowerOfTwo3(int x){
        return (x & (x - 1)) == 0;

    }


    @Test
    public void test01(){
        System.out.println(isPowerOfTwo2(4));

    }

    @Test
    public void test02(){
        System.out.println(isPowerOfTwo3(4));

    }

//-----------------------------------------------------------------------------/

//2) Count the number of ones in the binary representation of the given number.

    /*
    The basic approach to evaluate the binary form of a number is to traverse
     on it and count the number of ones.
     But this approach takes log2N of time in every case. Why log2N ?
    As to get a number in its binary form, we have to divide it by 2,
    until it gets 0, which will take log2N of time.

    x - 1就是将x中最后的1和它后面的0都反转，就是100。。。变成011。。。
    这样x&(x-1)就会将x中最后一个1重置为0

    计算这样的操作可以进行多少次，就得到总共有多少个1。
     */



    int count_one (int n)
    {
        int count = 0;
        while( n != 0)
        {
            n = n&(n-1);
            count++;
        }
        return count;
    }
    @Test
    public void testcount1(){
        System.out.println(Integer.parseInt("101010",2));//42
    }

    @Test
    public void test03(){
        int a = 13;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(count_one(a));

    }

//-----------------------------------------------------------------------------
    //3) Check if the ith bit is set in the binary form of the given number.

    //To check if the ith bit is set or not (1 or not), we can use AND operator.

    //1 << i得到只有第i为1的数，N & (1 << i)检查N中第i位数字是否为1
    boolean check (int N, int i)
    {
        if( (N & (1 << i)) != 0)
            return true;
        else
            return false;
    }

    @Test
    public void test04(){
        int a = 8;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(check(a, 0));
        System.out.println(check(a, 1));
        System.out.println(check(a, 2));
        System.out.println(check(a, 3));
//        System.out.println(check(a, 4));

    }

//-----------------------------------------------------------------------------

    //4) How to generate all the possible subsets of a set ?
    /*
    A = {a, b, c}

    0 = (000)2 = {}
    1 = (001)2 = {c}
    2 = (010)2 = {b}
    3 = (011)2 = {b, c}
    4 = (100)2 = {a}
    5 = (101)2 = {a, c}
    6 = (110)2 = {a, b}
    7 = (111)2 = {a, b, c}
     */

    void possibleSubsets(char A[], int N)
    {
        for(int i = 0;i < (1 << N); ++i)
        {
            for(int j = 0;j < N;++j){
                System.out.println("i " + Integer.toBinaryString(i));
                System.out.println("1 << j " + Integer.toBinaryString(1 << j));
                if((i & (1 << j)) != 0){
                    System.out.print(A[j] +" ");
                }
            }
            System.out.println("=======================");
//            cout << endl;
        }
    }
    @Test
    public void test05(){
        char[] A = {'a','b','c'};
        possibleSubsets(A,3);
    }


    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);

        // 1 << n is 2^n
        // each subset equals to an binary integer between 0 .. 2^n - 1
        // 0 -> 000 -> []
        // 1 -> 001 -> [1]
        // 2 -> 010 -> [2]
        // ..
        // 7 -> 111 -> [1,2,3]

        /*
        000
        001
        010
        011
        100
        101
        110
        111
         */
        //全部可能，每次loop完成一个subset
        for (int i = 0; i < (1 << n); i++) {
            ArrayList<Integer> subset = new ArrayList<>();

        /*
        i为 000，001，010，011，100，101，110，111中的一个
        1 << j 就1向左移动j位，那么就是逐个检查i中每个位置，如果为1，subset就要加入nums[j]
         */
            for (int j = 0; j < n; j++) {
                // check whether the jth digit in i's binary representation is 1
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            result.add(subset);
        }

        return result;
    }

//------------------------------------------------------------------------------//
    //5) Find the largest power of 2 (most significant bit in binary form),
    // which is less than or equal to the given number N.
    /*
    Let’s take the N as 16 bit integer and binary form of N is {1000000000000000}.
    N           1000 0000 0000 0000
    N>>1        0100 0000 0000 0000
    N| (N>>1)   1100 0000 0000 0000

    N           1100 0000 0000 0000
    N>>2        0011 0000 0000 0000
    N| (N>>2)   1111 0000 0000 0000

    N           1111 0000 0000 0000
    N>>4        0000 1111 0000 0000
    N| (N>>4)   1111 1111 0000 0000

    N           1111 1111 0000 0000
    N>>8        0000 0000 1111 1111
    N| (N>>8)   1111 1111 1111 1111

     */

    long largest_power(long N)
    {
        //changing all right side bits to 1.
        N = N| (N>>1);
        N = N| (N>>2);
        N = N| (N>>4);
        N = N| (N>>8);


//as now the number is 2 * x-1, where x is required answer,
// so adding 1 and dividing it by2.
        return (N+1)>>1;

    }

    @Test
    public void test06(){
        System.out.println(largest_power(3));
        System.out.println(largest_power(4));
        System.out.println(largest_power(7));
        System.out.println(largest_power(9));
    }


}
