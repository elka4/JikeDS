package _10_DS.Kth;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


/*
 * Find K-th largest element in N arrays.

 Notice: You can swap elements in the array

Example:

In n=2 arrays [[9,3,2,4,7],[1,2,3,4,8]], the 3rd largest element is 7.

In n=2 arrays [[9,3,2,4,8],[1,2,3,4,2]], the 1st largest element is 9,
2nd largest element is 8, 3rd largest element is 7 and etc.

Tags: Heap
Related: Problems Medium Largest Number 18 %
 */


public class _5Kth_Largest_in_N_Arrays {

    class Node {
        public int value, from_id, index;
        public Node(int _v, int _id, int _i) {
            this.value = _v;
            this.from_id = _id;
            this.index = _i;
        }
    }

    /**
     * @param arrays a list of array
     * @param k an integer
     * @return an integer, K-th largest element in N arrays
     */
    public int KthInArrays(int[][] arrays, int k) {
        // Write your code here
        Queue<Node> queue =  new PriorityQueue<>(
        		k, new Comparator<Node>() {  
                public int compare(Node o1, Node o2) {  
                    if (o1.value > o2.value)
                        return -1;
                    else if (o1.value < o2.value)
                        return 1;
                    else
                        return 0;
                }
            }); 

        int n = arrays.length;
        int i;

        //N * Len * log(Len), Len is average array length
        //hehe, a little long to sort
        for (i = 0; i < n; ++i) {
        		//Len * log(Len)
            Arrays.sort(arrays[i]);
            
            if (arrays[i].length > 0) {
                int from_id = i;
                int index = arrays[i].length - 1;
                int value = arrays[i][index];
                queue.add(new Node(value, from_id, index));
            }
        }

        //pay attention to the heap use, poll one and add one each turn
        //O(K*logN)  kth so K turns, N elements in heap
        for (i  = 0; i < k; ++i) {
            Node temp = queue.poll();
            int from_id = temp.from_id;
            int index = temp.index;
            int value = temp.value;
            
            if (i == k - 1)
                return value;
            
            if (index > 0) {
                index --;
                value = arrays[from_id][index];
                queue.add(new Node(value, from_id, index));
            }
        }

        return -1;
    }
}




//-------------------------------------------------------------------------//////////





//-------------------------------------------------------------------------//////////





//-------------------------------------------------------------------------//////////





//-------------------------------------------------------------------------//////////





//-------------------------------------------------------------------------//////////