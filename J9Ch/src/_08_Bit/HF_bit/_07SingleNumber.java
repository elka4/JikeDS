package _08_Bit.HF_bit;


import org.junit.Test;

//  82. 落单的数
//  http://lintcode.com/zh-cn/problem/single-number/
//
public class _07SingleNumber {
//------------------------------------------------------------------
    //1
    public int singleNumber(int[] A) {
        if(A == null || A.length == 0) {
            return -1;
        }
        int rst = 0;
        for (int i = 0; i < A.length; i++) {
            rst ^= A[i];
        }
        return rst;
    }

//------------------------------------------------------------------
    //2
    public int singleNumber2(int[] nums) {
        int result = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            System.out.println(nums[i]);
            System.out.printf("%10s%32s\n", "nums[i]: ", Integer.toBinaryString(nums[i]));
            //^=把相同的位数全部归零，不同的位置全部为1.
            //只有一个不同的元素，那么其他元素全部归零。剩下的自然就是这个不同的元素。
            result ^= nums[i];
            System.out.printf("%10s%32s\n", "result: ", Integer.toBinaryString(nums[i]));
            System.out.println("-------------------------------------------------");
        }
        return result;
    }

    @Test
    public void test02(){
        System.out.println(singleNumber2(new int[]{1,2,2,1,3,4,3}));
    }

    /*
    1
 nums[i]:                                1
  result:                                1
-------------------------------------------------
2
 nums[i]:                               10
  result:                               10
-------------------------------------------------
2
 nums[i]:                               10
  result:                               10
-------------------------------------------------
1
 nums[i]:                                1
  result:                                1
-------------------------------------------------
3
 nums[i]:                               11
  result:                               11
-------------------------------------------------
4
 nums[i]:                              100
  result:                              100
-------------------------------------------------
3
 nums[i]:                               11
  result:                               11
-------------------------------------------------
4
     */
//------------------------------------------------------------------
}
/*
Given 2*n + 1 numbers, every numbers occurs twice except one, find it.

Example
Given [1,2,2,1,3,4,3], return 4
------------------------------------------------------------------
 */

/*
82. 落单的数

给出2*n + 1 个的数字，除其中一个数字之外其他每个数字均出现两次，找到这个数字。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出 [1,2,2,1,3,4,3]，返回 4

挑战
一次遍历，常数级的额外空间复杂度

标签
贪心
相关题目
中等 寻找重复的数 34 %
中等 落单的数 III 36 %
中等 落单的数 II 40 %
中等 主元素 III 29 %
中等 主元素 II 31 %
容易 主元素
------------------------------------------------------------------
 */