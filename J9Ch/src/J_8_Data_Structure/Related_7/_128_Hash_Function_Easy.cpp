/** 128 Hash Function*/

class Solution {
public:
    int hashCode(string key,int HASH_SIZE) {
        int ans = 0;
        for(int i = 0; i < key.size();i++) {
            ans = (1LL * ans * 33 + key[i]) % HASH_SIZE;
        }
	return ans;
    }
};