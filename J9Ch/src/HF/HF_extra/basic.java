package HF.HF_extra;

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
        System.out.println(Integer.toBinaryString(A));

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

    //3.      a & b
    @Test
    public void test04(){
        int a = 10 & 44; // a   8
        System.out.print("  ");print(10);
        print(44);
        System.out.print("  ");print(a);
        System.out.println(a);
    }

    //4.      A|B

}
