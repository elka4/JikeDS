'''/** 148 Sort Colors
 *
 * Medium
'''


class Solution:
    def sort(self, A, flag, index):
        start, end = index, len(A) - 1
        while start <= end:
            while start <= end and A[start] == flag:
                start += 1
            while start <= end and A[end] != flag:
                end -= 1
            if start <= end:
                A[start], A[end] = A[end], A[start]
                start += 1
                end -= 1
        return start

    # @param A a list of integers
    # @return nothing, sort in place
    def sortColors(self, A):
        self.sort(A, 1, self.sort(A, 0, 0))