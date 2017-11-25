package _05_DFS._Back_Other;

import org.junit.Test;

//  357. Count Numbers with Unique Digits
//  https://leetcode.com/problems/count-numbers-with-unique-digits/description/
public class _357_BackTracking_Count_Numbers_with_Unique_Digits_M {
    //DP
    //JAVA DP O(1) solution.
    /*
    Following the hint. Let f(n) = count of number with unique digits of length n.

f(1) = 10. (0, 1, 2, 3, ...., 9)

f(2) = 9 * 9. Because for each number i from 1, ..., 9, we can pick j to form a 2-digit number ij and there are 9 numbers that are different from i for j to choose from.

f(3) = f(2) * 8 = 9 * 9 * 8. Because for each number with unique digits of length 2, say ij, we can pick k to form a 3 digit number ijk and there are 8 numbers that are different from i and j for k to choose from.

Similarly f(4) = f(3) * 7 = 9 * 9 * 8 * 7....

...

f(10) = 9 * 9 * 8 * 7 * 6 * ... * 1

f(11) = 0 = f(12) = f(13)....

any number with length > 10 couldn't be unique digits number.

The problem is asking for numbers from 0 to 10^n. Hence return f(1) + f(2) + .. + f(n)

As @4acreg suggests, There are only 11 different ans. You can create a lookup table for it. This problem is O(1) in essence.
     */
    public int countNumbersWithUniqueDigits1(int n) {
        if (n == 0)     return 1;

        int res = 10;
        int uniqueDigits = 9;
        int availableNumber = 9;
        while (n-- > 1 && availableNumber > 0) {
            uniqueDigits = uniqueDigits * availableNumber;
            res += uniqueDigits;
            availableNumber--;
        }
        return res;
    }
    @Test
    public void test01(){
        System.out.println(countNumbersWithUniqueDigits1(2));
    }

//------------------------------------------------------------------------------////////
/*
    Java, O(1), with explanation
    This is a digit combination problem. Can be solved in at most 10 loops.

    When n == 0, return 1. I got this answer from the test case.

    When n == 1, _ can put 10 digit in the only position. [0, ... , 10]. Answer is 10.

    When n == 2, _ _ first digit has 9 choices [1, ..., 9], second one has 9 choices excluding the already chosen one. So totally 9 * 9 = 81. answer should be 10 + 81 = 91

    When n == 3, _ _ _ total choice is 9 * 9 * 8 = 684. answer is 10 + 81 + 648 = 739

    When n == 4, _ _ _ _ total choice is 9 * 9 * 8 * 7.

            ...

    When n == 10, _ _ _ _ _ _ _ _ _ _ total choice is 9 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1

    When n == 11, _ _ _ _ _ _ _ _ _ _ _ total choice is 9 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1 * 0 = 0
*/

    public static int countNumbersWithUniqueDigits2(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 10, base = 9;
        for (int i = 2; i <= n && i <= 10; i++) {
            base = base * (9 - i + 2);
            ans += base;
        }
        return ans;
    }
    @Test
    public void test02(){
        System.out.println(countNumbersWithUniqueDigits2(2));
    }

//------------------------------------------------------------------------------////////
    //Backtracking solution
    public int countNumbersWithUniqueDigits3(int n) {
        if (n > 10) {
            return countNumbersWithUniqueDigits3(10);
        }
        int count = 1; // x == 0
        long max = (long) Math.pow(10, n);

        boolean[] used = new boolean[10];

        for (int i = 1; i < 10; i++) {//i就是数字的位数
            used[i] = true;
            count += search(i, max, used);
            used[i] = false;
        }

        return count;
    }

    private int search(long prev, long max, boolean[] used) {
        int count = 0;
        if (prev < max) {
            count += 1;
        } else {
            return count;
        }
        for (int i = 0; i < 10; i++) {
            if (!used[i]) {
                used[i] = true;
                long cur = 10 * prev + i;
                count += search(cur, max, used);
                used[i] = false;
            }
        }
        return count;
    }

    @Test
    public void test03(){
        System.out.println(countNumbersWithUniqueDigits3(2));
    }

//------------------------------------------------------------------------------////////

    public int countNumbersWithUniqueDigits4(int n) {
        if (n == 0) {
            return 2;
        }
        if (n == 1) {
            return 10; // should be 11
        }
        n = Math.min(n, 10);
        int sum = 10;
        int tmp = 9;
        for (int i = 1; i < n; i++) {
            tmp *= 10 - i;
            sum += tmp;
        }
        return sum;
    }
    @Test
    public void test04(){
        System.out.println(countNumbersWithUniqueDigits4(2));
    }

//-----------------------------------------------------------------------------/
    // 9Ch
    //  solution 1
    //DP Solution
    public int countNumbersWithUniqueDigits5(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        if (n > 10) {
            n = 10;
        }
        //f[i]表示不含0的i位数中满足条件的数的个数
        //g[i]表示含有0的i位数中满足条件的数的个数
        int[] f = new int[11];
        int[] g = new int[11];
        int ans = 10;
        g[0] = 1;
        g[1] = 9;
        for (int i = 2; i <= n; i++) {
            f[i] = f[i-1] * (11 - i) + g[i-2] * (11 - i);
            g[i] = g[i-1] * (10 - i);
            ans += f[i] + g[i];
        }
        return ans;
    }
    @Test
    public void test05(){
        System.out.println(countNumbersWithUniqueDigits5(2));
    }

//------------------------------------------------------------------------------////////
    // 9Ch
    // solution 2
    //Math Method
    public int countNumbersWithUniqueDigits6(int n) {
        if (n == 0) {
            return 1;
        }
        if (n > 10) {
            n = 10;
        }
        int ans = 1;
        int multiple = 9;
        for (int i = n - 1; i >= 0; i--) {
            if (i == 0) {
                ans += multiple;
            } else {
                ans += (n - i + 1) * multiple;
            }
            multiple = multiple * (10 - n + i - 1);
        }
        return ans;
    }
    @Test
    public void test06(){
        System.out.println(countNumbersWithUniqueDigits6(2));
    }
//-----------------------------------------------------------------------------/
}
/*
Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10^n.

Example:
Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])


 */
