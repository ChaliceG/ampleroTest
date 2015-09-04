package com.amplero.interview;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class WordScrambleApp {

	private final WordScramble hardScramble = new HardScramble();
	private final WordScramble poorScramble = new PoorScramble();
	private final WordScramble noScramble = new NoScramble();

	public void evaluate(final List<WordPair> words) {
		for (WordPair wordPair : words) {
			String scrambled = wordPair.getScrambled();
			String unscrambled = wordPair.getUnscrambled();

			// validations
			if (WordScrambleUtil.anagram(scrambled, unscrambled) == false) {
				System.err.println("Skipping invalid input line in file. Words not anagrams: " + wordPair);
				continue;
			}

			if (WordScrambleUtil.validUppercaseLetters(scrambled) == false
					|| WordScrambleUtil.validUppercaseLetters(unscrambled) == false) {
				System.err.println("Skipping invalid input line in file. Invalid characters in words: " + wordPair);
				continue;
			}

			// scoring
			if (noScramble.score(scrambled, unscrambled))
				System.out.println(scrambled + " is a not a scramble of " + unscrambled);
			else if (poorScramble.score(scrambled, unscrambled))
				System.out.println(scrambled + " is a poor scramble of " + unscrambled);
			else if (hardScramble.score(scrambled, unscrambled))
				System.out.println(scrambled + " is a hard scramble of " + unscrambled);
			else
				System.out.println(scrambled + " is a fair scramble of " + unscrambled);
		}
	}

	public static void main(final String[] args) throws IOException {

		if (args.length != 1) {
			System.err.println("Error launching Word Scramble App. Synxtax: WordScrambleApp {input_file}");
			System.exit(1);
		}
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			List<WordPair> list = new ArrayList<WordPair>();

			int count = 1;
			while ((line = br.readLine()) != null) {
				try {
					String[] input = line.split("\\s+");
					if (input.length != 2) {
						System.err.println("Skipping invalid line in file. Line " + count + ": " + line);
						continue;
					}
					list.add(new WordPair(input[0].toUpperCase(), input[1].toUpperCase()));
					count++;
				} catch (Exception e) {
					System.err.println("Error reading line in input file. Error: " + e.getMessage());
					continue;
				}
			}

			WordScrambleApp scrambleApp = new WordScrambleApp();
			scrambleApp.evaluate(list);
		}
	}
}
