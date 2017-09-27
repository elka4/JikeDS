package Classification.Bit_Manipulation_26;
import java.util.*;

public class b_137_Single_Number_II {
    /*
    lintcode: Given 3*n + 1 numbers, every numbers occurs triple times except one, find it.

    Example
    Given [1,1,2,3,3,3,2,2,4,1] return 4
     */

    /*
    看了一些资料，说是用到了bit vector
    https://www.youtube.com/watch?v=SYoJ6gUXZvc
    Ref: http://blog.csdn.NET/fightforyourdream/article/details/14634123
    比较懒惰，就直接写出来了，也没有太仔细研究什么是Bit Vector. 大概看了一下 应该是就是bit位来存储状态的一个数据结构啦。
    检查一个bit位是：array[i>>5] & 1 << (i & 0x1f)
     */
    public int singleNumber_lint(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int result=0;
        int[] bits=new int[32];
        for (int i = 0; i < 32; i++) {
            for(int j = 0; j < A.length; j++) {
                bits[i] += A[j] >> i & 1;
                bits[i] %= 3;
            }

            result |= (bits[i] << i);
        }
        return result;
    }

    //代码如下所示，用一个32位的数的每一位表示某一位出现几次，出现3次就给它归零：
    public int singleNumber_lint2(int[] A) {
        if (A == null) {
            return 0;
        }

        int rst = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int j = 0; j < A.length; j++) {
                if (((A[j] >> i) & 1) == 1) {
                    sum ++;
                    sum = sum%3;
                }
            }
            rst |= sum << i;
        }

        return rst;
    }

//////////////////////////////////////////////////////////////////

    //http://blog.csdn.net/fightforyourdream/article/details/14634123

    // Time: O(n), space: O(n)
    public static int singleNumber_hash(int[] A) {
        Hashtable<Integer, Integer> ht = new Hashtable<Integer, Integer>();
        for(int i=0; i<A.length; i++){
            Integer val = ht.get(A[i]);
            if(val != null){
                ht.put(A[i], val+1);
            }else{
                ht.put(A[i], 1);
            }
        }

        Set<Integer> set = ht.keySet();
        for (Integer i : set) {
            if(ht.get(i) != 3){
                return i;
            }
        }
        return 0;
    }

    // Time: O(n), space: O(1)
    // 基本思想是每个数都可以表示成二进制形式，进而统计每个数在每一位出现的次数
    public static int singleNumber_bit(int[] A) {
        // 创建一个长度为32的数组countsPerBit，
        // countsPerBit[i]表示A中所有数字在i位出现的次数
        int[] countsPerBit = new int[32];
        int result = 0;
        for(int i=0; i<32; i++){
            for(int j=0; j<A.length; j++){
                if(((A[j] >> i) & 1) == 1){
                    countsPerBit[i] = (countsPerBit[i] + 1) % 3;
                }
            }
            result |= (countsPerBit[i] << i);
        }
        return result;
    }

    public int singleNumber_bit2(int[] A) {
        int[] buf = new int[32];

        for(int i=0; i<32; i++){
            int cnt = 0;
            int p = 1;
            p <<= i;
            for(int j=0; j<A.length; j++){
                int tmp = A[j];
                if((tmp & p) != 0){ //!!!  cannot use ==1
                    cnt++;
                }
            }
            buf[i] = cnt%3;
        }

        int res = 0;
        for(int i=0; i<32; i++){
            res |= (buf[i] << i);
        }
        return res;
    }



    public int singleNumber2_Bit3(int[] A) {
        Hashtable<Integer, Integer> ht = new Hashtable<Integer,Integer>();
        for(int i=0; i<A.length; i++){
            Integer val = ht.get(A[i]);
            if(val != null){
                ht.put(A[i],val+1);
            }else{
                ht.put(A[i],1);
            }
        }

        for(Integer key : ht.keySet()){
            if(ht.get(key) != 3){
                return key;
            }
        }

        return -1;
    }
//////////////////////////////////////////////////////////////////

    public int singleNumber(int[] A) {
        int ones = 0, twos = 0;
        for(int i = 0; i < A.length; i++){
            ones = (ones ^ A[i]) & ~twos;
            twos = (twos ^ A[i]) & ~ones;
        }
        return ones;
    }

///////////////////////////////////////////////////////////////////

    //https://leetcode.com/problems/single-number-ii/tabs/discuss

    public int singleNumber2(int[] nums) {
        //we need to implement a tree-time counter(base 3) that if
        // a bit appears three time ,it will be zero.
        //#curent  income  ouput
        //# ab      c/c       ab/ab
        //# 00      1/0       01/00
        //# 01      1/0       10/01
        //# 10      1/0       00/10
        // a=~abc+a~b~c;
        // b=~a~bc+~ab~c;
        int a=0;
        int b=0;
        for(int c:nums){
            int ta=(~a&b&c)|(a&~b&~c);
            b=(~a&~b&c)|(~a&b&~c);
            a=ta;
        }
        //we need find the number that is 01,10 => 1, 00 => 0.
        return a|b;

    }
///////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////


}

/*
Given an array of integers, every element appears three times except
 for one, which appears exactly once. Find that single one.

Note:
Your algorithm should have a linear runtime complexity.
Could you implement it without using extra memory?
 */
