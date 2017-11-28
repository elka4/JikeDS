package _String._String;
import java.util.*;
import org.junit.Test;

//  68. Text Justification
//  https://leetcode.com/problems/text-justification/
//
//
//
public class _068_String_Text_Justification_H {
//------------------------------------------------------------------------------
    //Simple Java Solution
    public class Solution1 {
        public List<String> fullJustify(String[] words, int L) {
            List<String> lines = new ArrayList<String>();

            int index = 0;
            while (index < words.length) {
                int count = words[index].length();
                int last = index + 1;
                while (last < words.length) {
                    if (words[last].length() + count + 1 > L) break;
                    count += words[last].length() + 1;
                    last++;
                }

                StringBuilder builder = new StringBuilder();
                int diff = last - index - 1;
                // if last line or number of words in the line is 1, left-justified
                if (last == words.length || diff == 0) {
                    for (int i = index; i < last; i++) {
                        builder.append(words[i] + " ");
                    }
                    builder.deleteCharAt(builder.length() - 1);
                    for (int i = builder.length(); i < L; i++) {
                        builder.append(" ");
                    }
                } else {
                    // middle justified
                    int spaces = (L - count) / diff;
                    int r = (L - count) % diff;
                    for (int i = index; i < last; i++) {
                        builder.append(words[i]);
                        if (i < last - 1) {
                            for (int j = 0; j <= (spaces + ((i - index) < r ? 1 : 0)); j++) {
                                builder.append(" ");
                            }
                        }
                    }
                }
                lines.add(builder.toString());
                index = last;
            }


            return lines;
        }
    }
//------------------------------------------------------------------------------
    //Easy Java implementation
    public class Solution2 {
        private List<String> result;

        public List<String> fullJustify(String[] words, int maxWidth) {
            result = new ArrayList<String>();
            if (words == null || words.length == 0 || maxWidth < 0) return result;
            if (maxWidth == 0) {
                result.add("");
                return result;
            }
            helper(words, 0, maxWidth);
            return result;
        }

        public void helper(String[] words, int start, int L) {
            if (start >= words.length) return;

            int i = start, len = 0, total = 0, next = -1;
            while (total < L && i < words.length) {
                total += words[i].length();
                if (total > L) { // only in this case we need skip i++
                    next = i;
                    break;
                }
                len += words[i].length();
                total++; // count space
                i++;
            }

            if (next == -1) next = i;
            addList(words, start, next, len, L);

            helper(words, next, L);
        }

        public void addList(String[] words, int i, int j, int len, int L) {
            StringBuilder sb = new StringBuilder("");
            int count = j-i-1, space = 0, more = 0, s = 0;
            if (count == 0 || j == words.length) { // the last line
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k == j-1) break;
                    sb.append(" ");
                }
                space = L - sb.length();
                s = 0;
                while (s++ < space) sb.append(" ");
            } else {
                space = (L - len) / count; more = (L - len) % count;
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    s = 0;
                    if (k == j-1) break;
                    while (s++ < space) sb.append(" ");
                    if (more-- > 0) sb.append(" ");
                }
            }

            result.add(sb.toString());
        }
    }
//------------------------------------------------------------------------------
//9Ch
public class Jiuzhang {
    public ArrayList<String> fullJustify(String[] words, int L) {
        int wordsCount = words.length;
        ArrayList<String> result = new ArrayList<String>();
        int curLen = 0;
        int lastI = 0;
        for (int i = 0; i <= wordsCount; i++) {
            if (i == wordsCount || curLen + words[i].length() + i - lastI > L) {
                StringBuffer buf = new StringBuffer();
                int spaceCount = L - curLen;
                int spaceSlots = i - lastI - 1;
                if (spaceSlots == 0 || i == wordsCount) {
                    for(int j = lastI; j < i; j++){
                        buf.append(words[j]);
                        if(j != i - 1)
                            appendSpace(buf, 1);
                    }
                    appendSpace(buf, L - buf.length());
                } else {
                    int spaceEach = spaceCount / spaceSlots;
                    int spaceExtra = spaceCount % spaceSlots;
                    for (int j = lastI; j < i; j++) {
                        buf.append(words[j]);
                        if (j != i - 1)
                            appendSpace(buf, spaceEach + (j - lastI < spaceExtra ? 1 : 0));
                    }
                }
                result.add(buf.toString());
                lastI = i;
                curLen = 0;
            }
            if (i < wordsCount)
                curLen += words[i].length();
        }
        return result;
    }

    private void appendSpace(StringBuffer sb, int count) {
        for (int i = 0; i < count; i++)
            sb.append(' ');
    }
}

//------------------------------------------------------------------------------
}
/*
Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

click to show corner cases.

Seen this question in a real interview before?   Yes  No
Companies
Facebook LinkedIn Airbnb
Related Topics
String
 */