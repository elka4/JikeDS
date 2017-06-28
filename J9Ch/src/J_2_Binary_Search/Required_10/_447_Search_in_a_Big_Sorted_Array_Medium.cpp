/**447. Search in a Big Sorted Array
 * Medium*/

/**
 * Definition of ArrayReader:
 *
 * class ArrayReader {
 * public:
 *     int get(int index) {
 *          // return the number on given index,
 *          // return -1 if index is less than zero.
 *     }
 * };
 */
class ArrayReader {
public:
    int get(int index) {
        return 1;
    }
}

class Solution {
public:
    /**
     * @param reader: An instance of ArrayReader.
     * @param target: An integer
     * @return: An integer which is the first index of target.
     */
    int searchBigSortedArray(ArrayReader *reader, int target) {
        int e = 1;
        while (reader->get(e) < target) {
            e *= 2;
        }

        int s = 0;
        while (s + 1 < e) {
            int mid = s + (e - s) / 2;
            int v = reader->get(mid);
            if (v == target) {
                e = mid;
            } else if (v < target) {
                s = mid;
            } else {
                e = mid;
            }
        }
        if (reader->get(s) == target) {
            return s;
        } else if (reader->get(e) == target) {
            return e;
        }
        return -1;
    }
};