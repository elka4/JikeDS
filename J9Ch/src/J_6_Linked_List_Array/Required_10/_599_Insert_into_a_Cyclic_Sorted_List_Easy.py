'''/** 599 Insert into a Cyclic Sorted List
 * Easy'''


"""
Definition of ListNode
class ListNode(object):

    def __init__(self, val, next=None):
        self.val = val
        self.next = next
"""
class Solution:
    # @param {ListNode} node a list node in the list
    # @param {int} x an integer
    # @return {ListNode} the inserted new list node
    def insert(self, node, x):
        # Write your code here
        if node is None:
            node = ListNode(x)
            node.next = node
            return node

        p = node
        prev = None
        while True:
            prev = p
            p = p.next
            if x <= p.val and x >= prev.val:
                break

            if (prev.val > p.val) and (x < p.val or x > prev.val):
                break

            if p is node:
                break

        newNode = ListNode(x)
        newNode.next = p
        prev.next = newNode
        return newNode