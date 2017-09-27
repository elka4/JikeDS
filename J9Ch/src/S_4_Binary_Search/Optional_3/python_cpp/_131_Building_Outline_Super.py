
class HashHeap:

    def __init__(self):
        self.heap = [0]
        self.hash = {}

    def add(self, key, value):
        self.heap.append((key, value))
        self.hash[key] = self.heap[0] + 1
        self.heap[0] += 1
        self._siftup(self.heap[0])

    def remove(self, key):
        index = self.hash[key]
        self._swap(index, self.heap[0])
        del self.hash[self.heap[self.heap[0]][0]]
        self.heap.pop()
        self.heap[0] -= 1
        if index <= self.heap[0]:
            index = self._siftup(index)
            self._siftdown(index)

    def hasKey(self, key):
        return key in self.hash

    def max(self):
        return 0 if self.heap[0] == 0 else self.heap[1][1]

    def _swap(self, a, b):
        self.heap[a], self.heap[b] = self.heap[b], self.heap[a]
        self.hash[self.heap[a][0]] = a
        self.hash[self.heap[b][0]] = b

    def _siftup(self, index):
        while index != 1:
            if self.heap[index][1] <= self.heap[index / 2][1]:
                break
            self._swap(index, index / 2)
            index = index / 2
        return index

    def _siftdown(self, index):
        size = self.heap[0]
        while index < size:
            t = index
            if index * 2 <= size and self.heap[t][1] < self.heap[index * 2][1]:
                t = index * 2
            if index * 2 + 1 <= size and self.heap[t][1] < self.heap[index * 2 + 1][1]:
                t = index * 2 + 1
            if t == index:
                break
            self._swap(index, t)
            index = t
        return index

class Solution:
    # @param buildings: A list of lists of integers
    # @return: A list of lists of integers
    def buildingOutline(self, buildings):
        if len(buildings) == 0:
            return []

        begins = [(b[0], b[2], index) for index, b in enumerate(buildings)]
        ends = [(b[1], b[2], index) for index, b in enumerate(buildings)]
        heights = sorted(begins + ends, key=lambda x: x[0])

        hashheap = HashHeap()
        y = {}
        for x, height, index in heights:
            if hashheap.hasKey(index):
                hashheap.remove(index)
            else:
                hashheap.add(index, height)
            y[x] = hashheap.max()

        temp = []
        lastX, lastY = None, None
        for x in sorted(y.keys()):
            if lastX is not None and lastY != 0:
                temp.append((lastX, x, lastY))
            lastX, lastY = x, y[x]

        results = []
        lastInterval = temp[0]
        for start, end, height in temp[1:]:
            if start == lastInterval[1] and height == lastInterval[2]:
                lastInterval = lastInterval[0], end, height
            else:
                results.append(lastInterval)
                lastInterval = (start, end, height)
        results.append(lastInterval)
        return results


# 普通heap的做法

import heapq
class Solution(object):
    def getSkyline(self, buildings):
        if len(buildings) == 0:
            return []

        # All possible corner positions
        positions = set([b[0] for b in buildings] + [b[1] for b in buildings])

        heap = []  # Get highest height (As heapq maintains min_heap, store -height)
        result = [[-1, 0]]
        i = 0
        buildings_len = len(buildings)
        for pos in positions:
            while i < buildings_len and buildings[i][0] <= pos:
                # Add the new buildings whose left side is lefter than pos
                heapq.heappush( heap, (-buildings[i][2], buildings[i][1]) )
                i += 1
            while heap and heap[0][1] <= pos:
                # Remove the past buildings whose right side is lefter than pos
                heapq.heappop(heap)

            # Pick the highest existing building at this moment
            height = 0
            if heap != []:
                height = -heap[0][0]
            if height != result[-1][1]:
                result.append( [pos, height] )

        return result[1:]
