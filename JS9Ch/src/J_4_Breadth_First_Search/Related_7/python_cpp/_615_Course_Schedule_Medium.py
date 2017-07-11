# /**625 Course Schedule


class Solution:
    # @param {int} numCourses a total of n courses
    # @param {int[][]} prerequisites a list of prerequisite pairs
    # @return {boolean} true if can finish all courses or false
    def canFinish(self, numCourses, prerequisites):
        # Write your code here
        edges = {i: [] for i in xrange(numCourses)}
        degrees = [0 for i in xrange(numCourses)]
        for i, j in prerequisites:
            edges[j].append(i)
            degrees[i] += 1
        import Queue
        queue, count = Queue.Queue(maxsize = numCourses), 0

        for i in xrange(numCourses):
            if degrees[i] == 0:
                queue.put(i)

        while not queue.empty():
            node = queue.get()
            count += 1

            for x in edges[node]:
                degrees[x] -= 1
                if degrees[x] == 0:
                    queue.put(x)

        return count == numCourses