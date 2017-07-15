package J_8_Data_Structure.Required_10;
import java.util.*;import lib.ListNode;
/** 4 Ugly Number II
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */
public class _4_Ugly_Number_II {

    // version 1: O(n) scan
    class Solution1 {
        /**
         * @param n an integer
         * @return the nth prime number as description.
         */
        public int nthUglyNumber(int n) {
            List<Integer> uglys = new ArrayList<Integer>();
            uglys.add(1);

            int p2 = 0, p3 = 0, p5 = 0;
            // p2, p3 & p5 share the same queue: uglys

            for (int i = 1; i < n; i++) {
                int lastNumber = uglys.get(i - 1);
                while (uglys.get(p2) * 2 <= lastNumber) p2++;
                while (uglys.get(p3) * 3 <= lastNumber) p3++;
                while (uglys.get(p5) * 5 <= lastNumber) p5++;

                uglys.add(Math.min(
                        Math.min(uglys.get(p2) * 2, uglys.get(p3) * 3),
                        uglys.get(p5) * 5
                ));
            }

            return uglys.get(n - 1);
        }
    };

    // version 2 O(nlogn) HashMap + Heap
    class Solution2 {
        /**
         * @param n an integer
         * @return the nth prime number as description.
         */
        public int nthUglyNumber(int n) {
            // Write your code here
            Queue<Long> Q = new PriorityQueue<Long>();
            HashSet<Long> inQ = new HashSet<Long>();
            Long[] primes = new Long[3];
            primes[0] = Long.valueOf(2);
            primes[1] = Long.valueOf(3);
            primes[2] = Long.valueOf(5);
            for (int i = 0; i < 3; i++) {
                Q.add(primes[i]);
                inQ.add(primes[i]);
            }
            Long number = Long.valueOf(1);
            for (int i = 1; i < n; i++) {
                number = Q.poll();
                for (int j = 0; j < 3; j++) {
                    if (!inQ.contains(primes[j] * number)) {
                        Q.add(number * primes[j]);
                        inQ.add(number * primes[j]);
                    }
                }
            }
            return number.intValue();
        }
    };
}