'''/** 596. Minimum Subtree
 * Easy'''


class Solution:
    # @param {TreeNode} root the root of binary tree
    # @return {int} the minimun weight node
    import sys
    minumun_weight = sys.maxint
    result = None

    def findSubtree(self, root):
        # Write your code here
        self.helper(root)

        return self.result

    def helper(self, root):
        if root is None:
            return 0

        left_weight = self.helper(root.left)
        right_weight = self.helper(root.right)

        if left_weight + right_weight + root.val <= self.minumun_weight:
            self.minumun_weight = left_weight + right_weight + root.val
            self.result = root

        return left_weight + right_weight + root.val