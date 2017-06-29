


class Solution:
    # @param {int[]} A an integer arrays sorted in ascending order
    # @param {int[]} B an integer arrays sorted in ascending order
    # @param {int} k an integer
    # @return {int} an integer
    def kthSmallestSum(self, A, B, k):
        # Write your code here
        if not A or not B:
            return 0
        import heapq
        m, n = len(A), len(B)
        def vertical_search(k):
            heap = []
            for i in range(min(k, n)):
                heapq.heappush(heap, (A[0] + B[i], 0, i))
            while k > 1:
                min_element = heapq.heappop(heap)
                x, y = min_element[1], min_element[2]
                if x + 1 < m:
                    heapq.heappush(heap, (A[x + 1] + B[y], x + 1, y))
                k -= 1
            return heapq.heappop(heap)[0]
        return vertical_search(k)