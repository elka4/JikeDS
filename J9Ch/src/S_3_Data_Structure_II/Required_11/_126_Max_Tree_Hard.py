
"""
Definition of TreeNode:
class TreeNode:
    def __init__(self, val):
        this.val = val
        this.left, this.right = None, None
"""
class Stack:
    def __init__(self):
        self.items = []

    def __getitem__(self, index):
        return self.items[index]

    def isEmpty(self):
        return len(self.items)==0

    def push(self, item):
        self.items.append(item)

    def pop(self):
        return self.items.pop()

    def peek(self):
        if not self.isEmpty():
            return self.items[len(self.items)-1]

    def size(self):
        return len(self.items)
class Solution:
    # @param A: Given an integer array with no duplicates.
    # @return: The root of max tree.
    def maxTree(self, A):
        # write your code here
        stk = Stack()
        for ele in A:
            node = TreeNode(ele)
            while not stk.isEmpty() and ele > stk.peek().val:
                node.left = stk.pop()
            if not stk.isEmpty():
                stk.peek().right = node
            stk.push(node)
        return stk[0]
