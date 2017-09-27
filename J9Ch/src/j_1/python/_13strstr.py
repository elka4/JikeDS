class Solution:
    def strStr(self, source, target):
        if source is None or target is None:
            return -1
        return source.find(target)