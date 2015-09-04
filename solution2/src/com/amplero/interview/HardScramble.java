package com.amplero.interview;


public class HardScramble extends AbstractWordScramble {

    @Override
    public boolean score(final String scrambledWord, final String  unscrambledWord) {
    	boolean sameFirstLetter = scrambledWord.charAt(0) == unscrambledWord.charAt(0);
        boolean looksReal = looksReal(scrambledWord);

        boolean consecutiveLettersInCorrectPlace = true;
        String [] scrambledPairs = WordScrambleUtil.generateCharacterPairs(scrambledWord);
        String [] unscrambledPairs = WordScrambleUtil.generateCharacterPairs(unscrambledWord);

        for (int i = 0; i < unscrambledPairs.length; i++) {
            if (unscrambledPairs[i].equals(scrambledPairs[i]) == false) {
               consecutiveLettersInCorrectPlace = false;
               break;
            }
        }

        if (!sameFirstLetter && !consecutiveLettersInCorrectPlace && looksReal)
            return true;
        return false;
    }
}
