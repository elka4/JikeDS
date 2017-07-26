package _2Tree_Adv;

import org.junit.Test;

public class _1SqrtX {
    //jiuzhang
    public int sqrt0(int x) {
        // find the last number which square of it <= x
        long start = 1, end = x;
        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            if (mid * mid <= x) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (end * end <= x) {
            return (int) end;
        }
        return (int) start;
    }

///!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    //https://www.sigmainfy.com/blog/leetcode-sqrtx.html
    int sqrt7(int x) {
        if (x <= 1)
            return x;
        int res = 0;
        //因为java的int是32位，所以平方根用16位就够了，本来有一位，移动15位，得到16位
        // bit是上限，right
        int bit = (1 << 15);//15 个 0 "100。。。00" > 32
        //"100" ^ 2 = 4 ^ 2 = 16 < 32
        //"101" ^ 2 = 5 ^ 2 = 25 < 32
        //"110" ^ 2 = 6 ^ 2 = 36 > 32
        System.out.println("bit = (1 << 15); " + Integer.toBinaryString( + bit));

        while (bit != 0) {
            res |= bit;
            System.out.println("res |= bit;      " + Integer.toBinaryString(res));
            //也就是 res * res > x
            //
            if (res > x / res){
                System.out.println("x / res          " + Integer.toBinaryString(x / res));
                res ^= bit; // set it back
                System.out.println("res ^= bit;      " + Integer.toBinaryString(res));
            }

            System.out.println("bit >>= 1        " + Integer.toBinaryString(bit));
            bit >>= 1;//
            System.out.println("bit >>= 1        " + Integer.toBinaryString(bit));
            System.out.println("===================================");
        }
        return res;
    }

    @Test
    public void test01(){
        int a = 16;
        System.out.println(sqrt7(a));
    }

//////////////////////////////////

    public int sqrt(int x) {
        if (x == 0)
            return 0;
        int left = 1, right = Integer.MAX_VALUE;
        while (true) {
            int mid = left + (right - left)/2;
            if (mid > x/mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x/(mid + 1))
                    return mid;
                left = mid + 1;
            }
        }
    }

////////////////////////////////////////////////////

    public int sqrt2(int x) {
        long r = x;
        while (r*r > x)
            r = (r + x/r) / 2;
        return (int) r;
    }

////////////////////////////////////////////////////

    public int mySqrt3(int x) {
        if (x == 0) return 0;
        int start = 1, end = x;
        while (start < end) {
            int mid = start + (end - start) / 2;
            // Found the result
            if (mid <= x / mid && (mid + 1) > x / (mid + 1))
                return mid;
            // Keep checking the left part
            else if (mid > x / mid)
                end = mid;
            else
                // Keep checking the right part
                start = mid + 1;
        }
        return start;
    }

////////////////////////////////////////////////////

    public int mySqrt(int x) {
        int res = 0;
        for (int mask = 1 << 15; mask != 0; mask >>>= 1) {
            int next = res | mask; //set bit
            if (next <= x / next) res = next;
        }
        return res;
    }

    ////
    public int sqrt5(int x) {
        if(x==0)
            return 0;
        int h=0;
        while((long)(1<<h)*(long)(1<<h)<=x) // firstly, find the most significant bit
            h++;
        h--;
        int b=h-1;
        int res=(1<<h);
        while(b>=0){  // find the remaining bits
            if((long)(res | (1<<b))*(long)(res |(1<<b))<=x)
                res|=(1<<b);
            b--;
        }
        return res;
    }

    //////!!!!!!!!
    //https://www.sigmainfy.com/blog/leetcode-sqrtx.html
    int sqrt6(int x) {
        if (x <= 1)
            return x;

        int l = 1, r = x;
                int sol = 0 , mid = 0;

        while (l <= r) {
            mid = l + (r - l) / 2;
            if (mid <= x / mid) {
                l = mid + 1;
                sol = mid;
            }
            else
                r = mid - 1;
        }
        return sol;
    }



}
