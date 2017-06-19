class Solution:
    # @param {int[]} A an integer array sorted in ascending order
    # @param {int} target an integer
    # @return {int} an integer
    def findPosition(self, A, target):
        # Write your code here
        if len(A) == 0 or A == None:
            return -1

        start = 0
        end = len(A) - 1

        if target < A[start] or target > A[end]:
            return -1

        while start + 1 < end:
            mid = start + (end - start) / 2
            if target == A[mid]:
                return mid
            elif target > A[mid]:
                start = mid
            else:
                end = mid

        if target == A[end]:
            return end
        elif target == A[start]:
            return start
        else:
            return -1

if __name__ == "__main__":
    sol = Solution()
    '''nums = [1, 2]'''
    '''nums = {1, 2}'''
    A = (1, 2, 3, 4, 5, 8, 9)

    print sol.findPosition(A, 5)
