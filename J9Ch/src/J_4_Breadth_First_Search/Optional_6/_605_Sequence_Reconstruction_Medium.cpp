/**605 Sequence Reconstruction*/


class Solution {
public:
    /**
     * @param org a permutation of the integers from 1 to n
     * @param seqs a list of sequences
     * @return true if it can be reconstructed only one or false
     */
    bool sequenceReconstruction(vector<int>& org, vector<vector<int>>& seqs) {
        // Write your code here
        unordered_map<int, int> pos, pre;
        for (int i = 0; i < org.size(); ++i) pos[org[i]] = i;
        for (auto& seq : seqs) {
            for (int i = 0; i < seq.size(); ++i) {
                if (pos.find(seq[i]) == pos.end()) {
                    return false;
                }
                if (i > 0 && pos[seq[i - 1]] >= pos[seq[i]]) {
                    return false;
                }
                if (pre.find(seq[i]) == pre.end()) {
                    pre[seq[i]] = (i > 0) ? pos[seq[i - 1]] : -1;
                } else {
                    pre[seq[i]] = max(pre[seq[i]], (i > 0) ? pos[seq[i - 1]] : -1);
                }
            }
        }
        for (int i = 0; i < org.size(); ++i) {
            if (pre[org[i]] != i - 1)
                return false;
        }
        return true;
    }
};