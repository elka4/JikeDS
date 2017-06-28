'''/**
67
 Binary Tree Inorder Traversal
 '''


from lintcode import *
class Solution:
    """
    @param root: The root of binary tree.
    @return: Inorder in ArrayList which contains node values.
    """
    def inorderTraversal(self, root):
        if root is None:
            return []
        return self.inorderTraversal(root.left) + [root.val] \
               + self.inorderTraversal(root.right)