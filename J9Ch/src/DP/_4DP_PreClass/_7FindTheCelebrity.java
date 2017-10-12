package DP._4DP_PreClass;

/**
 * Created by tzh on 2/23/17.
 */
public class _7FindTheCelebrity {
    boolean knows(int candidate, int i){
        return true;
    }
    public  int findCelebrity(int n){
        if(n <= 0){
            return -1;
        }
        if(n == 1){
            return 0;
        }
        int candidate = 0;
        for(int i = 1; i < n; i++){
//find a possible candidate who knows nobody else in his right hand
            if (knows(candidate, i)){
                candidate = i;
            }
        }
        for(int i = 0; i < n; i++){

            if (i < candidate && knows(candidate, i)) {
//check the candidate: A. knows nobody in his left hand
                return  -1;
               //known by everyone else
            }
            if(!knows(i, candidate)) {
                return -1;
            }
        }
        return candidate;
    }

}
