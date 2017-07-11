
class ZigzagIterator {
private:
    int p;
    vector<int>::iterator begin[2], end[2];

public:
    /**
     * @param v1 v2 two 1d vectors
     */
    ZigzagIterator(vector<int>& v1, vector<int>& v2) {
        // initialize your data structure here.
        begin[0] = v1.begin(), begin[1] = v2.begin();
        end[0] = v1.end(), end[1] = v2.end();
        p = 0;
    }

    int next() {
        // Write your code here
        int elem;
        if (begin[0] == end[0])
            elem = *begin[1]++;
        else if (begin[1] == end[1])
            elem = *begin[0]++;
        else {
            elem = *begin[p]++;
            p = (p + 1) % 2;
        }
        return elem;
    }

    bool hasNext() {
        // Write your code here
        return begin[0] != end[0] || begin[1] != end[1];
    }
};

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator solution(v1, v2);
 * while (solution.hasNext()) result.push_back(solution.next());
 * Ouptut result
 */