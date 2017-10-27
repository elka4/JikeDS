package _10_DS._Stack;
import java.util.*;
import org.junit.Test;
public class _232_Stack_Implement_Queue_using_Stacks_E {

/*
Short O(1) amortized, C++ / Java / Ruby
I have one input stack, onto which I push the incoming elements, and one output stack, from which I peek/pop. I move elements from input stack to output stack when needed, i.e., when I need to peek/pop but the output stack is empty. When that happens, I move all elements from input to output stack, thereby reversing the order so it's the correct order for peek/pop.

The loop in peek does the moving from input to output stack. Each element only ever gets moved like that once, though, and only after we already spent time pushing it, so the overall amortized cost for each operation is O(1).
 */


    class MyQueue {

        Stack<Integer> input = new Stack();
        Stack<Integer> output = new Stack();

        public void push(int x) {
            input.push(x);
        }

        public void pop() {
            peek();
            output.pop();
        }

        public int peek() {
            if (output.empty())
                while (!input.empty())
                    output.push(input.pop());
            return output.peek();
        }

        public boolean empty() {
            return input.empty() && output.empty();
        }
    }



}
