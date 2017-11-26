package _09_Math;
import java.util.*;

//  202. Happy Number
//  https://leetcode.com/problems/happy-number/description/
//  http://www.lintcode.com/zh-cn/problem/happy-number/
//  3:
//
public class _202_Happy_Number {
//-----------------------------------------------------------------------
/*    Beat 90% Fast Easy Understand Java Solution with Brief Explanation
    The idea is to use one hash set to record sum of every digit square of every number occurred. Once the current sum cannot be added to set, return false; once the current sum equals 1, return true;*/
    class Solution1{
        public boolean isHappy(int n) {
            Set<Integer> inLoop = new HashSet<Integer>();
            int squareSum,remain;
            while (inLoop.add(n)) {
                squareSum = 0;
                while (n > 0) {
                    remain = n%10;
                    squareSum += remain*remain;
                    n /= 10;
                }
                if (squareSum == 1)
                    return true;
                else
                    n = squareSum;

            }
            return false;
        }
    }

//-----------------------------------------------------------------------
    //2
    //O(1) space Java solution
    public class Solution2 {
        public boolean isHappy(int n) {
            int x = n;
            int y = n;
            while(x>1){
                x = cal(x) ;
                if(x==1) return true ;
                y = cal(cal(y));
                if(y==1) return true ;

                if(x==y) return false;
            }
            return true ;
        }
        public int cal(int n){
            int x = n;
            int s = 0;
            while(x>0){
                s = s+(x%10)*(x%10);
                x = x/10;
            }
            return s ;
        }
    }
//-----------------------------------------------------------------------
    // 9Ch
    public class Jiuzhang {
        private int getNextHappy(int n) {
            int sum = 0;
            while (n != 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            return sum;
        }

        public boolean isHappy(int n) {
            HashSet<Integer> hash = new HashSet<Integer>();
            while (n != 1) {
                if (hash.contains(n)) {
                    return false;
                }
                hash.add(n);
                n = getNextHappy(n);
            }
            return true;
        }
    }

//-----------------------------------------------------------------------
}
/*
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
 */



/*
488. 快乐数

 描述
 笔记
 数据
 评测
写一个算法来判断一个数是不是"快乐数"。

一个数是不是快乐是这么定义的：对于一个正整数，每一次将该数替换为他每个位置上的数字的平方和，然后重复这个过程直到这个数变为1，或是无限循环但始终变不到1。如果可以变为1，那么这个数就是快乐数。

样例
19 就是一个快乐数。

1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1
标签
哈希表 数学
相关题目
容易 各位相加 36 %
容易 丑数 35 %
中等 丑数 II
 */