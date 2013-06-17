
package clids.ex5.crosswords;

import clids.ex5.search.BoardMove;
import clids.ex5.search.SearchBoard;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;


public class MyCrossword implements Crossword {

    private TreeSet<String> unusedWords;
    private Square[][] board;
    private int quality;
    private HashMap<String, CrosswordEntry> usedWords;
    private CrosswordDictionary dictionary;

    /**
     * Basic contracture, Creates a new CrossWord with no structure nor dictionary.
     */
    public MyCrossword() {
        quality = 0;
        usedWords = new HashMap<>();
    }

    /**
     * Copy constructor, copies the current board, quality, unusedWords, usedWords and Dictionary.
     * @param m - Crossword to copy from.
     */
    public MyCrossword(MyCrossword m) {
        this.board = m.board.clone();
        this.quality = m.getQuality();
        this.unusedWords = (TreeSet<String>) m.unusedWords.clone();
        this.usedWords = (HashMap<String, CrosswordEntry>) m.usedWords.clone();
        this.dictionary = m.dictionary;

    }

    @Override
    public void attachDictionary(CrosswordDictionary dictionary) { 
        unusedWords = new TreeSet<>(new wordsComperator());
        //Adds all the new words into the 'unusedWords' collection.
        unusedWords.addAll(dictionary.getTerms());
        this.dictionary = dictionary;
    }

    /**
     * Convert the structure into a double array of Squares.
     * @param structure - 
     *                     the structured needed to be attached.
     */
    @Override
    public void attachStructure(CrosswordStructure structure) {
        board = new Square[structure.getHeight()][structure.getWidth()];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Square(structure.getSlotType(j, i));
            }
        }
    }

    @Override
    public Collection<CrosswordEntry> getCrosswordEntries() {
        return usedWords.values();
    }

    /**
     * checks if the current board represents the best solution possible for the problem.
     * @return true iff all the word had been added to the board.
     */
    @Override
    public boolean isBestSolution() {
        return unusedWords.isEmpty();
    }

    /**
     * 
     * @return an iterator that return the possible moves in the asked order.
     */
    @Override
    public Iterator<CrosswordEntry> getMovesIterator() {
        /* Uses the help-class initializeStartPos that recieves a board and returns all the
         * posible StartPositions in it. Every startPosition represents the larges availabe, 
         * so the tree doesn't contain the sub- positions. For Example for the Board:
         *                    "_______"  (1X7)
         * initializeStartPos would return 1 horizontal 7 length start point and 7 vertical
         * 1 legth start points. But won't return 6 length horizontal. this way we save space.
          */
        TreeSet<StartPosition> position = initializeStartPos.initialStartPos(board);
        //Returns the iterator.
        return new MovesIterator(unusedWords, position);
    }

    @Override
    public int getQuality() {
        return quality;
    }

    /**
     * 
     * @return the quality bound as described in the excersize.
     */
    @Override
    public int getQualityBound() {

        // Creates the positions tree as described in the getIterator method.
        TreeSet<StartPosition> position = initializeStartPos.initialStartPos(board);
        // Creates a String tree that stands for one unused word illusion for the Iterator.
        TreeSet<String> word = new TreeSet<>();
        int qualityBound = quality;
        MovesIterator m;

        // Iterates over all the unused words. For every word, creates a moves Iterator that
        // should search moves in the given board but only with the current word. If it succeded
        // to find at least one possible move it means that it is possible to insert it into
        // the board, so we add it to the quality bound.
        for (String s : unusedWords) {
            word.add(s);
            m = new MovesIterator(word, position);
            // If m.hasNext() == true => there is at least one possible move with s.
            if (m.hasNext()) {
                qualityBound = qualityBound + s.length();
            }
            // Cleans the tree.
            word.remove(s);
        }
        return qualityBound;

     }

    @Override
    public void doMove(CrosswordEntry move) {
        //Trusts the iterator and not checking the correctness of the move.
        
        // For each letter in the added word,  changes the letter in the board
        // and adds one to the overRides of the square.
        for (int n = 0; n < move.getTerm().length(); n++) {
            int x = move.getPosition().getX();
            int y = move.getPosition().getY();
            if (move.getPosition().isVertical()) {
                y = y + n;
            } else {
                x = x + n;
            }
            board[y][x].setLetter(move.getTerm().charAt(n));
            board[y][x].setOverRides(board[y][x].getOverRides() + 1);

        }
        quality = quality + move.getTerm().length();
        unusedWords.remove(move.getTerm());
        usedWords.put(move.getTerm(), move);
        
    }

    @Override
    public void undoMove(CrosswordEntry move) {
        //Trusts the iterator and not checking the correctness of the move.
        
        // For each letter in the added word, discard 1 from the overrides
        // of the requested square, and if neccary, change the letter to Default letter.
        for (int n = 0; n < move.getTerm().length(); n++) {
            int x = move.getPosition().getX();
            int y = move.getPosition().getY();
            if (move.getPosition().isVertical()) {
                y = y + n;
            } else {
                x = x + n;
            }
            
            board[y][x].setOverRides(board[y][x].getOverRides() - 1);
            if (board[y][x].getOverRides() == 0) {
                board[y][x].setLetter(Square.DEFAULT_CHAR);
            }
        }
        quality = quality - move.getTerm().length();
        unusedWords.add(move.getTerm());
        usedWords.remove(move.getTerm());
        // System.out.println("Finished");
        //toStringg();
    }

    @Override
    public SearchBoard<CrosswordEntry> getCopy() {
        return new MyCrossword(this);
    }


    /**
     * 
     * Moves Iterator - an inner class that can calculate from a given positions
     * tree  a set of unused words and a current board the next best move.
     */
    private class MovesIterator<M extends BoardMove> implements Iterator<M> {

        private TreeSet<String> unused;
        private TreeSet<StartPosition> startPoints;
        String currentWord;
        StartPosition currentPos;
        CrosswordEntryMove currentMove;

        /**
         * 
         * @param unused - a set of unused words.
         * @param startPoints - a tree of possible positions.
         *                     this contains only the big positions and not
         *                     the sub - positions, as described above.
         */
        public MovesIterator(TreeSet<String> unused, TreeSet<StartPosition> startPoints) {
            this.unused = unused;
            this.startPoints = (TreeSet<StartPosition>) startPoints.clone();
            //Initialize
            currentWord = null;
            currentPos = null;
            currentMove = null;
        }

        /**
         * This method, initialize the nextMove along with checking if it is exists.
         * it means that if you call .next() twice a row without calling hasNext()
         * you'l get the same move.
         * @return true - if there is a next possible move
         * false - otherwize.
         */
        @Override
        public boolean hasNext() {
            //Checks if first iteration.
            if (currentPos == null) {
                if (currentWord == null) {
                    //if first iteration, initialize currentPos and currentWord to
                    //be the best word and startPos as decided by the comperators.
                    currentPos = startPoints.first();
                    currentWord = unused.first();
                } else {
                    //if there is no position but there is a word than it means that
                    //the algorithm got stucked and there is no next word.
                    return false;
                }
            } else {
                //if not first iteration, updates the word and position.
                updateWordsPos();
            }
            //Tries to find a suiteble move. Runs until it finds one, or after it finished
            //all the possible tries.
            while (currentPos != null && !isWordFit(currentWord, currentPos)) {
                updateWordsPos();
            }
            //if finished all the possible tries, and hadnt found any match, return false.
            if (currentPos == null) {
                return false;
            }
            //if found a suitble move, saves it for the 'next()' method and returns true.
            currentMove = new CrosswordEntryMove(currentWord, 
                                dictionary.getTermDefinition(currentWord), currentPos);
            return true;
        }

        @Override
        public M next() {
            //See hasNext() description.
            return (M) currentMove;
        }

        /**
         * The main method of the iteration. checks if a given move is a legal move.
         * @param word - a word.
         * @param position - a start position.
         * @return  true if the word can fit the current board in the given position.
         */
        private boolean isWordFit(String word, StartPosition position) {
            //Check if word is longer than place
            if (position.getLength() < word.length()) {
                return false;
            }

            Square currentPosition = board[position.Y][position.X];

            //Check if the position is a start of another word
            if ((position.isVertical() && currentPosition.isStartOfVertical())
                    || (!position.isVertical() && currentPosition.isStartOfHorizontal())) {
                return false;
            }
            //Checks if letters fit.
            for (int n = 0; n < word.length(); n++) {
                if (position.isVertical()) {
                    currentPosition = board[position.Y + n][position.X];
                } else {
                    currentPosition = board[position.Y][position.X + n];
                }
                //whather the box is Frame_Slot or the letters don't fit, return false.
                if (currentPosition.getOverRides() == -1
                        || (word.charAt(n) != currentPosition.getLetter() 
                            && (currentPosition.getLetter() != Square.DEFAULT_CHAR))) {
                    return false;
                }
            }


            return true;
        }

        /**
         * must be called everytime a advance in the iteration is requested.
         * advances one in the unused words to a lesser word, and if finished
         * the list, than delete current position from the tree (all the possible words
         * had been tried on it), creates its 'next' and add it to the tree (notice
         * that the 'next' is allways lesser than its father in the comperator so we can
         * use it safely. You can see more details in the README) and find the next
         * best position.
         * when finished all the possible positions and their children, will
         * initialize the currentPosition as null.
         */
        private void updateWordsPos() {
            currentWord = unused.higher(currentWord);
            //Checks if finished all the words.
            if (currentWord == null) {
                currentWord = unused.first();
                //removes previous position.
                startPoints.remove(currentPos);
                if (currentPos.getNext() != null) {
                    //adds the 'next'
                    startPoints.add(currentPos.getNext());
                }
                //progress the position.
                currentPos = startPoints.higher(currentPos);

            }

        }

        /**
         * not supported and not used.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }
    }
}
