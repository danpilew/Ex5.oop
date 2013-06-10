package clids.ex5.crosswords;

import java.io.IOException;
import java.util.Set;

/**
 * Represents a dictionary that keeps words and their matching definitions.
 * @author CLIDS
 */
public interface CrosswordDictionary {
	
	/**
	 * Given a term, the method returns the matching definition.
	 * 
	 * @param term The given term
	 * @return The definition string
	 */
	String getTermDefinition(String term);

	/**
	 * Retrieves the set of terms in the dictionary.
	 * @return the set of terms in the dictionary.
	 */
	Set<String> getTerms();

	/**
	 * Loads dictionary from a text file. The dictionary format is defined in ex3 pdf.
	 * 
	 * @param glossFileName name of a dictionary file.
	 * @throws IOException on any I/O error.
	 */
	void load(String glossFileName) throws IOException;
}
