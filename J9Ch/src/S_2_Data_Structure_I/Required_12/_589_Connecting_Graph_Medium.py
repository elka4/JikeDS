

class ConnectingGraph:

    # @param {int} n
    def __init__(self, n):
        # initialize your data structure here.
        self.father = [0 for _ in xrange(n + 1)]

    def find(self, x):
        if self.father[x] == 0:
            return x
        self.father[x] = self.find(self.father[x])
        return self.father[x]

    # @param {int} a, b
    # return nothing
    def connect(self, a, b):
        # Write your code here
        root_a = self.find(a)
        root_b = self.find(b)
        if root_a != root_b:
            self.father[root_a] = root_b

    # @param {int} a, b
    # return {boolean} true if they are connected or false
    def query(self, a, b):
        # Write your code here
        root_a = self.find(a)
        root_b = self.find(b)
        return root_a == root_b
