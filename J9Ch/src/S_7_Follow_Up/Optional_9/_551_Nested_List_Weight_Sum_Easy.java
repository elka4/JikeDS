package S_7_Follow_Up.Optional_9;
import java.util.*;
/** 551 Nested List Weight Sum
 * Created by tianhuizhu on 6/28/17.
 */
public class _551_Nested_List_Weight_Sum_Easy {
     public interface NestedInteger {

            // @return true if this NestedInteger holds a single integer,
            // rather than a nested list.
            public boolean isInteger();

            // @return the single integer that this NestedInteger holds,
            // if it holds a single integer
            // Return null if this NestedInteger holds a nested list
            public Integer getInteger();

            // @return the nested list that this NestedInteger holds,
            // if it holds a nested list
            // Return null if this NestedInteger holds a single integer
            public List<NestedInteger> getList();
     }
// 递归
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *
     *     // @return true if this NestedInteger holds a single integer,
     *     // rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds,
     *     // if it holds a single integer
     *     // Return null if this NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // @return the nested list that this NestedInteger holds,
     *     // if it holds a nested list
     *     // Return null if this NestedInteger holds a single integer
     *     public List<NestedInteger> getList();
     * }
     */
    public class Solution1 {

        public int depthSum(List<NestedInteger> nestedList) {
            return helper(nestedList, 1);
        }

        public int helper(List<NestedInteger> nestedList, int depth){
            if (nestedList == null || nestedList.size() == 0)
                return 0;

            int sum = 0;
            for(NestedInteger ele : nestedList) {
                if (ele.isInteger()) {
                    sum += ele.getInteger() * depth;
                } else {
                    sum += helper(ele.getList(), depth + 1);
                }
            }

            return sum;
        }
    }

// 非递归
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *
     *     // @return true if this NestedInteger holds a single integer,
     *     // rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds,
     *     // if it holds a single integer
     *     // Return null if this NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // @return the nested list that this NestedInteger holds,
     *     // if it holds a nested list
     *     // Return null if this NestedInteger holds a single integer
     *     public List<NestedInteger> getList();
     * }
     */
    public class Solution2 {
        public int depthSum(List<NestedInteger> nestedList) {
            // Write your code here
            if (nestedList == null || nestedList.size() == 0) {
                return 0;
            }
            int sum = 0;
            Queue<NestedInteger> queue = new LinkedList<NestedInteger>();
            for (NestedInteger nestedInt : nestedList) {
                queue.offer(nestedInt);
            }

            int depth = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                depth++;
                for (int i = 0; i < size; i++) {
                    NestedInteger nestedInt = queue.poll();
                    if (nestedInt.isInteger()) {
                        sum += nestedInt.getInteger() * depth;
                    } else {
                        for (NestedInteger innerInt : nestedInt.getList()) {
                            queue.offer(innerInt);
                        }
                    }
                }
            }
            return sum;
        }
    }
}
