package _09_Math;

/*
LeetCode – Nth Digit (Java)

Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Java Solution

The solution of this problem is obvious when the following regularity is discovered.

1 - 9  : 9
10 - 99 : 90 * 2
100 - 999 : 900 * 3
1000 - 9999 : 9000 * 4
... ...
For example given n is 1000, we first -9 and then -180. The left is 811. The number is 100+810/3=370. The digit is the (810%3=0)th. Therefore, the digit is 3.
 */
public class Nth_Digit {
    public int findNthDigit(int m) {
        long n=m; // convert int to long
        long start=1,  len=1,  count=9;

        while(n>len*count){
            n=n-len*count;
            len++;
            count=count*10;
            start=start*10;
        }

        // identify the number
        start = start + (n-1)/len;

        // identify the digit
        return String.valueOf(start).charAt((int)((n-1)%len))-'0';
    }
}
