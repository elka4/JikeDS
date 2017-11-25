package _08_Bit;

/*
Twitter Codility Problem – Max Binary Gap

Problem: Get maximum binary Gap.

For example, 9's binary form is 1001, the gap is 2.
 */


public class Maximum_Binary_Gap {
//------------------------------------------------------------------------------

//    Java Solution 1
//
//    An integer x & 1 will get the last digit of the integer.

    /*
    每次检查最右位是1还是0，然后N右移一位
    如果最右位是0则count++
    如果最右位是1则update max，重设count为0

    注意count设为-1，以及count >= 0的条件，好确保只找1和1之间的0，而不计算1。。。0的0
     */
    public int getGap(int N) {
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

//------------------------------------------------------------------------------


    public int getGap2(int N) {
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
//------------------------------------------------------------------------------


}
