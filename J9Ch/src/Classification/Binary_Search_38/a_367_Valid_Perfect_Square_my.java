package Classification.Binary_Search_38;
// 367	Valid Perfect Square

import org.junit.Test;

/**
 * Created by tianhuizhu on 6/19/17.
 * a square number is 1+3+5+7+... Time Complexity O(sqrt(N)) (Credit to lizhibupt, thanks for correcting this).
 binary search. Time Complexity O(logN)
 Newton Method. See [this wiki page][1]. Time Complexity is close to constant, given a positive integer.
 */

public class a_367_Valid_Perfect_Square_my {
    public boolean isPerfectSquare(int num) {
        if (num <= 0){
            return false;
        }
        long start = 1;
        long end = num;
        while (start + 1 < end){
            long mid = start + (end - start) / 2;
            long square = mid * mid;
            if (num == square) {
                return true;
            } else if (square < num){
                start = mid;
            } else{
                end = mid;
            }
        }
        return start * start == num ? true : false;
    }
    @Test
    public void test(){
        System.out.println(isPerfectSquare(2147483647));
    }


}
