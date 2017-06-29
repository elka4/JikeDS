

class Queue {
public:
    stack<int> stack1;
    stack<int> stack2;

    Queue() {
    }

    void push(int element) {
        stack1.push(element);
    }

    void adjust() {
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.top());
                stack1.pop();
            }
        }
    }

    int pop() {
        adjust();
        int temp = stack2.top();
        stack2.pop();
        return temp;
    }

    int top() {
        adjust();
        return stack2.top();
    }
};