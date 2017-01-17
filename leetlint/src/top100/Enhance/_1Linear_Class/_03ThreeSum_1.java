package top100.Enhance._1Linear_Class;
import java.util.*;
//Three Sum -- Method 1
public class _03ThreeSum_1 {

public List <List <Integer>>   threeSum(int[] array) {   
   List <List <Integer>>   res = new ArrayList <List <Integer>>  ();
   if(array == null || array.length ==  3)
     return res;
   //Method: three pointers, one fixed, one left to right from fixed, 
   //one right to left from rightmos
   Arrays.sort(array);
   int fixed = 0;
   while(fixed <  array.length - 2) {
     int left = fixed + 1;
     int right = array.length - 1;
     int rest = 0 - array[fixed];
     while(left <  right) {
       //A result found
       if(array[left] + array[right] == rest) {
         List <Integer>  list = new ArrayList <Integer> ();
         list.add(array[fixed]);
         list.add(array[left++]);
         list.add(array[right--]);
         res.add(list);
         //Skip Duplicates for left
         while(left  < right && array[left] == array[left - 1]) {
           left++;
         }

         //Skip Duplicates for right
         while(left <  right && array[right] == array[right + 1]) {
           right--;
         }         
       } else if (array[left] + array[right] <  rest) {
         left++;
       } else {
         right--;
       }
     }
     fixed++;
     //Skip Duplicates for fixed
     while(fixed <  array.length - 2 && array[fixed] == array[fixed - 1]) {
       fixed++;
     }
   }
   return res;
 }
}
