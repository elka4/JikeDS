
class Solution:

    def search(self, nums, S, index):
        if index == len(nums):
            self.results.append(S)
            return

        self.search(nums, S + [nums[index]], index + 1)
        self.search(nums, S, index + 1)

    def subsets(self, nums):
        self.results = []
        self.search(sorted(nums), [], 0)
        return self.results

if __name__ == "__main__":
    sol = Solution()
    '''nums = [1, 2]'''
    '''nums = {1, 2}'''
    nums = [1, 2]
    sorted(nums)
    print sol.subsets(nums)