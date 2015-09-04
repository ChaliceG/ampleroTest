package com.amplero.interview;

public class NoScramble extends AbstractWordScramble {

    /**
     * @return true if the word is not scrambled at all
     */
    @Override
    public boolean score(final String scrambledWord, final String unscrambledWord) {
       return scrambledWord.equals(unscrambledWord);
    }
}



