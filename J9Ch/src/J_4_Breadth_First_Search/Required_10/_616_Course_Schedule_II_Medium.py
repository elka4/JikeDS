'''/** 616. Course Schedule II
 * Medium'''


class Solution:
    # @param {int} numCourses a total of n courses
    # @param {int[][]} prerequisites a list of prerequisite pairs
    # @return {int[]} the course order
    def findOrder(self, numCourses, prerequisites):
        # Write your code here
        edges = {i: [] for i in xrange(numCourses)}
        degrees = [0 for i in xrange(numCourses)]
        for i, j in prerequisites:
            edges[j].append(i)
            degrees[i] += 1
        import Queue
        queue = Queue.Queue(maxsize = numCourses)

        for i in xrange(numCourses):
            if degrees[i] == 0:
                queue.put(i)

        order = []
        while not queue.empty():
            node = queue.get()
            order.append(node)

            for x in edges[node]:
                degrees[x] -= 1
                if degrees[x] == 0:
                    queue.put(x)

        if len(order) == numCourses:
            return order
        return []