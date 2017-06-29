
class Solution:
    # @param S: a list of integers
    # @return: a integer
    def triangleCount(self, S):
        edges = sorted(S, reverse=True)
        sum = 0
        for index, longest in enumerate(edges):
            i, j = index + 1, index + 2
            while j < len(edges) and edges[i] + edges[j] > longest:
                j += 1
            j -= 1
            while i < j:
                sum += j - i
                i += 1
                while i < j and edges[i] + edges[j] <= longest:
                    j -= 1
        return sum