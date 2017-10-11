package DP.DP4;


/*


f[i] = f[i-A0] + f[i-A1] +...+ f[i-AN-1]

f[i]:多少种组合能拼出i
f[i-A0]：多少种组合能拼出i - A0
f[i-A1] +...+ f[i-AN-1]: 多少种组合能拼出i - AN-1

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

//Backpack VI
public class _7BackpackVI {

//////////////////////////////////////////////////////////////////
    /**
     * @param nums an integer array and all positive numbers, no duplicates
     * @param target an integer
     * @return an integer
     */
    public int backPackVI(int[] nums, int target) {
        // Write your code here
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 1; i <= target; ++i)
            for (int  j = 0; j < nums.length; ++j)
                if (i >= nums[j])
                    f[i] += f[i - nums[j]];

        return f[target];
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