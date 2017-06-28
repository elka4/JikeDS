'''/** 121. Word Ladder II
 * Hard'''



# version 1:
# Python Version:
class Solution:
    # @param start, a string
    # @param end, a string
    # @param dict, a set of string
    # @return a list of lists of string

    def getEntry(self, word, index):
        return word[:index] + word[index + 1:]

    def buildIndexes(self, length, dict):
        indexes = []
        for i in range(length):
            index = {}
            for word in dict:
                entry = self.getEntry(word, i)
                words = index.get(entry, [])
                words.append(word)
                index[entry] = words
            indexes.append(index)
        return indexes

    def BFS(self, start, end):
        self.distance = {}
        self.distance[start] = 0
        queue = [start]
        while len(queue) != 0:
            head = queue[0]
            del queue[0]
            for word in self.getNextWord(head):
                if word not in self.distance:
                    self.distance[word] = self.distance[head] + 1
                    queue.append(word)

    def DFS(self, curt, target, path):
        if curt == target:
            self.results.append(list(path))
            return

        for word in self.getNextWord(curt):
            if self.distance.get(word, -2) + 1 == self.distance[curt]:
                path.append(word)
                self.DFS(word, target, path)
                del path[len(path) - 1]

    def getNextWord(self, word):
        for i in range(len(word)):
            entry = self.getEntry(word, i)
            if entry in self.indexes[i]:
                for nextWord in self.indexes[i][entry]:
                    if nextWord != word:
                        yield nextWord

    def findLadders(self, start, end, dict):
        if start is None or end is None or len(start) != len(end):
            return []
        if start not in dict or end not in dict:
            return []

        self.dict = dict
        self.indexes = self.buildIndexes(len(start), dict)
        self.BFS(end, start)

        self.results = []
        if start in self.distance:
            self.DFS(start, end, [start])
        return self.results

# version 2:
class Solution(object):
    # @param start, a string
    # @param end, a string
    # @param dict, a set of string
    # @return a list of lists of string
    def findLadders(self, start, end, dict):
        # Write your code here
        dict.add(start)
        dict.add(end)

        def buildPath(path,word):
            if len(preMap[word]) == 0:
                result.append([word] + path)
                return
            path.insert(0,word)
            for w in preMap[word]:
                buildPath(path,w)
            path.pop(0)

        length = len(start)
        preMap = {}
        for word in dict:
            preMap[word] = []
        result = []
        cur_level = set()
        cur_level.add(start)

        while True:
            pre_level = cur_level
            cur_level = set()
            for word in pre_level:
                dict.remove(word)
            for word in pre_level:
                for i in range(length):
                    left = word[:i]
                    right = word[i+1:]
                    for c in 'abcdefghijklmnopqrstuvwxyz':
                        if c != word[i]:
                            nextWord = left + c + right
                            if nextWord in dict:
                                preMap[nextWord].append(word)
                                cur_level.add(nextWord)
            if len(cur_level) == 0:
                return []
            if end in cur_level:
                break
        buildPath([],end)
        return result