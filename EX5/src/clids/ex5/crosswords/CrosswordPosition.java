package clids.ex5.crosswords;

/**
 * Specifies a basic position of an entry in a crossword.
 * @author CLIDS
 */
public interface CrosswordPosition {

	/**
	 * @return The x coordinate.
	 */
	public abstract int getX();

	/**
	 * 
	 * @return The Y coordinate.
	 */
	public abstract int getY();

	/**
	 * @return True iff position is a vertical position.
	 */
	public abstract boolean isVertical();

}
