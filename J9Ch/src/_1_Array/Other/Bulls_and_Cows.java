package _1_Array.Other;

import java.util.HashMap;

/*
LeetCode – Bulls and Cows (Java)

You are playing the following Bulls and Cows game with your friend:
You write down a number and ask your friend to guess what the number is.
Each time your friend makes a guess, you provide a hint that indicates
how many digits in said guess match your secret number exactly in both
digit and position (called "bulls") and how many digits match the secret
number but locate in the wrong position (called "cows"). Your friend will
use successive guesses and hints to eventually derive the secret number.

For example:
Secret number: "1807"
Friend's guess: "7810"

Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
Write a function to return a hint according to the secret number and
friend's guess, use A to indicate the bulls and B to indicate the cows.
In the above example, your function should return "1A3B".
 */


public class Bulls_and_Cows {

    //Java Solution 1 - Using HashMap
    public String getHint(String secret, String guess) {
        int countBull=0;
        int countCow=0;

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        //check bull
        for(int i=0; i<secret.length(); i++){
            char c1 = secret.charAt(i);
            char c2 = guess.charAt(i);

            if(c1==c2){
                countBull++;
            }else{
                if(map.containsKey(c1)){
                    int freq = map.get(c1);
                    freq++;
                    map.put(c1, freq);
                }else{
                    map.put(c1, 1);
                }
            }
        }

        //check cow
        for(int i=0; i<secret.length(); i++){
            char c1 = secret.charAt(i);
            char c2 = guess.charAt(i);

            if(c1!=c2){
                if(map.containsKey(c2)){
                    countCow++;
                    if(map.get(c2)==1){
                        map.remove(c2);
                    }else{
                        int freq = map.get(c2);
                        freq--;
                        map.put(c2, freq);
                    }
                }
            }
        }

        return countBull+"A"+countCow+"B";
    }




   /* Java Solution 2 - Using an Array

    Since the secret and guess only contain numbers and there are at most
    10 possible digits, we can use two arrays to track the frequency of
    each digits in secret and guess.*/

    public String getHint2(String secret, String guess) {
        int countBull=0;
        int countCow =0;
        int[] arr1 = new int[10];
        int[] arr2 = new int[10];

        for(int i=0; i<secret.length(); i++){
            char c1 = secret.charAt(i);
            char c2 = guess.charAt(i);

            if(c1==c2)
                countBull++;
            else{
                arr1[c1-'0']++;
                arr2[c2-'0']++;
            }
        }

        for(int i=0; i<10; i++){
            countCow += Math.min(arr1[i], arr2[i]);
        }

        return countBull+"A"+countCow+"B";
    }


////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






}
