package _01_Array.Other;

/*
LeetCode – Compare Version Numbers (Java)

Problem

Compare two version numbers version1 and version2. If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0. You may assume that the version strings are non-empty and contain only digits and the . character. The . character does not represent a decimal point and is used to separate number sequences. Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37

The tricky part of the problem is to handle cases like 1.0 and 1. They should be equal.

 */

public class Compare_Version_Numbers {

    public int compareVersion(String version1, String version2) {
        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");

        int i=0;
        while(i<arr1.length || i<arr2.length){
            if(i<arr1.length && i<arr2.length){
                if(Integer.parseInt(arr1[i]) < Integer.parseInt(arr2[i])){
                    return -1;
                }else if(Integer.parseInt(arr1[i]) > Integer.parseInt(arr2[i])){
                    return 1;
                }
            } else if(i<arr1.length){
                if(Integer.parseInt(arr1[i]) != 0){
                    return 1;
                }
            } else if(i<arr2.length){
                if(Integer.parseInt(arr2[i]) != 0){
                    return -1;
                }
            }

            i++;
        }

        return 0;
    }



//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






}
