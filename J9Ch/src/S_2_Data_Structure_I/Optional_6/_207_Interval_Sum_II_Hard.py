


class SegmentTree(object):

    def __init__(self, start, end, sum=0):
        self.start = start
        self.end = end
        self.sum = sum
        self.left, self.right = None, None

    @classmethod
    def build(cls, start, end, a):
        if start > end:
            return None

        if start == end:
            return SegmentTree(start, end, a[start])

        node = SegmentTree(start, end, a[start])

        mid = (start + end) / 2
        node.left = cls.build(start, mid, a)
        node.right = cls.build(mid + 1, end, a)
        node.sum = node.left.sum + node.right.sum
        return node

    @classmethod
    def modify(cls, root, index, value):
        if root is None:
            return

        if root.start == root.end:
            root.sum = value
            return

        if root.left.end >= index:
            cls.modify(root.left, index, value)
        else:
            cls.modify(root.right, index, value)

        root.sum = root.left.sum + root.right.sum

    @classmethod
    def query(cls, root, start, end):
        if root.start > end or root.end < start:
            return 0

        if start <= root.start and root.end <= end:
            return root.sum

        return cls.query(root.left, start, end) + \
               cls.query(root.right, start, end)


class Solution:
    # @param A: An integer list
    def __init__(self, A):
        # write your code here
        self.root = SegmentTree.build(0, len(A)-1, A)


    # @param start, end: Indices
    # @return: The sum from start to end
    def query(self, start, end):
        # write your code here
        return SegmentTree.query(self.root, start, end)


    # @param index, value: modify A[index] to value.
    def modify(self, index, value):
        # write your code here
        SegmentTree.modify(self.root, index, value)