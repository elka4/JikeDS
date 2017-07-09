'''/**
 * 624 Remove Substrings'''


class Solution:
    # @param {string} s a string
    # @param {set} dict a set of n substrings
    # @return {int} the minimum length
    def minLength(self, s, dict):
        # Write your code here
        import Queue
        que = Queue.Queue()
        que.put(s)
        hash = set([s])

        min = len(s)

        while not que.empty():
            s = que.get()
            for sub in dict:
                found = s.find(sub)
                while found != -1:
                    new_s = s[:found] + s[found + len(sub):]
                    if new_s not in hash:
                        if len(new_s) < min:
                            min = len(new_s)
                        que.put(new_s)
                        hash.add(new_s)

                    found = s.find(sub, found + 1)
        return min