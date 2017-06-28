'''/**
 246
 Binary Tree Path Sum II
 '''


"""
Definition of TreeNode:
class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left, self.right = None, None
"""
class Solution:
    # @param {TreeNode} root the root of binary tree
    # @param {int} target an integer
    # @return {int[][]} all valid paths
    def binaryTreePathSum2(self, root, target):
        # Write your code here
        result = []
        path = []
        if root is None:
            return result
        self.dfs(root, path, result, 0,  target)
        return result

    def dfs(self, root, path, result, l, target):
        if root is None:
            return
        path.append(root.val)
        tmp = target
        for i in xrange(l , -1, -1):
            tmp -= path[i]
            if tmp == 0:
                result.append(path[i:])

        self.dfs(root.left, path, result, l + 1, target)
        self.dfs(root.right, path, result, l + 1, target)

        path.pop()