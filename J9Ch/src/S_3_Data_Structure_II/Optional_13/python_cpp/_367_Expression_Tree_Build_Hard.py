
import sys
"""
Definition of ExpressionTreeNode:
class ExpressionTreeNode:
    def __init__(self, symbol):
        self.symbol = symbol
        self.left, self.right = None, None
"""

class MyTreeNode:
    def __init__(self, val, s):
        self.left = None
        self.right = None
        self.val = val
        self.exp_node = ExpressionTreeNode(s)

class Solution:
    # @param expression: A string list
    # @return: The root of expression tree
    def get_val(self, a, base):
        if a == '+' or a == '-':
            if base == sys.maxint:
                return base
            return 1 + base
        if a == '*' or a == '/':
            if base == sys.maxint:
                return base
            return 2 + base
        return sys.maxint

    def build(self, expression):
        # write your code here
        root = self.create_tree(expression)
        return self.copy_tree(root)

    def copy_tree(self, root):
        if not root:
            return None
        root.exp_node.left = self.copy_tree(root.left)
        root.exp_node.right = self.copy_tree(root.right)
        return root.exp_node


    def create_tree(self, expression):
        stack = []
        base = 0
        for i in range(len(expression)):
            if i != len(expression):
                if expression[i] == '(':
                    if base != sys.maxint:
                        base += 10
                    continue
                elif expression[i] == ')':
                    if base != sys.maxint:
                        base -= 10
                    continue
                val = self.get_val(expression[i], base)

            node = MyTreeNode(val, expression[i])
            while stack and val <= stack[-1].val:
                node.left = stack.pop()
            if stack:
                stack[-1].right = node
            stack.append(node)
        if not stack:
            return None
        return stack[0]