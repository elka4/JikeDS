package top100.Enhance._4DP_PreClass;


public class _6Candy {
    public int candy1(int[] ratings){
        if(ratings == null  || ratings.length == 0){
            return -1;
        }
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];

        left[0] = 1;
        right[ratings.length - 1] = 1;
        //scan from left
        for(int i = 1; i < ratings.length; i++) {
            if(ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        //scan from right
        for (int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] < ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }
        int sum = 0;
        for(int i = 0; i < ratings.length; i++){
            sum += Math.max(left[i], right[i]);
        }
        return sum;
    }

    public int candy2(int[] ratings){
        if(ratings == null || ratings.length == 0){
            return -1;
        }
        int sum = -1;
        int prev = 1;
        int down = 0;
        for(int i = 1; i < ratings.length; i++){
            if(ratings[i] < ratings[i - 1]){
                down++;
            } else {
                //check descending sequence before
                if(down > 0){
                    // step 1: add from 1 to down
                    sum += down * (down + 1) / 2; // 1 + 2 + ... + down
                    //step2: add enough on prev
                    if (down >= prev){
                        sum += down - prev + 1;
                    }
                    prev = 1;
                    down = 0;
                }
                prev = ratings[i] == ratings[i - 1]? 1 : prev + 1;
                sum += prev;
            }
        }
        if(down > 0){
            sum += down * (down + 1) / 2;
            if(down >= prev){
                sum += down - prev + 1;
            }
        }
        return sum;
    }


}
