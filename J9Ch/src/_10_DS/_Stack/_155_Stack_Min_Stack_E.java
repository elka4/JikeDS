package _10_DS._Stack;
import java.util.*;
import org.junit.Test;
public class _155_Stack_Min_Stack_E {

/////////////////////////////////////////////////
    //jiuzhang
    // version 1:
    public class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<Integer>();
            minStack = new Stack<Integer>();
        }

        public void push(int number) {
            stack.push(number);
            if (minStack.isEmpty()) {
                minStack.push(number);
            } else {
                minStack.push(Math.min(number, minStack.peek()));
            }
        }

        public int pop() {
            minStack.pop();
            return stack.pop();
        }

        public int min() {
            return minStack.peek();
        }
    }

// version 2, save more space. but space c
}
/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
 */