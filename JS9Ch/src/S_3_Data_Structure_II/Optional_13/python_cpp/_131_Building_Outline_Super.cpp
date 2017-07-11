

struct Node {
    int x, h, isLeft;
    Node(int _x, int _h, int _isLeft):x(_x), h(_h),isLeft(_isLeft){}
    bool operator <(const Node &a) const {
        return x < a.x || x == a.x && isLeft < a.isLeft;
    }
};

class Solution {
public:
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */

    vector<vector<int> > buildingOutline(vector<vector<int> > &buildings) {
        // write your code here
        multiset<int> s;
        vector<vector<int> > result;
        vector<Node> p;
        int len = buildings.size();
        if (len == 0)
            return result;

        for (int i = 0; i < len; ++i) {
            p.push_back(Node(buildings[i][0], buildings[i][2], 1));
            p.push_back(Node(buildings[i][1], buildings[i][2], 0));
        }


        sort(p.begin(), p.end());

        len = 2 * len;
        int last = 0, size = 0;
        for (int i = 0; i < len; ++i) {
            if (s.empty())
                last = p[i].x;

            if (!s.empty() && (*s.rbegin()) <= p[i].h && last < p[i].x) {
                vector<int> tmp;
                if (size > 0 && result[size - 1][2] == (*s.rbegin()) && result[size - 1][1] == last) {
                    result[size -1][1] = p[i].x;
                } else {
                    tmp.push_back(last);
                    tmp.push_back(p[i].x);
                    tmp.push_back(*s.rbegin());
                    result.push_back(tmp);
                    size ++;
                }
                last = p[i].x;
            }
            if (p[i].isLeft == 1)
                s.insert(p[i].h);
            else
                s.erase(s.lower_bound(p[i].h));
        }

        return result;
    }
};