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
import javax.swing.text.Position;

/**
 *
 * @author t7639496
 */
public class MyCrossword implements Crossword, Cloneable{

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
        return unusedWords.isEmpty();
    }

    @Override
    public Iterator<CrosswordEntry> getMovesIterator() {
        TreeSet<StartPosition> position = initializeStartPos.initialStartPos(board);
        return new MovesIterator(unusedWords, position);
    }

    @Override
    public int getQuality() {
        return quality;
    }

    @Override
    public int getQualityBound() {
        
        return 100;
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
            return (SearchBoard<CrosswordEntry>) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(MyCrossword.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private class MovesIterator<M extends BoardMove> implements Iterator<M> {

        private TreeSet<String> unused;
        private TreeSet<StartPosition> startPoints;
        String currentWord;
        StartPosition currentPos;
        CrosswordEntryMove currentMove;

        public MovesIterator(TreeSet<String> unused, TreeSet<StartPosition> startPoints) {
            this.unused = unused;
            this.startPoints = startPoints;
            currentWord = null;
            currentPos = null;
            currentMove = null;
        }

        @Override
        public boolean hasNext() {
             while(!isWordFit(currentWord,currentPos) || currentPos == null){
                  updateWordsPos();
            }
            if(currentPos == null)
                return false;
            currentMove = new CrosswordEntryMove(currentWord, "Shalala", currentPos);
            return true;
        }

        @Override
        public M next() {
           return (M) currentMove;
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
                        || (word.charAt(n) != currentPosition.getLetter() && (word.charAt(n) != '@'))) {
                    return false;
                }
            }


            return true;
        }

        private void updateWordsPos() {
           currentWord = unused.higher(currentWord);
           if(currentWord == null){
               currentWord = unused.first();
               startPoints.remove(currentPos);
               if(currentPos.getNext() != null){
                     startPoints.add(currentPos.getNext());
               }
               currentPos = startPoints.higher(currentPos);
               
           }
           
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
