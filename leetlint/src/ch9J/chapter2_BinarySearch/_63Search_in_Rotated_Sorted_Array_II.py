
class Solution:
    # @param num: a rotated sorted array
    # @return: the minimum number in the array
    def findMin(self, num):
        # write your code here
        min = num[0]
        start, end = 0, len(num) - 1
        while start<end:
            mid = (start+end)/2
            if num[mid]>num[end]:
                start = mid+1
            elif num[mid]<num[end]:
                end = mid
            else:
                end = end - 1
        return num[start]