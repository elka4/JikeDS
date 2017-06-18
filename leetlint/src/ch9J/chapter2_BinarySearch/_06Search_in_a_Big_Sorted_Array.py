"""
Definition of ArrayReader:
class ArrayReader:
    def get(self, index):
        # this would return the number on the given index
        # return -1 if index is less than zero.
"""
class Solution:
    # @param {ArrayReader} reader: An instance of ArrayReader
    # @param {int} target an integer
    # @return {int} an integer
    def searchBigSortedArray(self, reader, target):
        index = 0
        while reader.get(index) < target:
            index = index * 2 + 1

        start, end = 0, index
        while start + 1 < end:
            mid = (start + end) / 2
            if reader.get(mid) >= target:
                end = mid
            else:
                start = mid

        if reader.get(start) == target:
            return start
        if reader.get(end) == target:
            return end
        return -1