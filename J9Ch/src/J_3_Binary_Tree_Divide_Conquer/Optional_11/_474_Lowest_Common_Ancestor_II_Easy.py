'''/**
 * 474
 * Lowest Common Ancestor II
 '''



"""
Definition of ParentTreeNode:
class ParentTreeNode:
    def __init__(self, val):
        self.val = val
        self.parent, self.left, self.right = None, None, None
"""
class Solution:
    """
    @param root: The root of the tree
    @param A and B: Two node in the tree
    @return: The lowest common ancestor of A and B
    """
    def lowestCommonAncestorII(self, root, A, B):
        # Write your code here
        dict = {}
        while A is not None:
            dict[A] = True
            A = A.parent

        while B is not None:
            if B in dict:
                return B
            B = B.parent

        return root