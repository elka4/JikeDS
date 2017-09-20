package HF.HF2_Algorithms_DS_I._2Hash_String_Char;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/*

思路:
• 要在o(1)的时间内插入删除，只能hash。那hash可以getRandom吗?
– 不太好做
• 什么数据结构比较好getRandom?
– 数组
• 考虑hash与数组结合起来用，hash插入一个，数组也插入一个。那么问题
来了，数组删除元素怎么办? – 与最后插入的一个元素交换
• 那怎么o(1)时间在数组中找到要删除元素(要交换)的位置? – 用hash将元素的位置记下来
 */

/*
算法:
• 插入:
– 数组末尾加入这个元素
– Hash这个元素存下数组中的下标
• 删除:
– 通过hash找到这个元素在数组中的位置
– 数数组中这个元素和数组的末尾元素交换，交换后删除
– Hash中删除这个元素，更新数组原末尾元素现在在数组中的位置
• Pick:
– 数组中random一个返回
 */

/*
• Company Tags: Google Amazon Facebook
考点:
• 两种数据结构的综合应用
 */

/*
能力维度:
1. 理解问题
3. 基础数据结构/算法
4. 逻辑思维/算法优化能力
6. 算法分析(时间/空间复杂度)
 */

//Longest Consecutive Sequence
public class _4LongestConsecutiveSequence {
    //jiuzhang
    /**
     * @param nums: A list of integers
     * @return an integer
     */
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int longest = 0;
        for (int i = 0; i < nums.length; i++) {
            int down = nums[i] - 1;
            while (set.contains(down)) {
                set.remove(down);
                down--;
            }

            int up = nums[i] + 1;
            while (set.contains(up)) {
                set.remove(up);
                up++;
            }

            longest = Math.max(longest, up - down - 1);
        }

        return longest;
    }

//////////////////////////////////////////////////////////////

    // version: 高频题班
    /**
     * @param num: A list of integers
     * @return an integer
     */
    public int longestConsecutive2(int[] num) {
        // write you code here
        Set<Integer> set = new HashSet<>();
        for (int item : num) {
            set.add(item);
        }

        int ans = 0;
        for (int item : num) {
            if (set.contains(item)) {
                set.remove(item);

                int pre = item - 1;
                int next = item + 1;
                while (set.contains(pre)) {
                    set.remove(pre);
                    pre--;
                }
                while (set.contains(next)) {
                    set.remove(next);
                    next++;
                }
                ans = Math.max(ans, next - pre - 1);
            }
        }
        return ans;
    }

    @Test
    public void test(){
        int[] arr = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive2(arr));
    }
//////////////////////////////////////////////////////////////



//////////////////////////////////////////////////////////////

}
/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Have you met this question in a real interview? Yes
Clarification
Your algorithm should run in O(n) complexity.

Example
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 */
