package _10_DS._Stack;
import java.util.*;
import org.junit.Test;
public class _225_Stack_Implement_Stack_using_Queues_E {

//https://leetcode.com/articles/implement-stack-using-queues/
//Only push is O(n), others are O(1). Using one queue. Combination of two shared solutions
    class MyStack
    {
        Queue<Integer> queue;

        public MyStack()
        {
            this.queue=new LinkedList<Integer>();
        }

        // Push element x onto stack.
        public void push(int x)
        {
            queue.add(x);
            for(int i=0;i<queue.size()-1;i++)
            {
                queue.add(queue.poll());
            }
        }

        // Removes the element on top of the stack.
        public void pop()
        {
            queue.poll();
        }

        // Get the top element.
        public int top()
        {
            return queue.peek();
        }

        // Return whether the stack is empty.
        public boolean empty()
        {
            return queue.isEmpty();
        }
    }
////////////////////////////////////////////////////////////////////////////////////
//Java solutions about three ways one of which utilizes one queue, and the others utilize two queues
//    First, let's see the most easiest way: one queue.
//    In this method, the point is that after you add one element to the queue, rotate the queue to make the tail be the head.

class Solution2{
    class MyStack {

        //one Queue solution
        private Queue<Integer> q = new LinkedList<Integer>();

        // Push element x onto stack.
        public void push(int x) {
            q.add(x);
            for(int i = 1; i < q.size(); i ++) { //rotate the queue to make the tail be the head
                q.add(q.poll());
            }
        }

        // Removes the element on top of the stack.
        public void pop() {
            q.poll();
        }

        // Get the top element.
        public int top() {
            return q.peek();
        }

        // Return whether the stack is empty.
        public boolean empty() {
            return q.isEmpty();
        }
    }
}
//    Then, two queue ways.
//
//1 Push method is inefficient.
//
//    when you push an element, choose one empty queue(whichever when both are empty) to add this element, and then push all elements of the other queue into the chosen queue. After that, the newest element is at the head of the chosen queue so that whenever you want to do pop() or top(), you always get the newest element.
//
//    For example,
//
//    push(1):
//
//            [ , ,1] [ , , ]
//
//    push(2):
//
//            [ , , ] [ ,1,2]
//
//    push(3):
//
//            [ 1, 2,3 ] [ , , ]

    class Solution3{
        class MyStack {
            //using two queue. The push is inefficient.
            private Queue<Integer> q1 = new LinkedList<Integer>();
            private Queue<Integer> q2 = new LinkedList<Integer>();
            public void push(int x) {
                if(q1.isEmpty()) {
                    q1.add(x);
                    for(int i = 0; i < q2.size(); i ++)
                        q1.add(q2.poll());
                }else {
                    q2.add(x);
                    for(int i = 0; i < q1.size(); i++)
                        q2.add(q1.poll());
                }
            }

            public void pop() {
                if(!q1.isEmpty())
                    q1.poll();
                else
                    q2.poll();
            }
            public int top() {
                return q1.isEmpty() ? q2.peek() : q1.peek();
            }
            public boolean empty() {
                return q1.isEmpty() && q2.isEmpty();
            }
        }
    }

//2 pop() and top() are inefficient
//
//    When you push elements, choose a queue which is not empty(whichever when both are empty).
//    When you do pop() or top(), first pop all elements of the queue except the tail into another empty queue, and then pop the tail which is your want.
//
//    For example:
//
//    push(1):
//
//            [ , , 1] [ , , ]
//
//    push(2):
//
//            [ ,2,1] [ , , ]
//
//    top();
//
//[ , , 2] [ , ,1] -> [ , , ] [ ,2,1]
//
//    pop():
//
//            [ , , 1] [ , ,2] -> [ , ,1] [ , , ]
//
//    push(3) :
//
//            [ ,3,1] [ , , ]

    class Solution4{

        class MyStack{
            //using two queue. The pop and top are inefficient.
            private Queue<Integer> q1 = new LinkedList<Integer>();
            private Queue<Integer> q2 = new LinkedList<Integer>();
            public void push(int x) {
                if(!q1.isEmpty())
                    q1.add(x);
                else
                    q2.add(x);
            }
            public void pop() {
                if(q1.isEmpty()) {
                    int size = q2.size();
                    for(int i = 1; i < size; i ++) {
                        q1.add(q2.poll());
                    }
                    q2.poll();
                }else {
                    int size = q1.size();
                    for(int i = 1; i < size; i ++) {
                        q2.add(q1.poll());
                    }
                    q1.poll();
                }
            }
            public int top() {
                int res;
                if(q1.isEmpty()) {
                    int size = q2.size();
                    for(int i = 1; i < size; i ++) {
                        q1.add(q2.poll());
                    }
                    res = q2.poll();
                    q1.add(res);
                }else {
                    int size = q1.size();
                    for(int i = 1; i < size; i ++) {
                        q2.add(q1.poll());
                    }
                    res = q1.poll();
                    q2.add(res);
                }
                return res;
            }
            public boolean empty() {
                return q1.isEmpty() && q2.isEmpty();
            }
        }

    }
////////////////////////////////////////////////////////////////////////////////////
}
