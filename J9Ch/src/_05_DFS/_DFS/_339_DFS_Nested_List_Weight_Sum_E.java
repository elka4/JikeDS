package _05_DFS._DFS;
import java.util.*;

import lib.*;
import org.junit.Test;
public class _339_DFS_Nested_List_Weight_Sum_E {

    interface NestedInteger {

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
    //2ms easy to understand java solution
    class Solution{
        public int depthSum(List<NestedInteger> nestedList) {
            return helper(nestedList, 1);
        }

        private int helper(List<NestedInteger> list, int depth)
        {
            int ret = 0;
            for (NestedInteger e: list)
            {
                ret += e.isInteger()? e.getInteger() * depth: helper(e.getList(), depth + 1);
            }
            return ret;
        }
    }

    public class Solution2 {
        public int depthSum(List<NestedInteger> nestedList) {
            return depthSum(nestedList, 1);
        }
        public int depthSum(List<NestedInteger> nestedList, int level) {
            int result = 0;
            for(NestedInteger ni : nestedList) {
                if (ni.isInteger()) {
                    result = result + (level * ni.getInteger());
                }else {
                    result = result + depthSum(ni.getList(), level+1);
                }
            }
            return result;
        }
    }
    //Java Solution: similar to tree level order traversal
    class Solution3{
        public int depthSum(List<NestedInteger> nestedList) {
            if(nestedList == null){
                return 0;
            }

            int sum = 0;
            int level = 1;

            Queue<NestedInteger> queue = new LinkedList<NestedInteger>(nestedList);
            while(queue.size() > 0){
                int size = queue.size();

                for(int i = 0; i < size; i++){
                    NestedInteger ni = queue.poll();

                    if(ni.isInteger()){
                        sum += ni.getInteger() * level;
                    }else{
                        queue.addAll(ni.getList());
                    }
                }

                level++;
            }

            return sum;
        }
    }
//-------------------------------------------------------------------------///
//Depth-first Traversal [Accepted]

class leet{
    public int depthSum(List<NestedInteger> nestedList) {
        return depthSum(nestedList, 1);
    }

    public int depthSum(List<NestedInteger> list, int depth) {
        int sum = 0;
        for (NestedInteger n : list) {
            if (n.isInteger()) {
                sum += n.getInteger() * depth;
            } else {
                sum += depthSum(n.getList(), depth + 1);
            }
        }
        return sum;
    }
}




//-------------------------------------------------------------------------///
public class Jiuzhang1 {

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
    public class Jiuzhang2 {
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
/*

 */