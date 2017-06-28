'''/**
 472
 Binary Tree Path Sum III
 '''



"""
Definition of ParentTreeNode:
class ParentTreeNode:
    def __init__(self, val):
        self.val = val
        self.parent, self.left, self.right = None, None, None
"""
class Solution:
    # @param {ParentTreeNode} root the root of binary tree
    # @param {int} target an integer
    # @return {int[][]} all valid paths
    def binaryTreePathSum3(self, root, target):
        # Write your code here
        results = []
        self.dfs(root, target, results)
        return results

    def dfs(self, root, target, results):
        if root is None:
            return

        path = []
        self.findSum(root, None, target, path, results)

        self.dfs(root.left, target, results)
        self.dfs(root.right, target, results)

    def findSum(self, root, father, target, path, results):
        path.append(root.val)
        target -= root.val

        if target == 0:
            results.append(path[:])

        if root.parent not in [None, father]:
            self.findSum(root.parent, root, target, path, results)

        if root.left not in [None, father]:
            self.findSum(root.left, root, target, path, results)

        if root.right not in [None, father]:
            self.findSum(root.right, root, target, path, results)

        path.pop()

