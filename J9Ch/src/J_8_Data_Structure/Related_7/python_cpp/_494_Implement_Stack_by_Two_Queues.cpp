
class Stack {
public:
    // Push a new item into the stack
    void push(int x) {
        // Write your code here
        if (queue1.empty()) {
            queue1.push(x);
            while(!queue2.empty()){
                int tmp = queue2.front();
                queue2.pop();
                queue1.push(tmp);
            }
        } else {
            queue2.push(x);
            while(!queue1.empty()){
                int tmp = queue1.front();
                queue1.pop();
                queue2.push(tmp);
            }
        }
    }

    // Pop the top of the stack
    void pop() {
        // Write your code here
        if (!queue1.empty())
            queue1.pop();
        if (!queue2.empty())
            queue2.pop();
    }

    // Return the top of the stack
    int top() {
        // Write your code here
        if (!queue1.empty())
            return queue1.front();
        if (!queue2.empty())
            return queue2.front();
    }

    // Check the stack is empty or not.
    bool isEmpty() {
        // Write your code here
        return queue1.empty() && queue2.empty();
    }

private:
    queue<int> queue1;
    queue<int> queue2;
};