package de.none.anagrams;

import java.io.File;
import java.util.List;

/**
 * @author Umapragash Sornalingam
 * 
 * Main class for the anagrams collector
 * */
public class AnagramMain {

	/**
	 * Starts the anagrams-collector
	 * 
	 * @param args {@link String} ([0]=path to file)
	 */
	public static void main(String[] args) {
		// reading path to file 
		File sample = new File(args[0]);
		
		// make sure, sample file exist
		if (sample.exists()) {
			// create AnagramCollector
			AnagramCollector anagramCollector = new AnagramCollector(sample);
			
			// perform logic
			List<String> anagrams = anagramCollector.collect();
			
			// print the results
			anagrams.forEach(System.out::println);
		} else {
			System.err.println(String.format("File %s not found", args[0]));
		}
	}

}
