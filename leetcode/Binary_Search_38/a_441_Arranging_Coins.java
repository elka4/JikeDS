package leetcode.Binary_Search_38;

/**
 * Created by tianhuizhu on 6/19/17.
 */
public class a_441_Arranging_Coins {

    public int arrangeCoins(int n) {
        int start = 0;
        int end = n;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) >>> 1; //>> can ac
            if ((0.5 * mid * mid + 0.5 * mid) <= n) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start - 1;
    }

    public int arrangeCoins2(int n) {
        return (int) ((Math.sqrt(1 + 8.0 * n) - 1) / 2);
    }

    public int arrangeCoins3 (int n) {
        //convert int to long to prevent integer overflow
        long nLong = (long)n;

        long st = 0;
        long ed = nLong;

        long mid = 0;

        while (st <= ed){
            mid = st + (ed - st) / 2;

            if (mid * (mid + 1) <= 2 * nLong){
                st = mid + 1;
            }else{
                ed = mid - 1;
            }
        }

        return (int)(st - 1);
    }

    public int arrangeCoins4(int n) {
        return (int) Math.floor((-1 + Math.sqrt(1+8L*n))/2.0);
    }

    public int arrangeCoins5(int n) {
        int lo = 0, hi = n;
        while(lo<=hi){
            long mid = lo + (hi-lo)/2, a = (1+mid), b = (2+mid);
            if(a*mid/2<=(long)n && a*b/2>(long)n) return (int)mid;
            else if(a*mid/2>(long)n) hi = (int)mid-1;
            else lo = (int)mid + 1;
        }
        return lo;
    }

}
