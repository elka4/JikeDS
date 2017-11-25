package _08_Bit.HF_bit;


import org.junit.Test;

//  83. 落单的数 II
//  http://lintcode.com/zh-cn/problem/single-number-ii/
//
public class _08SingleNumber_II {
//----------------------------------------------------------------
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for(int i = 0; i < nums.length; i++){
            System.out.printf("%10s%32s\n", "ones: ", Integer.toBinaryString(ones));
            System.out.printf("%10s%32s\n", "nums[i]: ", Integer.toBinaryString(nums[i]));
            System.out.printf("%10s%32s\n", "nums[i]: ", Integer.toBinaryString(nums[i]));
            System.out.printf("%10s%32s\n", "ones ^ nums[i]: ", Integer.toBinaryString(ones ^ nums[i]));
            System.out.printf("%10s%32s\n", "twos: ", Integer.toBinaryString(twos));
            System.out.printf("%10s%32s\n", "~twos: ", Integer.toBinaryString(~twos));

            ones = (ones ^ nums[i]) & ~twos;
            System.out.printf("%10s%32s\n", "ones: ", Integer.toBinaryString(ones));


            System.out.println("....................................................");

            System.out.printf("%10s%32s\n", "twos: ", Integer.toBinaryString(twos));
            System.out.printf("%10s%32s\n", "nums[i]: ", Integer.toBinaryString(nums[i]));
            System.out.printf("%10s%32s\n", "nums[i]: ", Integer.toBinaryString(nums[i]));
            System.out.printf("%10s%32s\n", "twos ^ nums[i]: ", Integer.toBinaryString(twos ^ nums[i]));
            System.out.printf("%10s%32s\n", "ones: ", Integer.toBinaryString(ones));
            System.out.printf("%10s%32s\n", "~ones: ", Integer.toBinaryString(~ones));
            twos = (twos ^ nums[i]) & ~ones;
            System.out.printf("%10s%32s\n", "twos: ", Integer.toBinaryString(twos));

            System.out.println("---------------------------------------------------------");
        }
        return ones;
    }


    /*
    当a出现一次的时候，ones能保存a。当a出现两次的时候，twos能保存a。

    当a出现三次的时候，ones和twos都清零。

    所以，如果一个数值中所有的数都通过这个循环的话，出现三次的数都清零了，

    有一个数如果出现一次，它保存在ones中；如果出现两次的话保存在twos中。
     */
    public int singleNumber1(int[] nums) {
        int ones = 0, twos = 0;
        for(int i = 0; i < nums.length; i++){
            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        }
        return ones;
    }

/*
    ones:                                0
 nums[i]:                                1
 nums[i]:                                1
ones ^ nums[i]:                                1
    twos:                                0
   ~twos: 11111111111111111111111111111111
    ones:                                1
....................................................
    twos:                                0
 nums[i]:                                1
 nums[i]:                                1
twos ^ nums[i]:                                1
    ones:                                1
   ~ones: 11111111111111111111111111111110
    twos:                                0
---------------------------------------------------------
    ones:                                1
 nums[i]:                                1
 nums[i]:                                1
ones ^ nums[i]:                                0
    twos:                                0
   ~twos: 11111111111111111111111111111111
    ones:                                0
....................................................
    twos:                                0
 nums[i]:                                1
 nums[i]:                                1
twos ^ nums[i]:                                1
    ones:                                0
   ~ones: 11111111111111111111111111111111
    twos:                                1
---------------------------------------------------------
    ones:                                0
 nums[i]:                               10
 nums[i]:                               10
ones ^ nums[i]:                               10
    twos:                                1
   ~twos: 11111111111111111111111111111110
    ones:                               10
....................................................
    twos:                                1
 nums[i]:                               10
 nums[i]:                               10
twos ^ nums[i]:                               11
    ones:                               10
   ~ones: 11111111111111111111111111111101
    twos:                                1
---------------------------------------------------------
    ones:                               10
 nums[i]:                               11
 nums[i]:                               11
ones ^ nums[i]:                                1
    twos:                                1
   ~twos: 11111111111111111111111111111110
    ones:                                0
....................................................
    twos:                                1
 nums[i]:                               11
 nums[i]:                               11
twos ^ nums[i]:                               10
    ones:                                0
   ~ones: 11111111111111111111111111111111
    twos:                               10
---------------------------------------------------------
    ones:                                0
 nums[i]:                               11
 nums[i]:                               11
ones ^ nums[i]:                               11
    twos:                               10
   ~twos: 11111111111111111111111111111101
    ones:                                1
....................................................
    twos:                               10
 nums[i]:                               11
 nums[i]:                               11
twos ^ nums[i]:                                1
    ones:                                1
   ~ones: 11111111111111111111111111111110
    twos:                                0
---------------------------------------------------------
    ones:                                1
 nums[i]:                               11
 nums[i]:                               11
ones ^ nums[i]:                               10
    twos:                                0
   ~twos: 11111111111111111111111111111111
    ones:                               10
....................................................
    twos:                                0
 nums[i]:                               11
 nums[i]:                               11
twos ^ nums[i]:                               11
    ones:                               10
   ~ones: 11111111111111111111111111111101
    twos:                                1
---------------------------------------------------------
    ones:                               10
 nums[i]:                               10
 nums[i]:                               10
ones ^ nums[i]:                                0
    twos:                                1
   ~twos: 11111111111111111111111111111110
    ones:                                0
....................................................
    twos:                                1
 nums[i]:                               10
 nums[i]:                               10
twos ^ nums[i]:                               11
    ones:                                0
   ~ones: 11111111111111111111111111111111
    twos:                               11
---------------------------------------------------------
    ones:                                0
 nums[i]:                               10
 nums[i]:                               10
ones ^ nums[i]:                               10
    twos:                               11
   ~twos: 11111111111111111111111111111100
    ones:                                0
....................................................
    twos:                               11
 nums[i]:                               10
 nums[i]:                               10
twos ^ nums[i]:                                1
    ones:                                0
   ~ones: 11111111111111111111111111111111
    twos:                                1
---------------------------------------------------------
    ones:                                0
 nums[i]:                              100
 nums[i]:                              100
ones ^ nums[i]:                              100
    twos:                                1
   ~twos: 11111111111111111111111111111110
    ones:                              100
....................................................
    twos:                                1
 nums[i]:                              100
 nums[i]:                              100
twos ^ nums[i]:                              101
    ones:                              100
   ~ones: 11111111111111111111111111111011
    twos:                                1
---------------------------------------------------------
    ones:                              100
 nums[i]:                                1
 nums[i]:                                1
ones ^ nums[i]:                              101
    twos:                                1
   ~twos: 11111111111111111111111111111110
    ones:                              100
....................................................
    twos:                                1
 nums[i]:                                1
 nums[i]:                                1
twos ^ nums[i]:                                0
    ones:                              100
   ~ones: 11111111111111111111111111111011
    twos:                                0
---------------------------------------------------------
4
 */
    @Test
    public void test(){
        int[] nums = new int[]{1,1,2,3,3,3,2,2,4,1};
        System.out.println(singleNumber(nums));
    }
//----------------------------------------------------------------
}
/*
83. 落单的数 II

给出3*n + 1 个的数字，除其中一个数字之外其他每个数字均出现三次，找到这个数字。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出 [1,1,2,3,3,3,2,2,4,1] ，返回 4

挑战
一次遍历，常数级的额外空间复杂度

标签
贪心
相关题目
中等 落单的数 III 36 %
容易 落单的数 46 %
中等 主元素 III 29 %
中等 主元素 II 31 %
容易 主元素
 */