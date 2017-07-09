
class Vector2D {
public:
    Vector2D(vector<vector<int>>& vec2d) {
        // Initialize your data structure here
        begin = vec2d.begin();
        end = vec2d.end();
        pos = 0;
    }

    int next() {
        // Write your code here
        hasNext();
        return (*begin)[pos++];
    }

    bool hasNext() {
        // Write your code here
        while (begin != end && pos == (*begin).size())
            begin++, pos = 0;
        return begin != end;
    }

private:
    vector<vector<int>>::iterator begin, end;
    int pos;
};

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i(vec2d);
 * while (i.hasNext()) cout << i.next();
 */