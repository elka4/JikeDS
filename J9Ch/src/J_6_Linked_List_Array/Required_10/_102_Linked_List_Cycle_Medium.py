'''/** 102 Linked List Cycle
 * Medium'''


# from lintcode import ListNode

"""
Definition of ListNode
class ListNode(object):

    def __init__(self, val, next=None):
        self.val = val
        self.next = next
"""
class Solution:
    """
    @param head: The first node of the linked list.
    @return: True if it has a cycle, or false
    """
    def hasCycle(self, head):
        # write your code here
        if head is None:
            return False
        p1 = head
        p2 = head
        while True:
            if p1.next is not None:
                p1=p1.next.next
                p2=p2.next
                if p1 is None or p2 is None:
                    return False
                elif p1 == p2:
                    return True
            else:
                return False
        return False
