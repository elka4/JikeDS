package _String._String;
import java.util.*;
import org.junit.Test;

//
//  165. Compare Version Numbers
//  https://leetcode.com/problems/compare-version-numbers/description/
//  5:
//
public class _165_String_Compare_Version_Numbers_E {
//------------------------------------------------------------------------------
    //1
    /*Accepted small Java solution.
        This code assumes that next level is zero if no mo levels in shorter version number. And than compare levels.*/
    class Solution1{
        public int compareVersion(String version1, String version2) {
            String[] levels1 = version1.split("\\.");
            String[] levels2 = version2.split("\\.");

            int length = Math.max(levels1.length, levels2.length);
            for (int i=0; i<length; i++) {
                Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
                Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
                int compare = v1.compareTo(v2);
                if (compare != 0) {
                    return compare;
                }
            }

            return 0;
        }
    }

//------------------------------------------------------------------------------
    //2
/*    Java solution with fewer if logic
    I checked other Java solution and the basic idea is the same. In addition, I simply the logic by making the two version number same length. For example, if version1 = "1.0.2", and version2 = "1.0", the I will convert the version2 to "1.0.0".*/

    public int compareVersion(String version1, String version2) {

        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        for ( int i = 0; i < Math.max(v1.length, v2.length); i++ ) {
            int num1 = i < v1.length ? Integer.parseInt( v1[i] ) : 0;
            int num2 = i < v2.length ? Integer.parseInt( v2[i] ) : 0;
            if ( num1 < num2 ) {
                return -1;
            } else if ( num1 > num2 ) {
                return +1;
            }
        }

        return 0;
    }

//------------------------------------------------------------------------------
    //3
    //My JAVA solution without split
    public class Solution3 {
        public int compareVersion(String version1, String version2) {
            int temp1 = 0,temp2 = 0;
            int len1 = version1.length(),len2 = version2.length();
            int i = 0,j = 0;
            while(i<len1 || j<len2) {
                temp1 = 0;
                temp2 = 0;
                while(i<len1 && version1.charAt(i) != '.') {
                    temp1 = temp1*10 + version1.charAt(i++)-'0';

                }
                while(j<len2 && version2.charAt(j) != '.') {
                    temp2 = temp2*10 + version2.charAt(j++)-'0';

                }
                if(temp1>temp2) return 1;
                else if(temp1<temp2) return -1;
                else {
                    i++;
                    j++;

                }

            }
            return 0;

        }
    }
//------------------------------------------------------------------------------
    //4
    //Simple JAVA Solution
    public class Solution4 {
        public int compareVersion(String version1, String version2) {
            String[] v1 = version1.split("\\.");
            String[] v2 = version2.split("\\.");

            int longest = v1.length > v2.length? v1.length: v2.length;

            for(int i=0; i<longest; i++)
            {
                int ver1 = i<v1.length? Integer.parseInt(v1[i]): 0;
                int ver2 = i<v2.length? Integer.parseInt(v2[i]): 0;

                if(ver1> ver2) return 1;
                if(ver1 < ver2) return -1;
            }
            return 0;
        }
    }

//------------------------------------------------------------------------------
    //5
    //My solutions in 3 languages
    public class Solution5 {
        public int compareVersion(String version1, String version2) {
            String[] v1 = version1.split("\\.");
            String[] v2 = version2.split("\\.");
            for (int i = 0; i < Math.max(v1.length, v2.length); i++) {
                int gap = (i < v1.length ? Integer.parseInt(v1[i]) : 0) - (i < v2.length ? Integer.parseInt(v2[i]) : 0);
                if (gap != 0) {
                    return gap > 0 ? 1 : -1;
                }
            }
            return 0;
        }
    }

//------------------------------------------------------------------------------
}
/*
Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
Credits:
Special thanks to @ts for adding this problem and creating all test cases.

Seen this question in a real interview before?   Yes  No
Companies
Microsoft Apple
Related Topics
String
 */