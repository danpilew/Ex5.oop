package clids.ex5.crosswords;

import clids.ex5.search.SearchBoard;

import java.util.Collection;

/**
 * An empty Crossword consists of: 
 * - a dictionary (loaded from a given dictionary file)
 * - a shape (loaded from a given shape file)
 * 
 * The partially or fully "filled" Crossword in addition 
 * should be able to return a list of crossword entries (which are entries 
 * that are filled in this crossword object).
 * 
 * You can assume (and don't have to check) that calls to SearchBoard's 
 * interface methods and calls to getCrosswordEntries() will always come
 * after calls to attachdictionary() and attachStructure() methods, and
 * that attachdictionary() and attachStructure() will be called only once
 * per crossword.
 * 
 * @author CLIDS
 */
public interface Crossword extends SearchBoard<CrosswordEntry>  {

	/**
	 * Initializes all structures associated with crossword dictionary 
	 * Assumes valid and non-NULL dictionary object
	 * 
	 * @param dictionary - the dictionary object
	 * 					  (generated according to a text file)
	 */
	public void attachDictionary(CrosswordDictionary dictionary);

	/**
	 * Initializes all data structures associated with crossword structure. 
	 * Assumes valid and non-NULL shape object
	 * 
	 * @param shape - the structure object (generated according to a text file)
	 */
	public void attachStructure(CrosswordStructure structure);

	/**
	 * Retrieves list of filled crossword entries associated with this 
	 * Crossword.The set of filled entries should satisfy both of 
	 * exercise requirements. 
	 * 
	 * There is no requirement on the order of the returned collection
	 * of entries.
	 * 
	 * @return Collection of filled entries.
	 */
	Collection<CrosswordEntry> getCrosswordEntries();	
}
