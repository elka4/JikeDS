package _10_DS._Stack;
import java.util.*;

import org.junit.Test;
public class _341_Stack_Flatten_Nested_List_Iterator_M {
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

////////////////////////////////////////////////////////////////////////////////////
class Solution{
/*    Simple Java solution using a stack with explanation
    A question before this is the Nested List Weight Sum, and it requires recursion to solve. As it carries to this problem that we will need recursion to solve it. But since we need to access each NestedInteger at a time, we will use a stack to help.

    In the constructor, we push all the nestedList into the stack from back to front, so when we pop the stack, it returns the very first element. Second, in the hasNext() function, we peek the first element in stack currently, and if it is an Integer, we will return true and pop the element. If it is a list, we will further flatten it. This is iterative version of flatting the nested list. Again, we need to iterate from the back to front of the list.*/

    public class NestedIterator implements Iterator<Integer> {
        Stack<NestedInteger> stack = new Stack<>();
        public NestedIterator(List<NestedInteger> nestedList) {
            for(int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            return stack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            while(!stack.isEmpty()) {
                NestedInteger curr = stack.peek();
                if(curr.isInteger()) {
                    return true;
                }
                stack.pop();
                for(int i = curr.getList().size() - 1; i >= 0; i--) {
                    stack.push(curr.getList().get(i));
                }
            }
            return false;
        }
    }
}


////////////////////////////////////////////////////////////////////////////////////
public class NestedIterator implements Iterator<Integer> {

    private Stack<NestedInteger> stack;

    private void pushListToStack(List<NestedInteger> nestedList) {
        Stack<NestedInteger> temp = new Stack<>();
        for (NestedInteger nested : nestedList) {
            temp.push(nested);
        }

        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        pushListToStack(nestedList);
    }

    // @return {int} the next element in the iteration
    @Override
    public Integer next() {
        if (!hasNext()) {
            return null;
        }
        return stack.pop().getInteger();
    }

    // @return {boolean} true if the iteration has more element or false
    @Override
    public boolean hasNext() {
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            pushListToStack(stack.pop().getList());
        }

        return !stack.isEmpty();
    }

    @Override
    public void remove() {}
}

}
/*
Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].


 */