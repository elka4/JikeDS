package _BinarySearch.Count;
import java.util.Arrays;


//  leetcode 475. Heaters
//  4:
public class _475_BinarySearch_Heaters_E {
//-----------------------------------------------------------------------------

/*    Short and Clean Java Binary Search Solution
    The idea is to leverage decent Arrays.binarySearch() function provided by Java.

    For each house, find its position between those heaters (thus we need the heaters array to be sorted).
    Calculate the distances between this house and left heater and right heater, get a MIN value of those two values. Corner cases are there is no left or right heater.
    Get MAX value among distances in step 2. It's the answer.
    Time complexity: max(O(nlogn), O(mlogn)) - m is the length of houses, n is the length of heaters.*/

    public class Solution {
        public int findRadius(int[] houses, int[] heaters) {
            Arrays.sort(heaters);
            int result = Integer.MIN_VALUE;

            for (int house : houses) {
                int index = Arrays.binarySearch(heaters, house);

                if (index < 0) {
                    index = -(index + 1);
                }

                int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
                int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;

                result = Math.max(result, Math.min(dist1, dist2));
            }

            return result;
        }
    }
//-----------------------------------------------------------------------------
    //2
/*
    Simple Java Solution with 2 Pointers
    Based on 2 pointers, the idea is to find the nearest heater for each house,
    by comparing the next heater with the current heater.
*/

    public class Solution2 {
        public int findRadius(int[] houses, int[] heaters) {
            Arrays.sort(houses);
            Arrays.sort(heaters);

            int i = 0, j = 0, res = 0;
            while (i < houses.length) {
                while (j < heaters.length - 1
                        && Math.abs(heaters[j + 1] - houses[i]) <= Math.abs(heaters[j] - houses[i])) {
                    j++;
                }
                res = Math.max(res, Math.abs(heaters[j] - houses[i]));
                i++;
            }

            return res;
        }
    }
//-----------------------------------------------------------------------------
    //3
    public class Solution3 {
        public int findRadius(int[] houses, int[] heaters) {
            Arrays.sort(houses);
            Arrays.sort(heaters);

            int i = 0, res = 0;
            for (int house : houses) {
                while (i < heaters.length - 1 && heaters[i] + heaters[i + 1] <= house * 2) {
                    i++;
                }
                res = Math.max(res, Math.abs(heaters[i] - house));
            }

            return res;
        }
    }

//-----------------------------------------------------------------------------
    //4
    //    My Java Binary Search Solution, Concise and Easy to Understand
    public class Solution4 {
        public int findRadius(int[] houses, int[] heaters) {
            int m = houses.length;
            int n = heaters.length;
            Arrays.sort(houses);
            Arrays.sort(heaters);

            int left = 0;
            int right = Math.max(Math.abs(houses[0] - heaters[n - 1]),
                                 Math.abs(houses[m - 1] - heaters[0]));

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (cover(mid, houses, heaters))
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            return left;
        }

        public boolean cover(int radius, int[] houses, int[] heaters) {
            int m = houses.length;
            int n = heaters.length;
            int j = 0;

            for (int i = 0; i < n; i++) {
                long low = heaters[i] - radius;
                long high = heaters[i] + radius;
                while (j < m && low <= (long)houses[j] && (long)houses[j] <= high)
                    j++;
            }
            return j == m;
        }
    }
//-----------------------------------------------------------------------------
}
/*
Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.

Note:
Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
As long as a house is in the heaters' warm radius range, it can be warmed.
All the heaters follow your radius standard and the warm radius will the same.

Example 1:
Input: [1,2,3],[2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.

Example 2:
Input: [1,2,3,4],[1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.

 */