

class Stack:
    # initialize your data structure here.
    def __init__(self):
        self.queue = []

    # @param x, an integer, push a new item into the stack
    # @return nothing
    def push(self, x):
        # Write your code here
        self.queue.append(x)

    # @return nothing, pop the top of the stack
    def pop(self):
        # Write your code here
        for x in range(len(self.queue) - 1):
            self.queue.append(self.queue.pop(0))
        self.queue.pop(0)

    # @return an integer, return the top of the stack
    def top(self):
        # Write your code here
        top = None
        for x in range(len(self.queue)):
            top = self.queue.pop(0)
            self.queue.append(top)
        return top

    # @return an boolean, check the stack is empty or not.
    def isEmpty(self):
        # Write your code here
        return self.queue == []