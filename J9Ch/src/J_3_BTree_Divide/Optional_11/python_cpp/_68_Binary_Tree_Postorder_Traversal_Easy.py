'''/**
 68
 Binary Tree Postorder Traversal '''
class TreeNode:
    def __init__(self, val):
        this.val = val
        this.left, this.right = None, None
# from lintcode import TreeNode
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
    @return: Postorder in ArrayList which contains node values.
    """
    result = []

    def traverse(self, root):
        if root is None:
            return
        self.traverse(root.left)
        self.traverse(root.right)
        self.results.append(root.val)



    def postorderTraversal(self, root):
        self.results = []
        self.traverse(root)
        return self.results
        # write your code here