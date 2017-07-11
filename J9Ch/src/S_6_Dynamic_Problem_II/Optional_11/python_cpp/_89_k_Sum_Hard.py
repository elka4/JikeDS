
class Solution:

    def kSum(self, A, k, target):
        ans = [[[0 for i in range(target + 1)] for j in range(k + 1)] for K in range(len(A) + 1)]

        ans[0][0][0] = 1
        for I in range(len(A)):
            item = A[I]
            for J in range(target + 1):
                for K in range(k + 1):
                    tk = k - K
                    tj = target - J
                    ans[I + 1][tk][tj] = ans[I][tk][tj]
                    if tk - 1 >= 0 and tj - item >= 0:
                        ans[I + 1][tk][tj] += ans[I][tk - 1][tj - item]
        return ans[len(A)][k][target]