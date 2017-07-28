package bit;

import org.junit.Test;

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


/*http://javarevisited.blogspot.com/2013/05/how-to-check-if-integer-number-is-power-of-two-example.html
     * checking if number is power of 2 using bit shift operator in java
     * e.g. 4 in binary format is "0000 0000 0000 0000 0000 0000 0000 0100";
     * and -4 is                  "1111 1111 1111 1111 1111 1111 1111 1100";
     * and 4&-4 will be           "0000 0000 0000 0000 0000 0000 0000 0100"
     */


    //Read more: http://javarevisited.blogspot.com/2013/05/how-to-check-if-integer-number-is-power-of-two-example.html#ixzz4o3mlgh00
    boolean isPowerOfTwo2(int x)
    {
        // x will check if x == 0 and !(x & (x - 1))
        // will check if x is a power of 2 or not
        return (x & -x) == x;
    }

    boolean isPowerOfTwo3(int x){
        return (x & (x -1)) == 0;

    }

    @Test
    public void test01(){
        System.out.println(isPowerOfTwo2(4));

    }

    @Test
    public void test02(){
        System.out.println(isPowerOfTwo3(4));

    }

///////////////////////////////////////////////////////////////////
    //2) Count the number of ones in the binary representation of the given number.

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
    public void test03(){
        int a = 13;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(count_one(a));

    }

//////////////////////////////////////////////////////////////////
    //3) Check if the ith bit is set in the binary form of the given number.

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

//////////////////////////////////////////////////////////////////

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
                if((i & (1 << j)) != 0)
                    //cout << A[j] << ‘ ‘;
                    System.out.print(A[j] +" ");
            }
            System.out.println();
//            cout << endl;
        }
    }
    @Test
    public void test05(){
        char[] A = {'a','b','c'};
        possibleSubsets(A,3);
    }

///////////////////////////////////////////////////////////////////////
    //5) Find the largest power of 2 (most significant bit in binary form),
    // which is less than or equal to the given number N.

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
