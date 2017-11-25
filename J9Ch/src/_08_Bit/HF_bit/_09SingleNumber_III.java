package _08_Bit.HF_bit;

import java.util.*;
import org.junit.Test;

//  84. Single Number III
//  http://lintcode.com/zh-cn/problem/single-number-iii/
//
public class _09SingleNumber_III {
//---------------------------------------------------------------------
    public int[] singleNumber(int[] nums) {
        //用于记录，区分“两个”数组
        int diff = 0;
        for(int i = 0; i < nums.length; i ++) {
            diff ^= nums[i];
        }
        //取最后一位1
        //先介绍一下原码，反码和补码
        //原码，就是其二进制表示（注意，有一位符号位）
        //反码，正数的反码就是原码，负数的反码是符号位不变，其余位取反
        //补码，正数的补码就是原码，负数的补码是反码+1
        //在机器中都是采用补码形式存
        //diff & (-diff)就是取diff的最后一位1的位置
        System.out.printf("%10s%32s\n", "diff: ", Integer.toBinaryString(diff));
        System.out.printf("%10s%32s\n", "-diff: ", Integer.toBinaryString(-diff));


        diff &= -diff;
        System.out.printf("%10s%32s\n", "diff &= -diff: ", Integer.toBinaryString(diff));


        int[] rets = {0, 0};
        for(int i = 0; i < nums.length; i ++) {
            //分属两个“不同”的数组
            if ((nums[i] & diff) == 0) {
                rets[0] ^= nums[i];
            }
            else {
                rets[1] ^= nums[i];
            }
        }
        return rets;
    }

    public int[] singleNumber1(int[] nums) {
        int diff = 0;
        for(int i = 0; i < nums.length; i ++) {
            diff ^= nums[i];
        }
        diff &= -diff;
        int[] rets = {0, 0};
        for(int i = 0; i < nums.length; i ++) {
            if ((nums[i] & diff) == 0) {
                rets[0] ^= nums[i];
            }
            else {
                rets[1] ^= nums[i];
            }
        }
        return rets;
    }
/*
http://blog.csdn.net/guoziqing506/article/details/52231357

题目描述：给出2*n + 2个的数字，除其中两个数字之外其他每个数字均出现两次，找到这两个数字。
样例：给出 [1,2,2,3,4,4,5,3]，返回 1和5
挑战 ：O(n)时间复杂度，O(1)的额外空间复杂度

我们之前已经做过两道类似的题目，分别是落单的数，落单的数 II，思路都是位运算。这道题也不例外。

不过这道题想出方法来倒还真不太容易，至少我当时没想出来，也是后来查了别人的做法，才知道的，在此，我将别人的方法用我的话再说一遍，努力让它更好理解。

当然，首先想到的就是跟之前2*n + 1个数时的情况一样（详见：落单的数），先将所有的数异或一遍，这样，我们就将数组中那两个不同的数异或到了一个结果中（此处不懂的话看刚才给的链接）。现在的难处在于无法将这个结果拆开，拆成我们想要的那两个不同的数。

怎么办呢？我们如果对二进制足够熟悉，就不难得出这样一个结论，这个异或的结果（为方便描述，记为Xor）的二进制位中为1的位，必然是这两个不同的数（方便起见，记为first 和 second）不同的位，也就是说，first和second在这些位中一个是1，一个是0。不失一般性，我们就找Xor中第一个为1的位，将这个位数记为k.

那么，一定隐含了这样一个逻辑：在成对的2*n个数当中，一定有2x个数的第k位是1，而有2y个数的第k位是0，其中，x + y = n，所以，

换个说法，既然Xor的第k为是1，那我们不妨假设first的第k位是0，而second的第k位是1。那么，如果令x个数第k位为1的数，和second一起，与Xor异或，就能得到first，这个道理与2n + 1时是一样的。而再令first与Xor异或，就能得到second.

于是，可以按以下步骤操作：
1. 将数组中所有的数异或，得到一个结果，记为Xor
2. 查出Xor中第一个为1的位（也就是为1的最小的位），记为k
3. 查出数组中所有第k位为1的数（这里面当然包括second）与Xor异或，得到first
4. 将first与Xor异或，得到second

 */
    @Test
    public void test(){
        System.out.println(singleNumber(new int[]{1,2,2,3,4,4,5,3})[0]);
    }

    /*  http://blog.csdn.net/wutingyehe/article/details/51085309
    题目描述：给出2*n + 2个的数字，除其中两个数字之外其他每个数字均出现两次，找到这两个数字。
样例：给出 [1,2,2,3,4,4,5,3]，返回 1和5
挑战 ：O(n)时间复杂度，O(1)的额外空间复杂度

我们之前已经做过两道类似的题目，分别是落单的数，落单的数 II，思路都是位运算。这道题也不例外。

不过这道题想出方法来倒还真不太容易，至少我当时没想出来，也是后来查了别人的做法，才知道的，在此，我将别人的方法用我的话再说一遍，努力让它更好理解。

当然，首先想到的就是跟之前2*n + 1个数时的情况一样（详见：落单的数），先将所有的数异或一遍，这样，我们就将数组中那两个不同的数异或到了一个结果中（此处不懂的话看刚才给的链接）。现在的难处在于无法将这个结果拆开，拆成我们想要的那两个不同的数。

怎么办呢？我们如果对二进制足够熟悉，就不难得出这样一个结论，这个异或的结果（为方便描述，记为Xor）的二进制位中为1的位，必然是这两个不同的数（方便起见，记为first 和 second）不同的位，也就是说，first和second在这些位中一个是1，一个是0。不失一般性，我们就找Xor中第一个为1的位，将这个位数记为k.

那么，一定隐含了这样一个逻辑：在成对的2*n个数当中，一定有2x个数的第k位是1，而有2y个数的第k位是0，其中，x + y = n，所以，

换个说法，既然Xor的第k为是1，那我们不妨假设first的第k位是0，而second的第k位是1。那么，如果令x个数第k位为1的数，和second一起，与Xor异或，就能得到first，这个道理与2n + 1时是一样的。而再令first与Xor异或，就能得到second.

于是，可以按以下步骤操作：
1. 将数组中所有的数异或，得到一个结果，记为Xor
2. 查出Xor中第一个为1的位（也就是为1的最小的位），记为k
3. 查出数组中所有第k位为1的数（这里面当然包括second）与Xor异或，得到first
4. 将first与Xor异或，得到second
     */

    @Test
    public void tst02(){
        int diff = 5;
        diff &= -diff;
        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(5 & (-5)));
        System.out.println("....................................");
        System.out.println(Integer.toBinaryString(64));
        System.out.println(Integer.toBinaryString(64 & (-64)));
        System.out.println("....................................");
    }
//---------------------------------------------------------------------
/*
解题

根据落单的数I，可以想到，所有的数进行异或运行的结果就是所求两个数的异或结果。

这个异或的结果，二进制数是1的位置说明这两个数对应的二进制位不相同。然后再怎么还原？？？
参考，理解的不是很透，找到第k位后，再判断数组中所以数的第k位是0 还是1，,出现两次的数对求解无影响，
通过这个第k为把数组分成两类，也就把两个数分开了，这里的第k位在a、b中一定不相同的，一定是一个0一个1。
 */
    public class Solution2 {
        /**
         * @param A : An integer array
         * @return : Two integers
         */
        public List<Integer> singleNumberIII(int[] A) {
            // write your code here
            int axorb = 0;
            LinkedList<Integer> res = new LinkedList<Integer>();
            for( int i = 0; i <A.length;i++){
                axorb ^= A[i];
            }
            int a = 0;
            int b = 0;
            int k = 0;
            while( axorb % 2==0){
                axorb >>= 1;
                k++;
            }
            for(int i=0;i< A.length;i++){
                int tmp =( A[i]>>k)%2;
                if(tmp==0)
                    a ^= A[i];
                else
                    b ^= A[i];
            }
            res.add(a);
            res.add(b);
            return res;
        }
    }
//---------------------------------------------------------------------
}
/*
84. 落单的数 III

给出2*n + 2个的数字，除其中两个数字之外其他每个数字均出现两次，找到这两个数字。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出 [1,2,2,3,4,4,5,3]，返回 1和5

挑战
O(n)时间复杂度，O(1)的额外空间复杂度

标签
LintCode 版权所有 贪心
相关题目
中等 落单的数 II 40 %
容易 落单的数 46 %
中等 主元素 III 29 %
中等 主元素 II 31 %
容易 主元素
 */