package clids.ex5.crosswords;

import clids.ex5.search.BoardMove;

/**
 * A crossword entry is a word at a given position in a crossword.
 * 
 * @author CLIDS
 */
public interface CrosswordEntry extends BoardMove {

	/**
	 * Returns the X/Y/Vertical position of an entry
	 * @return the position
	 */
	public CrosswordPosition getPosition();
	
	/**
	 * Returns the corresponding definition of the entry.
	 * 
	 * @return the corresponding dictionary definition.
	 */
	public String getDefinition();

	/**
	 * Returns the corresponding word of the entry.
	 * 
	 * @return the corresponding term.
	 */
	public String getTerm();
		

	/**
	 * Retrieves length of the entry (redundant convenience, may be calculated
	 * through getTerm()).
	 * 
	 * @return number of letters of the term.
	 */
	public int getLength();
		
}
