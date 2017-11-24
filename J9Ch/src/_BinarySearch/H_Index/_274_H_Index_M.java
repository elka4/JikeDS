package _BinarySearch.H_Index;

import java.util.Arrays;

//  274. H-Index
//  https://leetcode.com/problems/h-index/description/
//
public class _274_H_Index_M {
//https://leetcode.com/problems/h-index/solution/
//Approach #1 (Sorting) [Accepted]
public int hIndex1(int[] citations){
    // sorting the citations in ascending order
    Arrays.sort(citations);
    // finding h-index by linear search
    int i = 0;
    while(i < citations.length && citations[citations.length - 1 - i] > i){
        i++;
    }
    return i; // after the while loop, i = i' +1
}

//Approach #2 (Counting) [Accepted]
public int hIndex2(int[] citations) {
    int n = citations.length;
    int[] papers = new int[n + 1];
    // counting papers for each citation number
    for (int c:citations) {
        papers[Math.min(n, c)]++;
    }
    //finding the h-index
    int k = n;
    for (int s = papers[n]; k > s; s += papers[k]){
        k--;
    }
    return k;
}



//---------------------------------/////////////////////
    public int hIndex(int[] citations) {
        Arrays.sort(citations);

        int result = 0;
        for(int i=0; i<citations.length; i++){
            int smaller = Math.min(citations[i], citations.length-i);
            result = Math.max(result, smaller);
        }

        return result;
    }
}
/*
Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.
 */
