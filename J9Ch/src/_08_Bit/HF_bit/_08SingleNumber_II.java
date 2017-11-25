package _08_Bit.HF_bit;


//  83. 落单的数 II
//  http://lintcode.com/zh-cn/problem/single-number-ii/
//
public class _08SingleNumber_II {
//----------------------------------------------------------------
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for(int i = 0; i < nums.length; i++){
            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        }
        return ones;
    }
//----------------------------------------------------------------
}
/*
83. 落单的数 II

 描述
 笔记
 数据
 评测
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