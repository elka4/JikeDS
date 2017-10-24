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
}
