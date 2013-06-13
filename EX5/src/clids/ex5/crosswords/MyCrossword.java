/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clids.ex5.crosswords;

import clids.ex5.search.BoardMove;
import clids.ex5.search.SearchBoard;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author t7639496
 */
public class MyCrossword implements Crossword {

    private TreeSet<String> unusedWords;
    private Square[][] board;
    private int quality;
    private HashMap<String, CrosswordEntry> usedWords;

    public MyCrossword() {
        quality = 0;
        usedWords = new HashMap<String,CrosswordEntry>();
    }

    @Override
    public void attachDictionary(CrosswordDictionary dictionary) {
        unusedWords = new TreeSet<String>(new wordsComperator());
    }

    @Override
    public void attachStructure(CrosswordStructure structure) {
        board = new Square[structure.getHeight()][structure.getWidth()];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Square(structure.getSlotType(i, j));
            }
        }
    }

    @Override
    public Collection<CrosswordEntry> getCrosswordEntries() {
        return usedWords.values();
    }

    
    @Override
    public boolean isBestSolution() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<CrosswordEntry> getMovesIterator() {
     //   return new MovesIterator<CrosswordEntry>(unused,reshef);
        return null;
    }

    @Override
    public int getQuality() {
        return quality;
    }

    @Override
    public int getQualityBound() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doMove(CrosswordEntry move) {
         for (int n = 0; n < move.getTerm().length(); n++) {
             int x  = move.getPosition().getX();
             int y = move.getPosition().getY();
                if (move.getPosition().isVertical()) {
                    x++;
                } else {
                    y++;
                }
               board[y][x].setLetter(move.getTerm().charAt(n));
               board[y][x].setOverRides( board[y][x].getOverRides() + 1);
               quality = quality + move.getTerm().length();
               unusedWords.remove(move.getTerm());
               usedWords.put(move.getTerm(), move);
            }
    }

    @Override
    public void undoMove(CrosswordEntry move) {
        for (int n = 0; n < move.getTerm().length(); n++) {
             int x  = move.getPosition().getX();
             int y = move.getPosition().getY();
                if (move.getPosition().isVertical()) {
                    x++;
                } else {
                    y++;
                }
                //***************************************************************************
               board[y][x].setOverRides( board[y][x].getOverRides() - 1);
               if(board[x][y].getOverRides() == 0)
                    board[y][x].setLetter('@');
               quality = quality - move.getTerm().length();
               unusedWords.add(move.getTerm());
               usedWords.remove(move.getTerm());
            }
    }

    @Override
    public SearchBoard<CrosswordEntry> getCopy() {
        try {
            return (SearchBoard) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(MyCrossword.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private class MovesIterator<M extends BoardMove> implements Iterator<M> {

        private TreeSet<String> unused;
        private TreeSet<StartPosition> startPoints;
        private int i, j;

        public MovesIterator(TreeSet<String> unused, TreeSet<StartPosition> startPoints) {
            this.startPoints = startPoints;
            this.unused = unused;
            i = 0;
            j = -1;
        }

        @Override
        public boolean hasNext() {
            return (i == startPoints.size());
        }

        @Override
        public M next() {
            updateIJ();
            //while()

            return null;
        }

        private boolean isWordFit(String word, StartPosition position) {
            //Check if word is longer than place
            if (position.getLength() < word.length()) 
                return false;
            
            Square currentPosition = board[position.Y][position.X];

            //Check if the position is a start of another word
            if ((position.isVertical() && currentPosition.isStartOfVertical())
                    || (!position.isVertical() && currentPosition.isStartOfHorizontal())) {
                return false;
            }
            //Checks if letters fit.
            for (int n = 0; n < word.length(); n++) {
                if (position.isVertical()) {
                    currentPosition = board[position.Y][position.X + n];
                } else {
                    currentPosition = board[position.Y + n][position.X];
                }
                //whather the box is Frame_Slot or the letters don't fit, return false.
                if (currentPosition.getOverRides() == -1
                        || (word.charAt(i) != currentPosition.getLetter() && (word.charAt(i) != '@'))) {
                    return false;
                }
            }


            return true;
        }

        private void updateIJ() {
            j++;
            if (j == unused.size()) {
                j = 0;
                i++;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
