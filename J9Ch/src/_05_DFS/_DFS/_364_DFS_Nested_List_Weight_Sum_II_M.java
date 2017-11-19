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

    //No depth variable, no multiplication
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

//    JAVA AC BFS solution
    public int depthSumInverse2(List<NestedInteger> nestedList) {
        if (nestedList == null) return 0;
        Queue<NestedInteger> queue = new LinkedList<NestedInteger>();
        int prev = 0;
        int total = 0;
        for (NestedInteger next: nestedList) {
            queue.offer(next);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            for (int i = 0; i < size; i++) {
                NestedInteger current = queue.poll();
                if (current.isInteger()) levelSum += current.getInteger();
                List<NestedInteger> nextList = current.getList();
                if (nextList != null) {
                    for (NestedInteger next: nextList) {
                        queue.offer(next);
                    }
                }
            }
            prev += levelSum;
            total += prev;
        }
        return total;
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
//-------------------------------------------------------------------------///




//-------------------------------------------------------------------------///






}
/*
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:
Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

Example 2:
Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)


 */