package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _364_DFS_Nested_List_Weight_Sum_II_M {

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


    public int depthSumInverse(List<NestedInteger> nestedList) {
        int unweighted = 0, weighted = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger())
                    unweighted += ni.getInteger();
                else
                    nextLevel.addAll(ni.getList());
            }
            weighted += unweighted;
            nestedList = nextLevel;
        }
        return weighted;
    }


    public class Solution {
        public int depthSumInverse(List<NestedInteger> nestedList) {
            return helper(nestedList, 0);
        }

        private int helper(List<NestedInteger> niList, int prev) {
            int intSum = prev;
            List<NestedInteger> levelBreak = new ArrayList<>();

            for (NestedInteger ni : niList) {
                if (ni.isInteger()) {
                    intSum += ni.getInteger();
                } else {
                    levelBreak.addAll(ni.getList());
                }
            }

            int listSum = levelBreak.isEmpty()? 0 : helper(levelBreak, intSum);

            return listSum + intSum;
        }
    }
//////////////////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */