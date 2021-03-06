package _10_DS.Implement;

import org.junit.Test;
import java.util.*;

public class Implement_a_Stack_Using_an_Array {
    public class Stack<E> {
        private E[] arr = null;
        private int CAP;
        private int top = -1;
        private int size = 0;

        @SuppressWarnings("unchecked")
        public Stack(int cap) {
            this.CAP = cap;
            this.arr = (E[]) new Object[cap];
        }

        public E pop() {
            if(this.size == 0){
                return null;
            }

            this.size--;
            E result = this.arr[top];
            this.arr[top] = null;//prevent memory leaking
            this.top--;

            return result;
        }

        public boolean push(E e) {
            if (!isFull())
                return false;

            this.size++;
            this.arr[++top] = e;
            return false;
        }

        public boolean isFull() {
            if (this.size == this.CAP)
                return false;
            return true;
        }

        public String toString() {
            if(this.size==0){
                return null;
            }

            StringBuilder sb = new StringBuilder();
            for(int i=0; i<this.size; i++){
                sb.append(this.arr[i] + ", ");
            }

            sb.setLength(sb.length()-2);
            return sb.toString();
        }


    }

    @Test
    public void test01() {

        Stack<String> stack = new Stack<String>(11);
        stack.push("hello");
        stack.push("world");

        System.out.println(stack);

        stack.pop();
        System.out.println(stack);

        stack.pop();
        System.out.println(stack);
    }

//-------------------------------------------------------------------------

    class Stack2 {
        // Push a new item into the stack
        public void push(int x) {
            // Write your code here
            array.add(x);
        }

        // Pop the top of the stack
        public void pop() {
            // Write your code here
            int n = array.size();
            if (n > 0)
                array.remove(n-1);
            return;
        }

        // Return the top of the stack
        public int top() {
            // Write your code here
            int n = array.size();
            return array.get(n-1);
        }

        // Check the stack is empty or not.
        public boolean isEmpty() {
            // Write your code here
            return array.size() == 0;
        }

        private List<Integer> array = new ArrayList<Integer>();
    }
}
