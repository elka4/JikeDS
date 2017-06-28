'''/**
 * 378
 * Convert Binary Search Tree to Doubly Linked List
 '''


"""
Definition of TreeNode:
class TreeNode:
    def __init__(self, val):
        this.val = val
        this.left, this.right = None, None
Definition of Doubly-ListNode
class DoublyListNode(object):

    def __init__(self, val, next=None):
        self.val = val
        self.next = self.prev = next
"""

class Solution:
    """
    @param root, the root of tree
    @return: a doubly list node
    """
    def bstToDoublyList(self, root):
        # Write your code here
        dfs = []
        self.getDfs(root, dfs)
        if len(dfs) == 0:
            return None

        head = None
        prev = None
        for val in dfs:
            node = DoublyListNode(val)
            if head is None:
                head = node
            else:
                prev.next = node
            node.prev = prev
            prev = node
        return head

    def getDfs(self, root, dfs):
        if root is None:
            return
        self.getDfs(root.left, dfs)
        dfs.append(root.val)
        self.getDfs(root.right, dfs)