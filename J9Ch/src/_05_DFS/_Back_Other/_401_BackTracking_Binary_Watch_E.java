package _05_DFS._Back_Other;
import org.junit.Test;

import java.util.*;

//  401. Binary Watch
//  https://leetcode.com/problems/binary-watch/description/
//  http://www.lintcode.com/zh-cn/problem/binary-time/
public class _401_BackTracking_Binary_Watch_E {
        //straightforward java answer
    public List<String> readBinaryWatch1(int num) {
        List<String> times = new ArrayList<>();
        for (int h=0; h<12; h++)
            for (int m=0; m<60; m++)
                if (Integer.bitCount(h * 64 + m) == num)
                    times.add(String.format("%d:%02d", h, m));
        return times;
    }
    @Test
    public void test01(){
        System.out.println(readBinaryWatch1(1));
    }
    //[0:01, 0:02, 0:04, 0:08, 0:16, 0:32, 1:00, 2:00, 4:00, 8:00]
//-------------------------------------------------------------------------/////
    //3ms Java Solution Using Backtracking and Idea of "Permutation and Combination"
    public List<String> readBinaryWatch2(int num) {
        List<String> res = new ArrayList<>();
        int[] nums1 = new int[]{8, 4, 2, 1}, nums2 = new int[]{32, 16, 8, 4, 2, 1};
        for(int i = 0; i <= num; i++) {
            List<Integer> list1 = generateDigit(nums1, i);
            List<Integer> list2 = generateDigit(nums2, num - i);
            for(int num1: list1) {
                if(num1 >= 12) continue;
                for(int num2: list2) {
                    if(num2 >= 60) continue;
                    res.add(num1 + ":" + (num2 < 10 ? "0" + num2 : num2));
                }
            }
        }
        return res;
    }

    private List<Integer> generateDigit(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        generateDigitHelper(nums, count, 0, 0, res);
        return res;
    }

    private void generateDigitHelper(int[] nums, int count, int pos, int sum, List<Integer> res) {
        if(count == 0) {
            res.add(sum);
            return;
        }

        for(int i = pos; i < nums.length; i++) {
            generateDigitHelper(nums, count - 1, i + 1, sum + nums[i], res);
        }
    }
    @Test
    public void test02(){
        System.out.println(readBinaryWatch2(1));
    }
    //[0:32, 0:16, 0:08, 0:04, 0:02, 0:01, 8:00, 4:00, 2:00, 1:00]
//------------------------------------------------------------------------------/
    // 9Ch
    public List<String> readBinaryWatch3(int num) {
        ArrayList<String> ans = new ArrayList<String>();
        ArrayList<ArrayList<Integer>> hour = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> min = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < 4; i++) {
            hour.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < 6; i++) {
            min.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < 12; i++) {
            int n = Integer.bitCount(i);
            hour.get(n).add(i);
        }
        for(int i = 0; i < 60; i++) {
            min.add(new ArrayList<Integer>());
            int n = Integer.bitCount(i);
            min.get(n).add(i);
        }
        for(int i = 0; i <= num && i < 4; i++) {
            for(int h = 0; h < hour.get(i).size(); h++) {
                for(int m = 0; m < min.get(num - i).size() && num - i < 6; m++) {
                    String string = hour.get(i).get(h).toString() + ":";
                    if(min.get(num - i).get(m) < 10) {
                        string += "0";
                    }
                    string += min.get(num - i).get(m).toString();
                    ans.add(string);
                }
            }
        }
        return ans;
    }
    @Test
    public void test03(){
        System.out.println(readBinaryWatch3(1));
    }
    //[0:01, 0:02, 0:04, 0:08, 0:16, 0:32, 1:00, 2:00, 4:00, 8:00]
//------------------------------------------------------------------------------/
}

/*
二进制时间

给了一个二进制显示时间的手表和一个非负整数 n, n 代表在给定时间表上 1 的数量, 返回所有可能的时间

 注意事项

输出的顺序没有要求.
小时不能包含前导零, 比如 "01:00" 是不允许的, 应该为 "1:00".
分钟必须由两位数组成, 可能包含前导零, 比如 "10:2" 是无效的, 应该为 "10:02".

样例
给出 n = 1
返回 ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 */


/*
For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".

 */