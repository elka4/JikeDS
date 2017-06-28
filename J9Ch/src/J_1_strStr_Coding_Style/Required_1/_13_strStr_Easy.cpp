// 13
// strStr
// Easy


#include <cstring>
#include <iostream>

using namespace std;


class Solution {
 public:
    /**
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    int strStr(const char *source, const char *target) {
        if (source == NULL || target == NULL) {
            return -1;
        }
        int target_size = strlen(target);
        int source_size = strlen(source);
        int i, j;
        for (i = 0; i < source_size - target_size + 1; i++) {
            for (j = 0; j < target_size; j++) {
                if (source[i + j] != target[j]) {
                    break;
                }
            }
            if (j == target_size) {
                return i;
            }
        }
        return -1;
    }
};