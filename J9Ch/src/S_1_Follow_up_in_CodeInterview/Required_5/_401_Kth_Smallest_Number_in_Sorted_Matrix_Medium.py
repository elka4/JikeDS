

# Version 1 using heapq

import heapq

class Solution:

    def kthSmallest(self, matrix, k):
        # Write your code here
        m, n = len(matrix), len(matrix[0])
        visited = [[False] * n for _ in range(m)]
        queue = [(matrix[0][0], 0, 0)]
        result = None
        visited[0][0] = True
        for _ in range(k):
            result, i, j = heapq.heappop(queue)
            if i + 1 < m and not visited[i + 1][j]:
                visited[i + 1][j] = True
                heapq.heappush(queue, (matrix[i + 1][j], i + 1, j))
            if j + 1 < n and not visited[i][j + 1]:
                visited[i][j + 1] = True
                heapq.heappush(queue, (matrix[i][j + 1], i, j + 1))
        return result



# Version 2
class Solution:
    # @param matrix: a matrix of integers
    # @param k: an integer
    # @return: the kth smallest number in the matrix
    def kthSmallest(self, matrix, k):
        # write your code here
        ind = 0
        val = 0
        index = []
        nums = []
        top = []
        last = []
        m = len(matrix)
        n = len(matrix[0])
        for i in xrange(m): self.heapAdd(nums, index, matrix[i][0], i)
        for i in xrange(m): last.append(0)
        while len(top)<k:
            val = nums[0]
            ind = index[0]
            self.heapDel(nums, index)
            if last[ind]!=n-1:
                last[ind] += 1
                self.heapAdd(nums, index, matrix[ind][last[ind]], ind)
            top.append(val)
        return top[len(top)-1]

    def heapAdd(self, nums, index, val, ind):
        nums.append(val)
        index.append(ind)
        n = len(nums)-1
        while n>0 and nums[n]<nums[(n-1)/2]:
            nums[n], nums[(n-1)/2] = nums[(n-1)/2], nums[n]
            index[n], index[(n-1)/2] = index[(n-1)/2], index[n]
            n = (n-1)/2

    def heapDel(self, nums, index):
        n = len(nums)-1
        if n>=0: nums[0] = nums[n]
        if n>=0: index[0] = index[n]
        if len(nums)>0: nums.pop()
        if len(index)>0: index.pop()
        n = 0
        while n*2+1<len(nums):
            t = n*2+1
            if t+1<len(nums) and nums[t+1]<nums[t]: t += 1
            if nums[n]<=nums[t]: break
            nums[n], nums[t] = nums[t], nums[n]
            index[n], index[t] = index[t],