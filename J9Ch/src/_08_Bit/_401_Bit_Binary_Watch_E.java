package _08_Bit;
import java.util.*;
import org.junit.Test;

//  401. Binary Watch

//  https://leetcode.com/problems/binary-watch/description/
//  http://www.lintcode.com/zh-cn/problem/binary-time/
public class _401_Bit_Binary_Watch_E {
    //Simple Python+Java


    public List<String> readBinaryWatch(int num) {
        List<String> times = new ArrayList<>();
        for (int h=0; h<12; h++)
            for (int m=0; m<60; m++)
                if (Integer.bitCount(h * 64 + m) == num)
                    times.add(String.format("%d:%02d", h, m));
        return times;
    }
    //3ms Java Solution Using Backtracking and Idea of "Permutation and Combination"
    public class Solution2 {
        public List<String> readBinaryWatch(int num) {
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
    }



    //straightforward java answer
    public List<String> readBinaryWatch3(int num) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == num) {
                    result.add(String.format("%d:%02d", i, j));
                }
            }
        }
        return result;
    }

    public List<String> readBinaryWatch4(int num) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == num) {
                    result.add(String.format("%d:%02d", i, j));
                }
            }
        }
        return result;
    }


//    Java two loops, using bitCount, beat 90%

    public List<String> readBinaryWatch6(int num) {
        int bitCount = 0;
        List<String> ls = new ArrayList<String>();

        for(int i=0; i<12;i++){
            for(int j=0; j<60;j++){
                bitCount = Integer.bitCount(i)+Integer.bitCount(j);
                if (bitCount==num){
                    if(j<10){
                        ls.add(i+":0"+j);
                    }else{
                        ls.add(i+":"+j);
                    }
                }
            }
        }
        return ls;
    }

//    No need to compute hour bit counts each time. And when num is less than hour bit counts, just skip to next loop.

    public List<String> readBinaryWatch7(int num) {
        List<String> times = new ArrayList<>();
        for(int h=0; h <12; ++h) {
            int b1 = Integer.bitCount(h);
            if(b1 > num) continue;
            for(int m=0; m < 60; ++m) {
                if(Integer.bitCount(m) == num - b1) {
                    times.add(h + ":" + (m < 10 ? "0" : "") + m);
                }
            }
        }
        return times;
    }
//------------------------------------------------------------------------------

/*    Simple Java AC solution with Explanation

9
    johnyrufus16
    Reputation:  157
    The logic is based on DFS, to generate nCk. Represent all the lights by an array of n = 10.
    Then generate all possible values of the array by setting all possible k bits.
    Convert these array positions into their corresponding times, making sure to handle any time that exceeds hours >= 12 and minutes > 59

    For A[10] with indices 0 to 9

            0 1 2 3 represents the Hour lights
4 5 6 7 8 9 represents the Minute lights*/

    public  List<String> readBinaryWatch8(int num) {
        List<String> list = new ArrayList<>();
        dfs(new int[10], 0, 0, list, num);
        return list;
    }

    private static void dfs(int[] a, int i, int k, List<String> list, int num) {
        if(k == num) {
            String res = getTime(a);
            if(res != null)
                list.add(res);
            return;
        }
        if(i == a.length) {
            return;
        }
        a[i] = 0;
        dfs(a, i+1, k, list, num);

        a[i] = 1;
        dfs(a, i+1, k+1, list, num);

        a[i] = 0;
    }

    private static String getTime(int[] a) {
        int hours = 0;
        for(int i = 0; i < 4; i++) {
            if(a[i] == 1) {
                hours = hours + (int)Math.pow(2, i);
            }
        }

        int minutes = 0;
        for(int i = 4; i < 10; i++) {
            if(a[i] == 1) {
                minutes = minutes + (int)Math.pow(2, i-4);
            }
        }
        String min = "" + minutes;
        if(minutes  <  10)
            min = "0" + min;
        String res = hours + ":" + min;
        if(hours  >= 12  ||  minutes  >=  60)
            return null;
        return res;
    }


//------------------------------------------------------------------------------


//------------------------------------------------------------------------------
// 9Ch
public class Jiuzhang {
    public List<String> readBinaryWatch(int num) {
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
}

//------------------------------------------------------------------------------
}
/*
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
A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.


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