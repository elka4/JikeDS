package HF.HF_bit;

import org.junit.Test;

public class basic {
    /*
    1.     A<<B
    2.     A>>B,A>>>B
    3.      A&B
    4.      A|B
    5.      ~A
    6.
    A^B
     */

    private void print(int A){
        System.out.printf("%32s",Integer.toBinaryString(A));
        System.out.println();

    }

    //1.     A<<B
    @Test
    public void test01(){
        int A = 12; int B = 2;
        // C 的值将是 (110000)2 也就是十进制中的 48
        int C = A << B;

        System.out.println(Integer.toBinaryString(A));
        System.out.println(Integer.toBinaryString(C));
        /*
        001100
        110000

         */
    }

//-----------------------------------------------------------
    //十进制和二进制如何转换?
    /*
         x y

        (x)y

        (13)10 << 3 = (1101)2 << 3 = (1101000)2 = (104)10 3 << 1 = 6
        2147483647 << 1 = -2
     */
    @Test
    public void test02(){
        int A = 12; int B = 2;
        // C 的值将是 (110000)2 也就是十进制中的 48
        int C = A << B;

        System.out.println(Integer.toBinaryString(A));
        System.out.println(Integer.toBinaryString(C));
        /*
        001100
        110000
         */
    }
//-----------------------------------------------------------


    //2.     A>>B,A>>>B

    @Test
    public void test03(){
        int a = -127 >> 2; // a    -32
        print(a);
        System.out.println(a);

        int b = -127 >>> 2; // b    1073741792
        print(b);
        System.out.println(b);

    }
//-----------------------------------------------------------

    //3.      a & b
    @Test
    public void test04(){
        int a = 10 & 44; // a   8
        System.out.print("  ");print(10);
        print(44);
        System.out.print("  ");print(a);
        System.out.println(a);
    }
//-----------------------------------------------------------

    //4.      A|B
//-----------------------------------------------------------


//-----------------------------------------------------------
// 1.test bit
// 检查第x位是否是1
@Test
public void test_bit(){
    System.out.println("------------------------------------");
    print(10);
    print(1<<1);
    print(10 & 1<<1);
    System.out.println("------------------------------------");
    print(10);
    print(1<<2);
    print(10 & 1<<2);
    System.out.println("------------------------------------");
    print(10);
    print(1<<3);
    print(10 & 1<<3);
    System.out.println("------------------------------------");

}
/*
------------------------------------
                            1010
                              10
                              10
------------------------------------
                            1010
                             100
                               0
------------------------------------
                            1010
                            1000
                            1000
------------------------------------

 */



//-----------------------------------------------------------
//2. clear bit
@Test
public void clear_bit(){
    System.out.println("------------------------------------");
    print(10);
    print(~(1<<1));
    print(10 & ~(1<<1));
    System.out.println("------------------------------------");
    print(10);
    print(~(1<<2));
    print(10 & ~(1<<2));
    System.out.println("------------------------------------");
    print(10);
    print(~(1<<3));
    print(10 & ~(1<<3));
    System.out.println("------------------------------------");
}
/*
------------------------------------
                            1010
11111111111111111111111111111101
                            1000
------------------------------------
                            1010
11111111111111111111111111111011
                            1010
------------------------------------
                            1010
11111111111111111111111111110111
                              10
------------------------------------

 */
//-----------------------------------------------------------
//remove last bit
@Test
public void remove_last_bit(){
    System.out.println("------------------------------------");
    print(10);
    print(~(10-1));
    print(10 & (10-1));
    System.out.println("------------------------------------");
}
//-----------------------------------------------------------
    //计算一个数二进制表示中有多少个1.
    //就是用上面的方法计算最后一个1可以remove多少次
@Test
public void count_1(){
    int n = 10;
    int count = 0;
    print(n);
    System.out.println("---------------------------");
    while(n != 0){
        print(n);
        print(n-1);
        print(n & (n-1));
        n = n & (n-1);
        count++;
        System.out.println("---------------------------");
    }
    System.out.println(count);
}
//-----------------------------------------------------------
//XOR ^ 满足交换律，结合律
//a ^ a = 0， a ^ 0 = a
// Single Number
@Test
public void XOR(){
    int ans = 0;
    //for
}
//-----------------------------------------------------------


}
