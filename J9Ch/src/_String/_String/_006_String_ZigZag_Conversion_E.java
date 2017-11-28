package _String._String;
import java.util.*;
import org.junit.Test;

//  6. ZigZag Conversion
//  https://leetcode.com/problems/zigzag-conversion/
//
//  4:
//
public class _006_String_ZigZag_Conversion_E {
//------------------------------------------------------------------------------
    //1
    /*
    Easy to understand Java solution
        Create nRows StringBuffers, and keep collecting characters from original string to corresponding StringBuffer. Just take care of your index to keep them in bound.
    */
    class Solution1{
        public String convert(String s, int nRows) {
            char[] c = s.toCharArray();
            int len = c.length;
            StringBuffer[] sb = new StringBuffer[nRows];
            for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();

            int i = 0;
            while (i < len) {
                for (int idx = 0; idx < nRows && i < len; idx++) // vertically down
                    sb[idx].append(c[i++]);
                for (int idx = nRows-2; idx >= 1 && i < len; idx--) // obliquely up
                    sb[idx].append(c[i++]);
            }
            for (int idx = 1; idx < sb.length; idx++)
                sb[0].append(sb[idx]);
            return sb[0].toString();
        }
    }

//------------------------------------------------------------------------------
    //2
    //If you are confused with zigzag pattern,come and see!


        /*n=numRows
    Δ=2n-2    1                           2n-1                         4n-3
    Δ=        2                     2n-2  2n                    4n-4   4n-2
    Δ=        3               2n-3        2n+1              4n-5       .
    Δ=        .           .               .               .            .
    Δ=        .       n+2                 .           3n               .
    Δ=        n-1 n+1                     3n-3    3n-1                 5n-5
    Δ=2n-2    n                           3n-2                         5n-4
    */
    public String convert(String s, int numRows) {
        //actually to find the pattern of indexes
        //special conditions numRows:1
        if (numRows == 1) return s;
        int offset = 2 * numRows - 2;
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < numRows; i ++) {
            //first and last row increase a model
            //zigzag pattern is an upside down N pattern
            for (int j = 0; j*offset + i < s.length(); j ++) {
                result.append(s.charAt(j*offset + i));
                if (i != 0 && i != numRows - 1 && (j+1)*offset - i < s.length()) result.append(s.charAt((j+1)*offset - i));
            }
        }
        return result.toString();
    }
//------------------------------------------------------------------------------
    //3
    //JAVA solution--easy and clear ( interesting approach )
    public class Solution3 {
        public String convert(String s, int numRows) {
            if (numRows <= 1) return s;
            StringBuilder[] sb = new StringBuilder[numRows];
            for (int i = 0; i < sb.length; i++) {
                sb[i] = new StringBuilder("");   //init every sb element **important step!!!!
            }
            int incre = 1;
            int index = 0;
            for (int i = 0; i < s.length(); i++) {
                sb[index].append(s.charAt(i));
                if (index == 0) {
                    incre = 1;
                }
                if (index == numRows - 1) {
                    incre = -1;
                }
                index += incre;
            }
            String re = "";
            for (int i = 0; i < sb.length; i++) {
                re += sb[i];
            }
            return re.toString();
        }
    }

//------------------------------------------------------------------------------



//------------------------------------------------------------------------------
    //4
    //9Ch
    public class Jiuzhang {
        public String convert(String s, int nRows) {
            int length = s.length();
            if (length <= nRows || nRows == 1) return s;
            char[] chars = new char[length];
            int step = 2 * (nRows - 1);
            int count = 0;
            for (int i = 0; i < nRows; i++){
                int interval = step - 2 * i;
                for (int j = i; j < length; j += step){
                    chars[count] = s.charAt(j);
                    count++;
                    if (interval < step && interval > 0
                            && j + interval < length && count <  length){
                        chars[count] = s.charAt(j + interval);
                        count++;
                    }
                }
            }
            return new String(chars);
        }
    }

//------------------------------------------------------------------------------
}
/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
Seen this question in a real interview before?   Yes  No
Related Topics
String
 */