package bit;

import org.junit.Test;

public class basic {
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




}
