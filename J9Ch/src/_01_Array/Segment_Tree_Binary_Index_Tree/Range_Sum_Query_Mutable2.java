package _01_Array.Segment_Tree_Binary_Index_Tree;

public class Range_Sum_Query_Mutable2 {
    //Java Solution 2 - Binary Index Tree / Fenwick Tree

    //Here is a perfect video to show how binary index tree works.
    // In addition, my notes at the end of the post may also help.

    public class NumArray {

        int[] btree;
        int[] arr;

        public NumArray(int[] nums) {
            btree = new int[nums.length+1];
            arr = nums;

            for(int i=0; i<nums.length; i++){
                add(i+1, nums[i]);
            }
        }

        void add(int i, int val) {
            for(int j=i; j<btree.length; j=j+(j&(-j)) ){
                btree[j] += val;
            }
        }

        void update(int i, int val) {
            add(i+1, val-arr[i]);
            arr[i]=val;
        }

        public int sumRange(int i, int j) {
            return sumHelper(j+1)-sumHelper(i);
        }

        public int sumHelper(int i){
            int sum=0;
            for(int j=i; j>=1; j=j-(j&(-j))){
                sum += btree[j];
            }
            return sum;
        }
    }


//-------------------------------------------------------------------------//////////////////






//-------------------------------------------------------------------------//////////////////






//-------------------------------------------------------------------------//////////////////






//-------------------------------------------------------------------------//////////////////






//-------------------------------------------------------------------------//////////////////






//-------------------------------------------------------------------------//////////////////






}
