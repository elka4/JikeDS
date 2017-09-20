package HF.HF1_Simulation_String._1_phone_interview;

import org.junit.Test;

import java.util.LinkedList;
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

public class _1SlidingWindowAveragefromDataStream {


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
            if (id - size >= 0) {
                return (sum[id] - sum[id - size]) / size;
            } else {
                return sum[id] / id;
            }
        }
    }


    @Test
    public void test01(){
        MovingAverage m = new MovingAverage(3);
        System.out.println(m.next(1));
        System.out.println(m.next(10));
        System.out.println(m.next(3));
        System.out.println(m.next(5));
    }

//////////////////////////////////////////////////////////////////

    //jiuzhang
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

    /**
     * Your MovingAverage object will be instantiated and called as such:
     * MovingAverage obj = new MovingAverage(size);
     * double param = obj.next(val);
     */

//////////////////////////////////////////////////////////////////

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

//////////////////////////////////////////////////////////////////

    //滚动
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

//////////////////////////////////////////////////////////////////

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

//////////////////////////////////////////////////////////////////


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