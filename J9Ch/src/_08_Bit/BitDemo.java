package _08_Bit;
import org.junit.Test;


public class BitDemo {
    public static void main(String[] args) {
        int bitmask = 0x000F;
        System.out.println(bitmask);
        int val = 0x2222;
        // prints "2"
        System.out.println(val & bitmask);
    }
//------------------------------------------------------------------------------

    /**
     * ���һ��int�Ķ�������
     * @param num
     */
    private static void printInfo(int num){
        System.out.println(Integer.toBinaryString(num));
    }

//------------------------------------------------------------------------------

    //0 for octadezimal 0b for binary and 0x for hexadezimal.
    @Test
    public void test01(){
        int binary  = 0b10;
        int octa    = 010;
        int hexa    = 0x10;

        System.out.println(binary);
        System.out.println(octa);
        System.out.println(hexa);
    }
//------------------------------------------------------------------------------

    @Test
    public void test02(){
        int a = 60;	/* 60 = 0011 1100 */
        int b = 13;	/* 13 = 0000 1101 */
        int c = 0;

        c = a & b;        /* 12 = 0000 1100 */
        System.out.println("a & b = " + c );
    }
//------------------------------------------------------------------------------

    @Test
    public void test03(){
        int a = 60;	/* 60 = 0011 1100 */
        int b = 13;	/* 13 = 0000 1101 */
        int c = 0;

        c = a | b;        /* 61 = 0011 1101 */
        System.out.println("a | b = " + c );
    }
//------------------------------------------------------------------------------

    @Test
    public void test04(){
        int a = 60;	/* 60 = 0011 1100 */
        int b = 13;	/* 13 = 0000 1101 */
        int c = 0;

        c = a ^ b;        /* 49 = 0011 0001 */
        System.out.println("a ^ b = " + c );
    }
//------------------------------------------------------------------------------

    @Test
    public void test05(){
        int a = 60;	/* 60 = 0011 1100 */
        int b = 13;	/* 13 = 0000 1101 */
        int c = 0;

        c = ~a;           /*-61 = 1100 0011 */
        System.out.println("~a = " + c );
    }
//------------------------------------------------------------------------------

    @Test
    public void test06(){
        int a = 60;	/* 60 = 0011 1100 */
        int b = 13;	/* 13 = 0000 1101 */
        int c = 0;

        c = a << 2;       /* 240 = 1111 0000 */
        System.out.println("a << 2 = " + c );
    }
//------------------------------------------------------------------------------

    @Test
    public void test07(){
        int a = 60;	/* 60 = 0011 1100 */
        int b = 13;	/* 13 = 0000 1101 */
        int c = 0;

        c = a >> 2;       /* 15 = 1111 */
        System.out.println("a >> 2  = " + c );
    }
//------------------------------------------------------------------------------

    @Test
    public void test08(){
        int a = 60;	/* 60 = 0011 1100 */
        int b = 13;	/* 13 = 0000 1101 */
        int c = 0;

        c = a >>> 2;      /* 15 = 0000 1111 */
        System.out.println("a >>> 2 = " + c );
    }
//------------------------------------------------------------------------------

    @Test
    public void test09() {
        System.out.println(Integer.toBinaryString(0));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(2));
        System.out.println(Integer.toBinaryString(3));
        System.out.println(Integer.toBinaryString(4));
        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(6));
        System.out.println(Integer.toBinaryString(7));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-2));
        System.out.println(Integer.toBinaryString(-3));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println("11111111111111111111111111111111".length());
    }
//------------------------------------------------------------------------------

    @Test
    public void test10() {
        int a = -15, b = 15;
        // -4��-15 = 1111 0001(������)�����ƶ�λ�����λ�ɷ���λ��佫�õ�1111 1100��-4
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(a>>1));
        System.out.println(a>>1);
        System.out.println(a >> 2);
        // 3��15=0000 1111(������)�����ƶ�λ�����λ�ɷ���λ��佫�õ�0000 0011��3
        System.out.println(b >> 2);
    }
//------------------------------------------------------------------------------

    @Test
    public void test11() {
        int A = 00111100;
        int B = 00001101;
        int result = A&B;
        System.out.println(result);

    }
//------------------------------------------------------------------------------

    @Test
    public void test12() {
        int A = 6;
        System.out.println(A);
        System.out.println(Integer.toBinaryString(A));
        int B = ~6;
        System.out.println(B);
        System.out.println(Integer.toBinaryString(B));
        int C = A^A;
        int D = C^A;

        System.out.println();
    }
//------------------------------------------------------------------------------

    @Test
    public void test13() {
        int A = 6;
        int B = ~6;

        int C = A^A;
        int D = C^A;
        System.out.println(C);
        System.out.println(Integer.toBinaryString(C));
        System.out.println(D);
        System.out.println(Integer.toBinaryString(D));
    }
//------------------------------------------------------------------------------

    @Test
    public void test14() {
        // prints 144 --octal representation
        System.out.println(Integer.toString(100,8));
        // prints 1100100 --binary representation
        System.out.println(Integer.toString(100,2));
//prints 64 --Hex representation
        System.out.println(Integer.toString(100,16));
    }
//------------------------------------------------------------------------------

}
