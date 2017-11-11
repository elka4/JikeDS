package _05_DFS._Back_Other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//  89. Gray Code
//  https://leetcode.com/problems/gray-code/description/
//  http://www.lintcode.com/zh-cn/problem/gray-code/
public class _089_BackTracking_Gray_Code_M {

/////////////////////////////////////////////////////////////////////////
    //    An accepted three line solution in JAVA
    class Solution1{
        public List<Integer> grayCode(int n) {
            List<Integer> result = new LinkedList<>();
            for (int i = 0; i < 1<<n; i++) result.add(i ^ i>>1);
            return result;
        }
    }

    class Solution2{
        //    Share my solution
        //    My idea is to generate the sequence iteratively.
        // For example, when n=3, we can get the result based on n=2.

        //00,01,11,10 -> (000,001,011,010 ) (110,111,101,100).
        // The middle two numbers only differ at their highest bit,
        // while the rest numbers of part two are exactly symmetric of part one.
        // It is easy to see its correctness.
        //    Code is simple:

        public List<Integer> grayCode3(int n) {
            List<Integer> rs=new ArrayList<Integer>();
            rs.add(0);
            for(int i=0;i<n;i++){
                int size=rs.size();
                for(int k=size-1;k>=0;k--)
                    rs.add(rs.get(k) | 1<<i);
            }
            return rs;
        }

    }


/////////////////////////////////////////////////////////////////////////

//jiuzhang
public class Jiuzhang {
    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (n <= 1) {
            for (int i = 0; i <= n; i++){
                result.add(i);
            }
            return result;
        }
        result = grayCode(n - 1);
        ArrayList<Integer> r1 = reverse(result);
        int x = 1 << (n-1);

        for (int i = 0; i < r1.size(); i++) {
            r1.set(i, r1.get(i) + x);
        }

        result.addAll(r1);
        return result;
    }

    public ArrayList<Integer> reverse (ArrayList<Integer> r) {
        ArrayList<Integer> rev = new ArrayList<Integer>();
        for (int i = r.size() - 1; i >= 0; i--) {
            rev.add(r.get(i));
        }
        return rev;
    }
}
/////////////////////////////////////////////////////////////////////////



}
/*
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.


 */

/*
lint

格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个二进制的差异。

给定一个非负整数 n ，表示该代码中所有二进制的总数，请找出其格雷编码顺序。一个格雷编码顺序必须以 0 开始，并覆盖所有的 2n 个整数。
注意事项

对于给定的 n，其格雷编码顺序并不唯一。

根据以上定义， [0,2,3,1] 也是一个有效的格雷编码顺序。
您在真实的面试中是否遇到过这个题？
样例

给定 n = 2， 返回 [0,1,3,2]。其格雷编码顺序为：

00 - 0
01 - 1
11 - 3
10 - 2

挑战

O(2n) 时间复杂度。

 */