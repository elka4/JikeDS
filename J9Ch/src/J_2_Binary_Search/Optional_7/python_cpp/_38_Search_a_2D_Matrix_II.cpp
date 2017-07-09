
class Solution {
public:
    /**
     * @param matrix: A list of lists of integers
     * @param target: An integer you want to search in matrix
     * @return: An integer indicate the total occurrence of target in the given matrix
     */
    int searchMatrix(vector<vector<int> > &matrix, int target) {
        // write your code here
        if (matrix.empty())
        {
            return 0;
        }
        int m = matrix.size();
        int n = matrix[0].size();
        int i = m - 1;
        int j = 0;
        int occur = 0;
        while (i >= 0 && j < n)
        {
            if (matrix[i][j] == target)
            {
                ++occur;
            }
            if (matrix[i][j] < target)
            {
                ++j;
            }
            else
            {
                --i;
            }
        }
        return occur;
    }
};
