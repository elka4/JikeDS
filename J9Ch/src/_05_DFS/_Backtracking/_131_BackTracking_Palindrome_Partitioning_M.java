package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _131_BackTracking_Palindrome_Partitioning_M {

    public class Solution {
        public List<List<String>> partition(String s) {
            int len = s.length();
            List<List<String>>[] result = new List[len + 1];
            result[0] = new ArrayList<List<String>>();
            result[0].add(new ArrayList<String>());

            boolean[][] pair = new boolean[len][len];
            for (int i = 0; i < s.length(); i++) {
                result[i + 1] = new ArrayList<List<String>>();
                for (int left = 0; left <= i; left++) {
                    if (s.charAt(left) == s.charAt(i) && (i-left <= 1 || pair[left + 1][i - 1])) {
                        pair[left][i] = true;
                        String str = s.substring(left, i + 1);
                        for (List<String> r : result[left]) {
                            List<String> ri = new ArrayList<String>(r);
                            ri.add(str);
                            result[i + 1].add(ri);
                        }
                    }
                }
            }
            return result[len];
        }
    }


    public class Solution2 {
        List<List<String>> resultLst;
        ArrayList<String> currLst;
        public List<List<String>> partition(String s) {
            resultLst = new ArrayList<List<String>>();
            currLst = new ArrayList<String>();
            backTrack(s,0);
            return resultLst;
        }
        public void backTrack(String s, int l){
            if(currLst.size()>0 //the initial str could be palindrome
                    && l>=s.length()){
                List<String> r = (ArrayList<String>) currLst.clone();
                resultLst.add(r);
            }
            for(int i=l;i<s.length();i++){
                if(isPalindrome(s,l,i)){
                    if(l==i)
                        currLst.add(Character.toString(s.charAt(i)));
                    else
                        currLst.add(s.substring(l,i+1));
                    backTrack(s,i+1);
                    currLst.remove(currLst.size()-1);
                }
            }
        }
        public boolean isPalindrome(String str, int l, int r){
            if(l==r) return true;
            while(l<r){
                if(str.charAt(l)!=str.charAt(r)) return false;
                l++;r--;
            }
            return true;
        }
    }
}
