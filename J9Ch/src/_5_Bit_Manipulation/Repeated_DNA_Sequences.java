package _5_Bit_Manipulation;
import java.util.*;
/*
LeetCode – Repeated DNA Sequences (Java)

Problem

All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example, given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
return: ["AAAAACCCCC", "CCCCCAAAAA"].

Java Solution

The key to solve this problem is that each of the 4 nucleotides can be stored in 2 bits. So the 10-letter-long sequence can be converted to 20-bits-long integer. The following is a Java solution. You may use an example to manually execute the program and see how it works.
 */

//all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.


public class Repeated_DNA_Sequences {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<String>();

        int len = s.length();
        if (len < 10) {
            return result;
        }

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);

        Set<Integer> temp = new HashSet<Integer>();
        Set<Integer> added = new HashSet<Integer>();

        int hash = 0;
        for (int i = 0; i < len; i++) {
            if (i < 9) {
                //each ACGT fit 2 bits, so left shift 2
                hash = (hash << 2) + map.get(s.charAt(i));
            } else {
                hash = (hash << 2) + map.get(s.charAt(i));
                //make length of hash to be 20
                hash = hash &  (1 << 20) - 1;

                if (temp.contains(hash) && !added.contains(hash)) {
                    result.add(s.substring(i - 9, i + 1));
                    added.add(hash); //track added
                } else {
                    temp.add(hash);
                }
            }

        }

        return result;
    }


/////////////////////////////////////////////////


    //Clean Java solution (hashmap + bits manipulation)

    public List<String> findRepeatedDnaSequences2(String s) {
        Set<Integer> words = new HashSet<>();
        Set<Integer> doubleWords = new HashSet<>();
        List<String> rv = new ArrayList<>();
        char[] map = new char[26];
        //map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;

        for(int i = 0; i < s.length() - 9; i++) {
            int v = 0;
            for(int j = i; j < i + 10; j++) {
                v <<= 2;
                v |= map[s.charAt(j) - 'A'];
            }
            if(!words.add(v) && doubleWords.add(v)) {
                rv.add(s.substring(i, i + 10));
            }
        }
        return rv;
    }
//////////////////////////////////////////////////////////

    //7 lines simple Java, O(n)
    public List<String> findRepeatedDnaSequences3(String s) {
        Set seen = new HashSet(), repeated = new HashSet();
        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            if (!seen.add(ten))
                repeated.add(ten);
        }
        return new ArrayList(repeated);
    }

}
