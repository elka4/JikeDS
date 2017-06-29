


# Definition for a point.
# class Point:
#     def __init__(self, a=0, b=0):
#         self.x = a
#         self.y = b

class Solution:
    # @param {int} n an integer
    # @param {int} m an integer
    # @param {Pint[]} operators an array of point
    # @return {int[]} an integer array
    def __init__(self):
        self.f = []

    def find(self, x):
        if self.f[x] == x:
            return x
        self.f[x] = self.find(self.f[x])
        return self.f[x]

    def merge(self, x, y):
        if self.f[x] == -1 or self.f[y] == -1:
            return False

        x = self.find(x)
        y = self.find(y)
        if x != y:
            self.f[x] = y
            return True
        else:
            return False

    def inside(self, x, y, n, m):
        return x >= 0 and y >=0 and x < n and y < m

    def numIslands2(self, n, m, operators):
        # Write your code here
        d = [[0,1],[0,-1],[1,0],[-1,0]]
        area = 0;
        cnt = len(operators)
        ret = []
        for i in xrange(n * m): self.f.append(-1)
        for point in operators:
            num = point.x * m + point.y;
            if (self.f[num] == -1):
                area += 1;
                self.f[num] = num

            for k in xrange(4):
                x = point.x + d[k][0];
                y = point.y + d[k][1];

                if self.inside(x, y, n, m):
                    if self.merge(x * m + y, num):
                        area -= 1

            ret.append(area);

        return ret