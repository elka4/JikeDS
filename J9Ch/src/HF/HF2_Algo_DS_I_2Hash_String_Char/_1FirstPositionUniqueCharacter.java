package HF.HF2_Algo_DS_I_2Hash_String_Char;

/*
• Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
• Examples:
• s = "lintcode"
• return 0.
• s = "lovelintcode",
• return 2.
 */

/*
思路:
• 扫一遍统计每个字符出现的次数(用什么统计?Hash)
• 再扫一遍找出第一个出现次数=1的字符
 */

/*
• Company Tags:Amazon
 考点:
• Hash的应用
 */

/*
能力维度:
3. 基础数据结构/算法
 */

// First Position Unique Character
public class _1FirstPositionUniqueCharacter {
    //jiuzhang
    /**
     * @param s a string
     * @return it's index
     */
    public int firstUniqChar(String s) {
        // Write your code here
        int[] alp = new int[256];
        char[] arr =s.toCharArray();

        for(char c : arr ){
            alp[c]++;
        }

        for(int i = 0; i < arr.length; i++){
            if (alp[arr[i]]==1) return i;
        }

        return -1;
    }

//-------------------------------------------------------------------------

    // version: 高频题班
    /**
     * @param s a string
     * @return it's index
     */
    public int firstUniqChar2(String s) {
        // Write your code here
        int[] cnt = new int[256];

        for (char c : s.toCharArray()) {
            cnt[c]++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (cnt[s.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }


//-------------------------------------------------------------------------

}

/*
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Have you met this question in a real interview? Yes
Example
Given s = "lintcode", return 0.

Given s = "lovelintcode", return 2.
 */