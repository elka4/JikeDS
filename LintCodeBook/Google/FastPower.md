##Fast Power

19% Accepted

	Calculate the an % b where a, b and n are all 32bit integers.

	Have you met this question in a real interview? Yes
	Example
	For 231 % 3 = 2

	For 1001000 % 1000 = 0

####Challenge
- O(logn)

####Tags Expand
- Divide and Conquer

####思路

	数学题，考察整数求模的一些特性，不知道这个特性的话此题一时半会解不出来，本题中利用的关键特性为：
	(a * b) % p = ((a % p) * (b % p)) % p
	即 a 与 b 的乘积模 p 的值等于 a, b 分别模 p 相乘后再模 p 的值，只能帮你到这儿了，不看以下的答案先想想知道此关系后如何解这道题。

	分三种情况讨论 n 的值，需要特别注意的是n == 0，虽然此时 a0的值为1，但是不可直接返回1，因为b == 1时应该返回0，故稳妥的写法为返回1 % b.
	递归模型中，需要注意的是要分 n 是奇数还是偶数，奇数的话需要多乘一个 a, 保存乘积值时需要使用long型防止溢出，最后返回时强制转换回int。


```java
class Solution {
    /*
     * @param a, b, n: 32bit integers
     * @return: An integer
     */
     public int fastPower(int a, int b, int n) {
        if (n == 1) {
            return a % b;
        } else if (n == 0) {
            return 1 % b;
        } else if (n < 0) {
            return -1;
        }

        // (a * b) % p = ((a % p) * (b % p)) % p
        // use long to prevent overflow
        long product = fastPower(a, b, n / 2);
        product = (product * product) % b;
        if (n % 2 == 1) {
            product = (product * a) % b;
        }

        // cast long to int
        return (int) product;
    }
};

```
