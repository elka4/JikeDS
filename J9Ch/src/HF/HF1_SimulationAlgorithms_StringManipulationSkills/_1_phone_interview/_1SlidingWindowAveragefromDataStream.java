package HF.HF1_SimulationAlgorithms_StringManipulationSkills._1_phone_interview;

public class _1SlidingWindowAveragefromDataStream {
    double[] sum;
    int id, size;

    _1SlidingWindowAveragefromDataStream(int s){
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

////////////////////////////////////////////

    class MovingAverage{
        double[] sum;
        int id, size;

        MovingAverage(int s){
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