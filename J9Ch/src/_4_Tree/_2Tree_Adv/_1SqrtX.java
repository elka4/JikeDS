package _4_Tree._2Tree_Adv;

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
    //还是binary search 的思想。
    //
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

        //bit需要不断向右移动，对每个bit位置进行处理知道bit为0，就是对每个位置都处理过了
        while (bit != 0) {
            res |= bit;
            System.out.println("res |= bit;      " + Integer.toBinaryString(res));
            //也就是 res * res > x
            //这后res有两种情况，第一种是res还是太大（100000），这时候bit右移1位成为（10000）
            //res通过 res ^= bit;被重置为0；
            //那么在下一轮开始时res |= bit;就会将res重设为bit，就是10000。

            //第二种情况，res在某个循环已经太小，不满足res > x / res，这时候就不会执行res ^= bit;
            //不会重置res，那么下一轮执行res |= bit;的时候，res的一位就会被设为1，以增大res，
            //试图接近正确结果。而res就用来存储当前结果，不会再为0。

            //也就是每次不满足res > x / res， res就是太小，下一轮循环就要在下一位上0变为1，以增加res

            //但是一旦进入if，就要从这个位置检查大小，如同以上描述。

            //如果不满足res > x / res，也就是当前res太小，也就是说当前res都下一位应设为1以增大res
            if (res > x / res){//16:10000
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
        System.out.println(Integer.toBinaryString(a));
    }

    @Test
    public void test02(){
        int a = 3;//11
        System.out.println(Integer.toBinaryString(a));
        int b = 4;//100
        System.out.println(Integer.toBinaryString(b));
        System.out.println("a |= b : " + Integer.toBinaryString(a |= b));//OR 111
        System.out.println("a ^= b : " + Integer.toBinaryString(a ^= b));//XOR 11
    }

    @Test
    public void test03(){
        int a = 7;//111
        System.out.println(Integer.toBinaryString(a));
        int b = 9;//1001
        System.out.println(Integer.toBinaryString(b));
        System.out.println("a |= b : " + Integer.toBinaryString(a |= b));//OR 1111
        System.out.println("a ^= b : " + Integer.toBinaryString(a ^= b));//XOR 110
    }
    @Test
    public void stringToBinary(){
        System.out.println(Integer.parseInt("10000",2));//16
        System.out.println(Integer.parseInt("111",2));//7
    }
    @Test
    public void test04(){
        int a = 170;
        System.out.println(sqrt7(a));
        System.out.println(Integer.toBinaryString(a));
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
