'''/** 611 Knight Shortest Path
 * Medium'''


# Definition for a point.
# class Point:
#     def __init__(self, a=0, b=0):
#         self.x = a
#         self.y = b

class Solution:
    # @param {boolean[][]} grid a chessboard included 0 (False) and 1 (True)
    # @param {Point} source a point
    # @param {Point} destination a point
    # @return {int} the shortest path
    def shortestPath(self, grid, source, destination):
        # Write your code here
        n = len(grid)
        m = len(grid[0])

        import sys
        record = [[sys.maxint for _ in xrange(m)] for i in xrange(n)]
        record[source.x][source.y] = 0

        import Queue
        q = Queue.Queue(maxsize = n * m)
        q.put(source)

        d = [(-2, -1), (-2, 1), (-1, 2), (1, 2), (2, 1), (2, -1), (1, -2), (-1, -2)]
        while not q.empty():
            head = q.get()
            for dx, dy in d:
                x, y = head.x + dx, head.y + dy
                if x >=0 and x < n and y >= 0 and y < m and not grid[x][y] and \
                                        record[head.x][head.y] + 1 < record[x][y]:
                    record[x][y] = record[head.x][head.y] + 1
                    q.put(Point(x, y))

        if record[destination.x][destination.y] == sys.maxint:
            return -1

        return record[destination.x][destination.y]