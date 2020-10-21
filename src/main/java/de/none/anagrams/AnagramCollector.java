package de.none.anagrams;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Umapragash Sornalingam
 * 
 *         Anagram Collector
 * 
 *         Reads the given file (e.g. the sample.txt) and handles the search and
 *         the collection of each possible anagram
 */
public class AnagramCollector {
	
	private File sample;
	
	/**
	 * {@link Constructor}
	 * 
	 * @param sample {@link File} txt-file with words 
	 * */
	public AnagramCollector(File sample) {
		this.sample = sample;
	}

	/**
	 * Starts the search and the collection for anagrams
	 * 
	 * @return {@link List}
	 */
	public List<String> collect() {
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
	 * @return {@link List}
	 */
	private List<String> buildResults(Map<String, List<String>> sortedWords) {
		List<String> results = new ArrayList<>();
		// go through every grouped anagrams
		sortedWords.forEach((anagramGroupKey, anagramGroupList) -> {
			// add line to results
			results.add(appendAnagram(anagramGroupList));
		});
		// return the list of grouped anagrams
		return results;
	}

	/**
	 * Collecting every value of each anagram-group as one string
	 * 
	 * @param anagramGroupList {@link List} of {@link String}
	 * 
	 * @return {@link String}
	 * */
	private String appendAnagram(List<String> anagramGroupList) {
		StringBuilder sb = new StringBuilder();
		// go through every anagram in group
		anagramGroupList.forEach(anagram -> {
			// append anagram and spacer
			sb.append(anagram + " ");
		});
		// return the results as a string
		return sb.toString();
	}

	/**
	 * Sorts given String in alphabetical order and returns a new string
	 * 
	 * @param value {@link String} to sort
	 * 
	 * @return {@link String}
	 */
	private String sortChars(String value) {
		// get string as char-array
		char[] chars = value.toCharArray();
		
		// alphabetical sort
		Arrays.sort(chars);
		
		// return sorted char-array as new string
		return new String(chars);
	}

	/**
	 * Reads the given file line by line and returns a list of strings
	 * 
	 * @param sample {@link File} to read
	 * 
	 * @return {@link List} of {@link String}
	 * */
	private List<String> readLines(File sample) {
		List<String> words = new ArrayList<>();
		// read file into a stream
		try(Stream<String> stream = Files.lines(sample.toPath())) {
			// collect all non-empty line to a list
			words.addAll(stream.filter(line -> line != null && !line.isEmpty()).collect(Collectors.toList()));
		} catch(IOException e) {
			// logging would be nicer
			e.printStackTrace();
		}
		return words;
	}
	
}
