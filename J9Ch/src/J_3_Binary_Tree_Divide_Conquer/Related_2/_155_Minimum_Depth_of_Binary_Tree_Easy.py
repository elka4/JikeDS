'''/**
155
Minimum Depth of Binary Tree'''


"""
Definition of TreeNode:
class TreeNode:
    def __init__(self, val):
        this.val = val
        this.left, this.right = None, None
"""
class Solution:
    """
    @param root: The root of binary tree.
    @return: An integer
    """
    def minDepth(self, root):
        # write your code here
        return self.find(root)

    def find(self, node):
        if node is None:
            return 0
        left, right = 0, 0
        if node.left != None:
            left = self.find(node.left)
        else:
            return self.find(node.right) + 1

        if node.right != None:
            right = self.find(node.right)
        else:
            return left + 1

        return min(left,right) + 1