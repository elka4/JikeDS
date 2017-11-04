package _09_Math.mod;

/*
LeetCode – Palindrome Number (Java)

Determine whether an integer is a palindrome. Do this without extra space.

Thoughts

Problems related with numbers are frequently solved by / and %. No need of extra space is required. This problem is similar with the Reverse Integer problem.

Note: no extra space here means do not convert the integer to string, since string will be a copy of the integer and take extra space. The space take by div, left, and right can be ignored.
 */
public class Palindrome_Number {
    public boolean isPalindrome(int x) {
        //negative numbers are not palindrome
        if (x < 0)
            return false;

        // initialize how many zeros
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }

        while (x != 0) {
            int left = x / div;
            int right = x % 10;

            if (left != right)
                return false;

            x = (x % div) / 10;
            div /= 100;
        }

        return true;
    }
}
