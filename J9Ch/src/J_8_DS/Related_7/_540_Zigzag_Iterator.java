package J_8_DS.Related_7;

import java.util.Iterator;
import java.util.List;
/**540 Zigzag Iterator

 * Created by tianhuizhu on 7/10/17.
 */
public class _540_Zigzag_Iterator {

    public class ZigzagIterator {

        public Iterator<Integer> it1;
        public Iterator<Integer> it2;
        public int turns;

        /**
         * @param v1 v2 two 1d vectors
         */
        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            // initialize your data structure here.
            this.it1 = v1.iterator();
            this.it2 = v2.iterator();
            turns = 0;
        }

        public int next() {
            // Write your code here
            turns++;
            if((turns % 2 == 1 && it1.hasNext()) || (!it2.hasNext())) {
                return it1.next();
            } else if((turns % 2 == 0 && it2.hasNext()) || (!it1.hasNext())) {
                return it2.next();
            }
            return -1;
        }

        public boolean hasNext() {
            // Write your code here
            return it1.hasNext() || it2.hasNext();
        }
    }

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator solution = new ZigzagIterator(v1, v2);
 * while (solution.hasNext()) result.add(solution.next());
 * Output result
 */
}
