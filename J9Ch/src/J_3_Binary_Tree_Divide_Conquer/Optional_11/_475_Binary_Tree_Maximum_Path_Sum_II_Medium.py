'''/**
 475
 Binary Tree Maximum Path Sum II
 '''


"""
Definition of TreeNode:
class TreeNode:
    def __init__(self, val):
        this.val = val
        this.left, this.right = None, None
"""
class Solution:
    """
    @param root the root of binary tree.
    @return an integer
    """
    def dfs(self, node, length, vals):
        length += node.val
        vals.append(length)
        if node.left is not None:
            self.dfs(node.left, length, vals)
        if node.right is not None:
            self.dfs(node.right, length, vals)


    def maxPathSum2(self, root):
        # Write your code here
        vals = []
        self.dfs(root, 0, vals)
        return max(vals)