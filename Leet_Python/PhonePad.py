
def getWords(numbers, pad):
    if len(numbers) == 1:
        return pad[numbers[0]]
    else:
        words = getWords(numbers[:-1], pad)
        results = []
        for c in pad[numbers[-1]]:
            results += [w + c for w in words]
            return results

print(getWords("32", {"2":["a", "b", "c"], "3":["d", "e", "f"]}))


class Solution:
    # @param {string} digits
    # @return {string[]}
    def letterCombinations(self, digits):
        mapping = {'2': 'abc', '3': 'def', '4': 'ghi', '5': 'jkl',
                   '6': 'mno', '7': 'pqrs', '8': 'tuv', '9': 'wxyz'}
        if len(digits) == 0:
            return []
        if len(digits) == 1:
            return list(mapping[digits[0]])
        prev = self.letterCombinations(digits[:-1])
        additional = mapping[digits[-1]]
        return [s + c for s in prev for c in additional]

# s = Solution()
print(Solution().letterCombinations("32"))
