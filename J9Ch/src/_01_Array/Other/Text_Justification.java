package _01_Array.Other;

import java.util.ArrayList;
import java.util.List;
/*
LeetCode – Text Justification (Java)

Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified. You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters. Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right. For the last line of text, it should be left justified and no extra space is inserted between words.

For example, words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
"This is an",
"example of text",
"justification. "
]

Java Solution

There is not a special algorithm required to solve this problem.
To correctly solve this problem, the following situations should be taken care of:
1. if a line has only one word and the word's length is less than max width,
we need to fill the left part with spaces.
2. how to distribute extra spaces for each words when the number of spaces can
not be evenly distributed to each word.
 */


public class Text_Justification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<String>();

        if(words==null || words.length==0){
            return result;
        }


        int count=0;
        int last=0;
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0; i<words.length; i++){
            count = count + words[i].length();

            if(count+i-last>maxWidth){
                int wordsLen = count-words[i].length();
                int spaceLen = maxWidth-wordsLen;
                int eachLen = 1;
                int extraLen = 0;

                if(i-last-1>0){
                    eachLen = spaceLen/(i-last-1);
                    extraLen = spaceLen%(i-last-1);
                }

                StringBuilder sb = new StringBuilder();

                for(int k=last; k<i-1; k++){
                    sb.append(words[k]);

                    int ce = 0;
                    while(ce<eachLen){
                        sb.append(" ");
                        ce++;
                    }

                    if(extraLen>0){
                        sb.append(" ");
                        extraLen--;
                    }
                }

                sb.append(words[i-1]);//last words in the line
                //if only one word in this line, need to fill left with space
                while(sb.length()<maxWidth){
                    sb.append(" ");
                }

                result.add(sb.toString());

                last = i;
                count=words[i].length();
            }
        }

        int lastLen = 0;
        StringBuilder sb = new StringBuilder();

        for(int i=last; i<words.length-1; i++){
            count = count+words[i].length();
            sb.append(words[i]+" ");
        }

        sb.append(words[words.length-1]);
        int d=0;
        while(sb.length()<maxWidth){
            sb.append(" ");
        }
        result.add(sb.toString());

        return result;
    }



//----------------------------------------------------------------------------





//----------------------------------------------------------------------------





//----------------------------------------------------------------------------





//----------------------------------------------------------------------------





//----------------------------------------------------------------------------







}
