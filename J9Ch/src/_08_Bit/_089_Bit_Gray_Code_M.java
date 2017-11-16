package _08_Bit;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//  89. Gray Code
//  https://leetcode.com/problems/gray-code/description/
//  http://www.lintcode.com/zh-cn/problem/gray-code/
public class _089_Bit_Gray_Code_M {

/////////////////////////////////////////////////////////////////////////
    //An accepted three line solution in JAVA
    //完全是Bit 操作
    //  i ^ i>>1 操作得到的相邻两个二进制数字在一位上为0或1

    public List<Integer> grayCode11(int n) {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < 1<<n; i++){
            result.add(i ^ i>>1);
        }
        return result;
    }

    public List<Integer> grayCode1(int n) {
        List<Integer> result = new LinkedList<>();
        System.out.println("1<<n: " + (1<<n));
        System.out.println("1<<n: " + Integer.toBinaryString(1<<n));
        for (int i = 0; i < 1<<n; i++){
            result.add(i ^ i>>1);
            System.out.print(i + ": ");
            if (Integer.toBinaryString(i).length() == 2) System.out.print("0");
            if (Integer.toBinaryString(i).length() == 1) System.out.print("00");
            System.out.printf("%s", Integer.toBinaryString(i));
//            if (Integer.toBinaryString(i>>1).length() == 2) System.out.print("0");
//            if (Integer.toBinaryString(i>>1).length() == 1) System.out.print("00");
            System.out.print(" ^ i: ");
            if (Integer.toBinaryString(i>>1).length() == 2) System.out.print("0");
            if (Integer.toBinaryString(i>>1).length() == 1) System.out.print("00");
            System.out.printf("%s", Integer.toBinaryString(i>>1));
            System.out.print(" = ");
            if (Integer.toBinaryString(i ^ i>>1).length() == 2) System.out.print("0");
            if (Integer.toBinaryString(i ^ i>>1).length() == 1) System.out.print("00");
            System.out.printf("%s\n", Integer.toBinaryString(i ^ i>>1));

        }
        return result;
    }
    @Test
    public void test01(){
        System.out.println(grayCode1(2));
//        for (int i:grayCode1(2)) {
//            System.out.printf("%2s\n", Integer.toBinaryString(i));
//        }
    }
    /*
            1<<n: 4
            1<<n: 100
            0:  0 ^ i:  0 =    0
            1:  1 ^ i:  0 =    1
            2: 10 ^ i:  1 =   11
            3: 11 ^ i:  1 =   10
            [0, 1, 3, 2]
     */

    @Test
    public void test02(){
        System.out.println(grayCode1(3));
//        for (int i:grayCode1(3)) {
//            System.out.printf("%3s\n", Integer.toBinaryString(i));
//        }
    }
    /*
                1<<n: 8
                1<<n: 1000
                0:  0 ^ i:  0 =    0
                1:  1 ^ i:  0 =    1
                2: 10 ^ i:  1 =   11
                3: 11 ^ i:  1 =   10
                4:100 ^ i: 10 =  110
                5:101 ^ i: 10 =  111
                6:110 ^ i: 11 =  101
                7:111 ^ i: 11 =  100
                [0, 1, 3, 2, 6, 7, 5, 4]
     */
    //java.util.MissingFormatArgumentException: Format specifier '%s'
/////////////////////////////////////////////////////////////////////////
/*
    Java short code with explanation

    how to go from i = 1 [0,1] to i=2?

    1.copy list of i=1 in reverse order

    0, 1 || 1, 0

    2 append "1" in front all numbers of the copy

    0, 1 || 11, 10
*/
    public List<Integer> grayCode022(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        int mask = 1;
        for (int i = 1; i <= n; i++) {
            // change from len i-1 to i
            for (int j = list.size() - 1; j >= 0; j--) {
                list.add(list.get(j) | mask);
            }
            mask <<= 1;
        }
        return list;
    }


/////////////////////////////////////////////////////////////////////////

    //Java Solution with Recursive
    public List<Integer> grayCode3(int n) {
        List<Integer> list = new ArrayList<Integer>();
        if (n==0) {
            list.add(0);
        } else {
            grayCode3(n, 0, list);
        }
        return list;
    }
    public void grayCode3(int n, int val, List<Integer> list) {
        int flag = Integer.bitCount(val)%2;
        if (n > 1) {
            grayCode3(n-1, val<<1|(flag&1), list);
            grayCode3(n-1, val<<1|(flag^1), list);
        } else {
            list.add(val<<1|(flag&1));
            list.add(val<<1|(flag^1));
        }
    }

    @Test
    public void test03(){
        //Returns the number of one-bits in the two's complement binary
        //* representation of the specified {@code int} value.
        System.out.println(Integer.toBinaryString(1));//1
        System.out.println(Integer.bitCount(1));      //1

        System.out.println(Integer.toBinaryString(5));//101
        System.out.println(Integer.bitCount(5));      //2

        System.out.println(Integer.toBinaryString(11));//1011
        System.out.println(Integer.bitCount(11));      //3
    }



/////////////////////////////////////////////////////////////////////////
    //    Share my solution
    //    My idea is to generate the sequence iteratively.
    // For example, when n=3, we can get the result based on n=2.

    //00,01,11,10 -> (000,001,011,010 ) (110,111,101,100).
    // The middle two numbers only differ at their highest bit,首尾对应的两个元素
    // while the rest numbers of part two are exactly symmetric of part one.
    // It is easy to see its correctness.
    //    Code is simple:
    //每次都是将最高位设为1 result.get(k) | 1 << i
    //将一个数第i位设为1的操作：| 1 << i
    //要理解并记住这种倒着处理的方法，肯定有用的
    public List<Integer> grayCode22(int n) {
        List<Integer> result = new ArrayList<Integer>();
        result.add(0);
        for(int i = 0; i < n; i++){
            int size = result.size();
            //每轮对已有结果倒着处理，从后往前的处理，将结果中每个元素的首位，如果为0就设为1
            //倒着操作才使得下面循环中两个元素result.get(k)和操作结果result.get(k) | 1 << i只是在首尾不同，一个为0一个为1
            //之前的全部结果，已经确保了每两个相邻元素只在一位有差别，这样只对首位0进行操作，可以确保新的结果和相邻结果依旧只在一位不同
            //每轮的首位相同，除第一个元素外，都为1。每轮中相邻元素之间差别来自于已有结果是确定相邻元素只有一位不同。
            //不同轮首元素与上一轮尾元素不同，是新一轮首位多了一位为1
            for(int k = size - 1; k >= 0; k--){
                result.add(result.get(k) | 1 << i);
            }
        }
        return result;
    }
    /*
    000,
    001,
    011,010,
    110,111,101,100
     */
    public List<Integer> grayCode2(int n) {
        List<Integer> result = new ArrayList<Integer>();
        result.add(0);
        for(int i = 0; i < n; i++){// 2 ^ i 位二进制
            int size = result.size();
            for(int k = size - 1; k >= 0; k--){
                System.out.printf("i:" + i + " k:" + k + ". result.get(k):%3s",
                        Integer.toBinaryString(result.get(k)));
                System.out.printf(" | 1 << i:%3s", Integer.toBinaryString(1 << i));
                System.out.printf(" = %4s\n", Integer.toBinaryString(result.get(k) | 1 << i));
                result.add(result.get(k) | 1 << i);

            }
        }
        return result;
    }
    @Test
    public void test21(){
        System.out.println(grayCode2(3));
//        for (int i:grayCode2(2)) {
//            System.out.printf("%2s\n", Integer.toBinaryString(i));
//        }
    }
    /*
            i:0 k:0. result.get(k):  0 | 1 << i:  1 =    1
            i:1 k:1. result.get(k):  1 | 1 << i: 10 =   11
            i:1 k:0. result.get(k):  0 | 1 << i: 10 =   10
            i:2 k:3. result.get(k): 10 | 1 << i:100 =  110
            i:2 k:2. result.get(k): 11 | 1 << i:100 =  111
            i:2 k:1. result.get(k):  1 | 1 << i:100 =  101
            i:2 k:0. result.get(k):  0 | 1 << i:100 =  100
            [0, 1, 3, 2, 6, 7, 5, 4]
     */
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

    /////////////////////////////////////////////////////////////////////////
    //jiuzhang DFS
    public ArrayList<Integer> grayCode5(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (n <= 1) {
            for (int i = 0; i <= n; i++){
                result.add(i);
            }
            return result;
        }

        result = grayCode5(n - 1);

        ArrayList<Integer> r1 = reverse(result);
        int x = 1 << (n-1);

        for (int i = 0; i < r1.size(); i++) {
            r1.set(i, r1.get(i) + x);
        }

        result.addAll(r1);
        return result;
    }

    public ArrayList<Integer> reverse (ArrayList<Integer> r) {
        ArrayList<Integer> rev = new ArrayList<Integer>();
        for (int i = r.size() - 1; i >= 0; i--) {
            rev.add(r.get(i));
        }
        return rev;
    }
    @Test
    public void test31(){
        System.out.println(grayCode3(2));
        for (int i:grayCode3(2)) {
            System.out.printf("%2s\n", Integer.toBinaryString(i));
        }
    }
/////////////////////////////////////////////////////////////////////////
/*
Java solution use a stack, easy to figure out, simple and interesting

4
    S SOY
    Reputation:  440
            0		0
            1		1

            00  		0
            01		1		+1
            11		3		--------
            10		2		-1

            000		0
            001		1		+1
            011		3		+2
            010		2		-1
            110		6		-----------
            111		7		+1
            101		5		-2
            100		4		-1


            0000		0
            0001		1		+1
            0011		3		+2
            0010		2		-1
            0110		6		+4
            0111		7		+1
            0101		5		-2
            0100		4		-1
            1100		12		-----------
            1101		13		+1
            1111		15		+2
            1110		14		-1
            1010		10		-4
            1011		11		+1
            1001		9		-2
            1000		8		-1
    From the above example, I believe you will find out that why use a stack here, all the post half is the pre half plus the Math.pow(2, i) in the symmetric form!!!
*/

    public List<Integer> grayCode4(int n) {
        List<Integer> list = new LinkedList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        list.add(0);
        int i = 0;
        while(i < n) {
            int len = list.size();
            int inc = (int)(Math.pow(2, i));
            for(int j=0; j<len; j++) {
                stack.push(list.get(j));
            }
            while(!stack.isEmpty()) {
                list.add(stack.pop() + inc);
            }
            i++;
        }
        return list;
    }


/////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////

}

/*
lint

格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个二进制的差异。

给定一个非负整数 n ，表示该代码中所有二进制的总数，请找出其格雷编码顺序。一个格雷编码顺序必须以 0 开始，并覆盖所有的 2n 个整数。
注意事项

对于给定的 n，其格雷编码顺序并不唯一。

根据以上定义， [0,2,3,1] 也是一个有效的格雷编码顺序。
您在真实的面试中是否遇到过这个题？
样例

给定 n = 2， 返回 [0,1,3,2]。其格雷编码顺序为：

00 - 0
01 - 1
11 - 3
10 - 2

挑战

O(2n) 时间复杂度。

 */



/*
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 */

