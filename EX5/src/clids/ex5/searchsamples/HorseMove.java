
package clids.ex5.searchsamples;

import clids.ex5.search.BoardMove;


/**
 * This class which represents a single move for HorseBoard-based puzzle.
 * It keeps 3 fields: x, y and move number.
 *
 * @author CLIDS
 */
class HorseMove implements BoardMove{
	/* fields */
	protected int x;
	protected int y;
	protected int moveNumber;

	HorseMove(int x, int y, int moveNumber) {
		this.x = x;
		this.y = y;
		this.moveNumber = moveNumber;
	}

	public int getNumber() {
		return this.moveNumber;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

}
