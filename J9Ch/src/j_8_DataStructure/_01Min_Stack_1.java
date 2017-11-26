package j_8_DataStructure;

import java.util.Stack;

//version1
public class _01Min_Stack_1 {
	private Stack<Integer> stack;
    private Stack<Integer> minStack;
    
    public _01Min_Stack_1() {
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


//-------------------------------------------------------------------------------

    //version 2, save more space. but space complexity doesn't change.
    public class _01Min_Stack_2 {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public _01Min_Stack_2() {
            stack = new Stack<Integer>();
            minStack = new Stack<Integer>();
        }

        public void push(int number) {
            stack.push(number);
            if (minStack.empty() == true)
                minStack.push(number);
            else
            if (minStack.peek() >= number)
                minStack.push(number);
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



/*Implement a stack with min() function, which will return the 
 * smallest number in the stack.
 

It should support push, pop and min operation all in O(1) cost.

 Notice

min operation will never be called if there is no number in the stack.

Have you met this question in a real interview? Yes
Example
push(1)
pop()   // return 1
push(2)
push(3)
min()   // return 2
push(1)
min()   // return 1
Tags 
Stack Zenefits Uber Google
Related Problems 
Hard Max Tree 29 %
Medium Implement Queue by Two Stacks 39 %*/