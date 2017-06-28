'''/** 531 Six Degrees '''


# Definition for Undirected graph node
# class UndirectedGraphNode:
#     def __init__(self, x):
#         self.label = x
#         self.neighbors = []

import Queue

class Solution:
    '''
    @param {UndirectedGraphNode[]} graph a list of Undirected graph node
    @param {UndirectedGraphNode} s, t two Undirected graph nodes
    @return {int} an integer
    '''
    def sixDegrees(self, graph, s, t):
        # Write your code here
        d = {}
        q = Queue.Queue(maxsize = len(graph))

        q.put(s)
        d[s] = 0
        while not q.empty():
            x = q.get()
            if x == t:
                return d[x]

            for y in x.neighbors:
                if y not in d:
                    d[y] = d[x] + 1
                    q.put(y)

        return -1