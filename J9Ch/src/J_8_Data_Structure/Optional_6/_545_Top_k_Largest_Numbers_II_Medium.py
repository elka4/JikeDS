# /** 545 Top k Largest Numbers II


import heapq

class Solution:

    # @param {int} k an integer
    def __init__(self, k):
        self.k = k
        self.nums = []
        heapq.heapify(self.nums)

    # @param {int} num an integer
    def add(self, num):
        if len(self.nums) < self.k:
            heapq.heappush(self.nums, num)
        elif num > self.nums[0]:
            heapq.heappop(self.nums)
            heapq.heappush(self.nums, num)

    # @return {int[]} the top k largest numbers in array
    def topk(self):
        return sorted(self.nums, reverse=True)