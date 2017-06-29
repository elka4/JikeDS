


class SegTree:

    def __init__(self, start, end):
        self.start = start
        self.end = end
        self.left = None
        self.right = None
        self.count = 0
        if start != end:
            self.left = SegTree(start, (start + end) / 2)
            self.right = SegTree((start + end) / 2 + 1, end)

    def sum(self, start, end):
        if start <= self.start and end >= self.end:
            return self.count

        if self.start == self.end:
            return 0

        if end <= self.left.end:
            return self.left.sum(start, end)

        if start >= self.right.start:
            return self.right.sum(start, end)

        return (self.left.sum(start, self.left.end) +
                self.right.sum(self.right.start, end))

    def inc(self, index):
        if self.start == self.end:
            self.count += 1
            return

        if index <= self.left.end:
            self.left.inc(index)
        else:
            self.right.inc(index)

        self.count = self.left.count + self.right.count


class Solution:
    """
    @param A: A list of integer
    @return: Count the number of element before this element 'ai' is
             smaller than it and return count number list
    """
    def countOfSmallerNumberII(self, A):
        if len(A) == 0:
            return []

        root = SegTree(0, max(A))

        results = []
        for a in A:
            results.append(root.sum(0, a - 1))
            root.inc(a)
        return results