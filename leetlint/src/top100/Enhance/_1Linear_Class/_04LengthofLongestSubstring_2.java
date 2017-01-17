package top100.Enhance._1Linear_Class;
//Length of Longest substring -- Method 2
public class _04LengthofLongestSubstring_2 {

public int lengthOfLongestSubstring2(String s) {
     if (s == null || s.length() == 0) {
         return 0;
     }
     int start = 0;
     int end = 0;
     //Array Map to remove duplicates
     int[] map = new int[128];
     int max = Integer.MIN_VALUE;
     char[] chS = s.toCharArray();
     while(end  < chS.length){
         //PollFirst --  start++
         while (map[chS[end]]  > 0) {
             map[chS[start++]]--;
         }
         //OfferLast --  end++
         map[chS[end++]]++;
         max = Math.max(max, end - start);
     }

     return max;
 }  
}
