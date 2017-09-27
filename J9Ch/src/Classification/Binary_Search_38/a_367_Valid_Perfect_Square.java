package Classification.Binary_Search_38;
// 367	Valid Perfect Square

/**
 * Created by tianhuizhu on 6/19/17.
 * a square number is 1+3+5+7+... Time Complexity O(sqrt(N)) (Credit to lizhibupt, thanks for correcting this).
 binary search. Time Complexity O(logN)
 Newton Method. See [this wiki page][1]. Time Complexity is close to constant, given a positive integer.
 */

public class a_367_Valid_Perfect_Square {
    public boolean isPerfectSquare1(int num) {
        if (num < 1) return false;
        for (int i = 1; num > 0; i += 2)
            num -= i;
        return num == 0;
    }

    public boolean isPerfectSquare2(int num) {
        if (num < 1) return false;
        long left = 1, right = num;// long type to avoid 2147483647 case

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long t = mid * mid;
            if (t > num) {
                right = mid - 1;
            } else if (t < num) {
                left = mid + 1;
            } else {
                return true;
            }
        }

        return false;
    }

    boolean isPerfectSquare3(int num) {
        if (num < 1) return false;
        long t = num / 2;
        while (t * t > num) {
            t = (t + num / t) / 2;
        }
        return t * t == num;
    }

    public boolean isPerfectSquare_mine (int num) {
        if (num < 1) return false;
        long left = 1, right = num;// long type to avoid 2147483647 case

        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            long t = mid * mid;
            if (t > num) {
                right = mid ;
            } else if (t < num) {
                left = mid;
            } else {
                return true;
            }
        }
        if (left * left == num){
            return true;
        }
        return false;
    }


}
