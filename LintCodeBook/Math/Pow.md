#Pow(x, n)

	Implement pow(x, n).

####思路
	这道题是一道数值计算的题目，因为指数是可以使结果变大的运算，所以要注意越界的问题。
	如同我在Sqrt(x)这道题中提到的，一般来说数值计算的题目可以用两种方法来解，一种是以2为基进行位处理的方法，
	另一种是用二分法。
	二分法递归

```java
public class Solution {
    public double myPow(double x, int n) {

        if (n > 0) {
            return power(x, n);
        } else {
            return 1 / power(x, -n);
        }
    }

    public double power(double x, int n) {
        if (n == 0) {
            return 1;
        }

        double half = power(x, n /2);

        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}
```
