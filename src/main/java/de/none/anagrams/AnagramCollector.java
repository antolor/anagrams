package de.none.anagrams;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 * @author Umapragash Sornalingam
 * 
 *         Anagram Collector
 * 
 *         Reads the given file (e.g. the sample.txt) and handles the search and
 *         the collection of each possible anagram
 */
public class AnagramCollector {
	
	private AnagramCollector() {
		// default private constructor
	}

	/**
	 * Starts the search and the collection for anagrams
	 * 
	 * @param sample {@link File} txt-file with words
	 * 
	 * @return Results as list of found anagrams
	 */
	public static List<String> collect(File sample) {
		// read the file
		List<String> words = readLines(sample);

		// sort, group and collect all words with same characters
		Map<String, List<String>> sortedWords = words.stream()
				.collect(Collectors.groupingBy(value -> sortChars(value)));

		// remove all non-anangrams
		sortedWords.entrySet().removeIf(entry -> entry.getValue().size() < 2);

		// return of a list of found anagrams
		return buildResults(sortedWords);
	}

	/**
	 * Goes through the given map and collects the values as single lines
	 * 
	 * @param sortedWords Map:<br />
	 *                    key {@link String}<br />
	 *                    value {@link List} of {@link String}
	 * @return reduced {@link List} of {@link String} as result
	 */
	private static List<String> buildResults(Map<String, List<String>> sortedWords) {
		List<String> results = new ArrayList<>();

		// go through every grouped anagrams
		sortedWords.forEach((anagramGroupKey, anagramGroupList) -> {
			// for collecting every value of each anagram-group
			StringBuilder sb = new StringBuilder();
			
			// go through every anagram in group
			anagramGroupList.forEach(anagram -> {
				sb.append(anagram);
				// for the space between words
				sb.append(" ");
			});
			// add line to results
			results.add(sb.toString());
		});
		// return the list of grouped anagrams
		return results;
	}

	/**
	 * Sorts given String in alphabetical order and returns a new string
	 * 
	 * @param value {@link String} to sort
	 * 
	 * @return sorted {@link String}
	 */
	private static String sortChars(String value) {
		char[] chars = value.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}

	/**
	 * Reads the given file line by line and returns a list of strings
	 * 
	 * @param sample {@link File} to read
	 * 
	 * @return {@link List} of {@link String}s
	 * */
	private static List<String> readLines(File sample) {
		List<String> words = new ArrayList<>();
		// Using lineiterator from commons-io
		try (LineIterator lineIter = FileUtils.lineIterator(sample)) {
			while (lineIter.hasNext()) {
				words.add(lineIter.nextLine());
			}
		} catch (IOException e) {
			// logging would be nicer
			e.printStackTrace();
		}
		return words;
	}

}
