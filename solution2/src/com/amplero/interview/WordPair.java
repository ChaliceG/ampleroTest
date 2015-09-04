package com.amplero.interview;

public class WordPair {

    private final String scrambled;
    private final String unscrambled;

    public WordPair(final String scrambled, final String unscrambled) {
        this.scrambled = scrambled;
        this.unscrambled = unscrambled;
    }

    public String getScrambled() {
        return scrambled;
    }

    public String getUnscrambled() {
        return unscrambled;
    }

    @Override
    public String toString() {
        return "Input [scrambled=" + scrambled + ", unscrambled="
                + unscrambled + "]";
    }
}
