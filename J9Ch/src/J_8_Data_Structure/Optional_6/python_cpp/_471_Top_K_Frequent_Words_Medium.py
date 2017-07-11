# /** 471 Top K Frequent Words


class Solution:
    # @param {string[]} words a list of string
    # @param {int} k an integer
    # @return {string[]} a list of string
    def topKFrequentWords(self, words, k):
        # Write your code here
        dict = {}
        for word in words:
            if word not in dict:
                dict[word] = 1
            else:
                dict[word] += 1
        p = []
        for key, value in dict.items():
            p.append((value, key))

        p.sort(cmp=self.cmp)
        result = []
        for i in xrange(k):
            result.append(p[i][1])
        return result

    def cmp(self, a, b):
        if a[0] > b[0] or a[0] == b[0] and a[1] < b[1]:
            return -1
        elif a[0] == b[0] and a[1] == b[1]:
            return 0
        else:
            return 1