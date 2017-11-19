package J_8_DS.other.Iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**541 Zigzag Iterator II

 * Created by tianhuizhu on 7/10/17.
 */

/**
 * Your ZigzagIterator2 object will be instantiated and called as such:
 * ZigzagIterator2 solution = new ZigzagIterator2(vecs);
 * while (solution.hasNext()) result.add(solution.next());
 * Output result
 */


public class _541_Zigzag_Iterator_II {

    public class ZigzagIterator2 {

        public List<Iterator<Integer>> its;
        public int turns;

        /**
         * @param vecs a list of 1d vectors
         */
        public ZigzagIterator2(ArrayList<ArrayList<Integer>> vecs) {
            // initialize your data structure here.
            this.its = new ArrayList<Iterator<Integer>>();
            for (List<Integer> vec : vecs) {
                if (vec.size() > 0)
                    its.add(vec.iterator());
            }
            turns = 0;
        }

        public int next() {
            // Write your code here
            int elem = its.get(turns).next();
            if (its.get(turns).hasNext())
                turns = (turns + 1) % its.size();
            else {
                its.remove(turns);
                if (its.size() > 0)
                    turns %= its.size();
            }
            return elem;
        }

        public boolean hasNext() {
            // Write your code here
            return its.size() > 0;
        }
    }



//-------------------------------------------------------------------------//////////////////





//-------------------------------------------------------------------------//////////////////





//-------------------------------------------------------------------------//////////////////





//-------------------------------------------------------------------------//////////////////





//-------------------------------------------------------------------------//////////////////







}
