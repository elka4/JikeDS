
class ZigzagIterator2 {
private:
    int k, p;
    vector<vector<int>::iterator> begins, ends;

public:
    /**
     * @param vecs a list of 1d vectors
     */
    ZigzagIterator2(vector<vector<int>>& vecs) {
        // initialize your data structure here.
        int n = vecs.size();
        k = 0, p = 0;
        for (int i = 0; i < n; ++i) {
            if (vecs[i].size() > 0) {
                k ++;
                begins.push_back(vecs[i].begin());
                ends.push_back(vecs[i].end());
            }
        }
    }

    int next() {
        // Write your code here
        int elem =  *begins[p] ++;
        if (begins[p] == ends[p]) {
            begins.erase(begins.begin() + p);
            ends.erase(ends.begin() + p);
            k --;
            if (k > 0)
                p %= k;
        } else
            p = (p + 1) % k;
        return elem;
    }

    bool hasNext() {
        // Write your code here
        return k > 0;
    }
};

/**
 * Your ZigzagIterator2 object will be instantiated and called as such:
 * ZigzagIterator2 solution(vecs);
 * while (solution.hasNext()) result.push_back(solution.next());
 * Ouptut result
 */