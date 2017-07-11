# /** 443 Two Sum - Greater than target


class Solution:
    # @param nums, an array of integer
    # @param target, an integer
    # @return an integer
    def twoSum2(self, nums, target):
        # Write your code here
        # print nums, target
        debug =False
        count = 0
        if (nums == None or len(nums) == 0):
            return count
        nums.sort()
        if debug:print nums
        i = 0
        j = len(nums) - 1
        while (i < j):
            if debug : print "new while"
            if debug:print "pos:",i,j
            if debug : print "value:",nums[i],nums[j]
            temp_sum = nums[i] + nums[j]
            if temp_sum <= target:
                # count +=len(nums)-1-j
                i += 1
            elif temp_sum > target:
                count+= j-i
                j -= 1
            if debug : print count
        if debug:print "len",len(nums)
        # if i!=len(nums)-2:count += (len(nums)-1-i + 1)*(len(nums)-1-i)/2
        return count