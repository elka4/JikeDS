
class TreeNode:
    def __init__(self, val):
        this.val = val
        this.left, this.right = None, None


class Solution:
    """
    @param root, the root of binary tree.
    @return true if it is a mirror of itself, or false.
    """
    def help(self, p, q):
        if p == None and q == None: return True
        if p and q and p.val == q.val:
            return self.help(p.right, q.left) and self.help(p.left, q.right)
        return False

    def isSymmetric(self, root):
        # Write your code here
        if root:
            return self.help(root.left, root.right)
        return True