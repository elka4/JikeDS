'''/** 607 Two Sum - Data structure design
 * Easy'''


class TwoSum(object):

    def __init__(self):
        # initialize your data structure here
        self.count = {}


    # Add the number to an internal data structure.
    # @param number {int}
    # @return nothing
    def add(self, number):
        # Write your code here
        if number in self.count:
            self.count[number] += 1
        else:
            self.count[number] = 1



    # Find if there exists any pair of numbers which sum is equal to the value.
    # @param value {int}
    # @return true if can be found or false
    def find(self, value):
        # Write your code here
        for num in self.count:
            if value - num in self.count and \
                    (value - num != num or self.count[num] > 1):
                return True
        return False



        # Your TwoSum object will be instantiated and called as such:
        # twoSum = TwoSum()
        # twoSum.add(number)
        # twoSum.find(value)