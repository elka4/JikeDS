package _5_Bit_Manipulation;

/*
Twitter Codility Problem – Max Binary Gap

Problem: Get maximum binary Gap.

For example, 9's binary form is 1001, the gap is 2.


 */
public class Maximum_Binary_Gap {
//    Java Solution 1
//
//    An integer x & 1 will get the last digit of the integer.

    public static int getGap(int N) {
        int max = 0;
        int count = -1;
        int r = 0;

        while (N > 0) {
            // get right most bit & shift right
            r = N & 1;
            N = N >> 1;

            if (0 == r && count >= 0) {
                count++;
            }

            if (1 == r) {
                max = count > max ? count : max;
                count = 0;
            }
        }

        return max;
    }
    //Time is O(n).

    //Java Solution 2

    public static int getGap2(int N) {
        int pre = -1;
        int len = 0;

        while (N > 0) {
            int k = N & -N;

            int curr = (int) Math.log(k);

            N = N & (N - 1);

            if (pre != -1 && Math.abs(curr - pre) > len) {
                len = Math.abs(curr - pre) + 1;
            }
            pre = curr;
        }

        return len;
    }
    //Time is O(log(n)).


}
