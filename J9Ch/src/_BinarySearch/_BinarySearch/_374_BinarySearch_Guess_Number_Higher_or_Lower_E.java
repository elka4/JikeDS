package _BinarySearch._BinarySearch;
import java.util.*;
import org.junit.Test;
public class _374_BinarySearch_Guess_Number_Higher_or_Lower_E {
    abstract class GuessGame {
        int guessNumber(int x) {
            return 0;
        }
    }

    int guess(int x){
        return x;
    }
//Approach #2 Binary Search [Accepted]
public class Solution2 extends GuessGame {
    public int guessNumber(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int res = guess(mid);
            if (res == 0)
                return mid;
            else if (res < 0)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }
}

//Approach #3 Ternary Search [Accepted]

    public class Solution3 extends GuessGame {
        public int guessNumber(int n) {
            int low = 1;
            int high = n;
            while (low <= high) {
                int mid1 = low + (high - low) / 3;
                int mid2 = high - (high - low) / 3;
                int res1 = guess(mid1);
                int res2 = guess(mid2);
                if (res1 == 0)
                    return mid1;
                if (res2 == 0)
                    return mid2;
                else if (res1 < 0)
                    high = mid1 - 1;
                else if (res2 > 0)
                    low = mid2 + 1;
                else {
                    low = mid1 + 1;
                    high = mid2 - 1;
                }
            }
            return -1;
        }
    }



}