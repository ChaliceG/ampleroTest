package com.amplero.interview;

import java.util.Stack;

public abstract class AbstractWordScramble implements WordScramble {

    @Override
    public abstract boolean score(String scrambledWord, String unscrambledWord);

    /**
     * Evaluate whether the scrambled word is real.
     * A scramble looks like a real word if the letters alternate between vowels (@see WordScramble.VowelCombination)
     * and consonants (@see WordScramble.ConsonantCombination)
     *
     * @param scrambledWord
     * @return true if scrambled word looks real; false otherwise
     */
    public final boolean looksReal(final String scrambledWord) {
        //if no combinations, then letters should alternate between vowels and consonants
        if (hasCombination(scrambledWord) == false) {
            for (int i = 1; i < scrambledWord.length(); i++) {
                if ((WordScramble.VowelCombination.get(String.valueOf(scrambledWord.charAt(i))) != null
                        && WordScramble.VowelCombination.get(String.valueOf(scrambledWord.charAt(i - 1))) != null)||
                        WordScramble.VowelCombination.get(String.valueOf(scrambledWord.charAt(i))) == null
                        && WordScramble.VowelCombination.get(String.valueOf(scrambledWord.charAt(i - 1))) == null)
                    return false;
            }
        } else {
            //has combinations
            Stack<String> stack = new Stack<String>();

            for (int i = 0; i < scrambledWord.length(); ) {
                String combo = String.valueOf(scrambledWord.charAt(i));
                for (int j = i; j < scrambledWord.length(); ) {
                    int x = j + 1;
                    String tmp = scrambledWord.substring(j, x);
                    while ((isVowelCombination(tmp) || isConsonantCombination(tmp)) && x++ < scrambledWord.length()) {
                        if (tmp.length()  > combo.length())
                            combo = tmp;
                        tmp = scrambledWord.substring(j, x);
                    }
                    i = x - 1;
                    break;
                }

                if (stack.isEmpty())
                    stack.push(combo);
                else {
                    if ((isVowelCombination(stack.peek()) && isVowelCombination(combo))
                            || (isConsonantCombination(stack.peek()) && isConsonantCombination(combo)))
                        return false;
                    else {
                        stack.pop();
                        stack.push(combo);
                    }
                }
            }
        }

        return true;
    }

    private static boolean isVowelCombination(final String val) {
        return WordScramble.VowelCombination.get(val) != null;
    }
    private static boolean isConsonantCombination(final String val) {
        return WordScramble.ConsonantCombination.get(val) != null;
    }

    private static boolean hasCombination(final String scrambledWord) {

        String [] scrambledPairs = WordScrambleUtil.generateCharacterPairs(scrambledWord);
        for (String str: scrambledPairs) {
            if (WordScramble.ConsonantCombination.get(str) != null || WordScramble.VowelCombination.get(str) != null)
                return true;
        }

        String [] scrambledTriples = WordScrambleUtil.generateCharacterTriples(scrambledWord);
        for (String str: scrambledTriples) {
            if (WordScramble.ConsonantCombination.get(str) != null || WordScramble.VowelCombination.get(str) != null)
                return true;
        }

        return false;
    }
}
