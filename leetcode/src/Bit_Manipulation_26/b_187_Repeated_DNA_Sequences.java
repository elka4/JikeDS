package Bit_Manipulation_26;
import java.util.*;

public class b_187_Repeated_DNA_Sequences {
//Clean Java solution (hashmap + bits manipulation)

    public List<String> findRepeatedDnaSequences(String s) {
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

/////////////////////////////////////////////////////////////

//    7 lines simple Java, O(n)
public List<String> findRepeatedDnaSequences2(String s) {
    Set seen = new HashSet(), repeated = new HashSet();
    for (int i = 0; i + 9 < s.length(); i++) {
        String ten = s.substring(i, i + 10);
        if (!seen.add(ten))
            repeated.add(ten);
    }
    return new ArrayList(repeated);
}


/////////////////////////////////////////////////////////////

//    Short Java "rolling-hash" solution
//    Hi guys!
//
//    The idea is to use rolling hash technique or in case of string search also known as Rabin-Karp algorithm. As our alphabet A consists of only 4 letters we can be not afraid of collisions. The hash for a current window slice could be found in a constant time by subtracting the former first character times size of the A in the power of 9 and updating remaining hash by the standard rule: hash = hash*A.size() + curr_char.
//
//    Check out the Java code below.
//
//    Hope it helps!

    public class Solution {
        private final Map<Character, Integer> A = new HashMap<>();
         { A.put('A',0); A.put('C',1); A.put('G',2); A.put('T',3); }
        private final int A_SIZE_POW_9 = (int) Math.pow(A.size(), 9);

        public List<String> findRepeatedDnaSequences(String s) {
            Set<String> res = new HashSet<>();
            Set<Integer> hashes = new HashSet<>();
            for (int i = 0, rhash = 0; i < s.length(); i++) {
                if (i > 9) rhash -= A_SIZE_POW_9 * A.get(s.charAt(i-10));
                rhash = A.size() * rhash + A.get(s.charAt(i));
                if (i > 8 && !hashes.add(rhash)) res.add(s.substring(i-9,i+1));
            }
            return new ArrayList<>(res);
        }
    }

/////////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////////

}

/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
 */