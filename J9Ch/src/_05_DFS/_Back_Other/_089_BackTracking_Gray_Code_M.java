package _05_DFS._Back_Other;
import a.a.P;
import org.junit.Test;

import java.util.*;

//  89. Gray Code
//  https://leetcode.com/problems/gray-code/description/
//  http://www.lintcode.com/zh-cn/problem/gray-code/
public class _089_BackTracking_Gray_Code_M {

/////////////////////////////////////////////////////////////////////////
    //    An accepted three line solution in JAVA
    public List<Integer> grayCode1(int n) {
        List<Integer> result = new LinkedList<>();
        System.out.println("1<<n: " + (1<<n));
        System.out.println("1<<n: " + Integer.toBinaryString(1<<n));
        for (int i = 0; i < 1<<n; i++){
            result.add(i ^ i>>1);
            System.out.printf(i + ":%3s", Integer.toBinaryString(i));
            System.out.printf(" ^ i:%3s", Integer.toBinaryString(i>>1));
            System.out.printf(" = %4s\n", Integer.toBinaryString(i ^ i>>1));

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
    //    Share my solution
    //    My idea is to generate the sequence iteratively.
    // For example, when n=3, we can get the result based on n=2.

    //00,01,11,10 -> (000,001,011,010 ) (110,111,101,100).
    // The middle two numbers only differ at their highest bit,
    // while the rest numbers of part two are exactly symmetric of part one.
    // It is easy to see its correctness.
    //    Code is simple:
    public List<Integer> grayCode2(int n) {
        List<Integer> result = new ArrayList<Integer>();
        result.add(0);
        for(int i = 0; i < n; i++){
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
    //jiuzhang
    public ArrayList<Integer> grayCode3(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (n <= 1) {
            for (int i = 0; i <= n; i++){
                result.add(i);
            }
            return result;
        }

        result = grayCode3(n - 1);

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

