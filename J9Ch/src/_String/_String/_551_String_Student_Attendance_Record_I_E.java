package _String._String;
import java.util.*;
import org.junit.Test;

//  551. Student Attendance Record I
//  https://leetcode.com/problems/student-attendance-record-i/description/
//
//  Student Attendance Record II
//  7:
//
public class _551_String_Student_Attendance_Record_I_E {
//------------------------------------------------------------------------------
//https://leetcode.com/problems/student-attendance-record-i/solution/
//------------------------------------------------------------------------------
    //1
    //Approach #1 Simple Solution [Accepted]
    public class Solution1 {
        public boolean checkRecord(String s) {
            int count=0;
            for(int i=0;i<s.length();i++)
                if(s.charAt(i)=='A')
                    count++;
            return count<2 && s.indexOf("LLL")<0;
        }
    }




//------------------------------------------------------------------------------
    //2
    //Approach #2 Better Solution [Accepted]
    public class Solution2 {
        public boolean checkRecord(String s) {
            int count=0;
            for(int i=0;i<s.length() && count<2 ;i++)
                if(s.charAt(i)=='A')
                    count++;
            return count<2 && s.indexOf("LLL")<0;
        }
    }


//------------------------------------------------------------------------------
    //3
    //Approach #3 Single pass Solution (Without indexOf method) [Accepted]
    public class Solution3 {
        public boolean checkRecord(String s) {
            int countA = 0;
            for (int i = 0; i < s.length() && countA < 2; i++) {
                if (s.charAt(i) == 'A')
                    countA++;
                if (i <= s.length() - 3 && s.charAt(i) == 'L' && s.charAt(i + 1) == 'L' && s.charAt(i + 2) == 'L')
                    return false;
            }
            return countA < 2;
        }
    }


//------------------------------------------------------------------------------
    //4
    //Approach #4 Using Regex [Accepted]
    public class Solution4 {
        public boolean checkRecord(String s) {
            return !s.matches(".*(A.*A|LLL).*");
        }
    }
//------------------------------------------------------------------------------
    //5
    //Java 1-liner
    public boolean checkRecord(String s) {
    return !s.matches(".*LLL.*|.*A.*A.*");
}
//------------------------------------------------------------------------------
    //6
/*    DescriptionHintsSubmissionsDiscussSolution
    Java Simple without Regex 3 lines
    Simple by using Java String Functions -*/

    public class Solution5 {
        public boolean checkRecord(String s) {
            if(s.indexOf("A") != s.lastIndexOf("A") || s.contains("LLL"))
                return false;
            return true;
        }
    }

//------------------------------------------------------------------------------
    //7
    //Java O(N) solution - Accepted
    public boolean checkRecord6(String s) {
        int countA=0;
        int continuosL = 0;
        int charA = 'A';
        int charL ='L';
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == charA){
                countA++;
                continuosL = 0;
            }
            else if(s.charAt(i) == charL){
                continuosL++;
            }
            else{
                continuosL = 0;
            }
            if(countA >1 || continuosL > 2 ){
                return false;
            }
        }
        return true;

    }


//------------------------------------------------------------------------------
}
/*
You are given a string representing an attendance record for a student. The record only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True

Example 2:
Input: "PPALLL"
Output: False

Companies
Google

Related Topics
String

Similar Questions
Student Attendance Record II
 */