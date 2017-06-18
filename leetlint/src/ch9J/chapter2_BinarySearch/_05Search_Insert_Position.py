
class Solution:
    """
    @param A : a list of integers
    @param target : an integer to be inserted
    @return : an integer
    """
    def searchInsert(self, A, target):
        if len(A) == 0:
            return 0

        start, end = 0, len(A) - 1
        # first position >= target
        while start + 1 < end:
            mid = (start + end) / 2
            if A[mid] >= target:
                end = mid
            else:
                start = mid

        if A[start] >= target:
            return start
        if A[end] >= target:
            return end
        return len(A)