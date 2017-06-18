
class Solution:
    # @param s: a list of char
    # @param offset: an integer
    # @return: nothing
    def rotateString(self, s, offset):
        # write you code here
        if len(s) > 0:
            offset = offset % len(s)

        temp = (s + s)[len(s) - offset : 2 * len(s) - offset]

        for i in xrange(len(temp)):
            s[i] = temp[i]