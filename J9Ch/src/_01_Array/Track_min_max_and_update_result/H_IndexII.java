package _01_Array.Track_min_max_and_update_result;
/*
Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?

 */


public class H_IndexII {

    public int hIndex(int[] citations) {
        if(citations==null || citations.length==0)
            return 0;

        int result = 0;

        for(int i=0; i<citations.length; i++){
            int smaller = Math.min(citations[i], citations.length-i);
            result = Math.max(result, smaller);
        }

        return result;
    }



//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






}
