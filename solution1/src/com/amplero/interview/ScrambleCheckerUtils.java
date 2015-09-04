package com.amplero.interview;

import static com.amplero.interview.Constants.SPECIAL_CASES;
import static com.amplero.interview.Constants.TUPLE_FORMAT_REGEX;
import static com.amplero.interview.Constants.VOWELS;

/**
 * Utility class to house helper methods for the Word Scrambler application.
 */
public final class ScrambleCheckerUtils {
  public static enum MATCH_TYPE {
    NOT, POOR, FAIR, HARD
  }

  /**
   * Determines if the String provided represents a validly formatted scramble tuple- namely two
   * equal length words separated by a space.
   * 
   * @return true if the String provided is a validly formatted tuple, false otherwise.
   */
  public static boolean validTuple(final String tuple) {
    return tuple.matches(TUPLE_FORMAT_REGEX) && tuple.charAt(tuple.length() / 2) == ' '
        && tuple.length() % 2 != 0;
  }

  /**
   * Determines if a scramble tuple has been scrambled at all.
   * 
   * @param scramble the scrambled part of the tuple
   * @param original the unscrambled/original part of the tuple
   * 
   * @return true if the tuple is scrambled, false otherwise
   */
  public static boolean scrambled(final String scramble, final String original) {
    checkForNullOrEmpty(scramble, original);

    return !scramble.equalsIgnoreCase(original);
  }

  /**
   * Determines if a scrambled word and it's original word have letters that match. In the
   * requirements this is defined as either the first letter or any two consecutive letters being in
   * the correct place.
   * 
   * @param scrabmle the scrambled part of the tuple
   * @param original the unscrambled/original part of the tuple
   * 
   * @return true if the tuple has the first or any two consecutive letters in the correct place
   */
  public static boolean lettersCorrectlyPlaced(final String scramble, final String original) {
    checkForNullOrEmpty(scramble, original);

    char[] scrambleLetters = scramble.toCharArray();
    char[] originalLetters = original.toCharArray();
    boolean previousLettersMatched = false;

    // If the first letters match that's enough to return true
    if (scrambleLetters[0] == originalLetters[0]) {
      return true;
    }

    // Checks for any two consecutive matching letter positions after the first letter
    for (int i = 1; i < scrambleLetters.length; i++) {
      // If the current letter and the previous letter match we can return true
      if (scrambleLetters[i] == originalLetters[i]) {
        if (previousLettersMatched) {
          return true;
        }

        // If there was no previous matching letter we just store that we've encountered a match and
        // move on
        previousLettersMatched = true;
      } else {
        // If the current letters don't match we need to clear the state and move on
        previousLettersMatched = false;
      }
    }

    // Looped all the way through the words with no consecutive correct placements
    return false;
  }

  /**
   * Determines if the given word looks "real". "Real" in this sense means that there are
   * alternating vowels and consonants, or barring that, the faulting substring is either part of a
   * special list of substrings or duplicated consonants such as "PP" or "TT".
   * 
   * @param word the word being tested
   * @return true if the word provided looks "real" as defined above, false otherwise
   */
  public static boolean looksReal(final String word) {
    if (word == null || word.isEmpty()) {
      throw new IllegalArgumentException("Input cannot be null or empty");
    }

    // Single letter words CAN'T break any rules so they MUST look real
    if (word.length() == 1) {
      return true;
    }

    // Convert to upper case just to be safe since our special case test expects that
    final String uppercaseWord = word.toUpperCase();

    int numConsecutiveConsonants = 0;
    int numConsecutiveVowels = 0;

    for (int i = 0; i < uppercaseWord.length(); i++) {
      char currChar = uppercaseWord.charAt(i);

      // Track whether we need a vowel or a consonant next
      if (isVowel(currChar)) {
        numConsecutiveConsonants = 0;
        numConsecutiveVowels++;
      } else {
        numConsecutiveConsonants++;
        numConsecutiveVowels = 0;
      }

      // If we're at the limit for consecutive consonants there better be a special case happening
      // otherwise this word does not look "real"
      if (numConsecutiveConsonants > 1
          && !(specialCase(uppercaseWord.substring(i - numConsecutiveConsonants + 1, i + 1)) || (uppercaseWord
              .charAt(i) == uppercaseWord.charAt(i - 1) && uppercaseWord.charAt(i) != uppercaseWord
              .charAt(i - 2)))) {
        return false;
      }

      // If we're at the limit for consecutive vowels there better be a special case happening
      // otherwise this word does not look "real"
      if (numConsecutiveVowels > 1
          && !specialCase(uppercaseWord.substring(i - numConsecutiveVowels + 1, i + 1))) {
        return false;
      }
    }

    // Looped over the whole word and didn't hit the failure case. Must look real
    return true;
  }

  /**
   * A helper method to format and output the results of the scramble testing.
   * 
   * @param scramble the scrambled word under test
   * 
   * @param original the original word under test
   * 
   * @param type the MATCH_TYPE of the tuple provided
   */
  public static void outputResult(final String scramble, final String original,
      final MATCH_TYPE type) {
    checkForNullOrEmpty(scramble, original);

    switch (type) {
      case NOT:
        System.out.println(scramble + " is not a scramble of " + original);
        break;
      case POOR:
        System.out.println(scramble + " is a poor scramble of " + original);
        break;
      case FAIR:
        System.out.println(scramble + " is a fair scramble of " + original);
        break;
      case HARD:
        System.out.println(scramble + " is a hard scramble of " + original);
        break;
      default:
        throw new IllegalArgumentException("Unknown type given to output formatter.");
    }
  }

  /**
   * Checks whether the given character is a vowel.
   * 
   * @param c the input to test
   * @return true if the input is a vowel, false otherwise
   */
  public static boolean isVowel(char c) {
    Character upperChar = Character.toUpperCase(c);

    if (VOWELS.indexOf(upperChar) == -1) {
      return false;
    }

    return true;
  }

  /**
   * Checks whether the given word has exemptions from the alternating consonant vowel consonant
   * rule-of-reality.
   * 
   * @param c the input to test
   * @return true if the input is a vowel, false otherwise
   */
  public static boolean specialCase(String word) {
    for (String entry : SPECIAL_CASES) {
      if (entry.equals(word)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Helper methods to check the validity of input String arguments.
   * 
   * @param a input #1
   * @param b input #2
   */
  public static void checkForNullOrEmpty(final String a, final String b) {
    if (a == null || a.isEmpty()) {
      throw new IllegalArgumentException("Scrambled word cannot be null or empty");
    }

    if (b == null || b.isEmpty()) {
      throw new IllegalArgumentException("Original word cannot be null or empty");
    }
  }
}
