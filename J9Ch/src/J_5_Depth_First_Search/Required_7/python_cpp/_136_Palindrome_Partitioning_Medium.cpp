/** 136. Palindrome Partitioning
 * Medium*/


class Solution {
public:
    /**
     * @param s: A string
     * @return: A list of lists of string
     */
    vector<vector<string>>v;
    vector<string>v1;
    vector<vector<string>> partition(string s) {
        // write your code here
        v.clear();
        if(s.size()==0)return v;
        v1.clear();
        dfs(0,s);
        return v;
    }
    bool ishui(string s) {
        int i=0,j=s.size()-1;
        while(i<j)
        {
            if(s[i]==s[j])
            {
                i++;j--;
            }
            else return false;
        }
        return true;
    }
    void dfs(int depth,string s) {
        if(depth==s.size())
        {
            v.push_back(v1);
        }
        if(depth<s.size())
        {
            for(int i=depth;i<s.size();i++)
            {
                if(ishui(s.substr(depth,i-depth+1)))
                {
                    v1.push_back(s.substr(depth,i-depth+1));
                    dfs(i+1,s);
                    v1.pop_back();
                }
            }
        }
    }
};