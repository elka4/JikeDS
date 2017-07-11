

# Definition for a directed graph node
# class DirectedGraphNode:
#     def __init__(self, x):
#         self.label = x
#         self.neighbors = []
class Solution:
    # @param {DirectedGraphNode[]} nodes a array of directed graph node
    # @return {int[][]} a connected set of a directed graph
    def __init__(self):
        self.f = {}

    def merge(self, x, y):
        x = self.find(x)
        y = self.find(y)
        if x != y:
            self.f[x] = y

    def find(self, x):
        if self.f[x] == x:
            return x

        self.f[x] = self.find(self.f[x])
        return self.f[x]

    def connectedSet2(self, nodes):
        # Write your code here
        for node in nodes:
            self.f[node.label] = node.label

        for node in nodes:
            for nei in node.neighbors:
                self.merge(node.label, nei.label)

        result = []
        g = {}
        cnt = 0

        for node in nodes:
            x = self.find(node.label)
            if not x in g:
                cnt += 1
                g[x] = cnt

            if len(result) < cnt:
                result.append([])

            result[g[x] - 1].append(node.label)

        return result