
class Solution {
public:
    /**
     * @param heights: a matrix of integers
     * @return: an integer
     */
    int trapRainWater(vector<vector<int> > &heights) {
        // write your code here
        vector<int> area, hgt;
        int m = heights.size();
        if (m==0) return 0;
        int n = heights[0].size();
        if (n==0) return 0;
        bool flag[m*n];
        for (int i=0; i<m*n; ++i) flag[i] = true;
        for (int i=0; i<m; ++i) {
            hgt.push_back(heights[i][0]);
            area.push_back(i*n);
            adjust_up(hgt, area);
            flag[i*n] = false;
            hgt.push_back(heights[i][n-1]);
            area.push_back(i*n+n-1);
            adjust_up(hgt, area);
            flag[i*n+n-1] = false;
        }
        for (int i=1; i<n-1; ++i) {
            hgt.push_back(heights[0][i]);
            area.push_back(i);
            adjust_up(hgt, area);
            flag[i] = false;
            hgt.push_back(heights[m-1][i]);
            area.push_back((m-1)*n+i);
            adjust_up(hgt, area);
            flag[(m-1)*n+i] = false;
        }
        int count = m+m+n+n-4, ans = 0;
        while (count<m*n) {
            int position = area[0], h = hgt[0];
            hgt[0] = hgt.back(); hgt.pop_back();
            area[0] = area.back(); area.pop_back();
            adjust_down(hgt, area);
            for (int i=0; i<4; ++i) {
                int p = position;
                if (i==0)
                    if (p-n>=0 && flag[p-n]) p -= n;
                    else continue;
                else
                    if (i==1)
                        if (p%n!=0 && flag[p-1]) --p;
                        else continue;
                    else
                        if (i==2)
                            if ((p+1)%n!=0 && flag[p+1]) ++p;
                            else continue;
                        else
                            if (p+n<m*n && flag[p+n]) p += n;
                            else continue;
                int x = p/n, y = p-x*n;
                hgt.push_back(max(heights[x][y], h));
                area.push_back(p);
                adjust_up(hgt, area);
                flag[p] = false; ++count;
                ans += max(heights[x][y], h)-heights[x][y];
            }
        }
        return ans;
    }

    void adjust_up(vector<int> &hgt, vector<int> &area) {
        int p = hgt.size()-1;
        while (p>0) {
            if (hgt[p]>=hgt[(p-1)/2]) break;
            swap(hgt[p], hgt[(p-1)/2]);
            swap(area[p], area[(p-1)/2]);
            p = (p-1)/2;
        }
    }

    void adjust_down(vector<int> &hgt, vector<int> &area) {
        int p = 0;
        while (p<hgt.size()) {
            int t = p*2+1;
            if (t>=hgt.size()) break;
            if (t+1<hgt.size() && hgt[t+1]<hgt[t]) ++t;
            if (hgt[p]<=hgt[t]) break;
            swap(hgt[p], hgt[t]);
            swap(area[p], area[t]);
            p = t;
        }
    }
};