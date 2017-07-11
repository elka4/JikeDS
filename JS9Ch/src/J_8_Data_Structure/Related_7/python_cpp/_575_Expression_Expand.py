

#version 1
class Solution:
    # @param {string} s  an expression includes numbers, letters and brackets
    # @return {string} a string
    def expressionExpand(self, s):
        stack = []
        number = 0
        for char in s:
            if char.isdigit():
                number = number * 10 + ord(char) - ord('0')
            elif char == '[':
                stack.append(number)
                number = 0
            elif char == ']':
                strs = []
                while len(stack):
                    top = stack.pop()
                    if type(top) == int:
                        stack.append(''.join(reversed(strs)) * top)
                        break
                    strs.append(top)
            else:
                stack.append(char)
        return ''.join(stack)

# version 2
class Solution:
    # @param {string} s  an expression includes numbers, letters and brackets
    # @return {string} a string
    def expressionExpand(self, s):
        # Write your code here
        if '[' not in s:
            return s

        if not s[0].isdigit():
            index = 0
            while not s[index].isdigit():
                index += 1

            left_str = s[0: index]
            right_str = s[index:]
            times = 1
        else:
            left = s.find('[')
            times = int(s[0: left])
            pair = 0
            for index in xrange(left, len(s)):
                if s[index] == '[':
                    pair += 1
                elif s[index] == ']':
                    pair -= 1
                if pair == 0:
                    right = index
                    break

            left_str = s[left + 1 : right]
            right_str = s[right + 1: ]

        return self.expressionExpand(left_str) * times + \
               self.expressionExpand(right_str)