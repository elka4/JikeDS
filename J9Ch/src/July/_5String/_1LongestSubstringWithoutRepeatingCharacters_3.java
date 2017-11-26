package July._5String;

import java.util.HashSet;

// Longest Substring Without Repeating Characters
public class _1LongestSubstringWithoutRepeatingCharacters_3 {
    /**
     * @param s: a string
     * @return: an integer
     */
    //方法一：
    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[256]; // map from character's ASCII to its last occured index

        int j = 0;
        int i = 0;
        int ans = 0;
        for (i = 0; i < s.length(); i++) {
            while (j < s.length() && map[s.charAt(j)]==0) {
                map[s.charAt(j)] = 1;
                ans = Math.max(ans, j-i + 1);
                j ++;
            }
            map[s.charAt(i)] = 0;
        }

        return ans;
    }


//-------------------------------------------------------------------------------

    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        HashSet<Character> set = new HashSet<Character>();

        int leftBound = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                while (leftBound < i && s.charAt(leftBound) != s.charAt(i)) {
                    set.remove(s.charAt(leftBound));
                    leftBound ++;
                }
                leftBound ++;
            } else {
                set.add(s.charAt(i));
                max = Math.max(max, i - leftBound + 1);
            }
        }

        return max;
    }

//-------------------------------------------------------------------------------

    //Java Solution 1

    //The first solution is like the problem of "determine if a string has all unique characters" in CC 150. We can use a flag array to track the existing characters for the longest substring without repeating characters.

    public int lengthOfLongestSubstring3(String s) {
        if(s==null)
            return 0;
        boolean[] flag = new boolean[256];

        int result = 0;
        int start = 0;
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            char current = arr[i];
            if (flag[current]) {
                result = Math.max(result, i - start);
                // the loop update the new start point
                // and reset flag array
                // for example, abccab, when it comes to 2nd c,
                // it update start from 0 to 3, reset flag for a,b
                for (int k = start; k < i; k++) {
                    if (arr[k] == current) {
                        start = k + 1;
                        break;
                    }
                    flag[arr[k]] = false;
                }
            } else {
                flag[current] = true;
            }
        }

        result = Math.max(arr.length - start, result);

        return result;
    }


    //Java Solution 2 - HashSet
    public int lengthOfLongestSubstring4(String s) {
        if(s==null || s.length()==0)
            return 0;

        HashSet<Character> set = new HashSet<Character>();

        int max=0;

        int i=0;
        int start=0;
        while(i<s.length()){
            char c = s.charAt(i);
            if(!set.contains(c)){
                set.add(c);
            }else{
                max = Math.max(max, set.size());

                while(start<i&&s.charAt(start)!=c){
                    set.remove(s.charAt(start));
                    start++;
                }
                start++;
            }

            i++;
        }

        max = Math.max(max, set.size());

        return max;
    }


    public int lengthOfLongestSubstring5(String s) {
        if(s==null || s.length()==0){
            return 0;
        }

        int start=0;
        int max = 0;

        HashSet<Character> set = new HashSet<Character>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);

            if(!set.contains(c)){
                set.add(c);

                max = Math.max(max, i-start+1);
            }else{
                for(int j=start; j<i; j++){
                    set.remove(s.charAt(j));

                    if(s.charAt(j)==c){
                        start=j+1;
                        break;
                    }
                }

                set.add(c);
            }
        }

        return max;
    }
//-------------------------------------------------------------------------------


//-------------------------------------------------------------------------------
}
