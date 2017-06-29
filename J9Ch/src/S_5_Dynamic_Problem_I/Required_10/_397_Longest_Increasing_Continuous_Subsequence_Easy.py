

class Solution:
    # @param {int[]} A an array of Integer
    # @return {int}  an integer
    def longestIncreasingContinuousSubsequence(self, A):
        return max(self.getLongest(A), self.getLongest(list(reversed(A))))

    def getLongest(self, A):
        length, longest = 0, 0
        for index, a in enumerate(A):
            if index == 0 or A[index] < A[index - 1]:
                length = 1
            else:
                length += 1
            longest = max(longest, length)
        return longest