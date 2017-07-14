package S_3_Data_Structure_II.all;

import java.util.Stack;

/**12 Min Stack
 * Created by tianhuizhu on 6/28/17.
 */
public class _12_Min_Stack {

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




/////////////////////////////////////////////////////////////////////////////

    // version 2, save more space. but space complexity doesn't change.
    public class MinStack2 {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public MinStack2() {
            stack = new Stack<Integer>();
            minStack = new Stack<Integer>();
        }

        public void push(int number) {
            stack.push(number);
            if (minStack.empty() == true)
                minStack.push(number);
            else {
                // 这里考虑的相等的情况也会继续push
                if (minStack.peek() >= number)
                    minStack.push(number);
            }
        }

        public int pop() {
            if (stack.peek().equals(minStack.peek()) )
                minStack.pop();
            return stack.pop();
        }

        public int min() {
            return minStack.peek();
        }
    }




}
