package com.amplero.interview;

import static com.amplero.interview.Constants.TUPLE_SPLIT_REGEX;

/**
 * This is the main class for the application used to determine the quality of scrambled words.
 */
public class ApplicationEntry {
  /**
   * Takes the given input Strings, quantifies their "quality", and outputs the results in a human
   * readable form to the console.
   * 
   * @param args a array of strings representing each scramble tuple. A scramble tuple is made up of
   *        two words, the scramble itself followed by a space followed by the original word.
   * 
   * @throws IllegalArgumentException if the input does not follow the requirements of scramble,
   *         space, original word
   */
  public static void main(String[] args) throws IllegalArgumentException {

    for (String tuple : args) {
      if (!ScrambleCheckerUtils.validTuple(tuple)) {
        throw new IllegalArgumentException("Invalid tuple format: " + tuple);
      }

      String[] tupleVals = tuple.split(TUPLE_SPLIT_REGEX);

      String scramble = tupleVals[0].toUpperCase();
      String original = tupleVals[1].toUpperCase();

      if (!ScrambleCheckerUtils.scrambled(scramble, original)) {
        ScrambleCheckerUtils.outputResult(scramble, original, ScrambleCheckerUtils.MATCH_TYPE.NOT);
      } else if (ScrambleCheckerUtils.lettersCorrectlyPlaced(scramble, original)
          && !ScrambleCheckerUtils.looksReal(scramble)) {
        ScrambleCheckerUtils.outputResult(scramble, original, ScrambleCheckerUtils.MATCH_TYPE.POOR);
      } else if (!ScrambleCheckerUtils.lettersCorrectlyPlaced(scramble, original)
          && ScrambleCheckerUtils.looksReal(scramble)) {
        ScrambleCheckerUtils.outputResult(scramble, original, ScrambleCheckerUtils.MATCH_TYPE.HARD);
      } else {
        // Catch all type
        ScrambleCheckerUtils.outputResult(scramble, original, ScrambleCheckerUtils.MATCH_TYPE.FAIR);
      }
    }
  }

}
