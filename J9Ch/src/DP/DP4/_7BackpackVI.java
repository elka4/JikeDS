package DP.DP4;


/*


f[i] = f[i-A0] + f[i-A1] +...+ f[i-AN-1]

f[i]:多少种组合能拼出i
f[i-A0]：多少种组合能拼出i - A0
f[i-A1] +...+ f[i-AN-1]: 多少种组合能拼出i - AN-1

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */

/*
Backpack VI:
http://www.lintcode.com/en/problem/backpack-vi/

这道题就是combination sum，代码如下:

int backPackVI(vector<int>& nums, int target) {
 // Write your code here
    if(target <= 0 || nums.empty()) return 0;
    int n = nums.size();
    vector<int> dp(target+1, 0);
    dp[0] = 1;
    for(int i=1; i<=target; i++){
        for(int j=0; j<n; j++){
            if(i >= nums[j]) dp[i] += dp[i-nums[j]];
        }
    }
    return dp[target];
 }

作者：stepsma
链接：http://www.jianshu.com/p/7f192e75d734
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

import com.intellij.codeInsight.template.postfix.templates.SoutPostfixTemplate;
import org.junit.Test;

//Backpack VI
public class _7BackpackVI {
    // 9CH DP, 不记录状态
    public int backPackVI (int[] A, int T) {
        int[] f = new int[T + 1];
        f[0] = 1;
        int i, j;
        for (i = 1; i <= T; i++) {
            f[i] = 0;
            for (j = 0; j < A.length; j++) {
                if (i >= A[j]) {
                    f[i] += f[i - A[j]];
                }
            }
        }
        return f[T];
    }
    /*
    Given nums = [1, 2, 4], target = 4 return 6
                   5, 7, 13, 17   32 return 22
     */
    @Test
    public void test01(){
        int[] A = {1, 2, 4};
        int T = 4;
        System.out.println(backPackVI(A, T));

        A = new int[]{5, 7, 13, 17};
        T = 32;
        System.out.println(backPackVI(A, T));

    }
//////////////////////////////////////////////////////////////////

    // 9Ch DP, 记录状态
    public int backPackVI2 (int[] A, int T) {
        int[] f = new int[T + 1];

        // pai[i]: if f[i] >= 1, 最后一个数字可以是pai[i]
        int[] pai = new int[T + 1];

        f[0] = 1;
        int i, j;
        for (i = 1; i <= T; i++) {
            f[i] = 0;
            for (j = 0; j < A.length; j++) {
                if (i >= A[j]) {
                    f[i] += f[i - A[j]];
                    if (f[i - A[j]] > 0) {
                        pai[i] = A[j];
                    }
                }
            }
        }

        if (f[T] > 0) {
            i = T;
            System.out.println(i + "=");
            while (i != 0) {
                System.out.println(pai[i]);
                // sum is i now
                // last number CAN be pai[i]
                // previous sum is i - pai[i]
                i -= pai[i];
            }

        }
        return f[T];

    }

    @Test
    public void test02(){
        int[] A = {1, 2, 4};
        int T = 4;
        System.out.println(backPackVI2(A, T));

        A = new int[]{5, 7, 13, 17};
        T = 32;
        System.out.println(backPackVI2(A, T));
    }
//////////////////////////////////////////////////////////////////
    /**
     * @param nums an integer array and all positive numbers, no duplicates
     * @param target an integer
     * @return an integer
     */
    public int backPackVI3(int[] nums, int target) {
        // Write your code here
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 1; i <= target; ++i)
            for (int  j = 0; j < nums.length; ++j)
                if (i >= nums[j])
                    f[i] += f[i - nums[j]];

        return f[target];
    }
    @Test
    public void test03(){
        int[] A = {1, 2, 4};
        int T = 4;
        System.out.println(backPackVI3(A, T));
    }
//////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////
}
/*
Given an integer array nums with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

 Notice

A number in the array can be used multiple times in the combination.
Different orders are counted as different combinations.

Have you met this question in a real interview? Yes
Example
Given nums = [1, 2, 4], target = 4

The possible combination ways are:
[1, 1, 1, 1]
[1, 1, 2]
[1, 2, 1]
[2, 1, 1]
[2, 2]
[4]
return 6
 */

/*
给出一个都是正整数的数组 nums，其中没有重复的数。从中找出所有的和为 target 的组合个数。

 注意事项

一个数可以在组合中出现多次。
数的顺序不同则会被认为是不同的组合。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出 nums = [1, 2, 4], target = 4
可能的所有组合有：

[1, 1, 1, 1]
[1, 1, 2]
[1, 2, 1]
[2, 1, 1]
[2, 2]
[4]
返回 6
 */