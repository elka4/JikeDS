'''/** 618. Search Graph Nodes
 * Medium'''

# Definition for a undirected graph node
# class UndirectedGraphNode:
#     def __init__(self, x):
#         self.label = x
#         self.neighbors = []

class Solution:
    # @param {UndirectedGraphNode[]} graph a list of undirected graph node
    # @param {dict} values a dict, <UndirectedGraphNode, (int)value>
    # @param {UndirectedGraphNode} node an Undirected graph node
    # @param {int} target an integer
    # @return {UndirectedGraphNode} a node
    def searchNode(self, graph, values, node, target):
        # Write your code here
        import Queue
        q = Queue.Queue(maxsize = len(graph))
        if values[node] == target:
            return node

        q.put(node)
        del values[node]

        while not q.empty():
            head = q.get()
            for n in head.neighbors:
                if n in values:
                    if values[n] == target:
                        return n
                    del values[n]
                    q.put(n)
        return None