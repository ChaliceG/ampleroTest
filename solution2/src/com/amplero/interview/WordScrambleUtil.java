package com.amplero.interview;

import java.util.HashMap;
import java.util.Map;

public final class WordScrambleUtil {

    /* Checks whether the two words are anagrams */
    static boolean anagram(final String scrambled, final String unscrambled) {
        if (scrambled.length() != unscrambled.length())
            return false;

        Map<Character, Integer> charMap = new HashMap<Character, Integer>();
        for (char c: scrambled.toCharArray()) {
            if (charMap.get(c) != null)
                charMap.put(c, charMap.get(c) + 1);
            else
                charMap.put(c,1);
        }

        for (char c: unscrambled.toCharArray()) {
            if (charMap.get(c) == null)
                return false;
            charMap.put(c, charMap.get(c) - 1);
        }

        for (Character c: charMap.keySet())
            if (charMap.get(c) != 0)
                return false;
        return true;
    }

    /* Checks whether all letters in the word are upper-case*/
    static boolean validUppercaseLetters(final String word) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) < 'A' || word.charAt(i) > 'Z')
                return false;
        }
        return true;
    }

    /* Generates word pairs from the input. For example, 'as, sd, df' will be generated from 'asdf' */
    static String[] generateCharacterPairs(final String word) {
        String [] pairs = new String[word.length() - 1];
        for (int i = 0; i < word.length() - 1; i++) {
            String pair = word.substring(i, i+2);
            pairs[i] = pair;
        }
        return pairs;
    }

    /* Generates word triples from the input. For example, 'asd, sdf' will be generated from 'asdf' */
    static String[] generateCharacterTriples(final String word) {
        if (word.length() < 3)
            return new String[]{word};
        String[] triples = new String[word.length() - 2];
        for (int i = 0; i < word.length() - 2; i++) {
            String pair = word.substring(i, i+3);
            triples[i] = pair;
        }
        return triples;
    }
}
