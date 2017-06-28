# /** 211 String Permutation


class Solution:
    # @param {string} A a string
    # @param {string} B a string
    # @return {boolean} a boolean
    def stringPermutation(self, A, B):
        # Write your code here
        A = sorted(A)
        B = sorted(B)
        return A == B