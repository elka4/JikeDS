# /** 10 String Permutation II


class Solution:
    # @param {string} str a string
    # @return {string[]} all permutations
    def stringPermutation2(self, str):
        # Write your code here
        result = []
        if str == '':
            return ['']

        s = list(str)
        s.sort()
        while True:
            result.append(''.join(s))
            s = self.nextPermutation(s)
            if s is None:
                break

        return result

    def nextPermutation(self, num):
        # write your code here
        n = len(num)
        i = n - 1
        while i >= 1 and num[i - 1] >= num[i]:
            i -= 1
        if i == 0: return None
        j = n - 1
        while j >= 0 and num[j] <= num[i - 1]:
            j -= 1
        num[i - 1], num[j] = num[j], num[i - 1]
        num[i:] = num[i:][::-1]
        return num