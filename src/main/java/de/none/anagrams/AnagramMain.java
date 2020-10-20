package de.none.anagrams;

import java.io.File;

/**
 * @author Umapragash Sornalingam
 * 
 * Main class for the anagrams collector
 * */
public class AnagramMain {

	/**
	 * Starts the anagrams-collector
	 * 
	 * @param args {@link String} ({0}=path to file)
	 */
	public static void main(String[] args) {
		AnagramCollector.collect(new File(args[0])).forEach(System.out::println);
	}

}
