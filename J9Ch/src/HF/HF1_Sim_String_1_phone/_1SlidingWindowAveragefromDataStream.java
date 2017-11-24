package HF.HF1_Sim_String_1_phone;

import org.junit.Test;

import java.util.*;
import java.util.Queue;

/*
Example:
• size=3
• m.next(1) = 1
• m.next(10)=(1+10)/2
• m.next(3) =(1+10+3)/3
• m.next (5) = (10 + 3 + 5) / 3
 */

/*
思路:
• 我们先来一个最简单的做法
– 来一个数就存数组
– for 循环最近size 个数求和取平均返回
• 时间复杂度是多少呢? – 每次O(size)
• 怎样优化算法——如何快速求和? – 前缀和数组

• 方便快速求a数组中某一段的和
– a[k] + a[k + 1] +... + a[j] = s[j] - s[k -1] 时间复杂度o(1)
• 怎样快求s数组? – s[i] = s[i - 1] + a[i]        时间复杂度o(n)
 */

/*
• Company Tags: Google
考点:
• 能否想到前缀和优化
• 能否进一步想到空间优化
• 15分钟bug free 写出来
• 电面好题，筛选基本代码能力的面试者
 */

/*
能力维度:
3. 基础数据结构/算法
5. 细节处理(corner case)
 */

// Sliding Window Average from Data Stream
public class _1SlidingWindowAveragefromDataStream {

//-------------------------------------------------------------------------

    //in class
    class MovingAverage{
        double[] sum;
        int id, size;

        MovingAverage(int s){
            sum = new double[1000000];
            id = 0;
            size = s;
        }

        public double next(int val){
            id++;
            sum[id] = sum[id - 1] + val;
            /*
            如果当前数组中含有超出或等于s个数字，取s个数字计算平均值
            如果当前数组中少于s个数字，有多少取多少
             */
            if (id - size >= 0) {
                System.out.println("id - size >= 0");
                return (sum[id] - sum[id - size]) / size;
            } else {
                System.out.println("else");
                return sum[id] / id;
            }
        }
    }


    @Test
    public void test01(){
        MovingAverage m = new MovingAverage(3);
        System.out.println("result: " + m.next(1));
        System.out.println("------------------------");

        System.out.println("result: " + m.next(10));
        System.out.println("------------------------");

        System.out.println("result: " + m.next(3));
        System.out.println("------------------------");

        System.out.println("result: " + m.next(5));
        System.out.println("------------------------");

    }

//-------------------------------------------------------------------------

    // 9Ch
    public class MovingAverage2 {

        private Queue<Integer> que;
        private double sum = 0;
        private int size;

        /** Initialize your data structure here. */
        public MovingAverage2(int size) {
            que = new LinkedList<Integer>();
            this.size = size;
        }

        public double next(int val) {
            // Write your code here
            sum += val;
            if (que.size() == size) {
                sum = sum - que.poll();
            }
            que.offer(val);
            return sum / que.size();
        }
    }

    @Test
    public void test02(){
        MovingAverage2 m = new MovingAverage2(3);
        System.out.println(m.next(1));
        System.out.println(m.next(10));
        System.out.println(m.next(3));
        System.out.println(m.next(5));
    }

    /**
     * Your MovingAverage object will be instantiated and called as such:
     * MovingAverage obj = new MovingAverage(size);
     * double param = obj.next(val);
     */

//-------------------------------------------------------------------------

// version: 高频题班
//非滚动
    public class MovingAverage3 {
        /**
         * Initialize your data structure here.
         */
        int id, size;
        double[] sum;

        MovingAverage3(int s) {
            id = 0;
            size = s;
            sum = new double[1000000];   //this is not final version
        }

        public double next(int val) {
            // Write your code here
            id++;
            sum[id] = sum[id - 1] + val;
            if (id - size >= 0) {
                return (sum[id] - sum[id - size]) / size;
            } else {
                return sum[id] / id;
            }
        }
    }

    @Test
    public void test03(){
        MovingAverage3 m = new MovingAverage3(3);
        System.out.println(m.next(1));
        System.out.println(m.next(10));
        System.out.println(m.next(3));
        System.out.println(m.next(5));
    }

//-------------------------------------------------------------------------

    //滚动
    // 只要是mod比 s+1 大的就行
    public class MovingAverage4 {
        /**
         * Initialize your data structure here.
         */
        int id, size;
        double[] sum;

        MovingAverage4(int s) {
            id = 0;
            size = s;
            sum = new double[size + 1];
        }

        int mod(int x) {
            return x % (size + 1);
        }

        public double next(int val) {
            // Write your code here
            id++;
            sum[mod(id)] = sum[mod(id - 1)] + val;
            if (id - size >= 0) {
                return (sum[mod(id)] - sum[mod(id - size)]) / size;
            } else {
                return sum[mod(id)] / id;
            }

        }
    }

    @Test
    public void test04(){
        MovingAverage4 m = new MovingAverage4(3);
        System.out.println(m.next(1));
        System.out.println(m.next(10));
        System.out.println(m.next(3));
        System.out.println(m.next(5));
    }

//-------------------------------------------------------------------------

    class MovingAverage5{
        double[] sum;
        int id, size;

        MovingAverage5(int s){
            sum = new double[s + 1];
            id = 0;
            size = s;
        }

        int mod(int x){
            return x % (size + 1);
        }

        public double next(int val){
            id++;
            sum[mod(id)] = sum[mod(id - 1)] + val;
            if (id - size >= 0) {
                return (sum[mod(id)] - sum[mod(id - size)]) / size;
            } else {
                return sum[mod(id)] / id;
            }
        }
    }

    @Test
    public void test05(){
        MovingAverage m = new MovingAverage(3);
        System.out.println(m.next(1));
        System.out.println(m.next(10));
        System.out.println(m.next(3));
        System.out.println(m.next(5));
    }

//-------------------------------------------------------------------------
    // Java O(1) time solution.
    //The idea is to keep the sum so far and update the sum just by
    // replacing the oldest number with the new entry.

    public class MovingAverage6 {
        private int [] window;
        private int n, insert;
        private long sum;

        /** Initialize your data structure here. */
        public MovingAverage6(int size) {
            window = new int[size];
            insert = 0;
            sum = 0;
        }

        public double next(int val) {
            if (n < window.length)  n++;
            sum -= window[insert];
            sum += val;
            window[insert] = val;
            insert = (insert + 1) % window.length;

            return (double)sum / n;
        }
    }

    public class MovingAverage7 {
        Queue<Integer> q;
        double sum = 0;
        int size;

        public MovingAverage7(int s) {
            q = new LinkedList();
            size = s;
        }

        public double next(int val) {
            if(q.size() == size){
                sum = sum - q.poll();
            }
            q.offer(val);
            sum += val;
            return sum/q.size();
        }
    }
//-------------------------------------------------------------------------
public class MovingAverage8 {
    private double previousSum = 0.0;
    private int maxSize;
    private Queue<Integer> currentWindow;

    public MovingAverage8(int size) {
        currentWindow = new LinkedList<Integer>();
        maxSize = size;
    }

    public double next(int val) {
        if (currentWindow.size() == maxSize)
        {
            previousSum -= currentWindow.remove();
        }

        previousSum += val;
        currentWindow.add(val);
        return previousSum / currentWindow.size();
    }
}
//-------------------------------------------------------------------------
//JAVA O(1) using Deque

    public class MovingAverage9 {

        Deque<Integer> dq;
        int size;
        int sum;
        public MovingAverage9(int size) {
            dq = new LinkedList<>();
            this.size = size;
            this.sum = 0;
        }

        public double next(int val) {
            if (dq.size() < size) {
                sum += val;
                dq.addLast(val);
                return (double) (sum / dq.size());
            } else {
                int temp = dq.pollFirst();
                sum -= temp;
                dq.addLast(val);
                sum += val;
                return (double) (sum / size);
            }
        }

    }

//-------------------------------------------------------------------------

    //Simple Java LinkedList Solution
    public class MovingAverage10 {
        private LinkedList<Integer> nums;
        private int total;
        private int windowSize;

        /** Initialize your data structure here. */
        public MovingAverage10(int size) {
            nums = new LinkedList<>();
            windowSize = size;
            total = 0;
        }

        public double next(int val) {
            if (nums.size() < windowSize) {
                total += val;
                nums.add(val);
            } else {
                total = total - nums.pollLast() + val;
                nums.add(val);
            }
            return total / nums.size();
        }
    }

//-------------------------------------------------------------------------


}
/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

您在真实的面试中是否遇到过这个题？ Yes
样例
MovingAverage m = new MovingAverage(3);
m.next(1) = 1 // return 1.00000
m.next(10) = (1 + 10) / 2 // return 5.50000
m.next(3) = (1 + 10 + 3) / 3 // return 4.66667
m.next(5) = (10 + 3 + 5) / 3 // return 6.00000
 */