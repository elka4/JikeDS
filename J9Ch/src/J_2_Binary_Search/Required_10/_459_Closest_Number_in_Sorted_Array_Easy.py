'''/**459. Closest Number in Sorted Array
 * Easy'''


class Solution:
    # @param {int[]} A an integer array sorted in ascending order
    # @param {int} target an integer
    # @return {int} an integer
    def closestNumber(self, A, target):
        if not A:
            return -1
        start, end = 0, len(A) - 1
        while start + 1 < end:
            mid = (start + end) / 2
            if A[mid] == target:
                return mid
            elif A[mid] > target:
                end = mid
            else:
                start = mid
        if A[end] - target > target - A[start]:
            return start
        else:
            return end

A = [1,4,6,10,20]
B = 21
sol = Solution()
print(sol.closestNumber(A, B))
print(Solution().closestNumber(A, B))