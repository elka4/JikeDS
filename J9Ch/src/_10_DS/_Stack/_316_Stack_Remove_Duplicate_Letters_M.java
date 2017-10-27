package _10_DS._Stack;
import java.util.*;
import org.junit.Test;
public class _316_Stack_Remove_Duplicate_Letters_M {

/*    Easy to understand iterative Java solution
    The basic idea is to find out the smallest result letter by letter (one letter at a time). Here is the thinking process for input "cbacdcbc":

    find out the last appeared position for each letter;
    c - 7
    b - 6
    a - 2
    d - 4
    find out the smallest index from the map in step 1 (a - 2);
    the first letter in the final result must be the smallest letter from index 0 to index 2;
    repeat step 2 to 3 to find out remaining letters.
    the smallest letter from index 0 to index 2: a
    the smallest letter from index 3 to index 4: c
    the smallest letter from index 4 to index 4: d
    the smallest letter from index 5 to index 6: b
    so the result is "acdb"

    Notes:

    after one letter is determined in step 3, it need to be removed from the "last appeared position map", and the same letter should be ignored in the following steps
    in step 3, the beginning index of the search range should be the index of previous determined letter plus one*/
    public class Solution {

        public String removeDuplicateLetters(String s) {
            if (s == null || s.length() <= 1) return s;

            Map<Character, Integer> lastPosMap = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                lastPosMap.put(s.charAt(i), i);
            }

            char[] result = new char[lastPosMap.size()];
            int begin = 0, end = findMinLastPos(lastPosMap);

            for (int i = 0; i < result.length; i++) {
                char minChar = 'z' + 1;
                for (int k = begin; k <= end; k++) {
                    if (lastPosMap.containsKey(s.charAt(k)) && s.charAt(k) < minChar) {
                        minChar = s.charAt(k);
                        begin = k+1;
                    }
                }

                result[i] = minChar;
                if (i == result.length-1) break;

                lastPosMap.remove(minChar);
                if (s.charAt(end) == minChar) end = findMinLastPos(lastPosMap);
            }

            return new String(result);
        }

        private int findMinLastPos(Map<Character, Integer> lastPosMap) {
            if (lastPosMap == null || lastPosMap.isEmpty()) return -1;
            int minLastPos = Integer.MAX_VALUE;
            for (int lastPos : lastPosMap.values()) {
                minLastPos = Math.min(minLastPos, lastPos);
            }
            return minLastPos;
        }

    }





//    Java solution using Stack with comments
    public String removeDuplicateLetters(String sr) {

        int[] res = new int[26]; //will contain number of occurences of character (i+'a')
        boolean[] visited = new boolean[26]; //will contain if character (i+'a') is present in current result Stack
        char[] ch = sr.toCharArray();
        for(char c: ch){  //count number of occurences of character
            res[c-'a']++;
        }
        Stack<Character> st = new Stack<>(); // answer stack
        int index;
        for(char s:ch){
            index= s-'a';
            res[index]--;   //decrement number of characters remaining in the string to be analysed
            if(visited[index]) //if character is already present in stack, dont bother
                continue;
            //if current character is smaller than last character in stack which occurs later in the string again
            //it can be removed and  added later e.g stack = bc remaining string abc then a can pop b and then c
            while(!st.isEmpty() && s<st.peek() && res[st.peek()-'a']!=0){
                visited[st.pop()-'a']=false;
            }
            st.push(s); //add current character and mark it as visited
            visited[index]=true;
        }

        StringBuilder sb = new StringBuilder();
        //pop character from stack and build answer string from back
        while(!st.isEmpty()){
            sb.insert(0,st.pop());
        }
        return sb.toString();
    }



/*15 ms Java solution
for "cbacdcbc", we counts each letter's index:

    a----2
    b----1,6
    c----0,3,5,7
    d----4
    we go from a to d, to find the first letter who has a index smaller than the largest index of the rest. Here, index 2 of letter a is smaller than 6, 7, 4, so we first pick a; then we remove all index smaller than 2, and we have:

    b----6
    c----3,5,7
    d----4
    the next round we pick c not b, why ? cuz 6 of b is larger than 4, but 3 of c is smaller than 4 and 6.

    b---6
    d---4
    then we pick d and b to form "acdb"

    O(n) time to count index, and as we only have 26 letters, it's about O(26 * 26) to find a candidate letter and O(n) time to remove all index. So I think the running time is O(n).*/

    public class Solution3 {
        public String removeDuplicateLetters(String s) {
            HashMap<Character, ArrayList<Integer>> counts = new HashMap<Character, ArrayList<Integer>>();
            ArrayList<Character> keys = new ArrayList<Character>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!counts.containsKey(c)) {
                    counts.put(c, new ArrayList<Integer>());
                    keys.add(c);
                }
                counts.get(c).add(i);
            }
            Collections.sort(keys);
            StringBuilder sb = new StringBuilder();
            while (!counts.isEmpty()) {
                boolean found = true;
                for (int i = 0; i < keys.size(); i++) {
                    int index = counts.get(keys.get(i)).get(0);
                    for (int j = 0; j < keys.size(); j++) {
                        ArrayList<Integer> count = counts.get(keys.get(j));
                        if (count.get(count.size() - 1) < index) {
                            found = false;
                            break;
                        }
                    }
                    if (found) {
                        sb.append(keys.get(i));
                        counts.remove(keys.get(i));
                        keys.remove(i);
                        for (int j = 0; j < keys.size(); j++) {
                            ArrayList<Integer> count = counts.get(keys.get(j));
                            while (count.get(0) < index) {
                                count.remove(0);
                            }
                        }
                        break;
                    }
                    found = true;
                }
            }
            return sb.toString();
        }
    }
}
/*
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"


 */