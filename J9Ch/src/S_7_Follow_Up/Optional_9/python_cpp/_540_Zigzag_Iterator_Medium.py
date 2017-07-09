
class ZigzagIterator:

    # @param {int[]} v1 v2 two 1d vectors
    def __init__(self, v1, v2):
        # initialize your data structure here
        self.queue = [v for v in (v1, v2) if v]


    def next(self):
        # Write your code here
        v = self.queue.pop(0)
        value = v.pop(0)
        if v:
            self.queue.append(v)
        return value


    def hasNext(self):
        # Write your code here
        return len(self.queue) > 0


# Your ZigzagIterator object will be instantiated and called as such:
# solution, result = ZigzagIterator(v1, v2), []
